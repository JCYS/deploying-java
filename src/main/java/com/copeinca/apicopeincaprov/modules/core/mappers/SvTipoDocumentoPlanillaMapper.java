/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/mappers/Mapper.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.mappers;

import com.copeinca.apicopeincaprov.modules.core.models.dto.SvTipoDocumentoPlanillaDTO;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvTipoDocumentoPlanillaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface SvTipoDocumentoPlanillaMapper {

    @Mappings({ @Mapping(source = "id", target = "id"), //
            @Mapping(source = "description", target = "description"), //
            @Mapping(source = "ambito", target = "ambito"), //
            @Mapping(source = "influencia", target = "influencia"), //
            @Mapping(source = "dependencia", target = "dependencia"), //
            @Mapping(source = "campoPlantillaLf", target = "campoPlantillaLf"), //
            @Mapping(source = "obligatorio", target = "obligatorio"), //
            @Mapping(source = "vencimiento", target = "vencimiento"), //
            @Mapping(source = "isDeleted", target = "isDeleted"), //
            @Mapping(source = "version", target = "version") //
    })
    SvTipoDocumentoPlanillaEntity dtoToEntity(SvTipoDocumentoPlanillaDTO dto);

    @Mappings({ @Mapping(source = "id", target = "id"), //
            @Mapping(source = "description", target = "description"), //
            @Mapping(source = "ambito", target = "ambito"), //
            @Mapping(source = "influencia", target = "influencia"), //
            @Mapping(source = "dependencia", target = "dependencia"), //
            @Mapping(source = "campoPlantillaLf", target = "campoPlantillaLf"), //
            @Mapping(source = "obligatorio", target = "obligatorio"), //
            @Mapping(source = "vencimiento", target = "vencimiento"), //
            @Mapping(source = "isDeleted", target = "isDeleted"), //
            @Mapping(source = "version", target = "version") //
    })
    SvTipoDocumentoPlanillaDTO entityToDto(SvTipoDocumentoPlanillaEntity entity);

}
