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

//---Simple Interface for SvEstadoCalidadAmbientalEntity.

@Data
@ToString
@EqualsAndHashCode
public class SvEstadoCalidadAmbientalResponse {

    //****************************
    // Variables de Condiciones
    //****************************

    private String idCondicion;
    private String nameCondicion;
    private String descriptionCondicion;
    private String isDeletedCondicion;
    private String versionCondicion;

    //****************************
    // Variables Listas
    //****************************

    private List<String> idList;
    private List<String> nameList;
    private List<String> descriptionList;
    private List<Boolean> isDeletedList;
    private List<Long> versionList;

}
