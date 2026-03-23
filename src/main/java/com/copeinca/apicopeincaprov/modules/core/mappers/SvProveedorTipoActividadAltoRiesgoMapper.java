/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/mappers/Mapper.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.mappers;

import com.copeinca.apicopeincaprov.modules.core.models.dto.SvProveedorTipoActividadAltoRiesgoDTO;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvProveedorTipoActividadAltoRiesgoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface SvProveedorTipoActividadAltoRiesgoMapper {

    @Mappings({ @Mapping(source = "id", target = "id"), //
            @Mapping(source = "tipoActividadAltoRiesgoCode", target = "tipoActividadAltoRiesgoCode"), //
            @Mapping(source = "proveedorId", target = "proveedorId"), //
            @Mapping(source = "isActive", target = "isActive"), //
            @Mapping(source = "isDeleted", target = "isDeleted"), //
            @Mapping(source = "version", target = "version") //
    })
    SvProveedorTipoActividadAltoRiesgoEntity dtoToEntity(SvProveedorTipoActividadAltoRiesgoDTO dto);

    @Mappings({ @Mapping(source = "id", target = "id"), //
            @Mapping(source = "tipoActividadAltoRiesgoCode", target = "tipoActividadAltoRiesgoCode"), //
            @Mapping(source = "proveedorId", target = "proveedorId"), //
            @Mapping(source = "isActive", target = "isActive"), //
            @Mapping(source = "isDeleted", target = "isDeleted"), //
            @Mapping(source = "version", target = "version") //
    })
    SvProveedorTipoActividadAltoRiesgoDTO entityToDto(SvProveedorTipoActividadAltoRiesgoEntity entity);

}
