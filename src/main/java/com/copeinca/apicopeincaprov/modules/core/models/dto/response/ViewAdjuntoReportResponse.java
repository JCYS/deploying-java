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

//---Simple Interface for ViewAdjuntoReportEntity.

@Data
@ToString
@EqualsAndHashCode
public class ViewAdjuntoReportResponse {

    //****************************
    // Variables de Condiciones
    //****************************

    private String idCondicion;
    private String nombreArchivoCondicion;
    private String idRepositorioCondicion;
    private String clasificationCondicion;
    private String fechaDocumentoCondicion;
    private String fechaVencimientoCondicion;
    private String rutaRelativaCondicion;
    private String ssoComentarioRevisionCondicion;
    private String caComentarioRevisionCondicion;
    private String proveedorIdCondicion;
    private String trabajadorIdCondicion;
    private String tipoDocumentoPlanillaCodeCondicion;
    private String solicitudVisitaIdCondicion;
    private String tipoActividadAltoRiesgoCodeCondicion;
    private String tdpDescripcionCondicion;
    private String tdpAmbitoCondicion;
    private String tdpInfluenciaCondicion;
    private String tdpDependenciaCondicion;
    private String tdpCampoPlantillaLfCondicion;
    private String tdpObligatorioCondicion;
    private String tdpVencimientoCondicion;
    private String tipoPropietarioCondicion;
    private String documentoVencidoCondicion;
    private String diasHastaVencimientoCondicion;

    //****************************
    // Variables Listas
    //****************************

    private List<String> idList;
    private List<String> nombreArchivoList;
    private List<String> idRepositorioList;
    private List<String> clasificationList;
    private List<LocalDate> fechaDocumentoList;
    private List<LocalDate> fechaVencimientoList;
    private List<String> rutaRelativaList;
    private List<String> ssoComentarioRevisionList;
    private List<String> caComentarioRevisionList;
    private List<String> proveedorIdList;
    private List<String> trabajadorIdList;
    private List<String> tipoDocumentoPlanillaCodeList;
    private List<String> solicitudVisitaIdList;
    private List<String> tipoActividadAltoRiesgoCodeList;
    private List<String> tdpDescripcionList;
    private List<String> tdpAmbitoList;
    private List<String> tdpInfluenciaList;
    private List<String> tdpDependenciaList;
    private List<String> tdpCampoPlantillaLfList;
    private List<Boolean> tdpObligatorioList;
    private List<Boolean> tdpVencimientoList;
    private List<String> tipoPropietarioList;
    private List<Boolean> documentoVencidoList;
    private List<Long> diasHastaVencimientoList;

}
