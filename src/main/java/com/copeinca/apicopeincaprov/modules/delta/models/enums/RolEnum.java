package com.copeinca.apicopeincaprov.modules.delta.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RolEnum {

    SSO                   ( "controlprov_sso" ),
    CALIDAD_AMBIENTAL     ( "controlprov_amb" ),
    SEGURIDAD_PATRIMONIAL ( "controlprov_secpatr" ),
    ADMINISTRACION        ( "controlprov_admin" ),
    CREADOR_PROVEEDORES   ( "controlprov_creaprov" ),
    PROVEEDOR             ( "controlprov_prov" );

    private final String code;

}
