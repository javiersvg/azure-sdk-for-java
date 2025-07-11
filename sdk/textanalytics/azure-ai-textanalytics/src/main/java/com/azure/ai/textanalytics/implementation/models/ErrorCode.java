// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.ai.textanalytics.implementation.models;

import com.azure.core.annotation.Generated;
import com.azure.core.util.ExpandableStringEnum;
import java.util.Collection;

/**
 * Human-readable error code.
 */
public final class ErrorCode extends ExpandableStringEnum<ErrorCode> {
    /**
     * Static value InvalidRequest for ErrorCode.
     */
    @Generated
    public static final ErrorCode INVALID_REQUEST = fromString("InvalidRequest");

    /**
     * Static value InvalidArgument for ErrorCode.
     */
    @Generated
    public static final ErrorCode INVALID_ARGUMENT = fromString("InvalidArgument");

    /**
     * Static value Unauthorized for ErrorCode.
     */
    @Generated
    public static final ErrorCode UNAUTHORIZED = fromString("Unauthorized");

    /**
     * Static value Forbidden for ErrorCode.
     */
    @Generated
    public static final ErrorCode FORBIDDEN = fromString("Forbidden");

    /**
     * Static value NotFound for ErrorCode.
     */
    @Generated
    public static final ErrorCode NOT_FOUND = fromString("NotFound");

    /**
     * Static value ProjectNotFound for ErrorCode.
     */
    @Generated
    public static final ErrorCode PROJECT_NOT_FOUND = fromString("ProjectNotFound");

    /**
     * Static value OperationNotFound for ErrorCode.
     */
    @Generated
    public static final ErrorCode OPERATION_NOT_FOUND = fromString("OperationNotFound");

    /**
     * Static value AzureCognitiveSearchNotFound for ErrorCode.
     */
    @Generated
    public static final ErrorCode AZURE_COGNITIVE_SEARCH_NOT_FOUND = fromString("AzureCognitiveSearchNotFound");

    /**
     * Static value AzureCognitiveSearchIndexNotFound for ErrorCode.
     */
    @Generated
    public static final ErrorCode AZURE_COGNITIVE_SEARCH_INDEX_NOT_FOUND
        = fromString("AzureCognitiveSearchIndexNotFound");

    /**
     * Static value TooManyRequests for ErrorCode.
     */
    @Generated
    public static final ErrorCode TOO_MANY_REQUESTS = fromString("TooManyRequests");

    /**
     * Static value AzureCognitiveSearchThrottling for ErrorCode.
     */
    @Generated
    public static final ErrorCode AZURE_COGNITIVE_SEARCH_THROTTLING = fromString("AzureCognitiveSearchThrottling");

    /**
     * Static value AzureCognitiveSearchIndexLimitReached for ErrorCode.
     */
    @Generated
    public static final ErrorCode AZURE_COGNITIVE_SEARCH_INDEX_LIMIT_REACHED
        = fromString("AzureCognitiveSearchIndexLimitReached");

    /**
     * Static value InternalServerError for ErrorCode.
     */
    @Generated
    public static final ErrorCode INTERNAL_SERVER_ERROR = fromString("InternalServerError");

    /**
     * Static value ServiceUnavailable for ErrorCode.
     */
    @Generated
    public static final ErrorCode SERVICE_UNAVAILABLE = fromString("ServiceUnavailable");

    /**
     * Static value Timeout for ErrorCode.
     */
    @Generated
    public static final ErrorCode TIMEOUT = fromString("Timeout");

    /**
     * Static value QuotaExceeded for ErrorCode.
     */
    @Generated
    public static final ErrorCode QUOTA_EXCEEDED = fromString("QuotaExceeded");

    /**
     * Static value Conflict for ErrorCode.
     */
    @Generated
    public static final ErrorCode CONFLICT = fromString("Conflict");

    /**
     * Static value Warning for ErrorCode.
     */
    @Generated
    public static final ErrorCode WARNING = fromString("Warning");

    /**
     * Creates a new instance of ErrorCode value.
     * 
     * @deprecated Use the {@link #fromString(String)} factory method.
     */
    @Generated
    @Deprecated
    public ErrorCode() {
    }

    /**
     * Creates or finds a ErrorCode from its string representation.
     * 
     * @param name a name to look for.
     * @return the corresponding ErrorCode.
     */
    @Generated
    public static ErrorCode fromString(String name) {
        return fromString(name, ErrorCode.class);
    }

    /**
     * Gets known ErrorCode values.
     * 
     * @return known ErrorCode values.
     */
    @Generated
    public static Collection<ErrorCode> values() {
        return values(ErrorCode.class);
    }
}
