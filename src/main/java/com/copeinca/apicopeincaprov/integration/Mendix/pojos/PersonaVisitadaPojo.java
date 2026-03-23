package com.copeinca.apicopeincaprov.integration.Mendix.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class PersonaVisitadaPojo {

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
    private String obligatorio; // En este JSON llega como "1", no boolean

    @JsonProperty("Vencimiento")
    private String vencimiento; // También llega como texto "0"

    @JsonProperty("NumeroCaso")
    private Integer numeroCaso;

    @JsonProperty("Mensaje")
    private String mensaje;
}
