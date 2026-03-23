
/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/models/dto/enums/EntityColumnEnum.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.models.writer.enums;

import com.copeinca.apicopeincaprov.commons.report.excel.IExcelColumnPropertiesEnum;
import lombok.RequiredArgsConstructor;

/**
* Enum que mapea las columnas del DTO SvAdjuntoDTO con sus índices.
* Utilizado para lectura de archivos CSV y Excel.
*/
@RequiredArgsConstructor
public enum SvAdjuntoColumnEnum implements IExcelColumnPropertiesEnum {

    ADJUNTO_ID(0, "ADJUNTO_ID", 400), //
    NOMBRE_ARCHIVO(1, "NOMBRE_ARCHIVO", 400), //
    ID_REPOSITORIO(2, "ID_REPOSITORIO", 400), //
    CLASIFICATION(3, "CLASIFICATION", 400), //
    FECHA_DOCUMENTO(4, "FECHA_DOCUMENTO", 400), //
    FECHA_VENCIMIENTO(5, "FECHA_VENCIMIENTO", 400), //
    RUTA_RELATIVA(6, "RUTA_RELATIVA", 400), //
    SSO_COMENTARIO_REVISION(7, "SSO_COMENTARIO_REVISION", 400), //
    CA_COMENTARIO_REVISION(8, "CA_COMENTARIO_REVISION", 400), //
    MOTIVO(9, "MOTIVO", 400), //
    PROVEEDOR_ID(10, "PROVEEDOR_ID", 400), //
    TRABAJADOR_ID(11, "TRABAJADOR_ID", 400), //
    TIPO_DOCUMENTO_PLANILLA_CODE(12, "TIPO_DOCUMENTO_PLANILLA_CODE", 400), //
    SOLICITUD_VISITA_ID(13, "SOLICITUD_VISITA_ID", 400), //
    TIPO_ACTIVIDAD_ALTO_RIESGO_CODE(14, "TIPO_ACTIVIDAD_ALTO_RIESGO_CODE", 400), //
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