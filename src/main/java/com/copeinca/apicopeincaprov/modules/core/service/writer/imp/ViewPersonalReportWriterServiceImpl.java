/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/writer/imp/EntityWriterServiceImpl.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.writer.imp;

import com.copeinca.apicopeincaprov.commons.report.excel.SimpleExcelColumn;
import com.copeinca.apicopeincaprov.commons.report.excel.SimpleExcelExportUtilities;
import com.copeinca.apicopeincaprov.global.dtos.AttachmentData;
import com.copeinca.apicopeincaprov.modules.core.models.dto.ViewPersonalReportDTO;
import com.copeinca.apicopeincaprov.modules.core.models.writer.enums.ViewPersonalReportColumnEnum;
import com.copeinca.apicopeincaprov.modules.core.service.writer.IViewPersonalReportWriterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
* Implementación del servicio de escritura para ViewPersonalReportView
* Actualmente lanza excepciones para métodos no implementados.
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class ViewPersonalReportWriterServiceImpl implements IViewPersonalReportWriterService {

    @Override
    public AttachmentData exportToCsv(List<ViewPersonalReportDTO> data) {
        log.warn("Método exportToCsv no implementado para ViewPersonalReportView");
        throw new UnsupportedOperationException("La exportación a CSV no está implementada para ViewPersonalReportView");
    }

    @Override
    public AttachmentData exportToExcel(List<ViewPersonalReportDTO> data) throws Exception {

        log.info("Iniciando exportación a Excel de {} registros de ViewPersonalReportDTO", data != null ? data.size() : 0);

        //--- Crear lista de filas para Excel
        List<List<SimpleExcelColumn>> rows = Optional.ofNullable(data).orElse(new ArrayList<>()).stream()//
                .map(this::defineColumns)//
                .collect(Collectors.toList());

        //--- Generar archivo Excel
        AttachmentData result = SimpleExcelExportUtilities.generateExcelFile(rows, "ViewPersonalReports");

        log.info("Archivo Excel generado exitosamente: {}", result.getName());

        return result;

    }

    /**
     * Define las columnas para un usuario específico
     */
    @Override
    public List<SimpleExcelColumn> defineColumns(ViewPersonalReportDTO dto) {

        return Arrays.asList(

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(ViewPersonalReportColumnEnum.PERSONAL_SEDE_ID) //
                        .value(dto.getId()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(ViewPersonalReportColumnEnum.IS_ACTIVE) //
                        .value(dto.getIsActive()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(ViewPersonalReportColumnEnum.PERSONAL_ID) //
                        .value(dto.getPersonalId()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(ViewPersonalReportColumnEnum.SEDE_ID) //
                        .value(dto.getSedeId()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(ViewPersonalReportColumnEnum.PERSONAL_USUARIO) //
                        .value(dto.getPersonalUsuario()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(ViewPersonalReportColumnEnum.PERSONAL_NOMBRE_USUARIO) //
                        .value(dto.getPersonalNombreUsuario()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(ViewPersonalReportColumnEnum.PERSONAL_CODIGO) //
                        .value(dto.getPersonalCodigo()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(ViewPersonalReportColumnEnum.PERSONAL_DESCRIPCION) //
                        .value(dto.getPersonalDescripcion()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(ViewPersonalReportColumnEnum.SEDE_NAME) //
                        .value(dto.getSedeName()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(ViewPersonalReportColumnEnum.SEDE_AMBITO) //
                        .value(dto.getSedeAmbito()) //
                        .build());

    }
}
