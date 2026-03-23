
/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/models/dto/enums/EntityColumnEnum.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.models.writer.enums;

import com.copeinca.apicopeincaprov.commons.report.excel.IExcelColumnPropertiesEnum;
import lombok.RequiredArgsConstructor;

/**
* Enum que mapea las columnas del DTO ViewPersonalReportDTO con sus índices.
* Utilizado para lectura de archivos CSV y Excel.
*/
@RequiredArgsConstructor
public enum ViewPersonalReportColumnEnum implements IExcelColumnPropertiesEnum {

    PERSONAL_SEDE_ID(0, "PERSONAL_SEDE_ID", 400), //
    IS_ACTIVE(1, "IS_ACTIVE", 400), //
    PERSONAL_ID(2, "PERSONAL_ID", 400), //
    SEDE_ID(3, "SEDE_ID", 400), //
    PERSONAL_USUARIO(4, "PERSONAL_USUARIO", 400), //
    PERSONAL_NOMBRE_USUARIO(5, "PERSONAL_NOMBRE_USUARIO", 400), //
    PERSONAL_CODIGO(6, "PERSONAL_CODIGO", 400), //
    PERSONAL_DESCRIPCION(7, "PERSONAL_DESCRIPCION", 400), //
    SEDE_NAME(8, "SEDE_NAME", 400), //
    SEDE_AMBITO(9, "SEDE_AMBITO", 400), //
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