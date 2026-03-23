/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/mappers/Mapper.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.mappers;

import com.copeinca.apicopeincaprov.modules.core.models.dto.SvSolicitudVisitaEventoDTO;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvSolicitudVisitaEventoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface SvSolicitudVisitaEventoMapper {

    @Mappings({ @Mapping(source = "id", target = "id"), //
            @Mapping(source = "fechaEvento", target = "fechaEvento"), //
            @Mapping(source = "usuario", target = "usuario"), //
            @Mapping(source = "evento", target = "evento"), //
            @Mapping(source = "ambito", target = "ambito"), //
            @Mapping(source = "revision", target = "revision"), //
            @Mapping(source = "respuestaUsuario", target = "respuestaUsuario"), //
            @Mapping(source = "solicitudVisitaId", target = "solicitudVisitaId"), //
            @Mapping(source = "isDeleted", target = "isDeleted"), //
            @Mapping(source = "version", target = "version") //
    })
    SvSolicitudVisitaEventoEntity dtoToEntity(SvSolicitudVisitaEventoDTO dto);

    @Mappings({ @Mapping(source = "id", target = "id"), //
            @Mapping(source = "fechaEvento", target = "fechaEvento"), //
            @Mapping(source = "usuario", target = "usuario"), //
            @Mapping(source = "evento", target = "evento"), //
            @Mapping(source = "ambito", target = "ambito"), //
            @Mapping(source = "revision", target = "revision"), //
            @Mapping(source = "respuestaUsuario", target = "respuestaUsuario"), //
            @Mapping(source = "solicitudVisitaId", target = "solicitudVisitaId"), //
            @Mapping(source = "isDeleted", target = "isDeleted"), //
            @Mapping(source = "version", target = "version") //
    })
    SvSolicitudVisitaEventoDTO entityToDto(SvSolicitudVisitaEventoEntity entity);

}
