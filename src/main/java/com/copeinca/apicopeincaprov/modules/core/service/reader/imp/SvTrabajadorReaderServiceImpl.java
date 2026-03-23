/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/reader/imp/EntityExcelReaderServiceImpl.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.reader.imp;

import com.copeinca.apicopeincaprov.commons.report.excel.ExcelUtilities;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvTrabajadorDTO;
import com.copeinca.apicopeincaprov.modules.core.models.writer.enums.SvTrabajadorColumnEnum;
import com.copeinca.apicopeincaprov.modules.core.service.reader.ISvTrabajadorExcelReaderService;
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
* Implementación del servicio de lectura para SvTrabajadorEntity
* Actualmente lanza excepciones para métodos no implementados.
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class SvTrabajadorReaderServiceImpl implements ISvTrabajadorExcelReaderService {

    @Override
    public List<SvTrabajadorDTO> readFromCsv(InputStream inputStream) {
        log.warn("Método readFromCsv no implementado para SvTrabajadorEntity");
        throw new UnsupportedOperationException("La lectura de archivos CSV no está implementada para SvTrabajadorEntity");
    }

    @Override
    public List<SvTrabajadorDTO> readFromExcel(InputStream inputStream) throws IOException {

        //--- Convertimos el InputStream en Workbook de Apache POI
        Workbook workbook = ExcelUtilities.getWorkbook(inputStream);

        Sheet sheet = workbook.getSheetAt(0);

        List<SvTrabajadorDTO> list = new ArrayList<>();

        for (Row row : sheet) {

            if (row.getRowNum() == 0)
                continue;

            SvTrabajadorDTO dto = new SvTrabajadorDTO();

//            dto.setId(ExcelUtilities.getCellValueAsString(row.getCell(SvTrabajadorColumnEnum.TRABAJADOR_ID.getColumnIndex())));
            dto.setId(null);
//            dto.setProveedorId(ExcelUtilities.getCellValueAsString(row.getCell(SvTrabajadorColumnEnum.PROVEEDOR_ID.getColumnIndex())));
            dto.setProveedorId(null);
            dto.setTipoDocumentoIdentidadCode(
                    ExcelUtilities.getCellValueAsString(row.getCell(SvTrabajadorColumnEnum.TIPO_DOCUMENTO_IDENTIDAD_CODE.getColumnIndex())));
            dto.setNroDocumentoIdentidad(ExcelUtilities.getCellValueAsString(row.getCell(SvTrabajadorColumnEnum.NRO_DOCUMENTO_IDENTIDAD.getColumnIndex())));
            dto.setNombre(ExcelUtilities.getCellValueAsString(row.getCell(SvTrabajadorColumnEnum.NOMBRE.getColumnIndex())));
            dto.setTelefono(ExcelUtilities.getCellValueAsString(row.getCell(SvTrabajadorColumnEnum.TELEFONO.getColumnIndex())));
            dto.setEmail(ExcelUtilities.getCellValueAsString(row.getCell(SvTrabajadorColumnEnum.EMAIL.getColumnIndex())));
//            dto.setSsoMotivo(ExcelUtilities.getCellValueAsString(row.getCell(SvTrabajadorColumnEnum.SSO_MOTIVO.getColumnIndex())));
//            dto.setSoTieneVigencia(ExcelUtilities.getCellValueAsBoolean(row.getCell(SvTrabajadorColumnEnum.SO_TIENE_VIGENCIA.getColumnIndex())));
//            dto.setSsoFechaInicioVigencia(
//                    ExcelUtilities.getCellValueAsLocalDate(row.getCell(SvTrabajadorColumnEnum.SSO_FECHA_INICIO_VIGENCIA.getColumnIndex())));
//            dto.setSsoFechaFinVigencia(ExcelUtilities.getCellValueAsLocalDate(row.getCell(SvTrabajadorColumnEnum.SSO_FECHA_FIN_VIGENCIA.getColumnIndex())));
            dto.setSsoMotivo(null);
            dto.setSoTieneVigencia(null);
            dto.setSsoFechaInicioVigencia(
                   null);
            dto.setSsoFechaFinVigencia(null);
            dto.setIndTrabajoAltoRiesgo(ExcelUtilities.getCellValueAsBoolean(row.getCell(SvTrabajadorColumnEnum.IND_TRABAJO_ALTO_RIESGO.getColumnIndex())));
            dto.setIndPrevencionista(ExcelUtilities.getCellValueAsBoolean(row.getCell(SvTrabajadorColumnEnum.IND_PREVENCIONISTA.getColumnIndex())));
//            dto.setEstadoSsoCode(ExcelUtilities.getCellValueAsString(row.getCell(SvTrabajadorColumnEnum.ESTADO_SSO_CODE.getColumnIndex())));
            dto.setEstadoSsoCode(null);
//            dto.setIsActive(ExcelUtilities.getCellValueAsBoolean(row.getCell(SvTrabajadorColumnEnum.IS_ACTIVE.getColumnIndex())));
//            dto.setIsDeleted(ExcelUtilities.getCellValueAsBoolean(row.getCell(SvTrabajadorColumnEnum.IS_DELETED.getColumnIndex())));
//            dto.setVersion(ExcelUtilities.getCellValueAsLong(row.getCell(SvTrabajadorColumnEnum.VERSION.getColumnIndex())));
            dto.setIsActive(true);
            dto.setIsDeleted(false);
            dto.setVersion(null);

            list.add(dto);

        }

        workbook.close();

        return list;

    }

}