package com.copeinca.apicopeincaprov.integration.LaserFiche.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "laserfiche.destination")
public class DestinationLaserFicheProperties {

    private String url;
    private String user;
    private String password;
}
