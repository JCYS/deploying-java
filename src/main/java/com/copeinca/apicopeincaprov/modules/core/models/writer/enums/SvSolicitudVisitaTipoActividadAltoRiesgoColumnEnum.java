
/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/models/dto/enums/EntityColumnEnum.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.models.writer.enums;

import com.copeinca.apicopeincaprov.commons.report.excel.IExcelColumnPropertiesEnum;
import lombok.RequiredArgsConstructor;

/**
* Enum que mapea las columnas del DTO SvSolicitudVisitaTipoActividadAltoRiesgoDTO con sus índices.
* Utilizado para lectura de archivos CSV y Excel.
*/
@RequiredArgsConstructor
public enum SvSolicitudVisitaTipoActividadAltoRiesgoColumnEnum implements IExcelColumnPropertiesEnum {

    SOLICITUD_VISITA_TIPO_ACTIVIDAD_ALTO_RIESGO_ID(0, "SOLICITUD_VISITA_TIPO_ACTIVIDAD_ALTO_RIESGO_ID", 400), //
    IS_ACTIVE(1, "IS_ACTIVE", 400), //
    TIPO_ACTIVIDAD_ALTO_RIESGO_CODE(2, "TIPO_ACTIVIDAD_ALTO_RIESGO_CODE", 400), //
    SOLICITUD_VISITA_ID(3, "SOLICITUD_VISITA_ID", 400), //
    IS_DELETED(4, "IS_DELETED", 400), //
    VERSION(5, "VERSION", 400), //
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