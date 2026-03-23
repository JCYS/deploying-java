/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/models/dto/response/EntityResponse.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.models.dto.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

//---Simple Interface for SvRolEntity.

@Data
@ToString
@EqualsAndHashCode
public class SvRolResponse {

    //****************************
    // Variables de Condiciones
    //****************************

    private String idCondicion;
    private String rolNameCondicion;
    private String rolDescriptionCondicion;
    private String isDeletedCondicion;
    private String versionCondicion;

    //****************************
    // Variables Listas
    //****************************

    private List<String> idList;
    private List<String> rolNameList;
    private List<String> rolDescriptionList;
    private List<Boolean> isDeletedList;
    private List<Long> versionList;

}
