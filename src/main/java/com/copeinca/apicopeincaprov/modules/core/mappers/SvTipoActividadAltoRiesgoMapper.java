/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/mappers/Mapper.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.mappers;

import com.copeinca.apicopeincaprov.modules.core.models.dto.SvTipoActividadAltoRiesgoDTO;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvTipoActividadAltoRiesgoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface SvTipoActividadAltoRiesgoMapper {

    @Mappings({ @Mapping(source = "id", target = "id"), //
            @Mapping(source = "description", target = "description"), //
            @Mapping(source = "isActive", target = "isActive"), //
            @Mapping(source = "isDeleted", target = "isDeleted"), //
            @Mapping(source = "version", target = "version") //
    })
    SvTipoActividadAltoRiesgoEntity dtoToEntity(SvTipoActividadAltoRiesgoDTO dto);

    @Mappings({ @Mapping(source = "id", target = "id"), //
            @Mapping(source = "description", target = "description"), //
            @Mapping(source = "isActive", target = "isActive"), //
            @Mapping(source = "isDeleted", target = "isDeleted"), //
            @Mapping(source = "version", target = "version") //
    })
    SvTipoActividadAltoRiesgoDTO entityToDto(SvTipoActividadAltoRiesgoEntity entity);

}
