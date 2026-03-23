/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/mappers/Mapper.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.mappers;

import com.copeinca.apicopeincaprov.modules.core.models.dto.ViewPersonalReportDTO;
import com.copeinca.apicopeincaprov.modules.core.models.views.ViewPersonalReportView;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ViewPersonalReportMapper {

    @Mappings({ @Mapping(source = "id", target = "id"), //
            @Mapping(source = "isActive", target = "isActive"), //
            @Mapping(source = "personalId", target = "personalId"), //
            @Mapping(source = "sedeId", target = "sedeId"), //
            @Mapping(source = "personalUsuario", target = "personalUsuario"), //
            @Mapping(source = "personalNombreUsuario", target = "personalNombreUsuario"), //
            @Mapping(source = "personalCodigo", target = "personalCodigo"), //
            @Mapping(source = "personalDescripcion", target = "personalDescripcion"), //
            @Mapping(source = "sedeName", target = "sedeName"), //
            @Mapping(source = "sedeAmbito", target = "sedeAmbito") //
    })
    ViewPersonalReportView dtoToEntity(ViewPersonalReportDTO dto);

    @Mappings({ @Mapping(source = "id", target = "id"), //
            @Mapping(source = "isActive", target = "isActive"), //
            @Mapping(source = "personalId", target = "personalId"), //
            @Mapping(source = "sedeId", target = "sedeId"), //
            @Mapping(source = "personalUsuario", target = "personalUsuario"), //
            @Mapping(source = "personalNombreUsuario", target = "personalNombreUsuario"), //
            @Mapping(source = "personalCodigo", target = "personalCodigo"), //
            @Mapping(source = "personalDescripcion", target = "personalDescripcion"), //
            @Mapping(source = "sedeName", target = "sedeName"), //
            @Mapping(source = "sedeAmbito", target = "sedeAmbito") //
    })
    ViewPersonalReportDTO entityToDto(ViewPersonalReportView entity);

}
