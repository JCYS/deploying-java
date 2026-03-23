/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/reader/imp/EntityExcelReaderServiceImpl.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.reader.imp;

import com.copeinca.apicopeincaprov.commons.report.excel.ExcelUtilities;
import com.copeinca.apicopeincaprov.modules.core.models.dto.ViewTrabajadorReportDTO;
import com.copeinca.apicopeincaprov.modules.core.models.writer.enums.ViewTrabajadorReportColumnEnum;
import com.copeinca.apicopeincaprov.modules.core.service.reader.IViewTrabajadorReportExcelReaderService;
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
* Implementación del servicio de lectura para ViewTrabajadorReportView
* Actualmente lanza excepciones para métodos no implementados.
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class ViewTrabajadorReportReaderServiceImpl implements IViewTrabajadorReportExcelReaderService {

    @Override
    public List<ViewTrabajadorReportDTO> readFromCsv(InputStream inputStream) {
        log.warn("Método readFromCsv no implementado para ViewTrabajadorReportView");
        throw new UnsupportedOperationException("La lectura de archivos CSV no está implementada para ViewTrabajadorReportView");
    }

    @Override
    public List<ViewTrabajadorReportDTO> readFromExcel(InputStream inputStream) throws IOException {

        //--- Convertimos el InputStream en Workbook de Apache POI
        Workbook workbook = ExcelUtilities.getWorkbook(inputStream);

        Sheet sheet = workbook.getSheetAt(0);

        List<ViewTrabajadorReportDTO> list = new ArrayList<>();

        for (Row row : sheet) {

            if (row.getRowNum() == 0)
                continue;

            ViewTrabajadorReportDTO dto = new ViewTrabajadorReportDTO();

            dto.setId(ExcelUtilities.getCellValueAsString(row.getCell(ViewTrabajadorReportColumnEnum.TRABAJADOR_ID.getColumnIndex())));
            dto.setProveedorId(ExcelUtilities.getCellValueAsString(row.getCell(ViewTrabajadorReportColumnEnum.PROVEEDOR_ID.getColumnIndex())));
            dto.setProvNroDocumentoIdentidad(
                    ExcelUtilities.getCellValueAsString(row.getCell(ViewTrabajadorReportColumnEnum.PROV_NRO_DOCUMENTO_IDENTIDAD.getColumnIndex())));
            dto.setProvNombreFiscal(ExcelUtilities.getCellValueAsString(row.getCell(ViewTrabajadorReportColumnEnum.PROV_NOMBRE_FISCAL.getColumnIndex())));
            dto.setTipoDocumentoIdentidadCode(
                    ExcelUtilities.getCellValueAsString(row.getCell(ViewTrabajadorReportColumnEnum.TIPO_DOCUMENTO_IDENTIDAD_CODE.getColumnIndex())));
            dto.setNroDocumentoIdentidad(
                    ExcelUtilities.getCellValueAsString(row.getCell(ViewTrabajadorReportColumnEnum.NRO_DOCUMENTO_IDENTIDAD.getColumnIndex())));
            dto.setTdiNombre(ExcelUtilities.getCellValueAsString(row.getCell(ViewTrabajadorReportColumnEnum.TDI_NOMBRE.getColumnIndex())));
            dto.setNombre(ExcelUtilities.getCellValueAsString(row.getCell(ViewTrabajadorReportColumnEnum.NOMBRE.getColumnIndex())));
            dto.setTelefono(ExcelUtilities.getCellValueAsString(row.getCell(ViewTrabajadorReportColumnEnum.TELEFONO.getColumnIndex())));
            dto.setEmail(ExcelUtilities.getCellValueAsString(row.getCell(ViewTrabajadorReportColumnEnum.EMAIL.getColumnIndex())));
            dto.setEstadoSsoCode(ExcelUtilities.getCellValueAsString(row.getCell(ViewTrabajadorReportColumnEnum.ESTADO_SSO_CODE.getColumnIndex())));
            dto.setSsoMotivo(ExcelUtilities.getCellValueAsString(row.getCell(ViewTrabajadorReportColumnEnum.SSO_MOTIVO.getColumnIndex())));
            dto.setSoTieneVigencia(ExcelUtilities.getCellValueAsBoolean(row.getCell(ViewTrabajadorReportColumnEnum.SO_TIENE_VIGENCIA.getColumnIndex())));
            dto.setSsoFechaInicioVigencia(
                    ExcelUtilities.getCellValueAsLocalDate(row.getCell(ViewTrabajadorReportColumnEnum.SSO_FECHA_INICIO_VIGENCIA.getColumnIndex())));
            dto.setSsoFechaFinVigencia(
                    ExcelUtilities.getCellValueAsLocalDate(row.getCell(ViewTrabajadorReportColumnEnum.SSO_FECHA_FIN_VIGENCIA.getColumnIndex())));
            dto.setIsDeleted(ExcelUtilities.getCellValueAsBoolean(row.getCell(ViewTrabajadorReportColumnEnum.IS_DELETED.getColumnIndex())));
            dto.setIsActive(ExcelUtilities.getCellValueAsBoolean(row.getCell(ViewTrabajadorReportColumnEnum.IS_ACTIVE.getColumnIndex())));
            dto.setSsoEstadoNombre(ExcelUtilities.getCellValueAsString(row.getCell(ViewTrabajadorReportColumnEnum.SSO_ESTADO_NOMBRE.getColumnIndex())));
            dto.setSsoEstadoVigente(ExcelUtilities.getCellValueAsString(row.getCell(ViewTrabajadorReportColumnEnum.SSO_ESTADO_VIGENTE.getColumnIndex())));
            dto.setIndTrabajoAltoRiesgo(
                    ExcelUtilities.getCellValueAsBoolean(row.getCell(ViewTrabajadorReportColumnEnum.IND_TRABAJO_ALTO_RIESGO.getColumnIndex())));
            dto.setIndPrevencionista(ExcelUtilities.getCellValueAsBoolean(row.getCell(ViewTrabajadorReportColumnEnum.IND_PREVENCIONISTA.getColumnIndex())));
            dto.setTarCodigos(ExcelUtilities.getCellValueAsString(row.getCell(ViewTrabajadorReportColumnEnum.TAR_CODIGOS.getColumnIndex())));
            dto.setTarDescripciones(ExcelUtilities.getCellValueAsString(row.getCell(ViewTrabajadorReportColumnEnum.TAR_DESCRIPCIONES.getColumnIndex())));
            dto.setNDocsSsoAdjuntos(ExcelUtilities.getCellValueAsLong(row.getCell(ViewTrabajadorReportColumnEnum.N_DOCS_SSO_ADJUNTOS.getColumnIndex())));
            dto.setNDocsCalidadAdjuntos(
                    ExcelUtilities.getCellValueAsLong(row.getCell(ViewTrabajadorReportColumnEnum.N_DOCS_CALIDAD_ADJUNTOS.getColumnIndex())));
            dto.setNDocsAarAdjuntos(ExcelUtilities.getCellValueAsLong(row.getCell(ViewTrabajadorReportColumnEnum.N_DOCS_AAR_ADJUNTOS.getColumnIndex())));
            dto.setNDocsAbAdjuntos(ExcelUtilities.getCellValueAsLong(row.getCell(ViewTrabajadorReportColumnEnum.N_DOCS_AB_ADJUNTOS.getColumnIndex())));
            dto.setNDocsPrevAdjuntos(ExcelUtilities.getCellValueAsLong(row.getCell(ViewTrabajadorReportColumnEnum.N_DOCS_PREV_ADJUNTOS.getColumnIndex())));

            list.add(dto);

        }

        workbook.close();

        return list;

    }

}