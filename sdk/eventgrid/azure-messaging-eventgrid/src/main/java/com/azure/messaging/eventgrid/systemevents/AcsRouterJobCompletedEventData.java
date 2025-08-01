// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.
package com.azure.messaging.eventgrid.systemevents;

import com.azure.core.annotation.Fluent;
import com.azure.core.annotation.Generated;
import com.azure.json.JsonReader;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;
import java.util.Map;

/**
 * Schema of the Data property of an EventGridEvent for a Microsoft.Communication.RouterJobCompleted event.
 * 
 * @deprecated This class is deprecated and may be removed in future releases. System events are now available in the
 * azure-messaging-eventgrid-systemevents package.
 */
@Fluent
@Deprecated
public final class AcsRouterJobCompletedEventData extends AcsRouterJobEventData {

    /*
     * Router Job Completed Assignment Id
     */
    @Generated
    private String assignmentId;

    /*
     * Router Job Completed Worker Id
     */
    @Generated
    private String workerId;

    /**
     * Creates an instance of AcsRouterJobCompletedEventData class.
     */
    @Generated
    public AcsRouterJobCompletedEventData() {
    }

    /**
     * Get the assignmentId property: Router Job Completed Assignment Id.
     *
     * @return the assignmentId value.
     */
    @Generated
    public String getAssignmentId() {
        return this.assignmentId;
    }

    /**
     * Set the assignmentId property: Router Job Completed Assignment Id.
     *
     * @param assignmentId the assignmentId value to set.
     * @return the AcsRouterJobCompletedEventData object itself.
     */
    @Generated
    public AcsRouterJobCompletedEventData setAssignmentId(String assignmentId) {
        this.assignmentId = assignmentId;
        return this;
    }

    /**
     * Get the workerId property: Router Job Completed Worker Id.
     *
     * @return the workerId value.
     */
    @Generated
    public String getWorkerId() {
        return this.workerId;
    }

    /**
     * Set the workerId property: Router Job Completed Worker Id.
     *
     * @param workerId the workerId value to set.
     * @return the AcsRouterJobCompletedEventData object itself.
     */
    @Generated
    public AcsRouterJobCompletedEventData setWorkerId(String workerId) {
        this.workerId = workerId;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public AcsRouterJobCompletedEventData setQueueId(String queueId) {
        super.setQueueId(queueId);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public AcsRouterJobCompletedEventData setLabels(Map<String, String> labels) {
        super.setLabels(labels);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public AcsRouterJobCompletedEventData setTags(Map<String, String> tags) {
        super.setTags(tags);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public AcsRouterJobCompletedEventData setJobId(String jobId) {
        super.setJobId(jobId);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public AcsRouterJobCompletedEventData setChannelReference(String channelReference) {
        super.setChannelReference(channelReference);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public AcsRouterJobCompletedEventData setChannelId(String channelId) {
        super.setChannelId(channelId);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeMapField("labels", getLabels(), (writer, element) -> writer.writeString(element));
        jsonWriter.writeMapField("tags", getTags(), (writer, element) -> writer.writeString(element));
        jsonWriter.writeStringField("jobId", getJobId());
        jsonWriter.writeStringField("channelReference", getChannelReference());
        jsonWriter.writeStringField("channelId", getChannelId());
        jsonWriter.writeStringField("queueId", getQueueId());
        jsonWriter.writeStringField("assignmentId", this.assignmentId);
        jsonWriter.writeStringField("workerId", this.workerId);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of AcsRouterJobCompletedEventData from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of AcsRouterJobCompletedEventData if the JsonReader was pointing to an instance of it, or
     * null if it was pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the AcsRouterJobCompletedEventData.
     */
    @Generated
    public static AcsRouterJobCompletedEventData fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            AcsRouterJobCompletedEventData deserializedAcsRouterJobCompletedEventData
                = new AcsRouterJobCompletedEventData();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();
                if ("labels".equals(fieldName)) {
                    Map<String, String> labels = reader.readMap(reader1 -> reader1.getString());
                    deserializedAcsRouterJobCompletedEventData.setLabels(labels);
                } else if ("tags".equals(fieldName)) {
                    Map<String, String> tags = reader.readMap(reader1 -> reader1.getString());
                    deserializedAcsRouterJobCompletedEventData.setTags(tags);
                } else if ("jobId".equals(fieldName)) {
                    deserializedAcsRouterJobCompletedEventData.setJobId(reader.getString());
                } else if ("channelReference".equals(fieldName)) {
                    deserializedAcsRouterJobCompletedEventData.setChannelReference(reader.getString());
                } else if ("channelId".equals(fieldName)) {
                    deserializedAcsRouterJobCompletedEventData.setChannelId(reader.getString());
                } else if ("queueId".equals(fieldName)) {
                    deserializedAcsRouterJobCompletedEventData.setQueueId(reader.getString());
                } else if ("assignmentId".equals(fieldName)) {
                    deserializedAcsRouterJobCompletedEventData.assignmentId = reader.getString();
                } else if ("workerId".equals(fieldName)) {
                    deserializedAcsRouterJobCompletedEventData.workerId = reader.getString();
                } else {
                    reader.skipChildren();
                }
            }
            return deserializedAcsRouterJobCompletedEventData;
        });
    }
}
