package com.copeinca.apicopeincaprov.integration.CloudIdentityService.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ScimListResponse<T> {

    private List<String> schemas;

    private Integer totalResults;
    private Integer itemsPerPage;
    private Integer startIndex;

    @JsonProperty("Resources")
    private List<T> resources;
}
