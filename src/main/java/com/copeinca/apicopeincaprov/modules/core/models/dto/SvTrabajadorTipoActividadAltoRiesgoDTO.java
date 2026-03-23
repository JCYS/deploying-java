/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/models/dto/EntityDTO.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.models.dto;

import com.copeinca.apicopeincaprov.commons.models.BaseEntityDTO;
import jakarta.validation.constraints.NotNull;
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
public class SvTrabajadorTipoActividadAltoRiesgoDTO extends BaseEntityDTO {

    //****************************
    // Atributos
    //****************************

    private String id;

    private String trabajadorId;

    private String tipoActividadAltoRiesgoCode;

    private String  description;

    @NotNull
    private Boolean isActive;

}
