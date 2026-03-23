/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/reader/imp/EntityExcelReaderServiceImpl.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.reader.imp;

import com.copeinca.apicopeincaprov.commons.report.excel.ExcelUtilities;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvSolicitudVisitaHistorialDTO;
import com.copeinca.apicopeincaprov.modules.core.models.writer.enums.SvSolicitudVisitaHistorialColumnEnum;
import com.copeinca.apicopeincaprov.modules.core.service.reader.ISvSolicitudVisitaHistorialExcelReaderService;
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
* Implementación del servicio de lectura para SvSolicitudVisitaHistorialEntity
* Actualmente lanza excepciones para métodos no implementados.
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class SvSolicitudVisitaHistorialReaderServiceImpl implements ISvSolicitudVisitaHistorialExcelReaderService {

    @Override
    public List<SvSolicitudVisitaHistorialDTO> readFromCsv(InputStream inputStream) {
        log.warn("Método readFromCsv no implementado para SvSolicitudVisitaHistorialEntity");
        throw new UnsupportedOperationException("La lectura de archivos CSV no está implementada para SvSolicitudVisitaHistorialEntity");
    }

    @Override
    public List<SvSolicitudVisitaHistorialDTO> readFromExcel(InputStream inputStream) throws IOException {

        //--- Convertimos el InputStream en Workbook de Apache POI
        Workbook workbook = ExcelUtilities.getWorkbook(inputStream);

        Sheet sheet = workbook.getSheetAt(0);

        List<SvSolicitudVisitaHistorialDTO> list = new ArrayList<>();

        for (Row row : sheet) {

            if (row.getRowNum() == 0)
                continue;

            SvSolicitudVisitaHistorialDTO dto = new SvSolicitudVisitaHistorialDTO();

            dto.setId(ExcelUtilities.getCellValueAsString(row.getCell(SvSolicitudVisitaHistorialColumnEnum.SOLICITUD_VISITA_HISTORIAL_ID.getColumnIndex())));
            dto.setSolicitudVisitaId(
                    ExcelUtilities.getCellValueAsString(row.getCell(SvSolicitudVisitaHistorialColumnEnum.SOLICITUD_VISITA_ID.getColumnIndex())));
            dto.setEstadoSolicitudVisitaCode(
                    ExcelUtilities.getCellValueAsString(row.getCell(SvSolicitudVisitaHistorialColumnEnum.ESTADO_SOLICITUD_VISITA_CODE.getColumnIndex())));
            dto.setFechaHora(ExcelUtilities.getCellValueAsLocalDateTime(row.getCell(SvSolicitudVisitaHistorialColumnEnum.FECHA_HORA.getColumnIndex())));
            dto.setUsuario(ExcelUtilities.getCellValueAsString(row.getCell(SvSolicitudVisitaHistorialColumnEnum.USUARIO.getColumnIndex())));
            dto.setAmbito(ExcelUtilities.getCellValueAsString(row.getCell(SvSolicitudVisitaHistorialColumnEnum.AMBITO.getColumnIndex())));
            dto.setRevision(ExcelUtilities.getCellValueAsString(row.getCell(SvSolicitudVisitaHistorialColumnEnum.REVISION.getColumnIndex())));
            dto.setRptaByUsuario(ExcelUtilities.getCellValueAsString(row.getCell(SvSolicitudVisitaHistorialColumnEnum.RPTA_BY_USUARIO.getColumnIndex())));
            dto.setIsDeleted(ExcelUtilities.getCellValueAsBoolean(row.getCell(SvSolicitudVisitaHistorialColumnEnum.IS_DELETED.getColumnIndex())));
            dto.setVersion(ExcelUtilities.getCellValueAsLong(row.getCell(SvSolicitudVisitaHistorialColumnEnum.VERSION.getColumnIndex())));

            list.add(dto);

        }

        workbook.close();

        return list;

    }

}