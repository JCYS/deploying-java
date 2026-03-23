/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/mappers/Mapper.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.mappers;

import com.copeinca.apicopeincaprov.modules.core.models.dto.SvSolicitudVisistaDTO;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvSolicitudVisistaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface SvSolicitudVisistaMapper {

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
            @Mapping(source = "isDeleted", target = "isDeleted"), //
            @Mapping(source = "version", target = "version"), //
            @Mapping(source = "correlativo", target = "correlativo")
    })
    SvSolicitudVisistaEntity dtoToEntity(SvSolicitudVisistaDTO dto);

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
            @Mapping(source = "isDeleted", target = "isDeleted"), //
            @Mapping(source = "version", target = "version") //
            //@Mapping(source = "correlativo", target = "correlativo")
    })
    SvSolicitudVisistaDTO entityToDto(SvSolicitudVisistaEntity entity);

}
