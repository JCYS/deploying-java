package com.copeinca.apicopeincaprov.modules.core.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EstadoCalidadAmbientalEnum {

    PRELIMINAR( "ST_CA_0001", "PRELIMINAR", "Estado preliminar para Calidad Ambiental" ),
    APTO( "ST_CA_0002", "APTO", "Apto para Calidad Ambiental" ),
    NO_APTO( "ST_CA_0003", "NO APTO", "No apto para Calidad Ambiental" );

    private final String code;
    private final String name;
    private final String desc;

}
