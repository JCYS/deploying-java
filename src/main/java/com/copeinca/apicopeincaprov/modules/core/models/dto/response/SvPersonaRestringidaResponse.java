/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/models/dto/response/EntityResponse.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.models.dto.response;

import com.copeinca.apicopeincaprov.modules.core.models.entities.SvSedeEntity;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvTipoDocumentoIdentidadEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

//---Simple Interface for SvPersonaRestringidaEntity.

@Data
@ToString
@EqualsAndHashCode
public class SvPersonaRestringidaResponse {

    //****************************
    // Variables de Condiciones
    //****************************

    private String idCondicion;
    private String numeroDocumentoCondicion;
    private String licenciaConducirCondicion;
    private String nombreCompletoCondicion;
    private String usuarioReportaCondicion;
    private String restringidoElCondicion;
    private String restringidoHastaCondicion;
    private String observacionesCondicion;
    private String isDeletedCondicion;
    private String versionCondicion;

    private String sedeOriginadoraCondicion;
    private String tipoDocumentoIdentidadCodeCondicion;

    //****************************
    // Variables Listas
    //****************************

    private List<String> idList;
    private List<String> numeroDocumentoList;
    private List<String> licenciaConducirList;
    private List<String> nombreCompletoList;
    private List<String> usuarioReportaList;
    private List<LocalDateTime> restringidoElList;
    private List<LocalDateTime> restringidoHastaList;
    private List<String> observacionesList;
    private List<Boolean> isDeletedList;
    private List<Long> versionList;

    private List<SvSedeEntity> sedeOriginadoraList;

    private List<SvTipoDocumentoIdentidadEntity> tipoDocumentoIdentidadCodeList;

}
