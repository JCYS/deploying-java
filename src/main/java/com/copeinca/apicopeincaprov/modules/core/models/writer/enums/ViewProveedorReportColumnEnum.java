
/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/models/dto/enums/EntityColumnEnum.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.models.writer.enums;

import com.copeinca.apicopeincaprov.commons.report.excel.IExcelColumnPropertiesEnum;
import lombok.RequiredArgsConstructor;

/**
* Enum que mapea las columnas del DTO ViewProveedorReportDTO con sus índices.
* Utilizado para lectura de archivos CSV y Excel.
*/
@RequiredArgsConstructor
public enum ViewProveedorReportColumnEnum implements IExcelColumnPropertiesEnum {

    PROVEEDOR_ID(0, "PROVEEDOR_ID", 400), //
    NRO_DOCUMENTO_IDENTIDAD(1, "NRO_DOCUMENTO_IDENTIDAD", 400), //
    NOMBRE_FISCAL(2, "NOMBRE_FISCAL", 400), //
    EMAIL(3, "EMAIL", 400), //
    DIRECCION(4, "DIRECCION", 400), //
    TELEFONO(5, "TELEFONO", 400), //
    NACIONAL(6, "NACIONAL", 400), //
    CONTACTO_PRINCIPAL(7, "CONTACTO_PRINCIPAL", 400), //
    CONTACTO_PRINCIPAL_TELEFONO(8, "CONTACTO_PRINCIPAL_TELEFONO", 400), //
    ORIGEN_REGISTRO(9, "ORIGEN_REGISTRO", 400), //
    SEDES_IDS(10, "SEDES_IDS", 400), //
    SEDES_NOMBRES(11, "SEDES_NOMBRES", 400), //
    ESTADO_SSO_CODE(12, "ESTADO_SSO_CODE", 400), //
    SSO_TIENE_VIGENCIA(13, "SSO_TIENE_VIGENCIA", 400), //
    SSO_FECHA_INICIO_VIGENCIA(14, "SSO_FECHA_INICIO_VIGENCIA", 400), //
    SSO_FECHA_FIN_VIGENCIA(15, "SSO_FECHA_FIN_VIGENCIA", 400), //
    SSO_MOTIVO(16, "SSO_MOTIVO", 400), //
    SSO_USUARIO_EVALUADOR(17, "SSO_USUARIO_EVALUADOR", 400), //
    SSO_FECHA_EVALUACION(18, "SSO_FECHA_EVALUACION", 400), //
    SSO_ESTADO_NOMBRE(19, "SSO_ESTADO_NOMBRE", 400), //
    SSO_ESTADO_DESCRIPCION(20, "SSO_ESTADO_DESCRIPCION", 400), //
    SSO_ESTADO_VIGENTE(21, "SSO_ESTADO_VIGENTE", 400), //
    ESTADO_CALIDAD_AMBIENTAL_CODE(22, "ESTADO_CALIDAD_AMBIENTAL_CODE", 400), //
    CA_TIENE_VIGENCIA(23, "CA_TIENE_VIGENCIA", 400), //
    CA_FECHA_INICIO_VIGENCIA(24, "CA_FECHA_INICIO_VIGENCIA", 400), //
    CA_FECHA_FIN_VIGENCIA(25, "CA_FECHA_FIN_VIGENCIA", 400), //
    CA_MOTIVO(26, "CA_MOTIVO", 400), //
    CA_USUARIO_EVALUADOR(27, "CA_USUARIO_EVALUADOR", 400), //
    CA_FECHA_EVALUACION(28, "CA_FECHA_EVALUACION", 400), //
    CA_ESTADO_NOMBRE(29, "CA_ESTADO_NOMBRE", 400), //
    CA_ESTADO_DESCRIPCION(30, "CA_ESTADO_DESCRIPCION", 400), //
    CA_ESTADO_VIGENTE(31, "CA_ESTADO_VIGENTE", 400), //
    N_TRABAJADORES_APTOS(32, "N_TRABAJADORES_APTOS", 400), //
    N_TRABAJADORES_TOTALES(33, "N_TRABAJADORES_TOTALES", 400), //
    N_DOCS_PROV_SSO(34, "N_DOCS_PROV_SSO", 400), //
    N_DOCS_CA(35, "N_DOCS_CA", 400), //
    N_DOCS_AAR(36, "N_DOCS_AAR", 400), //
    N_DOCS_AB(37, "N_DOCS_AB", 400), //
    N_DOCS_TRABAJ_SSO(38, "N_DOCS_TRABAJ_SSO", 400), //
    IND_REALIZA_ACTIVIDAD_ALTO_RIESGO(39, "IND_REALIZA_ACTIVIDAD_ALTO_RIESGO", 400), //
    IND_REALIZA_ACTIVIDAD_A_BORDO(40, "IND_REALIZA_ACTIVIDAD_A_BORDO", 400), //
    TAR_CODIGOS(41, "TAR_CODIGOS", 400), //
    TAR_DESCRIPCIONES(42, "TAR_DESCRIPCIONES", 400), //
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