
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
 * DTO filtro para búsquedas dinámicas de ViewTrabajadorReport.
 * Utiliza operaciones de filtro dinámico con soporte para múltiples operadores.
 */
@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ViewTrabajadorReportFilter implements Serializable {

    private List<FilterOperation<String>> id;
    private List<FilterOperation<String>> proveedorId;
    private List<FilterOperation<String>> provNroDocumentoIdentidad;
    private List<FilterOperation<String>> provNombreFiscal;
    private List<FilterOperation<String>> tipoDocumentoIdentidadCode;
    private List<FilterOperation<String>> nroDocumentoIdentidad;
    private List<FilterOperation<String>> tdiNombre;
    private List<FilterOperation<String>> nombre;
    private List<FilterOperation<String>> telefono;
    private List<FilterOperation<String>> email;
    private List<FilterOperation<String>> estadoSsoCode;
    private List<FilterOperation<String>> ssoMotivo;
    private List<FilterOperation<Boolean>> soTieneVigencia;
    private List<FilterOperation<LocalDate>> ssoFechaInicioVigencia;
    private List<FilterOperation<LocalDate>> ssoFechaFinVigencia;
    private List<FilterOperation<Boolean>> isDeleted;
    private List<FilterOperation<Boolean>> isActive;
    private List<FilterOperation<String>> ssoEstadoNombre;
    private List<FilterOperation<String>> ssoEstadoVigente;
    private List<FilterOperation<Boolean>> indTrabajoAltoRiesgo;
    private List<FilterOperation<Boolean>> indPrevencionista;
    private List<FilterOperation<String>> tarCodigos;
    private List<FilterOperation<String>> tarDescripciones;
    private List<FilterOperation<Long>> nDocsSsoAdjuntos;
    private List<FilterOperation<Long>> nDocsCalidadAdjuntos;
    private List<FilterOperation<Long>> nDocsAarAdjuntos;
    private List<FilterOperation<Long>> nDocsAbAdjuntos;
    private List<FilterOperation<Long>> nDocsPrevAdjuntos;
    private List<FilterOperation<String>> isValid;

}
