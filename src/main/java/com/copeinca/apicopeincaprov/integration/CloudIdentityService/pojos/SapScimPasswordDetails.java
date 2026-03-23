package com.copeinca.apicopeincaprov.integration.CloudIdentityService.pojos;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SapScimPasswordDetails {
    private String loginTime;
    private Integer failedLoginAttempts;
    private String setTime;
    private String status;
    private String policy;
}
