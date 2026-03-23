package com.copeinca.apicopeincaprov.integration.CloudIdentityService.config;

import com.sap.cloud.sdk.cloudplatform.connectivity.AuthenticationType;
import com.sap.cloud.sdk.cloudplatform.connectivity.DefaultHttpDestination;
import com.sap.cloud.sdk.cloudplatform.connectivity.HttpDestination;

import java.net.URI;

public class DestinationIAS {
    public static HttpDestination create() {

        return DefaultHttpDestination.builder("https://avqfsmngl.accounts.ondemand.com")
                .name("IAS_SCIM_BASIC")
                .uri(URI.create("https://avqfsmngl.accounts.ondemand.com"))
                .authenticationType(AuthenticationType.BASIC_AUTHENTICATION)
                .property("username", "d21d0726-5e24-427f-b6e7-6a7c5654f3fe")
                .property("password", "N_Y?rDlY--MhDQ1spPJxyLctVmSxo@DqPnK")
                .build();
    }
}
