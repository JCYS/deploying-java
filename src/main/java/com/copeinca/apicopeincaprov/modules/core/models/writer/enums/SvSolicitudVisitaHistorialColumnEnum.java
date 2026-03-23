
/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/models/dto/enums/EntityColumnEnum.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.models.writer.enums;

import com.copeinca.apicopeincaprov.commons.report.excel.IExcelColumnPropertiesEnum;
import lombok.RequiredArgsConstructor;

/**
* Enum que mapea las columnas del DTO SvSolicitudVisitaHistorialDTO con sus índices.
* Utilizado para lectura de archivos CSV y Excel.
*/
@RequiredArgsConstructor
public enum SvSolicitudVisitaHistorialColumnEnum implements IExcelColumnPropertiesEnum {

    SOLICITUD_VISITA_HISTORIAL_ID(0, "SOLICITUD_VISITA_HISTORIAL_ID", 400), //
    SOLICITUD_VISITA_ID(1, "SOLICITUD_VISITA_ID", 400), //
    ESTADO_SOLICITUD_VISITA_CODE(2, "ESTADO_SOLICITUD_VISITA_CODE", 400), //
    FECHA_HORA(3, "FECHA_HORA", 400), //
    USUARIO(4, "USUARIO", 400), //
    AMBITO(5, "AMBITO", 400), //
    REVISION(6, "REVISION", 400), //
    RPTA_BY_USUARIO(7, "RPTA_BY_USUARIO", 400), //
    IS_DELETED(8, "IS_DELETED", 400), //
    VERSION(9, "VERSION", 400), //
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