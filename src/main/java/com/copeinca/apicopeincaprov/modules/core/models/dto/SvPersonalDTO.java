/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/models/dto/EntityDTO.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.models.dto;

import com.copeinca.apicopeincaprov.commons.models.BaseEntityDTO;
import jakarta.validation.constraints.NotEmpty;
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
public class SvPersonalDTO extends BaseEntityDTO {

    //****************************
    // Atributos
    //****************************

    private String id;

    @NotEmpty
    @Size(max = 100)
    private String usuario;

    @Size(max = 200)
    private String nombreUsuario;

    @Size(max = 100)
    private String codigo;

    @Size(max = 200)
    private String descripcion;

    @Size(max = 100)
    private String ambito;

    @Size(max = 100)
    private String influencia;

    @Size(max = 100)
    private String dependencia;

    @Size(max = 100)
    private String campoPlantillaLf;

    @Size(max = 2)
    private String obligatorio;

    @Size(max = 10)
    private String vencimiento;

    @Size(max = 20)
    private String numeroCaso;

    @Size(max = 200)
    private String mensaje;

    private String tipoDocumentoIdentidadCode;

}
