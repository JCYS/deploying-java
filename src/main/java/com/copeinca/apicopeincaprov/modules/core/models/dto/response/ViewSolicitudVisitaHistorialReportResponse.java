/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/models/dto/response/EntityResponse.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.models.dto.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

//---Simple Interface for ViewSolicitudVisitaHistorialReportEntity.

@Data
@ToString
@EqualsAndHashCode
public class ViewSolicitudVisitaHistorialReportResponse {

    //****************************
    // Variables de Condiciones
    //****************************

    private String idCondicion;
    private String fechaHoraCondicion;
    private String usuarioCondicion;
    private String ambitoCondicion;
    private String revisionCondicion;
    private String rptaByUsuarioCondicion;
    private String solicitudVisitaIdCondicion;
    private String estadoSolicitudVisitaCodeCondicion;
    private String solvisNroOrdenServicioCondicion;
    private String solvisCodigoVisitaCondicion;
    private String esvNombreCondicion;
    private String prevEstadoSolicitudVisitaCodeCondicion;
    private String prevEstadoNombreCondicion;
    private String prevFechaHoraCondicion;

    //****************************
    // Variables Listas
    //****************************

    private List<String> idList;
    private List<LocalDateTime> fechaHoraList;
    private List<String> usuarioList;
    private List<String> ambitoList;
    private List<String> revisionList;
    private List<String> rptaByUsuarioList;
    private List<String> solicitudVisitaIdList;
    private List<String> estadoSolicitudVisitaCodeList;
    private List<String> solvisNroOrdenServicioList;
    private List<String> solvisCodigoVisitaList;
    private List<String> esvNombreList;
    private List<String> prevEstadoSolicitudVisitaCodeList;
    private List<String> prevEstadoNombreList;
    private List<LocalDateTime> prevFechaHoraList;

}
