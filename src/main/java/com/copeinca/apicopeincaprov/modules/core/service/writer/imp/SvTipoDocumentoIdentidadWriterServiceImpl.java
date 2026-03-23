/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/writer/imp/EntityWriterServiceImpl.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.writer.imp;

import com.copeinca.apicopeincaprov.commons.report.excel.SimpleExcelColumn;
import com.copeinca.apicopeincaprov.commons.report.excel.SimpleExcelExportUtilities;
import com.copeinca.apicopeincaprov.global.dtos.AttachmentData;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvTipoDocumentoIdentidadDTO;
import com.copeinca.apicopeincaprov.modules.core.models.writer.enums.SvTipoDocumentoIdentidadColumnEnum;
import com.copeinca.apicopeincaprov.modules.core.service.writer.ISvTipoDocumentoIdentidadWriterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
* Implementación del servicio de escritura para SvTipoDocumentoIdentidadEntity
* Actualmente lanza excepciones para métodos no implementados.
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class SvTipoDocumentoIdentidadWriterServiceImpl implements ISvTipoDocumentoIdentidadWriterService {

    @Override
    public AttachmentData exportToCsv(List<SvTipoDocumentoIdentidadDTO> data) {
        log.warn("Método exportToCsv no implementado para SvTipoDocumentoIdentidadEntity");
        throw new UnsupportedOperationException("La exportación a CSV no está implementada para SvTipoDocumentoIdentidadEntity");
    }

    @Override
    public AttachmentData exportToExcel(List<SvTipoDocumentoIdentidadDTO> data) throws Exception {

        log.info("Iniciando exportación a Excel de {} registros de SvTipoDocumentoIdentidadDTO", data != null ? data.size() : 0);

        //--- Crear lista de filas para Excel
        List<List<SimpleExcelColumn>> rows = Optional.ofNullable(data).orElse(new ArrayList<>()).stream()//
                .map(this::defineColumns)//
                .collect(Collectors.toList());

        //--- Generar archivo Excel
        AttachmentData result = SimpleExcelExportUtilities.generateExcelFile(rows, "SvTipoDocumentoIdentidads");

        log.info("Archivo Excel generado exitosamente: {}", result.getName());

        return result;

    }

    /**
     * Define las columnas para un usuario específico
     */
    @Override
    public List<SimpleExcelColumn> defineColumns(SvTipoDocumentoIdentidadDTO dto) {

        return Arrays.asList(

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvTipoDocumentoIdentidadColumnEnum.TIPO_DOCUMENTO_IDENTIDAD_CODE) //
                        .value(dto.getId()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvTipoDocumentoIdentidadColumnEnum.NAME) //
                        .value(dto.getName()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvTipoDocumentoIdentidadColumnEnum.IS_ACTIVE) //
                        .value(dto.getIsActive()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvTipoDocumentoIdentidadColumnEnum.EXPRESION_REGULAR) //
                        .value(dto.getExpresionRegular()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvTipoDocumentoIdentidadColumnEnum.IS_DELETED) //
                        .value(dto.getIsDeleted()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvTipoDocumentoIdentidadColumnEnum.VERSION) //
                        .value(dto.getVersion()) //
                        .build());

    }
}
