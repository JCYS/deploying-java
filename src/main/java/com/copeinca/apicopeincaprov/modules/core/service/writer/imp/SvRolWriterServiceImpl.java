/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/writer/imp/EntityWriterServiceImpl.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.writer.imp;

import com.copeinca.apicopeincaprov.commons.report.excel.SimpleExcelColumn;
import com.copeinca.apicopeincaprov.commons.report.excel.SimpleExcelExportUtilities;
import com.copeinca.apicopeincaprov.global.dtos.AttachmentData;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvRolDTO;
import com.copeinca.apicopeincaprov.modules.core.models.writer.enums.SvRolColumnEnum;
import com.copeinca.apicopeincaprov.modules.core.service.writer.ISvRolWriterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
* Implementación del servicio de escritura para SvRolEntity
* Actualmente lanza excepciones para métodos no implementados.
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class SvRolWriterServiceImpl implements ISvRolWriterService {

    @Override
    public AttachmentData exportToCsv(List<SvRolDTO> data) {
        log.warn("Método exportToCsv no implementado para SvRolEntity");
        throw new UnsupportedOperationException("La exportación a CSV no está implementada para SvRolEntity");
    }

    @Override
    public AttachmentData exportToExcel(List<SvRolDTO> data) throws Exception {

        log.info("Iniciando exportación a Excel de {} registros de SvRolDTO", data != null ? data.size() : 0);

        //--- Crear lista de filas para Excel
        List<List<SimpleExcelColumn>> rows = Optional.ofNullable(data).orElse(new ArrayList<>()).stream()//
                .map(this::defineColumns)//
                .collect(Collectors.toList());

        //--- Generar archivo Excel
        AttachmentData result = SimpleExcelExportUtilities.generateExcelFile(rows, "SvRols");

        log.info("Archivo Excel generado exitosamente: {}", result.getName());

        return result;

    }

    /**
     * Define las columnas para un usuario específico
     */
    @Override
    public List<SimpleExcelColumn> defineColumns(SvRolDTO dto) {

        return Arrays.asList(

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvRolColumnEnum.ROL_CODE) //
                        .value(dto.getId()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvRolColumnEnum.ROL_NAME) //
                        .value(dto.getRolName()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvRolColumnEnum.ROL_DESCRIPTION) //
                        .value(dto.getRolDescription()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvRolColumnEnum.IS_DELETED) //
                        .value(dto.getIsDeleted()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvRolColumnEnum.VERSION) //
                        .value(dto.getVersion()) //
                        .build());

    }
}
