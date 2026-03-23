/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/models/dto/response/EntityResponse.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.models.dto.response;

import com.copeinca.apicopeincaprov.modules.core.models.entities.SvSolicitudVisistaEntity;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvTipoActividadAltoRiesgoEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

//---Simple Interface for SvSolicitudVisitaTipoActividadAltoRiesgoEntity.

@Data
@ToString
@EqualsAndHashCode
public class SvSolicitudVisitaTipoActividadAltoRiesgoResponse {

    //****************************
    // Variables de Condiciones
    //****************************

    private String idCondicion;
    private String isActiveCondicion;
    private String isDeletedCondicion;
    private String versionCondicion;

    private String tipoActividadAltoRiesgoCodeCondicion;
    private String solicitudVisitaCondicion;

    //****************************
    // Variables Listas
    //****************************

    private List<String> idList;
    private List<Boolean> isActiveList;
    private List<Boolean> isDeletedList;
    private List<Long> versionList;

    private List<SvTipoActividadAltoRiesgoEntity> tipoActividadAltoRiesgoCodeList;

    private List<SvSolicitudVisistaEntity> solicitudVisitaList;

}
