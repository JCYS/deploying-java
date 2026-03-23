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

//---Simple Interface for SvTipoDocumentoPlanillaEntity.

@Data
@ToString
@EqualsAndHashCode
public class SvTipoDocumentoPlanillaResponse {

    //****************************
    // Variables de Condiciones
    //****************************

    private String idCondicion;
    private String descriptionCondicion;
    private String ambitoCondicion;
    private String influenciaCondicion;
    private String dependenciaCondicion;
    private String campoPlantillaLfCondicion;
    private String obligatorioCondicion;
    private String vencimientoCondicion;
    private String isDeletedCondicion;
    private String versionCondicion;

    //****************************
    // Variables Listas
    //****************************

    private List<String> idList;
    private List<String> descriptionList;
    private List<String> ambitoList;
    private List<String> influenciaList;
    private List<String> dependenciaList;
    private List<String> campoPlantillaLfList;
    private List<Boolean> obligatorioList;
    private List<Boolean> vencimientoList;
    private List<Boolean> isDeletedList;
    private List<Long> versionList;

}
