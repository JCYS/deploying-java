/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/writer/imp/EntityWriterServiceImpl.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.writer.imp;

import com.copeinca.apicopeincaprov.commons.report.excel.SimpleExcelColumn;
import com.copeinca.apicopeincaprov.commons.report.excel.SimpleExcelExportUtilities;
import com.copeinca.apicopeincaprov.global.dtos.AttachmentData;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvAreaDTO;
import com.copeinca.apicopeincaprov.modules.core.models.writer.enums.SvAreaColumnEnum;
import com.copeinca.apicopeincaprov.modules.core.service.writer.ISvAreaWriterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
* Implementación del servicio de escritura para SvAreaEntity
* Actualmente lanza excepciones para métodos no implementados.
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class SvAreaWriterServiceImpl implements ISvAreaWriterService {

    @Override
    public AttachmentData exportToCsv(List<SvAreaDTO> data) {
        log.warn("Método exportToCsv no implementado para SvAreaEntity");
        throw new UnsupportedOperationException("La exportación a CSV no está implementada para SvAreaEntity");
    }

    @Override
    public AttachmentData exportToExcel(List<SvAreaDTO> data) throws Exception {

        log.info("Iniciando exportación a Excel de {} registros de SvAreaDTO", data != null ? data.size() : 0);

        //--- Crear lista de filas para Excel
        List<List<SimpleExcelColumn>> rows = Optional.ofNullable(data).orElse(new ArrayList<>()).stream()//
                .map(this::defineColumns)//
                .collect(Collectors.toList());

        //--- Generar archivo Excel
        AttachmentData result = SimpleExcelExportUtilities.generateExcelFile(rows, "SvAreas");

        log.info("Archivo Excel generado exitosamente: {}", result.getName());

        return result;

    }

    /**
     * Define las columnas para un usuario específico
     */
    @Override
    public List<SimpleExcelColumn> defineColumns(SvAreaDTO dto) {

        return Arrays.asList(

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvAreaColumnEnum.AREA_ID) //
                        .value(dto.getId()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvAreaColumnEnum.NAME) //
                        .value(dto.getName()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvAreaColumnEnum.AMBITO) //
                        .value(dto.getAmbito()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvAreaColumnEnum.OPERATIVA) //
                        .value(dto.getOperativa()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvAreaColumnEnum.SEDE_ID) //
                        .value(dto.getSedeId()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvAreaColumnEnum.IS_DELETED) //
                        .value(dto.getIsDeleted()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvAreaColumnEnum.VERSION) //
                        .value(dto.getVersion()) //
                        .build());

    }
}
