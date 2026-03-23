package com.copeinca.apicopeincaprov.integration.Mendix.dtos.imput;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DocumentosAdjuntos {

    @JsonProperty("docsadjuntos")
    private List<DocumentoAdjunto> docsadjuntos;
}
