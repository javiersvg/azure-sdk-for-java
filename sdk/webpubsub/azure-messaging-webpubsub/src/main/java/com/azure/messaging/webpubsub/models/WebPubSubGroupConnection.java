// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.messaging.webpubsub.models;

import com.azure.core.annotation.Immutable;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;

/**
 * Represents a connection in a group.
 */
@Immutable
public final class WebPubSubGroupConnection implements JsonSerializable<WebPubSubGroupConnection> {

    /**
     * The ID of a connection.
     */
    private final String connectionId;

    /**
     * The ID of a user.
     */
    private final String userId;

    /**
     * Creates an instance of WebPubSubGroupMember class.
     *
     * @param connectionId the connectionId value to set.
     * @param userId the userId value to set.
     */
    private WebPubSubGroupConnection(String connectionId, String userId) {
        this.connectionId = connectionId;
        this.userId = userId;
    }

    /**
     * Gets the connection ID.
     *
     * @return The ID of the connection.
     */
    public String getConnectionId() {
        return connectionId;
    }

    /**
     * Gets the user ID.
     *
     * @return The ID of the user.
     */
    public String getUserId() {
        return userId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("connectionId", this.connectionId);
        if (this.userId != null) {
            jsonWriter.writeStringField("userId", this.userId);
        }
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of WebPubSubGroupMember from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of WebPubSubGroupMember if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the WebPubSubGroupMember.
     */
    public static WebPubSubGroupConnection fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            String connectionId = null;
            String userId = null;
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();
                if ("connectionId".equals(fieldName)) {
                    connectionId = reader.getString();
                } else if ("userId".equals(fieldName)) {
                    userId = reader.getString();
                } else {
                    reader.skipChildren();
                }
            }
            if (connectionId == null) {
                throw new IllegalStateException("Missing required property: connectionId");
            }
            return new WebPubSubGroupConnection(connectionId, userId);
        });
    }
}
