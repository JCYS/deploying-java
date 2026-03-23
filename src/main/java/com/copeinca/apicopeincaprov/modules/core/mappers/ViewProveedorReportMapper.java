/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/mappers/Mapper.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.mappers;

import com.copeinca.apicopeincaprov.modules.core.models.dto.ViewProveedorReportDTO;
import com.copeinca.apicopeincaprov.modules.core.models.views.ViewProveedorReportView;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ViewProveedorReportMapper {

    @Mappings({ @Mapping(source = "id", target = "id"), //
            @Mapping(source = "nroDocumentoIdentidad", target = "nroDocumentoIdentidad"), //
            @Mapping(source = "nombreFiscal", target = "nombreFiscal"), //
            @Mapping(source = "email", target = "email"), //
            @Mapping(source = "direccion", target = "direccion"), //
            @Mapping(source = "telefono", target = "telefono"), //
            @Mapping(source = "nacional", target = "nacional"), //
            @Mapping(source = "contactoPrincipal", target = "contactoPrincipal"), //
            @Mapping(source = "contactoPrincipalTelefono", target = "contactoPrincipalTelefono"), //
            @Mapping(source = "origenRegistro", target = "origenRegistro"), //
            @Mapping(source = "sedesIds", target = "sedesIds"), //
            @Mapping(source = "sedesNombres", target = "sedesNombres"), //
            @Mapping(source = "estadoSsoCode", target = "estadoSsoCode"), //
            @Mapping(source = "ssoTieneVigencia", target = "ssoTieneVigencia"), //
            @Mapping(source = "ssoFechaInicioVigencia", target = "ssoFechaInicioVigencia"), //
            @Mapping(source = "ssoFechaFinVigencia", target = "ssoFechaFinVigencia"), //
            @Mapping(source = "ssoMotivo", target = "ssoMotivo"), //
            @Mapping(source = "ssoUsuarioEvaluador", target = "ssoUsuarioEvaluador"), //
            @Mapping(source = "ssoFechaEvaluacion", target = "ssoFechaEvaluacion"), //
            @Mapping(source = "ssoEstadoNombre", target = "ssoEstadoNombre"), //
            @Mapping(source = "ssoEstadoDescripcion", target = "ssoEstadoDescripcion"), //
            @Mapping(source = "ssoEstadoVigente", target = "ssoEstadoVigente"), //
            @Mapping(source = "estadoCalidadAmbientalCode", target = "estadoCalidadAmbientalCode"), //
            @Mapping(source = "caTieneVigencia", target = "caTieneVigencia"), //
            @Mapping(source = "caFechaInicioVigencia", target = "caFechaInicioVigencia"), //
            @Mapping(source = "caFechaFinVigencia", target = "caFechaFinVigencia"), //
            @Mapping(source = "caMotivo", target = "caMotivo"), //
            @Mapping(source = "caUsuarioEvaluador", target = "caUsuarioEvaluador"), //
            @Mapping(source = "caFechaEvaluacion", target = "caFechaEvaluacion"), //
            @Mapping(source = "caEstadoNombre", target = "caEstadoNombre"), //
            @Mapping(source = "caEstadoDescripcion", target = "caEstadoDescripcion"), //
            @Mapping(source = "caEstadoVigente", target = "caEstadoVigente"), //
            @Mapping(source = "NTrabajadoresAptos", target = "nTrabajadoresAptos"), //
            @Mapping(source = "NTrabajadoresTotales", target = "nTrabajadoresTotales"), //
            @Mapping(source = "NDocsProvSso", target = "nDocsProvSso"), //
            @Mapping(source = "NDocsCa", target = "nDocsCa"), //
            @Mapping(source = "NDocsAar", target = "nDocsAar"), //
            @Mapping(source = "NDocsAb", target = "nDocsAb"), //
            @Mapping(source = "NDocsProvSsoValid", target = "nDocsProvSsoValid"), //
            @Mapping(source = "NDocsCaValid", target = "nDocsCaValid"), //
            @Mapping(source = "NDocsAarValid", target = "nDocsAarValid"), //
            @Mapping(source = "NDocsAbValid", target = "nDocsAbValid"), //
            @Mapping(source = "NDocsTrabajSso", target = "nDocsTrabajSso"), //
            @Mapping(source = "indRealizaActividadAltoRiesgo", target = "indRealizaActividadAltoRiesgo"), //
            @Mapping(source = "indRealizaActividadABordo", target = "indRealizaActividadABordo"), //
            @Mapping(source = "tarCodigos", target = "tarCodigos"), //
            @Mapping(source = "tarDescripciones", target = "tarDescripciones")
    })
    ViewProveedorReportView dtoToEntity(ViewProveedorReportDTO dto);

    @Mappings({ @Mapping(source = "id", target = "id"), //
            @Mapping(source = "nroDocumentoIdentidad", target = "nroDocumentoIdentidad"), //
            @Mapping(source = "nombreFiscal", target = "nombreFiscal"), //
            @Mapping(source = "email", target = "email"), //
            @Mapping(source = "direccion", target = "direccion"), //
            @Mapping(source = "telefono", target = "telefono"), //
            @Mapping(source = "nacional", target = "nacional"), //
            @Mapping(source = "contactoPrincipal", target = "contactoPrincipal"), //
            @Mapping(source = "contactoPrincipalTelefono", target = "contactoPrincipalTelefono"), //
            @Mapping(source = "origenRegistro", target = "origenRegistro"), //
            @Mapping(source = "sedesIds", target = "sedesIds"), //
            @Mapping(source = "sedesNombres", target = "sedesNombres"), //
            @Mapping(source = "estadoSsoCode", target = "estadoSsoCode"), //
            @Mapping(source = "ssoTieneVigencia", target = "ssoTieneVigencia"), //
            @Mapping(source = "ssoFechaInicioVigencia", target = "ssoFechaInicioVigencia"), //
            @Mapping(source = "ssoFechaFinVigencia", target = "ssoFechaFinVigencia"), //
            @Mapping(source = "ssoMotivo", target = "ssoMotivo"), //
            @Mapping(source = "ssoUsuarioEvaluador", target = "ssoUsuarioEvaluador"), //
            @Mapping(source = "ssoFechaEvaluacion", target = "ssoFechaEvaluacion"), //
            @Mapping(source = "ssoEstadoNombre", target = "ssoEstadoNombre"), //
            @Mapping(source = "ssoEstadoDescripcion", target = "ssoEstadoDescripcion"), //
            @Mapping(source = "ssoEstadoVigente", target = "ssoEstadoVigente"), //
            @Mapping(source = "estadoCalidadAmbientalCode", target = "estadoCalidadAmbientalCode"), //
            @Mapping(source = "caTieneVigencia", target = "caTieneVigencia"), //
            @Mapping(source = "caFechaInicioVigencia", target = "caFechaInicioVigencia"), //
            @Mapping(source = "caFechaFinVigencia", target = "caFechaFinVigencia"), //
            @Mapping(source = "caMotivo", target = "caMotivo"), //
            @Mapping(source = "caUsuarioEvaluador", target = "caUsuarioEvaluador"), //
            @Mapping(source = "caFechaEvaluacion", target = "caFechaEvaluacion"), //
            @Mapping(source = "caEstadoNombre", target = "caEstadoNombre"), //
            @Mapping(source = "caEstadoDescripcion", target = "caEstadoDescripcion"), //
            @Mapping(source = "caEstadoVigente", target = "caEstadoVigente"), //
            @Mapping(source = "NTrabajadoresAptos", target = "nTrabajadoresAptos"), //
            @Mapping(source = "NTrabajadoresTotales", target = "nTrabajadoresTotales"), //
            @Mapping(source = "NDocsProvSso", target = "nDocsProvSso"), //
            @Mapping(source = "NDocsCa", target = "nDocsCa"), //
            @Mapping(source = "NDocsAar", target = "nDocsAar"), //
            @Mapping(source = "NDocsAb", target = "nDocsAb"), //
            @Mapping(source = "NDocsProvSsoValid", target = "nDocsProvSsoValid"), //
            @Mapping(source = "NDocsCaValid", target = "nDocsCaValid"), //
            @Mapping(source = "NDocsAarValid", target = "nDocsAarValid"), //
            @Mapping(source = "NDocsAbValid", target = "nDocsAbValid"), //
            @Mapping(source = "NDocsTrabajSso", target = "nDocsTrabajSso"), //
            @Mapping(source = "indRealizaActividadAltoRiesgo", target = "indRealizaActividadAltoRiesgo"), //
            @Mapping(source = "indRealizaActividadABordo", target = "indRealizaActividadABordo"), //
            @Mapping(source = "tarCodigos", target = "tarCodigos"), //
            @Mapping(source = "tarDescripciones", target = "tarDescripciones"), //
            @Mapping(source = "createdDate", target = "createdDate"),
            @Mapping(source = "notificaCa", target = "notificaCa"), //
            @Mapping(source = "notificaSso", target = "notificaSso")
    })
    ViewProveedorReportDTO entityToDto(ViewProveedorReportView entity);

}
