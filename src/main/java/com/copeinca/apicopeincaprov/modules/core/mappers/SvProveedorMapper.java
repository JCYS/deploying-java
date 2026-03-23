/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/mappers/Mapper.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.mappers;

import com.copeinca.apicopeincaprov.modules.core.models.dto.SvProveedorDTO;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvProveedorEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface SvProveedorMapper {

    @Mappings({ @Mapping(source = "id", target = "id"), //
            @Mapping(source = "estadoCalidadAmbientalCode", target = "estadoCalidadAmbientalCode"), //
            @Mapping(source = "nroDocumentoIdentidad", target = "nroDocumentoIdentidad"), //
            @Mapping(source = "nombreFiscal", target = "nombreFiscal"), //
            @Mapping(source = "email", target = "email"), //
            @Mapping(source = "direccion", target = "direccion"), //
            @Mapping(source = "contactoPrincipal", target = "contactoPrincipal"), //
            @Mapping(source = "contactoPrincipalTelefono", target = "contactoPrincipalTelefono"), //
            @Mapping(source = "ssoTieneVigencia", target = "ssoTieneVigencia"), //
            @Mapping(source = "ssoFechaInicioVigencia", target = "ssoFechaInicioVigencia"), //
            @Mapping(source = "ssoFechaFinVigencia", target = "ssoFechaFinVigencia"), //
            @Mapping(source = "ssoMotivo", target = "ssoMotivo"), //
            @Mapping(source = "ssoUsuarioEvaluador", target = "ssoUsuarioEvaluador"), //
            @Mapping(source = "ssoFechaEvaluacion", target = "ssoFechaEvaluacion"), //
            @Mapping(source = "indRealizaActividadAltoRiesgo", target = "indRealizaActividadAltoRiesgo"), //
            @Mapping(source = "indRealizaActividadABordo", target = "indRealizaActividadABordo"), //
            @Mapping(source = "caTieneVigencia", target = "caTieneVigencia"), //
            @Mapping(source = "caFechaInicioVigencia", target = "caFechaInicioVigencia"), //
            @Mapping(source = "caFechaFinVigencia", target = "caFechaFinVigencia"), //
            @Mapping(source = "caMotivo", target = "caMotivo"), //
            @Mapping(source = "caUsuarioEvaluador", target = "caUsuarioEvaluador"), //
            @Mapping(source = "caFechaEvaluacion", target = "caFechaEvaluacion"), //
            @Mapping(source = "origenRegistro", target = "origenRegistro"), //
            @Mapping(source = "estadoSsoCode", target = "estadoSsoCode"), //
            @Mapping(source = "indProveedorNotificadoCreacion", target = "indProveedorNotificadoCreacion"), //
            @Mapping(source = "telefono", target = "telefono"), //
            @Mapping(source = "nacional", target = "nacional"), //
            @Mapping(source = "isDeleted", target = "isDeleted"), //
            @Mapping(source = "version", target = "version"), //
            @Mapping(source = "notificaCa", target = "notificaCa"),
            @Mapping(source = "notificaSso", target = "notificaSso")
    })
    SvProveedorEntity dtoToEntity(SvProveedorDTO dto);

    @Mappings({ @Mapping(source = "id", target = "id"), //
            @Mapping(source = "estadoCalidadAmbientalCode", target = "estadoCalidadAmbientalCode"), //
            @Mapping(source = "nroDocumentoIdentidad", target = "nroDocumentoIdentidad"), //
            @Mapping(source = "nombreFiscal", target = "nombreFiscal"), //
            @Mapping(source = "email", target = "email"), //
            @Mapping(source = "direccion", target = "direccion"), //
            @Mapping(source = "contactoPrincipal", target = "contactoPrincipal"), //
            @Mapping(source = "contactoPrincipalTelefono", target = "contactoPrincipalTelefono"), //
            @Mapping(source = "ssoTieneVigencia", target = "ssoTieneVigencia"), //
            @Mapping(source = "ssoFechaInicioVigencia", target = "ssoFechaInicioVigencia"), //
            @Mapping(source = "ssoFechaFinVigencia", target = "ssoFechaFinVigencia"), //
            @Mapping(source = "ssoMotivo", target = "ssoMotivo"), //
            @Mapping(source = "ssoUsuarioEvaluador", target = "ssoUsuarioEvaluador"), //
            @Mapping(source = "ssoFechaEvaluacion", target = "ssoFechaEvaluacion"), //
            @Mapping(source = "indRealizaActividadAltoRiesgo", target = "indRealizaActividadAltoRiesgo"), //
            @Mapping(source = "indRealizaActividadABordo", target = "indRealizaActividadABordo"), //
            @Mapping(source = "caTieneVigencia", target = "caTieneVigencia"), //
            @Mapping(source = "caFechaInicioVigencia", target = "caFechaInicioVigencia"), //
            @Mapping(source = "caFechaFinVigencia", target = "caFechaFinVigencia"), //
            @Mapping(source = "caMotivo", target = "caMotivo"), //
            @Mapping(source = "caUsuarioEvaluador", target = "caUsuarioEvaluador"), //
            @Mapping(source = "caFechaEvaluacion", target = "caFechaEvaluacion"), //
            @Mapping(source = "origenRegistro", target = "origenRegistro"), //
            @Mapping(source = "estadoSsoCode", target = "estadoSsoCode"), //
            @Mapping(source = "indProveedorNotificadoCreacion", target = "indProveedorNotificadoCreacion"), //
            @Mapping(source = "telefono", target = "telefono"), //
            @Mapping(source = "nacional", target = "nacional"), //
            @Mapping(source = "isDeleted", target = "isDeleted"), //
            @Mapping(source = "version", target = "version"), //
            @Mapping(source = "notificaCa", target = "notificaCa"),
            @Mapping(source = "notificaSso", target = "notificaSso")
    })
    SvProveedorDTO entityToDto(SvProveedorEntity entity);

}
