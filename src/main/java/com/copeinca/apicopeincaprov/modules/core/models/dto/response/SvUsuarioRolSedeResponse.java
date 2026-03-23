/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/models/dto/response/EntityResponse.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.models.dto.response;

import com.copeinca.apicopeincaprov.modules.core.models.entities.SvRolEntity;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvSedeEntity;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvUsuarioEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

//---Simple Interface for SvUsuarioRolSedeEntity.

@Data
@ToString
@EqualsAndHashCode
public class SvUsuarioRolSedeResponse {

    //****************************
    // Variables de Condiciones
    //****************************

    private String idCondicion;
    private String isActiveCondicion;
    private String isDeletedCondicion;
    private String versionCondicion;

    private String sedeCondicion;
    private String rolCodeCondicion;
    private String usuarioCondicion;

    //****************************
    // Variables Listas
    //****************************

    private List<String> idList;
    private List<Boolean> isActiveList;
    private List<Boolean> isDeletedList;
    private List<Long> versionList;

    private List<SvSedeEntity> sedeList;

    private List<SvRolEntity> rolCodeList;

    private List<SvUsuarioEntity> usuarioList;

}
