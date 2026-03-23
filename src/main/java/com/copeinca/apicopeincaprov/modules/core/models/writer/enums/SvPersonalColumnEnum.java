
/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/models/dto/enums/EntityColumnEnum.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.models.writer.enums;

import com.copeinca.apicopeincaprov.commons.report.excel.IExcelColumnPropertiesEnum;
import lombok.RequiredArgsConstructor;

/**
* Enum que mapea las columnas del DTO SvPersonalDTO con sus índices.
* Utilizado para lectura de archivos CSV y Excel.
*/
@RequiredArgsConstructor
public enum SvPersonalColumnEnum implements IExcelColumnPropertiesEnum {

    PERSONAL_ID(0, "PERSONAL_ID", 400), //
    USUARIO(1, "USUARIO", 400), //
    NOMBRE_USUARIO(2, "NOMBRE_USUARIO", 400), //
    CODIGO(3, "CODIGO", 400), //
    DESCRIPCION(4, "DESCRIPCION", 400), //
    AMBITO(5, "AMBITO", 400), //
    INFLUENCIA(6, "INFLUENCIA", 400), //
    DEPENDENCIA(7, "DEPENDENCIA", 400), //
    CAMPO_PLANTILLA_LF(8, "CAMPO_PLANTILLA_LF", 400), //
    OBLIGATORIO(9, "OBLIGATORIO", 400), //
    VENCIMIENTO(10, "VENCIMIENTO", 400), //
    NUMERO_CASO(11, "NUMERO_CASO", 400), //
    MENSAJE(12, "MENSAJE", 400), //
    TIPO_DOCUMENTO_IDENTIDAD_CODE(13, "TIPO_DOCUMENTO_IDENTIDAD_CODE", 400), //
    IS_DELETED(14, "IS_DELETED", 400), //
    VERSION(15, "VERSION", 400), //
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