
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
 * DTO filtro para búsquedas dinámicas de ViewAdjuntoReport.
 * Utiliza operaciones de filtro dinámico con soporte para múltiples operadores.
 */
@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ViewAdjuntoReportFilter implements Serializable {

    private List<FilterOperation<String>> id;
    private List<FilterOperation<String>> nombreArchivo;
    private List<FilterOperation<String>> idRepositorio;
    private List<FilterOperation<String>> clasification;
    private List<FilterOperation<LocalDate>> fechaDocumento;
    private List<FilterOperation<LocalDate>> fechaVencimiento;
    private List<FilterOperation<String>> rutaRelativa;
    private List<FilterOperation<String>> ssoComentarioRevision;
    private List<FilterOperation<String>> caComentarioRevision;
    private List<FilterOperation<String>> proveedorId;
    private List<FilterOperation<String>> trabajadorId;
    private List<FilterOperation<String>> tipoDocumentoPlanillaCode;
    private List<FilterOperation<String>> solicitudVisitaId;
    private List<FilterOperation<String>> tipoActividadAltoRiesgoCode;
    private List<FilterOperation<String>> tdpDescripcion;
    private List<FilterOperation<String>> tdpAmbito;
    private List<FilterOperation<String>> tdpInfluencia;
    private List<FilterOperation<String>> tdpDependencia;
    private List<FilterOperation<String>> tdpCampoPlantillaLf;
    private List<FilterOperation<Boolean>> tdpObligatorio;
    private List<FilterOperation<Boolean>> tdpVencimiento;
    private List<FilterOperation<String>> tipoPropietario;
    private List<FilterOperation<Boolean>> documentoVencido;
    private List<FilterOperation<Long>> diasHastaVencimiento;

}
