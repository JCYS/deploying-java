/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/models/dto/EntityDTO.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.models.dto;

import com.copeinca.apicopeincaprov.commons.models.BaseEntityDTO;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
//@Builder
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class SvUsuarioDTO extends BaseEntityDTO {

    //****************************
    // Atributos
    //****************************

    private String id;

    @Size(max = 100)
    private String nombre;

    @Size(max = 200)
    private String apellidos;

    @Email
    @Size(max = 100)
    private String email;

    @Size(max = 50)
    private String identificadorExterno;

    @Transient
    private List<SvUsuarioRolSedeDTO> listSvUsuarioRolSedeDto;

    @Transient
    private List<SvSedeDTO> listSedes;
//    private List<Sv>

}
