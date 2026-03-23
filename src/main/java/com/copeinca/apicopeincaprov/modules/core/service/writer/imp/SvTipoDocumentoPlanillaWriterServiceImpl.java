/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/writer/imp/EntityWriterServiceImpl.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.writer.imp;

import com.copeinca.apicopeincaprov.commons.report.excel.SimpleExcelColumn;
import com.copeinca.apicopeincaprov.commons.report.excel.SimpleExcelExportUtilities;
import com.copeinca.apicopeincaprov.global.dtos.AttachmentData;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvTipoDocumentoPlanillaDTO;
import com.copeinca.apicopeincaprov.modules.core.models.writer.enums.SvTipoDocumentoPlanillaColumnEnum;
import com.copeinca.apicopeincaprov.modules.core.service.writer.ISvTipoDocumentoPlanillaWriterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
* Implementación del servicio de escritura para SvTipoDocumentoPlanillaEntity
* Actualmente lanza excepciones para métodos no implementados.
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class SvTipoDocumentoPlanillaWriterServiceImpl implements ISvTipoDocumentoPlanillaWriterService {

    @Override
    public AttachmentData exportToCsv(List<SvTipoDocumentoPlanillaDTO> data) {
        log.warn("Método exportToCsv no implementado para SvTipoDocumentoPlanillaEntity");
        throw new UnsupportedOperationException("La exportación a CSV no está implementada para SvTipoDocumentoPlanillaEntity");
    }

    @Override
    public AttachmentData exportToExcel(List<SvTipoDocumentoPlanillaDTO> data) throws Exception {

        log.info("Iniciando exportación a Excel de {} registros de SvTipoDocumentoPlanillaDTO", data != null ? data.size() : 0);

        //--- Crear lista de filas para Excel
        List<List<SimpleExcelColumn>> rows = Optional.ofNullable(data).orElse(new ArrayList<>()).stream()//
                .map(this::defineColumns)//
                .collect(Collectors.toList());

        //--- Generar archivo Excel
        AttachmentData result = SimpleExcelExportUtilities.generateExcelFile(rows, "SvTipoDocumentoPlanillas");

        log.info("Archivo Excel generado exitosamente: {}", result.getName());

        return result;

    }

    /**
     * Define las columnas para un usuario específico
     */
    @Override
    public List<SimpleExcelColumn> defineColumns(SvTipoDocumentoPlanillaDTO dto) {

        return Arrays.asList(

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvTipoDocumentoPlanillaColumnEnum.TIPO_DOCUMENTO_PLANILLA_CODE) //
                        .value(dto.getId()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvTipoDocumentoPlanillaColumnEnum.DESCRIPTION) //
                        .value(dto.getDescription()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvTipoDocumentoPlanillaColumnEnum.AMBITO) //
                        .value(dto.getAmbito()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvTipoDocumentoPlanillaColumnEnum.INFLUENCIA) //
                        .value(dto.getInfluencia()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvTipoDocumentoPlanillaColumnEnum.DEPENDENCIA) //
                        .value(dto.getDependencia()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvTipoDocumentoPlanillaColumnEnum.CAMPO_PLANTILLA_LF) //
                        .value(dto.getCampoPlantillaLf()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvTipoDocumentoPlanillaColumnEnum.OBLIGATORIO) //
                        .value(dto.getObligatorio()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvTipoDocumentoPlanillaColumnEnum.VENCIMIENTO) //
                        .value(dto.getVencimiento()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvTipoDocumentoPlanillaColumnEnum.IS_DELETED) //
                        .value(dto.getIsDeleted()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvTipoDocumentoPlanillaColumnEnum.VERSION) //
                        .value(dto.getVersion()) //
                        .build());

    }
}
