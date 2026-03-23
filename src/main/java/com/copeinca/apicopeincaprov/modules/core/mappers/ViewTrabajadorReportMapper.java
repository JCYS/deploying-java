/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/mappers/Mapper.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.mappers;

import com.copeinca.apicopeincaprov.modules.core.models.dto.ViewTrabajadorReportDTO;
import com.copeinca.apicopeincaprov.modules.core.models.views.ViewTrabajadorReportView;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ViewTrabajadorReportMapper {

    @Mappings({ @Mapping(source = "id", target = "id"), //
            @Mapping(source = "proveedorId", target = "proveedorId"), //
            @Mapping(source = "provNroDocumentoIdentidad", target = "provNroDocumentoIdentidad"), //
            @Mapping(source = "provNombreFiscal", target = "provNombreFiscal"), //
            @Mapping(source = "tipoDocumentoIdentidadCode", target = "tipoDocumentoIdentidadCode"), //
            @Mapping(source = "nroDocumentoIdentidad", target = "nroDocumentoIdentidad"), //
            @Mapping(source = "tdiNombre", target = "tdiNombre"), //
            @Mapping(source = "nombre", target = "nombre"), //
            @Mapping(source = "telefono", target = "telefono"), //
            @Mapping(source = "email", target = "email"), //
            @Mapping(source = "estadoSsoCode", target = "estadoSsoCode"), //
            @Mapping(source = "ssoMotivo", target = "ssoMotivo"), //
            @Mapping(source = "soTieneVigencia", target = "soTieneVigencia"), //
            @Mapping(source = "ssoFechaInicioVigencia", target = "ssoFechaInicioVigencia"), //
            @Mapping(source = "ssoFechaFinVigencia", target = "ssoFechaFinVigencia"), //
            @Mapping(source = "isDeleted", target = "isDeleted"), //
            @Mapping(source = "isActive", target = "isActive"), //
            @Mapping(source = "ssoEstadoNombre", target = "ssoEstadoNombre"), //
            @Mapping(source = "ssoEstadoVigente", target = "ssoEstadoVigente"), //
            @Mapping(source = "indTrabajoAltoRiesgo", target = "indTrabajoAltoRiesgo"), //
            @Mapping(source = "indPrevencionista", target = "indPrevencionista"), //
            @Mapping(source = "tarCodigos", target = "tarCodigos"), //
            @Mapping(source = "tarDescripciones", target = "tarDescripciones"), //
            @Mapping(source = "NDocsSsoAdjuntos", target = "nDocsSsoAdjuntos"), //
            @Mapping(source = "NDocsCalidadAdjuntos", target = "nDocsCalidadAdjuntos"), //
            @Mapping(source = "NDocsAarAdjuntos", target = "nDocsAarAdjuntos"), //
            @Mapping(source = "NDocsAbAdjuntos", target = "nDocsAbAdjuntos"), //
            @Mapping(source = "NDocsPrevAdjuntos", target = "nDocsPrevAdjuntos"), //
            @Mapping(source = "isValid", target = "isValid"),
            @Mapping(source = "NDocsTotalesValid", target = "nDocsTotalesValid"), //
            @Mapping(source = "NDocsTotales", target = "nDocsTotales") //
    })
    ViewTrabajadorReportView dtoToEntity(ViewTrabajadorReportDTO dto);

    @Mappings({ @Mapping(source = "id", target = "id"), //
            @Mapping(source = "proveedorId", target = "proveedorId"), //
            @Mapping(source = "provNroDocumentoIdentidad", target = "provNroDocumentoIdentidad"), //
            @Mapping(source = "provNombreFiscal", target = "provNombreFiscal"), //
            @Mapping(source = "tipoDocumentoIdentidadCode", target = "tipoDocumentoIdentidadCode"), //
            @Mapping(source = "nroDocumentoIdentidad", target = "nroDocumentoIdentidad"), //
            @Mapping(source = "tdiNombre", target = "tdiNombre"), //
            @Mapping(source = "nombre", target = "nombre"), //
            @Mapping(source = "telefono", target = "telefono"), //
            @Mapping(source = "email", target = "email"), //
            @Mapping(source = "estadoSsoCode", target = "estadoSsoCode"), //
            @Mapping(source = "ssoMotivo", target = "ssoMotivo"), //
            @Mapping(source = "soTieneVigencia", target = "soTieneVigencia"), //
            @Mapping(source = "ssoFechaInicioVigencia", target = "ssoFechaInicioVigencia"), //
            @Mapping(source = "ssoFechaFinVigencia", target = "ssoFechaFinVigencia"), //
            @Mapping(source = "isDeleted", target = "isDeleted"), //
            @Mapping(source = "isActive", target = "isActive"), //
            @Mapping(source = "ssoEstadoNombre", target = "ssoEstadoNombre"), //
            @Mapping(source = "ssoEstadoVigente", target = "ssoEstadoVigente"), //
            @Mapping(source = "indTrabajoAltoRiesgo", target = "indTrabajoAltoRiesgo"), //
            @Mapping(source = "indPrevencionista", target = "indPrevencionista"), //
            @Mapping(source = "tarCodigos", target = "tarCodigos"), //
            @Mapping(source = "tarDescripciones", target = "tarDescripciones"), //
            @Mapping(source = "NDocsSsoAdjuntos", target = "nDocsSsoAdjuntos"), //
            @Mapping(source = "NDocsCalidadAdjuntos", target = "nDocsCalidadAdjuntos"), //
            @Mapping(source = "NDocsAarAdjuntos", target = "nDocsAarAdjuntos"), //
            @Mapping(source = "NDocsAbAdjuntos", target = "nDocsAbAdjuntos"), //
            @Mapping(source = "NDocsPrevAdjuntos", target = "nDocsPrevAdjuntos"), //
            @Mapping(source = "isValid", target = "isValid"),
            @Mapping(source = "NDocsTotalesValid", target = "nDocsTotalesValid"), //
            @Mapping(source = "NDocsTotales", target = "nDocsTotales") //
    })
    ViewTrabajadorReportDTO entityToDto(ViewTrabajadorReportView entity);

}
