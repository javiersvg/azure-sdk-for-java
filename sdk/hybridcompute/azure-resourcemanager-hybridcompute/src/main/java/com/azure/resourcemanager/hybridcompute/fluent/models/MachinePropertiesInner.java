// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.resourcemanager.hybridcompute.fluent.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.management.exception.ManagementError;
import com.azure.core.util.CoreUtils;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import com.azure.resourcemanager.hybridcompute.models.AgentConfiguration;
import com.azure.resourcemanager.hybridcompute.models.AgentUpgrade;
import com.azure.resourcemanager.hybridcompute.models.CloudMetadata;
import com.azure.resourcemanager.hybridcompute.models.FirmwareProfile;
import com.azure.resourcemanager.hybridcompute.models.HardwareProfile;
import com.azure.resourcemanager.hybridcompute.models.IdentityKeyStore;
import com.azure.resourcemanager.hybridcompute.models.LocationData;
import com.azure.resourcemanager.hybridcompute.models.MachineExtensionInstanceView;
import com.azure.resourcemanager.hybridcompute.models.OSProfile;
import com.azure.resourcemanager.hybridcompute.models.ServiceStatuses;
import com.azure.resourcemanager.hybridcompute.models.StatusTypes;
import com.azure.resourcemanager.hybridcompute.models.StorageProfile;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * Describes the properties of a hybrid machine.
 */
@Fluent
public final class MachinePropertiesInner implements JsonSerializable<MachinePropertiesInner> {
    /*
     * Metadata pertaining to the geographic location of the resource.
     */
    private LocationData locationData;

    /*
     * Configurable properties that the user can set locally via the azcmagent config command, or remotely via ARM.
     */
    private AgentConfiguration agentConfiguration;

    /*
     * Statuses of dependent services that are reported back to ARM.
     */
    private ServiceStatuses serviceStatuses;

    /*
     * Information about the machine's hardware
     */
    private HardwareProfile hardwareProfile;

    /*
     * Information about the machine's storage
     */
    private StorageProfile storageProfile;

    /*
     * Information about the machine's firmware
     */
    private FirmwareProfile firmwareProfile;

    /*
     * The metadata of the cloud environment (Azure/GCP/AWS/OCI...).
     */
    private CloudMetadata cloudMetadata;

    /*
     * The info of the machine w.r.t Agent Upgrade
     */
    private AgentUpgrade agentUpgrade;

    /*
     * Specifies the operating system settings for the hybrid machine.
     */
    private OSProfile osProfile;

    /*
     * Specifies the License related properties for a machine.
     */
    private LicenseProfileMachineInstanceViewInner licenseProfile;

    /*
     * The provisioning state, which only appears in the response.
     */
    private String provisioningState;

    /*
     * The status of the hybrid machine agent.
     */
    private StatusTypes status;

    /*
     * The time of the last status change.
     */
    private OffsetDateTime lastStatusChange;

    /*
     * Details about the error state.
     */
    private List<ManagementError> errorDetails;

    /*
     * The hybrid machine agent full version.
     */
    private String agentVersion;

    /*
     * Specifies the hybrid machine unique ID.
     */
    private UUID vmId;

    /*
     * Specifies the hybrid machine display name.
     */
    private String displayName;

    /*
     * Specifies the hybrid machine FQDN.
     */
    private String machineFqdn;

    /*
     * Public Key that the client provides to be used during initial resource onboarding
     */
    private String clientPublicKey;

    /*
     * Specifies the identity key store a machine is using.
     */
    private IdentityKeyStore identityKeyStore;

    /*
     * Endorsement Key Certificate of the Trusted Platform Module (TPM) that the client provides to be used during
     * initial resource onboarding.
     */
    private String tpmEkCertificate;

    /*
     * The Operating System running on the hybrid machine.
     */
    private String osName;

    /*
     * The version of Operating System running on the hybrid machine.
     */
    private String osVersion;

    /*
     * The type of Operating System (windows/linux).
     */
    private String osType;

    /*
     * Specifies the Arc Machine's unique SMBIOS ID
     */
    private UUID vmUuid;

    /*
     * Machine Extensions information (deprecated field)
     */
    private List<MachineExtensionInstanceView> extensions;

    /*
     * Specifies the Operating System product SKU.
     */
    private String osSku;

    /*
     * The edition of the Operating System.
     */
    private String osEdition;

    /*
     * Specifies the Windows domain name.
     */
    private String domainName;

    /*
     * Specifies the AD fully qualified display name.
     */
    private String adFqdn;

    /*
     * Specifies the DNS fully qualified display name.
     */
    private String dnsFqdn;

    /*
     * The resource id of the private link scope this machine is assigned to, if any.
     */
    private String privateLinkScopeResourceId;

    /*
     * The resource id of the parent cluster (Azure HCI) this machine is assigned to, if any.
     */
    private String parentClusterResourceId;

    /*
     * Specifies the resource ID of the associated hardware device. Only settable by HCI RP.
     */
    private String hardwareResourceId;

    /*
     * Specifies whether any MS SQL instance is discovered on the machine.
     */
    private String mssqlDiscovered;

    /*
     * Detected properties from the machine.
     */
    private Map<String, String> detectedProperties;

    /*
     * Information about the network the machine is on.
     */
    private NetworkProfileInner networkProfile;

    /**
     * Creates an instance of MachinePropertiesInner class.
     */
    public MachinePropertiesInner() {
    }

    /**
     * Get the locationData property: Metadata pertaining to the geographic location of the resource.
     * 
     * @return the locationData value.
     */
    public LocationData locationData() {
        return this.locationData;
    }

    /**
     * Set the locationData property: Metadata pertaining to the geographic location of the resource.
     * 
     * @param locationData the locationData value to set.
     * @return the MachinePropertiesInner object itself.
     */
    public MachinePropertiesInner withLocationData(LocationData locationData) {
        this.locationData = locationData;
        return this;
    }

    /**
     * Get the agentConfiguration property: Configurable properties that the user can set locally via the azcmagent
     * config command, or remotely via ARM.
     * 
     * @return the agentConfiguration value.
     */
    public AgentConfiguration agentConfiguration() {
        return this.agentConfiguration;
    }

    /**
     * Get the serviceStatuses property: Statuses of dependent services that are reported back to ARM.
     * 
     * @return the serviceStatuses value.
     */
    public ServiceStatuses serviceStatuses() {
        return this.serviceStatuses;
    }

    /**
     * Set the serviceStatuses property: Statuses of dependent services that are reported back to ARM.
     * 
     * @param serviceStatuses the serviceStatuses value to set.
     * @return the MachinePropertiesInner object itself.
     */
    public MachinePropertiesInner withServiceStatuses(ServiceStatuses serviceStatuses) {
        this.serviceStatuses = serviceStatuses;
        return this;
    }

    /**
     * Get the hardwareProfile property: Information about the machine's hardware.
     * 
     * @return the hardwareProfile value.
     */
    public HardwareProfile hardwareProfile() {
        return this.hardwareProfile;
    }

    /**
     * Get the storageProfile property: Information about the machine's storage.
     * 
     * @return the storageProfile value.
     */
    public StorageProfile storageProfile() {
        return this.storageProfile;
    }

    /**
     * Get the firmwareProfile property: Information about the machine's firmware.
     * 
     * @return the firmwareProfile value.
     */
    public FirmwareProfile firmwareProfile() {
        return this.firmwareProfile;
    }

    /**
     * Get the cloudMetadata property: The metadata of the cloud environment (Azure/GCP/AWS/OCI...).
     * 
     * @return the cloudMetadata value.
     */
    public CloudMetadata cloudMetadata() {
        return this.cloudMetadata;
    }

    /**
     * Set the cloudMetadata property: The metadata of the cloud environment (Azure/GCP/AWS/OCI...).
     * 
     * @param cloudMetadata the cloudMetadata value to set.
     * @return the MachinePropertiesInner object itself.
     */
    public MachinePropertiesInner withCloudMetadata(CloudMetadata cloudMetadata) {
        this.cloudMetadata = cloudMetadata;
        return this;
    }

    /**
     * Get the agentUpgrade property: The info of the machine w.r.t Agent Upgrade.
     * 
     * @return the agentUpgrade value.
     */
    public AgentUpgrade agentUpgrade() {
        return this.agentUpgrade;
    }

    /**
     * Set the agentUpgrade property: The info of the machine w.r.t Agent Upgrade.
     * 
     * @param agentUpgrade the agentUpgrade value to set.
     * @return the MachinePropertiesInner object itself.
     */
    public MachinePropertiesInner withAgentUpgrade(AgentUpgrade agentUpgrade) {
        this.agentUpgrade = agentUpgrade;
        return this;
    }

    /**
     * Get the osProfile property: Specifies the operating system settings for the hybrid machine.
     * 
     * @return the osProfile value.
     */
    public OSProfile osProfile() {
        return this.osProfile;
    }

    /**
     * Set the osProfile property: Specifies the operating system settings for the hybrid machine.
     * 
     * @param osProfile the osProfile value to set.
     * @return the MachinePropertiesInner object itself.
     */
    public MachinePropertiesInner withOsProfile(OSProfile osProfile) {
        this.osProfile = osProfile;
        return this;
    }

    /**
     * Get the licenseProfile property: Specifies the License related properties for a machine.
     * 
     * @return the licenseProfile value.
     */
    public LicenseProfileMachineInstanceViewInner licenseProfile() {
        return this.licenseProfile;
    }

    /**
     * Set the licenseProfile property: Specifies the License related properties for a machine.
     * 
     * @param licenseProfile the licenseProfile value to set.
     * @return the MachinePropertiesInner object itself.
     */
    public MachinePropertiesInner withLicenseProfile(LicenseProfileMachineInstanceViewInner licenseProfile) {
        this.licenseProfile = licenseProfile;
        return this;
    }

    /**
     * Get the provisioningState property: The provisioning state, which only appears in the response.
     * 
     * @return the provisioningState value.
     */
    public String provisioningState() {
        return this.provisioningState;
    }

    /**
     * Get the status property: The status of the hybrid machine agent.
     * 
     * @return the status value.
     */
    public StatusTypes status() {
        return this.status;
    }

    /**
     * Get the lastStatusChange property: The time of the last status change.
     * 
     * @return the lastStatusChange value.
     */
    public OffsetDateTime lastStatusChange() {
        return this.lastStatusChange;
    }

    /**
     * Get the errorDetails property: Details about the error state.
     * 
     * @return the errorDetails value.
     */
    public List<ManagementError> errorDetails() {
        return this.errorDetails;
    }

    /**
     * Get the agentVersion property: The hybrid machine agent full version.
     * 
     * @return the agentVersion value.
     */
    public String agentVersion() {
        return this.agentVersion;
    }

    /**
     * Get the vmId property: Specifies the hybrid machine unique ID.
     * 
     * @return the vmId value.
     */
    public UUID vmId() {
        return this.vmId;
    }

    /**
     * Set the vmId property: Specifies the hybrid machine unique ID.
     * 
     * @param vmId the vmId value to set.
     * @return the MachinePropertiesInner object itself.
     */
    public MachinePropertiesInner withVmId(UUID vmId) {
        this.vmId = vmId;
        return this;
    }

    /**
     * Get the displayName property: Specifies the hybrid machine display name.
     * 
     * @return the displayName value.
     */
    public String displayName() {
        return this.displayName;
    }

    /**
     * Get the machineFqdn property: Specifies the hybrid machine FQDN.
     * 
     * @return the machineFqdn value.
     */
    public String machineFqdn() {
        return this.machineFqdn;
    }

    /**
     * Get the clientPublicKey property: Public Key that the client provides to be used during initial resource
     * onboarding.
     * 
     * @return the clientPublicKey value.
     */
    public String clientPublicKey() {
        return this.clientPublicKey;
    }

    /**
     * Set the clientPublicKey property: Public Key that the client provides to be used during initial resource
     * onboarding.
     * 
     * @param clientPublicKey the clientPublicKey value to set.
     * @return the MachinePropertiesInner object itself.
     */
    public MachinePropertiesInner withClientPublicKey(String clientPublicKey) {
        this.clientPublicKey = clientPublicKey;
        return this;
    }

    /**
     * Get the identityKeyStore property: Specifies the identity key store a machine is using.
     * 
     * @return the identityKeyStore value.
     */
    public IdentityKeyStore identityKeyStore() {
        return this.identityKeyStore;
    }

    /**
     * Set the identityKeyStore property: Specifies the identity key store a machine is using.
     * 
     * @param identityKeyStore the identityKeyStore value to set.
     * @return the MachinePropertiesInner object itself.
     */
    public MachinePropertiesInner withIdentityKeyStore(IdentityKeyStore identityKeyStore) {
        this.identityKeyStore = identityKeyStore;
        return this;
    }

    /**
     * Get the tpmEkCertificate property: Endorsement Key Certificate of the Trusted Platform Module (TPM) that the
     * client provides to be used during initial resource onboarding.
     * 
     * @return the tpmEkCertificate value.
     */
    public String tpmEkCertificate() {
        return this.tpmEkCertificate;
    }

    /**
     * Set the tpmEkCertificate property: Endorsement Key Certificate of the Trusted Platform Module (TPM) that the
     * client provides to be used during initial resource onboarding.
     * 
     * @param tpmEkCertificate the tpmEkCertificate value to set.
     * @return the MachinePropertiesInner object itself.
     */
    public MachinePropertiesInner withTpmEkCertificate(String tpmEkCertificate) {
        this.tpmEkCertificate = tpmEkCertificate;
        return this;
    }

    /**
     * Get the osName property: The Operating System running on the hybrid machine.
     * 
     * @return the osName value.
     */
    public String osName() {
        return this.osName;
    }

    /**
     * Get the osVersion property: The version of Operating System running on the hybrid machine.
     * 
     * @return the osVersion value.
     */
    public String osVersion() {
        return this.osVersion;
    }

    /**
     * Get the osType property: The type of Operating System (windows/linux).
     * 
     * @return the osType value.
     */
    public String osType() {
        return this.osType;
    }

    /**
     * Set the osType property: The type of Operating System (windows/linux).
     * 
     * @param osType the osType value to set.
     * @return the MachinePropertiesInner object itself.
     */
    public MachinePropertiesInner withOsType(String osType) {
        this.osType = osType;
        return this;
    }

    /**
     * Get the vmUuid property: Specifies the Arc Machine's unique SMBIOS ID.
     * 
     * @return the vmUuid value.
     */
    public UUID vmUuid() {
        return this.vmUuid;
    }

    /**
     * Get the extensions property: Machine Extensions information (deprecated field).
     * 
     * @return the extensions value.
     */
    public List<MachineExtensionInstanceView> extensions() {
        return this.extensions;
    }

    /**
     * Set the extensions property: Machine Extensions information (deprecated field).
     * 
     * @param extensions the extensions value to set.
     * @return the MachinePropertiesInner object itself.
     */
    public MachinePropertiesInner withExtensions(List<MachineExtensionInstanceView> extensions) {
        this.extensions = extensions;
        return this;
    }

    /**
     * Get the osSku property: Specifies the Operating System product SKU.
     * 
     * @return the osSku value.
     */
    public String osSku() {
        return this.osSku;
    }

    /**
     * Get the osEdition property: The edition of the Operating System.
     * 
     * @return the osEdition value.
     */
    public String osEdition() {
        return this.osEdition;
    }

    /**
     * Get the domainName property: Specifies the Windows domain name.
     * 
     * @return the domainName value.
     */
    public String domainName() {
        return this.domainName;
    }

    /**
     * Get the adFqdn property: Specifies the AD fully qualified display name.
     * 
     * @return the adFqdn value.
     */
    public String adFqdn() {
        return this.adFqdn;
    }

    /**
     * Get the dnsFqdn property: Specifies the DNS fully qualified display name.
     * 
     * @return the dnsFqdn value.
     */
    public String dnsFqdn() {
        return this.dnsFqdn;
    }

    /**
     * Get the privateLinkScopeResourceId property: The resource id of the private link scope this machine is assigned
     * to, if any.
     * 
     * @return the privateLinkScopeResourceId value.
     */
    public String privateLinkScopeResourceId() {
        return this.privateLinkScopeResourceId;
    }

    /**
     * Set the privateLinkScopeResourceId property: The resource id of the private link scope this machine is assigned
     * to, if any.
     * 
     * @param privateLinkScopeResourceId the privateLinkScopeResourceId value to set.
     * @return the MachinePropertiesInner object itself.
     */
    public MachinePropertiesInner withPrivateLinkScopeResourceId(String privateLinkScopeResourceId) {
        this.privateLinkScopeResourceId = privateLinkScopeResourceId;
        return this;
    }

    /**
     * Get the parentClusterResourceId property: The resource id of the parent cluster (Azure HCI) this machine is
     * assigned to, if any.
     * 
     * @return the parentClusterResourceId value.
     */
    public String parentClusterResourceId() {
        return this.parentClusterResourceId;
    }

    /**
     * Set the parentClusterResourceId property: The resource id of the parent cluster (Azure HCI) this machine is
     * assigned to, if any.
     * 
     * @param parentClusterResourceId the parentClusterResourceId value to set.
     * @return the MachinePropertiesInner object itself.
     */
    public MachinePropertiesInner withParentClusterResourceId(String parentClusterResourceId) {
        this.parentClusterResourceId = parentClusterResourceId;
        return this;
    }

    /**
     * Get the hardwareResourceId property: Specifies the resource ID of the associated hardware device. Only settable
     * by HCI RP.
     * 
     * @return the hardwareResourceId value.
     */
    public String hardwareResourceId() {
        return this.hardwareResourceId;
    }

    /**
     * Set the hardwareResourceId property: Specifies the resource ID of the associated hardware device. Only settable
     * by HCI RP.
     * 
     * @param hardwareResourceId the hardwareResourceId value to set.
     * @return the MachinePropertiesInner object itself.
     */
    public MachinePropertiesInner withHardwareResourceId(String hardwareResourceId) {
        this.hardwareResourceId = hardwareResourceId;
        return this;
    }

    /**
     * Get the mssqlDiscovered property: Specifies whether any MS SQL instance is discovered on the machine.
     * 
     * @return the mssqlDiscovered value.
     */
    public String mssqlDiscovered() {
        return this.mssqlDiscovered;
    }

    /**
     * Set the mssqlDiscovered property: Specifies whether any MS SQL instance is discovered on the machine.
     * 
     * @param mssqlDiscovered the mssqlDiscovered value to set.
     * @return the MachinePropertiesInner object itself.
     */
    public MachinePropertiesInner withMssqlDiscovered(String mssqlDiscovered) {
        this.mssqlDiscovered = mssqlDiscovered;
        return this;
    }

    /**
     * Get the detectedProperties property: Detected properties from the machine.
     * 
     * @return the detectedProperties value.
     */
    public Map<String, String> detectedProperties() {
        return this.detectedProperties;
    }

    /**
     * Get the networkProfile property: Information about the network the machine is on.
     * 
     * @return the networkProfile value.
     */
    public NetworkProfileInner networkProfile() {
        return this.networkProfile;
    }

    /**
     * Validates the instance.
     * 
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {
        if (locationData() != null) {
            locationData().validate();
        }
        if (agentConfiguration() != null) {
            agentConfiguration().validate();
        }
        if (serviceStatuses() != null) {
            serviceStatuses().validate();
        }
        if (hardwareProfile() != null) {
            hardwareProfile().validate();
        }
        if (storageProfile() != null) {
            storageProfile().validate();
        }
        if (firmwareProfile() != null) {
            firmwareProfile().validate();
        }
        if (cloudMetadata() != null) {
            cloudMetadata().validate();
        }
        if (agentUpgrade() != null) {
            agentUpgrade().validate();
        }
        if (osProfile() != null) {
            osProfile().validate();
        }
        if (licenseProfile() != null) {
            licenseProfile().validate();
        }
        if (extensions() != null) {
            extensions().forEach(e -> e.validate());
        }
        if (networkProfile() != null) {
            networkProfile().validate();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeJsonField("locationData", this.locationData);
        jsonWriter.writeJsonField("serviceStatuses", this.serviceStatuses);
        jsonWriter.writeJsonField("cloudMetadata", this.cloudMetadata);
        jsonWriter.writeJsonField("agentUpgrade", this.agentUpgrade);
        jsonWriter.writeJsonField("osProfile", this.osProfile);
        jsonWriter.writeJsonField("licenseProfile", this.licenseProfile);
        jsonWriter.writeStringField("vmId", Objects.toString(this.vmId, null));
        jsonWriter.writeStringField("clientPublicKey", this.clientPublicKey);
        jsonWriter.writeStringField("identityKeyStore",
            this.identityKeyStore == null ? null : this.identityKeyStore.toString());
        jsonWriter.writeStringField("tpmEkCertificate", this.tpmEkCertificate);
        jsonWriter.writeStringField("osType", this.osType);
        jsonWriter.writeArrayField("extensions", this.extensions, (writer, element) -> writer.writeJson(element));
        jsonWriter.writeStringField("privateLinkScopeResourceId", this.privateLinkScopeResourceId);
        jsonWriter.writeStringField("parentClusterResourceId", this.parentClusterResourceId);
        jsonWriter.writeStringField("hardwareResourceId", this.hardwareResourceId);
        jsonWriter.writeStringField("mssqlDiscovered", this.mssqlDiscovered);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of MachinePropertiesInner from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of MachinePropertiesInner if the JsonReader was pointing to an instance of it, or null if it
     * was pointing to JSON null.
     * @throws IOException If an error occurs while reading the MachinePropertiesInner.
     */
    public static MachinePropertiesInner fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            MachinePropertiesInner deserializedMachinePropertiesInner = new MachinePropertiesInner();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("locationData".equals(fieldName)) {
                    deserializedMachinePropertiesInner.locationData = LocationData.fromJson(reader);
                } else if ("agentConfiguration".equals(fieldName)) {
                    deserializedMachinePropertiesInner.agentConfiguration = AgentConfiguration.fromJson(reader);
                } else if ("serviceStatuses".equals(fieldName)) {
                    deserializedMachinePropertiesInner.serviceStatuses = ServiceStatuses.fromJson(reader);
                } else if ("hardwareProfile".equals(fieldName)) {
                    deserializedMachinePropertiesInner.hardwareProfile = HardwareProfile.fromJson(reader);
                } else if ("storageProfile".equals(fieldName)) {
                    deserializedMachinePropertiesInner.storageProfile = StorageProfile.fromJson(reader);
                } else if ("firmwareProfile".equals(fieldName)) {
                    deserializedMachinePropertiesInner.firmwareProfile = FirmwareProfile.fromJson(reader);
                } else if ("cloudMetadata".equals(fieldName)) {
                    deserializedMachinePropertiesInner.cloudMetadata = CloudMetadata.fromJson(reader);
                } else if ("agentUpgrade".equals(fieldName)) {
                    deserializedMachinePropertiesInner.agentUpgrade = AgentUpgrade.fromJson(reader);
                } else if ("osProfile".equals(fieldName)) {
                    deserializedMachinePropertiesInner.osProfile = OSProfile.fromJson(reader);
                } else if ("licenseProfile".equals(fieldName)) {
                    deserializedMachinePropertiesInner.licenseProfile
                        = LicenseProfileMachineInstanceViewInner.fromJson(reader);
                } else if ("provisioningState".equals(fieldName)) {
                    deserializedMachinePropertiesInner.provisioningState = reader.getString();
                } else if ("status".equals(fieldName)) {
                    deserializedMachinePropertiesInner.status = StatusTypes.fromString(reader.getString());
                } else if ("lastStatusChange".equals(fieldName)) {
                    deserializedMachinePropertiesInner.lastStatusChange = reader
                        .getNullable(nonNullReader -> CoreUtils.parseBestOffsetDateTime(nonNullReader.getString()));
                } else if ("errorDetails".equals(fieldName)) {
                    List<ManagementError> errorDetails = reader.readArray(reader1 -> ManagementError.fromJson(reader1));
                    deserializedMachinePropertiesInner.errorDetails = errorDetails;
                } else if ("agentVersion".equals(fieldName)) {
                    deserializedMachinePropertiesInner.agentVersion = reader.getString();
                } else if ("vmId".equals(fieldName)) {
                    deserializedMachinePropertiesInner.vmId
                        = reader.getNullable(nonNullReader -> UUID.fromString(nonNullReader.getString()));
                } else if ("displayName".equals(fieldName)) {
                    deserializedMachinePropertiesInner.displayName = reader.getString();
                } else if ("machineFqdn".equals(fieldName)) {
                    deserializedMachinePropertiesInner.machineFqdn = reader.getString();
                } else if ("clientPublicKey".equals(fieldName)) {
                    deserializedMachinePropertiesInner.clientPublicKey = reader.getString();
                } else if ("identityKeyStore".equals(fieldName)) {
                    deserializedMachinePropertiesInner.identityKeyStore
                        = IdentityKeyStore.fromString(reader.getString());
                } else if ("tpmEkCertificate".equals(fieldName)) {
                    deserializedMachinePropertiesInner.tpmEkCertificate = reader.getString();
                } else if ("osName".equals(fieldName)) {
                    deserializedMachinePropertiesInner.osName = reader.getString();
                } else if ("osVersion".equals(fieldName)) {
                    deserializedMachinePropertiesInner.osVersion = reader.getString();
                } else if ("osType".equals(fieldName)) {
                    deserializedMachinePropertiesInner.osType = reader.getString();
                } else if ("vmUuid".equals(fieldName)) {
                    deserializedMachinePropertiesInner.vmUuid
                        = reader.getNullable(nonNullReader -> UUID.fromString(nonNullReader.getString()));
                } else if ("extensions".equals(fieldName)) {
                    List<MachineExtensionInstanceView> extensions
                        = reader.readArray(reader1 -> MachineExtensionInstanceView.fromJson(reader1));
                    deserializedMachinePropertiesInner.extensions = extensions;
                } else if ("osSku".equals(fieldName)) {
                    deserializedMachinePropertiesInner.osSku = reader.getString();
                } else if ("osEdition".equals(fieldName)) {
                    deserializedMachinePropertiesInner.osEdition = reader.getString();
                } else if ("domainName".equals(fieldName)) {
                    deserializedMachinePropertiesInner.domainName = reader.getString();
                } else if ("adFqdn".equals(fieldName)) {
                    deserializedMachinePropertiesInner.adFqdn = reader.getString();
                } else if ("dnsFqdn".equals(fieldName)) {
                    deserializedMachinePropertiesInner.dnsFqdn = reader.getString();
                } else if ("privateLinkScopeResourceId".equals(fieldName)) {
                    deserializedMachinePropertiesInner.privateLinkScopeResourceId = reader.getString();
                } else if ("parentClusterResourceId".equals(fieldName)) {
                    deserializedMachinePropertiesInner.parentClusterResourceId = reader.getString();
                } else if ("hardwareResourceId".equals(fieldName)) {
                    deserializedMachinePropertiesInner.hardwareResourceId = reader.getString();
                } else if ("mssqlDiscovered".equals(fieldName)) {
                    deserializedMachinePropertiesInner.mssqlDiscovered = reader.getString();
                } else if ("detectedProperties".equals(fieldName)) {
                    Map<String, String> detectedProperties = reader.readMap(reader1 -> reader1.getString());
                    deserializedMachinePropertiesInner.detectedProperties = detectedProperties;
                } else if ("networkProfile".equals(fieldName)) {
                    deserializedMachinePropertiesInner.networkProfile = NetworkProfileInner.fromJson(reader);
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedMachinePropertiesInner;
        });
    }
}
