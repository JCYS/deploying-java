/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/mappers/Mapper.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.mappers;

import com.copeinca.apicopeincaprov.modules.core.models.dto.SvSolicitudVisitaMaterialDTO;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvSolicitudVisitaMaterialEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface SvSolicitudVisitaMaterialMapper {

    @Mappings({ @Mapping(source = "id", target = "id"), //
            @Mapping(source = "descripcion", target = "descripcion"), //
            @Mapping(source = "cantidad", target = "cantidad"), //
            @Mapping(source = "unidadMedida", target = "unidadMedida"), //
            @Mapping(source = "solicitudVisitaId", target = "solicitudVisitaId"), //
            @Mapping(source = "isDeleted", target = "isDeleted"), //
            @Mapping(source = "version", target = "version") //
    })
    SvSolicitudVisitaMaterialEntity dtoToEntity(SvSolicitudVisitaMaterialDTO dto);

    @Mappings({ @Mapping(source = "id", target = "id"), //
            @Mapping(source = "descripcion", target = "descripcion"), //
            @Mapping(source = "cantidad", target = "cantidad"), //
            @Mapping(source = "unidadMedida", target = "unidadMedida"), //
            @Mapping(source = "solicitudVisitaId", target = "solicitudVisitaId"), //
            @Mapping(source = "isDeleted", target = "isDeleted"), //
            @Mapping(source = "version", target = "version") //
    })
    SvSolicitudVisitaMaterialDTO entityToDto(SvSolicitudVisitaMaterialEntity entity);

}
