/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/reader/imp/EntityExcelReaderServiceImpl.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.reader.imp;

import com.copeinca.apicopeincaprov.commons.report.excel.ExcelUtilities;
import com.copeinca.apicopeincaprov.modules.core.models.dto.ViewPersonaRestringidaReportDTO;
import com.copeinca.apicopeincaprov.modules.core.models.writer.enums.ViewPersonaRestringidaReportColumnEnum;
import com.copeinca.apicopeincaprov.modules.core.service.reader.IViewPersonaRestringidaReportExcelReaderService;
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
* Implementación del servicio de lectura para ViewPersonaRestringidaReportView
* Actualmente lanza excepciones para métodos no implementados.
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class ViewPersonaRestringidaReportReaderServiceImpl implements IViewPersonaRestringidaReportExcelReaderService {

    @Override
    public List<ViewPersonaRestringidaReportDTO> readFromCsv(InputStream inputStream) {
        log.warn("Método readFromCsv no implementado para ViewPersonaRestringidaReportView");
        throw new UnsupportedOperationException("La lectura de archivos CSV no está implementada para ViewPersonaRestringidaReportView");
    }

    @Override
    public List<ViewPersonaRestringidaReportDTO> readFromExcel(InputStream inputStream) throws IOException {

        //--- Convertimos el InputStream en Workbook de Apache POI
        Workbook workbook = ExcelUtilities.getWorkbook(inputStream);

        Sheet sheet = workbook.getSheetAt(0);

        List<ViewPersonaRestringidaReportDTO> list = new ArrayList<>();

        for (Row row : sheet) {

            if (row.getRowNum() == 0)
                continue;

            ViewPersonaRestringidaReportDTO dto = new ViewPersonaRestringidaReportDTO();

            dto.setId(ExcelUtilities.getCellValueAsString(row.getCell(ViewPersonaRestringidaReportColumnEnum.PERSONA_RESTRINGIDA_ID.getColumnIndex())));
            dto.setNumeroDocumento(ExcelUtilities.getCellValueAsString(row.getCell(ViewPersonaRestringidaReportColumnEnum.NUMERO_DOCUMENTO.getColumnIndex())));
            dto.setLicenciaConducir(
                    ExcelUtilities.getCellValueAsString(row.getCell(ViewPersonaRestringidaReportColumnEnum.LICENCIA_CONDUCIR.getColumnIndex())));
            dto.setNombreCompleto(ExcelUtilities.getCellValueAsString(row.getCell(ViewPersonaRestringidaReportColumnEnum.NOMBRE_COMPLETO.getColumnIndex())));
            dto.setUsuarioReporta(ExcelUtilities.getCellValueAsString(row.getCell(ViewPersonaRestringidaReportColumnEnum.USUARIO_REPORTA.getColumnIndex())));
            dto.setRestringidoEl(
                    ExcelUtilities.getCellValueAsLocalDateTime(row.getCell(ViewPersonaRestringidaReportColumnEnum.RESTRINGIDO_EL.getColumnIndex())));
            dto.setRestringidoHasta(
                    ExcelUtilities.getCellValueAsLocalDateTime(row.getCell(ViewPersonaRestringidaReportColumnEnum.RESTRINGIDO_HASTA.getColumnIndex())));
            dto.setObservaciones(ExcelUtilities.getCellValueAsString(row.getCell(ViewPersonaRestringidaReportColumnEnum.OBSERVACIONES.getColumnIndex())));
            dto.setTipoDocumentoIdentidadCode(
                    ExcelUtilities.getCellValueAsString(row.getCell(ViewPersonaRestringidaReportColumnEnum.TIPO_DOCUMENTO_IDENTIDAD_CODE.getColumnIndex())));
            dto.setSedeOriginadoraId(
                    ExcelUtilities.getCellValueAsString(row.getCell(ViewPersonaRestringidaReportColumnEnum.SEDE_ORIGINADORA_ID.getColumnIndex())));
            dto.setTdiNombre(ExcelUtilities.getCellValueAsString(row.getCell(ViewPersonaRestringidaReportColumnEnum.TDI_NOMBRE.getColumnIndex())));
            dto.setSedeNombre(ExcelUtilities.getCellValueAsString(row.getCell(ViewPersonaRestringidaReportColumnEnum.SEDE_NOMBRE.getColumnIndex())));
            dto.setSedeAmbito(ExcelUtilities.getCellValueAsString(row.getCell(ViewPersonaRestringidaReportColumnEnum.SEDE_AMBITO.getColumnIndex())));
            dto.setRestriccionActiva(
                    ExcelUtilities.getCellValueAsBoolean(row.getCell(ViewPersonaRestringidaReportColumnEnum.RESTRICCION_ACTIVA.getColumnIndex())));
            dto.setDiasRestriccion(ExcelUtilities.getCellValueAsLong(row.getCell(ViewPersonaRestringidaReportColumnEnum.DIAS_RESTRICCION.getColumnIndex())));
            dto.setTipoRestriccion(ExcelUtilities.getCellValueAsString(row.getCell(ViewPersonaRestringidaReportColumnEnum.TIPO_RESTRICCION.getColumnIndex())));

            list.add(dto);

        }

        workbook.close();

        return list;

    }

}