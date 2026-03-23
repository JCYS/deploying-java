/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/reader/imp/EntityExcelReaderServiceImpl.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.reader.imp;

import com.copeinca.apicopeincaprov.commons.report.excel.ExcelUtilities;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvSolicitudVisitaAreaAutorizadoraDTO;
import com.copeinca.apicopeincaprov.modules.core.models.writer.enums.SvSolicitudVisitaAreaAutorizadoraColumnEnum;
import com.copeinca.apicopeincaprov.modules.core.service.reader.ISvSolicitudVisitaAreaAutorizadoraExcelReaderService;
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
* Implementación del servicio de lectura para SvSolicitudVisitaAreaAutorizadoraEntity
* Actualmente lanza excepciones para métodos no implementados.
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class SvSolicitudVisitaAreaAutorizadoraReaderServiceImpl implements ISvSolicitudVisitaAreaAutorizadoraExcelReaderService {

    @Override
    public List<SvSolicitudVisitaAreaAutorizadoraDTO> readFromCsv(InputStream inputStream) {
        log.warn("Método readFromCsv no implementado para SvSolicitudVisitaAreaAutorizadoraEntity");
        throw new UnsupportedOperationException("La lectura de archivos CSV no está implementada para SvSolicitudVisitaAreaAutorizadoraEntity");
    }

    @Override
    public List<SvSolicitudVisitaAreaAutorizadoraDTO> readFromExcel(InputStream inputStream) throws IOException {

        //--- Convertimos el InputStream en Workbook de Apache POI
        Workbook workbook = ExcelUtilities.getWorkbook(inputStream);

        Sheet sheet = workbook.getSheetAt(0);

        List<SvSolicitudVisitaAreaAutorizadoraDTO> list = new ArrayList<>();

        for (Row row : sheet) {

            if (row.getRowNum() == 0)
                continue;

            SvSolicitudVisitaAreaAutorizadoraDTO dto = new SvSolicitudVisitaAreaAutorizadoraDTO();

            dto.setId(ExcelUtilities
                    .getCellValueAsString(row.getCell(SvSolicitudVisitaAreaAutorizadoraColumnEnum.SOLICITUD_VISITA_AREA_AUTORIZADORA_ID.getColumnIndex())));
            dto.setIsActive(ExcelUtilities.getCellValueAsBoolean(row.getCell(SvSolicitudVisitaAreaAutorizadoraColumnEnum.IS_ACTIVE.getColumnIndex())));
            dto.setSolicitudVisitaId(
                    ExcelUtilities.getCellValueAsString(row.getCell(SvSolicitudVisitaAreaAutorizadoraColumnEnum.SOLICITUD_VISITA_ID.getColumnIndex())));
            dto.setAreaAutorizadoraId(
                    ExcelUtilities.getCellValueAsString(row.getCell(SvSolicitudVisitaAreaAutorizadoraColumnEnum.AREA_AUTORIZADORA_ID.getColumnIndex())));
            dto.setIsDeleted(ExcelUtilities.getCellValueAsBoolean(row.getCell(SvSolicitudVisitaAreaAutorizadoraColumnEnum.IS_DELETED.getColumnIndex())));
            dto.setVersion(ExcelUtilities.getCellValueAsLong(row.getCell(SvSolicitudVisitaAreaAutorizadoraColumnEnum.VERSION.getColumnIndex())));

            list.add(dto);

        }

        workbook.close();

        return list;

    }

}