/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/models/dto/response/EntityResponse.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.models.dto.response;

import com.copeinca.apicopeincaprov.modules.core.models.entities.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

//---Simple Interface for SvAdjuntoEntity.

@Data
@ToString
@EqualsAndHashCode
public class SvAdjuntoResponse {

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
    private String motivoCondicion;
    private String isDeletedCondicion;
    private String versionCondicion;

    private String tipoActividadAltoRiesgoCodeCondicion;
    private String proveedorCondicion;
    private String tipoDocumentoPlanillaCodeCondicion;
    private String solicitudVisitaCondicion;
    private String trabajadorCondicion;

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
    private List<String> motivoList;
    private List<Boolean> isDeletedList;
    private List<Long> versionList;

    private List<SvTipoActividadAltoRiesgoEntity> tipoActividadAltoRiesgoCodeList;

    private List<SvProveedorEntity> proveedorList;

    private List<SvTipoDocumentoPlanillaEntity> tipoDocumentoPlanillaCodeList;

    private List<SvSolicitudVisistaEntity> solicitudVisitaList;

    private List<SvTrabajadorEntity> trabajadorList;

}
