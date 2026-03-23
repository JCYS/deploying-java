package com.copeinca.apicopeincaprov.integration.Mendix.dtos.imput;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Caso {

    @JsonProperty("NroCaso")
    private String nroCaso;

    @JsonProperty("Estado de Caso")
    private String estadoDeCaso;

    @JsonProperty("Ambito")
    private String ambito;

    @JsonProperty("Revision")
    private String revision;

    @JsonProperty("Respuesta del usuario")
    private String respuestaDelUsuario;

    @JsonProperty("Usuario")
    private String usuario;

    @JsonProperty("Observaciones")
    private String observaciones;

    @JsonProperty("Fecha accion")
    private String fechaAccion;

    @JsonProperty("Tipo Documento Trabajador")
    private String tipoDocumentoTrabajador;

    @JsonProperty("Numero Documento Trabajador")
    private String numeroDocumentoTrabajador;

    @JsonProperty("Documentos adjuntos")
    private DocumentosAdjuntos documentosAdjuntos;
}

