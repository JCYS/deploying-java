/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/reader/imp/EntityExcelReaderServiceImpl.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.reader.imp;

import com.copeinca.apicopeincaprov.commons.report.excel.ExcelUtilities;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvUsuarioRolSedeDTO;
import com.copeinca.apicopeincaprov.modules.core.models.writer.enums.SvUsuarioRolSedeColumnEnum;
import com.copeinca.apicopeincaprov.modules.core.service.reader.ISvUsuarioRolSedeExcelReaderService;
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
* Implementación del servicio de lectura para SvUsuarioRolSedeEntity
* Actualmente lanza excepciones para métodos no implementados.
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class SvUsuarioRolSedeReaderServiceImpl implements ISvUsuarioRolSedeExcelReaderService {

    @Override
    public List<SvUsuarioRolSedeDTO> readFromCsv(InputStream inputStream) {
        log.warn("Método readFromCsv no implementado para SvUsuarioRolSedeEntity");
        throw new UnsupportedOperationException("La lectura de archivos CSV no está implementada para SvUsuarioRolSedeEntity");
    }

    @Override
    public List<SvUsuarioRolSedeDTO> readFromExcel(InputStream inputStream) throws IOException {

        //--- Convertimos el InputStream en Workbook de Apache POI
        Workbook workbook = ExcelUtilities.getWorkbook(inputStream);

        Sheet sheet = workbook.getSheetAt(0);

        List<SvUsuarioRolSedeDTO> list = new ArrayList<>();

        for (Row row : sheet) {

            if (row.getRowNum() == 0)
                continue;

            SvUsuarioRolSedeDTO dto = new SvUsuarioRolSedeDTO();

            dto.setId(ExcelUtilities.getCellValueAsString(row.getCell(SvUsuarioRolSedeColumnEnum.USUARIO_ROL_SEDE_ID.getColumnIndex())));
            dto.setUsuarioId(ExcelUtilities.getCellValueAsString(row.getCell(SvUsuarioRolSedeColumnEnum.USUARIO_ID.getColumnIndex())));
            dto.setRolCode(ExcelUtilities.getCellValueAsString(row.getCell(SvUsuarioRolSedeColumnEnum.ROL_CODE.getColumnIndex())));
            dto.setSedeId(ExcelUtilities.getCellValueAsString(row.getCell(SvUsuarioRolSedeColumnEnum.SEDE_ID.getColumnIndex())));
            dto.setIsActive(ExcelUtilities.getCellValueAsBoolean(row.getCell(SvUsuarioRolSedeColumnEnum.IS_ACTIVE.getColumnIndex())));
            dto.setIsDeleted(ExcelUtilities.getCellValueAsBoolean(row.getCell(SvUsuarioRolSedeColumnEnum.IS_DELETED.getColumnIndex())));
            dto.setVersion(ExcelUtilities.getCellValueAsLong(row.getCell(SvUsuarioRolSedeColumnEnum.VERSION.getColumnIndex())));

            list.add(dto);

        }

        workbook.close();

        return list;

    }

}