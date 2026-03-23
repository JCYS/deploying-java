
/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/models/dto/enums/EntityColumnEnum.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.models.writer.enums;

import com.copeinca.apicopeincaprov.commons.report.excel.IExcelColumnPropertiesEnum;
import lombok.RequiredArgsConstructor;

/**
* Enum que mapea las columnas del DTO SvProveedorDTO con sus índices.
* Utilizado para lectura de archivos CSV y Excel.
*/
@RequiredArgsConstructor
public enum SvProveedorColumnEnum implements IExcelColumnPropertiesEnum {

    PROVEEDOR_ID(0, "PROVEEDOR_ID", 400), //
    ESTADO_CALIDAD_AMBIENTAL_CODE(1, "ESTADO_CALIDAD_AMBIENTAL_CODE", 400), //
    NRO_DOCUMENTO_IDENTIDAD(2, "NRO_DOCUMENTO_IDENTIDAD", 400), //
    NOMBRE_FISCAL(3, "NOMBRE_FISCAL", 400), //
    EMAIL(4, "EMAIL", 400), //
    DIRECCION(5, "DIRECCION", 400), //
    CONTACTO_PRINCIPAL(6, "CONTACTO_PRINCIPAL", 400), //
    CONTACTO_PRINCIPAL_TELEFONO(7, "CONTACTO_PRINCIPAL_TELEFONO", 400), //
    SSO_TIENE_VIGENCIA(8, "SSO_TIENE_VIGENCIA", 400), //
    SSO_FECHA_INICIO_VIGENCIA(9, "SSO_FECHA_INICIO_VIGENCIA", 400), //
    SSO_FECHA_FIN_VIGENCIA(10, "SSO_FECHA_FIN_VIGENCIA", 400), //
    SSO_MOTIVO(11, "SSO_MOTIVO", 400), //
    SSO_USUARIO_EVALUADOR(12, "SSO_USUARIO_EVALUADOR", 400), //
    SSO_FECHA_EVALUACION(13, "SSO_FECHA_EVALUACION", 400), //
    IND_REALIZA_ACTIVIDAD_ALTO_RIESGO(14, "IND_REALIZA_ACTIVIDAD_ALTO_RIESGO", 400), //
    IND_REALIZA_ACTIVIDAD_A_BORDO(15, "IND_REALIZA_ACTIVIDAD_A_BORDO", 400), //
    CA_TIENE_VIGENCIA(16, "CA_TIENE_VIGENCIA", 400), //
    CA_FECHA_INICIO_VIGENCIA(17, "CA_FECHA_INICIO_VIGENCIA", 400), //
    CA_FECHA_FIN_VIGENCIA(18, "CA_FECHA_FIN_VIGENCIA", 400), //
    CA_MOTIVO(19, "CA_MOTIVO", 400), //
    CA_USUARIO_EVALUADOR(20, "CA_USUARIO_EVALUADOR", 400), //
    CA_FECHA_EVALUACION(21, "CA_FECHA_EVALUACION", 400), //
    ORIGEN_REGISTRO(22, "ORIGEN_REGISTRO", 400), //
    ESTADO_SSO_CODE(23, "ESTADO_SSO_CODE", 400), //
    IND_PROVEEDOR_NOTIFICADO_CREACION(24, "IND_PROVEEDOR_NOTIFICADO_CREACION", 400), //
    TELEFONO(25, "TELEFONO", 400), //
    NACIONAL(26, "NACIONAL", 400), //
    IS_DELETED(27, "IS_DELETED", 400), //
    VERSION(28, "VERSION", 400), //
    SEDES(29, "SEDES_NOMBRES", 400), //
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