
/*
* Autor: Anthony Ramos
* Email: anthonyramosdev@gmail.com
* Template pack-angular:src/main/java/modules/core/models/dto/filter/DTOFilter.e.vm
*/
package com.copeinca.apicopeincaprov.modules.core.models.dto.filters;

import com.copeinca.apicopeincaprov.commons.models.FilterOperation;
import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * DTO filtro para búsquedas dinámicas de SvProveedorTipoActividadAltoRiesgo.
 * Utiliza operaciones de filtro dinámico con soporte para múltiples operadores.
 */
@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class SvProveedorTipoActividadAltoRiesgoFilter implements Serializable {

    private List<FilterOperation<String>> id;
    private List<FilterOperation<Boolean>> isActive;
    private List<FilterOperation<Boolean>> isDeleted;
    private List<FilterOperation<Long>> version;

    /**
    * Filtro por lista de IDs para FK nativa: tipoActividadAltoRiesgoCode.
    * Filtro directo por identificadores sin operadores complejos.
    */
    private List<String> tipoActividadAltoRiesgoCodeIds;

    /**
    * Filtro por lista de IDs para FK nativa: proveedorId.
    * Filtro directo por identificadores sin operadores complejos.
    */
    private List<String> proveedorIdIds;

}
