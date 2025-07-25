// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.analytics.synapse.artifacts.models;

import com.azure.core.annotation.Generated;
import com.azure.core.util.ExpandableStringEnum;
import java.util.Collection;

/**
 * Defines values for SchedulerCurrentState.
 */
public final class SchedulerCurrentState extends ExpandableStringEnum<SchedulerCurrentState> {
    /**
     * Static value Queued for SchedulerCurrentState.
     */
    @Generated
    public static final SchedulerCurrentState QUEUED = fromString("Queued");

    /**
     * Static value Scheduled for SchedulerCurrentState.
     */
    @Generated
    public static final SchedulerCurrentState SCHEDULED = fromString("Scheduled");

    /**
     * Static value Ended for SchedulerCurrentState.
     */
    @Generated
    public static final SchedulerCurrentState ENDED = fromString("Ended");

    /**
     * Creates a new instance of SchedulerCurrentState value.
     * 
     * @deprecated Use the {@link #fromString(String)} factory method.
     */
    @Generated
    @Deprecated
    public SchedulerCurrentState() {
    }

    /**
     * Creates or finds a SchedulerCurrentState from its string representation.
     * 
     * @param name a name to look for.
     * @return the corresponding SchedulerCurrentState.
     */
    @Generated
    public static SchedulerCurrentState fromString(String name) {
        return fromString(name, SchedulerCurrentState.class);
    }

    /**
     * Gets known SchedulerCurrentState values.
     * 
     * @return known SchedulerCurrentState values.
     */
    @Generated
    public static Collection<SchedulerCurrentState> values() {
        return values(SchedulerCurrentState.class);
    }
}
