/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/writer/imp/EntityWriterServiceImpl.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.writer.imp;

import com.copeinca.apicopeincaprov.commons.report.excel.SimpleExcelColumn;
import com.copeinca.apicopeincaprov.commons.report.excel.SimpleExcelExportUtilities;
import com.copeinca.apicopeincaprov.global.dtos.AttachmentData;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvZonaDTO;
import com.copeinca.apicopeincaprov.modules.core.models.writer.enums.SvZonaColumnEnum;
import com.copeinca.apicopeincaprov.modules.core.service.writer.ISvZonaWriterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
* Implementación del servicio de escritura para SvZonaEntity
* Actualmente lanza excepciones para métodos no implementados.
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class SvZonaWriterServiceImpl implements ISvZonaWriterService {

    @Override
    public AttachmentData exportToCsv(List<SvZonaDTO> data) {
        log.warn("Método exportToCsv no implementado para SvZonaEntity");
        throw new UnsupportedOperationException("La exportación a CSV no está implementada para SvZonaEntity");
    }

    @Override
    public AttachmentData exportToExcel(List<SvZonaDTO> data) throws Exception {

        log.info("Iniciando exportación a Excel de {} registros de SvZonaDTO", data != null ? data.size() : 0);

        //--- Crear lista de filas para Excel
        List<List<SimpleExcelColumn>> rows = Optional.ofNullable(data).orElse(new ArrayList<>()).stream()//
                .map(this::defineColumns)//
                .collect(Collectors.toList());

        //--- Generar archivo Excel
        AttachmentData result = SimpleExcelExportUtilities.generateExcelFile(rows, "SvZonas");

        log.info("Archivo Excel generado exitosamente: {}", result.getName());

        return result;

    }

    /**
     * Define las columnas para un usuario específico
     */
    @Override
    public List<SimpleExcelColumn> defineColumns(SvZonaDTO dto) {

        return Arrays.asList(

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvZonaColumnEnum.ZONA_ID) //
                        .value(dto.getId()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvZonaColumnEnum.NAME) //
                        .value(dto.getName()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvZonaColumnEnum.OPERATIVA) //
                        .value(dto.getOperativa()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvZonaColumnEnum.SEDE_ID) //
                        .value(dto.getSedeId()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvZonaColumnEnum.IS_DELETED) //
                        .value(dto.getIsDeleted()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvZonaColumnEnum.VERSION) //
                        .value(dto.getVersion()) //
                        .build());

    }
}
