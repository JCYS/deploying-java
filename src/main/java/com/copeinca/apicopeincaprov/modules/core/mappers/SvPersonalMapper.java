/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/mappers/Mapper.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.mappers;

import com.copeinca.apicopeincaprov.modules.core.models.dto.SvPersonalDTO;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvPersonalEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface SvPersonalMapper {

    @Mappings({ @Mapping(source = "id", target = "id"), //
            @Mapping(source = "usuario", target = "usuario"), //
            @Mapping(source = "nombreUsuario", target = "nombreUsuario"), //
            @Mapping(source = "codigo", target = "codigo"), //
            @Mapping(source = "descripcion", target = "descripcion"), //
            @Mapping(source = "ambito", target = "ambito"), //
            @Mapping(source = "influencia", target = "influencia"), //
            @Mapping(source = "dependencia", target = "dependencia"), //
            @Mapping(source = "campoPlantillaLf", target = "campoPlantillaLf"), //
            @Mapping(source = "obligatorio", target = "obligatorio"), //
            @Mapping(source = "vencimiento", target = "vencimiento"), //
            @Mapping(source = "numeroCaso", target = "numeroCaso"), //
            @Mapping(source = "mensaje", target = "mensaje"), //
            @Mapping(source = "tipoDocumentoIdentidadCode", target = "tipoDocumentoIdentidadCode"), //
            @Mapping(source = "isDeleted", target = "isDeleted"), //
            @Mapping(source = "version", target = "version") //
    })
    SvPersonalEntity dtoToEntity(SvPersonalDTO dto);

    @Mappings({ @Mapping(source = "id", target = "id"), //
            @Mapping(source = "usuario", target = "usuario"), //
            @Mapping(source = "nombreUsuario", target = "nombreUsuario"), //
            @Mapping(source = "codigo", target = "codigo"), //
            @Mapping(source = "descripcion", target = "descripcion"), //
            @Mapping(source = "ambito", target = "ambito"), //
            @Mapping(source = "influencia", target = "influencia"), //
            @Mapping(source = "dependencia", target = "dependencia"), //
            @Mapping(source = "campoPlantillaLf", target = "campoPlantillaLf"), //
            @Mapping(source = "obligatorio", target = "obligatorio"), //
            @Mapping(source = "vencimiento", target = "vencimiento"), //
            @Mapping(source = "numeroCaso", target = "numeroCaso"), //
            @Mapping(source = "mensaje", target = "mensaje"), //
            @Mapping(source = "tipoDocumentoIdentidadCode", target = "tipoDocumentoIdentidadCode"), //
            @Mapping(source = "isDeleted", target = "isDeleted"), //
            @Mapping(source = "version", target = "version") //
    })
    SvPersonalDTO entityToDto(SvPersonalEntity entity);

}
