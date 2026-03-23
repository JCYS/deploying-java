package com.copeinca.apicopeincaprov.integration.Mendix.dtos.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class GrabaVisitaTerceraResponseDTO {

    @JsonProperty("Codigo")
    private String codigo;

    @JsonProperty("Mensaje")
    private String mensaje;

    @JsonProperty("NumeroCaso")
    private String numeroCaso;
}
