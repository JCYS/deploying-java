/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/writer/imp/EntityWriterServiceImpl.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.writer.imp;

import com.copeinca.apicopeincaprov.commons.report.excel.SimpleExcelColumn;
import com.copeinca.apicopeincaprov.commons.report.excel.SimpleExcelExportUtilities;
import com.copeinca.apicopeincaprov.global.dtos.AttachmentData;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvAdjuntoDTO;
import com.copeinca.apicopeincaprov.modules.core.models.writer.enums.SvAdjuntoColumnEnum;
import com.copeinca.apicopeincaprov.modules.core.service.writer.ISvAdjuntoWriterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
* Implementación del servicio de escritura para SvAdjuntoEntity
* Actualmente lanza excepciones para métodos no implementados.
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class SvAdjuntoWriterServiceImpl implements ISvAdjuntoWriterService {

    @Override
    public AttachmentData exportToCsv(List<SvAdjuntoDTO> data) {
        log.warn("Método exportToCsv no implementado para SvAdjuntoEntity");
        throw new UnsupportedOperationException("La exportación a CSV no está implementada para SvAdjuntoEntity");
    }

    @Override
    public AttachmentData exportToExcel(List<SvAdjuntoDTO> data) throws Exception {

        log.info("Iniciando exportación a Excel de {} registros de SvAdjuntoDTO", data != null ? data.size() : 0);

        //--- Crear lista de filas para Excel
        List<List<SimpleExcelColumn>> rows = Optional.ofNullable(data).orElse(new ArrayList<>()).stream()//
                .map(this::defineColumns)//
                .collect(Collectors.toList());

        //--- Generar archivo Excel
        AttachmentData result = SimpleExcelExportUtilities.generateExcelFile(rows, "SvAdjuntos");

        log.info("Archivo Excel generado exitosamente: {}", result.getName());

        return result;

    }

    /**
     * Define las columnas para un usuario específico
     */
    @Override
    public List<SimpleExcelColumn> defineColumns(SvAdjuntoDTO dto) {

        return Arrays.asList(

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvAdjuntoColumnEnum.ADJUNTO_ID) //
                        .value(dto.getId()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvAdjuntoColumnEnum.NOMBRE_ARCHIVO) //
                        .value(dto.getNombreArchivo()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvAdjuntoColumnEnum.ID_REPOSITORIO) //
                        .value(dto.getIdRepositorio()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvAdjuntoColumnEnum.CLASIFICATION) //
                        .value(dto.getClasification()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvAdjuntoColumnEnum.FECHA_DOCUMENTO) //
                        .value(dto.getFechaDocumento()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvAdjuntoColumnEnum.FECHA_VENCIMIENTO) //
                        .value(dto.getFechaVencimiento()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvAdjuntoColumnEnum.RUTA_RELATIVA) //
                        .value(dto.getRutaRelativa()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvAdjuntoColumnEnum.SSO_COMENTARIO_REVISION) //
                        .value(dto.getSsoComentarioRevision()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvAdjuntoColumnEnum.CA_COMENTARIO_REVISION) //
                        .value(dto.getCaComentarioRevision()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvAdjuntoColumnEnum.MOTIVO) //
                        .value(dto.getMotivo()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvAdjuntoColumnEnum.PROVEEDOR_ID) //
                        .value(dto.getProveedorId()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvAdjuntoColumnEnum.TRABAJADOR_ID) //
                        .value(dto.getTrabajadorId()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvAdjuntoColumnEnum.TIPO_DOCUMENTO_PLANILLA_CODE) //
                        .value(dto.getTipoDocumentoPlanillaCode()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvAdjuntoColumnEnum.SOLICITUD_VISITA_ID) //
                        .value(dto.getSolicitudVisitaId()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvAdjuntoColumnEnum.TIPO_ACTIVIDAD_ALTO_RIESGO_CODE) //
                        .value(dto.getTipoActividadAltoRiesgoCode()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvAdjuntoColumnEnum.IS_DELETED) //
                        .value(dto.getIsDeleted()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvAdjuntoColumnEnum.VERSION) //
                        .value(dto.getVersion()) //
                        .build());

    }
}
