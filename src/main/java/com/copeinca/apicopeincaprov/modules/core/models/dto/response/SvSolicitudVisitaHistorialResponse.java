/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/models/dto/response/EntityResponse.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.models.dto.response;

import com.copeinca.apicopeincaprov.modules.core.models.entities.SvEstadoSolicitudVisitaEntity;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvSolicitudVisistaEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

//---Simple Interface for SvSolicitudVisitaHistorialEntity.

@Data
@ToString
@EqualsAndHashCode
public class SvSolicitudVisitaHistorialResponse {

    //****************************
    // Variables de Condiciones
    //****************************

    private String idCondicion;
    private String fechaHoraCondicion;
    private String usuarioCondicion;
    private String ambitoCondicion;
    private String revisionCondicion;
    private String rptaByUsuarioCondicion;
    private String isDeletedCondicion;
    private String versionCondicion;

    private String estadoSolicitudVisitaCodeCondicion;
    private String solicitudVisitaCondicion;

    //****************************
    // Variables Listas
    //****************************

    private List<String> idList;
    private List<LocalDateTime> fechaHoraList;
    private List<String> usuarioList;
    private List<String> ambitoList;
    private List<String> revisionList;
    private List<String> rptaByUsuarioList;
    private List<Boolean> isDeletedList;
    private List<Long> versionList;

    private List<SvEstadoSolicitudVisitaEntity> estadoSolicitudVisitaCodeList;

    private List<SvSolicitudVisistaEntity> solicitudVisitaList;

}
