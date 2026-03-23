/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/reader/imp/EntityExcelReaderServiceImpl.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.reader.imp;

import com.copeinca.apicopeincaprov.commons.report.excel.ExcelUtilities;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvSolicitudVisitaEquipoDTO;
import com.copeinca.apicopeincaprov.modules.core.models.writer.enums.SvSolicitudVisitaEquipoColumnEnum;
import com.copeinca.apicopeincaprov.modules.core.service.reader.ISvSolicitudVisitaEquipoExcelReaderService;
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
* Implementación del servicio de lectura para SvSolicitudVisitaEquipoEntity
* Actualmente lanza excepciones para métodos no implementados.
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class SvSolicitudVisitaEquipoReaderServiceImpl implements ISvSolicitudVisitaEquipoExcelReaderService {

    @Override
    public List<SvSolicitudVisitaEquipoDTO> readFromCsv(InputStream inputStream) {
        log.warn("Método readFromCsv no implementado para SvSolicitudVisitaEquipoEntity");
        throw new UnsupportedOperationException("La lectura de archivos CSV no está implementada para SvSolicitudVisitaEquipoEntity");
    }

    @Override
    public List<SvSolicitudVisitaEquipoDTO> readFromExcel(InputStream inputStream) throws IOException {

        //--- Convertimos el InputStream en Workbook de Apache POI
        Workbook workbook = ExcelUtilities.getWorkbook(inputStream);

        Sheet sheet = workbook.getSheetAt(0);

        List<SvSolicitudVisitaEquipoDTO> list = new ArrayList<>();

        for (Row row : sheet) {

            if (row.getRowNum() == 0)
                continue;

            SvSolicitudVisitaEquipoDTO dto = new SvSolicitudVisitaEquipoDTO();

            dto.setId(ExcelUtilities.getCellValueAsString(row.getCell(SvSolicitudVisitaEquipoColumnEnum.SOLICITUD_VISITA_EQUIPO_ID.getColumnIndex())));
            dto.setDescripcion(ExcelUtilities.getCellValueAsString(row.getCell(SvSolicitudVisitaEquipoColumnEnum.DESCRIPCION.getColumnIndex())));
            dto.setMarca(ExcelUtilities.getCellValueAsString(row.getCell(SvSolicitudVisitaEquipoColumnEnum.MARCA.getColumnIndex())));
            dto.setModelo(ExcelUtilities.getCellValueAsString(row.getCell(SvSolicitudVisitaEquipoColumnEnum.MODELO.getColumnIndex())));
            dto.setNumeroSerie(ExcelUtilities.getCellValueAsString(row.getCell(SvSolicitudVisitaEquipoColumnEnum.NUMERO_SERIE.getColumnIndex())));
            dto.setTipoEquipo(ExcelUtilities.getCellValueAsString(row.getCell(SvSolicitudVisitaEquipoColumnEnum.TIPO_EQUIPO.getColumnIndex())));
            dto.setCodigoEquipo(ExcelUtilities.getCellValueAsString(row.getCell(SvSolicitudVisitaEquipoColumnEnum.CODIGO_EQUIPO.getColumnIndex())));
            dto.setCantidad(ExcelUtilities.getCellValueAsBigDecimal(row.getCell(SvSolicitudVisitaEquipoColumnEnum.CANTIDAD.getColumnIndex())));
            dto.setSolicitudVisitaId(ExcelUtilities.getCellValueAsString(row.getCell(SvSolicitudVisitaEquipoColumnEnum.SOLICITUD_VISITA_ID.getColumnIndex())));
            dto.setIsDeleted(ExcelUtilities.getCellValueAsBoolean(row.getCell(SvSolicitudVisitaEquipoColumnEnum.IS_DELETED.getColumnIndex())));
            dto.setVersion(ExcelUtilities.getCellValueAsLong(row.getCell(SvSolicitudVisitaEquipoColumnEnum.VERSION.getColumnIndex())));

            list.add(dto);

        }

        workbook.close();

        return list;

    }

}