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

//---Simple Interface for ViewSolicitudVisitaReportEntity.

@Data
@ToString
@EqualsAndHashCode
public class ViewSolicitudVisitaReportResponse {

    //****************************
    // Variables de Condiciones
    //****************************

    private String idCondicion;
    private String codigoVisitaCondicion;
    private String codigoSistemaExternoCondicion;
    private String fechaInicioCondicion;
    private String fechaFinCondicion;
    private String motivoVisitaCondicion;
    private String fechaAprobacionPersonaVisitadaCondicion;
    private String fechaObservacionAdministradorCondicion;
    private String observacionesCondicion;
    private String nroOrdenServicioCondicion;
    private String indActividadAltoRiesgoCondicion;
    private String indActividadABordoCondicion;
    private String indRequiereAndamiosCondicion;
    private String indRequiereGruaCondicion;
    private String indRequiereEquiposMovilesCondicion;
    private String indTrabajoBuceoCondicion;
    private String ssoMotivoCondicion;
    private String ssoDescargoCondicion;
    private String caMotivoCondicion;
    private String caDescargoCondicion;
    private String estadoSsoCodeCondicion;
    private String estadoCalidadAmbientalCodeCondicion;
    private String estadoSolicitudVisitaCodeCondicion;
    private String sedeIdCondicion;
    private String proveedorIdCondicion;
    private String personalIdCondicion;
    private String sedeNombreCondicion;
    private String sedeAmbitoCondicion;
    private String personalNombreUsuarioCondicion;
    private String personalUsuarioCondicion;
    private String proveedorNombreFiscalCondicion;
    private String proveedorNroDocumentoIdentidadCondicion;
    private String proveedorEmailCondicion;
    private String proveedorContactoPrincipalCondicion;
    private String esvNombreCondicion;
    private String ssoEstadoNombreCondicion;
    private String ssoEstadoDescripcionCondicion;
    private String caEstadoNombreCondicion;
    private String caEstadoDescripcionCondicion;
    private String tarDescripcionesCondicion;
    private String nDiasCondicion;
    private String nTrabajadoresCondicion;
    private String areasIdsCondicion;
    private String areasNombresCondicion;
    private String zonasIdsCondicion;
    private String zonasNombresCondicion;

    //****************************
    // Variables Listas
    //****************************

    private List<String> idList;
    private List<String> codigoVisitaList;
    private List<String> codigoSistemaExternoList;
    private List<LocalDateTime> fechaInicioList;
    private List<LocalDateTime> fechaFinList;
    private List<String> motivoVisitaList;
    private List<LocalDateTime> fechaAprobacionPersonaVisitadaList;
    private List<LocalDateTime> fechaObservacionAdministradorList;
    private List<String> observacionesList;
    private List<String> nroOrdenServicioList;
    private List<Boolean> indActividadAltoRiesgoList;
    private List<Boolean> indActividadABordoList;
    private List<Boolean> indRequiereAndamiosList;
    private List<Boolean> indRequiereGruaList;
    private List<Boolean> indRequiereEquiposMovilesList;
    private List<Boolean> indTrabajoBuceoList;
    private List<String> ssoMotivoList;
    private List<String> ssoDescargoList;
    private List<String> caMotivoList;
    private List<String> caDescargoList;
    private List<String> estadoSsoCodeList;
    private List<String> estadoCalidadAmbientalCodeList;
    private List<String> estadoSolicitudVisitaCodeList;
    private List<String> sedeIdList;
    private List<String> proveedorIdList;
    private List<String> personalIdList;
    private List<String> sedeNombreList;
    private List<String> sedeAmbitoList;
    private List<String> personalNombreUsuarioList;
    private List<String> personalUsuarioList;
    private List<String> proveedorNombreFiscalList;
    private List<String> proveedorNroDocumentoIdentidadList;
    private List<String> proveedorEmailList;
    private List<String> proveedorContactoPrincipalList;
    private List<String> esvNombreList;
    private List<String> ssoEstadoNombreList;
    private List<String> ssoEstadoDescripcionList;
    private List<String> caEstadoNombreList;
    private List<String> caEstadoDescripcionList;
    private List<String> tarDescripcionesList;
    private List<Long> nDiasList;
    private List<Long> nTrabajadoresList;
    private List<String> areasIdsList;
    private List<String> areasNombresList;
    private List<String> zonasIdsList;
    private List<String> zonasNombresList;

}
