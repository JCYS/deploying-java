/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/mappers/Mapper.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.mappers;

import com.copeinca.apicopeincaprov.modules.core.models.dto.SvRolDTO;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvRolEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface SvRolMapper {

    @Mappings({ @Mapping(source = "id", target = "id"), //
            @Mapping(source = "rolName", target = "rolName"), //
            @Mapping(source = "rolDescription", target = "rolDescription"), //
            @Mapping(source = "isDeleted", target = "isDeleted"), //
            @Mapping(source = "version", target = "version") //
    })
    SvRolEntity dtoToEntity(SvRolDTO dto);

    @Mappings({ @Mapping(source = "id", target = "id"), //
            @Mapping(source = "rolName", target = "rolName"), //
            @Mapping(source = "rolDescription", target = "rolDescription"), //
            @Mapping(source = "isDeleted", target = "isDeleted"), //
            @Mapping(source = "version", target = "version") //
    })
    SvRolDTO entityToDto(SvRolEntity entity);

}
