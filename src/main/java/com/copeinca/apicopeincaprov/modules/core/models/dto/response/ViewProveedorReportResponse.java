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
import java.time.LocalDateTime;
import java.util.List;

//---Simple Interface for ViewProveedorReportEntity.

@Data
@ToString
@EqualsAndHashCode
public class ViewProveedorReportResponse {

    //****************************
    // Variables de Condiciones
    //****************************

    private String idCondicion;
    private String nroDocumentoIdentidadCondicion;
    private String nombreFiscalCondicion;
    private String emailCondicion;
    private String direccionCondicion;
    private String telefonoCondicion;
    private String nacionalCondicion;
    private String contactoPrincipalCondicion;
    private String contactoPrincipalTelefonoCondicion;
    private String origenRegistroCondicion;
    private String sedesIdsCondicion;
    private String sedesNombresCondicion;
    private String estadoSsoCodeCondicion;
    private String ssoTieneVigenciaCondicion;
    private String ssoFechaInicioVigenciaCondicion;
    private String ssoFechaFinVigenciaCondicion;
    private String ssoMotivoCondicion;
    private String ssoUsuarioEvaluadorCondicion;
    private String ssoFechaEvaluacionCondicion;
    private String ssoEstadoNombreCondicion;
    private String ssoEstadoDescripcionCondicion;
    private String ssoEstadoVigenteCondicion;
    private String estadoCalidadAmbientalCodeCondicion;
    private String caTieneVigenciaCondicion;
    private String caFechaInicioVigenciaCondicion;
    private String caFechaFinVigenciaCondicion;
    private String caMotivoCondicion;
    private String caUsuarioEvaluadorCondicion;
    private String caFechaEvaluacionCondicion;
    private String caEstadoNombreCondicion;
    private String caEstadoDescripcionCondicion;
    private String caEstadoVigenteCondicion;
    private String nTrabajadoresAptosCondicion;
    private String nTrabajadoresTotalesCondicion;
    private String nDocsProvSsoCondicion;
    private String nDocsCaCondicion;
    private String nDocsAarCondicion;
    private String nDocsAbCondicion;
    private String nDocsTrabajSsoCondicion;
    private String indRealizaActividadAltoRiesgoCondicion;
    private String indRealizaActividadABordoCondicion;
    private String tarCodigosCondicion;
    private String tarDescripcionesCondicion;

    //****************************
    // Variables Listas
    //****************************

    private List<String> idList;
    private List<String> nroDocumentoIdentidadList;
    private List<String> nombreFiscalList;
    private List<String> emailList;
    private List<String> direccionList;
    private List<String> telefonoList;
    private List<Boolean> nacionalList;
    private List<String> contactoPrincipalList;
    private List<String> contactoPrincipalTelefonoList;
    private List<String> origenRegistroList;
    private List<String> sedesIdsList;
    private List<String> sedesNombresList;
    private List<String> estadoSsoCodeList;
    private List<Boolean> ssoTieneVigenciaList;
    private List<LocalDate> ssoFechaInicioVigenciaList;
    private List<String> ssoFechaFinVigenciaList;
    private List<String> ssoMotivoList;
    private List<String> ssoUsuarioEvaluadorList;
    private List<LocalDateTime> ssoFechaEvaluacionList;
    private List<String> ssoEstadoNombreList;
    private List<String> ssoEstadoDescripcionList;
    private List<String> ssoEstadoVigenteList;
    private List<String> estadoCalidadAmbientalCodeList;
    private List<Boolean> caTieneVigenciaList;
    private List<LocalDate> caFechaInicioVigenciaList;
    private List<LocalDate> caFechaFinVigenciaList;
    private List<String> caMotivoList;
    private List<String> caUsuarioEvaluadorList;
    private List<LocalDateTime> caFechaEvaluacionList;
    private List<String> caEstadoNombreList;
    private List<String> caEstadoDescripcionList;
    private List<String> caEstadoVigenteList;
    private List<Long> nTrabajadoresAptosList;
    private List<Long> nTrabajadoresTotalesList;
    private List<Long> nDocsProvSsoList;
    private List<Long> nDocsCaList;
    private List<Long> nDocsAarList;
    private List<Long> nDocsAbList;
    private List<Long> nDocsTrabajSsoList;
    private List<Boolean> indRealizaActividadAltoRiesgoList;
    private List<Boolean> indRealizaActividadABordoList;
    private List<String> tarCodigosList;
    private List<String> tarDescripcionesList;

}
