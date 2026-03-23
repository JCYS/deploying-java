/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/mappers/Mapper.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.mappers;

import com.copeinca.apicopeincaprov.modules.core.models.dto.ViewPersonaRestringidaReportDTO;
import com.copeinca.apicopeincaprov.modules.core.models.views.ViewPersonaRestringidaReportView;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ViewPersonaRestringidaReportMapper {

    @Mappings({ @Mapping(source = "id", target = "id"), //
            @Mapping(source = "numeroDocumento", target = "numeroDocumento"), //
            @Mapping(source = "licenciaConducir", target = "licenciaConducir"), //
            @Mapping(source = "nombreCompleto", target = "nombreCompleto"), //
            @Mapping(source = "usuarioReporta", target = "usuarioReporta"), //
            @Mapping(source = "restringidoEl", target = "restringidoEl"), //
            @Mapping(source = "restringidoHasta", target = "restringidoHasta"), //
            @Mapping(source = "observaciones", target = "observaciones"), //
            @Mapping(source = "tipoDocumentoIdentidadCode", target = "tipoDocumentoIdentidadCode"), //
            @Mapping(source = "sedeOriginadoraId", target = "sedeOriginadoraId"), //
            @Mapping(source = "tdiNombre", target = "tdiNombre"), //
            @Mapping(source = "sedeNombre", target = "sedeNombre"), //
            @Mapping(source = "sedeAmbito", target = "sedeAmbito"), //
            @Mapping(source = "restriccionActiva", target = "restriccionActiva"), //
            @Mapping(source = "diasRestriccion", target = "diasRestriccion"), //
            @Mapping(source = "tipoRestriccion", target = "tipoRestriccion") //
    })
    ViewPersonaRestringidaReportView dtoToEntity(ViewPersonaRestringidaReportDTO dto);

    @Mappings({ @Mapping(source = "id", target = "id"), //
            @Mapping(source = "numeroDocumento", target = "numeroDocumento"), //
            @Mapping(source = "licenciaConducir", target = "licenciaConducir"), //
            @Mapping(source = "nombreCompleto", target = "nombreCompleto"), //
            @Mapping(source = "usuarioReporta", target = "usuarioReporta"), //
            @Mapping(source = "restringidoEl", target = "restringidoEl"), //
            @Mapping(source = "restringidoHasta", target = "restringidoHasta"), //
            @Mapping(source = "observaciones", target = "observaciones"), //
            @Mapping(source = "tipoDocumentoIdentidadCode", target = "tipoDocumentoIdentidadCode"), //
            @Mapping(source = "sedeOriginadoraId", target = "sedeOriginadoraId"), //
            @Mapping(source = "tdiNombre", target = "tdiNombre"), //
            @Mapping(source = "sedeNombre", target = "sedeNombre"), //
            @Mapping(source = "sedeAmbito", target = "sedeAmbito"), //
            @Mapping(source = "restriccionActiva", target = "restriccionActiva"), //
            @Mapping(source = "diasRestriccion", target = "diasRestriccion"), //
            @Mapping(source = "tipoRestriccion", target = "tipoRestriccion") //
    })
    ViewPersonaRestringidaReportDTO entityToDto(ViewPersonaRestringidaReportView entity);

}
