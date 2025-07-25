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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * This activity is used for iterating over a collection and execute given activities.
 */
@Fluent
public class ForEachActivity extends ControlActivity {
    /*
     * Type of activity.
     */
    @Generated
    private String type = "ForEach";

    /*
     * Should the loop be executed in sequence or in parallel (max 50)
     */
    @Generated
    private Boolean isSequential;

    /*
     * Batch count to be used for controlling the number of parallel execution (when isSequential is set to false).
     */
    @Generated
    private Integer batchCount;

    /*
     * Collection to iterate.
     */
    @Generated
    private Expression items;

    /*
     * List of activities to execute .
     */
    @Generated
    private List<Activity> activities;

    /**
     * Creates an instance of ForEachActivity class.
     */
    @Generated
    public ForEachActivity() {
    }

    /**
     * Get the type property: Type of activity.
     * 
     * @return the type value.
     */
    @Generated
    @Override
    public String getType() {
        return this.type;
    }

    /**
     * Get the isSequential property: Should the loop be executed in sequence or in parallel (max 50).
     * 
     * @return the isSequential value.
     */
    @Generated
    public Boolean isSequential() {
        return this.isSequential;
    }

    /**
     * Set the isSequential property: Should the loop be executed in sequence or in parallel (max 50).
     * 
     * @param isSequential the isSequential value to set.
     * @return the ForEachActivity object itself.
     */
    @Generated
    public ForEachActivity setIsSequential(Boolean isSequential) {
        this.isSequential = isSequential;
        return this;
    }

    /**
     * Get the batchCount property: Batch count to be used for controlling the number of parallel execution (when
     * isSequential is set to false).
     * 
     * @return the batchCount value.
     */
    @Generated
    public Integer getBatchCount() {
        return this.batchCount;
    }

    /**
     * Set the batchCount property: Batch count to be used for controlling the number of parallel execution (when
     * isSequential is set to false).
     * 
     * @param batchCount the batchCount value to set.
     * @return the ForEachActivity object itself.
     */
    @Generated
    public ForEachActivity setBatchCount(Integer batchCount) {
        this.batchCount = batchCount;
        return this;
    }

    /**
     * Get the items property: Collection to iterate.
     * 
     * @return the items value.
     */
    @Generated
    public Expression getItems() {
        return this.items;
    }

    /**
     * Set the items property: Collection to iterate.
     * 
     * @param items the items value to set.
     * @return the ForEachActivity object itself.
     */
    @Generated
    public ForEachActivity setItems(Expression items) {
        this.items = items;
        return this;
    }

    /**
     * Get the activities property: List of activities to execute .
     * 
     * @return the activities value.
     */
    @Generated
    public List<Activity> getActivities() {
        return this.activities;
    }

    /**
     * Set the activities property: List of activities to execute .
     * 
     * @param activities the activities value to set.
     * @return the ForEachActivity object itself.
     */
    @Generated
    public ForEachActivity setActivities(List<Activity> activities) {
        this.activities = activities;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public ForEachActivity setName(String name) {
        super.setName(name);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public ForEachActivity setDescription(String description) {
        super.setDescription(description);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public ForEachActivity setState(ActivityState state) {
        super.setState(state);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public ForEachActivity setOnInactiveMarkAs(ActivityOnInactiveMarkAs onInactiveMarkAs) {
        super.setOnInactiveMarkAs(onInactiveMarkAs);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public ForEachActivity setDependsOn(List<ActivityDependency> dependsOn) {
        super.setDependsOn(dependsOn);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public ForEachActivity setUserProperties(List<UserProperty> userProperties) {
        super.setUserProperties(userProperties);
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
        jsonWriter.writeStringField("description", getDescription());
        jsonWriter.writeStringField("state", getState() == null ? null : getState().toString());
        jsonWriter.writeStringField("onInactiveMarkAs",
            getOnInactiveMarkAs() == null ? null : getOnInactiveMarkAs().toString());
        jsonWriter.writeArrayField("dependsOn", getDependsOn(), (writer, element) -> writer.writeJson(element));
        jsonWriter.writeArrayField("userProperties", getUserProperties(),
            (writer, element) -> writer.writeJson(element));
        jsonWriter.writeStringField("type", this.type);
        if (isSequential != null || batchCount != null || items != null || activities != null) {
            jsonWriter.writeStartObject("typeProperties");
            jsonWriter.writeBooleanField("isSequential", this.isSequential);
            jsonWriter.writeNumberField("batchCount", this.batchCount);
            jsonWriter.writeJsonField("items", this.items);
            jsonWriter.writeArrayField("activities", this.activities, (writer, element) -> writer.writeJson(element));
            jsonWriter.writeEndObject();
        }
        if (getAdditionalProperties() != null) {
            for (Map.Entry<String, Object> additionalProperty : getAdditionalProperties().entrySet()) {
                jsonWriter.writeUntypedField(additionalProperty.getKey(), additionalProperty.getValue());
            }
        }
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of ForEachActivity from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of ForEachActivity if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the ForEachActivity.
     */
    @Generated
    public static ForEachActivity fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            ForEachActivity deserializedForEachActivity = new ForEachActivity();
            Map<String, Object> additionalProperties = null;
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("name".equals(fieldName)) {
                    deserializedForEachActivity.setName(reader.getString());
                } else if ("description".equals(fieldName)) {
                    deserializedForEachActivity.setDescription(reader.getString());
                } else if ("state".equals(fieldName)) {
                    deserializedForEachActivity.setState(ActivityState.fromString(reader.getString()));
                } else if ("onInactiveMarkAs".equals(fieldName)) {
                    deserializedForEachActivity
                        .setOnInactiveMarkAs(ActivityOnInactiveMarkAs.fromString(reader.getString()));
                } else if ("dependsOn".equals(fieldName)) {
                    List<ActivityDependency> dependsOn
                        = reader.readArray(reader1 -> ActivityDependency.fromJson(reader1));
                    deserializedForEachActivity.setDependsOn(dependsOn);
                } else if ("userProperties".equals(fieldName)) {
                    List<UserProperty> userProperties = reader.readArray(reader1 -> UserProperty.fromJson(reader1));
                    deserializedForEachActivity.setUserProperties(userProperties);
                } else if ("type".equals(fieldName)) {
                    deserializedForEachActivity.type = reader.getString();
                } else if ("typeProperties".equals(fieldName) && reader.currentToken() == JsonToken.START_OBJECT) {
                    while (reader.nextToken() != JsonToken.END_OBJECT) {
                        fieldName = reader.getFieldName();
                        reader.nextToken();

                        if ("isSequential".equals(fieldName)) {
                            deserializedForEachActivity.isSequential = reader.getNullable(JsonReader::getBoolean);
                        } else if ("batchCount".equals(fieldName)) {
                            deserializedForEachActivity.batchCount = reader.getNullable(JsonReader::getInt);
                        } else if ("items".equals(fieldName)) {
                            deserializedForEachActivity.items = Expression.fromJson(reader);
                        } else if ("activities".equals(fieldName)) {
                            List<Activity> activities = reader.readArray(reader1 -> Activity.fromJson(reader1));
                            deserializedForEachActivity.activities = activities;
                        } else {
                            reader.skipChildren();
                        }
                    }
                } else {
                    if (additionalProperties == null) {
                        additionalProperties = new LinkedHashMap<>();
                    }

                    additionalProperties.put(fieldName, reader.readUntyped());
                }
            }
            deserializedForEachActivity.setAdditionalProperties(additionalProperties);

            return deserializedForEachActivity;
        });
    }
}
