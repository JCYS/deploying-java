/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/writer/imp/EntityWriterServiceImpl.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.writer.imp;

import com.copeinca.apicopeincaprov.commons.report.excel.SimpleExcelColumn;
import com.copeinca.apicopeincaprov.commons.report.excel.SimpleExcelExportUtilities;
import com.copeinca.apicopeincaprov.global.dtos.AttachmentData;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvSolicitudVisitaZonaDTO;
import com.copeinca.apicopeincaprov.modules.core.models.writer.enums.SvSolicitudVisitaZonaColumnEnum;
import com.copeinca.apicopeincaprov.modules.core.service.writer.ISvSolicitudVisitaZonaWriterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
* Implementación del servicio de escritura para SvSolicitudVisitaZonaEntity
* Actualmente lanza excepciones para métodos no implementados.
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class SvSolicitudVisitaZonaWriterServiceImpl implements ISvSolicitudVisitaZonaWriterService {

    @Override
    public AttachmentData exportToCsv(List<SvSolicitudVisitaZonaDTO> data) {
        log.warn("Método exportToCsv no implementado para SvSolicitudVisitaZonaEntity");
        throw new UnsupportedOperationException("La exportación a CSV no está implementada para SvSolicitudVisitaZonaEntity");
    }

    @Override
    public AttachmentData exportToExcel(List<SvSolicitudVisitaZonaDTO> data) throws Exception {

        log.info("Iniciando exportación a Excel de {} registros de SvSolicitudVisitaZonaDTO", data != null ? data.size() : 0);

        //--- Crear lista de filas para Excel
        List<List<SimpleExcelColumn>> rows = Optional.ofNullable(data).orElse(new ArrayList<>()).stream()//
                .map(this::defineColumns)//
                .collect(Collectors.toList());

        //--- Generar archivo Excel
        AttachmentData result = SimpleExcelExportUtilities.generateExcelFile(rows, "SvSolicitudVisitaZonas");

        log.info("Archivo Excel generado exitosamente: {}", result.getName());

        return result;

    }

    /**
     * Define las columnas para un usuario específico
     */
    @Override
    public List<SimpleExcelColumn> defineColumns(SvSolicitudVisitaZonaDTO dto) {

        return Arrays.asList(

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvSolicitudVisitaZonaColumnEnum.SOLICITUD_VISITA_ZONA_ID) //
                        .value(dto.getId()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvSolicitudVisitaZonaColumnEnum.IS_ACTIVE) //
                        .value(dto.getIsActive()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvSolicitudVisitaZonaColumnEnum.ZONA_ID) //
                        .value(dto.getZonaId()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvSolicitudVisitaZonaColumnEnum.SOLICITUD_VISITA_ID) //
                        .value(dto.getSolicitudVisitaId()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvSolicitudVisitaZonaColumnEnum.IS_DELETED) //
                        .value(dto.getIsDeleted()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvSolicitudVisitaZonaColumnEnum.VERSION) //
                        .value(dto.getVersion()) //
                        .build());

    }
}
