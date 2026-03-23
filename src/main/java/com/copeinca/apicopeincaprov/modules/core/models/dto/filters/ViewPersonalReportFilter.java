
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
 * DTO filtro para búsquedas dinámicas de ViewPersonalReport.
 * Utiliza operaciones de filtro dinámico con soporte para múltiples operadores.
 */
@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ViewPersonalReportFilter implements Serializable {

    private List<FilterOperation<String>> id;
    private List<FilterOperation<Boolean>> isActive;
    private List<FilterOperation<String>> personalId;
    private List<FilterOperation<String>> sedeId;
    private List<FilterOperation<String>> personalUsuario;
    private List<FilterOperation<String>> personalNombreUsuario;
    private List<FilterOperation<String>> personalCodigo;
    private List<FilterOperation<String>> personalDescripcion;
    private List<FilterOperation<String>> sedeName;
    private List<FilterOperation<String>> sedeAmbito;

}
