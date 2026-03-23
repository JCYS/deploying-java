/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/reader/imp/EntityExcelReaderServiceImpl.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.reader.imp;

import com.copeinca.apicopeincaprov.commons.report.excel.ExcelUtilities;
import com.copeinca.apicopeincaprov.modules.core.models.dto.ViewProveedorReportDTO;
import com.copeinca.apicopeincaprov.modules.core.models.writer.enums.ViewProveedorReportColumnEnum;
import com.copeinca.apicopeincaprov.modules.core.service.reader.IViewProveedorReportExcelReaderService;
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
* Implementación del servicio de lectura para ViewProveedorReportView
* Actualmente lanza excepciones para métodos no implementados.
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class ViewProveedorReportReaderServiceImpl implements IViewProveedorReportExcelReaderService {

    @Override
    public List<ViewProveedorReportDTO> readFromCsv(InputStream inputStream) {
        log.warn("Método readFromCsv no implementado para ViewProveedorReportView");
        throw new UnsupportedOperationException("La lectura de archivos CSV no está implementada para ViewProveedorReportView");
    }

    @Override
    public List<ViewProveedorReportDTO> readFromExcel(InputStream inputStream) throws IOException {

        //--- Convertimos el InputStream en Workbook de Apache POI
        Workbook workbook = ExcelUtilities.getWorkbook(inputStream);

        Sheet sheet = workbook.getSheetAt(0);

        List<ViewProveedorReportDTO> list = new ArrayList<>();

        for (Row row : sheet) {

            if (row.getRowNum() == 0)
                continue;

            ViewProveedorReportDTO dto = new ViewProveedorReportDTO();

            dto.setId(ExcelUtilities.getCellValueAsString(row.getCell(ViewProveedorReportColumnEnum.PROVEEDOR_ID.getColumnIndex())));
            dto.setNroDocumentoIdentidad(
                    ExcelUtilities.getCellValueAsString(row.getCell(ViewProveedorReportColumnEnum.NRO_DOCUMENTO_IDENTIDAD.getColumnIndex())));
            dto.setNombreFiscal(ExcelUtilities.getCellValueAsString(row.getCell(ViewProveedorReportColumnEnum.NOMBRE_FISCAL.getColumnIndex())));
            dto.setEmail(ExcelUtilities.getCellValueAsString(row.getCell(ViewProveedorReportColumnEnum.EMAIL.getColumnIndex())));
            dto.setDireccion(ExcelUtilities.getCellValueAsString(row.getCell(ViewProveedorReportColumnEnum.DIRECCION.getColumnIndex())));
            dto.setTelefono(ExcelUtilities.getCellValueAsString(row.getCell(ViewProveedorReportColumnEnum.TELEFONO.getColumnIndex())));
            dto.setNacional(ExcelUtilities.getCellValueAsBoolean(row.getCell(ViewProveedorReportColumnEnum.NACIONAL.getColumnIndex())));
            dto.setContactoPrincipal(ExcelUtilities.getCellValueAsString(row.getCell(ViewProveedorReportColumnEnum.CONTACTO_PRINCIPAL.getColumnIndex())));
            dto.setContactoPrincipalTelefono(
                    ExcelUtilities.getCellValueAsString(row.getCell(ViewProveedorReportColumnEnum.CONTACTO_PRINCIPAL_TELEFONO.getColumnIndex())));
            dto.setOrigenRegistro(ExcelUtilities.getCellValueAsString(row.getCell(ViewProveedorReportColumnEnum.ORIGEN_REGISTRO.getColumnIndex())));
            dto.setSedesIds(ExcelUtilities.getCellValueAsString(row.getCell(ViewProveedorReportColumnEnum.SEDES_IDS.getColumnIndex())));
            dto.setSedesNombres(ExcelUtilities.getCellValueAsString(row.getCell(ViewProveedorReportColumnEnum.SEDES_NOMBRES.getColumnIndex())));
            dto.setEstadoSsoCode(ExcelUtilities.getCellValueAsString(row.getCell(ViewProveedorReportColumnEnum.ESTADO_SSO_CODE.getColumnIndex())));
            dto.setSsoTieneVigencia(ExcelUtilities.getCellValueAsBoolean(row.getCell(ViewProveedorReportColumnEnum.SSO_TIENE_VIGENCIA.getColumnIndex())));
            dto.setSsoFechaInicioVigencia(
                    ExcelUtilities.getCellValueAsLocalDate(row.getCell(ViewProveedorReportColumnEnum.SSO_FECHA_INICIO_VIGENCIA.getColumnIndex())));
            dto.setSsoFechaFinVigencia(ExcelUtilities.getCellValueAsString(row.getCell(ViewProveedorReportColumnEnum.SSO_FECHA_FIN_VIGENCIA.getColumnIndex())));
            dto.setSsoMotivo(ExcelUtilities.getCellValueAsString(row.getCell(ViewProveedorReportColumnEnum.SSO_MOTIVO.getColumnIndex())));
            dto.setSsoUsuarioEvaluador(ExcelUtilities.getCellValueAsString(row.getCell(ViewProveedorReportColumnEnum.SSO_USUARIO_EVALUADOR.getColumnIndex())));
            dto.setSsoFechaEvaluacion(
                    ExcelUtilities.getCellValueAsLocalDateTime(row.getCell(ViewProveedorReportColumnEnum.SSO_FECHA_EVALUACION.getColumnIndex())));
            dto.setSsoEstadoNombre(ExcelUtilities.getCellValueAsString(row.getCell(ViewProveedorReportColumnEnum.SSO_ESTADO_NOMBRE.getColumnIndex())));
            dto.setSsoEstadoDescripcion(
                    ExcelUtilities.getCellValueAsString(row.getCell(ViewProveedorReportColumnEnum.SSO_ESTADO_DESCRIPCION.getColumnIndex())));
            dto.setSsoEstadoVigente(ExcelUtilities.getCellValueAsString(row.getCell(ViewProveedorReportColumnEnum.SSO_ESTADO_VIGENTE.getColumnIndex())));
            dto.setEstadoCalidadAmbientalCode(
                    ExcelUtilities.getCellValueAsString(row.getCell(ViewProveedorReportColumnEnum.ESTADO_CALIDAD_AMBIENTAL_CODE.getColumnIndex())));
            dto.setCaTieneVigencia(ExcelUtilities.getCellValueAsBoolean(row.getCell(ViewProveedorReportColumnEnum.CA_TIENE_VIGENCIA.getColumnIndex())));
            dto.setCaFechaInicioVigencia(
                    ExcelUtilities.getCellValueAsLocalDate(row.getCell(ViewProveedorReportColumnEnum.CA_FECHA_INICIO_VIGENCIA.getColumnIndex())));
            dto.setCaFechaFinVigencia(
                    ExcelUtilities.getCellValueAsLocalDate(row.getCell(ViewProveedorReportColumnEnum.CA_FECHA_FIN_VIGENCIA.getColumnIndex())));
            dto.setCaMotivo(ExcelUtilities.getCellValueAsString(row.getCell(ViewProveedorReportColumnEnum.CA_MOTIVO.getColumnIndex())));
            dto.setCaUsuarioEvaluador(ExcelUtilities.getCellValueAsString(row.getCell(ViewProveedorReportColumnEnum.CA_USUARIO_EVALUADOR.getColumnIndex())));
            dto.setCaFechaEvaluacion(
                    ExcelUtilities.getCellValueAsLocalDateTime(row.getCell(ViewProveedorReportColumnEnum.CA_FECHA_EVALUACION.getColumnIndex())));
            dto.setCaEstadoNombre(ExcelUtilities.getCellValueAsString(row.getCell(ViewProveedorReportColumnEnum.CA_ESTADO_NOMBRE.getColumnIndex())));
            dto.setCaEstadoDescripcion(ExcelUtilities.getCellValueAsString(row.getCell(ViewProveedorReportColumnEnum.CA_ESTADO_DESCRIPCION.getColumnIndex())));
            dto.setCaEstadoVigente(ExcelUtilities.getCellValueAsString(row.getCell(ViewProveedorReportColumnEnum.CA_ESTADO_VIGENTE.getColumnIndex())));
            dto.setNTrabajadoresAptos(ExcelUtilities.getCellValueAsLong(row.getCell(ViewProveedorReportColumnEnum.N_TRABAJADORES_APTOS.getColumnIndex())));
            dto.setNTrabajadoresTotales(ExcelUtilities.getCellValueAsLong(row.getCell(ViewProveedorReportColumnEnum.N_TRABAJADORES_TOTALES.getColumnIndex())));
            dto.setNDocsProvSso(ExcelUtilities.getCellValueAsLong(row.getCell(ViewProveedorReportColumnEnum.N_DOCS_PROV_SSO.getColumnIndex())));
            dto.setNDocsCa(ExcelUtilities.getCellValueAsLong(row.getCell(ViewProveedorReportColumnEnum.N_DOCS_CA.getColumnIndex())));
            dto.setNDocsAar(ExcelUtilities.getCellValueAsLong(row.getCell(ViewProveedorReportColumnEnum.N_DOCS_AAR.getColumnIndex())));
            dto.setNDocsAb(ExcelUtilities.getCellValueAsLong(row.getCell(ViewProveedorReportColumnEnum.N_DOCS_AB.getColumnIndex())));
            dto.setNDocsTrabajSso(ExcelUtilities.getCellValueAsLong(row.getCell(ViewProveedorReportColumnEnum.N_DOCS_TRABAJ_SSO.getColumnIndex())));
            dto.setIndRealizaActividadAltoRiesgo(
                    ExcelUtilities.getCellValueAsBoolean(row.getCell(ViewProveedorReportColumnEnum.IND_REALIZA_ACTIVIDAD_ALTO_RIESGO.getColumnIndex())));
            dto.setIndRealizaActividadABordo(
                    ExcelUtilities.getCellValueAsBoolean(row.getCell(ViewProveedorReportColumnEnum.IND_REALIZA_ACTIVIDAD_A_BORDO.getColumnIndex())));
            dto.setTarCodigos(ExcelUtilities.getCellValueAsString(row.getCell(ViewProveedorReportColumnEnum.TAR_CODIGOS.getColumnIndex())));
            dto.setTarDescripciones(ExcelUtilities.getCellValueAsString(row.getCell(ViewProveedorReportColumnEnum.TAR_DESCRIPCIONES.getColumnIndex())));

            list.add(dto);

        }

        workbook.close();

        return list;

    }

}