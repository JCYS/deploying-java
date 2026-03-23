package com.copeinca.apicopeincaprov.integration.Mendix.dtos.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActualizarCasoODataItem {

    @JsonProperty("Sede")
    private String sede;

    @JsonProperty("Usuario")
    private String usuario;

    @JsonProperty("NombreUsuario")
    private String nombreUsuario;

    @JsonProperty("Codigo")
    private String codigo;

    @JsonProperty("Descripcion")
    private String descripcion;

    @JsonProperty("TipoDocumento")
    private String tipoDocumento;

    @JsonProperty("Ambito")
    private String ambito;

    @JsonProperty("Influencia")
    private String influencia;

    @JsonProperty("Dependencia")
    private String dependencia;

    @JsonProperty("CampoPlantillaLF")
    private String campoPlantillaLF;

    @JsonProperty("Obligatorio")
    private Boolean obligatorio;

    @JsonProperty("Vencimiento")
    private Boolean vencimiento;

    @JsonProperty("NumeroCaso")
    private String numeroCaso;

    @JsonProperty("Mensaje")
    private String mensaje;
}
