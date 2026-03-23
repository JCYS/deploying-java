
/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/models/dto/enums/EntityColumnEnum.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.models.writer.enums;

import com.copeinca.apicopeincaprov.commons.report.excel.IExcelColumnPropertiesEnum;
import lombok.RequiredArgsConstructor;

/**
* Enum que mapea las columnas del DTO SvTrabajadorDTO con sus índices.
* Utilizado para lectura de archivos CSV y Excel.
*/
@RequiredArgsConstructor
public enum SvTrabajadorColumnEnum implements IExcelColumnPropertiesEnum {

    TRABAJADOR_ID(10, "TRABAJADOR_ID", 400), //
    PROVEEDOR_ID(10, "PROVEEDOR_ID", 400), //
    TIPO_DOCUMENTO_IDENTIDAD_CODE(0, "TIPO_DOCUMENTO_IDENTIDAD_CODE", 400), //
    NRO_DOCUMENTO_IDENTIDAD(1, "NRO_DOCUMENTO_IDENTIDAD", 400), //
    NOMBRE(2, "NOMBRE", 400), //
    TELEFONO(3, "TELEFONO", 400), //
    EMAIL(4, "EMAIL", 400), //
    SSO_MOTIVO(70, "SSO_MOTIVO", 400), //
    SO_TIENE_VIGENCIA(80, "SO_TIENE_VIGENCIA", 400), //
    SSO_FECHA_INICIO_VIGENCIA(90, "SSO_FECHA_INICIO_VIGENCIA", 400), //
    SSO_FECHA_FIN_VIGENCIA(10, "SSO_FECHA_FIN_VIGENCIA", 400), //
    IND_TRABAJO_ALTO_RIESGO(5, "IND_TRABAJO_ALTO_RIESGO", 400), //
    IND_PREVENCIONISTA(6, "IND_PREVENCIONISTA", 400), //
    ESTADO_SSO_CODE(13, "ESTADO_SSO_CODE", 400), //
    IS_ACTIVE(14, "IS_ACTIVE", 400), //
    IS_DELETED(15, "IS_DELETED", 400), //
    VERSION(16, "VERSION", 400), //
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