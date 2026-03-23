/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/reader/imp/EntityExcelReaderServiceImpl.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.reader.imp;

import com.copeinca.apicopeincaprov.commons.report.excel.ExcelUtilities;
import com.copeinca.apicopeincaprov.modules.core.models.dto.ViewPersonalReportDTO;
import com.copeinca.apicopeincaprov.modules.core.models.writer.enums.ViewPersonalReportColumnEnum;
import com.copeinca.apicopeincaprov.modules.core.service.reader.IViewPersonalReportExcelReaderService;
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
* Implementación del servicio de lectura para ViewPersonalReportView
* Actualmente lanza excepciones para métodos no implementados.
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class ViewPersonalReportReaderServiceImpl implements IViewPersonalReportExcelReaderService {

    @Override
    public List<ViewPersonalReportDTO> readFromCsv(InputStream inputStream) {
        log.warn("Método readFromCsv no implementado para ViewPersonalReportView");
        throw new UnsupportedOperationException("La lectura de archivos CSV no está implementada para ViewPersonalReportView");
    }

    @Override
    public List<ViewPersonalReportDTO> readFromExcel(InputStream inputStream) throws IOException {

        //--- Convertimos el InputStream en Workbook de Apache POI
        Workbook workbook = ExcelUtilities.getWorkbook(inputStream);

        Sheet sheet = workbook.getSheetAt(0);

        List<ViewPersonalReportDTO> list = new ArrayList<>();

        for (Row row : sheet) {

            if (row.getRowNum() == 0)
                continue;

            ViewPersonalReportDTO dto = new ViewPersonalReportDTO();

            dto.setId(ExcelUtilities.getCellValueAsString(row.getCell(ViewPersonalReportColumnEnum.PERSONAL_SEDE_ID.getColumnIndex())));
            dto.setIsActive(ExcelUtilities.getCellValueAsBoolean(row.getCell(ViewPersonalReportColumnEnum.IS_ACTIVE.getColumnIndex())));
            dto.setPersonalId(ExcelUtilities.getCellValueAsString(row.getCell(ViewPersonalReportColumnEnum.PERSONAL_ID.getColumnIndex())));
            dto.setSedeId(ExcelUtilities.getCellValueAsString(row.getCell(ViewPersonalReportColumnEnum.SEDE_ID.getColumnIndex())));
            dto.setPersonalUsuario(ExcelUtilities.getCellValueAsString(row.getCell(ViewPersonalReportColumnEnum.PERSONAL_USUARIO.getColumnIndex())));
            dto.setPersonalNombreUsuario(
                    ExcelUtilities.getCellValueAsString(row.getCell(ViewPersonalReportColumnEnum.PERSONAL_NOMBRE_USUARIO.getColumnIndex())));
            dto.setPersonalCodigo(ExcelUtilities.getCellValueAsString(row.getCell(ViewPersonalReportColumnEnum.PERSONAL_CODIGO.getColumnIndex())));
            dto.setPersonalDescripcion(ExcelUtilities.getCellValueAsString(row.getCell(ViewPersonalReportColumnEnum.PERSONAL_DESCRIPCION.getColumnIndex())));
            dto.setSedeName(ExcelUtilities.getCellValueAsString(row.getCell(ViewPersonalReportColumnEnum.SEDE_NAME.getColumnIndex())));
            dto.setSedeAmbito(ExcelUtilities.getCellValueAsString(row.getCell(ViewPersonalReportColumnEnum.SEDE_AMBITO.getColumnIndex())));

            list.add(dto);

        }

        workbook.close();

        return list;

    }

}