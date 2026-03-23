package com.copeinca.apicopeincaprov.integration.CloudIdentityService.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ScimUserName {
    private String familyName;
    private String givenName;
    private String middleName;
    private String formatted;
}