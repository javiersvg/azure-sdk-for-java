// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.analytics.synapse.artifacts.models;

import com.azure.core.annotation.Generated;
import com.azure.core.util.ExpandableStringEnum;
import java.util.Collection;

/**
 * Specify the credential type (key or cert) is used for service principal.
 */
public final class ODataAadServicePrincipalCredentialType
    extends ExpandableStringEnum<ODataAadServicePrincipalCredentialType> {
    /**
     * Static value ServicePrincipalKey for ODataAadServicePrincipalCredentialType.
     */
    @Generated
    public static final ODataAadServicePrincipalCredentialType SERVICE_PRINCIPAL_KEY
        = fromString("ServicePrincipalKey");

    /**
     * Static value ServicePrincipalCert for ODataAadServicePrincipalCredentialType.
     */
    @Generated
    public static final ODataAadServicePrincipalCredentialType SERVICE_PRINCIPAL_CERT
        = fromString("ServicePrincipalCert");

    /**
     * Creates a new instance of ODataAadServicePrincipalCredentialType value.
     * 
     * @deprecated Use the {@link #fromString(String)} factory method.
     */
    @Generated
    @Deprecated
    public ODataAadServicePrincipalCredentialType() {
    }

    /**
     * Creates or finds a ODataAadServicePrincipalCredentialType from its string representation.
     * 
     * @param name a name to look for.
     * @return the corresponding ODataAadServicePrincipalCredentialType.
     */
    @Generated
    public static ODataAadServicePrincipalCredentialType fromString(String name) {
        return fromString(name, ODataAadServicePrincipalCredentialType.class);
    }

    /**
     * Gets known ODataAadServicePrincipalCredentialType values.
     * 
     * @return known ODataAadServicePrincipalCredentialType values.
     */
    @Generated
    public static Collection<ODataAadServicePrincipalCredentialType> values() {
        return values(ODataAadServicePrincipalCredentialType.class);
    }
}
