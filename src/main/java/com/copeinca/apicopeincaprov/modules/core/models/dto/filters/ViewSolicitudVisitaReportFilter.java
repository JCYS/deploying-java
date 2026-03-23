
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
 * DTO filtro para búsquedas dinámicas de ViewSolicitudVisitaReport.
 * Utiliza operaciones de filtro dinámico con soporte para múltiples operadores.
 */
@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ViewSolicitudVisitaReportFilter implements Serializable {

    private List<FilterOperation<String>> id;
    private List<FilterOperation<String>> codigoVisita;
    private List<FilterOperation<String>> codigoSistemaExterno;
    private List<FilterOperation<LocalDateTime>> fechaInicio;
    private List<FilterOperation<LocalDateTime>> fechaFin;
    private List<FilterOperation<String>> motivoVisita;
    private List<FilterOperation<LocalDateTime>> fechaAprobacionPersonaVisitada;
    private List<FilterOperation<LocalDateTime>> fechaObservacionAdministrador;
    private List<FilterOperation<String>> observaciones;
    private List<FilterOperation<String>> nroOrdenServicio;
    private List<FilterOperation<Boolean>> indActividadAltoRiesgo;
    private List<FilterOperation<Boolean>> indActividadABordo;
    private List<FilterOperation<Boolean>> indRequiereAndamios;
    private List<FilterOperation<Boolean>> indRequiereGrua;
    private List<FilterOperation<Boolean>> indRequiereEquiposMoviles;
    private List<FilterOperation<Boolean>> indTrabajoBuceo;
    private List<FilterOperation<String>> ssoMotivo;
    private List<FilterOperation<String>> ssoDescargo;
    private List<FilterOperation<String>> caMotivo;
    private List<FilterOperation<String>> caDescargo;
    private List<FilterOperation<String>> estadoSsoCode;
    private List<FilterOperation<String>> estadoCalidadAmbientalCode;
    private List<FilterOperation<String>> estadoSolicitudVisitaCode;
    private List<FilterOperation<String>> sedeId;
    private List<FilterOperation<String>> proveedorId;
    private List<FilterOperation<String>> personalId;
    private List<FilterOperation<String>> sedeNombre;
    private List<FilterOperation<String>> sedeAmbito;
    private List<FilterOperation<String>> personalNombreUsuario;
    private List<FilterOperation<String>> personalUsuario;
    private List<FilterOperation<String>> proveedorNombreFiscal;
    private List<FilterOperation<String>> proveedorNroDocumentoIdentidad;
    private List<FilterOperation<String>> proveedorEmail;
    private List<FilterOperation<String>> proveedorContactoPrincipal;
    private List<FilterOperation<String>> esvNombre;
    private List<FilterOperation<String>> ssoEstadoNombre;
    private List<FilterOperation<String>> ssoEstadoDescripcion;
    private List<FilterOperation<String>> caEstadoNombre;
    private List<FilterOperation<String>> caEstadoDescripcion;
    private List<FilterOperation<String>> tarDescripciones;
    private List<FilterOperation<Long>> nDias;
    private List<FilterOperation<Long>> nTrabajadores;
    private List<FilterOperation<String>> areasIds;
    private List<FilterOperation<String>> areasNombres;
    private List<FilterOperation<String>> zonasIds;
    private List<FilterOperation<String>> zonasNombres;

    private String nombreTrbj;
    private String nroDocTrbj;
    private String tipoDocTrbj;

}
