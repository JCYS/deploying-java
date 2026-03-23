/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/mappers/Mapper.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.mappers;

import com.copeinca.apicopeincaprov.modules.core.models.dto.SvParametroDTO;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvParametroEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface SvParametroMapper {

    @Mappings({ @Mapping(source = "id", target = "id"), //
            @Mapping(source = "description", target = "description"), //
            @Mapping(source = "name", target = "name"), //
            @Mapping(source = "valor", target = "valor"), //
            @Mapping(source = "isDeleted", target = "isDeleted"), //
            @Mapping(source = "version", target = "version") //
    })
    SvParametroEntity dtoToEntity(SvParametroDTO dto);

    @Mappings({ @Mapping(source = "id", target = "id"), //
            @Mapping(source = "description", target = "description"), //
            @Mapping(source = "name", target = "name"), //
            @Mapping(source = "valor", target = "valor"), //
            @Mapping(source = "isDeleted", target = "isDeleted"), //
            @Mapping(source = "version", target = "version") //
    })
    SvParametroDTO entityToDto(SvParametroEntity entity);

}
