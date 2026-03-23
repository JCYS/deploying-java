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

//---Simple Interface for ViewPersonalReportEntity.

@Data
@ToString
@EqualsAndHashCode
public class ViewPersonalReportResponse {

    //****************************
    // Variables de Condiciones
    //****************************

    private String idCondicion;
    private String isActiveCondicion;
    private String personalIdCondicion;
    private String sedeIdCondicion;
    private String personalUsuarioCondicion;
    private String personalNombreUsuarioCondicion;
    private String personalCodigoCondicion;
    private String personalDescripcionCondicion;
    private String sedeNameCondicion;
    private String sedeAmbitoCondicion;

    //****************************
    // Variables Listas
    //****************************

    private List<String> idList;
    private List<Boolean> isActiveList;
    private List<String> personalIdList;
    private List<String> sedeIdList;
    private List<String> personalUsuarioList;
    private List<String> personalNombreUsuarioList;
    private List<String> personalCodigoList;
    private List<String> personalDescripcionList;
    private List<String> sedeNameList;
    private List<String> sedeAmbitoList;

}
