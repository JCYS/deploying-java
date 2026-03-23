/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/mappers/Mapper.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.mappers;

import com.copeinca.apicopeincaprov.modules.core.models.dto.SvTrabajadorDTO;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvTrabajadorEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface SvTrabajadorMapper {

    @Mappings({ @Mapping(source = "id", target = "id"), //
            @Mapping(source = "proveedorId", target = "proveedorId"), //
            @Mapping(source = "tipoDocumentoIdentidadCode", target = "tipoDocumentoIdentidadCode"), //
            @Mapping(source = "nroDocumentoIdentidad", target = "nroDocumentoIdentidad"), //
            @Mapping(source = "nombre", target = "nombre"), //
            @Mapping(source = "telefono", target = "telefono"), //
            @Mapping(source = "email", target = "email"), //
            @Mapping(source = "ssoMotivo", target = "ssoMotivo"), //
            @Mapping(source = "soTieneVigencia", target = "soTieneVigencia"), //
            @Mapping(source = "ssoFechaInicioVigencia", target = "ssoFechaInicioVigencia"), //
            @Mapping(source = "ssoFechaFinVigencia", target = "ssoFechaFinVigencia"), //
            @Mapping(source = "indTrabajoAltoRiesgo", target = "indTrabajoAltoRiesgo"), //
            @Mapping(source = "indPrevencionista", target = "indPrevencionista"), //
            @Mapping(source = "estadoSsoCode", target = "estadoSsoCode"), //
            @Mapping(source = "isActive", target = "isActive"), //
            @Mapping(source = "isDeleted", target = "isDeleted"), //
            @Mapping(source = "version", target = "version") //
    })
    SvTrabajadorEntity dtoToEntity(SvTrabajadorDTO dto);

    @Mappings({ @Mapping(source = "id", target = "id"), //
            @Mapping(source = "proveedorId", target = "proveedorId"), //
            @Mapping(source = "tipoDocumentoIdentidadCode", target = "tipoDocumentoIdentidadCode"), //
            @Mapping(source = "nroDocumentoIdentidad", target = "nroDocumentoIdentidad"), //
            @Mapping(source = "nombre", target = "nombre"), //
            @Mapping(source = "telefono", target = "telefono"), //
            @Mapping(source = "email", target = "email"), //
            @Mapping(source = "ssoMotivo", target = "ssoMotivo"), //
            @Mapping(source = "soTieneVigencia", target = "soTieneVigencia"), //
            @Mapping(source = "ssoFechaInicioVigencia", target = "ssoFechaInicioVigencia"), //
            @Mapping(source = "ssoFechaFinVigencia", target = "ssoFechaFinVigencia"), //
            @Mapping(source = "indTrabajoAltoRiesgo", target = "indTrabajoAltoRiesgo"), //
            @Mapping(source = "indPrevencionista", target = "indPrevencionista"), //
            @Mapping(source = "estadoSsoCode", target = "estadoSsoCode"), //
            @Mapping(source = "isActive", target = "isActive"), //
            @Mapping(source = "isDeleted", target = "isDeleted"), //
            @Mapping(source = "tipoDocumentoIdentidadCodeEntity.name", target = "tipoDocumentoIdentidadDescription"), //
            @Mapping(source = "estadoSsoCodeEntity.name", target = "estadoSsoDescription")
    })
    SvTrabajadorDTO entityToDto(SvTrabajadorEntity entity);

}
