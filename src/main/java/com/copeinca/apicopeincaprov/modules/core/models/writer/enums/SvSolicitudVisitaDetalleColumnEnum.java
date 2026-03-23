
/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/models/dto/enums/EntityColumnEnum.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.models.writer.enums;

import com.copeinca.apicopeincaprov.commons.report.excel.IExcelColumnPropertiesEnum;
import lombok.RequiredArgsConstructor;

/**
* Enum que mapea las columnas del DTO SvSolicitudVisitaDetalleDTO con sus índices.
* Utilizado para lectura de archivos CSV y Excel.
*/
@RequiredArgsConstructor
public enum SvSolicitudVisitaDetalleColumnEnum implements IExcelColumnPropertiesEnum {

    SOLICITUD_VISITA_DETALLE_ID(0, "SOLICITUD_VISITA_DETALLE_ID", 400), //
    PROVEEDOR_COMENTARIO(1, "PROVEEDOR_COMENTARIO", 400), //
    SOLICITUD_VISITA_ID(2, "SOLICITUD_VISITA_ID", 400), //
    TRABAJADOR_ID(3, "TRABAJADOR_ID", 400), //
    IS_DELETED(4, "IS_DELETED", 400), //
    VERSION(5, "VERSION", 400), //
    TIPO_DOCUMENTO(6, "TIPO_DOCUMENTO", 400), //
    NUM_DOCUMENTO(7, "NUM_DOCUMENTO", 400), //
    NOMBRE(8, "NOMBRE", 400), //
    ESTADO_SSO(9, "ESTADO_SSO", 400), //
    NUM_VISITA(10, "NUM_VISITA", 400),
    SSO_FECHA_FIN_VIGENCIA(10, "SSO_FECHA_FIN_VIGENCIA", 400), //
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