// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.ai.textanalytics.implementation;

import com.azure.ai.textanalytics.implementation.models.AnalyzeTextTask;
import com.azure.ai.textanalytics.implementation.models.AnalyzeTextTaskResult;
import com.azure.ai.textanalytics.implementation.models.ErrorResponseException;
import com.azure.core.annotation.BodyParam;
import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.HeaderParam;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.HostParam;
import com.azure.core.annotation.Post;
import com.azure.core.annotation.QueryParam;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceInterface;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.annotation.UnexpectedResponseExceptionType;
import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.UserAgentPolicy;
import com.azure.core.http.rest.Response;
import com.azure.core.http.rest.RestProxy;
import com.azure.core.util.Context;
import com.azure.core.util.FluxUtil;
import com.azure.core.util.serializer.JacksonAdapter;
import com.azure.core.util.serializer.SerializerAdapter;
import reactor.core.publisher.Mono;

/**
 * Initializes a new instance of the MicrosoftCognitiveLanguageServiceTextAnalysis type.
 */
public final class MicrosoftCognitiveLanguageServiceTextAnalysisImpl {
    /**
     * The proxy service used to perform REST calls.
     */
    private final MicrosoftCognitiveLanguageServiceTextAnalysisService service;

    /**
     * Supported Cognitive Services endpoint (e.g., https://&lt;resource-name&gt;.api.cognitiveservices.azure.com).
     */
    private final String endpoint;

    /**
     * Gets Supported Cognitive Services endpoint (e.g., https://&lt;resource-name&gt;.api.cognitiveservices.azure.com).
     * 
     * @return the endpoint value.
     */
    public String getEndpoint() {
        return this.endpoint;
    }

    /**
     * Api Version.
     */
    private final String apiVersion;

    /**
     * Gets Api Version.
     * 
     * @return the apiVersion value.
     */
    public String getApiVersion() {
        return this.apiVersion;
    }

    /**
     * The HTTP pipeline to send requests through.
     */
    private final HttpPipeline httpPipeline;

    /**
     * Gets The HTTP pipeline to send requests through.
     * 
     * @return the httpPipeline value.
     */
    public HttpPipeline getHttpPipeline() {
        return this.httpPipeline;
    }

    /**
     * The serializer to serialize an object into a string.
     */
    private final SerializerAdapter serializerAdapter;

    /**
     * Gets The serializer to serialize an object into a string.
     * 
     * @return the serializerAdapter value.
     */
    public SerializerAdapter getSerializerAdapter() {
        return this.serializerAdapter;
    }

    /**
     * The AnalyzeTextsImpl object to access its operations.
     */
    private final AnalyzeTextsImpl analyzeTexts;

    /**
     * Gets the AnalyzeTextsImpl object to access its operations.
     * 
     * @return the AnalyzeTextsImpl object.
     */
    public AnalyzeTextsImpl getAnalyzeTexts() {
        return this.analyzeTexts;
    }

    /**
     * Initializes an instance of MicrosoftCognitiveLanguageServiceTextAnalysis client.
     * 
     * @param endpoint Supported Cognitive Services endpoint (e.g.,
     * https://&lt;resource-name&gt;.api.cognitiveservices.azure.com).
     * @param apiVersion Api Version.
     */
    MicrosoftCognitiveLanguageServiceTextAnalysisImpl(String endpoint, String apiVersion) {
        this(new HttpPipelineBuilder().policies(new UserAgentPolicy(), new RetryPolicy()).build(),
            JacksonAdapter.createDefaultSerializerAdapter(), endpoint, apiVersion);
    }

    /**
     * Initializes an instance of MicrosoftCognitiveLanguageServiceTextAnalysis client.
     * 
     * @param httpPipeline The HTTP pipeline to send requests through.
     * @param endpoint Supported Cognitive Services endpoint (e.g.,
     * https://&lt;resource-name&gt;.api.cognitiveservices.azure.com).
     * @param apiVersion Api Version.
     */
    MicrosoftCognitiveLanguageServiceTextAnalysisImpl(HttpPipeline httpPipeline, String endpoint, String apiVersion) {
        this(httpPipeline, JacksonAdapter.createDefaultSerializerAdapter(), endpoint, apiVersion);
    }

    /**
     * Initializes an instance of MicrosoftCognitiveLanguageServiceTextAnalysis client.
     * 
     * @param httpPipeline The HTTP pipeline to send requests through.
     * @param serializerAdapter The serializer to serialize an object into a string.
     * @param endpoint Supported Cognitive Services endpoint (e.g.,
     * https://&lt;resource-name&gt;.api.cognitiveservices.azure.com).
     * @param apiVersion Api Version.
     */
    MicrosoftCognitiveLanguageServiceTextAnalysisImpl(HttpPipeline httpPipeline, SerializerAdapter serializerAdapter,
        String endpoint, String apiVersion) {
        this.httpPipeline = httpPipeline;
        this.serializerAdapter = serializerAdapter;
        this.endpoint = endpoint;
        this.apiVersion = apiVersion;
        this.analyzeTexts = new AnalyzeTextsImpl(this);
        this.service = RestProxy.create(MicrosoftCognitiveLanguageServiceTextAnalysisService.class, this.httpPipeline,
            this.getSerializerAdapter());
    }

    /**
     * The interface defining all the services for MicrosoftCognitiveLanguageServiceTextAnalysis to be used by the proxy
     * service to perform REST calls.
     */
    @Host("{Endpoint}/language")
    @ServiceInterface(name = "MicrosoftCognitiveLanguageServiceTextAnalysis")
    public interface MicrosoftCognitiveLanguageServiceTextAnalysisService {
        @Post("/:analyze-text")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(ErrorResponseException.class)
        Mono<Response<AnalyzeTextTaskResult>> analyzeText(@HostParam("Endpoint") String endpoint,
            @QueryParam("api-version") String apiVersion, @QueryParam("showStats") Boolean showStats,
            @BodyParam("application/json") AnalyzeTextTask body, @HeaderParam("Accept") String accept, Context context);

        @Post("/:analyze-text")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(ErrorResponseException.class)
        Response<AnalyzeTextTaskResult> analyzeTextSync(@HostParam("Endpoint") String endpoint,
            @QueryParam("api-version") String apiVersion, @QueryParam("showStats") Boolean showStats,
            @BodyParam("application/json") AnalyzeTextTask body, @HeaderParam("Accept") String accept, Context context);
    }

    /**
     * Request text analysis over a collection of documents.
     * 
     * Submit a collection of text documents for analysis. Specify a single unique task to be executed immediately.
     * 
     * @param body Collection of documents to analyze and a single task to execute.
     * @param showStats (Optional) if set to true, response will contain request and document level statistics.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<AnalyzeTextTaskResult>> analyzeTextWithResponseAsync(AnalyzeTextTask body, Boolean showStats) {
        return FluxUtil.withContext(context -> analyzeTextWithResponseAsync(body, showStats, context));
    }

    /**
     * Request text analysis over a collection of documents.
     * 
     * Submit a collection of text documents for analysis. Specify a single unique task to be executed immediately.
     * 
     * @param body Collection of documents to analyze and a single task to execute.
     * @param showStats (Optional) if set to true, response will contain request and document level statistics.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<AnalyzeTextTaskResult>> analyzeTextWithResponseAsync(AnalyzeTextTask body, Boolean showStats,
        Context context) {
        final String accept = "application/json";
        return service.analyzeText(this.getEndpoint(), this.getApiVersion(), showStats, body, accept, context);
    }

    /**
     * Request text analysis over a collection of documents.
     * 
     * Submit a collection of text documents for analysis. Specify a single unique task to be executed immediately.
     * 
     * @param body Collection of documents to analyze and a single task to execute.
     * @param showStats (Optional) if set to true, response will contain request and document level statistics.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<AnalyzeTextTaskResult> analyzeTextAsync(AnalyzeTextTask body, Boolean showStats) {
        return analyzeTextWithResponseAsync(body, showStats).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Request text analysis over a collection of documents.
     * 
     * Submit a collection of text documents for analysis. Specify a single unique task to be executed immediately.
     * 
     * @param body Collection of documents to analyze and a single task to execute.
     * @param showStats (Optional) if set to true, response will contain request and document level statistics.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<AnalyzeTextTaskResult> analyzeTextAsync(AnalyzeTextTask body, Boolean showStats, Context context) {
        return analyzeTextWithResponseAsync(body, showStats, context).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Request text analysis over a collection of documents.
     * 
     * Submit a collection of text documents for analysis. Specify a single unique task to be executed immediately.
     * 
     * @param body Collection of documents to analyze and a single task to execute.
     * @param showStats (Optional) if set to true, response will contain request and document level statistics.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<AnalyzeTextTaskResult> analyzeTextWithResponse(AnalyzeTextTask body, Boolean showStats,
        Context context) {
        final String accept = "application/json";
        return service.analyzeTextSync(this.getEndpoint(), this.getApiVersion(), showStats, body, accept, context);
    }

    /**
     * Request text analysis over a collection of documents.
     * 
     * Submit a collection of text documents for analysis. Specify a single unique task to be executed immediately.
     * 
     * @param body Collection of documents to analyze and a single task to execute.
     * @param showStats (Optional) if set to true, response will contain request and document level statistics.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorResponseException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public AnalyzeTextTaskResult analyzeText(AnalyzeTextTask body, Boolean showStats) {
        return analyzeTextWithResponse(body, showStats, Context.NONE).getValue();
    }
}
