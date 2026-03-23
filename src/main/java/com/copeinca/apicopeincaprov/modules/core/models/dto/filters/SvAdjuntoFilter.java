
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
 * DTO filtro para búsquedas dinámicas de SvAdjunto.
 * Utiliza operaciones de filtro dinámico con soporte para múltiples operadores.
 */
@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class SvAdjuntoFilter implements Serializable {

    private List<FilterOperation<String>> id;
    private List<FilterOperation<String>> nombreArchivo;
    private List<FilterOperation<String>> idRepositorio;
    private List<FilterOperation<String>> clasification;
    private List<FilterOperation<LocalDate>> fechaDocumento;
    private List<FilterOperation<LocalDate>> fechaVencimiento;
    private List<FilterOperation<String>> rutaRelativa;
    private List<FilterOperation<String>> ssoComentarioRevision;
    private List<FilterOperation<String>> caComentarioRevision;
    private List<FilterOperation<String>> motivo;
    private List<FilterOperation<Boolean>> isDeleted;
    private List<FilterOperation<Long>> version;

    /**
    * Filtro por lista de IDs para FK nativa: proveedorId.
    * Filtro directo por identificadores sin operadores complejos.
    */
    private List<String> proveedorIdIds;

    /**
    * Filtro por lista de IDs para FK nativa: trabajadorId.
    * Filtro directo por identificadores sin operadores complejos.
    */
    private List<String> trabajadorIdIds;

    /**
    * Filtro por lista de IDs para FK nativa: tipoDocumentoPlanillaCode.
    * Filtro directo por identificadores sin operadores complejos.
    */
    private List<String> tipoDocumentoPlanillaCodeIds;

    /**
    * Filtro por lista de IDs para FK nativa: solicitudVisitaId.
    * Filtro directo por identificadores sin operadores complejos.
    */
    private List<String> solicitudVisitaIdIds;

    /**
    * Filtro por lista de IDs para FK nativa: tipoActividadAltoRiesgoCode.
    * Filtro directo por identificadores sin operadores complejos.
    */
    private List<String> tipoActividadAltoRiesgoCodeIds;

}
