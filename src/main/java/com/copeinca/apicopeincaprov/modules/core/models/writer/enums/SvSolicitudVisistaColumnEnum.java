
/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/models/dto/enums/EntityColumnEnum.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.models.writer.enums;

import com.copeinca.apicopeincaprov.commons.report.excel.IExcelColumnPropertiesEnum;
import lombok.RequiredArgsConstructor;

/**
* Enum que mapea las columnas del DTO SvSolicitudVisistaDTO con sus índices.
* Utilizado para lectura de archivos CSV y Excel.
*/
@RequiredArgsConstructor
public enum SvSolicitudVisistaColumnEnum implements IExcelColumnPropertiesEnum {

    SOLICITUD_VISITA_ID(0, "SOLICITUD_VISITA_ID", 400), //
    CODIGO_VISITA(1, "CODIGO_VISITA", 400), //
    CODIGO_SISTEMA_EXTERNO(2, "CODIGO_SISTEMA_EXTERNO", 400), //
    FECHA_INICIO(3, "FECHA_INICIO", 400), //
    FECHA_FIN(4, "FECHA_FIN", 400), //
    MOTIVO_VISITA(5, "MOTIVO_VISITA", 400), //
    FECHA_APROBACION_PERSONA_VISITADA(6, "FECHA_APROBACION_PERSONA_VISITADA", 400), //
    FECHA_OBSERVACION_ADMINISTRADOR(7, "FECHA_OBSERVACION_ADMINISTRADOR", 400), //
    OBSERVACIONES(8, "OBSERVACIONES", 400), //
    NRO_ORDEN_SERVICIO(9, "NRO_ORDEN_SERVICIO", 400), //
    IND_ACTIVIDAD_ALTO_RIESGO(10, "IND_ACTIVIDAD_ALTO_RIESGO", 400), //
    IND_ACTIVIDAD_A_BORDO(11, "IND_ACTIVIDAD_A_BORDO", 400), //
    IND_REQUIERE_ANDAMIOS(12, "IND_REQUIERE_ANDAMIOS", 400), //
    IND_REQUIERE_GRUA(13, "IND_REQUIERE_GRUA", 400), //
    IND_REQUIERE_EQUIPOS_MOVILES(14, "IND_REQUIERE_EQUIPOS_MOVILES", 400), //
    IND_TRABAJO_BUCEO(15, "IND_TRABAJO_BUCEO", 400), //
    SSO_MOTIVO(16, "SSO_MOTIVO", 400), //
    SSO_DESCARGO(17, "SSO_DESCARGO", 400), //
    CA_MOTIVO(18, "CA_MOTIVO", 400), //
    CA_DESCARGO(19, "CA_DESCARGO", 400), //
    ESTADO_SSO_CODE(20, "ESTADO_SSO_CODE", 400), //
    ESTADO_CALIDAD_AMBIENTAL_CODE(21, "ESTADO_CALIDAD_AMBIENTAL_CODE", 400), //
    ESTADO_SOLICITUD_VISITA_CODE(22, "ESTADO_SOLICITUD_VISITA_CODE", 400), //
    SEDE_ID(23, "SEDE_ID", 400), //
    PROVEEDOR_ID(24, "PROVEEDOR_ID", 400), //
    PERSONAL_ID(25, "PERSONAL_ID", 400), //
    IS_DELETED(26, "IS_DELETED", 400), //
    VERSION(27, "VERSION", 400), //
    ;

    private final Integer columnIndex;
    private final String columnName;
    private final Integer width;

    @Override
    public String getColumnName() {
        return columnName;
    }

    @Override
    public Integer getColumnIndex() {
        return columnIndex;
    }

    @Override
    public Integer getWidth() {
        return width;
    }

}