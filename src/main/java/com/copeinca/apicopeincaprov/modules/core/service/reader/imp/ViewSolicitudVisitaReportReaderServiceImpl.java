/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/reader/imp/EntityExcelReaderServiceImpl.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.reader.imp;

import com.copeinca.apicopeincaprov.commons.report.excel.ExcelUtilities;
import com.copeinca.apicopeincaprov.modules.core.models.dto.ViewSolicitudVisitaReportDTO;
import com.copeinca.apicopeincaprov.modules.core.models.writer.enums.ViewSolicitudVisitaReportColumnEnum;
import com.copeinca.apicopeincaprov.modules.core.service.reader.IViewSolicitudVisitaReportExcelReaderService;
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
* Implementación del servicio de lectura para ViewSolicitudVisitaReportView
* Actualmente lanza excepciones para métodos no implementados.
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class ViewSolicitudVisitaReportReaderServiceImpl implements IViewSolicitudVisitaReportExcelReaderService {

    @Override
    public List<ViewSolicitudVisitaReportDTO> readFromCsv(InputStream inputStream) {
        log.warn("Método readFromCsv no implementado para ViewSolicitudVisitaReportView");
        throw new UnsupportedOperationException("La lectura de archivos CSV no está implementada para ViewSolicitudVisitaReportView");
    }

    @Override
    public List<ViewSolicitudVisitaReportDTO> readFromExcel(InputStream inputStream) throws IOException {

        //--- Convertimos el InputStream en Workbook de Apache POI
        Workbook workbook = ExcelUtilities.getWorkbook(inputStream);

        Sheet sheet = workbook.getSheetAt(0);

        List<ViewSolicitudVisitaReportDTO> list = new ArrayList<>();

        for (Row row : sheet) {

            if (row.getRowNum() == 0)
                continue;

            ViewSolicitudVisitaReportDTO dto = new ViewSolicitudVisitaReportDTO();

            dto.setId(ExcelUtilities.getCellValueAsString(row.getCell(ViewSolicitudVisitaReportColumnEnum.SOLICITUD_VISITA_ID.getColumnIndex())));
            dto.setCodigoVisita(ExcelUtilities.getCellValueAsString(row.getCell(ViewSolicitudVisitaReportColumnEnum.CODIGO_VISITA.getColumnIndex())));
            dto.setCodigoSistemaExterno(
                    ExcelUtilities.getCellValueAsString(row.getCell(ViewSolicitudVisitaReportColumnEnum.CODIGO_SISTEMA_EXTERNO.getColumnIndex())));
            dto.setFechaInicio(ExcelUtilities.getCellValueAsLocalDateTime(row.getCell(ViewSolicitudVisitaReportColumnEnum.FECHA_INICIO.getColumnIndex())));
            dto.setFechaFin(ExcelUtilities.getCellValueAsLocalDateTime(row.getCell(ViewSolicitudVisitaReportColumnEnum.FECHA_FIN.getColumnIndex())));
            dto.setMotivoVisita(ExcelUtilities.getCellValueAsString(row.getCell(ViewSolicitudVisitaReportColumnEnum.MOTIVO_VISITA.getColumnIndex())));
            dto.setFechaAprobacionPersonaVisitada(ExcelUtilities
                    .getCellValueAsLocalDateTime(row.getCell(ViewSolicitudVisitaReportColumnEnum.FECHA_APROBACION_PERSONA_VISITADA.getColumnIndex())));
            dto.setFechaObservacionAdministrador(ExcelUtilities
                    .getCellValueAsLocalDateTime(row.getCell(ViewSolicitudVisitaReportColumnEnum.FECHA_OBSERVACION_ADMINISTRADOR.getColumnIndex())));
            dto.setObservaciones(ExcelUtilities.getCellValueAsString(row.getCell(ViewSolicitudVisitaReportColumnEnum.OBSERVACIONES.getColumnIndex())));
            dto.setNroOrdenServicio(ExcelUtilities.getCellValueAsString(row.getCell(ViewSolicitudVisitaReportColumnEnum.NRO_ORDEN_SERVICIO.getColumnIndex())));
            dto.setIndActividadAltoRiesgo(
                    ExcelUtilities.getCellValueAsBoolean(row.getCell(ViewSolicitudVisitaReportColumnEnum.IND_ACTIVIDAD_ALTO_RIESGO.getColumnIndex())));
            dto.setIndActividadABordo(
                    ExcelUtilities.getCellValueAsBoolean(row.getCell(ViewSolicitudVisitaReportColumnEnum.IND_ACTIVIDAD_A_BORDO.getColumnIndex())));
            dto.setIndRequiereAndamios(
                    ExcelUtilities.getCellValueAsBoolean(row.getCell(ViewSolicitudVisitaReportColumnEnum.IND_REQUIERE_ANDAMIOS.getColumnIndex())));
            dto.setIndRequiereGrua(ExcelUtilities.getCellValueAsBoolean(row.getCell(ViewSolicitudVisitaReportColumnEnum.IND_REQUIERE_GRUA.getColumnIndex())));
            dto.setIndRequiereEquiposMoviles(
                    ExcelUtilities.getCellValueAsBoolean(row.getCell(ViewSolicitudVisitaReportColumnEnum.IND_REQUIERE_EQUIPOS_MOVILES.getColumnIndex())));
            dto.setIndTrabajoBuceo(ExcelUtilities.getCellValueAsBoolean(row.getCell(ViewSolicitudVisitaReportColumnEnum.IND_TRABAJO_BUCEO.getColumnIndex())));
            dto.setSsoMotivo(ExcelUtilities.getCellValueAsString(row.getCell(ViewSolicitudVisitaReportColumnEnum.SSO_MOTIVO.getColumnIndex())));
            dto.setSsoDescargo(ExcelUtilities.getCellValueAsString(row.getCell(ViewSolicitudVisitaReportColumnEnum.SSO_DESCARGO.getColumnIndex())));
            dto.setCaMotivo(ExcelUtilities.getCellValueAsString(row.getCell(ViewSolicitudVisitaReportColumnEnum.CA_MOTIVO.getColumnIndex())));
            dto.setCaDescargo(ExcelUtilities.getCellValueAsString(row.getCell(ViewSolicitudVisitaReportColumnEnum.CA_DESCARGO.getColumnIndex())));
            dto.setEstadoSsoCode(ExcelUtilities.getCellValueAsString(row.getCell(ViewSolicitudVisitaReportColumnEnum.ESTADO_SSO_CODE.getColumnIndex())));
            dto.setEstadoCalidadAmbientalCode(
                    ExcelUtilities.getCellValueAsString(row.getCell(ViewSolicitudVisitaReportColumnEnum.ESTADO_CALIDAD_AMBIENTAL_CODE.getColumnIndex())));
            dto.setEstadoSolicitudVisitaCode(
                    ExcelUtilities.getCellValueAsString(row.getCell(ViewSolicitudVisitaReportColumnEnum.ESTADO_SOLICITUD_VISITA_CODE.getColumnIndex())));
            dto.setSedeId(ExcelUtilities.getCellValueAsString(row.getCell(ViewSolicitudVisitaReportColumnEnum.SEDE_ID.getColumnIndex())));
            dto.setProveedorId(ExcelUtilities.getCellValueAsString(row.getCell(ViewSolicitudVisitaReportColumnEnum.PROVEEDOR_ID.getColumnIndex())));
            dto.setPersonalId(ExcelUtilities.getCellValueAsString(row.getCell(ViewSolicitudVisitaReportColumnEnum.PERSONAL_ID.getColumnIndex())));
            dto.setSedeNombre(ExcelUtilities.getCellValueAsString(row.getCell(ViewSolicitudVisitaReportColumnEnum.SEDE_NOMBRE.getColumnIndex())));
            dto.setSedeAmbito(ExcelUtilities.getCellValueAsString(row.getCell(ViewSolicitudVisitaReportColumnEnum.SEDE_AMBITO.getColumnIndex())));
            dto.setPersonalNombreUsuario(
                    ExcelUtilities.getCellValueAsString(row.getCell(ViewSolicitudVisitaReportColumnEnum.PERSONAL_NOMBRE_USUARIO.getColumnIndex())));
            dto.setPersonalUsuario(ExcelUtilities.getCellValueAsString(row.getCell(ViewSolicitudVisitaReportColumnEnum.PERSONAL_USUARIO.getColumnIndex())));
            dto.setProveedorNombreFiscal(
                    ExcelUtilities.getCellValueAsString(row.getCell(ViewSolicitudVisitaReportColumnEnum.PROVEEDOR_NOMBRE_FISCAL.getColumnIndex())));
            dto.setProveedorNroDocumentoIdentidad(
                    ExcelUtilities.getCellValueAsString(row.getCell(ViewSolicitudVisitaReportColumnEnum.PROVEEDOR_NRO_DOCUMENTO_IDENTIDAD.getColumnIndex())));
            dto.setProveedorEmail(ExcelUtilities.getCellValueAsString(row.getCell(ViewSolicitudVisitaReportColumnEnum.PROVEEDOR_EMAIL.getColumnIndex())));
            dto.setProveedorContactoPrincipal(
                    ExcelUtilities.getCellValueAsString(row.getCell(ViewSolicitudVisitaReportColumnEnum.PROVEEDOR_CONTACTO_PRINCIPAL.getColumnIndex())));
            dto.setEsvNombre(ExcelUtilities.getCellValueAsString(row.getCell(ViewSolicitudVisitaReportColumnEnum.ESV_NOMBRE.getColumnIndex())));
            dto.setSsoEstadoNombre(ExcelUtilities.getCellValueAsString(row.getCell(ViewSolicitudVisitaReportColumnEnum.SSO_ESTADO_NOMBRE.getColumnIndex())));
            dto.setSsoEstadoDescripcion(
                    ExcelUtilities.getCellValueAsString(row.getCell(ViewSolicitudVisitaReportColumnEnum.SSO_ESTADO_DESCRIPCION.getColumnIndex())));
            dto.setCaEstadoNombre(ExcelUtilities.getCellValueAsString(row.getCell(ViewSolicitudVisitaReportColumnEnum.CA_ESTADO_NOMBRE.getColumnIndex())));
            dto.setCaEstadoDescripcion(
                    ExcelUtilities.getCellValueAsString(row.getCell(ViewSolicitudVisitaReportColumnEnum.CA_ESTADO_DESCRIPCION.getColumnIndex())));
            dto.setTarDescripciones(ExcelUtilities.getCellValueAsString(row.getCell(ViewSolicitudVisitaReportColumnEnum.TAR_DESCRIPCIONES.getColumnIndex())));
            dto.setNDias(ExcelUtilities.getCellValueAsLong(row.getCell(ViewSolicitudVisitaReportColumnEnum.N_DIAS.getColumnIndex())));
            dto.setNTrabajadores(ExcelUtilities.getCellValueAsLong(row.getCell(ViewSolicitudVisitaReportColumnEnum.N_TRABAJADORES.getColumnIndex())));
            dto.setAreasIds(ExcelUtilities.getCellValueAsString(row.getCell(ViewSolicitudVisitaReportColumnEnum.AREAS_IDS.getColumnIndex())));
            dto.setAreasNombres(ExcelUtilities.getCellValueAsString(row.getCell(ViewSolicitudVisitaReportColumnEnum.AREAS_NOMBRES.getColumnIndex())));
            dto.setZonasIds(ExcelUtilities.getCellValueAsString(row.getCell(ViewSolicitudVisitaReportColumnEnum.ZONAS_IDS.getColumnIndex())));
            dto.setZonasNombres(ExcelUtilities.getCellValueAsString(row.getCell(ViewSolicitudVisitaReportColumnEnum.ZONAS_NOMBRES.getColumnIndex())));

            list.add(dto);

        }

        workbook.close();

        return list;

    }

}