/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/mappers/Mapper.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.mappers;

import com.copeinca.apicopeincaprov.modules.core.models.dto.SvSolicitudVisitaAreaAutorizadoraDTO;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvSolicitudVisitaAreaAutorizadoraEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface SvSolicitudVisitaAreaAutorizadoraMapper {

    @Mappings({ @Mapping(source = "id", target = "id"), //
            @Mapping(source = "isActive", target = "isActive"), //
            @Mapping(source = "solicitudVisitaId", target = "solicitudVisitaId"), //
            @Mapping(source = "areaAutorizadoraId", target = "areaAutorizadoraId"), //
            @Mapping(source = "isDeleted", target = "isDeleted"), //
            @Mapping(source = "version", target = "version") //
    })
    SvSolicitudVisitaAreaAutorizadoraEntity dtoToEntity(SvSolicitudVisitaAreaAutorizadoraDTO dto);

    @Mappings({ @Mapping(source = "id", target = "id"), //
            @Mapping(source = "isActive", target = "isActive"), //
            @Mapping(source = "solicitudVisitaId", target = "solicitudVisitaId"), //
            @Mapping(source = "areaAutorizadoraId", target = "areaAutorizadoraId"), //
            @Mapping(source = "isDeleted", target = "isDeleted"), //
            @Mapping(source = "version", target = "version") //
    })
    SvSolicitudVisitaAreaAutorizadoraDTO entityToDto(SvSolicitudVisitaAreaAutorizadoraEntity entity);

}
