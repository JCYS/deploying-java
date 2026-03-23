/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/writer/imp/EntityWriterServiceImpl.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.writer.imp;

import com.copeinca.apicopeincaprov.commons.report.excel.SimpleExcelColumn;
import com.copeinca.apicopeincaprov.commons.report.excel.SimpleExcelExportUtilities;
import com.copeinca.apicopeincaprov.global.dtos.AttachmentData;
import com.copeinca.apicopeincaprov.modules.core.models.dto.ViewPersonaRestringidaReportDTO;
import com.copeinca.apicopeincaprov.modules.core.models.writer.enums.ViewPersonaRestringidaReportColumnEnum;
import com.copeinca.apicopeincaprov.modules.core.service.writer.IViewPersonaRestringidaReportWriterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
* Implementación del servicio de escritura para ViewPersonaRestringidaReportView
* Actualmente lanza excepciones para métodos no implementados.
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class ViewPersonaRestringidaReportWriterServiceImpl implements IViewPersonaRestringidaReportWriterService {

    @Override
    public AttachmentData exportToCsv(List<ViewPersonaRestringidaReportDTO> data) {
        log.warn("Método exportToCsv no implementado para ViewPersonaRestringidaReportView");
        throw new UnsupportedOperationException("La exportación a CSV no está implementada para ViewPersonaRestringidaReportView");
    }

    @Override
    public AttachmentData exportToExcel(List<ViewPersonaRestringidaReportDTO> data) throws Exception {

        log.info("Iniciando exportación a Excel de {} registros de ViewPersonaRestringidaReportDTO", data != null ? data.size() : 0);

        //--- Crear lista de filas para Excel
        List<List<SimpleExcelColumn>> rows = Optional.ofNullable(data).orElse(new ArrayList<>()).stream()//
                .map(this::defineColumns)//
                .collect(Collectors.toList());

        //--- Generar archivo Excel
        AttachmentData result = SimpleExcelExportUtilities.generateExcelFile(rows, "ViewPersonaRestringidaReports");

        log.info("Archivo Excel generado exitosamente: {}", result.getName());

        return result;

    }

    /**
     * Define las columnas para un usuario específico
     */
    @Override
    public List<SimpleExcelColumn> defineColumns(ViewPersonaRestringidaReportDTO dto) {

        return Arrays.asList(

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(ViewPersonaRestringidaReportColumnEnum.PERSONA_RESTRINGIDA_ID) //
                        .value(dto.getId()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(ViewPersonaRestringidaReportColumnEnum.NUMERO_DOCUMENTO) //
                        .value(dto.getNumeroDocumento()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(ViewPersonaRestringidaReportColumnEnum.LICENCIA_CONDUCIR) //
                        .value(dto.getLicenciaConducir()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(ViewPersonaRestringidaReportColumnEnum.NOMBRE_COMPLETO) //
                        .value(dto.getNombreCompleto()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(ViewPersonaRestringidaReportColumnEnum.USUARIO_REPORTA) //
                        .value(dto.getUsuarioReporta()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(ViewPersonaRestringidaReportColumnEnum.RESTRINGIDO_EL) //
                        .value(dto.getRestringidoEl()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(ViewPersonaRestringidaReportColumnEnum.RESTRINGIDO_HASTA) //
                        .value(dto.getRestringidoHasta()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(ViewPersonaRestringidaReportColumnEnum.OBSERVACIONES) //
                        .value(dto.getObservaciones()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(ViewPersonaRestringidaReportColumnEnum.TIPO_DOCUMENTO_IDENTIDAD_CODE) //
                        .value(dto.getTipoDocumentoIdentidadCode()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(ViewPersonaRestringidaReportColumnEnum.SEDE_ORIGINADORA_ID) //
                        .value(dto.getSedeOriginadoraId()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(ViewPersonaRestringidaReportColumnEnum.TDI_NOMBRE) //
                        .value(dto.getTdiNombre()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(ViewPersonaRestringidaReportColumnEnum.SEDE_NOMBRE) //
                        .value(dto.getSedeNombre()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(ViewPersonaRestringidaReportColumnEnum.SEDE_AMBITO) //
                        .value(dto.getSedeAmbito()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(ViewPersonaRestringidaReportColumnEnum.RESTRICCION_ACTIVA) //
                        .value(dto.getRestriccionActiva()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(ViewPersonaRestringidaReportColumnEnum.DIAS_RESTRICCION) //
                        .value(dto.getDiasRestriccion()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(ViewPersonaRestringidaReportColumnEnum.TIPO_RESTRICCION) //
                        .value(dto.getTipoRestriccion()) //
                        .build());

    }
}
