package com.copeinca.apicopeincaprov.integration.Mendix.factory;
import com.copeinca.apicopeincaprov.integration.Mendix.config.DestinationMendixProperties;
import com.sap.cloud.sdk.cloudplatform.connectivity.AuthenticationType;
import com.sap.cloud.sdk.cloudplatform.connectivity.DefaultHttpDestination;
import com.sap.cloud.sdk.cloudplatform.connectivity.HttpDestination;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.net.URI;

@Component
@RequiredArgsConstructor
public class DestinationMendixFactory {

    private final DestinationMendixProperties properties;

    public HttpDestination create() {
        return DefaultHttpDestination.builder(properties.getUrl())
                .uri(URI.create(properties.getUrl()))
                .name("laserfiche-basic-auth")
                .authenticationType(AuthenticationType.BASIC_AUTHENTICATION)
                .property("User", properties.getUser())
                .property("Password", properties.getPassword())
                .build();
    }
}