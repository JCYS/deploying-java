/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/models/dto/response/EntityResponse.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.models.dto.response;

import com.copeinca.apicopeincaprov.modules.core.models.entities.SvSolicitudVisistaEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

//---Simple Interface for SvSolicitudVisitaEventoEntity.

@Data
@ToString
@EqualsAndHashCode
public class SvSolicitudVisitaEventoResponse {

    //****************************
    // Variables de Condiciones
    //****************************

    private String idCondicion;
    private String fechaEventoCondicion;
    private String usuarioCondicion;
    private String eventoCondicion;
    private String ambitoCondicion;
    private String revisionCondicion;
    private String respuestaUsuarioCondicion;
    private String isDeletedCondicion;
    private String versionCondicion;

    private String solicitudVisitaCondicion;

    //****************************
    // Variables Listas
    //****************************

    private List<String> idList;
    private List<LocalDateTime> fechaEventoList;
    private List<String> usuarioList;
    private List<String> eventoList;
    private List<String> ambitoList;
    private List<String> revisionList;
    private List<String> respuestaUsuarioList;
    private List<Boolean> isDeletedList;
    private List<Long> versionList;

    private List<SvSolicitudVisistaEntity> solicitudVisitaList;

}
