
/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/models/dto/enums/EntityColumnEnum.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.models.writer.enums;

import com.copeinca.apicopeincaprov.commons.report.excel.IExcelColumnPropertiesEnum;
import lombok.RequiredArgsConstructor;

/**
* Enum que mapea las columnas del DTO SvPersonaRestringidaDTO con sus índices.
* Utilizado para lectura de archivos CSV y Excel.
*/
@RequiredArgsConstructor
public enum SvPersonaRestringidaColumnEnum implements IExcelColumnPropertiesEnum {

    PERSONA_RESTRINGIDA_ID(0, "PERSONA_RESTRINGIDA_ID", 400), //
    TIPO_DOCUMENTO_IDENTIDAD_CODE(1, "TIPO_DOCUMENTO_IDENTIDAD_CODE", 400), //
    NUMERO_DOCUMENTO(2, "NUMERO_DOCUMENTO", 400), //
    LICENCIA_CONDUCIR(3, "LICENCIA_CONDUCIR", 400), //
    NOMBRE_COMPLETO(4, "NOMBRE_COMPLETO", 400), //
    USUARIO_REPORTA(5, "USUARIO_REPORTA", 400), //
    RESTRINGIDO_EL(6, "RESTRINGIDO_EL", 400), //
    RESTRINGIDO_HASTA(7, "RESTRINGIDO_HASTA", 400), //
    OBSERVACIONES(8, "OBSERVACIONES", 400), //
    SEDE_ORIGINADORA_ID(9, "SEDE_ORIGINADORA_ID", 400), //
    IS_DELETED(10, "IS_DELETED", 400), //
    VERSION(11, "VERSION", 400), //
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