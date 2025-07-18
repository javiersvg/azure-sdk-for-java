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
 * A phone number.
 */
@Fluent
public final class PhoneNumberIdentifierModel implements JsonSerializable<PhoneNumberIdentifierModel> {
    /*
     * The phone number, usually in E.164 format.
     */
    @Generated
    private String value;

    /*
     * True if the phone number is anonymous. By default false if missing. If the phone number is anonymous, the value
     * will be the string 'anonymous'.
     */
    @Generated
    private Boolean isAnonymous;

    /*
     * The asserted Id of the phone number. An asserted Id gets generated when the same phone number joins the same call
     * more than once.
     */
    @Generated
    private String assertedId;

    /**
     * Creates an instance of PhoneNumberIdentifierModel class.
     */
    @Generated
    public PhoneNumberIdentifierModel() {
    }

    /**
     * Get the value property: The phone number, usually in E.164 format.
     * 
     * @return the value value.
     */
    @Generated
    public String getValue() {
        return this.value;
    }

    /**
     * Set the value property: The phone number, usually in E.164 format.
     * 
     * @param value the value value to set.
     * @return the PhoneNumberIdentifierModel object itself.
     */
    @Generated
    public PhoneNumberIdentifierModel setValue(String value) {
        this.value = value;
        return this;
    }

    /**
     * Get the isAnonymous property: True if the phone number is anonymous. By default false if missing. If the phone
     * number is anonymous, the value will be the string 'anonymous'.
     * 
     * @return the isAnonymous value.
     */
    @Generated
    public Boolean isAnonymous() {
        return this.isAnonymous;
    }

    /**
     * Set the isAnonymous property: True if the phone number is anonymous. By default false if missing. If the phone
     * number is anonymous, the value will be the string 'anonymous'.
     * 
     * @param isAnonymous the isAnonymous value to set.
     * @return the PhoneNumberIdentifierModel object itself.
     */
    @Generated
    public PhoneNumberIdentifierModel setIsAnonymous(Boolean isAnonymous) {
        this.isAnonymous = isAnonymous;
        return this;
    }

    /**
     * Get the assertedId property: The asserted Id of the phone number. An asserted Id gets generated when the same
     * phone number joins the same call more than once.
     * 
     * @return the assertedId value.
     */
    @Generated
    public String getAssertedId() {
        return this.assertedId;
    }

    /**
     * Set the assertedId property: The asserted Id of the phone number. An asserted Id gets generated when the same
     * phone number joins the same call more than once.
     * 
     * @param assertedId the assertedId value to set.
     * @return the PhoneNumberIdentifierModel object itself.
     */
    @Generated
    public PhoneNumberIdentifierModel setAssertedId(String assertedId) {
        this.assertedId = assertedId;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("value", this.value);
        jsonWriter.writeBooleanField("isAnonymous", this.isAnonymous);
        jsonWriter.writeStringField("assertedId", this.assertedId);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of PhoneNumberIdentifierModel from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of PhoneNumberIdentifierModel if the JsonReader was pointing to an instance of it, or null if
     * it was pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the PhoneNumberIdentifierModel.
     */
    @Generated
    public static PhoneNumberIdentifierModel fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            PhoneNumberIdentifierModel deserializedPhoneNumberIdentifierModel = new PhoneNumberIdentifierModel();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("value".equals(fieldName)) {
                    deserializedPhoneNumberIdentifierModel.value = reader.getString();
                } else if ("isAnonymous".equals(fieldName)) {
                    deserializedPhoneNumberIdentifierModel.isAnonymous = reader.getNullable(JsonReader::getBoolean);
                } else if ("assertedId".equals(fieldName)) {
                    deserializedPhoneNumberIdentifierModel.assertedId = reader.getString();
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedPhoneNumberIdentifierModel;
        });
    }
}
