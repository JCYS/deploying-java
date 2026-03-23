
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
 * DTO filtro para búsquedas dinámicas de SvPersonal.
 * Utiliza operaciones de filtro dinámico con soporte para múltiples operadores.
 */
@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class SvPersonalFilter implements Serializable {

    private List<FilterOperation<String>> id;
    private List<FilterOperation<String>> usuario;
    private List<FilterOperation<String>> nombreUsuario;
    private List<FilterOperation<String>> codigo;
    private List<FilterOperation<String>> descripcion;
    private List<FilterOperation<String>> ambito;
    private List<FilterOperation<String>> influencia;
    private List<FilterOperation<String>> dependencia;
    private List<FilterOperation<String>> campoPlantillaLf;
    private List<FilterOperation<String>> obligatorio;
    private List<FilterOperation<String>> vencimiento;
    private List<FilterOperation<String>> numeroCaso;
    private List<FilterOperation<String>> mensaje;
    private List<FilterOperation<Boolean>> isDeleted;
    private List<FilterOperation<Long>> version;

    /**
    * Filtro por lista de IDs para FK nativa: tipoDocumentoIdentidadCode.
    * Filtro directo por identificadores sin operadores complejos.
    */
    private List<String> tipoDocumentoIdentidadCodeIds;

    private List<String> sedeIds;
    private List<String> sedeNames;

}
