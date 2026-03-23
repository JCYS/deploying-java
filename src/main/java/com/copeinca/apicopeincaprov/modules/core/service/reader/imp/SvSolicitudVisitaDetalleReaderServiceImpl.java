/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/reader/imp/EntityExcelReaderServiceImpl.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.reader.imp;

import com.copeinca.apicopeincaprov.commons.report.excel.ExcelUtilities;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvSolicitudVisitaDetalleDTO;
import com.copeinca.apicopeincaprov.modules.core.models.writer.enums.SvSolicitudVisitaDetalleColumnEnum;
import com.copeinca.apicopeincaprov.modules.core.service.reader.ISvSolicitudVisitaDetalleExcelReaderService;
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
* Implementación del servicio de lectura para SvSolicitudVisitaDetalleEntity
* Actualmente lanza excepciones para métodos no implementados.
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class SvSolicitudVisitaDetalleReaderServiceImpl implements ISvSolicitudVisitaDetalleExcelReaderService {

    @Override
    public List<SvSolicitudVisitaDetalleDTO> readFromCsv(InputStream inputStream) {
        log.warn("Método readFromCsv no implementado para SvSolicitudVisitaDetalleEntity");
        throw new UnsupportedOperationException("La lectura de archivos CSV no está implementada para SvSolicitudVisitaDetalleEntity");
    }

    @Override
    public List<SvSolicitudVisitaDetalleDTO> readFromExcel(InputStream inputStream) throws IOException {

        //--- Convertimos el InputStream en Workbook de Apache POI
        Workbook workbook = ExcelUtilities.getWorkbook(inputStream);

        Sheet sheet = workbook.getSheetAt(0);

        List<SvSolicitudVisitaDetalleDTO> list = new ArrayList<>();

        for (Row row : sheet) {

            if (row.getRowNum() == 0)
                continue;

            SvSolicitudVisitaDetalleDTO dto = new SvSolicitudVisitaDetalleDTO();

            dto.setId(ExcelUtilities.getCellValueAsString(row.getCell(SvSolicitudVisitaDetalleColumnEnum.SOLICITUD_VISITA_DETALLE_ID.getColumnIndex())));
            dto.setProveedorComentario(
                    ExcelUtilities.getCellValueAsString(row.getCell(SvSolicitudVisitaDetalleColumnEnum.PROVEEDOR_COMENTARIO.getColumnIndex())));
            dto.setSolicitudVisitaId(ExcelUtilities.getCellValueAsString(row.getCell(SvSolicitudVisitaDetalleColumnEnum.SOLICITUD_VISITA_ID.getColumnIndex())));
            dto.setTrabajadorId(ExcelUtilities.getCellValueAsString(row.getCell(SvSolicitudVisitaDetalleColumnEnum.TRABAJADOR_ID.getColumnIndex())));
            dto.setIsDeleted(ExcelUtilities.getCellValueAsBoolean(row.getCell(SvSolicitudVisitaDetalleColumnEnum.IS_DELETED.getColumnIndex())));
            dto.setVersion(ExcelUtilities.getCellValueAsLong(row.getCell(SvSolicitudVisitaDetalleColumnEnum.VERSION.getColumnIndex())));

            list.add(dto);

        }

        workbook.close();

        return list;

    }

}