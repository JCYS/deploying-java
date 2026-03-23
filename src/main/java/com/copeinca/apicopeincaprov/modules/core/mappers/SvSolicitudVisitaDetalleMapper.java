/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/mappers/Mapper.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.mappers;

import com.copeinca.apicopeincaprov.modules.core.models.dto.SvSolicitudVisitaDetalleDTO;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvSolicitudVisitaDetalleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface SvSolicitudVisitaDetalleMapper {

    @Mappings({ @Mapping(source = "id", target = "id"), //
            @Mapping(source = "proveedorComentario", target = "proveedorComentario"), //
            @Mapping(source = "solicitudVisitaId", target = "solicitudVisitaId"), //
            @Mapping(source = "trabajadorId", target = "trabajadorId"), //
            @Mapping(source = "isDeleted", target = "isDeleted"), //
            @Mapping(source = "version", target = "version") //
    })
    SvSolicitudVisitaDetalleEntity dtoToEntity(SvSolicitudVisitaDetalleDTO dto);

    @Mappings({ @Mapping(source = "id", target = "id"), //
            @Mapping(source = "proveedorComentario", target = "proveedorComentario"), //
            @Mapping(source = "solicitudVisitaId", target = "solicitudVisitaId"), //
            @Mapping(source = "trabajadorId", target = "trabajadorId"), //
            @Mapping(source = "isDeleted", target = "isDeleted"), //
            @Mapping(source = "version", target = "version"), //
            @Mapping(source = "trabajadorIDEntity.tipoDocumentoIdentidadCodeEntity.name", target = "tipoDocumento"), //
            @Mapping(source = "trabajadorIDEntity.nroDocumentoIdentidad", target = "numDocumento"), //
            @Mapping(source = "trabajadorIDEntity.nombre", target = "nombre"), //
            @Mapping(source = "trabajadorIDEntity.estadoSsoCodeEntity.name", target = "estadoSSODesc"), //
            @Mapping(source = "solicitudVisitaIDEntity.codigoVisita", target = "codVisita"),
            @Mapping(source = "trabajadorIDEntity.ssoFechaFinVigencia", target = "ssoFechaFinVigencia")
    })
    SvSolicitudVisitaDetalleDTO entityToDto(SvSolicitudVisitaDetalleEntity entity);

}
