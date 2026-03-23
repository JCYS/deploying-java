/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/mappers/Mapper.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.mappers;

import com.copeinca.apicopeincaprov.modules.core.models.dto.SvZonaDTO;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvZonaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface SvZonaMapper {

    @Mappings({ @Mapping(source = "id", target = "id"), //
            @Mapping(source = "name", target = "name"), //
            @Mapping(source = "operativa", target = "operativa"), //
            @Mapping(source = "sedeId", target = "sedeId"), //
            @Mapping(source = "isDeleted", target = "isDeleted"), //
            @Mapping(source = "version", target = "version") //
    })
    SvZonaEntity dtoToEntity(SvZonaDTO dto);

    @Mappings({ @Mapping(source = "id", target = "id"), //
            @Mapping(source = "name", target = "name"), //
            @Mapping(source = "operativa", target = "operativa"), //
            @Mapping(source = "sedeId", target = "sedeId"), //
            @Mapping(source = "isDeleted", target = "isDeleted"), //
            @Mapping(source = "version", target = "version") //
    })
    SvZonaDTO entityToDto(SvZonaEntity entity);

}
