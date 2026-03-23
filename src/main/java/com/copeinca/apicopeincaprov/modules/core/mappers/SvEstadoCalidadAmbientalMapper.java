/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/mappers/Mapper.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.mappers;

import com.copeinca.apicopeincaprov.modules.core.models.dto.SvEstadoCalidadAmbientalDTO;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvEstadoCalidadAmbientalEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface SvEstadoCalidadAmbientalMapper {

    @Mappings({ @Mapping(source = "id", target = "id"), //
            @Mapping(source = "name", target = "name"), //
            @Mapping(source = "description", target = "description"), //
            @Mapping(source = "isDeleted", target = "isDeleted"), //
            @Mapping(source = "version", target = "version") //
    })
    SvEstadoCalidadAmbientalEntity dtoToEntity(SvEstadoCalidadAmbientalDTO dto);

    @Mappings({ @Mapping(source = "id", target = "id"), //
            @Mapping(source = "name", target = "name"), //
            @Mapping(source = "description", target = "description"), //
            @Mapping(source = "isDeleted", target = "isDeleted"), //
            @Mapping(source = "version", target = "version") //
    })
    SvEstadoCalidadAmbientalDTO entityToDto(SvEstadoCalidadAmbientalEntity entity);

}
