// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.resourcemanager.appservice.generated;

/**
 * Samples for StaticSites ListBasicAuth.
 */
public final class StaticSitesListBasicAuthSamples {
    /*
     * x-ms-original-file:
     * specification/web/resource-manager/Microsoft.Web/stable/2024-11-01/examples/ListStaticSiteBasicAuth.json
     */
    /**
     * Sample code: Lists the basic auth properties for a static site.
     * 
     * @param azure The entry point for accessing resource management APIs in Azure.
     */
    public static void listsTheBasicAuthPropertiesForAStaticSite(com.azure.resourcemanager.AzureResourceManager azure) {
        azure.webApps()
            .manager()
            .serviceClient()
            .getStaticSites()
            .listBasicAuth("rg", "testStaticSite0", com.azure.core.util.Context.NONE);
    }
}
