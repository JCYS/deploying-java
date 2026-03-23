/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/writer/imp/EntityWriterServiceImpl.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.writer.imp;

import com.copeinca.apicopeincaprov.commons.report.excel.SimpleExcelColumn;
import com.copeinca.apicopeincaprov.commons.report.excel.SimpleExcelExportUtilities;
import com.copeinca.apicopeincaprov.global.dtos.AttachmentData;
import com.copeinca.apicopeincaprov.modules.core.models.dto.ViewSolicitudVisitaHistorialReportDTO;
import com.copeinca.apicopeincaprov.modules.core.models.writer.enums.ViewSolicitudVisitaHistorialReportColumnEnum;
import com.copeinca.apicopeincaprov.modules.core.service.writer.IViewSolicitudVisitaHistorialReportWriterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
* Implementación del servicio de escritura para ViewSolicitudVisitaHistorialReportView
* Actualmente lanza excepciones para métodos no implementados.
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class ViewSolicitudVisitaHistorialReportWriterServiceImpl implements IViewSolicitudVisitaHistorialReportWriterService {

    @Override
    public AttachmentData exportToCsv(List<ViewSolicitudVisitaHistorialReportDTO> data) {
        log.warn("Método exportToCsv no implementado para ViewSolicitudVisitaHistorialReportView");
        throw new UnsupportedOperationException("La exportación a CSV no está implementada para ViewSolicitudVisitaHistorialReportView");
    }

    @Override
    public AttachmentData exportToExcel(List<ViewSolicitudVisitaHistorialReportDTO> data) throws Exception {

        log.info("Iniciando exportación a Excel de {} registros de ViewSolicitudVisitaHistorialReportDTO", data != null ? data.size() : 0);

        //--- Crear lista de filas para Excel
        List<List<SimpleExcelColumn>> rows = Optional.ofNullable(data).orElse(new ArrayList<>()).stream()//
                .map(this::defineColumns)//
                .collect(Collectors.toList());

        //--- Generar archivo Excel
        AttachmentData result = SimpleExcelExportUtilities.generateExcelFile(rows, "ViewSolicitudVisitaHistorialReports");

        log.info("Archivo Excel generado exitosamente: {}", result.getName());

        return result;

    }

    /**
     * Define las columnas para un usuario específico
     */
    @Override
    public List<SimpleExcelColumn> defineColumns(ViewSolicitudVisitaHistorialReportDTO dto) {

        return Arrays.asList(

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(ViewSolicitudVisitaHistorialReportColumnEnum.SOLICITUD_VISITA_HISTORIAL_ID) //
                        .value(dto.getId()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(ViewSolicitudVisitaHistorialReportColumnEnum.FECHA_HORA) //
                        .value(dto.getFechaHora()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(ViewSolicitudVisitaHistorialReportColumnEnum.USUARIO) //
                        .value(dto.getUsuario()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(ViewSolicitudVisitaHistorialReportColumnEnum.AMBITO) //
                        .value(dto.getAmbito()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(ViewSolicitudVisitaHistorialReportColumnEnum.REVISION) //
                        .value(dto.getRevision()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(ViewSolicitudVisitaHistorialReportColumnEnum.RPTA_BY_USUARIO) //
                        .value(dto.getRptaByUsuario()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(ViewSolicitudVisitaHistorialReportColumnEnum.SOLICITUD_VISITA_ID) //
                        .value(dto.getSolicitudVisitaId()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(ViewSolicitudVisitaHistorialReportColumnEnum.ESTADO_SOLICITUD_VISITA_CODE) //
                        .value(dto.getEstadoSolicitudVisitaCode()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(ViewSolicitudVisitaHistorialReportColumnEnum.SOLVIS_NRO_ORDEN_SERVICIO) //
                        .value(dto.getSolvisNroOrdenServicio()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(ViewSolicitudVisitaHistorialReportColumnEnum.SOLVIS_CODIGO_VISITA) //
                        .value(dto.getSolvisCodigoVisita()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(ViewSolicitudVisitaHistorialReportColumnEnum.ESV_NOMBRE) //
                        .value(dto.getEsvNombre()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(ViewSolicitudVisitaHistorialReportColumnEnum.PREV_ESTADO_SOLICITUD_VISITA_CODE) //
                        .value(dto.getPrevEstadoSolicitudVisitaCode()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(ViewSolicitudVisitaHistorialReportColumnEnum.PREV_ESTADO_NOMBRE) //
                        .value(dto.getPrevEstadoNombre()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(ViewSolicitudVisitaHistorialReportColumnEnum.PREV_FECHA_HORA) //
                        .value(dto.getPrevFechaHora()) //
                        .build());

    }
}
