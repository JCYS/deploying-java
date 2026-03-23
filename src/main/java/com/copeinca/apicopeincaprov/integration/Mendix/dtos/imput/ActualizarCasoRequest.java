package com.copeinca.apicopeincaprov.integration.Mendix.dtos.imput;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActualizarCasoRequest {

    @JsonProperty("FiltroConsulta")
    private ActualizarCasoFiltroConsulta filtroConsulta;

}
