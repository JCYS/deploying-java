/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/mappers/Mapper.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.mappers;

import com.copeinca.apicopeincaprov.modules.core.models.dto.SvTrabajadorTipoActividadAltoRiesgoDTO;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvTrabajadorTipoActividadAltoRiesgoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface SvTrabajadorTipoActividadAltoRiesgoMapper {

    @Mappings({ @Mapping(source = "id", target = "id"), //
            @Mapping(source = "trabajadorId", target = "trabajadorId"), //
            @Mapping(source = "tipoActividadAltoRiesgoCode", target = "tipoActividadAltoRiesgoCode"), //
            @Mapping(source = "isActive", target = "isActive"), //
            @Mapping(source = "isDeleted", target = "isDeleted"), //
            @Mapping(source = "version", target = "version") //
    })
    SvTrabajadorTipoActividadAltoRiesgoEntity dtoToEntity(SvTrabajadorTipoActividadAltoRiesgoDTO dto);

    @Mappings({ @Mapping(source = "id", target = "id"), //
            @Mapping(source = "trabajadorId", target = "trabajadorId"), //
            @Mapping(source = "tipoActividadAltoRiesgoCode", target = "tipoActividadAltoRiesgoCode"), //
            @Mapping(source = "isActive", target = "isActive"), //
            @Mapping(source = "isDeleted", target = "isDeleted"), //
            @Mapping(source = "version", target = "version"), //
            @Mapping(source = "tipoActividadAltoRiesgoCodeEntity.description", target = "description") //
    })
    SvTrabajadorTipoActividadAltoRiesgoDTO entityToDto(SvTrabajadorTipoActividadAltoRiesgoEntity entity);

}
