
/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/models/dto/enums/EntityColumnEnum.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.models.writer.enums;

import com.copeinca.apicopeincaprov.commons.report.excel.IExcelColumnPropertiesEnum;
import lombok.RequiredArgsConstructor;

/**
* Enum que mapea las columnas del DTO SvSolicitudVisitaEventoDTO con sus índices.
* Utilizado para lectura de archivos CSV y Excel.
*/
@RequiredArgsConstructor
public enum SvSolicitudVisitaEventoColumnEnum implements IExcelColumnPropertiesEnum {

    SOLICITUD_VISITA_EVENTO_ID(0, "SOLICITUD_VISITA_EVENTO_ID", 400), //
    FECHA_EVENTO(1, "FECHA_EVENTO", 400), //
    USUARIO(2, "USUARIO", 400), //
    EVENTO(3, "EVENTO", 400), //
    AMBITO(4, "AMBITO", 400), //
    REVISION(5, "REVISION", 400), //
    RESPUESTA_USUARIO(6, "RESPUESTA_USUARIO", 400), //
    SOLICITUD_VISITA_ID(7, "SOLICITUD_VISITA_ID", 400), //
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