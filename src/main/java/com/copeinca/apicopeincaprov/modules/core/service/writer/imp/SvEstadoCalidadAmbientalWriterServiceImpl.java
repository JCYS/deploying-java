/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/writer/imp/EntityWriterServiceImpl.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.writer.imp;

import com.copeinca.apicopeincaprov.commons.report.excel.SimpleExcelColumn;
import com.copeinca.apicopeincaprov.commons.report.excel.SimpleExcelExportUtilities;
import com.copeinca.apicopeincaprov.global.dtos.AttachmentData;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvEstadoCalidadAmbientalDTO;
import com.copeinca.apicopeincaprov.modules.core.models.writer.enums.SvEstadoCalidadAmbientalColumnEnum;
import com.copeinca.apicopeincaprov.modules.core.service.writer.ISvEstadoCalidadAmbientalWriterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
* Implementación del servicio de escritura para SvEstadoCalidadAmbientalEntity
* Actualmente lanza excepciones para métodos no implementados.
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class SvEstadoCalidadAmbientalWriterServiceImpl implements ISvEstadoCalidadAmbientalWriterService {

    @Override
    public AttachmentData exportToCsv(List<SvEstadoCalidadAmbientalDTO> data) {
        log.warn("Método exportToCsv no implementado para SvEstadoCalidadAmbientalEntity");
        throw new UnsupportedOperationException("La exportación a CSV no está implementada para SvEstadoCalidadAmbientalEntity");
    }

    @Override
    public AttachmentData exportToExcel(List<SvEstadoCalidadAmbientalDTO> data) throws Exception {

        log.info("Iniciando exportación a Excel de {} registros de SvEstadoCalidadAmbientalDTO", data != null ? data.size() : 0);

        //--- Crear lista de filas para Excel
        List<List<SimpleExcelColumn>> rows = Optional.ofNullable(data).orElse(new ArrayList<>()).stream()//
                .map(this::defineColumns)//
                .collect(Collectors.toList());

        //--- Generar archivo Excel
        AttachmentData result = SimpleExcelExportUtilities.generateExcelFile(rows, "SvEstadoCalidadAmbientals");

        log.info("Archivo Excel generado exitosamente: {}", result.getName());

        return result;

    }

    /**
     * Define las columnas para un usuario específico
     */
    @Override
    public List<SimpleExcelColumn> defineColumns(SvEstadoCalidadAmbientalDTO dto) {

        return Arrays.asList(

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvEstadoCalidadAmbientalColumnEnum.ESTADO_CALIDAD_AMBIENTAL_CODE) //
                        .value(dto.getId()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvEstadoCalidadAmbientalColumnEnum.NAME) //
                        .value(dto.getName()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvEstadoCalidadAmbientalColumnEnum.DESCRIPTION) //
                        .value(dto.getDescription()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvEstadoCalidadAmbientalColumnEnum.IS_DELETED) //
                        .value(dto.getIsDeleted()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvEstadoCalidadAmbientalColumnEnum.VERSION) //
                        .value(dto.getVersion()) //
                        .build());

    }
}
