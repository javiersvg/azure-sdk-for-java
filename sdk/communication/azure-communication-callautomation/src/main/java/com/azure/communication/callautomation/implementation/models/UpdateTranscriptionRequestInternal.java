// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.communication.callautomation.implementation.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.annotation.Generated;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;

/**
 * The UpdateTranscriptionRequestInternal model.
 */
@Fluent
public final class UpdateTranscriptionRequestInternal implements JsonSerializable<UpdateTranscriptionRequestInternal> {
    /*
     * Defines new locale for transcription.
     */
    @Generated
    private String locale;

    /*
     * Sets Endpoint id where the custom model was deployed.
     */
    @Generated
    private String speechModelEndpointId;

    /*
     * The value to identify context of the operation.
     */
    @Generated
    private String operationContext;

    /*
     * Set a callback URI that overrides the default callback URI set by CreateCall/AnswerCall for this operation.
     * This setup is per-action. If this is not set, the default callback URI set by CreateCall/AnswerCall will be used.
     */
    @Generated
    private String operationCallbackUri;

    /**
     * Creates an instance of UpdateTranscriptionRequestInternal class.
     */
    @Generated
    public UpdateTranscriptionRequestInternal() {
    }

    /**
     * Get the locale property: Defines new locale for transcription.
     * 
     * @return the locale value.
     */
    @Generated
    public String getLocale() {
        return this.locale;
    }

    /**
     * Set the locale property: Defines new locale for transcription.
     * 
     * @param locale the locale value to set.
     * @return the UpdateTranscriptionRequestInternal object itself.
     */
    @Generated
    public UpdateTranscriptionRequestInternal setLocale(String locale) {
        this.locale = locale;
        return this;
    }

    /**
     * Get the speechModelEndpointId property: Sets Endpoint id where the custom model was deployed.
     * 
     * @return the speechModelEndpointId value.
     */
    @Generated
    public String getSpeechModelEndpointId() {
        return this.speechModelEndpointId;
    }

    /**
     * Set the speechModelEndpointId property: Sets Endpoint id where the custom model was deployed.
     * 
     * @param speechModelEndpointId the speechModelEndpointId value to set.
     * @return the UpdateTranscriptionRequestInternal object itself.
     */
    @Generated
    public UpdateTranscriptionRequestInternal setSpeechModelEndpointId(String speechModelEndpointId) {
        this.speechModelEndpointId = speechModelEndpointId;
        return this;
    }

    /**
     * Get the operationContext property: The value to identify context of the operation.
     * 
     * @return the operationContext value.
     */
    @Generated
    public String getOperationContext() {
        return this.operationContext;
    }

    /**
     * Set the operationContext property: The value to identify context of the operation.
     * 
     * @param operationContext the operationContext value to set.
     * @return the UpdateTranscriptionRequestInternal object itself.
     */
    @Generated
    public UpdateTranscriptionRequestInternal setOperationContext(String operationContext) {
        this.operationContext = operationContext;
        return this;
    }

    /**
     * Get the operationCallbackUri property: Set a callback URI that overrides the default callback URI set by
     * CreateCall/AnswerCall for this operation.
     * This setup is per-action. If this is not set, the default callback URI set by CreateCall/AnswerCall will be used.
     * 
     * @return the operationCallbackUri value.
     */
    @Generated
    public String getOperationCallbackUri() {
        return this.operationCallbackUri;
    }

    /**
     * Set the operationCallbackUri property: Set a callback URI that overrides the default callback URI set by
     * CreateCall/AnswerCall for this operation.
     * This setup is per-action. If this is not set, the default callback URI set by CreateCall/AnswerCall will be used.
     * 
     * @param operationCallbackUri the operationCallbackUri value to set.
     * @return the UpdateTranscriptionRequestInternal object itself.
     */
    @Generated
    public UpdateTranscriptionRequestInternal setOperationCallbackUri(String operationCallbackUri) {
        this.operationCallbackUri = operationCallbackUri;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("locale", this.locale);
        jsonWriter.writeStringField("speechModelEndpointId", this.speechModelEndpointId);
        jsonWriter.writeStringField("operationContext", this.operationContext);
        jsonWriter.writeStringField("operationCallbackUri", this.operationCallbackUri);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of UpdateTranscriptionRequestInternal from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of UpdateTranscriptionRequestInternal if the JsonReader was pointing to an instance of it, or
     * null if it was pointing to JSON null.
     * @throws IOException If an error occurs while reading the UpdateTranscriptionRequestInternal.
     */
    @Generated
    public static UpdateTranscriptionRequestInternal fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            UpdateTranscriptionRequestInternal deserializedUpdateTranscriptionRequestInternal
                = new UpdateTranscriptionRequestInternal();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("locale".equals(fieldName)) {
                    deserializedUpdateTranscriptionRequestInternal.locale = reader.getString();
                } else if ("speechModelEndpointId".equals(fieldName)) {
                    deserializedUpdateTranscriptionRequestInternal.speechModelEndpointId = reader.getString();
                } else if ("operationContext".equals(fieldName)) {
                    deserializedUpdateTranscriptionRequestInternal.operationContext = reader.getString();
                } else if ("operationCallbackUri".equals(fieldName)) {
                    deserializedUpdateTranscriptionRequestInternal.operationCallbackUri = reader.getString();
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedUpdateTranscriptionRequestInternal;
        });
    }
}
