/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/models/dto/response/EntityResponse.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.models.dto.response;

import com.copeinca.apicopeincaprov.modules.core.models.entities.SvEstadoSsoEntity;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvProveedorEntity;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvTipoDocumentoIdentidadEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

//---Simple Interface for SvTrabajadorEntity.

@Data
@ToString
@EqualsAndHashCode
public class SvTrabajadorResponse {

    //****************************
    // Variables de Condiciones
    //****************************

    private String idCondicion;
    private String nroDocumentoIdentidadCondicion;
    private String nombreCondicion;
    private String telefonoCondicion;
    private String emailCondicion;
    private String ssoMotivoCondicion;
    private String soTieneVigenciaCondicion;
    private String ssoFechaInicioVigenciaCondicion;
    private String ssoFechaFinVigenciaCondicion;
    private String indTrabajoAltoRiesgoCondicion;
    private String indPrevencionistaCondicion;
    private String isActiveCondicion;
    private String isDeletedCondicion;
    private String versionCondicion;

    private String tipoDocumentoIdentidadCodeCondicion;
    private String proveedorCondicion;
    private String estadoSsoCodeCondicion;

    //****************************
    // Variables Listas
    //****************************

    private List<String> idList;
    private List<String> nroDocumentoIdentidadList;
    private List<String> nombreList;
    private List<String> telefonoList;
    private List<String> emailList;
    private List<String> ssoMotivoList;
    private List<Boolean> soTieneVigenciaList;
    private List<LocalDate> ssoFechaInicioVigenciaList;
    private List<LocalDate> ssoFechaFinVigenciaList;
    private List<Boolean> indTrabajoAltoRiesgoList;
    private List<Boolean> indPrevencionistaList;
    private List<Boolean> isActiveList;
    private List<Boolean> isDeletedList;
    private List<Long> versionList;

    private List<SvTipoDocumentoIdentidadEntity> tipoDocumentoIdentidadCodeList;

    private List<SvProveedorEntity> proveedorList;

    private List<SvEstadoSsoEntity> estadoSsoCodeList;

}
