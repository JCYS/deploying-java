
/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/models/dto/enums/EntityColumnEnum.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.models.writer.enums;

import com.copeinca.apicopeincaprov.commons.report.excel.IExcelColumnPropertiesEnum;
import lombok.RequiredArgsConstructor;

/**
* Enum que mapea las columnas del DTO SvTipoDocumentoPlanillaDTO con sus índices.
* Utilizado para lectura de archivos CSV y Excel.
*/
@RequiredArgsConstructor
public enum SvTipoDocumentoPlanillaColumnEnum implements IExcelColumnPropertiesEnum {

    TIPO_DOCUMENTO_PLANILLA_CODE(0, "TIPO_DOCUMENTO_PLANILLA_CODE", 400), //
    DESCRIPTION(1, "DESCRIPTION", 400), //
    AMBITO(2, "AMBITO", 400), //
    INFLUENCIA(3, "INFLUENCIA", 400), //
    DEPENDENCIA(4, "DEPENDENCIA", 400), //
    CAMPO_PLANTILLA_LF(5, "CAMPO_PLANTILLA_LF", 400), //
    OBLIGATORIO(6, "OBLIGATORIO", 400), //
    VENCIMIENTO(7, "VENCIMIENTO", 400), //
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