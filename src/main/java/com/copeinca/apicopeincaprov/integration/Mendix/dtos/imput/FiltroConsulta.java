package com.copeinca.apicopeincaprov.integration.Mendix.dtos.imput;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FiltroConsulta {
    @JsonProperty("IDProveedor")
    private String IDProveedor;

    @JsonProperty("RUC")
    private String RUC;

    @JsonProperty("RazonSocial")
    private String RazonSocial;

    @JsonProperty("FechaCreacionInicio")
    private String FechaCreacionInicio;

    @JsonProperty("FechaCreacionFin")
    private String FechaCreacionFin;


    @JsonProperty("FechaVigenciaInicio")
    private String FechaVigenciaInicio;

    @JsonProperty("FechaVigenciaFin")
    private String FechaVigenciaFin;

    @JsonProperty("EstadoProveedorSSO")
    private String EstadoProveedorSSO;

    @JsonProperty("EstadoProveedorCalidad Ambiental")
    private String EstadoProveedorCalidadAmbiental;

    @JsonProperty("TipoDocumentoTrabajador")
    private String TipoDocumentoTrabajador;

    @JsonProperty("NumeroDocumentoTrabajador")
    private String NumeroDocumentoTrabajador;

    @JsonProperty("NombreTrabajador")
    private String NombreTrabajador;

    @JsonProperty("EstadoTrabajadorSSO")
    private String EstadoTrabajadorSSO;
}
