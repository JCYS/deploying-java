
/*
* Autor: Anthony Ramos
* Email: anthonyramosdev@gmail.com
* Template pack-angular:src/main/java/modules/core/models/dto/filter/DTOFilter.e.vm
*/
package com.copeinca.apicopeincaprov.modules.core.models.dto.filters;

import com.copeinca.apicopeincaprov.commons.models.FilterOperation;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * DTO filtro para búsquedas dinámicas de SvTrabajador.
 * Utiliza operaciones de filtro dinámico con soporte para múltiples operadores.
 */
@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class SvTrabajadorFilter implements Serializable {

    private List<FilterOperation<String>> id;
    private List<FilterOperation<String>> nroDocumentoIdentidad;
    private List<FilterOperation<String>> nombre;
    private List<FilterOperation<String>> telefono;
    private List<FilterOperation<String>> email;
    private List<FilterOperation<String>> ssoMotivo;
    private List<FilterOperation<Boolean>> soTieneVigencia;
    private List<FilterOperation<LocalDate>> ssoFechaInicioVigencia;
    private List<FilterOperation<LocalDate>> ssoFechaFinVigencia;
    private List<FilterOperation<Boolean>> indTrabajoAltoRiesgo;
    private List<FilterOperation<Boolean>> indPrevencionista;
    private List<FilterOperation<Boolean>> isActive;
    private List<FilterOperation<Boolean>> isDeleted;
    private List<FilterOperation<Long>> version;

    /**
    * Filtro por lista de IDs para FK nativa: proveedorId.
    * Filtro directo por identificadores sin operadores complejos.
    */
    private List<String> proveedorIdIds;

    /**
    * Filtro por lista de IDs para FK nativa: tipoDocumentoIdentidadCode.
    * Filtro directo por identificadores sin operadores complejos.
    */
    private List<String> tipoDocumentoIdentidadCodeIds;

    /**
    * Filtro por lista de IDs para FK nativa: estadoSsoCode.
    * Filtro directo por identificadores sin operadores complejos.
    */
    private List<String> estadoSsoCodeIds;

}
