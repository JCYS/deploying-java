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

//---Simple Interface for SvEstadoSolicitudVisitaEntity.

@Data
@ToString
@EqualsAndHashCode
public class SvEstadoSolicitudVisitaResponse {

    //****************************
    // Variables de Condiciones
    //****************************

    private String idCondicion;
    private String nameCondicion;
    private String isActiveCondicion;
    private String isDeletedCondicion;
    private String versionCondicion;

    //****************************
    // Variables Listas
    //****************************

    private List<String> idList;
    private List<String> nameList;
    private List<Boolean> isActiveList;
    private List<Boolean> isDeletedList;
    private List<Long> versionList;

}
