package com.copeinca.apicopeincaprov.integration.Mendix.dtos.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActualizarCasoODataResponse {

    @JsonProperty("@context")
    private String context;

    @JsonProperty("value")
    private List<ActualizarCasoODataItem> value;
}
