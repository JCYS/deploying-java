/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/models/dto/response/EntityResponse.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.models.dto.response;

import com.copeinca.apicopeincaprov.modules.core.models.entities.SvEstadoCalidadAmbientalEntity;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvEstadoSsoEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

//---Simple Interface for SvProveedorEntity.

@Data
@ToString
@EqualsAndHashCode
public class SvProveedorResponse {

    //****************************
    // Variables de Condiciones
    //****************************

    private String idCondicion;
    private String nroDocumentoIdentidadCondicion;
    private String nombreFiscalCondicion;
    private String emailCondicion;
    private String direccionCondicion;
    private String contactoPrincipalCondicion;
    private String contactoPrincipalTelefonoCondicion;
    private String ssoTieneVigenciaCondicion;
    private String ssoFechaInicioVigenciaCondicion;
    private String ssoFechaFinVigenciaCondicion;
    private String ssoMotivoCondicion;
    private String ssoUsuarioEvaluadorCondicion;
    private String ssoFechaEvaluacionCondicion;
    private String indRealizaActividadAltoRiesgoCondicion;
    private String indRealizaActividadABordoCondicion;
    private String caTieneVigenciaCondicion;
    private String caFechaInicioVigenciaCondicion;
    private String caFechaFinVigenciaCondicion;
    private String caMotivoCondicion;
    private String caUsuarioEvaluadorCondicion;
    private String caFechaEvaluacionCondicion;
    private String origenRegistroCondicion;
    private String indProveedorNotificadoCreacionCondicion;
    private String telefonoCondicion;
    private String nacionalCondicion;
    private String isDeletedCondicion;
    private String versionCondicion;

    private String estadoSsoCodeCondicion;
    private String estadoCalidadAmbientalCodeCondicion;

    //****************************
    // Variables Listas
    //****************************

    private List<String> idList;
    private List<String> nroDocumentoIdentidadList;
    private List<String> nombreFiscalList;
    private List<String> emailList;
    private List<String> direccionList;
    private List<String> contactoPrincipalList;
    private List<String> contactoPrincipalTelefonoList;
    private List<Boolean> ssoTieneVigenciaList;
    private List<LocalDate> ssoFechaInicioVigenciaList;
    private List<String> ssoFechaFinVigenciaList;
    private List<String> ssoMotivoList;
    private List<String> ssoUsuarioEvaluadorList;
    private List<LocalDateTime> ssoFechaEvaluacionList;
    private List<Boolean> indRealizaActividadAltoRiesgoList;
    private List<Boolean> indRealizaActividadABordoList;
    private List<Boolean> caTieneVigenciaList;
    private List<LocalDate> caFechaInicioVigenciaList;
    private List<LocalDate> caFechaFinVigenciaList;
    private List<String> caMotivoList;
    private List<String> caUsuarioEvaluadorList;
    private List<LocalDateTime> caFechaEvaluacionList;
    private List<String> origenRegistroList;
    private List<Boolean> indProveedorNotificadoCreacionList;
    private List<String> telefonoList;
    private List<Boolean> nacionalList;
    private List<Boolean> isDeletedList;
    private List<Long> versionList;

    private List<SvEstadoSsoEntity> estadoSsoCodeList;

    private List<SvEstadoCalidadAmbientalEntity> estadoCalidadAmbientalCodeList;

}
