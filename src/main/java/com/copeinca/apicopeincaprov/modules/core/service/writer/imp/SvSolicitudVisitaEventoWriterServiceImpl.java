/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/writer/imp/EntityWriterServiceImpl.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.writer.imp;

import com.copeinca.apicopeincaprov.commons.report.excel.SimpleExcelColumn;
import com.copeinca.apicopeincaprov.commons.report.excel.SimpleExcelExportUtilities;
import com.copeinca.apicopeincaprov.global.dtos.AttachmentData;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvSolicitudVisitaEventoDTO;
import com.copeinca.apicopeincaprov.modules.core.models.writer.enums.SvSolicitudVisitaEventoColumnEnum;
import com.copeinca.apicopeincaprov.modules.core.service.writer.ISvSolicitudVisitaEventoWriterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
* Implementación del servicio de escritura para SvSolicitudVisitaEventoEntity
* Actualmente lanza excepciones para métodos no implementados.
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class SvSolicitudVisitaEventoWriterServiceImpl implements ISvSolicitudVisitaEventoWriterService {

    @Override
    public AttachmentData exportToCsv(List<SvSolicitudVisitaEventoDTO> data) {
        log.warn("Método exportToCsv no implementado para SvSolicitudVisitaEventoEntity");
        throw new UnsupportedOperationException("La exportación a CSV no está implementada para SvSolicitudVisitaEventoEntity");
    }

    @Override
    public AttachmentData exportToExcel(List<SvSolicitudVisitaEventoDTO> data) throws Exception {

        log.info("Iniciando exportación a Excel de {} registros de SvSolicitudVisitaEventoDTO", data != null ? data.size() : 0);

        //--- Crear lista de filas para Excel
        List<List<SimpleExcelColumn>> rows = Optional.ofNullable(data).orElse(new ArrayList<>()).stream()//
                .map(this::defineColumns)//
                .collect(Collectors.toList());

        //--- Generar archivo Excel
        AttachmentData result = SimpleExcelExportUtilities.generateExcelFile(rows, "SvSolicitudVisitaEventos");

        log.info("Archivo Excel generado exitosamente: {}", result.getName());

        return result;

    }

    /**
     * Define las columnas para un usuario específico
     */
    @Override
    public List<SimpleExcelColumn> defineColumns(SvSolicitudVisitaEventoDTO dto) {

        return Arrays.asList(

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvSolicitudVisitaEventoColumnEnum.SOLICITUD_VISITA_EVENTO_ID) //
                        .value(dto.getId()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvSolicitudVisitaEventoColumnEnum.FECHA_EVENTO) //
                        .value(dto.getFechaEvento()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvSolicitudVisitaEventoColumnEnum.USUARIO) //
                        .value(dto.getUsuario()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvSolicitudVisitaEventoColumnEnum.EVENTO) //
                        .value(dto.getEvento()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvSolicitudVisitaEventoColumnEnum.AMBITO) //
                        .value(dto.getAmbito()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvSolicitudVisitaEventoColumnEnum.REVISION) //
                        .value(dto.getRevision()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvSolicitudVisitaEventoColumnEnum.RESPUESTA_USUARIO) //
                        .value(dto.getRespuestaUsuario()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvSolicitudVisitaEventoColumnEnum.SOLICITUD_VISITA_ID) //
                        .value(dto.getSolicitudVisitaId()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvSolicitudVisitaEventoColumnEnum.IS_DELETED) //
                        .value(dto.getIsDeleted()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvSolicitudVisitaEventoColumnEnum.VERSION) //
                        .value(dto.getVersion()) //
                        .build());

    }
}
