/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/writer/imp/EntityWriterServiceImpl.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.writer.imp;

import com.copeinca.apicopeincaprov.commons.report.excel.SimpleExcelColumn;
import com.copeinca.apicopeincaprov.commons.report.excel.SimpleExcelExportUtilities;
import com.copeinca.apicopeincaprov.global.dtos.AttachmentData;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvSolicitudVisitaEquipoDTO;
import com.copeinca.apicopeincaprov.modules.core.models.writer.enums.SvSolicitudVisitaEquipoColumnEnum;
import com.copeinca.apicopeincaprov.modules.core.service.writer.ISvSolicitudVisitaEquipoWriterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
* Implementación del servicio de escritura para SvSolicitudVisitaEquipoEntity
* Actualmente lanza excepciones para métodos no implementados.
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class SvSolicitudVisitaEquipoWriterServiceImpl implements ISvSolicitudVisitaEquipoWriterService {

    @Override
    public AttachmentData exportToCsv(List<SvSolicitudVisitaEquipoDTO> data) {
        log.warn("Método exportToCsv no implementado para SvSolicitudVisitaEquipoEntity");
        throw new UnsupportedOperationException("La exportación a CSV no está implementada para SvSolicitudVisitaEquipoEntity");
    }

    @Override
    public AttachmentData exportToExcel(List<SvSolicitudVisitaEquipoDTO> data) throws Exception {

        log.info("Iniciando exportación a Excel de {} registros de SvSolicitudVisitaEquipoDTO", data != null ? data.size() : 0);

        //--- Crear lista de filas para Excel
        List<List<SimpleExcelColumn>> rows = Optional.ofNullable(data).orElse(new ArrayList<>()).stream()//
                .map(this::defineColumns)//
                .collect(Collectors.toList());

        //--- Generar archivo Excel
        AttachmentData result = SimpleExcelExportUtilities.generateExcelFile(rows, "SvSolicitudVisitaEquipos");

        log.info("Archivo Excel generado exitosamente: {}", result.getName());

        return result;

    }

    /**
     * Define las columnas para un usuario específico
     */
    @Override
    public List<SimpleExcelColumn> defineColumns(SvSolicitudVisitaEquipoDTO dto) {

        return Arrays.asList(

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvSolicitudVisitaEquipoColumnEnum.SOLICITUD_VISITA_EQUIPO_ID) //
                        .value(dto.getId()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvSolicitudVisitaEquipoColumnEnum.DESCRIPCION) //
                        .value(dto.getDescripcion()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvSolicitudVisitaEquipoColumnEnum.MARCA) //
                        .value(dto.getMarca()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvSolicitudVisitaEquipoColumnEnum.MODELO) //
                        .value(dto.getModelo()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvSolicitudVisitaEquipoColumnEnum.NUMERO_SERIE) //
                        .value(dto.getNumeroSerie()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvSolicitudVisitaEquipoColumnEnum.TIPO_EQUIPO) //
                        .value(dto.getTipoEquipo()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvSolicitudVisitaEquipoColumnEnum.CODIGO_EQUIPO) //
                        .value(dto.getCodigoEquipo()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvSolicitudVisitaEquipoColumnEnum.CANTIDAD) //
                        .value(dto.getCantidad()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvSolicitudVisitaEquipoColumnEnum.SOLICITUD_VISITA_ID) //
                        .value(dto.getSolicitudVisitaId()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvSolicitudVisitaEquipoColumnEnum.IS_DELETED) //
                        .value(dto.getIsDeleted()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvSolicitudVisitaEquipoColumnEnum.VERSION) //
                        .value(dto.getVersion()) //
                        .build());

    }
}
