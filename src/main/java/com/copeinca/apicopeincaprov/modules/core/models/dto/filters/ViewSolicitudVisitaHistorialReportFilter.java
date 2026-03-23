
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
 * DTO filtro para búsquedas dinámicas de ViewSolicitudVisitaHistorialReport.
 * Utiliza operaciones de filtro dinámico con soporte para múltiples operadores.
 */
@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ViewSolicitudVisitaHistorialReportFilter implements Serializable {

    private List<FilterOperation<String>> id;
    private List<FilterOperation<LocalDateTime>> fechaHora;
    private List<FilterOperation<String>> usuario;
    private List<FilterOperation<String>> ambito;
    private List<FilterOperation<String>> revision;
    private List<FilterOperation<String>> rptaByUsuario;
    private List<FilterOperation<String>> solicitudVisitaId;
    private List<FilterOperation<String>> estadoSolicitudVisitaCode;
    private List<FilterOperation<String>> solvisNroOrdenServicio;
    private List<FilterOperation<String>> solvisCodigoVisita;
    private List<FilterOperation<String>> esvNombre;
    private List<FilterOperation<String>> prevEstadoSolicitudVisitaCode;
    private List<FilterOperation<String>> prevEstadoNombre;
    private List<FilterOperation<LocalDateTime>> prevFechaHora;

}
