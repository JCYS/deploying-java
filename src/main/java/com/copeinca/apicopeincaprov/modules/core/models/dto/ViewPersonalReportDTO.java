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
public class ViewPersonalReportDTO extends BaseEntityDTO {

    //****************************
    // Atributos
    //****************************

    private String id;

    @NotNull
    private Boolean isActive;

    @NotEmpty
    @Size(max = 36)
    private String personalId;

    @NotEmpty
    @Size(max = 36)
    private String sedeId;

    @NotEmpty
    @Size(max = 100)
    private String personalUsuario;

    @Size(max = 200)
    private String personalNombreUsuario;

    @Size(max = 100)
    private String personalCodigo;

    @Size(max = 200)
    private String personalDescripcion;

    @NotEmpty
    @Size(max = 100)
    private String sedeName;

    @NotEmpty
    @Size(max = 20)
    private String sedeAmbito;

}
