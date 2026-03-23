package com.copeinca.apicopeincaprov.integration.Mendix.config;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "mendix.destination")
public class DestinationMendixProperties {


    private String url;
    private String user;
    private String password;
}
