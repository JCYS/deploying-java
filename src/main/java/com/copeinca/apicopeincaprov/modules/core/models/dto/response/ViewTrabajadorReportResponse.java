/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/models/dto/response/EntityResponse.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.models.dto.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

//---Simple Interface for ViewTrabajadorReportEntity.

@Data
@ToString
@EqualsAndHashCode
public class ViewTrabajadorReportResponse {

    //****************************
    // Variables de Condiciones
    //****************************

    private String idCondicion;
    private String proveedorIdCondicion;
    private String provNroDocumentoIdentidadCondicion;
    private String provNombreFiscalCondicion;
    private String tipoDocumentoIdentidadCodeCondicion;
    private String nroDocumentoIdentidadCondicion;
    private String tdiNombreCondicion;
    private String nombreCondicion;
    private String telefonoCondicion;
    private String emailCondicion;
    private String estadoSsoCodeCondicion;
    private String ssoMotivoCondicion;
    private String soTieneVigenciaCondicion;
    private String ssoFechaInicioVigenciaCondicion;
    private String ssoFechaFinVigenciaCondicion;
    private String isDeletedCondicion;
    private String isActiveCondicion;
    private String ssoEstadoNombreCondicion;
    private String ssoEstadoVigenteCondicion;
    private String indTrabajoAltoRiesgoCondicion;
    private String indPrevencionistaCondicion;
    private String tarCodigosCondicion;
    private String tarDescripcionesCondicion;
    private String nDocsSsoAdjuntosCondicion;
    private String nDocsCalidadAdjuntosCondicion;
    private String nDocsAarAdjuntosCondicion;
    private String nDocsAbAdjuntosCondicion;
    private String nDocsPrevAdjuntosCondicion;

    //****************************
    // Variables Listas
    //****************************

    private List<String> idList;
    private List<String> proveedorIdList;
    private List<String> provNroDocumentoIdentidadList;
    private List<String> provNombreFiscalList;
    private List<String> tipoDocumentoIdentidadCodeList;
    private List<String> nroDocumentoIdentidadList;
    private List<String> tdiNombreList;
    private List<String> nombreList;
    private List<String> telefonoList;
    private List<String> emailList;
    private List<String> estadoSsoCodeList;
    private List<String> ssoMotivoList;
    private List<Boolean> soTieneVigenciaList;
    private List<LocalDate> ssoFechaInicioVigenciaList;
    private List<LocalDate> ssoFechaFinVigenciaList;
    private List<Boolean> isDeletedList;
    private List<Boolean> isActiveList;
    private List<String> ssoEstadoNombreList;
    private List<String> ssoEstadoVigenteList;
    private List<Boolean> indTrabajoAltoRiesgoList;
    private List<Boolean> indPrevencionistaList;
    private List<String> tarCodigosList;
    private List<String> tarDescripcionesList;
    private List<Long> nDocsSsoAdjuntosList;
    private List<Long> nDocsCalidadAdjuntosList;
    private List<Long> nDocsAarAdjuntosList;
    private List<Long> nDocsAbAdjuntosList;
    private List<Long> nDocsPrevAdjuntosList;

}
