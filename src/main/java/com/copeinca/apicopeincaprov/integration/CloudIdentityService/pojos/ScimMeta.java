package com.copeinca.apicopeincaprov.integration.CloudIdentityService.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ScimMeta {

    private String created;
    private String lastModified;
    private String location;
    private String resourceType;
    private String version;

    @JsonProperty("groups.cnt")
    private Integer groupsCnt;
}