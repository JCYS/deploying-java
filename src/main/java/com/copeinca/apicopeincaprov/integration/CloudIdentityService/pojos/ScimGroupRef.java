package com.copeinca.apicopeincaprov.integration.CloudIdentityService.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ScimGroupRef {

    private String value;
    private String display;

    @JsonProperty("$ref")
    private String ref;
}
