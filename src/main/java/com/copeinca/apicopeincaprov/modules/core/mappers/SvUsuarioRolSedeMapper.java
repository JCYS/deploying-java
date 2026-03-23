/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/mappers/Mapper.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.mappers;

import com.copeinca.apicopeincaprov.modules.core.models.dto.SvUsuarioRolSedeDTO;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvUsuarioRolSedeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface SvUsuarioRolSedeMapper {

    @Mappings({ @Mapping(source = "id", target = "id"), //
            @Mapping(source = "usuarioId", target = "usuarioId"), //
            @Mapping(source = "rolCode", target = "rolCode"), //
            @Mapping(source = "sedeId", target = "sedeId"), //
            @Mapping(source = "isActive", target = "isActive"), //
            @Mapping(source = "isDeleted", target = "isDeleted"), //
            @Mapping(source = "version", target = "version") //
    })
    SvUsuarioRolSedeEntity dtoToEntity(SvUsuarioRolSedeDTO dto);

    @Mappings({ @Mapping(source = "id", target = "id"), //
            @Mapping(source = "usuarioId", target = "usuarioId"), //
            @Mapping(source = "rolCode", target = "rolCode"), //
            @Mapping(source = "sedeId", target = "sedeId"), //
            @Mapping(source = "isActive", target = "isActive"), //
            @Mapping(source = "isDeleted", target = "isDeleted"), //
            @Mapping(source = "version", target = "version"), //
            @Mapping(source = "usuarioIDEntity.email", target = "emailUser") //
    })
    SvUsuarioRolSedeDTO entityToDto(SvUsuarioRolSedeEntity entity);

}
