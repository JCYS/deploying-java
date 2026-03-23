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

import java.time.LocalDateTime;
import java.util.List;

//---Simple Interface for SvSolicitudVisistaEntity.

@Data
@ToString
@EqualsAndHashCode
public class SvSolicitudVisistaResponse {

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
    private String isDeletedCondicion;
    private String versionCondicion;

    private String estadoCalidadAmbientalCodeCondicion;
    private String estadoSsoCodeCondicion;
    private String personalCondicion;
    private String proveedorCondicion;
    private String estadoSolicitudVisitaCodeCondicion;
    private String sedeCondicion;

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
    private List<Boolean> isDeletedList;
    private List<Long> versionList;

    private List<SvEstadoCalidadAmbientalEntity> estadoCalidadAmbientalCodeList;

    private List<SvEstadoSsoEntity> estadoSsoCodeList;

    private List<SvPersonalEntity> personalList;

    private List<SvProveedorEntity> proveedorList;

    private List<SvEstadoSolicitudVisitaEntity> estadoSolicitudVisitaCodeList;

    private List<SvSedeEntity> sedeList;

}
