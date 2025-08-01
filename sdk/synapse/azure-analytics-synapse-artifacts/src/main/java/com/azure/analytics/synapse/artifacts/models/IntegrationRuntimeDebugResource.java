// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.analytics.synapse.artifacts.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.annotation.Generated;
import com.azure.json.JsonReader;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;

/**
 * Integration runtime debug resource.
 */
@Fluent
public final class IntegrationRuntimeDebugResource extends SubResourceDebugResource {
    /*
     * Integration runtime properties.
     */
    @Generated
    private IntegrationRuntime properties;

    /**
     * Creates an instance of IntegrationRuntimeDebugResource class.
     */
    @Generated
    public IntegrationRuntimeDebugResource() {
    }

    /**
     * Get the properties property: Integration runtime properties.
     * 
     * @return the properties value.
     */
    @Generated
    public IntegrationRuntime getProperties() {
        return this.properties;
    }

    /**
     * Set the properties property: Integration runtime properties.
     * 
     * @param properties the properties value to set.
     * @return the IntegrationRuntimeDebugResource object itself.
     */
    @Generated
    public IntegrationRuntimeDebugResource setProperties(IntegrationRuntime properties) {
        this.properties = properties;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public IntegrationRuntimeDebugResource setName(String name) {
        super.setName(name);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("name", getName());
        jsonWriter.writeJsonField("properties", this.properties);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of IntegrationRuntimeDebugResource from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of IntegrationRuntimeDebugResource if the JsonReader was pointing to an instance of it, or
     * null if it was pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the IntegrationRuntimeDebugResource.
     */
    @Generated
    public static IntegrationRuntimeDebugResource fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            IntegrationRuntimeDebugResource deserializedIntegrationRuntimeDebugResource
                = new IntegrationRuntimeDebugResource();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("name".equals(fieldName)) {
                    deserializedIntegrationRuntimeDebugResource.setName(reader.getString());
                } else if ("properties".equals(fieldName)) {
                    deserializedIntegrationRuntimeDebugResource.properties = IntegrationRuntime.fromJson(reader);
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedIntegrationRuntimeDebugResource;
        });
    }
}
