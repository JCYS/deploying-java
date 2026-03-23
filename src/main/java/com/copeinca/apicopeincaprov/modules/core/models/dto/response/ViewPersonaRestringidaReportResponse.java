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

//---Simple Interface for ViewPersonaRestringidaReportEntity.

@Data
@ToString
@EqualsAndHashCode
public class ViewPersonaRestringidaReportResponse {

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
    private String tipoDocumentoIdentidadCodeCondicion;
    private String sedeOriginadoraIdCondicion;
    private String tdiNombreCondicion;
    private String sedeNombreCondicion;
    private String sedeAmbitoCondicion;
    private String restriccionActivaCondicion;
    private String diasRestriccionCondicion;
    private String tipoRestriccionCondicion;

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
    private List<String> tipoDocumentoIdentidadCodeList;
    private List<String> sedeOriginadoraIdList;
    private List<String> tdiNombreList;
    private List<String> sedeNombreList;
    private List<String> sedeAmbitoList;
    private List<Boolean> restriccionActivaList;
    private List<Long> diasRestriccionList;
    private List<String> tipoRestriccionList;

}
