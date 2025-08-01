// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.
package com.azure.messaging.eventgrid.systemevents;

import com.azure.core.annotation.Fluent;
import com.azure.core.annotation.Generated;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;

/**
 * Router Channel Configuration.
 * 
 * @deprecated This class is deprecated and may be removed in future releases. System events are now available in the
 * azure-messaging-eventgrid-systemevents package.
 */
@Fluent
@Deprecated
public final class AcsRouterChannelConfiguration implements JsonSerializable<AcsRouterChannelConfiguration> {

    /*
     * Channel ID for Router Job
     */
    @Generated
    private String channelId;

    /*
     * Capacity Cost Per Job for Router Job
     */
    @Generated
    private Integer capacityCostPerJob;

    /*
     * Max Number of Jobs for Router Job
     */
    @Generated
    private Integer maxNumberOfJobs;

    /**
     * Creates an instance of AcsRouterChannelConfiguration class.
     */
    @Generated
    public AcsRouterChannelConfiguration() {
    }

    /**
     * Get the channelId property: Channel ID for Router Job.
     *
     * @return the channelId value.
     */
    @Generated
    public String getChannelId() {
        return this.channelId;
    }

    /**
     * Set the channelId property: Channel ID for Router Job.
     *
     * @param channelId the channelId value to set.
     * @return the AcsRouterChannelConfiguration object itself.
     */
    @Generated
    public AcsRouterChannelConfiguration setChannelId(String channelId) {
        this.channelId = channelId;
        return this;
    }

    /**
     * Get the capacityCostPerJob property: Capacity Cost Per Job for Router Job.
     *
     * @return the capacityCostPerJob value.
     */
    @Generated
    public Integer getCapacityCostPerJob() {
        return this.capacityCostPerJob;
    }

    /**
     * Set the capacityCostPerJob property: Capacity Cost Per Job for Router Job.
     *
     * @param capacityCostPerJob the capacityCostPerJob value to set.
     * @return the AcsRouterChannelConfiguration object itself.
     */
    @Generated
    public AcsRouterChannelConfiguration setCapacityCostPerJob(Integer capacityCostPerJob) {
        this.capacityCostPerJob = capacityCostPerJob;
        return this;
    }

    /**
     * Get the maxNumberOfJobs property: Max Number of Jobs for Router Job.
     *
     * @return the maxNumberOfJobs value.
     */
    @Generated
    public Integer getMaxNumberOfJobs() {
        return this.maxNumberOfJobs;
    }

    /**
     * Set the maxNumberOfJobs property: Max Number of Jobs for Router Job.
     *
     * @param maxNumberOfJobs the maxNumberOfJobs value to set.
     * @return the AcsRouterChannelConfiguration object itself.
     */
    @Generated
    public AcsRouterChannelConfiguration setMaxNumberOfJobs(Integer maxNumberOfJobs) {
        this.maxNumberOfJobs = maxNumberOfJobs;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("channelId", this.channelId);
        jsonWriter.writeNumberField("capacityCostPerJob", this.capacityCostPerJob);
        jsonWriter.writeNumberField("maxNumberOfJobs", this.maxNumberOfJobs);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of AcsRouterChannelConfiguration from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of AcsRouterChannelConfiguration if the JsonReader was pointing to an instance of it, or null
     * if it was pointing to JSON null.
     * @throws IOException If an error occurs while reading the AcsRouterChannelConfiguration.
     */
    @Generated
    public static AcsRouterChannelConfiguration fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            AcsRouterChannelConfiguration deserializedAcsRouterChannelConfiguration
                = new AcsRouterChannelConfiguration();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();
                if ("channelId".equals(fieldName)) {
                    deserializedAcsRouterChannelConfiguration.channelId = reader.getString();
                } else if ("capacityCostPerJob".equals(fieldName)) {
                    deserializedAcsRouterChannelConfiguration.capacityCostPerJob
                        = reader.getNullable(JsonReader::getInt);
                } else if ("maxNumberOfJobs".equals(fieldName)) {
                    deserializedAcsRouterChannelConfiguration.maxNumberOfJobs = reader.getNullable(JsonReader::getInt);
                } else {
                    reader.skipChildren();
                }
            }
            return deserializedAcsRouterChannelConfiguration;
        });
    }
}
