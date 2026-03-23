package com.copeinca.apicopeincaprov.modules.core.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EstadoSolicitudVisitaEnum {

    PRELIMINAR_PROV( "ST_SV_0001", "PRELIMINAR PROVEEDOR", true ),
    POR_HABILITAR_SRV( "ST_SV_0002", "POR HABILITAR SERVICIO", true ),
    POR_APROBAR_VIS( "ST_SV_0003", "POR APROBAR VISITADO", true ),
    REV_RECHAZO_VIS( "ST_SV_0004", "REVISAR-RECHAZADO VISITADO", true ),
    PRELIMINAR_SEDES( "ST_SV_0005", "PRELIMINAR SEDES", true ),
    APTA_RECEPCION( "ST_SV_0006", "APTA PARA RECEPCION", true ),
    OBSERVADO( "ST_SV_0007", "OBSERVADO", true ),
    INICIADO( "ST_SV_0008", "INICIADA", true ),
    FINALIZADO( "ST_SV_0009", "FINALIZADO", true ),
    CANCELADO( "ST_SV_0010", "CANCELADO", true ),
    SUSPENDIDO( "ST_SV_0011", "SUSPENDIDO", true ),
    SIN_REG_SALIDA( "ST_SV_0012", "SIN REGISTRO DE SALIDA", true ),
    NO_ASISTIO( "ST_SV_0013", "NO ASISTIO", true ),
    FINALIZADO_SIN_REG_SALIDA( "ST_SV_0014", "FINALIZADO SIN REGISTRO DE SALIDA", true ),
    FINALIZADO_NO_ASISTIO( "ST_SV_0015", "FINALIZADO NO ASISTIO", true );

    private final String code;
    private final String name;
    private final Boolean isActive;

    public static EstadoSolicitudVisitaEnum fromCode( String code ) {
        for( EstadoSolicitudVisitaEnum e : values( ) ) {
            if( e.code.equals( code ) ) {
                return e;
            }
        }
        throw new IllegalArgumentException( "Invalid code: " + code );
    }
    public static EstadoSolicitudVisitaEnum fromName(String name) {
        for (EstadoSolicitudVisitaEnum e : values()) {
            if (e.name.equalsIgnoreCase(name)) { // ignoreCase por seguridad
                return e;
            }
        }
        throw new IllegalArgumentException("Invalid name: " + name);
    }
}
