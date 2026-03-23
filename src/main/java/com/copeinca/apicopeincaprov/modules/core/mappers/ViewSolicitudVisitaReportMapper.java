/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/mappers/Mapper.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.mappers;

import com.copeinca.apicopeincaprov.modules.core.models.dto.ViewSolicitudVisitaReportDTO;
import com.copeinca.apicopeincaprov.modules.core.models.views.ViewSolicitudVisitaReportView;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ViewSolicitudVisitaReportMapper {

    @Mappings({ @Mapping(source = "id", target = "id"), //
            @Mapping(source = "codigoVisita", target = "codigoVisita"), //
            @Mapping(source = "codigoSistemaExterno", target = "codigoSistemaExterno"), //
            @Mapping(source = "fechaInicio", target = "fechaInicio"), //
            @Mapping(source = "fechaFin", target = "fechaFin"), //
            @Mapping(source = "motivoVisita", target = "motivoVisita"), //
            @Mapping(source = "fechaAprobacionPersonaVisitada", target = "fechaAprobacionPersonaVisitada"), //
            @Mapping(source = "fechaObservacionAdministrador", target = "fechaObservacionAdministrador"), //
            @Mapping(source = "observaciones", target = "observaciones"), //
            @Mapping(source = "nroOrdenServicio", target = "nroOrdenServicio"), //
            @Mapping(source = "indActividadAltoRiesgo", target = "indActividadAltoRiesgo"), //
            @Mapping(source = "indActividadABordo", target = "indActividadABordo"), //
            @Mapping(source = "indRequiereAndamios", target = "indRequiereAndamios"), //
            @Mapping(source = "indRequiereGrua", target = "indRequiereGrua"), //
            @Mapping(source = "indRequiereEquiposMoviles", target = "indRequiereEquiposMoviles"), //
            @Mapping(source = "indTrabajoBuceo", target = "indTrabajoBuceo"), //
            @Mapping(source = "ssoMotivo", target = "ssoMotivo"), //
            @Mapping(source = "ssoDescargo", target = "ssoDescargo"), //
            @Mapping(source = "caMotivo", target = "caMotivo"), //
            @Mapping(source = "caDescargo", target = "caDescargo"), //
            @Mapping(source = "estadoSsoCode", target = "estadoSsoCode"), //
            @Mapping(source = "estadoCalidadAmbientalCode", target = "estadoCalidadAmbientalCode"), //
            @Mapping(source = "estadoSolicitudVisitaCode", target = "estadoSolicitudVisitaCode"), //
            @Mapping(source = "sedeId", target = "sedeId"), //
            @Mapping(source = "proveedorId", target = "proveedorId"), //
            @Mapping(source = "personalId", target = "personalId"), //
            @Mapping(source = "sedeNombre", target = "sedeNombre"), //
            @Mapping(source = "sedeAmbito", target = "sedeAmbito"), //
            @Mapping(source = "personalNombreUsuario", target = "personalNombreUsuario"), //
            @Mapping(source = "personalUsuario", target = "personalUsuario"), //
            @Mapping(source = "proveedorNombreFiscal", target = "proveedorNombreFiscal"), //
            @Mapping(source = "proveedorNroDocumentoIdentidad", target = "proveedorNroDocumentoIdentidad"), //
            @Mapping(source = "proveedorEmail", target = "proveedorEmail"), //
            @Mapping(source = "proveedorContactoPrincipal", target = "proveedorContactoPrincipal"), //
            @Mapping(source = "esvNombre", target = "esvNombre"), //
            @Mapping(source = "ssoEstadoNombre", target = "ssoEstadoNombre"), //
            @Mapping(source = "ssoEstadoDescripcion", target = "ssoEstadoDescripcion"), //
            @Mapping(source = "caEstadoNombre", target = "caEstadoNombre"), //
            @Mapping(source = "caEstadoDescripcion", target = "caEstadoDescripcion"), //
            @Mapping(source = "tarDescripciones", target = "tarDescripciones"), //
            @Mapping(source = "NDias", target = "nDias"), //
            @Mapping(source = "NTrabajadores", target = "nTrabajadores"), //
            @Mapping(source = "areasIds", target = "areasIds"), //
            @Mapping(source = "areasNombres", target = "areasNombres"), //
            @Mapping(source = "zonasIds", target = "zonasIds"), //
            @Mapping(source = "zonasNombres", target = "zonasNombres"), //
            @Mapping(source = "contactoPrincipalTelefono", target = "contactoPrincipalTelefono"), //
    })
    ViewSolicitudVisitaReportView dtoToEntity(ViewSolicitudVisitaReportDTO dto);

    @Mappings({ @Mapping(source = "id", target = "id"), //
            @Mapping(source = "codigoVisita", target = "codigoVisita"), //
            @Mapping(source = "codigoSistemaExterno", target = "codigoSistemaExterno"), //
            @Mapping(source = "fechaInicio", target = "fechaInicio"), //
            @Mapping(source = "fechaFin", target = "fechaFin"), //
            @Mapping(source = "motivoVisita", target = "motivoVisita"), //
            @Mapping(source = "fechaAprobacionPersonaVisitada", target = "fechaAprobacionPersonaVisitada"), //
            @Mapping(source = "fechaObservacionAdministrador", target = "fechaObservacionAdministrador"), //
            @Mapping(source = "observaciones", target = "observaciones"), //
            @Mapping(source = "nroOrdenServicio", target = "nroOrdenServicio"), //
            @Mapping(source = "indActividadAltoRiesgo", target = "indActividadAltoRiesgo"), //
            @Mapping(source = "indActividadABordo", target = "indActividadABordo"), //
            @Mapping(source = "indRequiereAndamios", target = "indRequiereAndamios"), //
            @Mapping(source = "indRequiereGrua", target = "indRequiereGrua"), //
            @Mapping(source = "indRequiereEquiposMoviles", target = "indRequiereEquiposMoviles"), //
            @Mapping(source = "indTrabajoBuceo", target = "indTrabajoBuceo"), //
            @Mapping(source = "ssoMotivo", target = "ssoMotivo"), //
            @Mapping(source = "ssoDescargo", target = "ssoDescargo"), //
            @Mapping(source = "caMotivo", target = "caMotivo"), //
            @Mapping(source = "caDescargo", target = "caDescargo"), //
            @Mapping(source = "estadoSsoCode", target = "estadoSsoCode"), //
            @Mapping(source = "estadoCalidadAmbientalCode", target = "estadoCalidadAmbientalCode"), //
            @Mapping(source = "estadoSolicitudVisitaCode", target = "estadoSolicitudVisitaCode"), //
            @Mapping(source = "sedeId", target = "sedeId"), //
            @Mapping(source = "proveedorId", target = "proveedorId"), //
            @Mapping(source = "personalId", target = "personalId"), //
            @Mapping(source = "sedeNombre", target = "sedeNombre"), //
            @Mapping(source = "sedeAmbito", target = "sedeAmbito"), //
            @Mapping(source = "personalNombreUsuario", target = "personalNombreUsuario"), //
            @Mapping(source = "personalUsuario", target = "personalUsuario"), //
            @Mapping(source = "proveedorNombreFiscal", target = "proveedorNombreFiscal"), //
            @Mapping(source = "proveedorNroDocumentoIdentidad", target = "proveedorNroDocumentoIdentidad"), //
            @Mapping(source = "proveedorEmail", target = "proveedorEmail"), //
            @Mapping(source = "proveedorContactoPrincipal", target = "proveedorContactoPrincipal"), //
            @Mapping(source = "esvNombre", target = "esvNombre"), //
            @Mapping(source = "ssoEstadoNombre", target = "ssoEstadoNombre"), //
            @Mapping(source = "ssoEstadoDescripcion", target = "ssoEstadoDescripcion"), //
            @Mapping(source = "caEstadoNombre", target = "caEstadoNombre"), //
            @Mapping(source = "caEstadoDescripcion", target = "caEstadoDescripcion"), //
            @Mapping(source = "tarDescripciones", target = "tarDescripciones"), //
            @Mapping(source = "NDias", target = "nDias"), //
            @Mapping(source = "NTrabajadores", target = "nTrabajadores"), //
            @Mapping(source = "areasIds", target = "areasIds"), //
            @Mapping(source = "areasNombres", target = "areasNombres"), //
            @Mapping(source = "zonasIds", target = "zonasIds"), //
            @Mapping(source = "zonasNombres", target = "zonasNombres"), //
            @Mapping(source = "contactoPrincipalTelefono", target = "contactoPrincipalTelefono"), //
    })
    ViewSolicitudVisitaReportDTO entityToDto(ViewSolicitudVisitaReportView entity);

}
