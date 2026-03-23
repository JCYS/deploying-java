package com.copeinca.apicopeincaprov.integration.Mendix.dtos.imput;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WsActualizaVisitaTerceraRequest {

    @JsonProperty("Caso")
    private Caso caso;

}
