/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/models/dto/response/EntityResponse.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.models.dto.response;

import com.copeinca.apicopeincaprov.modules.core.models.entities.SvSolicitudVisistaEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

//---Simple Interface for SvSolicitudVisitaEquipoEntity.

@Data
@ToString
@EqualsAndHashCode
public class SvSolicitudVisitaEquipoResponse {

    //****************************
    // Variables de Condiciones
    //****************************

    private String idCondicion;
    private String descripcionCondicion;
    private String marcaCondicion;
    private String modeloCondicion;
    private String numeroSerieCondicion;
    private String tipoEquipoCondicion;
    private String codigoEquipoCondicion;
    private String cantidadCondicion;
    private String isDeletedCondicion;
    private String versionCondicion;

    private String solicitudVisitaCondicion;

    //****************************
    // Variables Listas
    //****************************

    private List<String> idList;
    private List<String> descripcionList;
    private List<String> marcaList;
    private List<String> modeloList;
    private List<String> numeroSerieList;
    private List<String> tipoEquipoList;
    private List<String> codigoEquipoList;
    private List<BigDecimal> cantidadList;
    private List<Boolean> isDeletedList;
    private List<Long> versionList;

    private List<SvSolicitudVisistaEntity> solicitudVisitaList;

}
