/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/writer/imp/EntityWriterServiceImpl.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.writer.imp;

import com.copeinca.apicopeincaprov.commons.report.excel.SimpleExcelColumn;
import com.copeinca.apicopeincaprov.commons.report.excel.SimpleExcelExportUtilities;
import com.copeinca.apicopeincaprov.global.dtos.AttachmentData;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvTrabajadorTipoActividadAltoRiesgoDTO;
import com.copeinca.apicopeincaprov.modules.core.models.writer.enums.SvTrabajadorTipoActividadAltoRiesgoColumnEnum;
import com.copeinca.apicopeincaprov.modules.core.service.writer.ISvTrabajadorTipoActividadAltoRiesgoWriterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
* Implementación del servicio de escritura para SvTrabajadorTipoActividadAltoRiesgoEntity
* Actualmente lanza excepciones para métodos no implementados.
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class SvTrabajadorTipoActividadAltoRiesgoWriterServiceImpl implements ISvTrabajadorTipoActividadAltoRiesgoWriterService {

    @Override
    public AttachmentData exportToCsv(List<SvTrabajadorTipoActividadAltoRiesgoDTO> data) {
        log.warn("Método exportToCsv no implementado para SvTrabajadorTipoActividadAltoRiesgoEntity");
        throw new UnsupportedOperationException("La exportación a CSV no está implementada para SvTrabajadorTipoActividadAltoRiesgoEntity");
    }

    @Override
    public AttachmentData exportToExcel(List<SvTrabajadorTipoActividadAltoRiesgoDTO> data) throws Exception {

        log.info("Iniciando exportación a Excel de {} registros de SvTrabajadorTipoActividadAltoRiesgoDTO", data != null ? data.size() : 0);

        //--- Crear lista de filas para Excel
        List<List<SimpleExcelColumn>> rows = Optional.ofNullable(data).orElse(new ArrayList<>()).stream()//
                .map(this::defineColumns)//
                .collect(Collectors.toList());

        //--- Generar archivo Excel
        AttachmentData result = SimpleExcelExportUtilities.generateExcelFile(rows, "SvTrabajadorTipoActividadAltoRiesgos");

        log.info("Archivo Excel generado exitosamente: {}", result.getName());

        return result;

    }

    /**
     * Define las columnas para un usuario específico
     */
    @Override
    public List<SimpleExcelColumn> defineColumns(SvTrabajadorTipoActividadAltoRiesgoDTO dto) {

        return Arrays.asList(

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvTrabajadorTipoActividadAltoRiesgoColumnEnum.TRABAJADOR_TIPO_ACTIVIDAD_ALTO_RIESGO_ID) //
                        .value(dto.getId()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvTrabajadorTipoActividadAltoRiesgoColumnEnum.TRABAJADOR_ID) //
                        .value(dto.getTrabajadorId()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvTrabajadorTipoActividadAltoRiesgoColumnEnum.TIPO_ACTIVIDAD_ALTO_RIESGO_CODE) //
                        .value(dto.getTipoActividadAltoRiesgoCode()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvTrabajadorTipoActividadAltoRiesgoColumnEnum.IS_ACTIVE) //
                        .value(dto.getIsActive()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvTrabajadorTipoActividadAltoRiesgoColumnEnum.IS_DELETED) //
                        .value(dto.getIsDeleted()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvTrabajadorTipoActividadAltoRiesgoColumnEnum.VERSION) //
                        .value(dto.getVersion()) //
                        .build());

    }
}
