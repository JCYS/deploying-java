
/*
* Autor: Anthony Ramos
* Email: anthonyramosdev@gmail.com
* Template pack-angular:src/main/java/modules/core/models/dto/filter/DTOFilter.e.vm
*/
package com.copeinca.apicopeincaprov.modules.core.models.dto.filters;

import com.copeinca.apicopeincaprov.commons.models.FilterOperation;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * DTO filtro para búsquedas dinámicas de SvSolicitudVisitaEquipo.
 * Utiliza operaciones de filtro dinámico con soporte para múltiples operadores.
 */
@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class SvSolicitudVisitaEquipoFilter implements Serializable {

    private List<FilterOperation<String>> id;
    private List<FilterOperation<String>> descripcion;
    private List<FilterOperation<String>> marca;
    private List<FilterOperation<String>> modelo;
    private List<FilterOperation<String>> numeroSerie;
    private List<FilterOperation<String>> tipoEquipo;
    private List<FilterOperation<String>> codigoEquipo;
    private List<FilterOperation<BigDecimal>> cantidad;
    private List<FilterOperation<Boolean>> isDeleted;
    private List<FilterOperation<Long>> version;

    /**
    * Filtro por lista de IDs para FK nativa: solicitudVisitaId.
    * Filtro directo por identificadores sin operadores complejos.
    */
    private List<String> solicitudVisitaIdIds;

}
