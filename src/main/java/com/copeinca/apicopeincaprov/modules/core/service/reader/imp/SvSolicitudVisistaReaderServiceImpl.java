/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/reader/imp/EntityExcelReaderServiceImpl.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.reader.imp;

import com.copeinca.apicopeincaprov.commons.report.excel.ExcelUtilities;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvSolicitudVisistaDTO;
import com.copeinca.apicopeincaprov.modules.core.models.writer.enums.SvSolicitudVisistaColumnEnum;
import com.copeinca.apicopeincaprov.modules.core.service.reader.ISvSolicitudVisistaExcelReaderService;
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
* Implementación del servicio de lectura para SvSolicitudVisistaEntity
* Actualmente lanza excepciones para métodos no implementados.
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class SvSolicitudVisistaReaderServiceImpl implements ISvSolicitudVisistaExcelReaderService {

    @Override
    public List<SvSolicitudVisistaDTO> readFromCsv(InputStream inputStream) {
        log.warn("Método readFromCsv no implementado para SvSolicitudVisistaEntity");
        throw new UnsupportedOperationException("La lectura de archivos CSV no está implementada para SvSolicitudVisistaEntity");
    }

    @Override
    public List<SvSolicitudVisistaDTO> readFromExcel(InputStream inputStream) throws IOException {

        //--- Convertimos el InputStream en Workbook de Apache POI
        Workbook workbook = ExcelUtilities.getWorkbook(inputStream);

        Sheet sheet = workbook.getSheetAt(0);

        List<SvSolicitudVisistaDTO> list = new ArrayList<>();

        for (Row row : sheet) {

            if (row.getRowNum() == 0)
                continue;

            SvSolicitudVisistaDTO dto = new SvSolicitudVisistaDTO();

            dto.setId(ExcelUtilities.getCellValueAsString(row.getCell(SvSolicitudVisistaColumnEnum.SOLICITUD_VISITA_ID.getColumnIndex())));
            dto.setCodigoVisita(ExcelUtilities.getCellValueAsString(row.getCell(SvSolicitudVisistaColumnEnum.CODIGO_VISITA.getColumnIndex())));
            dto.setCodigoSistemaExterno(ExcelUtilities.getCellValueAsString(row.getCell(SvSolicitudVisistaColumnEnum.CODIGO_SISTEMA_EXTERNO.getColumnIndex())));
            dto.setFechaInicio(ExcelUtilities.getCellValueAsLocalDateTime(row.getCell(SvSolicitudVisistaColumnEnum.FECHA_INICIO.getColumnIndex())));
            dto.setFechaFin(ExcelUtilities.getCellValueAsLocalDateTime(row.getCell(SvSolicitudVisistaColumnEnum.FECHA_FIN.getColumnIndex())));
            dto.setMotivoVisita(ExcelUtilities.getCellValueAsString(row.getCell(SvSolicitudVisistaColumnEnum.MOTIVO_VISITA.getColumnIndex())));
            dto.setFechaAprobacionPersonaVisitada(
                    ExcelUtilities.getCellValueAsLocalDateTime(row.getCell(SvSolicitudVisistaColumnEnum.FECHA_APROBACION_PERSONA_VISITADA.getColumnIndex())));
            dto.setFechaObservacionAdministrador(
                    ExcelUtilities.getCellValueAsLocalDateTime(row.getCell(SvSolicitudVisistaColumnEnum.FECHA_OBSERVACION_ADMINISTRADOR.getColumnIndex())));
            dto.setObservaciones(ExcelUtilities.getCellValueAsString(row.getCell(SvSolicitudVisistaColumnEnum.OBSERVACIONES.getColumnIndex())));
            dto.setNroOrdenServicio(ExcelUtilities.getCellValueAsString(row.getCell(SvSolicitudVisistaColumnEnum.NRO_ORDEN_SERVICIO.getColumnIndex())));
            dto.setIndActividadAltoRiesgo(
                    ExcelUtilities.getCellValueAsBoolean(row.getCell(SvSolicitudVisistaColumnEnum.IND_ACTIVIDAD_ALTO_RIESGO.getColumnIndex())));
            dto.setIndActividadABordo(ExcelUtilities.getCellValueAsBoolean(row.getCell(SvSolicitudVisistaColumnEnum.IND_ACTIVIDAD_A_BORDO.getColumnIndex())));
            dto.setIndRequiereAndamios(ExcelUtilities.getCellValueAsBoolean(row.getCell(SvSolicitudVisistaColumnEnum.IND_REQUIERE_ANDAMIOS.getColumnIndex())));
            dto.setIndRequiereGrua(ExcelUtilities.getCellValueAsBoolean(row.getCell(SvSolicitudVisistaColumnEnum.IND_REQUIERE_GRUA.getColumnIndex())));
            dto.setIndRequiereEquiposMoviles(
                    ExcelUtilities.getCellValueAsBoolean(row.getCell(SvSolicitudVisistaColumnEnum.IND_REQUIERE_EQUIPOS_MOVILES.getColumnIndex())));
            dto.setIndTrabajoBuceo(ExcelUtilities.getCellValueAsBoolean(row.getCell(SvSolicitudVisistaColumnEnum.IND_TRABAJO_BUCEO.getColumnIndex())));
            dto.setSsoMotivo(ExcelUtilities.getCellValueAsString(row.getCell(SvSolicitudVisistaColumnEnum.SSO_MOTIVO.getColumnIndex())));
            dto.setSsoDescargo(ExcelUtilities.getCellValueAsString(row.getCell(SvSolicitudVisistaColumnEnum.SSO_DESCARGO.getColumnIndex())));
            dto.setCaMotivo(ExcelUtilities.getCellValueAsString(row.getCell(SvSolicitudVisistaColumnEnum.CA_MOTIVO.getColumnIndex())));
            dto.setCaDescargo(ExcelUtilities.getCellValueAsString(row.getCell(SvSolicitudVisistaColumnEnum.CA_DESCARGO.getColumnIndex())));
            dto.setEstadoSsoCode(ExcelUtilities.getCellValueAsString(row.getCell(SvSolicitudVisistaColumnEnum.ESTADO_SSO_CODE.getColumnIndex())));
            dto.setEstadoCalidadAmbientalCode(
                    ExcelUtilities.getCellValueAsString(row.getCell(SvSolicitudVisistaColumnEnum.ESTADO_CALIDAD_AMBIENTAL_CODE.getColumnIndex())));
            dto.setEstadoSolicitudVisitaCode(
                    ExcelUtilities.getCellValueAsString(row.getCell(SvSolicitudVisistaColumnEnum.ESTADO_SOLICITUD_VISITA_CODE.getColumnIndex())));
            dto.setSedeId(ExcelUtilities.getCellValueAsString(row.getCell(SvSolicitudVisistaColumnEnum.SEDE_ID.getColumnIndex())));
            dto.setProveedorId(ExcelUtilities.getCellValueAsString(row.getCell(SvSolicitudVisistaColumnEnum.PROVEEDOR_ID.getColumnIndex())));
            dto.setPersonalId(ExcelUtilities.getCellValueAsString(row.getCell(SvSolicitudVisistaColumnEnum.PERSONAL_ID.getColumnIndex())));
            dto.setIsDeleted(ExcelUtilities.getCellValueAsBoolean(row.getCell(SvSolicitudVisistaColumnEnum.IS_DELETED.getColumnIndex())));
            dto.setVersion(ExcelUtilities.getCellValueAsLong(row.getCell(SvSolicitudVisistaColumnEnum.VERSION.getColumnIndex())));

            list.add(dto);

        }

        workbook.close();

        return list;

    }

}