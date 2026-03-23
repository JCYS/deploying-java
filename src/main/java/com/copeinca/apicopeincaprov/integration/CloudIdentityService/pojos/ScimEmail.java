package com.copeinca.apicopeincaprov.integration.CloudIdentityService.pojos;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ScimEmail {
    private String value;
    private Boolean primary;
    private String type;
}
