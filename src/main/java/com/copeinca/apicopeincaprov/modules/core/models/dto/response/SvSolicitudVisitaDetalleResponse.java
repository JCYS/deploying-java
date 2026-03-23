/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/models/dto/response/EntityResponse.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.models.dto.response;

import com.copeinca.apicopeincaprov.modules.core.models.entities.SvSolicitudVisistaEntity;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvTrabajadorEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

//---Simple Interface for SvSolicitudVisitaDetalleEntity.

@Data
@ToString
@EqualsAndHashCode
public class SvSolicitudVisitaDetalleResponse {

    //****************************
    // Variables de Condiciones
    //****************************

    private String idCondicion;
    private String proveedorComentarioCondicion;
    private String isDeletedCondicion;
    private String versionCondicion;

    private String solicitudVisitaCondicion;
    private String trabajadorCondicion;

    //****************************
    // Variables Listas
    //****************************

    private List<String> idList;
    private List<String> proveedorComentarioList;
    private List<Boolean> isDeletedList;
    private List<Long> versionList;

    private List<SvSolicitudVisistaEntity> solicitudVisitaList;

    private List<SvTrabajadorEntity> trabajadorList;

}
