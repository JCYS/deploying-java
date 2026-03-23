package com.copeinca.apicopeincaprov.integration.Mendix.dtos.response;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY,
        getterVisibility = JsonAutoDetect.Visibility.NONE,
        isGetterVisibility = JsonAutoDetect.Visibility.NONE)
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Documento {

    private String ID;
    private String Ambito;

    @JsonProperty("Tipo de documento")
    private String tipoDeDocumento;

    private String Nombre;

    @JsonProperty("Fecha documento")
    private String fechaDocumento;

    @JsonProperty("Fecha vencimiento")
    private String fechaVencimiento;

    private String Revision;
}

