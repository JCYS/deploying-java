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
public class ActualizarCasoResponse {

    @JsonProperty("value")
    private List<ActualizarCasoResponseItem> value;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ActualizarCasoResponseItem {

        @JsonProperty("Codigo")
        private String codigo;

        @JsonProperty("NumeroCaso")
        private String numeroCaso;

        @JsonProperty("Mensaje")
        private String mensaje;
    }
}