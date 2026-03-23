package com.copeinca.apicopeincaprov.integration.CloudIdentityService.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SapScimUserExtension {
    private List<SapScimVerifiedEmail> emails;

    private String loginTime;
    private String userUuid;
    private Boolean mailVerified;
    private String userId;
    private String status;

    private SapScimPasswordDetails passwordDetails;
}