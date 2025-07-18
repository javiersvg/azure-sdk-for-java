// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.
package com.azure.messaging.eventgrid.systemevents;

import com.azure.core.annotation.Fluent;
import com.azure.core.annotation.Generated;
import com.azure.core.util.CoreUtils;
import com.azure.json.JsonReader;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Schema of the Data property of an EventGridEvent for a Microsoft.Communication.SMSReceived event.
 * 
 * @deprecated This class is deprecated and may be removed in future releases. System events are now available in the
 * azure-messaging-eventgrid-systemevents package.
 */
@Fluent
@Deprecated
public final class AcsSmsReceivedEventData extends AcsSmsEventBaseProperties {

    /*
     * The SMS content
     */
    @Generated
    private String message;

    /*
     * The time at which the SMS was received
     */
    @Generated
    private OffsetDateTime receivedTimestamp;

    /*
     * Number of segments in the message
     */
    @Generated
    private Integer segmentCount;

    /**
     * Creates an instance of AcsSmsReceivedEventData class.
     */
    @Generated
    public AcsSmsReceivedEventData() {
    }

    /**
     * Get the message property: The SMS content.
     *
     * @return the message value.
     */
    @Generated
    public String getMessage() {
        return this.message;
    }

    /**
     * Set the message property: The SMS content.
     *
     * @param message the message value to set.
     * @return the AcsSmsReceivedEventData object itself.
     */
    @Generated
    public AcsSmsReceivedEventData setMessage(String message) {
        this.message = message;
        return this;
    }

    /**
     * Get the receivedTimestamp property: The time at which the SMS was received.
     *
     * @return the receivedTimestamp value.
     */
    @Generated
    public OffsetDateTime getReceivedTimestamp() {
        return this.receivedTimestamp;
    }

    /**
     * Set the receivedTimestamp property: The time at which the SMS was received.
     *
     * @param receivedTimestamp the receivedTimestamp value to set.
     * @return the AcsSmsReceivedEventData object itself.
     */
    @Generated
    public AcsSmsReceivedEventData setReceivedTimestamp(OffsetDateTime receivedTimestamp) {
        this.receivedTimestamp = receivedTimestamp;
        return this;
    }

    /**
     * Get the segmentCount property: Number of segments in the message.
     *
     * @return the segmentCount value.
     */
    @Generated
    public Integer getSegmentCount() {
        return this.segmentCount;
    }

    /**
     * Set the segmentCount property: Number of segments in the message.
     *
     * @param segmentCount the segmentCount value to set.
     * @return the AcsSmsReceivedEventData object itself.
     */
    @Generated
    public AcsSmsReceivedEventData setSegmentCount(Integer segmentCount) {
        this.segmentCount = segmentCount;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public AcsSmsReceivedEventData setMessageId(String messageId) {
        super.setMessageId(messageId);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public AcsSmsReceivedEventData setFrom(String from) {
        super.setFrom(from);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public AcsSmsReceivedEventData setTo(String to) {
        super.setTo(to);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("messageId", getMessageId());
        jsonWriter.writeStringField("from", getFrom());
        jsonWriter.writeStringField("to", getTo());
        jsonWriter.writeStringField("message", this.message);
        jsonWriter.writeIntField("segmentCount", this.segmentCount);
        jsonWriter.writeStringField("receivedTimestamp",
            this.receivedTimestamp == null
                ? null
                : DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(this.receivedTimestamp));
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of AcsSmsReceivedEventData from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of AcsSmsReceivedEventData if the JsonReader was pointing to an instance of it, or null if it
     * was pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the AcsSmsReceivedEventData.
     */
    @Generated
    public static AcsSmsReceivedEventData fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            AcsSmsReceivedEventData deserializedAcsSmsReceivedEventData = new AcsSmsReceivedEventData();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();
                if ("messageId".equals(fieldName)) {
                    deserializedAcsSmsReceivedEventData.setMessageId(reader.getString());
                } else if ("from".equals(fieldName)) {
                    deserializedAcsSmsReceivedEventData.setFrom(reader.getString());
                } else if ("to".equals(fieldName)) {
                    deserializedAcsSmsReceivedEventData.setTo(reader.getString());
                } else if ("message".equals(fieldName)) {
                    deserializedAcsSmsReceivedEventData.message = reader.getString();
                } else if ("segmentCount".equals(fieldName)) {
                    deserializedAcsSmsReceivedEventData.segmentCount = reader.getInt();
                } else if ("receivedTimestamp".equals(fieldName)) {
                    deserializedAcsSmsReceivedEventData.receivedTimestamp = reader
                        .getNullable(nonNullReader -> CoreUtils.parseBestOffsetDateTime(nonNullReader.getString()));
                } else {
                    reader.skipChildren();
                }
            }
            return deserializedAcsSmsReceivedEventData;
        });
    }
}
