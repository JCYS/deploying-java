package com.copeinca.apicopeincaprov.integration.Mendix.dtos.imput;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DocumentoAdjunto {

    @JsonProperty("ID")
    private String id;

    @JsonProperty("Ambito")
    private String ambito;

    @JsonProperty("Campo LF Tipo de documento")
    private String campoLFTipoDocumento;

    @JsonProperty("Tipo de documento")
    private String tipoDeDocumento;

    @JsonProperty("Nombre")
    private String nombre;

    @JsonProperty("Fecha documento")
    private String fechaDocumento;

    @JsonProperty("Fecha vencimiento")
    private String fechaVencimiento;

    @JsonProperty("Revision")
    private String revision;
}
