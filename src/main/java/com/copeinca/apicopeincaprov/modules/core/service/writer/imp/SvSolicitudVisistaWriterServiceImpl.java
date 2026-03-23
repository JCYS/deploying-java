/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/writer/imp/EntityWriterServiceImpl.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.writer.imp;

import com.copeinca.apicopeincaprov.commons.report.excel.SimpleExcelColumn;
import com.copeinca.apicopeincaprov.commons.report.excel.SimpleExcelExportUtilities;
import com.copeinca.apicopeincaprov.global.dtos.AttachmentData;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvSolicitudVisistaDTO;
import com.copeinca.apicopeincaprov.modules.core.models.writer.enums.SvSolicitudVisistaColumnEnum;
import com.copeinca.apicopeincaprov.modules.core.service.writer.ISvSolicitudVisistaWriterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
* Implementación del servicio de escritura para SvSolicitudVisistaEntity
* Actualmente lanza excepciones para métodos no implementados.
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class SvSolicitudVisistaWriterServiceImpl implements ISvSolicitudVisistaWriterService {

    @Override
    public AttachmentData exportToCsv(List<SvSolicitudVisistaDTO> data) {
        log.warn("Método exportToCsv no implementado para SvSolicitudVisistaEntity");
        throw new UnsupportedOperationException("La exportación a CSV no está implementada para SvSolicitudVisistaEntity");
    }

    @Override
    public AttachmentData exportToExcel(List<SvSolicitudVisistaDTO> data) throws Exception {

        log.info("Iniciando exportación a Excel de {} registros de SvSolicitudVisistaDTO", data != null ? data.size() : 0);

        //--- Crear lista de filas para Excel
        List<List<SimpleExcelColumn>> rows = Optional.ofNullable(data).orElse(new ArrayList<>()).stream()//
                .map(this::defineColumns)//
                .collect(Collectors.toList());

        //--- Generar archivo Excel
        AttachmentData result = SimpleExcelExportUtilities.generateExcelFile(rows, "SvSolicitudVisistas");

        log.info("Archivo Excel generado exitosamente: {}", result.getName());

        return result;

    }

    /**
     * Define las columnas para un usuario específico
     */
    @Override
    public List<SimpleExcelColumn> defineColumns(SvSolicitudVisistaDTO dto) {

        return Arrays.asList(

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvSolicitudVisistaColumnEnum.SOLICITUD_VISITA_ID) //
                        .value(dto.getId()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvSolicitudVisistaColumnEnum.CODIGO_VISITA) //
                        .value(dto.getCodigoVisita()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvSolicitudVisistaColumnEnum.CODIGO_SISTEMA_EXTERNO) //
                        .value(dto.getCodigoSistemaExterno()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvSolicitudVisistaColumnEnum.FECHA_INICIO) //
                        .value(dto.getFechaInicio()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvSolicitudVisistaColumnEnum.FECHA_FIN) //
                        .value(dto.getFechaFin()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvSolicitudVisistaColumnEnum.MOTIVO_VISITA) //
                        .value(dto.getMotivoVisita()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvSolicitudVisistaColumnEnum.FECHA_APROBACION_PERSONA_VISITADA) //
                        .value(dto.getFechaAprobacionPersonaVisitada()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvSolicitudVisistaColumnEnum.FECHA_OBSERVACION_ADMINISTRADOR) //
                        .value(dto.getFechaObservacionAdministrador()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvSolicitudVisistaColumnEnum.OBSERVACIONES) //
                        .value(dto.getObservaciones()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvSolicitudVisistaColumnEnum.NRO_ORDEN_SERVICIO) //
                        .value(dto.getNroOrdenServicio()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvSolicitudVisistaColumnEnum.IND_ACTIVIDAD_ALTO_RIESGO) //
                        .value(dto.getIndActividadAltoRiesgo()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvSolicitudVisistaColumnEnum.IND_ACTIVIDAD_A_BORDO) //
                        .value(dto.getIndActividadABordo()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvSolicitudVisistaColumnEnum.IND_REQUIERE_ANDAMIOS) //
                        .value(dto.getIndRequiereAndamios()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvSolicitudVisistaColumnEnum.IND_REQUIERE_GRUA) //
                        .value(dto.getIndRequiereGrua()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvSolicitudVisistaColumnEnum.IND_REQUIERE_EQUIPOS_MOVILES) //
                        .value(dto.getIndRequiereEquiposMoviles()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvSolicitudVisistaColumnEnum.IND_TRABAJO_BUCEO) //
                        .value(dto.getIndTrabajoBuceo()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvSolicitudVisistaColumnEnum.SSO_MOTIVO) //
                        .value(dto.getSsoMotivo()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvSolicitudVisistaColumnEnum.SSO_DESCARGO) //
                        .value(dto.getSsoDescargo()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvSolicitudVisistaColumnEnum.CA_MOTIVO) //
                        .value(dto.getCaMotivo()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvSolicitudVisistaColumnEnum.CA_DESCARGO) //
                        .value(dto.getCaDescargo()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvSolicitudVisistaColumnEnum.ESTADO_SSO_CODE) //
                        .value(dto.getEstadoSsoCode()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvSolicitudVisistaColumnEnum.ESTADO_CALIDAD_AMBIENTAL_CODE) //
                        .value(dto.getEstadoCalidadAmbientalCode()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvSolicitudVisistaColumnEnum.ESTADO_SOLICITUD_VISITA_CODE) //
                        .value(dto.getEstadoSolicitudVisitaCode()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvSolicitudVisistaColumnEnum.SEDE_ID) //
                        .value(dto.getSedeId()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvSolicitudVisistaColumnEnum.PROVEEDOR_ID) //
                        .value(dto.getProveedorId()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvSolicitudVisistaColumnEnum.PERSONAL_ID) //
                        .value(dto.getPersonalId()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvSolicitudVisistaColumnEnum.IS_DELETED) //
                        .value(dto.getIsDeleted()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvSolicitudVisistaColumnEnum.VERSION) //
                        .value(dto.getVersion()) //
                        .build());

    }
}
