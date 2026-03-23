/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/mappers/Mapper.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.mappers;

import com.copeinca.apicopeincaprov.modules.core.models.dto.SvAreaDTO;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvAreaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface SvAreaMapper {

    @Mappings({ @Mapping(source = "id", target = "id"), //
            @Mapping(source = "name", target = "name"), //
            @Mapping(source = "ambito", target = "ambito"), //
            @Mapping(source = "operativa", target = "operativa"), //
            @Mapping(source = "sedeId", target = "sedeId"), //
            @Mapping(source = "isDeleted", target = "isDeleted"), //
            @Mapping(source = "version", target = "version") //
    })
    SvAreaEntity dtoToEntity(SvAreaDTO dto);

    @Mappings({ @Mapping(source = "id", target = "id"), //
            @Mapping(source = "name", target = "name"), //
            @Mapping(source = "ambito", target = "ambito"), //
            @Mapping(source = "operativa", target = "operativa"), //
            @Mapping(source = "sedeId", target = "sedeId"), //
            @Mapping(source = "isDeleted", target = "isDeleted"), //
            @Mapping(source = "version", target = "version") //
    })
    SvAreaDTO entityToDto(SvAreaEntity entity);

}
