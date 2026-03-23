
/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/models/dto/enums/EntityColumnEnum.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.models.writer.enums;

import com.copeinca.apicopeincaprov.commons.report.excel.IExcelColumnPropertiesEnum;
import lombok.RequiredArgsConstructor;

/**
* Enum que mapea las columnas del DTO SvTipoDocumentoIdentidadDTO con sus índices.
* Utilizado para lectura de archivos CSV y Excel.
*/
@RequiredArgsConstructor
public enum SvTipoDocumentoIdentidadColumnEnum implements IExcelColumnPropertiesEnum {

    TIPO_DOCUMENTO_IDENTIDAD_CODE(0, "TIPO_DOCUMENTO_IDENTIDAD_CODE", 400), //
    NAME(1, "NAME", 400), //
    IS_ACTIVE(2, "IS_ACTIVE", 400), //
    EXPRESION_REGULAR(3, "EXPRESION_REGULAR", 400), //
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