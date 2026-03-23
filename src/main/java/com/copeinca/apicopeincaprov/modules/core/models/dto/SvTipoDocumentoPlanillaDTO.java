/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/models/dto/EntityDTO.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.models.dto;

import com.copeinca.apicopeincaprov.commons.models.BaseEntityDTO;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
//@Builder
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class SvTipoDocumentoPlanillaDTO extends BaseEntityDTO {

    //****************************
    // Atributos
    //****************************

    @NotEmpty
    @Size(max = 20)
    private String id;

    @Size(max = 200)
    private String description;

    @Size(max = 50)
    private String ambito;

    @Size(max = 50)
    private String influencia;

    @Size(max = 200)
    private String dependencia;

    @Size(max = 200)
    private String campoPlantillaLf;

    @NotNull
    private Boolean obligatorio;

    @NotNull
    private Boolean vencimiento;

}
