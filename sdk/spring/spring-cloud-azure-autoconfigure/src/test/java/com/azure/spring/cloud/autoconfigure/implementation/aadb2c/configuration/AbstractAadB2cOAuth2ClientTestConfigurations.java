// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
package com.azure.spring.cloud.autoconfigure.implementation.aadb2c.configuration;

import com.azure.spring.cloud.autoconfigure.implementation.aadb2c.AadB2cConstants;
import com.azure.spring.cloud.autoconfigure.implementation.aadb2c.configuration.properties.AadB2cProperties;
import com.azure.spring.cloud.autoconfigure.implementation.aadb2c.configuration.properties.AuthorizationClientProperties;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.runner.WebApplicationContextRunner;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;

import java.util.Map;

abstract class AbstractAadB2cOAuth2ClientTestConfigurations {

    abstract WebApplicationContextRunner getDefaultContextRunner();

    protected String[] getAuthorizationClientPropertyValues() {
        return new String[] {
            "spring.cloud.azure.active-directory.b2c.enabled=true",
            String.format("%s.%s.scopes=%s", AadB2cConstants.AUTHORIZATION_CLIENTS,
                AadB2cConstants.CLIENT_CREDENTIAL_NAME, AadB2cConstants.TEST_CLIENT_CREDENTIAL_SCOPES),
            String.format("%s.%s.authorization-grant-type=%s", AadB2cConstants.AUTHORIZATION_CLIENTS,
                AadB2cConstants.CLIENT_CREDENTIAL_NAME, AadB2cConstants.TEST_CLIENT_CREDENTIAL_GRANT_TYPE),
        };
    }

    @Test
    void testClientCredentialProperties() {
        getDefaultContextRunner()
            .withPropertyValues(getAuthorizationClientPropertyValues())
            .run(c -> {
                final AadB2cProperties properties = c.getBean(AadB2cProperties.class);
                Assertions.assertNotNull(properties);
                Map<String, AuthorizationClientProperties> authorizationClients = properties.getAuthorizationClients();
                Assertions.assertFalse(authorizationClients.isEmpty());
                for (String clientName: authorizationClients.keySet()) {
                    Assertions.assertEquals(AadB2cConstants.CLIENT_CREDENTIAL_NAME, clientName);
                    Assertions.assertEquals(AadB2cConstants.TEST_CLIENT_CREDENTIAL_SCOPES,
                        authorizationClients.get(clientName).getScopes().get(0));
                    Assertions.assertEquals(AuthorizationGrantType.CLIENT_CREDENTIALS,
                        authorizationClients.get(clientName).getAuthorizationGrantType());
                }
            });
    }

    @Test
    void testClientRelatedBeans() {
        getDefaultContextRunner()
            .withPropertyValues(getAuthorizationClientPropertyValues())
            .run(c -> {
                final AadB2cOAuth2ClientConfiguration config = c.getBean(AadB2cOAuth2ClientConfiguration.class);
                final ClientRegistrationRepository clientRepo = c.getBean(ClientRegistrationRepository.class);
                final OAuth2AuthorizedClientService clientService = c.getBean(OAuth2AuthorizedClientService.class);
                final OAuth2AuthorizedClientRepository authorizedClientRepo =
                    c.getBean(OAuth2AuthorizedClientRepository.class);
                final OAuth2AuthorizedClientManager clientManager = c.getBean(OAuth2AuthorizedClientManager.class);

                Assertions.assertNotNull(config);
                Assertions.assertNotNull(clientRepo);
                Assertions.assertNotNull(clientService);
                Assertions.assertNotNull(authorizedClientRepo);
                Assertions.assertNotNull(clientManager);
            });
    }
}
