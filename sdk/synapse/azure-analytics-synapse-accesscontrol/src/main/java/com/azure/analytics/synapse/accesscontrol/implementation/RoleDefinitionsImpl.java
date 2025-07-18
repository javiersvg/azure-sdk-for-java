// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.analytics.synapse.accesscontrol.implementation;

import com.azure.analytics.synapse.accesscontrol.models.ErrorContractException;
import com.azure.analytics.synapse.accesscontrol.models.SynapseRoleDefinition;
import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.Get;
import com.azure.core.annotation.HeaderParam;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.HostParam;
import com.azure.core.annotation.PathParam;
import com.azure.core.annotation.QueryParam;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceInterface;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.annotation.UnexpectedResponseExceptionType;
import com.azure.core.http.rest.Response;
import com.azure.core.http.rest.RestProxy;
import com.azure.core.util.Context;
import com.azure.core.util.FluxUtil;
import java.util.List;
import reactor.core.publisher.Mono;

/**
 * An instance of this class provides access to all the operations defined in RoleDefinitions.
 */
public final class RoleDefinitionsImpl {
    /**
     * The proxy service used to perform REST calls.
     */
    private final RoleDefinitionsService service;

    /**
     * The service client containing this operation class.
     */
    private final AccessControlClientImpl client;

    /**
     * Initializes an instance of RoleDefinitionsImpl.
     * 
     * @param client the instance of the service client containing this operation class.
     */
    RoleDefinitionsImpl(AccessControlClientImpl client) {
        this.service
            = RestProxy.create(RoleDefinitionsService.class, client.getHttpPipeline(), client.getSerializerAdapter());
        this.client = client;
    }

    /**
     * The interface defining all the services for AccessControlClientRoleDefinitions to be used by the proxy service to
     * perform REST calls.
     */
    @Host("{endpoint}")
    @ServiceInterface(name = "AccessControlClientRoleDefinitions")
    public interface RoleDefinitionsService {
        @Get("/roleDefinitions")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(ErrorContractException.class)
        Mono<Response<List<SynapseRoleDefinition>>> listRoleDefinitions(@HostParam("endpoint") String endpoint,
            @QueryParam("api-version") String apiVersion, @QueryParam("isBuiltIn") Boolean isBuiltIn,
            @QueryParam("scope") String scope, @HeaderParam("Accept") String accept, Context context);

        @Get("/roleDefinitions/{roleDefinitionId}")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(ErrorContractException.class)
        Mono<Response<SynapseRoleDefinition>> getRoleDefinitionById(@HostParam("endpoint") String endpoint,
            @QueryParam("api-version") String apiVersion, @PathParam("roleDefinitionId") String roleDefinitionId,
            @HeaderParam("Accept") String accept, Context context);

        @Get("/rbacScopes")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(ErrorContractException.class)
        Mono<Response<List<String>>> listScopes(@HostParam("endpoint") String endpoint,
            @QueryParam("api-version") String apiVersion, @HeaderParam("Accept") String accept, Context context);
    }

    /**
     * List role definitions.
     * 
     * @param isBuiltIn Is a Synapse Built-In Role or not.
     * @param scope Scope of the Synapse Built-in Role.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorContractException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a list of Synapse roles available along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<List<SynapseRoleDefinition>>> listRoleDefinitionsWithResponseAsync(Boolean isBuiltIn,
        String scope) {
        return FluxUtil.withContext(context -> listRoleDefinitionsWithResponseAsync(isBuiltIn, scope, context));
    }

    /**
     * List role definitions.
     * 
     * @param isBuiltIn Is a Synapse Built-In Role or not.
     * @param scope Scope of the Synapse Built-in Role.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorContractException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a list of Synapse roles available along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<List<SynapseRoleDefinition>>> listRoleDefinitionsWithResponseAsync(Boolean isBuiltIn,
        String scope, Context context) {
        final String accept = "application/json, text/json";
        return service.listRoleDefinitions(this.client.getEndpoint(), this.client.getApiVersion(), isBuiltIn, scope,
            accept, context);
    }

    /**
     * List role definitions.
     * 
     * @param isBuiltIn Is a Synapse Built-In Role or not.
     * @param scope Scope of the Synapse Built-in Role.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorContractException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a list of Synapse roles available on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<List<SynapseRoleDefinition>> listRoleDefinitionsAsync(Boolean isBuiltIn, String scope) {
        return listRoleDefinitionsWithResponseAsync(isBuiltIn, scope).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * List role definitions.
     * 
     * @throws ErrorContractException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a list of Synapse roles available on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<List<SynapseRoleDefinition>> listRoleDefinitionsAsync() {
        final Boolean isBuiltIn = null;
        final String scope = null;
        return listRoleDefinitionsWithResponseAsync(isBuiltIn, scope).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * List role definitions.
     * 
     * @param isBuiltIn Is a Synapse Built-In Role or not.
     * @param scope Scope of the Synapse Built-in Role.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorContractException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a list of Synapse roles available on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<List<SynapseRoleDefinition>> listRoleDefinitionsAsync(Boolean isBuiltIn, String scope,
        Context context) {
        return listRoleDefinitionsWithResponseAsync(isBuiltIn, scope, context)
            .flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * List role definitions.
     * 
     * @param isBuiltIn Is a Synapse Built-In Role or not.
     * @param scope Scope of the Synapse Built-in Role.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorContractException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a list of Synapse roles available along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<List<SynapseRoleDefinition>> listRoleDefinitionsWithResponse(Boolean isBuiltIn, String scope,
        Context context) {
        return listRoleDefinitionsWithResponseAsync(isBuiltIn, scope, context).block();
    }

    /**
     * List role definitions.
     * 
     * @param isBuiltIn Is a Synapse Built-In Role or not.
     * @param scope Scope of the Synapse Built-in Role.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorContractException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a list of Synapse roles available.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public List<SynapseRoleDefinition> listRoleDefinitions(Boolean isBuiltIn, String scope) {
        return listRoleDefinitionsWithResponse(isBuiltIn, scope, Context.NONE).getValue();
    }

    /**
     * List role definitions.
     * 
     * @throws ErrorContractException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a list of Synapse roles available.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public List<SynapseRoleDefinition> listRoleDefinitions() {
        final Boolean isBuiltIn = null;
        final String scope = null;
        return listRoleDefinitionsWithResponse(isBuiltIn, scope, Context.NONE).getValue();
    }

    /**
     * Get role definition by role definition Id.
     * 
     * @param roleDefinitionId Synapse Built-In Role Definition Id.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorContractException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return role definition by role definition Id along with {@link Response} on successful completion of
     * {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<SynapseRoleDefinition>> getRoleDefinitionByIdWithResponseAsync(String roleDefinitionId) {
        return FluxUtil.withContext(context -> getRoleDefinitionByIdWithResponseAsync(roleDefinitionId, context));
    }

    /**
     * Get role definition by role definition Id.
     * 
     * @param roleDefinitionId Synapse Built-In Role Definition Id.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorContractException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return role definition by role definition Id along with {@link Response} on successful completion of
     * {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<SynapseRoleDefinition>> getRoleDefinitionByIdWithResponseAsync(String roleDefinitionId,
        Context context) {
        final String accept = "application/json, text/json";
        return service.getRoleDefinitionById(this.client.getEndpoint(), this.client.getApiVersion(), roleDefinitionId,
            accept, context);
    }

    /**
     * Get role definition by role definition Id.
     * 
     * @param roleDefinitionId Synapse Built-In Role Definition Id.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorContractException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return role definition by role definition Id on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SynapseRoleDefinition> getRoleDefinitionByIdAsync(String roleDefinitionId) {
        return getRoleDefinitionByIdWithResponseAsync(roleDefinitionId)
            .flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Get role definition by role definition Id.
     * 
     * @param roleDefinitionId Synapse Built-In Role Definition Id.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorContractException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return role definition by role definition Id on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<SynapseRoleDefinition> getRoleDefinitionByIdAsync(String roleDefinitionId, Context context) {
        return getRoleDefinitionByIdWithResponseAsync(roleDefinitionId, context)
            .flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Get role definition by role definition Id.
     * 
     * @param roleDefinitionId Synapse Built-In Role Definition Id.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorContractException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return role definition by role definition Id along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<SynapseRoleDefinition> getRoleDefinitionByIdWithResponse(String roleDefinitionId, Context context) {
        return getRoleDefinitionByIdWithResponseAsync(roleDefinitionId, context).block();
    }

    /**
     * Get role definition by role definition Id.
     * 
     * @param roleDefinitionId Synapse Built-In Role Definition Id.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorContractException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return role definition by role definition Id.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public SynapseRoleDefinition getRoleDefinitionById(String roleDefinitionId) {
        return getRoleDefinitionByIdWithResponse(roleDefinitionId, Context.NONE).getValue();
    }

    /**
     * List rbac scopes.
     * 
     * @throws ErrorContractException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a list of Synapse rbac scopes available along with {@link Response} on successful completion of
     * {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<List<String>>> listScopesWithResponseAsync() {
        return FluxUtil.withContext(context -> listScopesWithResponseAsync(context));
    }

    /**
     * List rbac scopes.
     * 
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorContractException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a list of Synapse rbac scopes available along with {@link Response} on successful completion of
     * {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<List<String>>> listScopesWithResponseAsync(Context context) {
        final String accept = "application/json, text/json";
        return service.listScopes(this.client.getEndpoint(), this.client.getApiVersion(), accept, context);
    }

    /**
     * List rbac scopes.
     * 
     * @throws ErrorContractException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a list of Synapse rbac scopes available on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<List<String>> listScopesAsync() {
        return listScopesWithResponseAsync().flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * List rbac scopes.
     * 
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorContractException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a list of Synapse rbac scopes available on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<List<String>> listScopesAsync(Context context) {
        return listScopesWithResponseAsync(context).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * List rbac scopes.
     * 
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorContractException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a list of Synapse rbac scopes available along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<List<String>> listScopesWithResponse(Context context) {
        return listScopesWithResponseAsync(context).block();
    }

    /**
     * List rbac scopes.
     * 
     * @throws ErrorContractException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a list of Synapse rbac scopes available.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public List<String> listScopes() {
        return listScopesWithResponse(Context.NONE).getValue();
    }
}
