/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/writer/imp/EntityWriterServiceImpl.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.writer.imp;

import com.copeinca.apicopeincaprov.commons.report.excel.SimpleExcelColumn;
import com.copeinca.apicopeincaprov.commons.report.excel.SimpleExcelExportUtilities;
import com.copeinca.apicopeincaprov.global.dtos.AttachmentData;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvSedeDTO;
import com.copeinca.apicopeincaprov.modules.core.models.writer.enums.SvSedeColumnEnum;
import com.copeinca.apicopeincaprov.modules.core.service.writer.ISvSedeWriterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
* Implementación del servicio de escritura para SvSedeEntity
* Actualmente lanza excepciones para métodos no implementados.
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class SvSedeWriterServiceImpl implements ISvSedeWriterService {

    @Override
    public AttachmentData exportToCsv(List<SvSedeDTO> data) {
        log.warn("Método exportToCsv no implementado para SvSedeEntity");
        throw new UnsupportedOperationException("La exportación a CSV no está implementada para SvSedeEntity");
    }

    @Override
    public AttachmentData exportToExcel(List<SvSedeDTO> data) throws Exception {

        log.info("Iniciando exportación a Excel de {} registros de SvSedeDTO", data != null ? data.size() : 0);

        //--- Crear lista de filas para Excel
        List<List<SimpleExcelColumn>> rows = Optional.ofNullable(data).orElse(new ArrayList<>()).stream()//
                .map(this::defineColumns)//
                .collect(Collectors.toList());

        //--- Generar archivo Excel
        AttachmentData result = SimpleExcelExportUtilities.generateExcelFile(rows, "SvSedes");

        log.info("Archivo Excel generado exitosamente: {}", result.getName());

        return result;

    }

    /**
     * Define las columnas para un usuario específico
     */
    @Override
    public List<SimpleExcelColumn> defineColumns(SvSedeDTO dto) {

        return Arrays.asList(

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvSedeColumnEnum.SEDE_ID) //
                        .value(dto.getId()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvSedeColumnEnum.NAME) //
                        .value(dto.getName()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvSedeColumnEnum.AMBITO) //
                        .value(dto.getAmbito()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvSedeColumnEnum.IS_DELETED) //
                        .value(dto.getIsDeleted()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvSedeColumnEnum.VERSION) //
                        .value(dto.getVersion()) //
                        .build());

    }
}
