/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/mappers/Mapper.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.mappers;

import com.copeinca.apicopeincaprov.modules.core.models.dto.SvSolicitudVisitaHistorialDTO;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvSolicitudVisitaHistorialEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface SvSolicitudVisitaHistorialMapper {

    @Mappings({ @Mapping(source = "id", target = "id"), //
            @Mapping(source = "solicitudVisitaId", target = "solicitudVisitaId"), //
            @Mapping(source = "estadoSolicitudVisitaCode", target = "estadoSolicitudVisitaCode"), //
            @Mapping(source = "fechaHora", target = "fechaHora"), //
            @Mapping(source = "usuario", target = "usuario"), //
            @Mapping(source = "ambito", target = "ambito"), //
            @Mapping(source = "revision", target = "revision"), //
            @Mapping(source = "rptaByUsuario", target = "rptaByUsuario"), //
            @Mapping(source = "isDeleted", target = "isDeleted"), //
            @Mapping(source = "version", target = "version") //
    })
    SvSolicitudVisitaHistorialEntity dtoToEntity(SvSolicitudVisitaHistorialDTO dto);

    @Mappings({ @Mapping(source = "id", target = "id"), //
            @Mapping(source = "solicitudVisitaId", target = "solicitudVisitaId"), //
            @Mapping(source = "estadoSolicitudVisitaCode", target = "estadoSolicitudVisitaCode"), //
            @Mapping(source = "fechaHora", target = "fechaHora"), //
            @Mapping(source = "usuario", target = "usuario"), //
            @Mapping(source = "ambito", target = "ambito"), //
            @Mapping(source = "revision", target = "revision"), //
            @Mapping(source = "rptaByUsuario", target = "rptaByUsuario"), //
            @Mapping(source = "isDeleted", target = "isDeleted"), //
            @Mapping(source = "version", target = "version") //
    })
    SvSolicitudVisitaHistorialDTO entityToDto(SvSolicitudVisitaHistorialEntity entity);

}
