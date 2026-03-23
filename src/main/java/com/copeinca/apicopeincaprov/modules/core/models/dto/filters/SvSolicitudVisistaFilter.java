
/*
* Autor: Anthony Ramos
* Email: anthonyramosdev@gmail.com
* Template pack-angular:src/main/java/modules/core/models/dto/filter/DTOFilter.e.vm
*/
package com.copeinca.apicopeincaprov.modules.core.models.dto.filters;

import com.copeinca.apicopeincaprov.commons.models.FilterOperation;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO filtro para búsquedas dinámicas de SvSolicitudVisista.
 * Utiliza operaciones de filtro dinámico con soporte para múltiples operadores.
 */
@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class SvSolicitudVisistaFilter implements Serializable {

    private List<FilterOperation<String>> id;
    private List<FilterOperation<String>> codigoVisita;
    private List<FilterOperation<String>> codigoSistemaExterno;
    private List<FilterOperation<LocalDateTime>> fechaInicio;
    private List<FilterOperation<LocalDateTime>> fechaFin;
    private List<FilterOperation<String>> motivoVisita;
    private List<FilterOperation<LocalDateTime>> fechaAprobacionPersonaVisitada;
    private List<FilterOperation<LocalDateTime>> fechaObservacionAdministrador;
    private List<FilterOperation<String>> observaciones;
    private List<FilterOperation<String>> nroOrdenServicio;
    private List<FilterOperation<Boolean>> indActividadAltoRiesgo;
    private List<FilterOperation<Boolean>> indActividadABordo;
    private List<FilterOperation<Boolean>> indRequiereAndamios;
    private List<FilterOperation<Boolean>> indRequiereGrua;
    private List<FilterOperation<Boolean>> indRequiereEquiposMoviles;
    private List<FilterOperation<Boolean>> indTrabajoBuceo;
    private List<FilterOperation<String>> ssoMotivo;
    private List<FilterOperation<String>> ssoDescargo;
    private List<FilterOperation<String>> caMotivo;
    private List<FilterOperation<String>> caDescargo;
    private List<FilterOperation<Boolean>> isDeleted;
    private List<FilterOperation<Long>> version;

    /**
    * Filtro por lista de IDs para FK nativa: estadoSsoCode.
    * Filtro directo por identificadores sin operadores complejos.
    */
    private List<String> estadoSsoCodeIds;

    /**
    * Filtro por lista de IDs para FK nativa: estadoCalidadAmbientalCode.
    * Filtro directo por identificadores sin operadores complejos.
    */
    private List<String> estadoCalidadAmbientalCodeIds;

    /**
    * Filtro por lista de IDs para FK nativa: estadoSolicitudVisitaCode.
    * Filtro directo por identificadores sin operadores complejos.
    */
    private List<String> estadoSolicitudVisitaCodeIds;

    /**
    * Filtro por lista de IDs para FK nativa: sedeId.
    * Filtro directo por identificadores sin operadores complejos.
    */
    private List<String> sedeIdIds;

    /**
    * Filtro por lista de IDs para FK nativa: proveedorId.
    * Filtro directo por identificadores sin operadores complejos.
    */
    private List<String> proveedorIdIds;

    /**
    * Filtro por lista de IDs para FK nativa: personalId.
    * Filtro directo por identificadores sin operadores complejos.
    */
    private List<String> personalIdIds;

}
