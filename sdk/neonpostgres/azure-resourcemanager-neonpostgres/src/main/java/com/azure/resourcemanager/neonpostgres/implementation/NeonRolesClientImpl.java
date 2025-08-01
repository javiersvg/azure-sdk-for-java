// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.azure.resourcemanager.neonpostgres.implementation;

import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.Get;
import com.azure.core.annotation.HeaderParam;
import com.azure.core.annotation.Headers;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.HostParam;
import com.azure.core.annotation.PathParam;
import com.azure.core.annotation.QueryParam;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceInterface;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.annotation.UnexpectedResponseExceptionType;
import com.azure.core.http.rest.PagedFlux;
import com.azure.core.http.rest.PagedIterable;
import com.azure.core.http.rest.PagedResponse;
import com.azure.core.http.rest.PagedResponseBase;
import com.azure.core.http.rest.Response;
import com.azure.core.http.rest.RestProxy;
import com.azure.core.management.exception.ManagementException;
import com.azure.core.util.Context;
import com.azure.core.util.FluxUtil;
import com.azure.core.util.logging.ClientLogger;
import com.azure.resourcemanager.neonpostgres.fluent.NeonRolesClient;
import com.azure.resourcemanager.neonpostgres.fluent.models.NeonRoleInner;
import com.azure.resourcemanager.neonpostgres.implementation.models.NeonRoleListResult;
import reactor.core.publisher.Mono;

/**
 * An instance of this class provides access to all the operations defined in NeonRolesClient.
 */
public final class NeonRolesClientImpl implements NeonRolesClient {
    /**
     * The proxy service used to perform REST calls.
     */
    private final NeonRolesService service;

    /**
     * The service client containing this operation class.
     */
    private final NeonPostgresManagementClientImpl client;

    /**
     * Initializes an instance of NeonRolesClientImpl.
     * 
     * @param client the instance of the service client containing this operation class.
     */
    NeonRolesClientImpl(NeonPostgresManagementClientImpl client) {
        this.service
            = RestProxy.create(NeonRolesService.class, client.getHttpPipeline(), client.getSerializerAdapter());
        this.client = client;
    }

    /**
     * The interface defining all the services for NeonPostgresManagementClientNeonRoles to be used by the proxy service
     * to perform REST calls.
     */
    @Host("{endpoint}")
    @ServiceInterface(name = "NeonPostgresManagementClientNeonRoles")
    public interface NeonRolesService {
        @Headers({ "Content-Type: application/json" })
        @Get("/subscriptions/{subscriptionId}/resourceGroups/{resourceGroupName}/providers/Neon.Postgres/organizations/{organizationName}/projects/{projectName}/branches/{branchName}/neonRoles")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(ManagementException.class)
        Mono<Response<NeonRoleListResult>> list(@HostParam("endpoint") String endpoint,
            @QueryParam("api-version") String apiVersion, @PathParam("subscriptionId") String subscriptionId,
            @PathParam("resourceGroupName") String resourceGroupName,
            @PathParam("organizationName") String organizationName, @PathParam("projectName") String projectName,
            @PathParam("branchName") String branchName, @HeaderParam("Accept") String accept, Context context);

        @Headers({ "Content-Type: application/json" })
        @Get("/subscriptions/{subscriptionId}/resourceGroups/{resourceGroupName}/providers/Neon.Postgres/organizations/{organizationName}/projects/{projectName}/branches/{branchName}/neonRoles")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(ManagementException.class)
        Response<NeonRoleListResult> listSync(@HostParam("endpoint") String endpoint,
            @QueryParam("api-version") String apiVersion, @PathParam("subscriptionId") String subscriptionId,
            @PathParam("resourceGroupName") String resourceGroupName,
            @PathParam("organizationName") String organizationName, @PathParam("projectName") String projectName,
            @PathParam("branchName") String branchName, @HeaderParam("Accept") String accept, Context context);

        @Headers({ "Content-Type: application/json" })
        @Get("{nextLink}")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(ManagementException.class)
        Mono<Response<NeonRoleListResult>> listNext(@PathParam(value = "nextLink", encoded = true) String nextLink,
            @HostParam("endpoint") String endpoint, @HeaderParam("Accept") String accept, Context context);

        @Headers({ "Content-Type: application/json" })
        @Get("{nextLink}")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(ManagementException.class)
        Response<NeonRoleListResult> listNextSync(@PathParam(value = "nextLink", encoded = true) String nextLink,
            @HostParam("endpoint") String endpoint, @HeaderParam("Accept") String accept, Context context);
    }

    /**
     * List NeonRole resources by Branch.
     * 
     * @param resourceGroupName The name of the resource group. The name is case insensitive.
     * @param organizationName Name of the Neon Organizations resource.
     * @param projectName The name of the Project.
     * @param branchName The name of the Branch.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response of a NeonRole list operation along with {@link PagedResponse} on successful completion of
     * {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<PagedResponse<NeonRoleInner>> listSinglePageAsync(String resourceGroupName, String organizationName,
        String projectName, String branchName) {
        if (this.client.getEndpoint() == null) {
            return Mono.error(
                new IllegalArgumentException("Parameter this.client.getEndpoint() is required and cannot be null."));
        }
        if (this.client.getSubscriptionId() == null) {
            return Mono.error(new IllegalArgumentException(
                "Parameter this.client.getSubscriptionId() is required and cannot be null."));
        }
        if (resourceGroupName == null) {
            return Mono
                .error(new IllegalArgumentException("Parameter resourceGroupName is required and cannot be null."));
        }
        if (organizationName == null) {
            return Mono
                .error(new IllegalArgumentException("Parameter organizationName is required and cannot be null."));
        }
        if (projectName == null) {
            return Mono.error(new IllegalArgumentException("Parameter projectName is required and cannot be null."));
        }
        if (branchName == null) {
            return Mono.error(new IllegalArgumentException("Parameter branchName is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil
            .withContext(context -> service.list(this.client.getEndpoint(), this.client.getApiVersion(),
                this.client.getSubscriptionId(), resourceGroupName, organizationName, projectName, branchName, accept,
                context))
            .<PagedResponse<NeonRoleInner>>map(res -> new PagedResponseBase<>(res.getRequest(), res.getStatusCode(),
                res.getHeaders(), res.getValue().value(), res.getValue().nextLink(), null))
            .contextWrite(context -> context.putAll(FluxUtil.toReactorContext(this.client.getContext()).readOnly()));
    }

    /**
     * List NeonRole resources by Branch.
     * 
     * @param resourceGroupName The name of the resource group. The name is case insensitive.
     * @param organizationName Name of the Neon Organizations resource.
     * @param projectName The name of the Project.
     * @param branchName The name of the Branch.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response of a NeonRole list operation as paginated response with {@link PagedFlux}.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    private PagedFlux<NeonRoleInner> listAsync(String resourceGroupName, String organizationName, String projectName,
        String branchName) {
        return new PagedFlux<>(() -> listSinglePageAsync(resourceGroupName, organizationName, projectName, branchName),
            nextLink -> listNextSinglePageAsync(nextLink));
    }

    /**
     * List NeonRole resources by Branch.
     * 
     * @param resourceGroupName The name of the resource group. The name is case insensitive.
     * @param organizationName Name of the Neon Organizations resource.
     * @param projectName The name of the Project.
     * @param branchName The name of the Branch.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response of a NeonRole list operation along with {@link PagedResponse}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private PagedResponse<NeonRoleInner> listSinglePage(String resourceGroupName, String organizationName,
        String projectName, String branchName) {
        if (this.client.getEndpoint() == null) {
            throw LOGGER.atError()
                .log(new IllegalArgumentException(
                    "Parameter this.client.getEndpoint() is required and cannot be null."));
        }
        if (this.client.getSubscriptionId() == null) {
            throw LOGGER.atError()
                .log(new IllegalArgumentException(
                    "Parameter this.client.getSubscriptionId() is required and cannot be null."));
        }
        if (resourceGroupName == null) {
            throw LOGGER.atError()
                .log(new IllegalArgumentException("Parameter resourceGroupName is required and cannot be null."));
        }
        if (organizationName == null) {
            throw LOGGER.atError()
                .log(new IllegalArgumentException("Parameter organizationName is required and cannot be null."));
        }
        if (projectName == null) {
            throw LOGGER.atError()
                .log(new IllegalArgumentException("Parameter projectName is required and cannot be null."));
        }
        if (branchName == null) {
            throw LOGGER.atError()
                .log(new IllegalArgumentException("Parameter branchName is required and cannot be null."));
        }
        final String accept = "application/json";
        Response<NeonRoleListResult> res
            = service.listSync(this.client.getEndpoint(), this.client.getApiVersion(), this.client.getSubscriptionId(),
                resourceGroupName, organizationName, projectName, branchName, accept, Context.NONE);
        return new PagedResponseBase<>(res.getRequest(), res.getStatusCode(), res.getHeaders(), res.getValue().value(),
            res.getValue().nextLink(), null);
    }

    /**
     * List NeonRole resources by Branch.
     * 
     * @param resourceGroupName The name of the resource group. The name is case insensitive.
     * @param organizationName Name of the Neon Organizations resource.
     * @param projectName The name of the Project.
     * @param branchName The name of the Branch.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response of a NeonRole list operation along with {@link PagedResponse}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private PagedResponse<NeonRoleInner> listSinglePage(String resourceGroupName, String organizationName,
        String projectName, String branchName, Context context) {
        if (this.client.getEndpoint() == null) {
            throw LOGGER.atError()
                .log(new IllegalArgumentException(
                    "Parameter this.client.getEndpoint() is required and cannot be null."));
        }
        if (this.client.getSubscriptionId() == null) {
            throw LOGGER.atError()
                .log(new IllegalArgumentException(
                    "Parameter this.client.getSubscriptionId() is required and cannot be null."));
        }
        if (resourceGroupName == null) {
            throw LOGGER.atError()
                .log(new IllegalArgumentException("Parameter resourceGroupName is required and cannot be null."));
        }
        if (organizationName == null) {
            throw LOGGER.atError()
                .log(new IllegalArgumentException("Parameter organizationName is required and cannot be null."));
        }
        if (projectName == null) {
            throw LOGGER.atError()
                .log(new IllegalArgumentException("Parameter projectName is required and cannot be null."));
        }
        if (branchName == null) {
            throw LOGGER.atError()
                .log(new IllegalArgumentException("Parameter branchName is required and cannot be null."));
        }
        final String accept = "application/json";
        Response<NeonRoleListResult> res
            = service.listSync(this.client.getEndpoint(), this.client.getApiVersion(), this.client.getSubscriptionId(),
                resourceGroupName, organizationName, projectName, branchName, accept, context);
        return new PagedResponseBase<>(res.getRequest(), res.getStatusCode(), res.getHeaders(), res.getValue().value(),
            res.getValue().nextLink(), null);
    }

    /**
     * List NeonRole resources by Branch.
     * 
     * @param resourceGroupName The name of the resource group. The name is case insensitive.
     * @param organizationName Name of the Neon Organizations resource.
     * @param projectName The name of the Project.
     * @param branchName The name of the Branch.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response of a NeonRole list operation as paginated response with {@link PagedIterable}.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<NeonRoleInner> list(String resourceGroupName, String organizationName, String projectName,
        String branchName) {
        return new PagedIterable<>(() -> listSinglePage(resourceGroupName, organizationName, projectName, branchName),
            nextLink -> listNextSinglePage(nextLink));
    }

    /**
     * List NeonRole resources by Branch.
     * 
     * @param resourceGroupName The name of the resource group. The name is case insensitive.
     * @param organizationName Name of the Neon Organizations resource.
     * @param projectName The name of the Project.
     * @param branchName The name of the Branch.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response of a NeonRole list operation as paginated response with {@link PagedIterable}.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedIterable<NeonRoleInner> list(String resourceGroupName, String organizationName, String projectName,
        String branchName, Context context) {
        return new PagedIterable<>(
            () -> listSinglePage(resourceGroupName, organizationName, projectName, branchName, context),
            nextLink -> listNextSinglePage(nextLink, context));
    }

    /**
     * Get the next page of items.
     * 
     * @param nextLink The URL to get the next list of items.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response of a NeonRole list operation along with {@link PagedResponse} on successful completion of
     * {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<PagedResponse<NeonRoleInner>> listNextSinglePageAsync(String nextLink) {
        if (nextLink == null) {
            return Mono.error(new IllegalArgumentException("Parameter nextLink is required and cannot be null."));
        }
        if (this.client.getEndpoint() == null) {
            return Mono.error(
                new IllegalArgumentException("Parameter this.client.getEndpoint() is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.listNext(nextLink, this.client.getEndpoint(), accept, context))
            .<PagedResponse<NeonRoleInner>>map(res -> new PagedResponseBase<>(res.getRequest(), res.getStatusCode(),
                res.getHeaders(), res.getValue().value(), res.getValue().nextLink(), null))
            .contextWrite(context -> context.putAll(FluxUtil.toReactorContext(this.client.getContext()).readOnly()));
    }

    /**
     * Get the next page of items.
     * 
     * @param nextLink The URL to get the next list of items.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response of a NeonRole list operation along with {@link PagedResponse}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private PagedResponse<NeonRoleInner> listNextSinglePage(String nextLink) {
        if (nextLink == null) {
            throw LOGGER.atError()
                .log(new IllegalArgumentException("Parameter nextLink is required and cannot be null."));
        }
        if (this.client.getEndpoint() == null) {
            throw LOGGER.atError()
                .log(new IllegalArgumentException(
                    "Parameter this.client.getEndpoint() is required and cannot be null."));
        }
        final String accept = "application/json";
        Response<NeonRoleListResult> res
            = service.listNextSync(nextLink, this.client.getEndpoint(), accept, Context.NONE);
        return new PagedResponseBase<>(res.getRequest(), res.getStatusCode(), res.getHeaders(), res.getValue().value(),
            res.getValue().nextLink(), null);
    }

    /**
     * Get the next page of items.
     * 
     * @param nextLink The URL to get the next list of items.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response of a NeonRole list operation along with {@link PagedResponse}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private PagedResponse<NeonRoleInner> listNextSinglePage(String nextLink, Context context) {
        if (nextLink == null) {
            throw LOGGER.atError()
                .log(new IllegalArgumentException("Parameter nextLink is required and cannot be null."));
        }
        if (this.client.getEndpoint() == null) {
            throw LOGGER.atError()
                .log(new IllegalArgumentException(
                    "Parameter this.client.getEndpoint() is required and cannot be null."));
        }
        final String accept = "application/json";
        Response<NeonRoleListResult> res = service.listNextSync(nextLink, this.client.getEndpoint(), accept, context);
        return new PagedResponseBase<>(res.getRequest(), res.getStatusCode(), res.getHeaders(), res.getValue().value(),
            res.getValue().nextLink(), null);
    }

    private static final ClientLogger LOGGER = new ClientLogger(NeonRolesClientImpl.class);
}
