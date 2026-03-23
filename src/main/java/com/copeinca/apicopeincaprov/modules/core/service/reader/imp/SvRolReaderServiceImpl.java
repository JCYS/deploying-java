/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/reader/imp/EntityExcelReaderServiceImpl.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.reader.imp;

import com.copeinca.apicopeincaprov.commons.report.excel.ExcelUtilities;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvRolDTO;
import com.copeinca.apicopeincaprov.modules.core.models.writer.enums.SvRolColumnEnum;
import com.copeinca.apicopeincaprov.modules.core.service.reader.ISvRolExcelReaderService;
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
* Implementación del servicio de lectura para SvRolEntity
* Actualmente lanza excepciones para métodos no implementados.
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class SvRolReaderServiceImpl implements ISvRolExcelReaderService {

    @Override
    public List<SvRolDTO> readFromCsv(InputStream inputStream) {
        log.warn("Método readFromCsv no implementado para SvRolEntity");
        throw new UnsupportedOperationException("La lectura de archivos CSV no está implementada para SvRolEntity");
    }

    @Override
    public List<SvRolDTO> readFromExcel(InputStream inputStream) throws IOException {

        //--- Convertimos el InputStream en Workbook de Apache POI
        Workbook workbook = ExcelUtilities.getWorkbook(inputStream);

        Sheet sheet = workbook.getSheetAt(0);

        List<SvRolDTO> list = new ArrayList<>();

        for (Row row : sheet) {

            if (row.getRowNum() == 0)
                continue;

            SvRolDTO dto = new SvRolDTO();

            dto.setId(ExcelUtilities.getCellValueAsString(row.getCell(SvRolColumnEnum.ROL_CODE.getColumnIndex())));
            dto.setRolName(ExcelUtilities.getCellValueAsString(row.getCell(SvRolColumnEnum.ROL_NAME.getColumnIndex())));
            dto.setRolDescription(ExcelUtilities.getCellValueAsString(row.getCell(SvRolColumnEnum.ROL_DESCRIPTION.getColumnIndex())));
            dto.setIsDeleted(ExcelUtilities.getCellValueAsBoolean(row.getCell(SvRolColumnEnum.IS_DELETED.getColumnIndex())));
            dto.setVersion(ExcelUtilities.getCellValueAsLong(row.getCell(SvRolColumnEnum.VERSION.getColumnIndex())));

            list.add(dto);

        }

        workbook.close();

        return list;

    }

}