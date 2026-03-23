/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/mappers/Mapper.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.mappers;

import com.copeinca.apicopeincaprov.modules.core.models.dto.SvTipoDocumentoIdentidadDTO;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvTipoDocumentoIdentidadEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface SvTipoDocumentoIdentidadMapper {

    @Mappings({ @Mapping(source = "id", target = "id"), //
            @Mapping(source = "name", target = "name"), //
            @Mapping(source = "isActive", target = "isActive"), //
            @Mapping(source = "expresionRegular", target = "expresionRegular"), //
            @Mapping(source = "isDeleted", target = "isDeleted"), //
            @Mapping(source = "version", target = "version") //
    })
    SvTipoDocumentoIdentidadEntity dtoToEntity(SvTipoDocumentoIdentidadDTO dto);

    @Mappings({ @Mapping(source = "id", target = "id"), //
            @Mapping(source = "name", target = "name"), //
            @Mapping(source = "isActive", target = "isActive"), //
            @Mapping(source = "expresionRegular", target = "expresionRegular"), //
            @Mapping(source = "isDeleted", target = "isDeleted"), //
            @Mapping(source = "version", target = "version") //
    })
    SvTipoDocumentoIdentidadDTO entityToDto(SvTipoDocumentoIdentidadEntity entity);

}
