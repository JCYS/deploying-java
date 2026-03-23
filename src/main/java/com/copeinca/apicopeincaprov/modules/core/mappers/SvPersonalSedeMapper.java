/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/mappers/Mapper.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.mappers;

import com.copeinca.apicopeincaprov.modules.core.models.dto.SvPersonalSedeDTO;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvPersonalSedeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface SvPersonalSedeMapper {

    @Mappings({ @Mapping(source = "id", target = "id"), //
            @Mapping(source = "personalId", target = "personalId"), //
            @Mapping(source = "sedeId", target = "sedeId"), //
            @Mapping(source = "isActive", target = "isActive"), //
            @Mapping(source = "isDeleted", target = "isDeleted"), //
            @Mapping(source = "version", target = "version") //
    })
    SvPersonalSedeEntity dtoToEntity(SvPersonalSedeDTO dto);

    @Mappings({ @Mapping(source = "id", target = "id"), //
            @Mapping(source = "personalId", target = "personalId"), //
            @Mapping(source = "sedeId", target = "sedeId"), //
            @Mapping(source = "isActive", target = "isActive"), //
            @Mapping(source = "isDeleted", target = "isDeleted"), //
            @Mapping(source = "version", target = "version") //
    })
    SvPersonalSedeDTO entityToDto(SvPersonalSedeEntity entity);

}
