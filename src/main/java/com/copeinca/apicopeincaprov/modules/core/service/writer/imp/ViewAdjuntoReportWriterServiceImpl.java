/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/writer/imp/EntityWriterServiceImpl.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.writer.imp;

import com.copeinca.apicopeincaprov.commons.report.excel.SimpleExcelColumn;
import com.copeinca.apicopeincaprov.commons.report.excel.SimpleExcelExportUtilities;
import com.copeinca.apicopeincaprov.global.dtos.AttachmentData;
import com.copeinca.apicopeincaprov.modules.core.models.dto.ViewAdjuntoReportDTO;
import com.copeinca.apicopeincaprov.modules.core.models.writer.enums.ViewAdjuntoReportColumnEnum;
import com.copeinca.apicopeincaprov.modules.core.service.writer.IViewAdjuntoReportWriterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
* Implementación del servicio de escritura para ViewAdjuntoReportView
* Actualmente lanza excepciones para métodos no implementados.
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class ViewAdjuntoReportWriterServiceImpl implements IViewAdjuntoReportWriterService {

    @Override
    public AttachmentData exportToCsv(List<ViewAdjuntoReportDTO> data) {
        log.warn("Método exportToCsv no implementado para ViewAdjuntoReportView");
        throw new UnsupportedOperationException("La exportación a CSV no está implementada para ViewAdjuntoReportView");
    }

    @Override
    public AttachmentData exportToExcel(List<ViewAdjuntoReportDTO> data) throws Exception {

        log.info("Iniciando exportación a Excel de {} registros de ViewAdjuntoReportDTO", data != null ? data.size() : 0);

        //--- Crear lista de filas para Excel
        List<List<SimpleExcelColumn>> rows = Optional.ofNullable(data).orElse(new ArrayList<>()).stream()//
                .map(this::defineColumns)//
                .collect(Collectors.toList());

        //--- Generar archivo Excel
        AttachmentData result = SimpleExcelExportUtilities.generateExcelFile(rows, "ViewAdjuntoReports");

        log.info("Archivo Excel generado exitosamente: {}", result.getName());

        return result;

    }

    /**
     * Define las columnas para un usuario específico
     */
    @Override
    public List<SimpleExcelColumn> defineColumns(ViewAdjuntoReportDTO dto) {

        return Arrays.asList(

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(ViewAdjuntoReportColumnEnum.ADJUNTO_ID) //
                        .value(dto.getId()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(ViewAdjuntoReportColumnEnum.NOMBRE_ARCHIVO) //
                        .value(dto.getNombreArchivo()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(ViewAdjuntoReportColumnEnum.ID_REPOSITORIO) //
                        .value(dto.getIdRepositorio()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(ViewAdjuntoReportColumnEnum.CLASIFICATION) //
                        .value(dto.getClasification()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(ViewAdjuntoReportColumnEnum.FECHA_DOCUMENTO) //
                        .value(dto.getFechaDocumento()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(ViewAdjuntoReportColumnEnum.FECHA_VENCIMIENTO) //
                        .value(dto.getFechaVencimiento()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(ViewAdjuntoReportColumnEnum.RUTA_RELATIVA) //
                        .value(dto.getRutaRelativa()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(ViewAdjuntoReportColumnEnum.SSO_COMENTARIO_REVISION) //
                        .value(dto.getSsoComentarioRevision()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(ViewAdjuntoReportColumnEnum.CA_COMENTARIO_REVISION) //
                        .value(dto.getCaComentarioRevision()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(ViewAdjuntoReportColumnEnum.PROVEEDOR_ID) //
                        .value(dto.getProveedorId()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(ViewAdjuntoReportColumnEnum.TRABAJADOR_ID) //
                        .value(dto.getTrabajadorId()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(ViewAdjuntoReportColumnEnum.TIPO_DOCUMENTO_PLANILLA_CODE) //
                        .value(dto.getTipoDocumentoPlanillaCode()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(ViewAdjuntoReportColumnEnum.SOLICITUD_VISITA_ID) //
                        .value(dto.getSolicitudVisitaId()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(ViewAdjuntoReportColumnEnum.TIPO_ACTIVIDAD_ALTO_RIESGO_CODE) //
                        .value(dto.getTipoActividadAltoRiesgoCode()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(ViewAdjuntoReportColumnEnum.TDP_DESCRIPCION) //
                        .value(dto.getTdpDescripcion()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(ViewAdjuntoReportColumnEnum.TDP_AMBITO) //
                        .value(dto.getTdpAmbito()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(ViewAdjuntoReportColumnEnum.TDP_INFLUENCIA) //
                        .value(dto.getTdpInfluencia()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(ViewAdjuntoReportColumnEnum.TDP_DEPENDENCIA) //
                        .value(dto.getTdpDependencia()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(ViewAdjuntoReportColumnEnum.TDP_CAMPO_PLANTILLA_LF) //
                        .value(dto.getTdpCampoPlantillaLf()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(ViewAdjuntoReportColumnEnum.TDP_OBLIGATORIO) //
                        .value(dto.getTdpObligatorio()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(ViewAdjuntoReportColumnEnum.TDP_VENCIMIENTO) //
                        .value(dto.getTdpVencimiento()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(ViewAdjuntoReportColumnEnum.TIPO_PROPIETARIO) //
                        .value(dto.getTipoPropietario()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(ViewAdjuntoReportColumnEnum.DOCUMENTO_VENCIDO) //
                        .value(dto.getDocumentoVencido()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(ViewAdjuntoReportColumnEnum.DIAS_HASTA_VENCIMIENTO) //
                        .value(dto.getDiasHastaVencimiento()) //
                        .build());

    }
}
