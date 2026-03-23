/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/mappers/Mapper.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.mappers;

import com.copeinca.apicopeincaprov.modules.core.models.dto.ViewSolicitudVisitaHistorialReportDTO;
import com.copeinca.apicopeincaprov.modules.core.models.views.ViewSolicitudVisitaHistorialReportView;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ViewSolicitudVisitaHistorialReportMapper {

    @Mappings({ @Mapping(source = "id", target = "id"), //
            @Mapping(source = "fechaHora", target = "fechaHora"), //
            @Mapping(source = "usuario", target = "usuario"), //
            @Mapping(source = "ambito", target = "ambito"), //
            @Mapping(source = "revision", target = "revision"), //
            @Mapping(source = "rptaByUsuario", target = "rptaByUsuario"), //
            @Mapping(source = "solicitudVisitaId", target = "solicitudVisitaId"), //
            @Mapping(source = "estadoSolicitudVisitaCode", target = "estadoSolicitudVisitaCode"), //
            @Mapping(source = "solvisNroOrdenServicio", target = "solvisNroOrdenServicio"), //
            @Mapping(source = "solvisCodigoVisita", target = "solvisCodigoVisita"), //
            @Mapping(source = "esvNombre", target = "esvNombre"), //
            @Mapping(source = "prevEstadoSolicitudVisitaCode", target = "prevEstadoSolicitudVisitaCode"), //
            @Mapping(source = "prevEstadoNombre", target = "prevEstadoNombre"), //
            @Mapping(source = "prevFechaHora", target = "prevFechaHora") //
    })
    ViewSolicitudVisitaHistorialReportView dtoToEntity(ViewSolicitudVisitaHistorialReportDTO dto);

    @Mappings({ @Mapping(source = "id", target = "id"), //
            @Mapping(source = "fechaHora", target = "fechaHora"), //
            @Mapping(source = "usuario", target = "usuario"), //
            @Mapping(source = "ambito", target = "ambito"), //
            @Mapping(source = "revision", target = "revision"), //
            @Mapping(source = "rptaByUsuario", target = "rptaByUsuario"), //
            @Mapping(source = "solicitudVisitaId", target = "solicitudVisitaId"), //
            @Mapping(source = "estadoSolicitudVisitaCode", target = "estadoSolicitudVisitaCode"), //
            @Mapping(source = "solvisNroOrdenServicio", target = "solvisNroOrdenServicio"), //
            @Mapping(source = "solvisCodigoVisita", target = "solvisCodigoVisita"), //
            @Mapping(source = "esvNombre", target = "esvNombre"), //
            @Mapping(source = "prevEstadoSolicitudVisitaCode", target = "prevEstadoSolicitudVisitaCode"), //
            @Mapping(source = "prevEstadoNombre", target = "prevEstadoNombre"), //
            @Mapping(source = "prevFechaHora", target = "prevFechaHora") //
    })
    ViewSolicitudVisitaHistorialReportDTO entityToDto(ViewSolicitudVisitaHistorialReportView entity);

}
