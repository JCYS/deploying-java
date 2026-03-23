/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/reader/imp/EntityExcelReaderServiceImpl.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.reader.imp;

import com.copeinca.apicopeincaprov.commons.report.excel.ExcelUtilities;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvAdjuntoDTO;
import com.copeinca.apicopeincaprov.modules.core.models.writer.enums.SvAdjuntoColumnEnum;
import com.copeinca.apicopeincaprov.modules.core.service.reader.ISvAdjuntoExcelReaderService;
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
* Implementación del servicio de lectura para SvAdjuntoEntity
* Actualmente lanza excepciones para métodos no implementados.
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class SvAdjuntoReaderServiceImpl implements ISvAdjuntoExcelReaderService {

    @Override
    public List<SvAdjuntoDTO> readFromCsv(InputStream inputStream) {
        log.warn("Método readFromCsv no implementado para SvAdjuntoEntity");
        throw new UnsupportedOperationException("La lectura de archivos CSV no está implementada para SvAdjuntoEntity");
    }

    @Override
    public List<SvAdjuntoDTO> readFromExcel(InputStream inputStream) throws IOException {

        //--- Convertimos el InputStream en Workbook de Apache POI
        Workbook workbook = ExcelUtilities.getWorkbook(inputStream);

        Sheet sheet = workbook.getSheetAt(0);

        List<SvAdjuntoDTO> list = new ArrayList<>();

        for (Row row : sheet) {

            if (row.getRowNum() == 0)
                continue;

            SvAdjuntoDTO dto = new SvAdjuntoDTO();

            dto.setId(ExcelUtilities.getCellValueAsString(row.getCell(SvAdjuntoColumnEnum.ADJUNTO_ID.getColumnIndex())));
            dto.setNombreArchivo(ExcelUtilities.getCellValueAsString(row.getCell(SvAdjuntoColumnEnum.NOMBRE_ARCHIVO.getColumnIndex())));
            dto.setIdRepositorio(ExcelUtilities.getCellValueAsString(row.getCell(SvAdjuntoColumnEnum.ID_REPOSITORIO.getColumnIndex())));
            dto.setClasification(ExcelUtilities.getCellValueAsString(row.getCell(SvAdjuntoColumnEnum.CLASIFICATION.getColumnIndex())));
            dto.setFechaDocumento(ExcelUtilities.getCellValueAsLocalDate(row.getCell(SvAdjuntoColumnEnum.FECHA_DOCUMENTO.getColumnIndex())));
            dto.setFechaVencimiento(ExcelUtilities.getCellValueAsLocalDate(row.getCell(SvAdjuntoColumnEnum.FECHA_VENCIMIENTO.getColumnIndex())));
            dto.setRutaRelativa(ExcelUtilities.getCellValueAsString(row.getCell(SvAdjuntoColumnEnum.RUTA_RELATIVA.getColumnIndex())));
            dto.setSsoComentarioRevision(ExcelUtilities.getCellValueAsString(row.getCell(SvAdjuntoColumnEnum.SSO_COMENTARIO_REVISION.getColumnIndex())));
            dto.setCaComentarioRevision(ExcelUtilities.getCellValueAsString(row.getCell(SvAdjuntoColumnEnum.CA_COMENTARIO_REVISION.getColumnIndex())));
            dto.setMotivo(ExcelUtilities.getCellValueAsString(row.getCell(SvAdjuntoColumnEnum.MOTIVO.getColumnIndex())));
            dto.setProveedorId(ExcelUtilities.getCellValueAsString(row.getCell(SvAdjuntoColumnEnum.PROVEEDOR_ID.getColumnIndex())));
            dto.setTrabajadorId(ExcelUtilities.getCellValueAsString(row.getCell(SvAdjuntoColumnEnum.TRABAJADOR_ID.getColumnIndex())));
            dto.setTipoDocumentoPlanillaCode(
                    ExcelUtilities.getCellValueAsString(row.getCell(SvAdjuntoColumnEnum.TIPO_DOCUMENTO_PLANILLA_CODE.getColumnIndex())));
            dto.setSolicitudVisitaId(ExcelUtilities.getCellValueAsString(row.getCell(SvAdjuntoColumnEnum.SOLICITUD_VISITA_ID.getColumnIndex())));
            dto.setTipoActividadAltoRiesgoCode(
                    ExcelUtilities.getCellValueAsString(row.getCell(SvAdjuntoColumnEnum.TIPO_ACTIVIDAD_ALTO_RIESGO_CODE.getColumnIndex())));
            dto.setIsDeleted(ExcelUtilities.getCellValueAsBoolean(row.getCell(SvAdjuntoColumnEnum.IS_DELETED.getColumnIndex())));
            dto.setVersion(ExcelUtilities.getCellValueAsLong(row.getCell(SvAdjuntoColumnEnum.VERSION.getColumnIndex())));

            list.add(dto);

        }

        workbook.close();

        return list;

    }

}