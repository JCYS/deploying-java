package com.copeinca.apicopeincaprov.integration.CloudIdentityService.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SapScimVerifiedEmail {
    private Boolean verified;
    private String value;
    private String verifiedTime;
    private Boolean primary;
}
