/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/reader/imp/EntityExcelReaderServiceImpl.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.reader.imp;

import com.copeinca.apicopeincaprov.commons.report.excel.ExcelUtilities;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvSedeDTO;
import com.copeinca.apicopeincaprov.modules.core.models.writer.enums.SvSedeColumnEnum;
import com.copeinca.apicopeincaprov.modules.core.service.reader.ISvSedeExcelReaderService;
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
* Implementación del servicio de lectura para SvSedeEntity
* Actualmente lanza excepciones para métodos no implementados.
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class SvSedeReaderServiceImpl implements ISvSedeExcelReaderService {

    @Override
    public List<SvSedeDTO> readFromCsv(InputStream inputStream) {
        log.warn("Método readFromCsv no implementado para SvSedeEntity");
        throw new UnsupportedOperationException("La lectura de archivos CSV no está implementada para SvSedeEntity");
    }

    @Override
    public List<SvSedeDTO> readFromExcel(InputStream inputStream) throws IOException {

        //--- Convertimos el InputStream en Workbook de Apache POI
        Workbook workbook = ExcelUtilities.getWorkbook(inputStream);

        Sheet sheet = workbook.getSheetAt(0);

        List<SvSedeDTO> list = new ArrayList<>();

        for (Row row : sheet) {

            if (row.getRowNum() == 0)
                continue;

            SvSedeDTO dto = new SvSedeDTO();

            dto.setId(ExcelUtilities.getCellValueAsString(row.getCell(SvSedeColumnEnum.SEDE_ID.getColumnIndex())));
            dto.setName(ExcelUtilities.getCellValueAsString(row.getCell(SvSedeColumnEnum.NAME.getColumnIndex())));
            dto.setAmbito(ExcelUtilities.getCellValueAsString(row.getCell(SvSedeColumnEnum.AMBITO.getColumnIndex())));
            dto.setIsDeleted(ExcelUtilities.getCellValueAsBoolean(row.getCell(SvSedeColumnEnum.IS_DELETED.getColumnIndex())));
            dto.setVersion(ExcelUtilities.getCellValueAsLong(row.getCell(SvSedeColumnEnum.VERSION.getColumnIndex())));

            list.add(dto);

        }

        workbook.close();

        return list;

    }

}