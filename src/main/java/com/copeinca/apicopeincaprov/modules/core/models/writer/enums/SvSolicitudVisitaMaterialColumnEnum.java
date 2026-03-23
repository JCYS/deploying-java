
/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/models/dto/enums/EntityColumnEnum.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.models.writer.enums;

import com.copeinca.apicopeincaprov.commons.report.excel.IExcelColumnPropertiesEnum;
import lombok.RequiredArgsConstructor;

/**
* Enum que mapea las columnas del DTO SvSolicitudVisitaMaterialDTO con sus índices.
* Utilizado para lectura de archivos CSV y Excel.
*/
@RequiredArgsConstructor
public enum SvSolicitudVisitaMaterialColumnEnum implements IExcelColumnPropertiesEnum {

    SOLICITUD_VISITA_MATERIAL_ID(0, "SOLICITUD_VISITA_MATERIAL_ID", 400), //
    DESCRIPCION(1, "DESCRIPCION", 400), //
    CANTIDAD(2, "CANTIDAD", 400), //
    UNIDAD_MEDIDA(3, "UNIDAD_MEDIDA", 400), //
    SOLICITUD_VISITA_ID(4, "SOLICITUD_VISITA_ID", 400), //
    IS_DELETED(5, "IS_DELETED", 400), //
    VERSION(6, "VERSION", 400), //
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