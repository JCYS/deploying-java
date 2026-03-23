/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/models/dto/response/EntityResponse.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.models.dto.response;

import com.copeinca.apicopeincaprov.modules.core.models.entities.SvPersonalEntity;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvSedeEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

//---Simple Interface for SvPersonalSedeEntity.

@Data
@ToString
@EqualsAndHashCode
public class SvPersonalSedeResponse {

    //****************************
    // Variables de Condiciones
    //****************************

    private String idCondicion;
    private String isActiveCondicion;
    private String isDeletedCondicion;
    private String versionCondicion;

    private String personalCondicion;
    private String sedeCondicion;

    //****************************
    // Variables Listas
    //****************************

    private List<String> idList;
    private List<Boolean> isActiveList;
    private List<Boolean> isDeletedList;
    private List<Long> versionList;

    private List<SvPersonalEntity> personalList;

    private List<SvSedeEntity> sedeList;

}
