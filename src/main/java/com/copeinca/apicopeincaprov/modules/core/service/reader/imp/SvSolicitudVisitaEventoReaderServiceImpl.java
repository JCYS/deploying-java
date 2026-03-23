/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/reader/imp/EntityExcelReaderServiceImpl.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.reader.imp;

import com.copeinca.apicopeincaprov.commons.report.excel.ExcelUtilities;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvSolicitudVisitaEventoDTO;
import com.copeinca.apicopeincaprov.modules.core.models.writer.enums.SvSolicitudVisitaEventoColumnEnum;
import com.copeinca.apicopeincaprov.modules.core.service.reader.ISvSolicitudVisitaEventoExcelReaderService;
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
* Implementación del servicio de lectura para SvSolicitudVisitaEventoEntity
* Actualmente lanza excepciones para métodos no implementados.
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class SvSolicitudVisitaEventoReaderServiceImpl implements ISvSolicitudVisitaEventoExcelReaderService {

    @Override
    public List<SvSolicitudVisitaEventoDTO> readFromCsv(InputStream inputStream) {
        log.warn("Método readFromCsv no implementado para SvSolicitudVisitaEventoEntity");
        throw new UnsupportedOperationException("La lectura de archivos CSV no está implementada para SvSolicitudVisitaEventoEntity");
    }

    @Override
    public List<SvSolicitudVisitaEventoDTO> readFromExcel(InputStream inputStream) throws IOException {

        //--- Convertimos el InputStream en Workbook de Apache POI
        Workbook workbook = ExcelUtilities.getWorkbook(inputStream);

        Sheet sheet = workbook.getSheetAt(0);

        List<SvSolicitudVisitaEventoDTO> list = new ArrayList<>();

        for (Row row : sheet) {

            if (row.getRowNum() == 0)
                continue;

            SvSolicitudVisitaEventoDTO dto = new SvSolicitudVisitaEventoDTO();

            dto.setId(ExcelUtilities.getCellValueAsString(row.getCell(SvSolicitudVisitaEventoColumnEnum.SOLICITUD_VISITA_EVENTO_ID.getColumnIndex())));
            dto.setFechaEvento(ExcelUtilities.getCellValueAsLocalDateTime(row.getCell(SvSolicitudVisitaEventoColumnEnum.FECHA_EVENTO.getColumnIndex())));
            dto.setUsuario(ExcelUtilities.getCellValueAsString(row.getCell(SvSolicitudVisitaEventoColumnEnum.USUARIO.getColumnIndex())));
            dto.setEvento(ExcelUtilities.getCellValueAsString(row.getCell(SvSolicitudVisitaEventoColumnEnum.EVENTO.getColumnIndex())));
            dto.setAmbito(ExcelUtilities.getCellValueAsString(row.getCell(SvSolicitudVisitaEventoColumnEnum.AMBITO.getColumnIndex())));
            dto.setRevision(ExcelUtilities.getCellValueAsString(row.getCell(SvSolicitudVisitaEventoColumnEnum.REVISION.getColumnIndex())));
            dto.setRespuestaUsuario(ExcelUtilities.getCellValueAsString(row.getCell(SvSolicitudVisitaEventoColumnEnum.RESPUESTA_USUARIO.getColumnIndex())));
            dto.setSolicitudVisitaId(ExcelUtilities.getCellValueAsString(row.getCell(SvSolicitudVisitaEventoColumnEnum.SOLICITUD_VISITA_ID.getColumnIndex())));
            dto.setIsDeleted(ExcelUtilities.getCellValueAsBoolean(row.getCell(SvSolicitudVisitaEventoColumnEnum.IS_DELETED.getColumnIndex())));
            dto.setVersion(ExcelUtilities.getCellValueAsLong(row.getCell(SvSolicitudVisitaEventoColumnEnum.VERSION.getColumnIndex())));

            list.add(dto);

        }

        workbook.close();

        return list;

    }

}