/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/reader/imp/EntityExcelReaderServiceImpl.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.reader.imp;

import com.copeinca.apicopeincaprov.commons.report.excel.ExcelUtilities;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvPersonaRestringidaDTO;
import com.copeinca.apicopeincaprov.modules.core.models.writer.enums.SvPersonaRestringidaColumnEnum;
import com.copeinca.apicopeincaprov.modules.core.service.reader.ISvPersonaRestringidaExcelReaderService;
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
* Implementación del servicio de lectura para SvPersonaRestringidaEntity
* Actualmente lanza excepciones para métodos no implementados.
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class SvPersonaRestringidaReaderServiceImpl implements ISvPersonaRestringidaExcelReaderService {

    @Override
    public List<SvPersonaRestringidaDTO> readFromCsv(InputStream inputStream) {
        log.warn("Método readFromCsv no implementado para SvPersonaRestringidaEntity");
        throw new UnsupportedOperationException("La lectura de archivos CSV no está implementada para SvPersonaRestringidaEntity");
    }

    @Override
    public List<SvPersonaRestringidaDTO> readFromExcel(InputStream inputStream) throws IOException {

        //--- Convertimos el InputStream en Workbook de Apache POI
        Workbook workbook = ExcelUtilities.getWorkbook(inputStream);

        Sheet sheet = workbook.getSheetAt(0);

        List<SvPersonaRestringidaDTO> list = new ArrayList<>();

        for (Row row : sheet) {

            if (row.getRowNum() == 0)
                continue;

            SvPersonaRestringidaDTO dto = new SvPersonaRestringidaDTO();

            dto.setId(ExcelUtilities.getCellValueAsString(row.getCell(SvPersonaRestringidaColumnEnum.PERSONA_RESTRINGIDA_ID.getColumnIndex())));
            dto.setTipoDocumentoIdentidadCode(
                    ExcelUtilities.getCellValueAsString(row.getCell(SvPersonaRestringidaColumnEnum.TIPO_DOCUMENTO_IDENTIDAD_CODE.getColumnIndex())));
            dto.setNumeroDocumento(ExcelUtilities.getCellValueAsString(row.getCell(SvPersonaRestringidaColumnEnum.NUMERO_DOCUMENTO.getColumnIndex())));
            dto.setLicenciaConducir(ExcelUtilities.getCellValueAsString(row.getCell(SvPersonaRestringidaColumnEnum.LICENCIA_CONDUCIR.getColumnIndex())));
            dto.setNombreCompleto(ExcelUtilities.getCellValueAsString(row.getCell(SvPersonaRestringidaColumnEnum.NOMBRE_COMPLETO.getColumnIndex())));
            dto.setUsuarioReporta(ExcelUtilities.getCellValueAsString(row.getCell(SvPersonaRestringidaColumnEnum.USUARIO_REPORTA.getColumnIndex())));

            dto.setRestringidoEl(ExcelUtilities.getCellValueAsLocalDateTimeFromString(row.getCell(SvPersonaRestringidaColumnEnum.RESTRINGIDO_EL.getColumnIndex())));
            dto.setRestringidoHasta(ExcelUtilities.getCellValueAsLocalDateTimeFromString(row.getCell(SvPersonaRestringidaColumnEnum.RESTRINGIDO_HASTA.getColumnIndex())));

            dto.setObservaciones(ExcelUtilities.getCellValueAsString(row.getCell(SvPersonaRestringidaColumnEnum.OBSERVACIONES.getColumnIndex())));
            dto.setSedeOriginadoraId(ExcelUtilities.getCellValueAsString(row.getCell(SvPersonaRestringidaColumnEnum.SEDE_ORIGINADORA_ID.getColumnIndex())));
            dto.setIsDeleted(ExcelUtilities.getCellValueAsBoolean(row.getCell(SvPersonaRestringidaColumnEnum.IS_DELETED.getColumnIndex())));
            dto.setVersion(ExcelUtilities.getCellValueAsLong(row.getCell(SvPersonaRestringidaColumnEnum.VERSION.getColumnIndex())));

            list.add(dto);

        }

        workbook.close();

        return list;

    }

}