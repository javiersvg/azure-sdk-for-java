// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.resourcemanager.networkcloud.models;

import com.azure.core.http.rest.PagedIterable;
import com.azure.core.http.rest.Response;
import com.azure.core.util.Context;

/**
 * Resource collection API of ClusterManagers.
 */
public interface ClusterManagers {
    /**
     * List cluster managers in the subscription.
     * 
     * Get a list of cluster managers in the provided subscription.
     * 
     * @throws com.azure.core.management.exception.ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a list of cluster managers in the provided subscription as paginated response with {@link PagedIterable}.
     */
    PagedIterable<ClusterManager> list();

    /**
     * List cluster managers in the subscription.
     * 
     * Get a list of cluster managers in the provided subscription.
     * 
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws com.azure.core.management.exception.ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a list of cluster managers in the provided subscription as paginated response with {@link PagedIterable}.
     */
    PagedIterable<ClusterManager> list(Context context);

    /**
     * List cluster managers in the resource group.
     * 
     * Get a list of cluster managers in the provided resource group.
     * 
     * @param resourceGroupName The name of the resource group. The name is case insensitive.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws com.azure.core.management.exception.ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a list of cluster managers in the provided resource group as paginated response with
     * {@link PagedIterable}.
     */
    PagedIterable<ClusterManager> listByResourceGroup(String resourceGroupName);

    /**
     * List cluster managers in the resource group.
     * 
     * Get a list of cluster managers in the provided resource group.
     * 
     * @param resourceGroupName The name of the resource group. The name is case insensitive.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws com.azure.core.management.exception.ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a list of cluster managers in the provided resource group as paginated response with
     * {@link PagedIterable}.
     */
    PagedIterable<ClusterManager> listByResourceGroup(String resourceGroupName, Context context);

    /**
     * Retrieve the cluster manager.
     * 
     * Get the properties of the provided cluster manager.
     * 
     * @param resourceGroupName The name of the resource group. The name is case insensitive.
     * @param clusterManagerName The name of the cluster manager.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws com.azure.core.management.exception.ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the properties of the provided cluster manager along with {@link Response}.
     */
    Response<ClusterManager> getByResourceGroupWithResponse(String resourceGroupName, String clusterManagerName,
        Context context);

    /**
     * Retrieve the cluster manager.
     * 
     * Get the properties of the provided cluster manager.
     * 
     * @param resourceGroupName The name of the resource group. The name is case insensitive.
     * @param clusterManagerName The name of the cluster manager.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws com.azure.core.management.exception.ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the properties of the provided cluster manager.
     */
    ClusterManager getByResourceGroup(String resourceGroupName, String clusterManagerName);

    /**
     * Delete the cluster manager.
     * 
     * Delete the provided cluster manager.
     * 
     * @param resourceGroupName The name of the resource group. The name is case insensitive.
     * @param clusterManagerName The name of the cluster manager.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws com.azure.core.management.exception.ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the current status of an async operation.
     */
    OperationStatusResult deleteByResourceGroup(String resourceGroupName, String clusterManagerName);

    /**
     * Delete the cluster manager.
     * 
     * Delete the provided cluster manager.
     * 
     * @param resourceGroupName The name of the resource group. The name is case insensitive.
     * @param clusterManagerName The name of the cluster manager.
     * @param ifMatch The ETag of the transformation. Omit this value to always overwrite the current resource. Specify
     * the last-seen ETag value to prevent accidentally overwriting concurrent changes.
     * @param ifNoneMatch Set to '*' to allow a new record set to be created, but to prevent updating an existing
     * resource. Other values will result in error from server as they are not supported.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws com.azure.core.management.exception.ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the current status of an async operation.
     */
    OperationStatusResult delete(String resourceGroupName, String clusterManagerName, String ifMatch,
        String ifNoneMatch, Context context);

    /**
     * Retrieve the cluster manager.
     * 
     * Get the properties of the provided cluster manager.
     * 
     * @param id the resource ID.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws com.azure.core.management.exception.ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the properties of the provided cluster manager along with {@link Response}.
     */
    ClusterManager getById(String id);

    /**
     * Retrieve the cluster manager.
     * 
     * Get the properties of the provided cluster manager.
     * 
     * @param id the resource ID.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws com.azure.core.management.exception.ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the properties of the provided cluster manager along with {@link Response}.
     */
    Response<ClusterManager> getByIdWithResponse(String id, Context context);

    /**
     * Delete the cluster manager.
     * 
     * Delete the provided cluster manager.
     * 
     * @param id the resource ID.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws com.azure.core.management.exception.ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the current status of an async operation.
     */
    OperationStatusResult deleteById(String id);

    /**
     * Delete the cluster manager.
     * 
     * Delete the provided cluster manager.
     * 
     * @param id the resource ID.
     * @param ifMatch The ETag of the transformation. Omit this value to always overwrite the current resource. Specify
     * the last-seen ETag value to prevent accidentally overwriting concurrent changes.
     * @param ifNoneMatch Set to '*' to allow a new record set to be created, but to prevent updating an existing
     * resource. Other values will result in error from server as they are not supported.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws com.azure.core.management.exception.ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the current status of an async operation.
     */
    OperationStatusResult deleteByIdWithResponse(String id, String ifMatch, String ifNoneMatch, Context context);

    /**
     * Begins definition for a new ClusterManager resource.
     * 
     * @param name resource name.
     * @return the first stage of the new ClusterManager definition.
     */
    ClusterManager.DefinitionStages.Blank define(String name);
}
