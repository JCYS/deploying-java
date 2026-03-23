/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/mappers/Mapper.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.mappers;

import com.copeinca.apicopeincaprov.modules.core.models.dto.SvPersonaRestringidaDTO;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvPersonaRestringidaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface SvPersonaRestringidaMapper {

    @Mappings({ @Mapping(source = "id", target = "id"), //
            @Mapping(source = "tipoDocumentoIdentidadCode", target = "tipoDocumentoIdentidadCode"), //
            @Mapping(source = "numeroDocumento", target = "numeroDocumento"), //
            @Mapping(source = "licenciaConducir", target = "licenciaConducir"), //
            @Mapping(source = "nombreCompleto", target = "nombreCompleto"), //
            @Mapping(source = "usuarioReporta", target = "usuarioReporta"), //
            @Mapping(source = "restringidoEl", target = "restringidoEl"), //
            @Mapping(source = "restringidoHasta", target = "restringidoHasta"), //
            @Mapping(source = "observaciones", target = "observaciones"), //
            @Mapping(source = "sedeOriginadoraId", target = "sedeOriginadoraId"), //
            @Mapping(source = "isDeleted", target = "isDeleted"), //
            @Mapping(source = "version", target = "version") //
    })
    SvPersonaRestringidaEntity dtoToEntity(SvPersonaRestringidaDTO dto);

    @Mappings({ @Mapping(source = "id", target = "id"), //
            @Mapping(source = "tipoDocumentoIdentidadCode", target = "tipoDocumentoIdentidadCode"), //
            @Mapping(source = "numeroDocumento", target = "numeroDocumento"), //
            @Mapping(source = "licenciaConducir", target = "licenciaConducir"), //
            @Mapping(source = "nombreCompleto", target = "nombreCompleto"), //
            @Mapping(source = "usuarioReporta", target = "usuarioReporta"), //
            @Mapping(source = "restringidoEl", target = "restringidoEl"), //
            @Mapping(source = "restringidoHasta", target = "restringidoHasta"), //
            @Mapping(source = "observaciones", target = "observaciones"), //
            @Mapping(source = "sedeOriginadoraId", target = "sedeOriginadoraId"), //
            @Mapping(source = "isDeleted", target = "isDeleted"), //
            @Mapping(source = "version", target = "version") //
    })
    SvPersonaRestringidaDTO entityToDto(SvPersonaRestringidaEntity entity);

}
