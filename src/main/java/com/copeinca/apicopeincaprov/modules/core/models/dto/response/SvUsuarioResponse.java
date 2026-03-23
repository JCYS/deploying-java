/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/models/dto/response/EntityResponse.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.models.dto.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

//---Simple Interface for SvUsuarioEntity.

@Data
@ToString
@EqualsAndHashCode
public class SvUsuarioResponse {

    //****************************
    // Variables de Condiciones
    //****************************

    private String idCondicion;
    private String nombreCondicion;
    private String apellidosCondicion;
    private String emailCondicion;
    private String identificadorExternoCondicion;
    private String isDeletedCondicion;
    private String versionCondicion;

    //****************************
    // Variables Listas
    //****************************

    private List<String> idList;
    private List<String> nombreList;
    private List<String> apellidosList;
    private List<String> emailList;
    private List<String> identificadorExternoList;
    private List<Boolean> isDeletedList;
    private List<Long> versionList;

}
