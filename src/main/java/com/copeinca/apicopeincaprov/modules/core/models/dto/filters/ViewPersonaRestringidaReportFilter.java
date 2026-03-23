
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
 * DTO filtro para búsquedas dinámicas de ViewPersonaRestringidaReport.
 * Utiliza operaciones de filtro dinámico con soporte para múltiples operadores.
 */
@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ViewPersonaRestringidaReportFilter implements Serializable {

    private List<FilterOperation<String>> id;
    private List<FilterOperation<String>> numeroDocumento;
    private List<FilterOperation<String>> licenciaConducir;
    private List<FilterOperation<String>> nombreCompleto;
    private List<FilterOperation<String>> usuarioReporta;
    private List<FilterOperation<LocalDateTime>> restringidoEl;
    private List<FilterOperation<LocalDateTime>> restringidoHasta;
    private List<FilterOperation<String>> observaciones;
    private List<FilterOperation<String>> tipoDocumentoIdentidadCode;
    private List<FilterOperation<String>> sedeOriginadoraId;
    private List<FilterOperation<String>> tdiNombre;
    private List<FilterOperation<String>> sedeNombre;
    private List<FilterOperation<String>> sedeAmbito;
    private List<FilterOperation<Boolean>> restriccionActiva;
    private List<FilterOperation<Long>> diasRestriccion;
    private List<FilterOperation<String>> tipoRestriccion;

}
