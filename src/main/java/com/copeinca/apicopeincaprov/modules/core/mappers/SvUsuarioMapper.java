/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/mappers/Mapper.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.mappers;

import com.copeinca.apicopeincaprov.modules.core.models.dto.SvUsuarioDTO;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvUsuarioEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface SvUsuarioMapper {

    @Mappings({ @Mapping(source = "id", target = "id"), //
            @Mapping(source = "nombre", target = "nombre"), //
            @Mapping(source = "apellidos", target = "apellidos"), //
            @Mapping(source = "email", target = "email"), //
            @Mapping(source = "identificadorExterno", target = "identificadorExterno"), //
            @Mapping(source = "isDeleted", target = "isDeleted"), //
            @Mapping(source = "version", target = "version") //
    })
    SvUsuarioEntity dtoToEntity(SvUsuarioDTO dto);

    @Mappings({ @Mapping(source = "id", target = "id"), //
            @Mapping(source = "nombre", target = "nombre"), //
            @Mapping(source = "apellidos", target = "apellidos"), //
            @Mapping(source = "email", target = "email"), //
            @Mapping(source = "identificadorExterno", target = "identificadorExterno"), //
            @Mapping(source = "isDeleted", target = "isDeleted"), //
            @Mapping(source = "version", target = "version") //
    })
    SvUsuarioDTO entityToDto(SvUsuarioEntity entity);

}
