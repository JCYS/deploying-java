/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/reader/imp/EntityExcelReaderServiceImpl.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.reader.imp;

import com.copeinca.apicopeincaprov.commons.report.excel.ExcelUtilities;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvProveedorDTO;
import com.copeinca.apicopeincaprov.modules.core.models.writer.enums.SvProveedorColumnEnum;
import com.copeinca.apicopeincaprov.modules.core.service.reader.ISvProveedorExcelReaderService;
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
* Implementación del servicio de lectura para SvProveedorEntity
* Actualmente lanza excepciones para métodos no implementados.
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class SvProveedorReaderServiceImpl implements ISvProveedorExcelReaderService {

    @Override
    public List<SvProveedorDTO> readFromCsv(InputStream inputStream) {
        log.warn("Método readFromCsv no implementado para SvProveedorEntity");
        throw new UnsupportedOperationException("La lectura de archivos CSV no está implementada para SvProveedorEntity");
    }

    @Override
    public List<SvProveedorDTO> readFromExcel(InputStream inputStream) throws IOException {

        //--- Convertimos el InputStream en Workbook de Apache POI
        Workbook workbook = ExcelUtilities.getWorkbook(inputStream);

        Sheet sheet = workbook.getSheetAt(0);

        List<SvProveedorDTO> list = new ArrayList<>();

        for (Row row : sheet) {

            if (row.getRowNum() == 0)
                continue;

            SvProveedorDTO dto = new SvProveedorDTO();

            dto.setId(ExcelUtilities.getCellValueAsString(row.getCell(SvProveedorColumnEnum.PROVEEDOR_ID.getColumnIndex())));
            dto.setEstadoCalidadAmbientalCode(
                    ExcelUtilities.getCellValueAsString(row.getCell(SvProveedorColumnEnum.ESTADO_CALIDAD_AMBIENTAL_CODE.getColumnIndex())));
            dto.setNroDocumentoIdentidad(ExcelUtilities.getCellValueAsString(row.getCell(SvProveedorColumnEnum.NRO_DOCUMENTO_IDENTIDAD.getColumnIndex())));
            dto.setNombreFiscal(ExcelUtilities.getCellValueAsString(row.getCell(SvProveedorColumnEnum.NOMBRE_FISCAL.getColumnIndex())));
            dto.setEmail(ExcelUtilities.getCellValueAsString(row.getCell(SvProveedorColumnEnum.EMAIL.getColumnIndex())));
            dto.setDireccion(ExcelUtilities.getCellValueAsString(row.getCell(SvProveedorColumnEnum.DIRECCION.getColumnIndex())));
            dto.setContactoPrincipal(ExcelUtilities.getCellValueAsString(row.getCell(SvProveedorColumnEnum.CONTACTO_PRINCIPAL.getColumnIndex())));
            dto.setContactoPrincipalTelefono(
                    ExcelUtilities.getCellValueAsString(row.getCell(SvProveedorColumnEnum.CONTACTO_PRINCIPAL_TELEFONO.getColumnIndex())));
            dto.setSsoTieneVigencia(ExcelUtilities.getCellValueAsBoolean(row.getCell(SvProveedorColumnEnum.SSO_TIENE_VIGENCIA.getColumnIndex())));
            dto.setSsoFechaInicioVigencia(
                    ExcelUtilities.getCellValueAsLocalDate(row.getCell(SvProveedorColumnEnum.SSO_FECHA_INICIO_VIGENCIA.getColumnIndex())));
            dto.setSsoFechaFinVigencia(ExcelUtilities.getCellValueAsString(row.getCell(SvProveedorColumnEnum.SSO_FECHA_FIN_VIGENCIA.getColumnIndex())));
            dto.setSsoMotivo(ExcelUtilities.getCellValueAsString(row.getCell(SvProveedorColumnEnum.SSO_MOTIVO.getColumnIndex())));
            dto.setSsoUsuarioEvaluador(ExcelUtilities.getCellValueAsString(row.getCell(SvProveedorColumnEnum.SSO_USUARIO_EVALUADOR.getColumnIndex())));
            dto.setSsoFechaEvaluacion(ExcelUtilities.getCellValueAsLocalDateTime(row.getCell(SvProveedorColumnEnum.SSO_FECHA_EVALUACION.getColumnIndex())));
            dto.setIndRealizaActividadAltoRiesgo(
                    ExcelUtilities.getCellValueAsBoolean(row.getCell(SvProveedorColumnEnum.IND_REALIZA_ACTIVIDAD_ALTO_RIESGO.getColumnIndex())));
            dto.setIndRealizaActividadABordo(
                    ExcelUtilities.getCellValueAsBoolean(row.getCell(SvProveedorColumnEnum.IND_REALIZA_ACTIVIDAD_A_BORDO.getColumnIndex())));
            dto.setCaTieneVigencia(ExcelUtilities.getCellValueAsBoolean(row.getCell(SvProveedorColumnEnum.CA_TIENE_VIGENCIA.getColumnIndex())));
            dto.setCaFechaInicioVigencia(ExcelUtilities.getCellValueAsLocalDate(row.getCell(SvProveedorColumnEnum.CA_FECHA_INICIO_VIGENCIA.getColumnIndex())));
            dto.setCaFechaFinVigencia(ExcelUtilities.getCellValueAsLocalDate(row.getCell(SvProveedorColumnEnum.CA_FECHA_FIN_VIGENCIA.getColumnIndex())));
            dto.setCaMotivo(ExcelUtilities.getCellValueAsString(row.getCell(SvProveedorColumnEnum.CA_MOTIVO.getColumnIndex())));
            dto.setCaUsuarioEvaluador(ExcelUtilities.getCellValueAsString(row.getCell(SvProveedorColumnEnum.CA_USUARIO_EVALUADOR.getColumnIndex())));
            dto.setCaFechaEvaluacion(ExcelUtilities.getCellValueAsLocalDateTime(row.getCell(SvProveedorColumnEnum.CA_FECHA_EVALUACION.getColumnIndex())));
            dto.setOrigenRegistro(ExcelUtilities.getCellValueAsString(row.getCell(SvProveedorColumnEnum.ORIGEN_REGISTRO.getColumnIndex())));
            dto.setEstadoSsoCode(ExcelUtilities.getCellValueAsString(row.getCell(SvProveedorColumnEnum.ESTADO_SSO_CODE.getColumnIndex())));
            dto.setIndProveedorNotificadoCreacion(
                    ExcelUtilities.getCellValueAsBoolean(row.getCell(SvProveedorColumnEnum.IND_PROVEEDOR_NOTIFICADO_CREACION.getColumnIndex())));
            dto.setTelefono(ExcelUtilities.getCellValueAsString(row.getCell(SvProveedorColumnEnum.TELEFONO.getColumnIndex())));
            dto.setNacional(ExcelUtilities.getCellValueAsBoolean(row.getCell(SvProveedorColumnEnum.NACIONAL.getColumnIndex())));
            dto.setIsDeleted(ExcelUtilities.getCellValueAsBoolean(row.getCell(SvProveedorColumnEnum.IS_DELETED.getColumnIndex())));
            dto.setVersion(ExcelUtilities.getCellValueAsLong(row.getCell(SvProveedorColumnEnum.VERSION.getColumnIndex())));
            dto.setSedesNombres(ExcelUtilities.getCellValueAsString(row.getCell(SvProveedorColumnEnum.SEDES.getColumnIndex())));

            list.add(dto);

        }

        workbook.close();

        return list;

    }

}