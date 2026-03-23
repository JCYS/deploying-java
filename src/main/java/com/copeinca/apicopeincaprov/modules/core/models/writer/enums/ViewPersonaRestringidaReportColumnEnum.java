
/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/models/dto/enums/EntityColumnEnum.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.models.writer.enums;

import com.copeinca.apicopeincaprov.commons.report.excel.IExcelColumnPropertiesEnum;
import lombok.RequiredArgsConstructor;

/**
* Enum que mapea las columnas del DTO ViewPersonaRestringidaReportDTO con sus índices.
* Utilizado para lectura de archivos CSV y Excel.
*/
@RequiredArgsConstructor
public enum ViewPersonaRestringidaReportColumnEnum implements IExcelColumnPropertiesEnum {

    PERSONA_RESTRINGIDA_ID(0, "PERSONA_RESTRINGIDA_ID", 400), //
    NUMERO_DOCUMENTO(1, "NUMERO_DOCUMENTO", 400), //
    LICENCIA_CONDUCIR(2, "LICENCIA_CONDUCIR", 400), //
    NOMBRE_COMPLETO(3, "NOMBRE_COMPLETO", 400), //
    USUARIO_REPORTA(4, "USUARIO_REPORTA", 400), //
    RESTRINGIDO_EL(5, "RESTRINGIDO_EL", 400), //
    RESTRINGIDO_HASTA(6, "RESTRINGIDO_HASTA", 400), //
    OBSERVACIONES(7, "OBSERVACIONES", 400), //
    TIPO_DOCUMENTO_IDENTIDAD_CODE(8, "TIPO_DOCUMENTO_IDENTIDAD_CODE", 400), //
    SEDE_ORIGINADORA_ID(9, "SEDE_ORIGINADORA_ID", 400), //
    TDI_NOMBRE(10, "TDI_NOMBRE", 400), //
    SEDE_NOMBRE(11, "SEDE_NOMBRE", 400), //
    SEDE_AMBITO(12, "SEDE_AMBITO", 400), //
    RESTRICCION_ACTIVA(13, "RESTRICCION_ACTIVA", 400), //
    DIAS_RESTRICCION(14, "DIAS_RESTRICCION", 400), //
    TIPO_RESTRICCION(15, "TIPO_RESTRICCION", 400), //
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