/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/mappers/Mapper.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.mappers;

import com.copeinca.apicopeincaprov.modules.core.models.dto.SvAdjuntoDTO;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvAdjuntoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface SvAdjuntoMapper {

    @Mappings({ @Mapping(source = "id", target = "id"), //
            @Mapping(source = "nombreArchivo", target = "nombreArchivo"), //
            @Mapping(source = "idRepositorio", target = "idRepositorio"), //
            @Mapping(source = "clasification", target = "clasification"), //
            @Mapping(source = "fechaDocumento", target = "fechaDocumento"), //
            @Mapping(source = "fechaVencimiento", target = "fechaVencimiento"), //
            @Mapping(source = "rutaRelativa", target = "rutaRelativa"), //
            @Mapping(source = "ssoComentarioRevision", target = "ssoComentarioRevision"), //
            @Mapping(source = "caComentarioRevision", target = "caComentarioRevision"), //
            @Mapping(source = "motivo", target = "motivo"), //
            @Mapping(source = "proveedorId", target = "proveedorId"), //
            @Mapping(source = "trabajadorId", target = "trabajadorId"), //
            @Mapping(source = "tipoDocumentoPlanillaCode", target = "tipoDocumentoPlanillaCode"), //
            @Mapping(source = "solicitudVisitaId", target = "solicitudVisitaId"), //c
            @Mapping(source = "tipoActividadAltoRiesgoCode", target = "tipoActividadAltoRiesgoCode"), //
            @Mapping(source = "isDeleted", target = "isDeleted"), //
            @Mapping(source = "version", target = "version") //
    })
    SvAdjuntoEntity dtoToEntity(SvAdjuntoDTO dto);

    @Mappings({ @Mapping(source = "id", target = "id"), //
            @Mapping(source = "nombreArchivo", target = "nombreArchivo"), //
            @Mapping(source = "idRepositorio", target = "idRepositorio"), //
            @Mapping(source = "clasification", target = "clasification"), //
            @Mapping(source = "fechaDocumento", target = "fechaDocumento"), //
            @Mapping(source = "fechaVencimiento", target = "fechaVencimiento"), //
            @Mapping(source = "rutaRelativa", target = "rutaRelativa"), //
            @Mapping(source = "ssoComentarioRevision", target = "ssoComentarioRevision"), //
            @Mapping(source = "caComentarioRevision", target = "caComentarioRevision"), //
            @Mapping(source = "motivo", target = "motivo"), //
            @Mapping(source = "proveedorId", target = "proveedorId"), //
            @Mapping(source = "trabajadorId", target = "trabajadorId"), //
            @Mapping(source = "tipoDocumentoPlanillaCode", target = "tipoDocumentoPlanillaCode"), //
            @Mapping(source = "solicitudVisitaId", target = "solicitudVisitaId"), //
            @Mapping(source = "tipoActividadAltoRiesgoCode", target = "tipoActividadAltoRiesgoCode"), //
            @Mapping(source = "isDeleted", target = "isDeleted"), //
            @Mapping(source = "version", target = "version"), //
            @Mapping(source = "tipoDocumentoPlanillaCodeEntity.description", target = "descTipoDocumentoPlanillaCode"), //
            @Mapping(source = "tipoDocumentoPlanillaCodeEntity.dependencia", target = "descDepTipoDocumentoPlanillaCode") //
    })
    SvAdjuntoDTO entityToDto(SvAdjuntoEntity entity);

}
