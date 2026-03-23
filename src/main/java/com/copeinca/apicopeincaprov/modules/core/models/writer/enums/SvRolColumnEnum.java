
/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/models/dto/enums/EntityColumnEnum.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.models.writer.enums;

import com.copeinca.apicopeincaprov.commons.report.excel.IExcelColumnPropertiesEnum;
import lombok.RequiredArgsConstructor;

/**
* Enum que mapea las columnas del DTO SvRolDTO con sus índices.
* Utilizado para lectura de archivos CSV y Excel.
*/
@RequiredArgsConstructor
public enum SvRolColumnEnum implements IExcelColumnPropertiesEnum {

    ROL_CODE(0, "ROL_CODE", 400), //
    ROL_NAME(1, "ROL_NAME", 400), //
    ROL_DESCRIPTION(2, "ROL_DESCRIPTION", 400), //
    IS_DELETED(3, "IS_DELETED", 400), //
    VERSION(4, "VERSION", 400), //
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