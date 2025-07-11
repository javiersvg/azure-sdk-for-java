// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.
package com.azure.messaging.eventgrid.systemevents;

import com.azure.core.annotation.Generated;
import com.azure.core.util.ExpandableStringEnum;
import java.util.Collection;

/**
 * The identifier kind, for example 'communicationUser' or 'phoneNumber'.
 * 
 * @deprecated This class is deprecated and may be removed in future releases. System events are now available in the
 * azure-messaging-eventgrid-systemevents package.
 */
@Deprecated
public final class AcsCommunicationIdentifierKind extends ExpandableStringEnum<AcsCommunicationIdentifierKind> {

    /**
     * Static value unknown for AcsCommunicationIdentifierKind.
     */
    @Generated
    public static final AcsCommunicationIdentifierKind UNKNOWN = fromString("unknown");

    /**
     * Static value communicationUser for AcsCommunicationIdentifierKind.
     */
    @Generated
    public static final AcsCommunicationIdentifierKind COMMUNICATION_USER = fromString("communicationUser");

    /**
     * Static value phoneNumber for AcsCommunicationIdentifierKind.
     */
    @Generated
    public static final AcsCommunicationIdentifierKind PHONE_NUMBER = fromString("phoneNumber");

    /**
     * Static value microsoftTeamsUser for AcsCommunicationIdentifierKind.
     */
    @Generated
    public static final AcsCommunicationIdentifierKind MICROSOFT_TEAMS_USER = fromString("microsoftTeamsUser");

    /**
     * Static value microsoftTeamsApp for AcsCommunicationIdentifierKind.
     */
    @Generated
    public static final AcsCommunicationIdentifierKind MICROSOFT_TEAMS_APP = fromString("microsoftTeamsApp");

    /**
     * Creates a new instance of AcsCommunicationIdentifierKind value.
     *
     * @deprecated Use the {@link #fromString(String)} factory method.
     */
    @Generated
    @Deprecated
    public AcsCommunicationIdentifierKind() {
    }

    /**
     * Creates or finds a AcsCommunicationIdentifierKind from its string representation.
     *
     * @param name a name to look for.
     * @return the corresponding AcsCommunicationIdentifierKind.
     */
    @Generated
    public static AcsCommunicationIdentifierKind fromString(String name) {
        return fromString(name, AcsCommunicationIdentifierKind.class);
    }

    /**
     * Gets known AcsCommunicationIdentifierKind values.
     *
     * @return known AcsCommunicationIdentifierKind values.
     */
    @Generated
    public static Collection<AcsCommunicationIdentifierKind> values() {
        return values(AcsCommunicationIdentifierKind.class);
    }
}
