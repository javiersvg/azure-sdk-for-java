// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.resourcemanager.recoveryservices.implementation;

import com.azure.core.annotation.BodyParam;
import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.Get;
import com.azure.core.annotation.HeaderParam;
import com.azure.core.annotation.Headers;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.HostParam;
import com.azure.core.annotation.Patch;
import com.azure.core.annotation.PathParam;
import com.azure.core.annotation.Put;
import com.azure.core.annotation.QueryParam;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceInterface;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.annotation.UnexpectedResponseExceptionType;
import com.azure.core.http.rest.Response;
import com.azure.core.http.rest.RestProxy;
import com.azure.core.management.exception.ManagementException;
import com.azure.core.util.Context;
import com.azure.core.util.FluxUtil;
import com.azure.core.util.logging.ClientLogger;
import com.azure.resourcemanager.recoveryservices.fluent.VaultExtendedInfoesClient;
import com.azure.resourcemanager.recoveryservices.fluent.models.VaultExtendedInfoResourceInner;
import reactor.core.publisher.Mono;

/**
 * An instance of this class provides access to all the operations defined in VaultExtendedInfoesClient.
 */
public final class VaultExtendedInfoesClientImpl implements VaultExtendedInfoesClient {
    /**
     * The proxy service used to perform REST calls.
     */
    private final VaultExtendedInfoesService service;

    /**
     * The service client containing this operation class.
     */
    private final RecoveryServicesManagementClientImpl client;

    /**
     * Initializes an instance of VaultExtendedInfoesClientImpl.
     * 
     * @param client the instance of the service client containing this operation class.
     */
    VaultExtendedInfoesClientImpl(RecoveryServicesManagementClientImpl client) {
        this.service = RestProxy.create(VaultExtendedInfoesService.class, client.getHttpPipeline(),
            client.getSerializerAdapter());
        this.client = client;
    }

    /**
     * The interface defining all the services for RecoveryServicesManagementClientVaultExtendedInfoes to be used by the
     * proxy service to perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "RecoveryServicesManagementClientVaultExtendedInfoes")
    public interface VaultExtendedInfoesService {
        @Headers({ "Content-Type: application/json" })
        @Get("/subscriptions/{subscriptionId}/resourceGroups/{resourceGroupName}/providers/Microsoft.RecoveryServices/vaults/{vaultName}/extendedInformation/vaultExtendedInfo")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(ManagementException.class)
        Mono<Response<VaultExtendedInfoResourceInner>> get(@HostParam("$host") String endpoint,
            @PathParam("subscriptionId") String subscriptionId, @QueryParam("api-version") String apiVersion,
            @PathParam("resourceGroupName") String resourceGroupName, @PathParam("vaultName") String vaultName,
            @HeaderParam("Accept") String accept, Context context);

        @Headers({ "Content-Type: application/json" })
        @Get("/subscriptions/{subscriptionId}/resourceGroups/{resourceGroupName}/providers/Microsoft.RecoveryServices/vaults/{vaultName}/extendedInformation/vaultExtendedInfo")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(ManagementException.class)
        Response<VaultExtendedInfoResourceInner> getSync(@HostParam("$host") String endpoint,
            @PathParam("subscriptionId") String subscriptionId, @QueryParam("api-version") String apiVersion,
            @PathParam("resourceGroupName") String resourceGroupName, @PathParam("vaultName") String vaultName,
            @HeaderParam("Accept") String accept, Context context);

        @Headers({ "Content-Type: application/json" })
        @Put("/subscriptions/{subscriptionId}/resourceGroups/{resourceGroupName}/providers/Microsoft.RecoveryServices/vaults/{vaultName}/extendedInformation/vaultExtendedInfo")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(ManagementException.class)
        Mono<Response<VaultExtendedInfoResourceInner>> createOrUpdate(@HostParam("$host") String endpoint,
            @PathParam("subscriptionId") String subscriptionId,
            @PathParam("resourceGroupName") String resourceGroupName, @PathParam("vaultName") String vaultName,
            @QueryParam("api-version") String apiVersion,
            @BodyParam("application/json") VaultExtendedInfoResourceInner resourceExtendedInfoDetails,
            @HeaderParam("Accept") String accept, Context context);

        @Headers({ "Content-Type: application/json" })
        @Put("/subscriptions/{subscriptionId}/resourceGroups/{resourceGroupName}/providers/Microsoft.RecoveryServices/vaults/{vaultName}/extendedInformation/vaultExtendedInfo")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(ManagementException.class)
        Response<VaultExtendedInfoResourceInner> createOrUpdateSync(@HostParam("$host") String endpoint,
            @PathParam("subscriptionId") String subscriptionId,
            @PathParam("resourceGroupName") String resourceGroupName, @PathParam("vaultName") String vaultName,
            @QueryParam("api-version") String apiVersion,
            @BodyParam("application/json") VaultExtendedInfoResourceInner resourceExtendedInfoDetails,
            @HeaderParam("Accept") String accept, Context context);

        @Headers({ "Content-Type: application/json" })
        @Patch("/subscriptions/{subscriptionId}/resourceGroups/{resourceGroupName}/providers/Microsoft.RecoveryServices/vaults/{vaultName}/extendedInformation/vaultExtendedInfo")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(ManagementException.class)
        Mono<Response<VaultExtendedInfoResourceInner>> update(@HostParam("$host") String endpoint,
            @PathParam("subscriptionId") String subscriptionId,
            @PathParam("resourceGroupName") String resourceGroupName, @PathParam("vaultName") String vaultName,
            @QueryParam("api-version") String apiVersion,
            @BodyParam("application/json") VaultExtendedInfoResourceInner resourceExtendedInfoDetails,
            @HeaderParam("Accept") String accept, Context context);

        @Headers({ "Content-Type: application/json" })
        @Patch("/subscriptions/{subscriptionId}/resourceGroups/{resourceGroupName}/providers/Microsoft.RecoveryServices/vaults/{vaultName}/extendedInformation/vaultExtendedInfo")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(ManagementException.class)
        Response<VaultExtendedInfoResourceInner> updateSync(@HostParam("$host") String endpoint,
            @PathParam("subscriptionId") String subscriptionId,
            @PathParam("resourceGroupName") String resourceGroupName, @PathParam("vaultName") String vaultName,
            @QueryParam("api-version") String apiVersion,
            @BodyParam("application/json") VaultExtendedInfoResourceInner resourceExtendedInfoDetails,
            @HeaderParam("Accept") String accept, Context context);
    }

    /**
     * Get the vault extended info.
     * 
     * @param resourceGroupName The name of the resource group. The name is case insensitive.
     * @param vaultName The name of the recovery services vault.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the vault extended info along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<Response<VaultExtendedInfoResourceInner>> getWithResponseAsync(String resourceGroupName,
        String vaultName) {
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
        if (vaultName == null) {
            return Mono.error(new IllegalArgumentException("Parameter vaultName is required and cannot be null."));
        }
        final String accept = "application/json";
        return FluxUtil
            .withContext(context -> service.get(this.client.getEndpoint(), this.client.getSubscriptionId(),
                this.client.getApiVersion(), resourceGroupName, vaultName, accept, context))
            .contextWrite(context -> context.putAll(FluxUtil.toReactorContext(this.client.getContext()).readOnly()));
    }

    /**
     * Get the vault extended info.
     * 
     * @param resourceGroupName The name of the resource group. The name is case insensitive.
     * @param vaultName The name of the recovery services vault.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the vault extended info on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<VaultExtendedInfoResourceInner> getAsync(String resourceGroupName, String vaultName) {
        return getWithResponseAsync(resourceGroupName, vaultName).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Get the vault extended info.
     * 
     * @param resourceGroupName The name of the resource group. The name is case insensitive.
     * @param vaultName The name of the recovery services vault.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the vault extended info along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<VaultExtendedInfoResourceInner> getWithResponse(String resourceGroupName, String vaultName,
        Context context) {
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
        if (vaultName == null) {
            throw LOGGER.atError()
                .log(new IllegalArgumentException("Parameter vaultName is required and cannot be null."));
        }
        final String accept = "application/json";
        return service.getSync(this.client.getEndpoint(), this.client.getSubscriptionId(), this.client.getApiVersion(),
            resourceGroupName, vaultName, accept, context);
    }

    /**
     * Get the vault extended info.
     * 
     * @param resourceGroupName The name of the resource group. The name is case insensitive.
     * @param vaultName The name of the recovery services vault.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the vault extended info.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public VaultExtendedInfoResourceInner get(String resourceGroupName, String vaultName) {
        return getWithResponse(resourceGroupName, vaultName, Context.NONE).getValue();
    }

    /**
     * Create vault extended info.
     * 
     * @param resourceGroupName The name of the resource group. The name is case insensitive.
     * @param vaultName The name of the recovery services vault.
     * @param resourceExtendedInfoDetails Details of ResourceExtendedInfo.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return vault extended information along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<Response<VaultExtendedInfoResourceInner>> createOrUpdateWithResponseAsync(String resourceGroupName,
        String vaultName, VaultExtendedInfoResourceInner resourceExtendedInfoDetails) {
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
        if (vaultName == null) {
            return Mono.error(new IllegalArgumentException("Parameter vaultName is required and cannot be null."));
        }
        if (resourceExtendedInfoDetails == null) {
            return Mono.error(
                new IllegalArgumentException("Parameter resourceExtendedInfoDetails is required and cannot be null."));
        } else {
            resourceExtendedInfoDetails.validate();
        }
        final String accept = "application/json";
        return FluxUtil
            .withContext(context -> service.createOrUpdate(this.client.getEndpoint(), this.client.getSubscriptionId(),
                resourceGroupName, vaultName, this.client.getApiVersion(), resourceExtendedInfoDetails, accept,
                context))
            .contextWrite(context -> context.putAll(FluxUtil.toReactorContext(this.client.getContext()).readOnly()));
    }

    /**
     * Create vault extended info.
     * 
     * @param resourceGroupName The name of the resource group. The name is case insensitive.
     * @param vaultName The name of the recovery services vault.
     * @param resourceExtendedInfoDetails Details of ResourceExtendedInfo.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return vault extended information on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<VaultExtendedInfoResourceInner> createOrUpdateAsync(String resourceGroupName, String vaultName,
        VaultExtendedInfoResourceInner resourceExtendedInfoDetails) {
        return createOrUpdateWithResponseAsync(resourceGroupName, vaultName, resourceExtendedInfoDetails)
            .flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Create vault extended info.
     * 
     * @param resourceGroupName The name of the resource group. The name is case insensitive.
     * @param vaultName The name of the recovery services vault.
     * @param resourceExtendedInfoDetails Details of ResourceExtendedInfo.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return vault extended information along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<VaultExtendedInfoResourceInner> createOrUpdateWithResponse(String resourceGroupName,
        String vaultName, VaultExtendedInfoResourceInner resourceExtendedInfoDetails, Context context) {
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
        if (vaultName == null) {
            throw LOGGER.atError()
                .log(new IllegalArgumentException("Parameter vaultName is required and cannot be null."));
        }
        if (resourceExtendedInfoDetails == null) {
            throw LOGGER.atError()
                .log(new IllegalArgumentException(
                    "Parameter resourceExtendedInfoDetails is required and cannot be null."));
        } else {
            resourceExtendedInfoDetails.validate();
        }
        final String accept = "application/json";
        return service.createOrUpdateSync(this.client.getEndpoint(), this.client.getSubscriptionId(), resourceGroupName,
            vaultName, this.client.getApiVersion(), resourceExtendedInfoDetails, accept, context);
    }

    /**
     * Create vault extended info.
     * 
     * @param resourceGroupName The name of the resource group. The name is case insensitive.
     * @param vaultName The name of the recovery services vault.
     * @param resourceExtendedInfoDetails Details of ResourceExtendedInfo.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return vault extended information.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public VaultExtendedInfoResourceInner createOrUpdate(String resourceGroupName, String vaultName,
        VaultExtendedInfoResourceInner resourceExtendedInfoDetails) {
        return createOrUpdateWithResponse(resourceGroupName, vaultName, resourceExtendedInfoDetails, Context.NONE)
            .getValue();
    }

    /**
     * Update vault extended info.
     * 
     * @param resourceGroupName The name of the resource group. The name is case insensitive.
     * @param vaultName The name of the recovery services vault.
     * @param resourceExtendedInfoDetails Details of ResourceExtendedInfo.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return vault extended information along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<Response<VaultExtendedInfoResourceInner>> updateWithResponseAsync(String resourceGroupName,
        String vaultName, VaultExtendedInfoResourceInner resourceExtendedInfoDetails) {
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
        if (vaultName == null) {
            return Mono.error(new IllegalArgumentException("Parameter vaultName is required and cannot be null."));
        }
        if (resourceExtendedInfoDetails == null) {
            return Mono.error(
                new IllegalArgumentException("Parameter resourceExtendedInfoDetails is required and cannot be null."));
        } else {
            resourceExtendedInfoDetails.validate();
        }
        final String accept = "application/json";
        return FluxUtil
            .withContext(
                context -> service.update(this.client.getEndpoint(), this.client.getSubscriptionId(), resourceGroupName,
                    vaultName, this.client.getApiVersion(), resourceExtendedInfoDetails, accept, context))
            .contextWrite(context -> context.putAll(FluxUtil.toReactorContext(this.client.getContext()).readOnly()));
    }

    /**
     * Update vault extended info.
     * 
     * @param resourceGroupName The name of the resource group. The name is case insensitive.
     * @param vaultName The name of the recovery services vault.
     * @param resourceExtendedInfoDetails Details of ResourceExtendedInfo.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return vault extended information on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    private Mono<VaultExtendedInfoResourceInner> updateAsync(String resourceGroupName, String vaultName,
        VaultExtendedInfoResourceInner resourceExtendedInfoDetails) {
        return updateWithResponseAsync(resourceGroupName, vaultName, resourceExtendedInfoDetails)
            .flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Update vault extended info.
     * 
     * @param resourceGroupName The name of the resource group. The name is case insensitive.
     * @param vaultName The name of the recovery services vault.
     * @param resourceExtendedInfoDetails Details of ResourceExtendedInfo.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return vault extended information along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<VaultExtendedInfoResourceInner> updateWithResponse(String resourceGroupName, String vaultName,
        VaultExtendedInfoResourceInner resourceExtendedInfoDetails, Context context) {
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
        if (vaultName == null) {
            throw LOGGER.atError()
                .log(new IllegalArgumentException("Parameter vaultName is required and cannot be null."));
        }
        if (resourceExtendedInfoDetails == null) {
            throw LOGGER.atError()
                .log(new IllegalArgumentException(
                    "Parameter resourceExtendedInfoDetails is required and cannot be null."));
        } else {
            resourceExtendedInfoDetails.validate();
        }
        final String accept = "application/json";
        return service.updateSync(this.client.getEndpoint(), this.client.getSubscriptionId(), resourceGroupName,
            vaultName, this.client.getApiVersion(), resourceExtendedInfoDetails, accept, context);
    }

    /**
     * Update vault extended info.
     * 
     * @param resourceGroupName The name of the resource group. The name is case insensitive.
     * @param vaultName The name of the recovery services vault.
     * @param resourceExtendedInfoDetails Details of ResourceExtendedInfo.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return vault extended information.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public VaultExtendedInfoResourceInner update(String resourceGroupName, String vaultName,
        VaultExtendedInfoResourceInner resourceExtendedInfoDetails) {
        return updateWithResponse(resourceGroupName, vaultName, resourceExtendedInfoDetails, Context.NONE).getValue();
    }

    private static final ClientLogger LOGGER = new ClientLogger(VaultExtendedInfoesClientImpl.class);
}
