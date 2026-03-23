
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
import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO filtro para búsquedas dinámicas de SvProveedor.
 * Utiliza operaciones de filtro dinámico con soporte para múltiples operadores.
 */
@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class SvProveedorFilter implements Serializable {

    private List<FilterOperation<String>> id;
    private List<FilterOperation<String>> nroDocumentoIdentidad;
    private List<FilterOperation<String>> nombreFiscal;
    private List<FilterOperation<String>> email;
    private List<FilterOperation<String>> direccion;
    private List<FilterOperation<String>> contactoPrincipal;
    private List<FilterOperation<String>> contactoPrincipalTelefono;
    private List<FilterOperation<Boolean>> ssoTieneVigencia;
    private List<FilterOperation<LocalDate>> ssoFechaInicioVigencia;
    private List<FilterOperation<String>> ssoFechaFinVigencia;
    private List<FilterOperation<String>> ssoMotivo;
    private List<FilterOperation<String>> ssoUsuarioEvaluador;
    private List<FilterOperation<LocalDateTime>> ssoFechaEvaluacion;
    private List<FilterOperation<Boolean>> indRealizaActividadAltoRiesgo;
    private List<FilterOperation<Boolean>> indRealizaActividadABordo;
    private List<FilterOperation<Boolean>> caTieneVigencia;
    private List<FilterOperation<LocalDate>> caFechaInicioVigencia;
    private List<FilterOperation<LocalDate>> caFechaFinVigencia;
    private List<FilterOperation<String>> caMotivo;
    private List<FilterOperation<String>> caUsuarioEvaluador;
    private List<FilterOperation<LocalDateTime>> caFechaEvaluacion;
    private List<FilterOperation<String>> origenRegistro;
    private List<FilterOperation<Boolean>> indProveedorNotificadoCreacion;
    private List<FilterOperation<String>> telefono;
    private List<FilterOperation<Boolean>> nacional;
    private List<FilterOperation<Boolean>> isDeleted;
    private List<FilterOperation<Long>> version;

    /**
    * Filtro por lista de IDs para FK nativa: estadoCalidadAmbientalCode.
    * Filtro directo por identificadores sin operadores complejos.
    */
    private List<String> estadoCalidadAmbientalCodeIds;

    /**
    * Filtro por lista de IDs para FK nativa: estadoSsoCode.
    * Filtro directo por identificadores sin operadores complejos.
    */
    private List<String> estadoSsoCodeIds;

    private List<String> sedeIds;

    private List<String> trabajadorIds;

    private List<String> trabajadorNombre;
    private List<String> trabajadorTipoDocIdentidad;
    private List<String> trabajadorNroDocIdentidad;
    private List<String> trabajadorEstadoSSOCodeIds;

    private LocalDate trabajadorSSOFechaInicioVigencia;
    private LocalDate trabajadorSSOFechaFinVigencia;

}
