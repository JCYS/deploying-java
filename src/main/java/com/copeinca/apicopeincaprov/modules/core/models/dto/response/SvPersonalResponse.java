/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/models/dto/response/EntityResponse.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.models.dto.response;

import com.copeinca.apicopeincaprov.modules.core.models.entities.SvTipoDocumentoIdentidadEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

//---Simple Interface for SvPersonalEntity.

@Data
@ToString
@EqualsAndHashCode
public class SvPersonalResponse {

    //****************************
    // Variables de Condiciones
    //****************************

    private String idCondicion;
    private String usuarioCondicion;
    private String nombreUsuarioCondicion;
    private String codigoCondicion;
    private String descripcionCondicion;
    private String ambitoCondicion;
    private String influenciaCondicion;
    private String dependenciaCondicion;
    private String campoPlantillaLfCondicion;
    private String obligatorioCondicion;
    private String vencimientoCondicion;
    private String numeroCasoCondicion;
    private String mensajeCondicion;
    private String isDeletedCondicion;
    private String versionCondicion;

    private String tipoDocumentoIdentidadCodeCondicion;

    //****************************
    // Variables Listas
    //****************************

    private List<String> idList;
    private List<String> usuarioList;
    private List<String> nombreUsuarioList;
    private List<String> codigoList;
    private List<String> descripcionList;
    private List<String> ambitoList;
    private List<String> influenciaList;
    private List<String> dependenciaList;
    private List<String> campoPlantillaLfList;
    private List<String> obligatorioList;
    private List<String> vencimientoList;
    private List<String> numeroCasoList;
    private List<String> mensajeList;
    private List<Boolean> isDeletedList;
    private List<Long> versionList;

    private List<SvTipoDocumentoIdentidadEntity> tipoDocumentoIdentidadCodeList;

}
