package com.copeinca.apicopeincaprov.integration.Mendix.dtos.imput;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActualizarCasoFiltroConsulta {

    @JsonProperty("NumeroCaso")
    private String numeroCaso;

    @JsonProperty("EstadoCaso")
    private String estadoCaso;

    @JsonProperty("Observacion")
    private String observacion;

    @JsonProperty("Usuario")
    private String usuario;

    /**
     * Formato esperado: dd/MM/yyyy HH:mm:ss
     */
    @JsonProperty("FechaAccion")
    private String fechaAccion;

}