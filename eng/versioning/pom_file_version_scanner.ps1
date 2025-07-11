# Copyright (c) Microsoft Corporation. All rights reserved.
# Licensed under the MIT License.

# This script requires Powershell 6 which defaults LocalMachine to Restricted on Windows client machines.
# From a Powershell 6 prompt run 'Get-ExecutionPolicy -List' and if the LocalMachine is Restricted or Undefined then
# run the following command from an admin Powershell 6 prompt 'Set-ExecutionPolicy -ExecutionPolicy RemoteSigned'. This
# will enable running scripts locally in Powershell 6.

# Use case: This script verifies the following:
# 1. There are no duplicate entries in any of the version_*.txt files
# 2. There are no duplicate entries in the external_dependencies.txt file
# 3. POM file verification across the repo which includes the following:
#    a. There are no <dependencyManagement> sections
#    b. Every <dependency> and <plugin> has a <groupId>, <artifactId> and <version>
#    c. Every <version> has the appropriate x-version-update tag
#    d. The <version>'s value is the same as the value in the version_*txt file or external_dependency
#
# Output:
# This script will process the entire repo. If any errors are encountered, it will report them at
# the time they are encountered and continue processing.
# Errors for any duplicate entries in the various version files will be reported before any POM files
# are processed.
# Errors for a given pom file will appear after the "processing pomFile=<fullPathAndFileName>" line for
# that file and before the processing line for the next file. Missing version x-version-update tag
# errors will cause the output of what the tag should be in the cases where it is known (ie. pom file's
# version or parent's version). In the cases of dependencies it'll output a tag and the appropriate
# type (current, dependency or external_dependency) will have to be selected. For example:
# <!-- {x-version-update;<groupId>:<artifactId>;current|dependency|external_dependency<select one>} -->"
#
# This script can be run locally from the root of the repo. .\eng\versioning\pom_file_version_scanner.ps1

[CmdletBinding()]
param()

# Since we're skipping Management for the moment, only look for files with certain parents. These
# limitations will vanish once Management track is updated.
$ValidParents = ("azure-sdk-parent", "azure-client-sdk-parent", "azure-data-sdk-parent", "azure-perf-test-parent", "azure-cosmos-spark_3_2-12", "io.clientcore:clientcore-parent")

# SpringSampleParents is necessary for the spring samples which have to build using the spring-boot-starter-parent BOM.
# The problem with this is, it's a BOM file and the spring dependencies are pulled in through that which means any
# dependencies may or may not have versions. Unfortunately, there are still version tags azure sdk client libraries
# which means these files have to be "sort of" scanned.
$SpringSampleParents = ("spring-boot-starter-parent", "azure-spring-boot-test-parent")

. "${PSScriptRoot}/../common/scripts/Helpers/PSModule-Helpers.ps1"
$Path = Resolve-Path ($PSScriptRoot + "/../../")
$SamplesPath = Resolve-Path ($PSScriptRoot + "/../../samples")
$SdkRoot = Resolve-Path ($PSScriptRoot + "/../../sdk")

# Not all POM files have a parent entry
# $PomFilesIgnoreParent = ("$($Path)\parent\pom.xml")
$script:FoundError = $false
$DependencyTypeCurrent = "current"
$DependencyTypeDependency = "dependency"
$DependencyTypeExternal = "external_dependency"
$DependencyTypeForError = "$($DependencyTypeCurrent)|$($DependencyTypeDependency)|$($DependencyTypeExternal)"
$UpdateTagFormat = "{x-version-update;<groupId>:<artifactId>;$($DependencyTypeForError)}"
$UseVerboseLogging = $PSBoundParameters['Debug'] -or $PSBoundParameters['Verbose']

if ($UseVerboseLogging) {
    Write-Host "SdkRoot=$SdkRoot"
}

Install-ModuleIfNotInstalled "powershell-yaml" "0.4.1" | Import-Module

$StartTime = $(get-date)

# This is the for the bannedDependencies include exceptions. All <include> entries need to be of the
# form <include>groupId:artifactId:[version]</include> which locks to a specific version. The exception
# to this is the blanket, wildcard include for com.azure and com.microsoft.azure libraries.
$ComAzureAllowlistIncludes = ("com.azure:*", "com.azure.v2:*", "com.azure.resourcemanager:*", "com.microsoft.azure:*", "com.azure.spring:*", "io.clientcore:*")

function Join-With-NewLine([string]$msg, [string]$append)
{
    return $msg + [Environment]::NewLine + $append
}

function Write-Log-Message([string]$msg, [boolean]$hasError)
{
    if ($hasError) {
        $script:FoundError = $true
        Write-Error-With-Color $potentialLogMessage
    } elseif ($UseVerboseLogging) {
        Write-Host $potentialLogMessage
    }
}

function Write-Error-With-Color([string]$msg)
{
    Write-Host "$($msg)" -ForegroundColor Red
}

# The expected format for a depenency, as found in the eng\versioning\version_*.txt files, is as follows:
# groupId:artifactId;dependency-version;current-version
class Dependency {
    [string]$id
    [string]$depVer
    [string]$curVer
    Dependency(
        [string]$inputString
    ){
        $split = $inputString.Split(";")
        if (($split.Count -ne 3) -and ($split.Count -ne 2))
        {
            # throw and let the caller handle the error since it'll have access to the
            # filename of the file with the malformed line for reporting
            throw
        }
        $this.id = $split[0]
        $this.depVer = $split[1]
        if ($split.Count -eq 3)
        {
            $this.curVer = $split[2]
        }
    }
}

# The expected format for an external depenency, as found in the eng\versioning\external_dependencies.txt file, is as follows:
# groupId:artifactId;dependency-version
class ExternalDependency {
    [string]$id
    [string]$ver
    ExternalDependency(
        [string]$inputString
    ){
        $split = $inputString.Split(";")
        if ($split.Count -ne 2)
        {
            # throw and let the caller handle the error since it'll have access to the
            # filename of the file with the malformed line for reporting
            throw
        }
        $this.id = $split[0]
        $this.ver = $split[1]
    }
}

# Create a dictionary of libraries per sdk/<ServiceDirectory>. The Key will be the directory
# and the Value will be a HashSet of the libraries produced. Note: This looks at ci*.yml meaning
# that it picks it'll pick up both track 1 and track 2 libraries. This is okay to join
# since the libraries produced by both tracks have different GroupIds and even then, have different
# ArtifactIds between tracks. Further, there are no interdependencies between track 1 and track 2
# libraries.
# The purpose of collecting this data is to verify that all interdependencies within an ServiceDirectory
# are using current versions of other libraries built and released from the same ServiceDirectory.
# Similarly, this can also be used to ensure that any dependencies not part of a given ServiceDirectory
# only use the dependency versions.
# It's also worth noting that the artifacts list is pulled from the ci.yml files. The search goes
# depth 3 due to the fact that there are some libraries whose pipelines yml files are one level deeper
# than the sdk/<SerciceDirectory>. They're in the sdk/<ServiceDirectory>/<LibraryDirectory>/ci.yml and
# sdk/communication is an example of this as each library has its own pipeline.
function Get-ArtifactsList-Per-Service-Directory {
    param([System.Collections.Specialized.OrderedDictionary]$ArtifactsPerSD)

    # Get all of the yml files under sdk/
    $ymlFiles = Get-ChildItem -Path $SdkRoot -Recurse -Depth 3 -File -Filter "ci*yml"
    if (-not $ymlFiles) {
        Write-Error "Unable to get yml files for the repository. If this is a sparse-checkout please ensure '**/*.yml' is one of the paths."
        exit 1
    }
    foreach ($ymlFile in $ymlFiles) {
        if ($UseVerboseLogging) {
            Write-Host "Processing yml file: $ymlFile"
        }
        # The ci.cosmos.yml lives in spring and is used to test the cosmos spring library. Its exception
        # will be moved once things are corrected.
        if ($ymlFile.FullName.Split([IO.Path]::DirectorySeparatorChar) -contains "resourcemanagerhybrid" -or
            $ymlFile.Name -eq "ci.cosmos.yml") {
            continue
        }
        # The path is going to be the key. Since there can be multiple yml files for a single path,
        # (ci.yml and ci.mgmt.yml),
        $ymlPath = Split-Path $ymlFile
        if (-not $ArtifactsPerSD.Contains($ymlPath)) {
            $artifactHashSet = New-Object 'System.Collections.Generic.HashSet[String]'
            $ArtifactsPerSD.Add($ymlPath, $artifactHashSet)
        }

        $ymlContent = Get-Content $ymlFile.FullName -Raw
        $ymlObject = ConvertFrom-Yaml $ymlContent -Ordered
        # Get each artifact that is built/released as part of this yml file
        foreach ($artifact in $ymlObject["extends"]["parameters"]["artifacts"]) {
            $libFullName = $artifact["groupId"] + ":" + $artifact["name"]
            # The same artifact in different yml files or twice in the same file is bad.
            if (-not $ArtifactsPerSD[$ymlPath].Add($libFullName)) {
                Write-Error "Processing yml file $($ymlFile.FullName)"
                Write-Error "$ymlPath already contains an Artifact entry for $libFullName"
            }
            elseif ($UseVerboseLogging) {
                Write-Host "    Adding Artifact: $libFullName"
            }
        }
        # These list of modules per sdk/<ServiceDirectory> has to be verified to be using the
        # latest version of other modules in the same ServiceDirectory which includes AdditionModules.
        # The groupIds will ensure that track1 and track2 libraries won't collide in here. In the
        # case of AdditionalModules, com.azure:perf-test-core is ubiquitous and can appear in the
        # AdditionalModules of ci.yml and ci.mgmt.yml. As such, it's the only library we'll ignore
        # a dupe of.
        foreach ($artifact in $ymlObject["extends"]["parameters"]["AdditionalModules"]) {
            $libFullName = $artifact["groupId"] + ":" + $artifact["name"]
            if (-not $ArtifactsPerSD[$ymlPath].Add($libFullName)) {
                if ($libFullName -ne "com.azure:perf-test-core") {
                    Write-Error "Processing yml file $($ymlFile.FullName)"
                    Write-Error "$ymlPath already contains an AdditionalModule entry for $libFullName"
                }
            }
            elseif ($UseVerboseLogging) {
                Write-Host "    Adding AdditionalModules: $libFullName"
            }
        }
    }
}

# Given the full path to a POM file, return the HashSet containing the list
# of libraries built and released from that service directory, if there is
# one, null otherwise.
function Get-Artifacts-Built-In-Service-Directory {
    param(
        [System.Collections.Specialized.OrderedDictionary]$ArtifactsPerSD,
        [string]$SdkRoot,
        [string]$PomFileFullPath
        )

        # There are POM files that aren't in under under /sdk. Those
        # aren't verified for current/dependency since they don't release
        # and won't have entries in the ArtifactsPerSD
        if ($PomFileFullPath.StartsWith($SdkRoot)) {
            $tmpFile = $PomFileFullPath
            while ($tmpFile -ne $SdkRoot) {
                if ($ArtifactsPerSD.Contains($tmpFile)) {
                    return $ArtifactsPerSD[$tmpFile]
                } else {
                    $tmpFile = Split-Path $tmpFile -Parent
                }
            }
        }
        return $null
}

function Build-Dependency-Hash-From-File {
    param(
        [hashtable]$depHash,
        [string]$depFile,
        [boolean]$extDepHash)
    foreach($line in Get-Content $depFile)
    {
        if (!$line -or $line.Trim() -eq '' -or $line.StartsWith("#"))
        {
            continue
        }
        if (!$extDepHash)
        {
            try {
                [Dependency]$dep = [Dependency]::new($line)
                # This is the case where there is no current version but dependency isn't
                # "unreleased_" or "beta_" meaning that the line is either incorrect or
                # has an invalid format
                if (-not $dep.curVer -and (-not $dep.Id.StartsWith("unreleased_")) -and (-not $dep.Id.StartsWith("beta_"))) {
                    Write-Error-With-Color "Invalid dependency line='$($line) in file=$($depFile) has no current version and is not unreleased_ or beta_.`nPlease ensure the format is <groupId>:<artifactId>;<dependencyVer>;<currentVer> and the seperators are semicolons (;)"
                    $script:FoundError = $true
                }
                if ($depHash.ContainsKey($dep.id))
                {
                    Write-Error-With-Color "Error: Duplicate dependency encountered. '$($dep.id)' defined in '$($depFile)' already exists in the dependency list which means it is defined in multiple version_*.txt files."
                    $script:FoundError = $true
                    continue
                }
                $depHash.Add($dep.id, $dep)
            }
            catch {
                Write-Error-With-Color "Invalid dependency line='$($line) in file=$($depFile)"
                $script:FoundError = $true
            }
        }
        else
        {
            try {
                [ExternalDependency]$dep = [ExternalDependency]::new($line)
                if ($depHash.ContainsKey($dep.id))
                {
                    Write-Error-With-Color "Error: Duplicate external_dependency encountered. '$($dep.id)' has a duplicate entry defined in '$($depFile)'. Please ensure that all entries are unique."
                    $script:FoundError = $true
                    continue
                }
                $depHash.Add($dep.id, $dep)
            }
            catch {
                Write-Error-With-Color "Invalid external dependency line='$($line) in file=$($depFile)"
                $script:FoundError = $true
            }
        }
    }
}

function Test-Dependency-Tag-And-Version {
    param(
        [hashtable]$libHash,
        [hashtable]$extDepHash,
        [string]$versionString,
        [string]$versionUpdateString,
        [System.Collections.Generic.HashSet[string]]$libPerSDHash = $null)

    # This is the format of the versionUpdateString and there should be 3 parts:
    # 1. The update tag, itself eg. x-version-update
    # 2. The <groupId>:<artifactId> which is verified using the hash lookup
    # 3. The dependency type which will be current or dependency or external_dependency

    # instead of creating the key from the groupId/artifactId it's necessary to pull the key
    # from the versionUpdateString in case it ends up being one of the dependency exceptions
    # which will have a <unique identifier>_ prepended to the groupId:artifactId
    $split = $versionUpdateString.Trim().Split(";")
    if ($split.Count -ne 3)
    {
        return "Error: malformed dependency update tag='$($versionUpdateString)'. The dependency tag should have the following format: $($UpdateTagFormat)"
    }
    $depKey = $split[1]
    $depType = $split[2]
    # remove the trailing end brace
    if (-not $depType.EndsWith("}"))
    {
        return "Error: malformed dependency update tag='$($versionUpdateString)' is missing the end brace."
    }
    $depType = $depType.Substring(0, $depType.IndexOf("}"))

    if ($depType -eq $DependencyTypeExternal)
    {
        if (!$extDepHash.ContainsKey($depKey))
        {
            return "Error: external_dependency '$($depKey)' does not exist in the external_dependencies. Please ensure the dependency type is correct or the dependency is appropriately added to the file."
        }
        else
        {
            if ($versionString -ne $extDepHash[$depKey].ver)
            {
                return "Error: $($depKey)'s <version> is '$($versionString)' but the external_dependency version is listed as $($extDepHash[$depKey].ver)"
            }
        }
    }
    # at this point the dependency type is "current" or "dependency"
    else
    {
        if (!$libHash.ContainsKey($depKey))
        {
            return "Error: $($depKey)'s dependency type is '$($depType)' but the dependency does not exist in any of the version_*.txt files. Should this be an external_dependency? Please ensure the dependency type is correct or the dependency is added to the appropriate file."
        }
        else
        {
            if ($depType -eq $DependencyTypeDependency)
            {
                # Any libraries built as part of the same pipeline need to use the current
                # version of other libraries within the same pipeline. Verify that this
                # dependency, of type dependency, shouldn't be current
                if ($libPerSDHash) {
                    if ($libPerSDHash.Contains($depKey)) {
                        return "Error: $($versionUpdateString.Trim()) is incorrectly set to $DependencyTypeDependency. Libraries building and releasing as part of the same pipeline need to be using the $DependencyTypeCurrent versions of each other."
                    }
                }
                if ($versionString -ne $libHash[$depKey].depVer)
                {
                    return "Error: $($depKey)'s <version> is '$($versionString)' but the dependency version is listed as $($libHash[$depKey].depVer)"
                }
            }
            elseif ($depType -eq $DependencyTypeCurrent)
            {
                # Verify that none of the 'current' dependencies are using a groupId that starts with 'unreleased_' or 'beta_'
                if ($depKey.StartsWith('unreleased_') -or $depKey.StartsWith('beta_'))
                {
                    return "Error: $($versionUpdateString.Trim()) is using an unreleased_ or beta_ dependency and trying to set current value. Only dependency versions can be set with an unreleased or beta dependency."
                }
                if ($libPerSDHash) {
                    if (-not $libPerSDHash.Contains($depKey)) {
                        return "Error: $($versionUpdateString.Trim()) is incorrectly set to $DependencyTypeCurrent. Only libraries building and releasing as part of the same pipeline should be using the $DependencyTypeCurrent versions of each other."
                    }
                }
                if ($versionString -ne $libHash[$depKey].curVer)
                {
                    return "Error: $($depKey)'s <version> is '$($versionString)' but the current version is listed as $($libHash[$depKey].curVer)"
                }
            }
            # At this point the version update string, itself, has an incorrect dependency tag
            else
            {
                return "Error: Invalid dependency type '$($depType)' in version update string $($versionUpdateString). Dependency type must be one of $($DependencyTypeForError)"
            }
        }
    }
}

# There are some configurations, like org.apache.maven.plugins:maven-enforcer-plugin,
# that have plugin and dependency configuration entries that are string patterns. This
# function will be called if the groupId and artifactId for a given plugin or dependency
# are both empty. It'll climb up the parents it finds a configuration entry or there are
# no more parents. If the node is part of a configuration entry then return true, otherwise
# return false.
function Confirm-Node-Is-Part-Of-Configuration {
    param(
        [System.Xml.XmlNode]$theNode
    )
    # Climbing up the parents the nodes will be System.Xml.XmlElement until we're at the very end
    # which will have a type of just 'xml'. If we encounter a configuration node return true
    # otherwise return false.
    while ($theNode.GetType() -ieq [System.Xml.XmlElement]) {
        if ($theNode.Name -ieq 'configuration')
        {
            return $true
        }
        $theNode = $theNode.ParentNode
    }
    return $false
}

# Spring samples will pull in most dependencies through use of the spring bom. Any dependency that is an
# an azure sdk client dependency needs to be verified and must have a groupId, artifactId, version and version tag.
# Similarly, any dependency with a version needs to have a version tag. Dependencies without a version tag are
# ignored as those are assumed to be coming from the BOM.
function Assert-Spring-Sample-Version-Tags {
    param(
        [hashtable]$libHash,
        [hashtable]$extDepHash,
        [xml]$xmlPomFile
    )

    $potentialLogMessage = "processing Spring Sample pomFile=$($pomFile)"
    $hasError = $false
    $xmlNsManagerSpring = New-Object -TypeName "Xml.XmlNamespaceManager" -ArgumentList $xmlPomFile.NameTable
    $xmlNsManagerSpring.AddNamespace("ns", $xmlPomFile.DocumentElement.NamespaceURI)

    if (-not $xmlPomFile.project.parent.groupId)
    {
        $hasError = $true
        $potentialLogMessage = Join-With-NewLine $potentialLogMessage "Error: parent/groupId is missing."
    }

    $versionNode = $xmlPomFile.SelectSingleNode("/ns:project/ns:parent/ns:version", $xmlNsManagerSpring)
    if (-not $versionNode)
    {
        $hasError = $true
        $potentialLogMessage = Join-With-NewLine $potentialLogMessage "Error: parent/version is missing."
        $potentialLogMessage = Join-With-NewLine $potentialLogMessage "Error: Missing project/version update tag. The tag should be <!-- {x-version-update;$($groupId):$($artifactId);current} -->"
    } else {
        $retVal = Test-Dependency-Tag-And-Version $libHash $extDepHash $versionNode.InnerText.Trim() $versionNode.NextSibling.Value
        if ($retVal)
        {
            $hasError = $true

            $potentialLogMessage = Join-With-NewLine $potentialLogMessage "$($retVal)"
        }
    }

    # Loop through the dependencies. If any dependency is in the libHash (aka, the libraries we build)
    # then it needs to have a version element and update tag.
    foreach($dependencyNode in $xmlPomFile.GetElementsByTagName("dependency"))
    {
        $artifactId = $dependencyNode.artifactId
        $groupId = $dependencyNode.groupId
        # If the artifactId and groupId are both empty then check to see if this
        # is part of a configuration entry. If so then just continue.
        if (!$artifactId -and !$groupId)
        {
            $isPartOfConfig = Confirm-Node-Is-Part-Of-Configuration $dependencyNode
            if (!$isPartOfConfig)
            {
                $hasError = $true
                # Because this particular case is harder to track down, print the OuterXML which is effectively the entire tag
                $potentialLogMessage = Join-With-NewLine $potentialLogMessage "Error: dependency is missing version element and/or artifactId and groupId elements dependencyNode=$($dependencyNode.OuterXml)"
            }
            continue
        }
        $hashKey = "$($groupId):$($artifactId)"
        $versionNode = $dependencyNode.GetElementsByTagName("version")[0]
        # If this is something we build and release, it better have a version and a version tag
        if ($libHash.ContainsKey($hashKey))
        {
            if (-not $versionNode)
            {
                $hasError = $true
                $potentialLogMessage = Join-With-NewLine $potentialLogMessage "Error: dependency is missing version element and tag groupId=$($groupId), artifactId=$($artifactId) should be <version></version> <!-- {x-version-update;$($groupId):$($artifactId);current|dependency|external_dependency<select one>} -->"
            } else {
                # verify the version tag and version are correct
                if ($versionNode.NextSibling -and $versionNode.NextSibling.NodeType -eq "Comment")
                {
                    $retVal = Test-Dependency-Tag-And-Version $libHash $extDepHash $versionNode.InnerText.Trim() $versionNode.NextSibling.Value
                    if ($retVal)
                    {
                        $hasError = $true
                        $potentialLogMessage = Join-With-NewLine $potentialLogMessage "$($retVal)"
                    }
                } else {
                    $hasError = $true
                    $potentialLogMessage = Join-With-NewLine $potentialLogMessage "Error: dependency is missing version tag groupId=$($groupId), artifactId=$($artifactId) tag should be <!-- {x-version-update;$($groupId):$($artifactId);current|dependency|external_dependency<select one>} -->"
                }
            }
        } else {
            # else, if there's a version tag verify it, otherwise just skip it since the version should be coming
            # from the bom
            if ($versionNode)
            {
                if  ($versionNode.NextSibling -and $versionNode.NextSibling.NodeType -eq "Comment")
                {
                    $retVal = Test-Dependency-Tag-And-Version $libHash $extDepHash $versionNode.InnerText.Trim() $versionNode.NextSibling.Value
                    if ($retVal)
                    {
                        $hasError = $true
                        $potentialLogMessage = Join-With-NewLine $potentialLogMessage "$($retVal)"
                    }
                # If there's no version tag then error, if there's a version then it must be tagged
                } else {
                    $hasError = $true
                    $potentialLogMessage = Join-With-NewLine $potentialLogMessage "Error: dependency is missing version tag groupId=$($groupId), artifactId=$($artifactId) tag should be <!-- {x-version-update;$($groupId):$($artifactId);current|dependency|external_dependency<select one>} -->"
                }
            }
        }
    }

    Write-Log-Message $potentialLogMessage $hasError
}

$ArtifactsPerSD = [ordered]@{};
Get-ArtifactsList-Per-Service-Directory $ArtifactsPerSD

# Create one dependency hashtable for libraries we build (the groupIds will make the entries unique) and
# one hash for external dependencies. If there are errors building the dependency hash, output those and
# exit. There's no point in scanning the pom files if any of the files have invalid or duplicate entries.
$libHash = @{}
$verClientFile = Join-Path $Path "eng/versioning/version_client.txt"
Build-Dependency-Hash-From-File $libHash $verClientFile $false
if ($script:FoundError)
{
    Write-Error-With-Color "There were errors encountered building the dependency hash from version_client.txt. Please fix errors and run the script again."
    Write-Error-With-Color "This script can be run locally from the root of the repo. .\eng\versioning\pom_file_version_scanner.ps1"
    exit 1
}

$extDepHash = @{}
$extDepFile = Join-Path $Path "eng/versioning/external_dependencies.txt"
Build-Dependency-Hash-From-File $extDepHash $extDepFile $true
if ($script:FoundError)
{
    Write-Error-With-Color "There were errors encountered building the external dependency has from external_dependencies.txt. Please fix errors and run the script again."
    Write-Error-With-Color "This script can be run locally from the root of the repo. .\eng\versioning\pom_file_version_scanner.ps1"
    exit 1
}

# Loop through every client and data POM file and perform the verification. Right now
# management isn't being processed, when it is the checks below will go away and every
# POM file under the sdk directory will get processed.
Get-ChildItem -Path $Path -Filter pom*.xml -Recurse -File | ForEach-Object {
    $pomFile = $_.FullName
    $xmlPomFile = $null

    if ($_.FullName -like "*azure-arm-parent*")
    {
        return
    }

    # Exclude everything that's in samples as this folder contains end to end samples
    # that uses BOM and some dependencies will not have versions as they are deduced from
    # the BOM. Running version checks on these samples will always fail. So, we'll skip
    # all version checks in the root samples directory.
    if ($_.FullName.StartsWith($SamplesPath))
    {
        return
    }

    # azure-core-jackson-tests verifies compatibility with different
    # Jackson versions, it should be excluded from version checks
    if ($_.FullName -like "*azure-core-jackson-tests*")
    {
        return
    }

    # Packages under sdk/resourcemanagerhybrid has duplicate artifactId with that under sdk/resourcemanager
    if ($_.FullName -like "*resourcemanagerhybrid*")
    {
        return
    }

    # Code customization packages should be excluded.
    if ($_.FullName -like "*swagger*")
    {
        return
    }

    $xmlPomFile = New-Object xml
    $xmlPomFile.Load($pomFile)
    if ($ValidParents -notcontains $xmlPomFile.project.parent.artifactId -and $ValidParents -notcontains $xmlPomFile.project.artifactId)
    {
        if ($SpringSampleParents -contains $xmlPomFile.project.parent.artifactId)
        {
            Assert-Spring-Sample-Version-Tags $libHash $extDepHash $xmlPomFile
        }
        # This may look odd but ForEach-Object is a cmdlet which means that "continue"
        # exits the loop altogether and "return" behaves like continue for a particular
        # loop
        return
    }

    $hasError = $false
    $potentialLogMessage = "processing pomFile=$($pomFile)"
    if ($xmlPomFile.project.dependencyManagement)
    {
        $hasError = $true
        $potentialLogMessage = Join-With-NewLine $potentialLogMessage "Error: <dependencyManagement> is not allowed. Every dependency must have its own version and version update tag"
    }

    $xmlNsManager = New-Object -TypeName "Xml.XmlNamespaceManager" -ArgumentList $xmlPomFile.NameTable
    $xmlNsManager.AddNamespace("ns", $xmlPomFile.DocumentElement.NamespaceURI)

    # Ensure that the project has a version tag with the exception of projects under the eng directory which
    # aren't releasing libraries but still need to have their dependencies checked
    if ($pomFile.Split([IO.Path]::DirectorySeparatorChar) -notcontains "eng")
    {
        $versionNode = $xmlPomFile.SelectSingleNode("/ns:project/ns:version", $xmlNsManager)
        if ($xmlPomFile.project.version -and $versionNode)
        {
            $artifactId = $xmlPomFile.project.artifactId
            $groupId = $xmlPomFile.project.groupId
            if ($versionNode.NextSibling -and $versionNode.NextSibling.NodeType -eq "Comment")
            {
                # the project's version will always be an update type of "current"
                if ($versionNode.NextSibling.Value.Trim() -ne "{x-version-update;$($groupId):$($artifactId);current}")
                {
                    $hasError = $true
                    # every project string needs to have an update tag and projects version tags are always 'current'
                    $potentialLogMessage = Join-With-NewLine $potentialLogMessage "Error: project/version update tag should be <!-- {x-version-update;$($groupId):$($artifactId);current} -->"
                }
                else
                {
                    # verify the version tag and version are correct
                    $retVal = Test-Dependency-Tag-And-Version $libHash $extDepHash $versionNode.InnerText.Trim() $versionNode.NextSibling.Value
                    if ($retVal)
                    {
                        $hasError = $true
                        $potentialLogMessage = Join-With-NewLine $potentialLogMessage "$($retVal)"
                    }
                }
            }
            else
            {
                $hasError = $true
                # <!-- {x-version-update;<groupId>:<artifactId>;current} -->
                # every project string needs to have an update tag and projects version tags are always 'current'
                $potentialLogMessage = Join-With-NewLine $potentialLogMessage "Error: Missing project/version update tag. The tag should be <!-- {x-version-update;$($groupId):$($artifactId);current} -->"
            }

        }
        else
        {
            # output an error for missing version element
            $hasError = $true
            $potentialLogMessage = Join-With-NewLine $potentialLogMessage "Error: Could not find project/version node for $($pomFile)"
        }
    }

    if ($xmlPomFile.project.parent) {
        # Verify the parent's version
        $versionNode = $xmlPomFile.SelectSingleNode("/ns:project/ns:parent/ns:version", $xmlNsManager)
        if ($xmlPomFile.project.parent.version -and $versionNode)
        {
            $artifactId = $xmlPomFile.project.parent.artifactId
            $groupId = $xmlPomFile.project.parent.groupId
            # versionNode.NextSibling.Value should be the actual XML tag starting with {x-version-update
            if ($versionNode.NextSibling -and $versionNode.NextSibling.NodeType -eq "Comment")
            {
                # parent version
                if ($versionNode.NextSibling.Value.Trim() -ne "{x-version-update;$($groupId):$($artifactId);current}")
                {
                    $hasError = $true
                    $potentialLogMessage = Join-With-NewLine $potentialLogMessage "Error: project/parent/version update tag should be <!-- {x-version-update;$($groupId):$($artifactId);current} -->"
                }
                else
                {
                    # verify the version tag and version are correct
                    $retVal = Test-Dependency-Tag-And-Version $libHash $extDepHash $versionNode.InnerText.Trim() $versionNode.NextSibling.Value
                    if ($retVal)
                    {
                        $hasError = $true
                        $potentialLogMessage = Join-With-NewLine $potentialLogMessage "$($retVal)"
                    }
                }
            }
            else
            {
                $hasError = $true
                # every project string needs to have an update tag and projects version tags are always 'current'
                $potentialLogMessage = Join-With-NewLine $potentialLogMessage "Error: Missing project/parent/version update tag. The tag should be <!-- {x-version-update;$($groupId):$($artifactId);current} -->"
            }
        }
        else
        {
            # output an error for missing version element
            $hasError = $true
            $potentialLogMessage = Join-With-NewLine $potentialLogMessage "Error: Could not find project/parent/version node for $($pomFile)"
        }
    }

    $artifactsPerSDHashSet = Get-Artifacts-Built-In-Service-Directory $ArtifactsPerSD $SdkRoot $pomFile

    # Verify every dependency as a group, artifact and version
    # GetElementsByTagName should get all dependencies including dependencies under plugins
    foreach($dependencyNode in $xmlPomFile.GetElementsByTagName("dependency"))
    {
        $artifactId = $dependencyNode.artifactId
        $groupId = $dependencyNode.groupId
        # If the artifactId and groupId are both empty then check to see if this
        # is part of a configuration entry. If so then just continue.
        if (!$artifactId -and !$groupId)
        {
            $isPartOfConfig = Confirm-Node-Is-Part-Of-Configuration $dependencyNode
            if (!$isPartOfConfig)
            {
                $hasError = $true
                # Because this particular case is harder to track down, print the OuterXML which is effectively the entire tag
                $potentialLogMessage = Join-With-NewLine $potentialLogMessage "Error: dependency is missing version element and/or artifactId and groupId elements dependencyNode=$($dependencyNode.OuterXml)"
            }
            continue
        }

        $versionNode = $dependencyNode.GetElementsByTagName("version")[0]
        if (!$versionNode)
        {
            $hasError = $true
            $potentialLogMessage = Join-With-NewLine $potentialLogMessage "Error: dependency is missing version element for groupId=$($groupId), artifactId=$($artifactId) should be <version></version> <!-- {x-version-update;$($groupId):$($artifactId);current|dependency|external_dependency<select one>} -->"
            continue
        }
        if ($versionNode.NextSibling -and $versionNode.NextSibling.NodeType -eq "Comment")
        {
            # unfortunately because there are POM exceptions we need to wildcard the group which may be
            # something like <area>_groupId
            if ($versionNode.NextSibling.Value.Trim() -notmatch "{x-version-update;(.+)?$($groupId):$($artifactId);\w+}")
            {
                $hasError = $true
                $potentialLogMessage = Join-With-NewLine $potentialLogMessage "Error: dependency version update tag for groupId=$($groupId), artifactId=$($artifactId) should be <!-- {x-version-update;$($groupId):$($artifactId);current|dependency|external_dependency<select one>} -->"
            }
            else
            {
                # verify the version tag and version are correct
                $retVal = Test-Dependency-Tag-And-Version $libHash $extDepHash $versionNode.InnerText.Trim() $versionNode.NextSibling.Value $artifactsPerSDHashSet
                if ($retVal)
                {
                    $hasError = $true
                    $potentialLogMessage = Join-With-NewLine $potentialLogMessage $retVal
                }
            }
        }
        else
        {
            $hasError = $true
            $potentialLogMessage = Join-With-NewLine $potentialLogMessage "Error: Missing dependency version update tag for groupId=$($groupId), artifactId=$($artifactId). The tag should be <!-- {x-version-update;$($groupId):$($artifactId);current|dependency|external_dependency<select one>} -->"
        }
    }
    # Verify every plugin has a group, artifact and version
    # Verify every dependency has a group, artifact and version
    # GetElementsByTagName should get all dependencies including dependencies under plugins
    foreach($pluginNode in $xmlPomFile.GetElementsByTagName("plugin"))
    {
        $artifactId = $pluginNode.artifactId
        $groupId = $pluginNode.groupId
        # If the artifactId and groupId are both empty then check to see if this
        # is part of a configuration entry.
        if (!$artifactId -and !$groupId)
        {
            $isPartOfConfig = Confirm-Node-Is-Part-Of-Configuration $pluginNode
            if (!$isPartOfConfig)
            {
                $hasError = $true
                # Because this particular case is harder to track down, print the OuterXML which is effectively the entire tag
                $potentialLogMessage = Join-With-NewLine $potentialLogMessage "Error: plugin is missing version element and/or artifactId and groupId elements pluginNode=$($pluginNode.OuterXml)"
            }
            continue
        }
        # plugins should always have an artifact but may not have a groupId
        if (!$groupId)
        {
            $hasError = $true
            $potentialLogMessage = Join-With-NewLine $potentialLogMessage "Error: plugin $($artifactId) is missing its groupId tag"
            continue
        }
        $versionNode = $pluginNode.GetElementsByTagName("version")[0]
        if (!$versionNode)
        {
            $hasError = $true
            $potentialLogMessage = Join-With-NewLine $potentialLogMessage "Error: plugin is missing version element for groupId=$($groupId), artifactId=$($artifactId) should be <version></version> <!-- {x-version-update;$($groupId):$($artifactId);current|dependency|external_dependency<select one>} -->"
            continue
        }
        if ($versionNode.NextSibling -and $versionNode.NextSibling.NodeType -eq "Comment")
        {
            # unfortunately because there are POM exceptions we need to wildcard the group which may be
            # something like <area>_groupId
            if ($versionNode.NextSibling.Value.Trim() -notmatch "{x-version-update;(.+)?$($groupId):$($artifactId);\w+}")
            {
                $hasError = $true
                $potentialLogMessage = Join-With-NewLine $potentialLogMessage "Error: plugin version update tag for groupId=$($groupId), artifactId=$($artifactId) should be <!-- {x-version-update;$($groupId):$($artifactId);current|dependency|external_dependency<select one>} -->"
            }
            else
            {
                # verify the version tag and version are correct
                $retVal = Test-Dependency-Tag-And-Version $libHash $extDepHash $versionNode.InnerText.Trim() $versionNode.NextSibling.Value
                if ($retVal)
                {
                    $hasError = $true
                    $potentialLogMessage = Join-With-NewLine $potentialLogMessage "$($retVal)"
                }
            }
        }
        else
        {
            $hasError = $true
            $potentialLogMessage = Join-With-NewLine $potentialLogMessage "Error: Missing plugin version update tag for groupId=$($groupId), artifactId=$($artifactId). The tag should be <!-- {x-version-update;$($groupId):$($artifactId);current|dependency|external_dependency<select one>} -->"
        }
    }

    foreach($signatureNode in $xmlPomFile.GetElementsByTagName("signature"))
    {
        $artifactId = $signatureNode.artifactId
        $groupId = $signatureNode.groupId
        # If the artifactId and groupId are both empty then check to see if this
        # is part of a configuration entry.
        if (!$artifactId -and !$groupId)
        {
            $isPartOfConfig = Confirm-Node-Is-Part-Of-Configuration $signatureNode
            if (!$isPartOfConfig)
            {
                $hasError = $true
                # Because this particular case is harder to track down, print the OuterXML which is effectively the entire tag
                $potentialLogMessage = Join-With-NewLine $potentialLogMessage "Error: signature is missing version element and/or artifactId and groupId elements signatureNode=$($signatureNode.OuterXml)"
            }
            continue
        }
        # signatures should always have an artifact but may not have a groupId
        if (!$groupId)
        {
            $hasError = $true
            $potentialLogMessage = Join-With-NewLine $potentialLogMessage "Error: signature $($artifactId) is missing its groupId tag"
            continue
        }
        $versionNode = $signatureNode.GetElementsByTagName("version")[0]
        if (!$versionNode)
        {
            $hasError = $true
            $potentialLogMessage = Join-With-NewLine $potentialLogMessage "Error: signature is missing version element for groupId=$($groupId), artifactId=$($artifactId) should be <version></version> <!-- {x-version-update;$($groupId):$($artifactId);current|dependency|external_dependency<select one>} -->"
            continue
        }
        if ($versionNode.NextSibling -and $versionNode.NextSibling.NodeType -eq "Comment")
        {
            # unfortunately because there are POM exceptions we need to wildcard the group which may be
            # something like <area>_groupId
            if ($versionNode.NextSibling.Value.Trim() -notmatch "{x-version-update;(.+)?$($groupId):$($artifactId);\w+}")
            {
                $hasError = $true
                $potentialLogMessage = Join-With-NewLine $potentialLogMessage "Error: signature version update tag for groupId=$($groupId), artifactId=$($artifactId) should be <!-- {x-version-update;$($groupId):$($artifactId);current|dependency|external_dependency<select one>} -->"
            }
            else
            {
                # verify the version tag and version are correct
                $retVal = Test-Dependency-Tag-And-Version $libHash $extDepHash $versionNode.InnerText.Trim() $versionNode.NextSibling.Value
                if ($retVal)
                {
                    $hasError = $true
                    $potentialLogMessage = Join-With-NewLine $potentialLogMessage "$($retVal)"
                }
            }
        }
        else
        {
            $hasError = $true
            $potentialLogMessage = Join-With-NewLine $potentialLogMessage "Error: Missing signature version update tag for groupId=$($groupId), artifactId=$($artifactId). The tag should be <!-- {x-version-update;$($groupId):$($artifactId);current|dependency|external_dependency<select one>} -->"
        }
    }

    # This is for the allowlist dependencies. Fetch the banned dependencies
    foreach($bannedDependencies in $xmlPomFile.GetElementsByTagName("bannedDependencies"))
    {
        # Include nodes will look like the following:
        # <include>groupId:artifactId:[version]</include> <!-- {x-include-update;groupId:artifactId;external_dependency} -->
        foreach($includeNode in $bannedDependencies.GetElementsByTagName("include"))
        {
            $rawIncludeText = $includeNode.InnerText.Trim()
            $split = $rawIncludeText.Split(":")
            if ($split.Count -eq 3)
            {
                $groupId = $split[0]
                $artifactId = $split[1]
                $version = $split[2]
                # The groupId match has to be able to deal with <area>_ for external dependency exceptions
                if (!$includeNode.NextSibling -or $includeNode.NextSibling.NodeType -ne "Comment")
                {
                    $hasError = $true
                    $potentialLogMessage = Join-With-NewLine $potentialLogMessage "Error: <include> is missing the update tag which should be <!-- {x-include-update;$($groupId):$($artifactId);current|dependency|external_dependency<select one>} -->"
                }
                elseif ($includeNode.NextSibling.Value.Trim() -notmatch "{x-include-update;(.+)?$($groupId):$($artifactId);(current|dependency|external_dependency)}")
                {
                    $hasError = $true
                    $potentialLogMessage = Join-With-NewLine $potentialLogMessage "Error: <include> version update tag for $($includeNode.InnerText) should be <!-- {x-include-update;$($groupId):$($artifactId);current|dependency|external_dependency<select one>} -->"
                }
                else
                {
                    # verify that the version is formatted correctly
                    if (!$version.StartsWith("[") -or !$version.EndsWith("]"))
                    {
                        $hasError = $true
                        $potentialLogMessage = Join-With-NewLine $potentialLogMessage "Error: the version entry '$($version)' for <include> '$($rawIncludeText)' is not formatted correctly. The include version needs to of the form '[<version>]', the braces lock the include to a specific version for these entries. -->"
                    }
                    # verify the version has the correct value
                    else
                    {
                        $versionWithoutBraces = $version.Substring(1, $version.Length -2)
                        # the key into the dependency has needs to be created from the tag's group/artifact
                        # entries in case it's an external dependency entry. Because this has already
                        # been validated for format, grab the group:artifact
                        $depKey = $includeNode.NextSibling.Value.Trim().Split(";")[1]
                        $depType = $includeNode.NextSibling.Value.Trim().Split(";")[2]
                        $depType = $depType.Substring(0, $depType.IndexOf("}"))
                        if ($depType -eq $DependencyTypeExternal)
                        {
                            if ($extDepHash.ContainsKey($depKey))
                            {
                                if ($versionWithoutBraces -ne $extDepHash[$depKey].ver)
                                {
                                    $hasError = $true
                                    $potentialLogMessage = Join-With-NewLine $potentialLogMessage "Error: $($depKey)'s version is '$($versionWithoutBraces)' but the external_dependency version is listed as $($extDepHash[$depKey].ver)"
                                }
                            }
                            else
                            {
                                $hasError = $true
                                $potentialLogMessage = Join-With-NewLine $potentialLogMessage "Error: the groupId:artifactId entry '$($depKey)' for <include> '$($rawIncludeText)' is not a valid external dependency. Please verify the entry exists in the external_dependencies.txt file. -->"
                            }
                        }
                        else
                        {
                            # If the tag isn't external_dependency then verify it exists in the library hash
                            if (!$libHash.ContainsKey($depKey))
                            {
                                $hasError = $true
                                return "Error: $($depKey)'s dependency type is '$($depType)' but the dependency does not exist in any of the version_*.txt files. Should this be an external_dependency? Please ensure the dependency type is correct or the dependency is added to the appropriate file."

                            }
                            if ($depType -eq $DependencyTypeDependency)
                            {
                                if ($versionWithoutBraces -ne $libHash[$depKey].depVer)
                                {
                                    $hasError = $true
                                    return "Error: $($depKey)'s <version> is '$($versionString)' but the dependency version is listed as $($libHash[$depKey].depVer)"
                                }
                            }
                            elseif ($depType -eq $DependencyTypeCurrent)
                            {
                                # Verify that none of the 'current' dependencies are using a groupId that starts with 'unreleased_' or 'beta_'
                                if ($depKey.StartsWith('unreleased_') -or $depKey.StartsWith('beta_'))
                                {
                                    $hasError = $true
                                    return "Error: $($versionUpdateString) is using an unreleased_ or beta_ dependency and trying to set current value. Only dependency versions can be set with an unreleased or beta dependency."
                                }
                                if ($versionWithoutBraces -ne $libHash[$depKey].curVer)
                                {
                                    $hasError = $true
                                    return "Error: $($depKey)'s <version> is '$($versionString)' but the current version is listed as $($libHash[$depKey].curVer)"
                                }
                            }
                        }
                    }
                }
            }
            # The only time a split count of 2 is allowed is in the following case.
            # <include>com.azure:*</include>
            # These entries will not and should not have an update tag
            elseif ($split.Count -eq 2)
            {
                if ($ComAzureAllowlistIncludes -notcontains $rawIncludeText)
                {
                    $hasError = $true
                    $AllowListIncludeForError = $ComAzureAllowlistIncludes -join " and "
                    $potentialLogMessage = Join-With-NewLine $potentialLogMessage "Error:  $($rawIncludeText) is not a valid <include> entry. With the exception of the $($AllowListIncludeForError), every <include> entry must be of the form <include>groupId:artifactId:[version]<include>"
                }
            }
            else
            {
                # At this point the include entry is wildly incorrect.
                $hasError = $true
                $potentialLogMessage = Join-With-NewLine $potentialLogMessage "Error:  $($rawIncludeText) is not a valid <include> entry. Every <include> entry must be of the form <include>groupId:artifactId:[version]<include>"
            }
        }
    }

    Write-Log-Message $potentialLogMessage $hasError
}

if ($UseVerboseLogging)
{
    $ElapsedTime = $(get-date) - $StartTime
    $TotalRunTime = "{0:HH:mm:ss}" -f ([datetime]$ElapsedTime.Ticks)
    Write-Host "Total run time=$($TotalRunTime)"
}

if ($script:FoundError)
{
    Write-Error-With-Color "There were errors encountered during execution. Please fix errors and run the script again."
    Write-Error-With-Color "This script can be run locally from the root of the repo. .\eng\versioning\pom_file_version_scanner.ps1"
    exit 1
}
# no errors, ensure it's exiting 0
exit 0
