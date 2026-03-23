/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/reader/imp/EntityExcelReaderServiceImpl.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.reader.imp;

import com.copeinca.apicopeincaprov.commons.report.excel.ExcelUtilities;
import com.copeinca.apicopeincaprov.modules.core.models.dto.ViewAdjuntoReportDTO;
import com.copeinca.apicopeincaprov.modules.core.models.writer.enums.ViewAdjuntoReportColumnEnum;
import com.copeinca.apicopeincaprov.modules.core.service.reader.IViewAdjuntoReportExcelReaderService;
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
* Implementación del servicio de lectura para ViewAdjuntoReportView
* Actualmente lanza excepciones para métodos no implementados.
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class ViewAdjuntoReportReaderServiceImpl implements IViewAdjuntoReportExcelReaderService {

    @Override
    public List<ViewAdjuntoReportDTO> readFromCsv(InputStream inputStream) {
        log.warn("Método readFromCsv no implementado para ViewAdjuntoReportView");
        throw new UnsupportedOperationException("La lectura de archivos CSV no está implementada para ViewAdjuntoReportView");
    }

    @Override
    public List<ViewAdjuntoReportDTO> readFromExcel(InputStream inputStream) throws IOException {

        //--- Convertimos el InputStream en Workbook de Apache POI
        Workbook workbook = ExcelUtilities.getWorkbook(inputStream);

        Sheet sheet = workbook.getSheetAt(0);

        List<ViewAdjuntoReportDTO> list = new ArrayList<>();

        for (Row row : sheet) {

            if (row.getRowNum() == 0)
                continue;

            ViewAdjuntoReportDTO dto = new ViewAdjuntoReportDTO();

            dto.setId(ExcelUtilities.getCellValueAsString(row.getCell(ViewAdjuntoReportColumnEnum.ADJUNTO_ID.getColumnIndex())));
            dto.setNombreArchivo(ExcelUtilities.getCellValueAsString(row.getCell(ViewAdjuntoReportColumnEnum.NOMBRE_ARCHIVO.getColumnIndex())));
            dto.setIdRepositorio(ExcelUtilities.getCellValueAsString(row.getCell(ViewAdjuntoReportColumnEnum.ID_REPOSITORIO.getColumnIndex())));
            dto.setClasification(ExcelUtilities.getCellValueAsString(row.getCell(ViewAdjuntoReportColumnEnum.CLASIFICATION.getColumnIndex())));
            dto.setFechaDocumento(ExcelUtilities.getCellValueAsLocalDate(row.getCell(ViewAdjuntoReportColumnEnum.FECHA_DOCUMENTO.getColumnIndex())));
            dto.setFechaVencimiento(ExcelUtilities.getCellValueAsLocalDate(row.getCell(ViewAdjuntoReportColumnEnum.FECHA_VENCIMIENTO.getColumnIndex())));
            dto.setRutaRelativa(ExcelUtilities.getCellValueAsString(row.getCell(ViewAdjuntoReportColumnEnum.RUTA_RELATIVA.getColumnIndex())));
            dto.setSsoComentarioRevision(
                    ExcelUtilities.getCellValueAsString(row.getCell(ViewAdjuntoReportColumnEnum.SSO_COMENTARIO_REVISION.getColumnIndex())));
            dto.setCaComentarioRevision(ExcelUtilities.getCellValueAsString(row.getCell(ViewAdjuntoReportColumnEnum.CA_COMENTARIO_REVISION.getColumnIndex())));
            dto.setProveedorId(ExcelUtilities.getCellValueAsString(row.getCell(ViewAdjuntoReportColumnEnum.PROVEEDOR_ID.getColumnIndex())));
            dto.setTrabajadorId(ExcelUtilities.getCellValueAsString(row.getCell(ViewAdjuntoReportColumnEnum.TRABAJADOR_ID.getColumnIndex())));
            dto.setTipoDocumentoPlanillaCode(
                    ExcelUtilities.getCellValueAsString(row.getCell(ViewAdjuntoReportColumnEnum.TIPO_DOCUMENTO_PLANILLA_CODE.getColumnIndex())));
            dto.setSolicitudVisitaId(ExcelUtilities.getCellValueAsString(row.getCell(ViewAdjuntoReportColumnEnum.SOLICITUD_VISITA_ID.getColumnIndex())));
            dto.setTipoActividadAltoRiesgoCode(
                    ExcelUtilities.getCellValueAsString(row.getCell(ViewAdjuntoReportColumnEnum.TIPO_ACTIVIDAD_ALTO_RIESGO_CODE.getColumnIndex())));
            dto.setTdpDescripcion(ExcelUtilities.getCellValueAsString(row.getCell(ViewAdjuntoReportColumnEnum.TDP_DESCRIPCION.getColumnIndex())));
            dto.setTdpAmbito(ExcelUtilities.getCellValueAsString(row.getCell(ViewAdjuntoReportColumnEnum.TDP_AMBITO.getColumnIndex())));
            dto.setTdpInfluencia(ExcelUtilities.getCellValueAsString(row.getCell(ViewAdjuntoReportColumnEnum.TDP_INFLUENCIA.getColumnIndex())));
            dto.setTdpDependencia(ExcelUtilities.getCellValueAsString(row.getCell(ViewAdjuntoReportColumnEnum.TDP_DEPENDENCIA.getColumnIndex())));
            dto.setTdpCampoPlantillaLf(ExcelUtilities.getCellValueAsString(row.getCell(ViewAdjuntoReportColumnEnum.TDP_CAMPO_PLANTILLA_LF.getColumnIndex())));
            dto.setTdpObligatorio(ExcelUtilities.getCellValueAsBoolean(row.getCell(ViewAdjuntoReportColumnEnum.TDP_OBLIGATORIO.getColumnIndex())));
            dto.setTdpVencimiento(ExcelUtilities.getCellValueAsBoolean(row.getCell(ViewAdjuntoReportColumnEnum.TDP_VENCIMIENTO.getColumnIndex())));
            dto.setTipoPropietario(ExcelUtilities.getCellValueAsString(row.getCell(ViewAdjuntoReportColumnEnum.TIPO_PROPIETARIO.getColumnIndex())));
            dto.setDocumentoVencido(ExcelUtilities.getCellValueAsBoolean(row.getCell(ViewAdjuntoReportColumnEnum.DOCUMENTO_VENCIDO.getColumnIndex())));
            dto.setDiasHastaVencimiento(ExcelUtilities.getCellValueAsLong(row.getCell(ViewAdjuntoReportColumnEnum.DIAS_HASTA_VENCIMIENTO.getColumnIndex())));

            list.add(dto);

        }

        workbook.close();

        return list;

    }

}