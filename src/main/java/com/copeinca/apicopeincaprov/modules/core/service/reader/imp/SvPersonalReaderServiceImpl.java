/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/reader/imp/EntityExcelReaderServiceImpl.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.reader.imp;

import com.copeinca.apicopeincaprov.commons.report.excel.ExcelUtilities;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvPersonalDTO;
import com.copeinca.apicopeincaprov.modules.core.models.writer.enums.SvPersonalColumnEnum;
import com.copeinca.apicopeincaprov.modules.core.service.reader.ISvPersonalExcelReaderService;
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
* Implementación del servicio de lectura para SvPersonalEntity
* Actualmente lanza excepciones para métodos no implementados.
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class SvPersonalReaderServiceImpl implements ISvPersonalExcelReaderService {

    @Override
    public List<SvPersonalDTO> readFromCsv(InputStream inputStream) {
        log.warn("Método readFromCsv no implementado para SvPersonalEntity");
        throw new UnsupportedOperationException("La lectura de archivos CSV no está implementada para SvPersonalEntity");
    }

    @Override
    public List<SvPersonalDTO> readFromExcel(InputStream inputStream) throws IOException {

        //--- Convertimos el InputStream en Workbook de Apache POI
        Workbook workbook = ExcelUtilities.getWorkbook(inputStream);

        Sheet sheet = workbook.getSheetAt(0);

        List<SvPersonalDTO> list = new ArrayList<>();

        for (Row row : sheet) {

            if (row.getRowNum() == 0)
                continue;

            SvPersonalDTO dto = new SvPersonalDTO();

            dto.setId(ExcelUtilities.getCellValueAsString(row.getCell(SvPersonalColumnEnum.PERSONAL_ID.getColumnIndex())));
            dto.setUsuario(ExcelUtilities.getCellValueAsString(row.getCell(SvPersonalColumnEnum.USUARIO.getColumnIndex())));
            dto.setNombreUsuario(ExcelUtilities.getCellValueAsString(row.getCell(SvPersonalColumnEnum.NOMBRE_USUARIO.getColumnIndex())));
            dto.setCodigo(ExcelUtilities.getCellValueAsString(row.getCell(SvPersonalColumnEnum.CODIGO.getColumnIndex())));
            dto.setDescripcion(ExcelUtilities.getCellValueAsString(row.getCell(SvPersonalColumnEnum.DESCRIPCION.getColumnIndex())));
            dto.setAmbito(ExcelUtilities.getCellValueAsString(row.getCell(SvPersonalColumnEnum.AMBITO.getColumnIndex())));
            dto.setInfluencia(ExcelUtilities.getCellValueAsString(row.getCell(SvPersonalColumnEnum.INFLUENCIA.getColumnIndex())));
            dto.setDependencia(ExcelUtilities.getCellValueAsString(row.getCell(SvPersonalColumnEnum.DEPENDENCIA.getColumnIndex())));
            dto.setCampoPlantillaLf(ExcelUtilities.getCellValueAsString(row.getCell(SvPersonalColumnEnum.CAMPO_PLANTILLA_LF.getColumnIndex())));
            dto.setObligatorio(ExcelUtilities.getCellValueAsString(row.getCell(SvPersonalColumnEnum.OBLIGATORIO.getColumnIndex())));
            dto.setVencimiento(ExcelUtilities.getCellValueAsString(row.getCell(SvPersonalColumnEnum.VENCIMIENTO.getColumnIndex())));
            dto.setNumeroCaso(ExcelUtilities.getCellValueAsString(row.getCell(SvPersonalColumnEnum.NUMERO_CASO.getColumnIndex())));
            dto.setMensaje(ExcelUtilities.getCellValueAsString(row.getCell(SvPersonalColumnEnum.MENSAJE.getColumnIndex())));
            dto.setTipoDocumentoIdentidadCode(
                    ExcelUtilities.getCellValueAsString(row.getCell(SvPersonalColumnEnum.TIPO_DOCUMENTO_IDENTIDAD_CODE.getColumnIndex())));
            dto.setIsDeleted(ExcelUtilities.getCellValueAsBoolean(row.getCell(SvPersonalColumnEnum.IS_DELETED.getColumnIndex())));
            dto.setVersion(ExcelUtilities.getCellValueAsLong(row.getCell(SvPersonalColumnEnum.VERSION.getColumnIndex())));

            list.add(dto);

        }

        workbook.close();

        return list;

    }

}