/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/writer/imp/EntityWriterServiceImpl.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.writer.imp;

import com.copeinca.apicopeincaprov.commons.report.excel.SimpleExcelColumn;
import com.copeinca.apicopeincaprov.commons.report.excel.SimpleExcelExportUtilities;
import com.copeinca.apicopeincaprov.global.dtos.AttachmentData;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvPersonalSedeDTO;
import com.copeinca.apicopeincaprov.modules.core.models.writer.enums.SvPersonalSedeColumnEnum;
import com.copeinca.apicopeincaprov.modules.core.service.writer.ISvPersonalSedeWriterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
* Implementación del servicio de escritura para SvPersonalSedeEntity
* Actualmente lanza excepciones para métodos no implementados.
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class SvPersonalSedeWriterServiceImpl implements ISvPersonalSedeWriterService {

    @Override
    public AttachmentData exportToCsv(List<SvPersonalSedeDTO> data) {
        log.warn("Método exportToCsv no implementado para SvPersonalSedeEntity");
        throw new UnsupportedOperationException("La exportación a CSV no está implementada para SvPersonalSedeEntity");
    }

    @Override
    public AttachmentData exportToExcel(List<SvPersonalSedeDTO> data) throws Exception {

        log.info("Iniciando exportación a Excel de {} registros de SvPersonalSedeDTO", data != null ? data.size() : 0);

        //--- Crear lista de filas para Excel
        List<List<SimpleExcelColumn>> rows = Optional.ofNullable(data).orElse(new ArrayList<>()).stream()//
                .map(this::defineColumns)//
                .collect(Collectors.toList());

        //--- Generar archivo Excel
        AttachmentData result = SimpleExcelExportUtilities.generateExcelFile(rows, "SvPersonalSedes");

        log.info("Archivo Excel generado exitosamente: {}", result.getName());

        return result;

    }

    /**
     * Define las columnas para un usuario específico
     */
    @Override
    public List<SimpleExcelColumn> defineColumns(SvPersonalSedeDTO dto) {

        return Arrays.asList(

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvPersonalSedeColumnEnum.PERSONAL_SEDE_ID) //
                        .value(dto.getId()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvPersonalSedeColumnEnum.PERSONAL_ID) //
                        .value(dto.getPersonalId()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvPersonalSedeColumnEnum.SEDE_ID) //
                        .value(dto.getSedeId()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvPersonalSedeColumnEnum.IS_ACTIVE) //
                        .value(dto.getIsActive()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvPersonalSedeColumnEnum.IS_DELETED) //
                        .value(dto.getIsDeleted()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvPersonalSedeColumnEnum.VERSION) //
                        .value(dto.getVersion()) //
                        .build());

    }
}
