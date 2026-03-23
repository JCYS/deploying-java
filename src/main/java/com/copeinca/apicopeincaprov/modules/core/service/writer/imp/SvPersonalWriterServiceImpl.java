/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/writer/imp/EntityWriterServiceImpl.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.writer.imp;

import com.copeinca.apicopeincaprov.commons.report.excel.SimpleExcelColumn;
import com.copeinca.apicopeincaprov.commons.report.excel.SimpleExcelExportUtilities;
import com.copeinca.apicopeincaprov.global.dtos.AttachmentData;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvPersonalDTO;
import com.copeinca.apicopeincaprov.modules.core.models.writer.enums.SvPersonalColumnEnum;
import com.copeinca.apicopeincaprov.modules.core.service.writer.ISvPersonalWriterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
* Implementación del servicio de escritura para SvPersonalEntity
* Actualmente lanza excepciones para métodos no implementados.
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class SvPersonalWriterServiceImpl implements ISvPersonalWriterService {

    @Override
    public AttachmentData exportToCsv(List<SvPersonalDTO> data) {
        log.warn("Método exportToCsv no implementado para SvPersonalEntity");
        throw new UnsupportedOperationException("La exportación a CSV no está implementada para SvPersonalEntity");
    }

    @Override
    public AttachmentData exportToExcel(List<SvPersonalDTO> data) throws Exception {

        log.info("Iniciando exportación a Excel de {} registros de SvPersonalDTO", data != null ? data.size() : 0);

        //--- Crear lista de filas para Excel
        List<List<SimpleExcelColumn>> rows = Optional.ofNullable(data).orElse(new ArrayList<>()).stream()//
                .map(this::defineColumns)//
                .collect(Collectors.toList());

        //--- Generar archivo Excel
        AttachmentData result = SimpleExcelExportUtilities.generateExcelFile(rows, "SvPersonals");

        log.info("Archivo Excel generado exitosamente: {}", result.getName());

        return result;

    }

    /**
     * Define las columnas para un usuario específico
     */
    @Override
    public List<SimpleExcelColumn> defineColumns(SvPersonalDTO dto) {

        return Arrays.asList(

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvPersonalColumnEnum.PERSONAL_ID) //
                        .value(dto.getId()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvPersonalColumnEnum.USUARIO) //
                        .value(dto.getUsuario()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvPersonalColumnEnum.NOMBRE_USUARIO) //
                        .value(dto.getNombreUsuario()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvPersonalColumnEnum.CODIGO) //
                        .value(dto.getCodigo()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvPersonalColumnEnum.DESCRIPCION) //
                        .value(dto.getDescripcion()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvPersonalColumnEnum.AMBITO) //
                        .value(dto.getAmbito()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvPersonalColumnEnum.INFLUENCIA) //
                        .value(dto.getInfluencia()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvPersonalColumnEnum.DEPENDENCIA) //
                        .value(dto.getDependencia()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvPersonalColumnEnum.CAMPO_PLANTILLA_LF) //
                        .value(dto.getCampoPlantillaLf()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvPersonalColumnEnum.OBLIGATORIO) //
                        .value(dto.getObligatorio()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvPersonalColumnEnum.VENCIMIENTO) //
                        .value(dto.getVencimiento()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvPersonalColumnEnum.NUMERO_CASO) //
                        .value(dto.getNumeroCaso()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvPersonalColumnEnum.MENSAJE) //
                        .value(dto.getMensaje()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvPersonalColumnEnum.TIPO_DOCUMENTO_IDENTIDAD_CODE) //
                        .value(dto.getTipoDocumentoIdentidadCode()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvPersonalColumnEnum.IS_DELETED) //
                        .value(dto.getIsDeleted()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvPersonalColumnEnum.VERSION) //
                        .value(dto.getVersion()) //
                        .build());

    }
}
