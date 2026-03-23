/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/mappers/Mapper.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.mappers;

import com.copeinca.apicopeincaprov.modules.core.models.dto.SvSolicitudVisitaZonaDTO;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvSolicitudVisitaZonaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface SvSolicitudVisitaZonaMapper {

    @Mappings({ @Mapping(source = "id", target = "id"), //
            @Mapping(source = "isActive", target = "isActive"), //
            @Mapping(source = "zonaId", target = "zonaId"), //
            @Mapping(source = "solicitudVisitaId", target = "solicitudVisitaId"), //
            @Mapping(source = "isDeleted", target = "isDeleted"), //
            @Mapping(source = "version", target = "version") //
    })
    SvSolicitudVisitaZonaEntity dtoToEntity(SvSolicitudVisitaZonaDTO dto);

    @Mappings({ @Mapping(source = "id", target = "id"), //
            @Mapping(source = "isActive", target = "isActive"), //
            @Mapping(source = "zonaId", target = "zonaId"), //
            @Mapping(source = "solicitudVisitaId", target = "solicitudVisitaId"), //
            @Mapping(source = "isDeleted", target = "isDeleted"), //
            @Mapping(source = "version", target = "version") //
    })
    SvSolicitudVisitaZonaDTO entityToDto(SvSolicitudVisitaZonaEntity entity);

}
