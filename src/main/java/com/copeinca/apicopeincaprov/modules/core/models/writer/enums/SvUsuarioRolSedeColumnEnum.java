
/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/models/dto/enums/EntityColumnEnum.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.models.writer.enums;

import com.copeinca.apicopeincaprov.commons.report.excel.IExcelColumnPropertiesEnum;
import lombok.RequiredArgsConstructor;

/**
* Enum que mapea las columnas del DTO SvUsuarioRolSedeDTO con sus índices.
* Utilizado para lectura de archivos CSV y Excel.
*/
@RequiredArgsConstructor
public enum SvUsuarioRolSedeColumnEnum implements IExcelColumnPropertiesEnum {

    USUARIO_ROL_SEDE_ID(0, "USUARIO_ROL_SEDE_ID", 400), //
    USUARIO_ID(1, "USUARIO_ID", 400), //
    ROL_CODE(2, "ROL_CODE", 400), //
    SEDE_ID(3, "SEDE_ID", 400), //
    IS_ACTIVE(4, "IS_ACTIVE", 400), //
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