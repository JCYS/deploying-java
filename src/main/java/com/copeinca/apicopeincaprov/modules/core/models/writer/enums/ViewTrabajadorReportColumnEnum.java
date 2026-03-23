
/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/models/dto/enums/EntityColumnEnum.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.models.writer.enums;

import com.copeinca.apicopeincaprov.commons.report.excel.IExcelColumnPropertiesEnum;
import lombok.RequiredArgsConstructor;

/**
* Enum que mapea las columnas del DTO ViewTrabajadorReportDTO con sus índices.
* Utilizado para lectura de archivos CSV y Excel.
*/
@RequiredArgsConstructor
public enum ViewTrabajadorReportColumnEnum implements IExcelColumnPropertiesEnum {

    TRABAJADOR_ID(0, "TRABAJADOR_ID", 400), //
    PROVEEDOR_ID(1, "PROVEEDOR_ID", 400), //
    PROV_NRO_DOCUMENTO_IDENTIDAD(2, "PROV_NRO_DOCUMENTO_IDENTIDAD", 400), //
    PROV_NOMBRE_FISCAL(3, "PROV_NOMBRE_FISCAL", 400), //
    TIPO_DOCUMENTO_IDENTIDAD_CODE(4, "TIPO_DOCUMENTO_IDENTIDAD_CODE", 400), //
    NRO_DOCUMENTO_IDENTIDAD(5, "NRO_DOCUMENTO_IDENTIDAD", 400), //
    TDI_NOMBRE(6, "TDI_NOMBRE", 400), //
    NOMBRE(7, "NOMBRE", 400), //
    TELEFONO(8, "TELEFONO", 400), //
    EMAIL(9, "EMAIL", 400), //
    ESTADO_SSO_CODE(10, "ESTADO_SSO_CODE", 400), //
    SSO_MOTIVO(11, "SSO_MOTIVO", 400), //
    SO_TIENE_VIGENCIA(12, "SO_TIENE_VIGENCIA", 400), //
    SSO_FECHA_INICIO_VIGENCIA(13, "SSO_FECHA_INICIO_VIGENCIA", 400), //
    SSO_FECHA_FIN_VIGENCIA(14, "SSO_FECHA_FIN_VIGENCIA", 400), //
    IS_DELETED(15, "IS_DELETED", 400), //
    IS_ACTIVE(16, "IS_ACTIVE", 400), //
    SSO_ESTADO_NOMBRE(17, "SSO_ESTADO_NOMBRE", 400), //
    SSO_ESTADO_VIGENTE(18, "SSO_ESTADO_VIGENTE", 400), //
    IND_TRABAJO_ALTO_RIESGO(19, "IND_TRABAJO_ALTO_RIESGO", 400), //
    IND_PREVENCIONISTA(20, "IND_PREVENCIONISTA", 400), //
    TAR_CODIGOS(21, "TAR_CODIGOS", 400), //
    TAR_DESCRIPCIONES(22, "TAR_DESCRIPCIONES", 400), //
    N_DOCS_SSO_ADJUNTOS(23, "N_DOCS_SSO_ADJUNTOS", 400), //
    N_DOCS_CALIDAD_ADJUNTOS(24, "N_DOCS_CALIDAD_ADJUNTOS", 400), //
    N_DOCS_AAR_ADJUNTOS(25, "N_DOCS_AAR_ADJUNTOS", 400), //
    N_DOCS_AB_ADJUNTOS(26, "N_DOCS_AB_ADJUNTOS", 400), //
    N_DOCS_PREV_ADJUNTOS(27, "N_DOCS_PREV_ADJUNTOS", 400),
    N_DOCS_TOTALES(27, "N_DOCS_TOTALES", 400),
    N_DOCS_TOTALES_VIGENTES(27, "N_DOCS_TOTALES_VIGENTES", 400),//
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