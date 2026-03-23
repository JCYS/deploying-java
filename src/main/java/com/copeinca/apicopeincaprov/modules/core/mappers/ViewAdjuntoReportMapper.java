/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/mappers/Mapper.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.mappers;

import com.copeinca.apicopeincaprov.modules.core.models.dto.ViewAdjuntoReportDTO;
import com.copeinca.apicopeincaprov.modules.core.models.views.ViewAdjuntoReportView;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ViewAdjuntoReportMapper {

    @Mappings({ @Mapping(source = "id", target = "id"), //
            @Mapping(source = "nombreArchivo", target = "nombreArchivo"), //
            @Mapping(source = "idRepositorio", target = "idRepositorio"), //
            @Mapping(source = "clasification", target = "clasification"), //
            @Mapping(source = "fechaDocumento", target = "fechaDocumento"), //
            @Mapping(source = "fechaVencimiento", target = "fechaVencimiento"), //
            @Mapping(source = "rutaRelativa", target = "rutaRelativa"), //
            @Mapping(source = "ssoComentarioRevision", target = "ssoComentarioRevision"), //
            @Mapping(source = "caComentarioRevision", target = "caComentarioRevision"), //
            @Mapping(source = "proveedorId", target = "proveedorId"), //
            @Mapping(source = "trabajadorId", target = "trabajadorId"), //
            @Mapping(source = "tipoDocumentoPlanillaCode", target = "tipoDocumentoPlanillaCode"), //
            @Mapping(source = "solicitudVisitaId", target = "solicitudVisitaId"), //
            @Mapping(source = "tipoActividadAltoRiesgoCode", target = "tipoActividadAltoRiesgoCode"), //
            @Mapping(source = "tdpDescripcion", target = "tdpDescripcion"), //
            @Mapping(source = "tdpAmbito", target = "tdpAmbito"), //
            @Mapping(source = "tdpInfluencia", target = "tdpInfluencia"), //
            @Mapping(source = "tdpDependencia", target = "tdpDependencia"), //
            @Mapping(source = "tdpCampoPlantillaLf", target = "tdpCampoPlantillaLf"), //
            @Mapping(source = "tdpObligatorio", target = "tdpObligatorio"), //
            @Mapping(source = "tdpVencimiento", target = "tdpVencimiento"), //
            @Mapping(source = "tipoPropietario", target = "tipoPropietario"), //
            @Mapping(source = "documentoVencido", target = "documentoVencido"), //
            @Mapping(source = "diasHastaVencimiento", target = "diasHastaVencimiento") //
    })
    ViewAdjuntoReportView dtoToEntity(ViewAdjuntoReportDTO dto);

    @Mappings({ @Mapping(source = "id", target = "id"), //
            @Mapping(source = "nombreArchivo", target = "nombreArchivo"), //
            @Mapping(source = "idRepositorio", target = "idRepositorio"), //
            @Mapping(source = "clasification", target = "clasification"), //
            @Mapping(source = "fechaDocumento", target = "fechaDocumento"), //
            @Mapping(source = "fechaVencimiento", target = "fechaVencimiento"), //
            @Mapping(source = "rutaRelativa", target = "rutaRelativa"), //
            @Mapping(source = "ssoComentarioRevision", target = "ssoComentarioRevision"), //
            @Mapping(source = "caComentarioRevision", target = "caComentarioRevision"), //
            @Mapping(source = "proveedorId", target = "proveedorId"), //
            @Mapping(source = "trabajadorId", target = "trabajadorId"), //
            @Mapping(source = "tipoDocumentoPlanillaCode", target = "tipoDocumentoPlanillaCode"), //
            @Mapping(source = "solicitudVisitaId", target = "solicitudVisitaId"), //
            @Mapping(source = "tipoActividadAltoRiesgoCode", target = "tipoActividadAltoRiesgoCode"), //
            @Mapping(source = "tdpDescripcion", target = "tdpDescripcion"), //
            @Mapping(source = "tdpAmbito", target = "tdpAmbito"), //
            @Mapping(source = "tdpInfluencia", target = "tdpInfluencia"), //
            @Mapping(source = "tdpDependencia", target = "tdpDependencia"), //
            @Mapping(source = "tdpCampoPlantillaLf", target = "tdpCampoPlantillaLf"), //
            @Mapping(source = "tdpObligatorio", target = "tdpObligatorio"), //
            @Mapping(source = "tdpVencimiento", target = "tdpVencimiento"), //
            @Mapping(source = "tipoPropietario", target = "tipoPropietario"), //
            @Mapping(source = "documentoVencido", target = "documentoVencido"), //
            @Mapping(source = "diasHastaVencimiento", target = "diasHastaVencimiento") //
    })
    ViewAdjuntoReportDTO entityToDto(ViewAdjuntoReportView entity);

}
