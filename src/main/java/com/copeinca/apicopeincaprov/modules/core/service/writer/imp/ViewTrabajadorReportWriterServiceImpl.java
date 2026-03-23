/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/writer/imp/EntityWriterServiceImpl.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.writer.imp;

import com.copeinca.apicopeincaprov.commons.report.excel.SimpleExcelColumn;
import com.copeinca.apicopeincaprov.commons.report.excel.SimpleExcelExportUtilities;
import com.copeinca.apicopeincaprov.global.dtos.AttachmentData;
import com.copeinca.apicopeincaprov.modules.core.models.dto.ViewTrabajadorReportDTO;
import com.copeinca.apicopeincaprov.modules.core.models.writer.enums.ViewTrabajadorReportColumnEnum;
import com.copeinca.apicopeincaprov.modules.core.service.writer.IViewTrabajadorReportWriterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
* Implementación del servicio de escritura para ViewTrabajadorReportView
* Actualmente lanza excepciones para métodos no implementados.
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class ViewTrabajadorReportWriterServiceImpl implements IViewTrabajadorReportWriterService {

    @Override
    public AttachmentData exportToCsv(List<ViewTrabajadorReportDTO> data) {
        log.warn("Método exportToCsv no implementado para ViewTrabajadorReportView");
        throw new UnsupportedOperationException("La exportación a CSV no está implementada para ViewTrabajadorReportView");
    }

    @Override
    public AttachmentData   exportToExcel(List<ViewTrabajadorReportDTO> data) throws Exception {

        log.info("Iniciando exportación a Excel de {} registros de ViewTrabajadorReportDTO", data != null ? data.size() : 0);

        //--- Crear lista de filas para Excel
        List<List<SimpleExcelColumn>> rows = Optional.ofNullable(data).orElse(new ArrayList<>()).stream()//
                .map(this::defineColumns)//
                .collect(Collectors.toList());

        //--- Generar archivo Excel
        AttachmentData result = SimpleExcelExportUtilities.generateExcelFile(rows, "ViewTrabajadorReports");

        log.info("Archivo Excel generado exitosamente: {}", result.getName());

        return result;

    }

    /**
     * Define las columnas para un usuario específico
     */
    @Override
    public List<SimpleExcelColumn> defineColumns(ViewTrabajadorReportDTO dto) {

        return Arrays.asList(

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(ViewTrabajadorReportColumnEnum.TIPO_DOCUMENTO_IDENTIDAD_CODE) //
                        .value(dto.getTdiNombre()) //
                        .build(),
                SimpleExcelColumn.builder() //
                        .excelColumnProperties(ViewTrabajadorReportColumnEnum.NRO_DOCUMENTO_IDENTIDAD) //
                        .value(dto.getNroDocumentoIdentidad()) //
                        .build(),
                SimpleExcelColumn.builder() //
                        .excelColumnProperties(ViewTrabajadorReportColumnEnum.NOMBRE) //
                        .value(dto.getNombre()) //
                        .build(),
                SimpleExcelColumn.builder() //
                        .excelColumnProperties(ViewTrabajadorReportColumnEnum.TELEFONO) //
                        .value(dto.getTelefono()) //
                        .build(),
                SimpleExcelColumn.builder() //
                        .excelColumnProperties(ViewTrabajadorReportColumnEnum.EMAIL) //
                        .value(dto.getEmail()) //
                        .build(),
                SimpleExcelColumn.builder() //
                        .excelColumnProperties(ViewTrabajadorReportColumnEnum.SSO_ESTADO_NOMBRE) //
                        .value(dto.getSsoEstadoNombre()) //
                        .build(),
                SimpleExcelColumn.builder() //
                        .excelColumnProperties(ViewTrabajadorReportColumnEnum.SSO_MOTIVO) //
                        .value(dto.getSsoMotivo()) //
                        .build(),
                SimpleExcelColumn.builder() //
                        .excelColumnProperties(ViewTrabajadorReportColumnEnum.SSO_FECHA_FIN_VIGENCIA) //
                        .value(dto.getSsoFechaFinVigencia()) //
                        .build(),
                SimpleExcelColumn.builder() //
                        .excelColumnProperties(ViewTrabajadorReportColumnEnum.IND_TRABAJO_ALTO_RIESGO) //
                        .value(dto.getIndTrabajoAltoRiesgo()) //
                        .build(),
                SimpleExcelColumn.builder() //
                        .excelColumnProperties(ViewTrabajadorReportColumnEnum.IND_PREVENCIONISTA) //
                        .value(dto.getIndPrevencionista()) //
                        .build(),
//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewTrabajadorReportColumnEnum.N_DOCS_PREV_ADJUNTOS) //
//                        .value(dto.getNDocsPrevAdjuntos()) //
//                        .build(),
                SimpleExcelColumn.builder() //
                        .excelColumnProperties(ViewTrabajadorReportColumnEnum.N_DOCS_TOTALES) //
                        .value(dto.getNDocsTotales()) //
                        .build(),
                SimpleExcelColumn.builder() //
                        .excelColumnProperties(ViewTrabajadorReportColumnEnum.N_DOCS_TOTALES_VIGENTES) //
                        .value(dto.getNDocsTotalesValid()) //
                        .build()
//
//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewTrabajadorReportColumnEnum.PROVEEDOR_ID) //
//                        .value(dto.getProveedorId()) //
//                        .build(),
//
//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewTrabajadorReportColumnEnum.PROV_NRO_DOCUMENTO_IDENTIDAD) //
//                        .value(dto.getProvNroDocumentoIdentidad()) //
//                        .build(),
//
//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewTrabajadorReportColumnEnum.PROV_NOMBRE_FISCAL) //
//                        .value(dto.getProvNombreFiscal()) //
//                        .build(),
//
//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewTrabajadorReportColumnEnum.TIPO_DOCUMENTO_IDENTIDAD_CODE) //
//                        .value(dto.getTipoDocumentoIdentidadCode()) //
//                        .build(),
//
//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewTrabajadorReportColumnEnum.NRO_DOCUMENTO_IDENTIDAD) //
//                        .value(dto.getNroDocumentoIdentidad()) //
//                        .build(),
//
//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewTrabajadorReportColumnEnum.TDI_NOMBRE) //
//                        .value(dto.getTdiNombre()) //
//                        .build(),
//
//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewTrabajadorReportColumnEnum.NOMBRE) //
//                        .value(dto.getNombre()) //
//                        .build(),
//
//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewTrabajadorReportColumnEnum.TELEFONO) //
//                        .value(dto.getTelefono()) //
//                        .build(),
//
//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewTrabajadorReportColumnEnum.EMAIL) //
//                        .value(dto.getEmail()) //
//                        .build(),
//
//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewTrabajadorReportColumnEnum.ESTADO_SSO_CODE) //
//                        .value(dto.getEstadoSsoCode()) //
//                        .build(),
//
//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewTrabajadorReportColumnEnum.SSO_MOTIVO) //
//                        .value(dto.getSsoMotivo()) //
//                        .build(),
//
//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewTrabajadorReportColumnEnum.SO_TIENE_VIGENCIA) //
//                        .value(dto.getSoTieneVigencia()) //
//                        .build(),
//
//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewTrabajadorReportColumnEnum.SSO_FECHA_INICIO_VIGENCIA) //
//                        .value(dto.getSsoFechaInicioVigencia()) //
//                        .build(),
//
//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewTrabajadorReportColumnEnum.SSO_FECHA_FIN_VIGENCIA) //
//                        .value(dto.getSsoFechaFinVigencia()) //
//                        .build(),
//
//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewTrabajadorReportColumnEnum.IS_DELETED) //
//                        .value(dto.getIsDeleted()) //
//                        .build(),
//
//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewTrabajadorReportColumnEnum.IS_ACTIVE) //
//                        .value(dto.getIsActive()) //
//                        .build(),
//
//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewTrabajadorReportColumnEnum.SSO_ESTADO_NOMBRE) //
//                        .value(dto.getSsoEstadoNombre()) //
//                        .build(),
//
//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewTrabajadorReportColumnEnum.SSO_ESTADO_VIGENTE) //
//                        .value(dto.getSsoEstadoVigente()) //
//                        .build(),
//
//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewTrabajadorReportColumnEnum.IND_TRABAJO_ALTO_RIESGO) //
//                        .value(dto.getIndTrabajoAltoRiesgo()) //
//                        .build(),
//
//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewTrabajadorReportColumnEnum.IND_PREVENCIONISTA) //
//                        .value(dto.getIndPrevencionista()) //
//                        .build(),
//
//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewTrabajadorReportColumnEnum.TAR_CODIGOS) //
//                        .value(dto.getTarCodigos()) //
//                        .build(),
//
//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewTrabajadorReportColumnEnum.TAR_DESCRIPCIONES) //
//                        .value(dto.getTarDescripciones()) //
//                        .build(),
//
//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewTrabajadorReportColumnEnum.N_DOCS_SSO_ADJUNTOS) //
//                        .value(dto.getNDocsSsoAdjuntos()) //
//                        .build(),
//
//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewTrabajadorReportColumnEnum.N_DOCS_CALIDAD_ADJUNTOS) //
//                        .value(dto.getNDocsCalidadAdjuntos()) //
//                        .build(),
//
//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewTrabajadorReportColumnEnum.N_DOCS_AAR_ADJUNTOS) //
//                        .value(dto.getNDocsAarAdjuntos()) //
//                        .build(),
//
//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewTrabajadorReportColumnEnum.N_DOCS_AB_ADJUNTOS) //
//                        .value(dto.getNDocsAbAdjuntos()) //
//                        .build(),
//
//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewTrabajadorReportColumnEnum.N_DOCS_PREV_ADJUNTOS) //
//                        .value(dto.getNDocsPrevAdjuntos()) //
//                        .build()
        );
    }
}
