/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/mappers/Mapper.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.mappers;

import com.copeinca.apicopeincaprov.modules.core.models.dto.SvSolicitudVisitaEquipoDTO;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvSolicitudVisitaEquipoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface SvSolicitudVisitaEquipoMapper {

    @Mappings({ @Mapping(source = "id", target = "id"), //
            @Mapping(source = "descripcion", target = "descripcion"), //
            @Mapping(source = "marca", target = "marca"), //
            @Mapping(source = "modelo", target = "modelo"), //
            @Mapping(source = "numeroSerie", target = "numeroSerie"), //
            @Mapping(source = "tipoEquipo", target = "tipoEquipo"), //
            @Mapping(source = "codigoEquipo", target = "codigoEquipo"), //
            @Mapping(source = "cantidad", target = "cantidad"), //
            @Mapping(source = "solicitudVisitaId", target = "solicitudVisitaId"), //
            @Mapping(source = "isDeleted", target = "isDeleted"), //
            @Mapping(source = "version", target = "version") //
    })
    SvSolicitudVisitaEquipoEntity dtoToEntity(SvSolicitudVisitaEquipoDTO dto);

    @Mappings({ @Mapping(source = "id", target = "id"), //
            @Mapping(source = "descripcion", target = "descripcion"), //
            @Mapping(source = "marca", target = "marca"), //
            @Mapping(source = "modelo", target = "modelo"), //
            @Mapping(source = "numeroSerie", target = "numeroSerie"), //
            @Mapping(source = "tipoEquipo", target = "tipoEquipo"), //
            @Mapping(source = "codigoEquipo", target = "codigoEquipo"), //
            @Mapping(source = "cantidad", target = "cantidad"), //
            @Mapping(source = "solicitudVisitaId", target = "solicitudVisitaId"), //
            @Mapping(source = "isDeleted", target = "isDeleted"), //
            @Mapping(source = "version", target = "version") //
    })
    SvSolicitudVisitaEquipoDTO entityToDto(SvSolicitudVisitaEquipoEntity entity);

}
