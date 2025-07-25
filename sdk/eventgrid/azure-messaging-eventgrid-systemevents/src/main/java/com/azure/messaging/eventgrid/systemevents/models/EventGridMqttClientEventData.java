// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.
package com.azure.messaging.eventgrid.systemevents.models;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.Immutable;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;

/**
 * Schema of the Data property of an EventGridEvent for MQTT Client state changes.
 */
@Immutable
public class EventGridMqttClientEventData implements JsonSerializable<EventGridMqttClientEventData> {

    /*
     * Unique identifier for the MQTT client that the client presents to the service
     * for authentication. This case-sensitive string can be up to 128 characters
     * long, and supports UTF-8 characters.
     */
    @Generated
    private final String clientAuthenticationName;

    /*
     * Name of the client resource in the Event Grid namespace.
     */
    @Generated
    private String clientName;

    /*
     * Name of the Event Grid namespace where the MQTT client was created or updated.
     */
    @Generated
    private final String namespaceName;

    /**
     * Creates an instance of EventGridMqttClientEventData class.
     *
     * @param clientAuthenticationName the clientAuthenticationName value to set.
     * @param namespaceName the namespaceName value to set.
     */
    @Generated
    protected EventGridMqttClientEventData(String clientAuthenticationName, String namespaceName) {
        this.clientAuthenticationName = clientAuthenticationName;
        this.namespaceName = namespaceName;
    }

    /**
     * Get the clientAuthenticationName property: Unique identifier for the MQTT client that the client presents to the
     * service
     * for authentication. This case-sensitive string can be up to 128 characters
     * long, and supports UTF-8 characters.
     *
     * @return the clientAuthenticationName value.
     */
    @Generated
    public String getClientAuthenticationName() {
        return this.clientAuthenticationName;
    }

    /**
     * Get the clientName property: Name of the client resource in the Event Grid namespace.
     *
     * @return the clientName value.
     */
    @Generated
    public String getClientName() {
        return this.clientName;
    }

    /**
     * Set the clientName property: Name of the client resource in the Event Grid namespace.
     *
     * @param clientName the clientName value to set.
     * @return the EventGridMqttClientEventData object itself.
     */
    @Generated
    private EventGridMqttClientEventData setClientName(String clientName) {
        this.clientName = clientName;
        return this;
    }

    /**
     * Get the namespaceName property: Name of the Event Grid namespace where the MQTT client was created or updated.
     *
     * @return the namespaceName value.
     */
    @Generated
    public String getNamespaceName() {
        return this.namespaceName;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("clientAuthenticationName", this.clientAuthenticationName);
        jsonWriter.writeStringField("namespaceName", this.namespaceName);
        jsonWriter.writeStringField("clientName", this.clientName);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of EventGridMqttClientEventData from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of EventGridMqttClientEventData if the JsonReader was pointing to an instance of it, or null
     * if it was pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the EventGridMqttClientEventData.
     */
    @Generated
    public static EventGridMqttClientEventData fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            String clientAuthenticationName = null;
            String namespaceName = null;
            String clientName = null;
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();
                if ("clientAuthenticationName".equals(fieldName)) {
                    clientAuthenticationName = reader.getString();
                } else if ("namespaceName".equals(fieldName)) {
                    namespaceName = reader.getString();
                } else if ("clientName".equals(fieldName)) {
                    clientName = reader.getString();
                } else {
                    reader.skipChildren();
                }
            }
            EventGridMqttClientEventData deserializedEventGridMqttClientEventData
                = new EventGridMqttClientEventData(clientAuthenticationName, namespaceName);
            deserializedEventGridMqttClientEventData.clientName = clientName;
            return deserializedEventGridMqttClientEventData;
        });
    }
}
