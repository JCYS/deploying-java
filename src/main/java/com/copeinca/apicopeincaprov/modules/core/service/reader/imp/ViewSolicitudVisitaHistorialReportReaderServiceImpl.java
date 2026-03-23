/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/reader/imp/EntityExcelReaderServiceImpl.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.reader.imp;

import com.copeinca.apicopeincaprov.commons.report.excel.ExcelUtilities;
import com.copeinca.apicopeincaprov.modules.core.models.dto.ViewSolicitudVisitaHistorialReportDTO;
import com.copeinca.apicopeincaprov.modules.core.models.writer.enums.ViewSolicitudVisitaHistorialReportColumnEnum;
import com.copeinca.apicopeincaprov.modules.core.service.reader.IViewSolicitudVisitaHistorialReportExcelReaderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
* Implementación del servicio de lectura para ViewSolicitudVisitaHistorialReportView
* Actualmente lanza excepciones para métodos no implementados.
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class ViewSolicitudVisitaHistorialReportReaderServiceImpl implements IViewSolicitudVisitaHistorialReportExcelReaderService {

    @Override
    public List<ViewSolicitudVisitaHistorialReportDTO> readFromCsv(InputStream inputStream) {
        log.warn("Método readFromCsv no implementado para ViewSolicitudVisitaHistorialReportView");
        throw new UnsupportedOperationException("La lectura de archivos CSV no está implementada para ViewSolicitudVisitaHistorialReportView");
    }

    @Override
    public List<ViewSolicitudVisitaHistorialReportDTO> readFromExcel(InputStream inputStream) throws IOException {

        //--- Convertimos el InputStream en Workbook de Apache POI
        Workbook workbook = ExcelUtilities.getWorkbook(inputStream);

        Sheet sheet = workbook.getSheetAt(0);

        List<ViewSolicitudVisitaHistorialReportDTO> list = new ArrayList<>();

        for (Row row : sheet) {

            if (row.getRowNum() == 0)
                continue;

            ViewSolicitudVisitaHistorialReportDTO dto = new ViewSolicitudVisitaHistorialReportDTO();

            dto.setId(ExcelUtilities
                    .getCellValueAsString(row.getCell(ViewSolicitudVisitaHistorialReportColumnEnum.SOLICITUD_VISITA_HISTORIAL_ID.getColumnIndex())));
            dto.setFechaHora(ExcelUtilities.getCellValueAsLocalDateTime(row.getCell(ViewSolicitudVisitaHistorialReportColumnEnum.FECHA_HORA.getColumnIndex())));
            dto.setUsuario(ExcelUtilities.getCellValueAsString(row.getCell(ViewSolicitudVisitaHistorialReportColumnEnum.USUARIO.getColumnIndex())));
            dto.setAmbito(ExcelUtilities.getCellValueAsString(row.getCell(ViewSolicitudVisitaHistorialReportColumnEnum.AMBITO.getColumnIndex())));
            dto.setRevision(ExcelUtilities.getCellValueAsString(row.getCell(ViewSolicitudVisitaHistorialReportColumnEnum.REVISION.getColumnIndex())));
            dto.setRptaByUsuario(
                    ExcelUtilities.getCellValueAsString(row.getCell(ViewSolicitudVisitaHistorialReportColumnEnum.RPTA_BY_USUARIO.getColumnIndex())));
            dto.setSolicitudVisitaId(
                    ExcelUtilities.getCellValueAsString(row.getCell(ViewSolicitudVisitaHistorialReportColumnEnum.SOLICITUD_VISITA_ID.getColumnIndex())));
            dto.setEstadoSolicitudVisitaCode(ExcelUtilities
                    .getCellValueAsString(row.getCell(ViewSolicitudVisitaHistorialReportColumnEnum.ESTADO_SOLICITUD_VISITA_CODE.getColumnIndex())));
            dto.setSolvisNroOrdenServicio(
                    ExcelUtilities.getCellValueAsString(row.getCell(ViewSolicitudVisitaHistorialReportColumnEnum.SOLVIS_NRO_ORDEN_SERVICIO.getColumnIndex())));
            dto.setSolvisCodigoVisita(
                    ExcelUtilities.getCellValueAsString(row.getCell(ViewSolicitudVisitaHistorialReportColumnEnum.SOLVIS_CODIGO_VISITA.getColumnIndex())));
            dto.setEsvNombre(ExcelUtilities.getCellValueAsString(row.getCell(ViewSolicitudVisitaHistorialReportColumnEnum.ESV_NOMBRE.getColumnIndex())));
            dto.setPrevEstadoSolicitudVisitaCode(ExcelUtilities
                    .getCellValueAsString(row.getCell(ViewSolicitudVisitaHistorialReportColumnEnum.PREV_ESTADO_SOLICITUD_VISITA_CODE.getColumnIndex())));
            dto.setPrevEstadoNombre(
                    ExcelUtilities.getCellValueAsString(row.getCell(ViewSolicitudVisitaHistorialReportColumnEnum.PREV_ESTADO_NOMBRE.getColumnIndex())));
            dto.setPrevFechaHora(
                    ExcelUtilities.getCellValueAsLocalDateTime(row.getCell(ViewSolicitudVisitaHistorialReportColumnEnum.PREV_FECHA_HORA.getColumnIndex())));

            list.add(dto);

        }

        workbook.close();

        return list;

    }

}