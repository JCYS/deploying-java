/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/writer/imp/EntityWriterServiceImpl.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.writer.imp;

import com.copeinca.apicopeincaprov.commons.report.excel.SimpleExcelColumn;
import com.copeinca.apicopeincaprov.commons.report.excel.SimpleExcelExportUtilities;
import com.copeinca.apicopeincaprov.global.dtos.AttachmentData;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvEstadoSsoDTO;
import com.copeinca.apicopeincaprov.modules.core.models.writer.enums.SvEstadoSsoColumnEnum;
import com.copeinca.apicopeincaprov.modules.core.service.writer.ISvEstadoSsoWriterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
* Implementación del servicio de escritura para SvEstadoSsoEntity
* Actualmente lanza excepciones para métodos no implementados.
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class SvEstadoSsoWriterServiceImpl implements ISvEstadoSsoWriterService {

    @Override
    public AttachmentData exportToCsv(List<SvEstadoSsoDTO> data) {
        log.warn("Método exportToCsv no implementado para SvEstadoSsoEntity");
        throw new UnsupportedOperationException("La exportación a CSV no está implementada para SvEstadoSsoEntity");
    }

    @Override
    public AttachmentData exportToExcel(List<SvEstadoSsoDTO> data) throws Exception {

        log.info("Iniciando exportación a Excel de {} registros de SvEstadoSsoDTO", data != null ? data.size() : 0);

        //--- Crear lista de filas para Excel
        List<List<SimpleExcelColumn>> rows = Optional.ofNullable(data).orElse(new ArrayList<>()).stream()//
                .map(this::defineColumns)//
                .collect(Collectors.toList());

        //--- Generar archivo Excel
        AttachmentData result = SimpleExcelExportUtilities.generateExcelFile(rows, "SvEstadoSsos");

        log.info("Archivo Excel generado exitosamente: {}", result.getName());

        return result;

    }

    /**
     * Define las columnas para un usuario específico
     */
    @Override
    public List<SimpleExcelColumn> defineColumns(SvEstadoSsoDTO dto) {

        return Arrays.asList(

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvEstadoSsoColumnEnum.ESTADO_SSO_CODE) //
                        .value(dto.getId()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvEstadoSsoColumnEnum.NAME) //
                        .value(dto.getName()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvEstadoSsoColumnEnum.DESCRIPTION) //
                        .value(dto.getDescription()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvEstadoSsoColumnEnum.IS_DELETED) //
                        .value(dto.getIsDeleted()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvEstadoSsoColumnEnum.VERSION) //
                        .value(dto.getVersion()) //
                        .build());

    }
}
