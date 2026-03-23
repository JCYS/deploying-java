package com.copeinca.apicopeincaprov.integration.LaserFiche.factory;


import com.copeinca.apicopeincaprov.integration.LaserFiche.config.DestinationLaserFicheProperties;
import com.sap.cloud.sdk.cloudplatform.connectivity.DefaultHttpDestination;
import com.sap.cloud.sdk.cloudplatform.connectivity.HttpDestination;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
@RequiredArgsConstructor
public class DestinationLaserFicheFactory {

    private final DestinationLaserFicheProperties properties;

    public HttpDestination create() {

        String credentials = properties.getUser() + ":" + properties.getPassword();
        String encoded = Base64.getEncoder()
                .encodeToString(credentials.getBytes(StandardCharsets.UTF_8));

        return DefaultHttpDestination.builder(properties.getUrl())
                .uri(URI.create(properties.getUrl()))
                .header("Authorization", "Basic " + encoded)
                .build();
    }
}
