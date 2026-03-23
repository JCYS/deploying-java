/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/reader/imp/EntityExcelReaderServiceImpl.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.reader.imp;

import com.copeinca.apicopeincaprov.commons.report.excel.ExcelUtilities;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvSolicitudVisitaMaterialDTO;
import com.copeinca.apicopeincaprov.modules.core.models.writer.enums.SvSolicitudVisitaMaterialColumnEnum;
import com.copeinca.apicopeincaprov.modules.core.service.reader.ISvSolicitudVisitaMaterialExcelReaderService;
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
* Implementación del servicio de lectura para SvSolicitudVisitaMaterialEntity
* Actualmente lanza excepciones para métodos no implementados.
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class SvSolicitudVisitaMaterialReaderServiceImpl implements ISvSolicitudVisitaMaterialExcelReaderService {

    @Override
    public List<SvSolicitudVisitaMaterialDTO> readFromCsv(InputStream inputStream) {
        log.warn("Método readFromCsv no implementado para SvSolicitudVisitaMaterialEntity");
        throw new UnsupportedOperationException("La lectura de archivos CSV no está implementada para SvSolicitudVisitaMaterialEntity");
    }

    @Override
    public List<SvSolicitudVisitaMaterialDTO> readFromExcel(InputStream inputStream) throws IOException {

        //--- Convertimos el InputStream en Workbook de Apache POI
        Workbook workbook = ExcelUtilities.getWorkbook(inputStream);

        Sheet sheet = workbook.getSheetAt(0);

        List<SvSolicitudVisitaMaterialDTO> list = new ArrayList<>();

        for (Row row : sheet) {

            if (row.getRowNum() == 0)
                continue;

            SvSolicitudVisitaMaterialDTO dto = new SvSolicitudVisitaMaterialDTO();

            dto.setId(ExcelUtilities.getCellValueAsString(row.getCell(SvSolicitudVisitaMaterialColumnEnum.SOLICITUD_VISITA_MATERIAL_ID.getColumnIndex())));
            dto.setDescripcion(ExcelUtilities.getCellValueAsString(row.getCell(SvSolicitudVisitaMaterialColumnEnum.DESCRIPCION.getColumnIndex())));
            dto.setCantidad(ExcelUtilities.getCellValueAsBigDecimal(row.getCell(SvSolicitudVisitaMaterialColumnEnum.CANTIDAD.getColumnIndex())));
            dto.setUnidadMedida(ExcelUtilities.getCellValueAsString(row.getCell(SvSolicitudVisitaMaterialColumnEnum.UNIDAD_MEDIDA.getColumnIndex())));
            dto.setSolicitudVisitaId(
                    ExcelUtilities.getCellValueAsString(row.getCell(SvSolicitudVisitaMaterialColumnEnum.SOLICITUD_VISITA_ID.getColumnIndex())));
            dto.setIsDeleted(ExcelUtilities.getCellValueAsBoolean(row.getCell(SvSolicitudVisitaMaterialColumnEnum.IS_DELETED.getColumnIndex())));
            dto.setVersion(ExcelUtilities.getCellValueAsLong(row.getCell(SvSolicitudVisitaMaterialColumnEnum.VERSION.getColumnIndex())));

            list.add(dto);

        }

        workbook.close();

        return list;

    }

}