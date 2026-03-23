/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/reader/imp/EntityExcelReaderServiceImpl.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.reader.imp;

import com.copeinca.apicopeincaprov.commons.report.excel.ExcelUtilities;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvEstadoSsoDTO;
import com.copeinca.apicopeincaprov.modules.core.models.writer.enums.SvEstadoSsoColumnEnum;
import com.copeinca.apicopeincaprov.modules.core.service.reader.ISvEstadoSsoExcelReaderService;
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
* Implementación del servicio de lectura para SvEstadoSsoEntity
* Actualmente lanza excepciones para métodos no implementados.
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class SvEstadoSsoReaderServiceImpl implements ISvEstadoSsoExcelReaderService {

    @Override
    public List<SvEstadoSsoDTO> readFromCsv(InputStream inputStream) {
        log.warn("Método readFromCsv no implementado para SvEstadoSsoEntity");
        throw new UnsupportedOperationException("La lectura de archivos CSV no está implementada para SvEstadoSsoEntity");
    }

    @Override
    public List<SvEstadoSsoDTO> readFromExcel(InputStream inputStream) throws IOException {

        //--- Convertimos el InputStream en Workbook de Apache POI
        Workbook workbook = ExcelUtilities.getWorkbook(inputStream);

        Sheet sheet = workbook.getSheetAt(0);

        List<SvEstadoSsoDTO> list = new ArrayList<>();

        for (Row row : sheet) {

            if (row.getRowNum() == 0)
                continue;

            SvEstadoSsoDTO dto = new SvEstadoSsoDTO();

            dto.setId(ExcelUtilities.getCellValueAsString(row.getCell(SvEstadoSsoColumnEnum.ESTADO_SSO_CODE.getColumnIndex())));
            dto.setName(ExcelUtilities.getCellValueAsString(row.getCell(SvEstadoSsoColumnEnum.NAME.getColumnIndex())));
            dto.setDescription(ExcelUtilities.getCellValueAsString(row.getCell(SvEstadoSsoColumnEnum.DESCRIPTION.getColumnIndex())));
            dto.setIsDeleted(ExcelUtilities.getCellValueAsBoolean(row.getCell(SvEstadoSsoColumnEnum.IS_DELETED.getColumnIndex())));
            dto.setVersion(ExcelUtilities.getCellValueAsLong(row.getCell(SvEstadoSsoColumnEnum.VERSION.getColumnIndex())));

            list.add(dto);

        }

        workbook.close();

        return list;

    }

}