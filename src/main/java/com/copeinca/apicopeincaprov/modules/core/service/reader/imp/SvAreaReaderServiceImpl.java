/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/reader/imp/EntityExcelReaderServiceImpl.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.reader.imp;

import com.copeinca.apicopeincaprov.commons.report.excel.ExcelUtilities;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvAreaDTO;
import com.copeinca.apicopeincaprov.modules.core.models.writer.enums.SvAreaColumnEnum;
import com.copeinca.apicopeincaprov.modules.core.service.reader.ISvAreaExcelReaderService;
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
* Implementación del servicio de lectura para SvAreaEntity
* Actualmente lanza excepciones para métodos no implementados.
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class SvAreaReaderServiceImpl implements ISvAreaExcelReaderService {

    @Override
    public List<SvAreaDTO> readFromCsv(InputStream inputStream) {
        log.warn("Método readFromCsv no implementado para SvAreaEntity");
        throw new UnsupportedOperationException("La lectura de archivos CSV no está implementada para SvAreaEntity");
    }

    @Override
    public List<SvAreaDTO> readFromExcel(InputStream inputStream) throws IOException {

        //--- Convertimos el InputStream en Workbook de Apache POI
        Workbook workbook = ExcelUtilities.getWorkbook(inputStream);

        Sheet sheet = workbook.getSheetAt(0);

        List<SvAreaDTO> list = new ArrayList<>();

        for (Row row : sheet) {

            if (row.getRowNum() == 0)
                continue;

            SvAreaDTO dto = new SvAreaDTO();

            dto.setId(ExcelUtilities.getCellValueAsString(row.getCell(SvAreaColumnEnum.AREA_ID.getColumnIndex())));
            dto.setName(ExcelUtilities.getCellValueAsString(row.getCell(SvAreaColumnEnum.NAME.getColumnIndex())));
            dto.setAmbito(ExcelUtilities.getCellValueAsString(row.getCell(SvAreaColumnEnum.AMBITO.getColumnIndex())));
            dto.setOperativa(ExcelUtilities.getCellValueAsString(row.getCell(SvAreaColumnEnum.OPERATIVA.getColumnIndex())));
            dto.setSedeId(ExcelUtilities.getCellValueAsString(row.getCell(SvAreaColumnEnum.SEDE_ID.getColumnIndex())));
            dto.setIsDeleted(ExcelUtilities.getCellValueAsBoolean(row.getCell(SvAreaColumnEnum.IS_DELETED.getColumnIndex())));
            dto.setVersion(ExcelUtilities.getCellValueAsLong(row.getCell(SvAreaColumnEnum.VERSION.getColumnIndex())));

            list.add(dto);

        }

        workbook.close();

        return list;

    }

}