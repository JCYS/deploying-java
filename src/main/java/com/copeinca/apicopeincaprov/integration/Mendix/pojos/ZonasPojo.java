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
public class ZonasPojo {

    @JsonProperty("Sede")
    private String sede;

    @JsonProperty("Area")
    private String area;

    @JsonProperty("Zona")
    private String zona;

    @JsonProperty("Ambito")
    private String ambito;

    @JsonProperty("Operativa")
    private String operativa;
}