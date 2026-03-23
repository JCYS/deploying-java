
/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/models/dto/enums/EntityColumnEnum.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.models.writer.enums;

import com.copeinca.apicopeincaprov.commons.report.excel.IExcelColumnPropertiesEnum;
import lombok.RequiredArgsConstructor;

/**
* Enum que mapea las columnas del DTO SvSolicitudVisitaEquipoDTO con sus índices.
* Utilizado para lectura de archivos CSV y Excel.
*/
@RequiredArgsConstructor
public enum SvSolicitudVisitaEquipoColumnEnum implements IExcelColumnPropertiesEnum {

    SOLICITUD_VISITA_EQUIPO_ID(0, "SOLICITUD_VISITA_EQUIPO_ID", 400), //
    DESCRIPCION(1, "DESCRIPCION", 400), //
    MARCA(2, "MARCA", 400), //
    MODELO(3, "MODELO", 400), //
    NUMERO_SERIE(4, "NUMERO_SERIE", 400), //
    TIPO_EQUIPO(5, "TIPO_EQUIPO", 400), //
    CODIGO_EQUIPO(6, "CODIGO_EQUIPO", 400), //
    CANTIDAD(7, "CANTIDAD", 400), //
    SOLICITUD_VISITA_ID(8, "SOLICITUD_VISITA_ID", 400), //
    IS_DELETED(9, "IS_DELETED", 400), //
    VERSION(10, "VERSION", 400), //
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