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
 * Schema of the Data property of an EventGridEvent for a Microsoft.Communication.RouterWorkerDeregistered event.
 * 
 * @deprecated This class is deprecated and may be removed in future releases. System events are now available in the
 * azure-messaging-eventgrid-systemevents package.
 */
@Fluent
@Deprecated
public final class AcsRouterWorkerDeregisteredEventData
    implements JsonSerializable<AcsRouterWorkerDeregisteredEventData> {

    /*
     * Router Worker Deregistered Worker Id
     */
    @Generated
    private String workerId;

    /**
     * Creates an instance of AcsRouterWorkerDeregisteredEventData class.
     */
    @Generated
    public AcsRouterWorkerDeregisteredEventData() {
    }

    /**
     * Get the workerId property: Router Worker Deregistered Worker Id.
     *
     * @return the workerId value.
     */
    @Generated
    public String getWorkerId() {
        return this.workerId;
    }

    /**
     * Set the workerId property: Router Worker Deregistered Worker Id.
     *
     * @param workerId the workerId value to set.
     * @return the AcsRouterWorkerDeregisteredEventData object itself.
     */
    @Generated
    public AcsRouterWorkerDeregisteredEventData setWorkerId(String workerId) {
        this.workerId = workerId;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("workerId", this.workerId);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of AcsRouterWorkerDeregisteredEventData from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of AcsRouterWorkerDeregisteredEventData if the JsonReader was pointing to an instance of it,
     * or null if it was pointing to JSON null.
     * @throws IOException If an error occurs while reading the AcsRouterWorkerDeregisteredEventData.
     */
    @Generated
    public static AcsRouterWorkerDeregisteredEventData fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            AcsRouterWorkerDeregisteredEventData deserializedAcsRouterWorkerDeregisteredEventData
                = new AcsRouterWorkerDeregisteredEventData();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();
                if ("workerId".equals(fieldName)) {
                    deserializedAcsRouterWorkerDeregisteredEventData.workerId = reader.getString();
                } else {
                    reader.skipChildren();
                }
            }
            return deserializedAcsRouterWorkerDeregisteredEventData;
        });
    }
}
