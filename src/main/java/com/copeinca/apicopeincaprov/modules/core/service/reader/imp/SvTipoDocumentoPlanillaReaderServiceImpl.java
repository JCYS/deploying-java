/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/reader/imp/EntityExcelReaderServiceImpl.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.reader.imp;

import com.copeinca.apicopeincaprov.commons.report.excel.ExcelUtilities;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvTipoDocumentoPlanillaDTO;
import com.copeinca.apicopeincaprov.modules.core.models.writer.enums.SvTipoDocumentoPlanillaColumnEnum;
import com.copeinca.apicopeincaprov.modules.core.service.reader.ISvTipoDocumentoPlanillaExcelReaderService;
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
* Implementación del servicio de lectura para SvTipoDocumentoPlanillaEntity
* Actualmente lanza excepciones para métodos no implementados.
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class SvTipoDocumentoPlanillaReaderServiceImpl implements ISvTipoDocumentoPlanillaExcelReaderService {

    @Override
    public List<SvTipoDocumentoPlanillaDTO> readFromCsv(InputStream inputStream) {
        log.warn("Método readFromCsv no implementado para SvTipoDocumentoPlanillaEntity");
        throw new UnsupportedOperationException("La lectura de archivos CSV no está implementada para SvTipoDocumentoPlanillaEntity");
    }

    @Override
    public List<SvTipoDocumentoPlanillaDTO> readFromExcel(InputStream inputStream) throws IOException {

        //--- Convertimos el InputStream en Workbook de Apache POI
        Workbook workbook = ExcelUtilities.getWorkbook(inputStream);

        Sheet sheet = workbook.getSheetAt(0);

        List<SvTipoDocumentoPlanillaDTO> list = new ArrayList<>();

        for (Row row : sheet) {

            if (row.getRowNum() == 0)
                continue;

            SvTipoDocumentoPlanillaDTO dto = new SvTipoDocumentoPlanillaDTO();

            dto.setId(ExcelUtilities.getCellValueAsString(row.getCell(SvTipoDocumentoPlanillaColumnEnum.TIPO_DOCUMENTO_PLANILLA_CODE.getColumnIndex())));
            dto.setDescription(ExcelUtilities.getCellValueAsString(row.getCell(SvTipoDocumentoPlanillaColumnEnum.DESCRIPTION.getColumnIndex())));
            dto.setAmbito(ExcelUtilities.getCellValueAsString(row.getCell(SvTipoDocumentoPlanillaColumnEnum.AMBITO.getColumnIndex())));
            dto.setInfluencia(ExcelUtilities.getCellValueAsString(row.getCell(SvTipoDocumentoPlanillaColumnEnum.INFLUENCIA.getColumnIndex())));
            dto.setDependencia(ExcelUtilities.getCellValueAsString(row.getCell(SvTipoDocumentoPlanillaColumnEnum.DEPENDENCIA.getColumnIndex())));
            dto.setCampoPlantillaLf(ExcelUtilities.getCellValueAsString(row.getCell(SvTipoDocumentoPlanillaColumnEnum.CAMPO_PLANTILLA_LF.getColumnIndex())));
            dto.setObligatorio(ExcelUtilities.getCellValueAsBoolean(row.getCell(SvTipoDocumentoPlanillaColumnEnum.OBLIGATORIO.getColumnIndex())));
            dto.setVencimiento(ExcelUtilities.getCellValueAsBoolean(row.getCell(SvTipoDocumentoPlanillaColumnEnum.VENCIMIENTO.getColumnIndex())));
            dto.setIsDeleted(ExcelUtilities.getCellValueAsBoolean(row.getCell(SvTipoDocumentoPlanillaColumnEnum.IS_DELETED.getColumnIndex())));
            dto.setVersion(ExcelUtilities.getCellValueAsLong(row.getCell(SvTipoDocumentoPlanillaColumnEnum.VERSION.getColumnIndex())));

            list.add(dto);

        }

        workbook.close();

        return list;

    }

}