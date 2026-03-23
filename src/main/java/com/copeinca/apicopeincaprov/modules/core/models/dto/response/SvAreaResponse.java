/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/models/dto/response/EntityResponse.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.models.dto.response;

import com.copeinca.apicopeincaprov.modules.core.models.entities.SvSedeEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

//---Simple Interface for SvAreaEntity.

@Data
@ToString
@EqualsAndHashCode
public class SvAreaResponse {

    //****************************
    // Variables de Condiciones
    //****************************

    private String idCondicion;
    private String nameCondicion;
    private String ambitoCondicion;
    private String operativaCondicion;
    private String isDeletedCondicion;
    private String versionCondicion;

    private String sedeCondicion;

    //****************************
    // Variables Listas
    //****************************

    private List<String> idList;
    private List<String> nameList;
    private List<String> ambitoList;
    private List<String> operativaList;
    private List<Boolean> isDeletedList;
    private List<Long> versionList;

    private List<SvSedeEntity> sedeList;

}
