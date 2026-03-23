/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/mappers/Mapper.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.mappers;

import com.copeinca.apicopeincaprov.modules.core.models.dto.SvProveedorSedeDTO;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvProveedorSedeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface SvProveedorSedeMapper {

    @Mappings({ @Mapping(source = "id", target = "id"), //
            @Mapping(source = "proveedorId", target = "proveedorId"), //
            @Mapping(source = "sedeId", target = "sedeId"), //
            @Mapping(source = "isActive", target = "isActive"), //
            @Mapping(source = "isDeleted", target = "isDeleted"), //
            @Mapping(source = "version", target = "version") //
    })
    SvProveedorSedeEntity dtoToEntity(SvProveedorSedeDTO dto);

    @Mappings({ @Mapping(source = "id", target = "id"), //
            @Mapping(source = "proveedorId", target = "proveedorId"), //
            @Mapping(source = "sedeId", target = "sedeId"), //
            @Mapping(source = "isActive", target = "isActive"), //
            @Mapping(source = "isDeleted", target = "isDeleted"), //
            @Mapping(source = "version", target = "version") //
    })
    SvProveedorSedeDTO entityToDto(SvProveedorSedeEntity entity);

}
