
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
 * DTO filtro para búsquedas dinámicas de SvSolicitudVisitaEvento.
 * Utiliza operaciones de filtro dinámico con soporte para múltiples operadores.
 */
@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class SvSolicitudVisitaEventoFilter implements Serializable {

    private List<FilterOperation<String>> id;
    private List<FilterOperation<LocalDateTime>> fechaEvento;
    private List<FilterOperation<String>> usuario;
    private List<FilterOperation<String>> evento;
    private List<FilterOperation<String>> ambito;
    private List<FilterOperation<String>> revision;
    private List<FilterOperation<String>> respuestaUsuario;
    private List<FilterOperation<Boolean>> isDeleted;
    private List<FilterOperation<Long>> version;

    /**
    * Filtro por lista de IDs para FK nativa: solicitudVisitaId.
    * Filtro directo por identificadores sin operadores complejos.
    */
    private List<String> solicitudVisitaIdIds;

}
