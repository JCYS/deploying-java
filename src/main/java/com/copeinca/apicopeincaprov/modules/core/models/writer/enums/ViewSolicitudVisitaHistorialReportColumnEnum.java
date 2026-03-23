
/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/models/dto/enums/EntityColumnEnum.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.models.writer.enums;

import com.copeinca.apicopeincaprov.commons.report.excel.IExcelColumnPropertiesEnum;
import lombok.RequiredArgsConstructor;

/**
* Enum que mapea las columnas del DTO ViewSolicitudVisitaHistorialReportDTO con sus índices.
* Utilizado para lectura de archivos CSV y Excel.
*/
@RequiredArgsConstructor
public enum ViewSolicitudVisitaHistorialReportColumnEnum implements IExcelColumnPropertiesEnum {

    SOLICITUD_VISITA_HISTORIAL_ID(0, "SOLICITUD_VISITA_HISTORIAL_ID", 400), //
    FECHA_HORA(1, "FECHA_HORA", 400), //
    USUARIO(2, "USUARIO", 400), //
    AMBITO(3, "AMBITO", 400), //
    REVISION(4, "REVISION", 400), //
    RPTA_BY_USUARIO(5, "RPTA_BY_USUARIO", 400), //
    SOLICITUD_VISITA_ID(6, "SOLICITUD_VISITA_ID", 400), //
    ESTADO_SOLICITUD_VISITA_CODE(7, "ESTADO_SOLICITUD_VISITA_CODE", 400), //
    SOLVIS_NRO_ORDEN_SERVICIO(8, "SOLVIS_NRO_ORDEN_SERVICIO", 400), //
    SOLVIS_CODIGO_VISITA(9, "SOLVIS_CODIGO_VISITA", 400), //
    ESV_NOMBRE(10, "ESV_NOMBRE", 400), //
    PREV_ESTADO_SOLICITUD_VISITA_CODE(11, "PREV_ESTADO_SOLICITUD_VISITA_CODE", 400), //
    PREV_ESTADO_NOMBRE(12, "PREV_ESTADO_NOMBRE", 400), //
    PREV_FECHA_HORA(13, "PREV_FECHA_HORA", 400), //
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