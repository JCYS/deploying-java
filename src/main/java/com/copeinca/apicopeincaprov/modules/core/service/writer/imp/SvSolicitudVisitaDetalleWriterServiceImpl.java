/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/writer/imp/EntityWriterServiceImpl.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.writer.imp;

import com.copeinca.apicopeincaprov.commons.report.excel.SimpleExcelColumn;
import com.copeinca.apicopeincaprov.commons.report.excel.SimpleExcelExportUtilities;
import com.copeinca.apicopeincaprov.global.dtos.AttachmentData;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvSolicitudVisitaDetalleDTO;
import com.copeinca.apicopeincaprov.modules.core.models.writer.enums.SvSolicitudVisitaDetalleColumnEnum;
import com.copeinca.apicopeincaprov.modules.core.service.writer.ISvSolicitudVisitaDetalleWriterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
* Implementación del servicio de escritura para SvSolicitudVisitaDetalleEntity
* Actualmente lanza excepciones para métodos no implementados.
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class SvSolicitudVisitaDetalleWriterServiceImpl implements ISvSolicitudVisitaDetalleWriterService {

    @Override
    public AttachmentData exportToCsv(List<SvSolicitudVisitaDetalleDTO> data) {
        log.warn("Método exportToCsv no implementado para SvSolicitudVisitaDetalleEntity");
        throw new UnsupportedOperationException("La exportación a CSV no está implementada para SvSolicitudVisitaDetalleEntity");
    }

    @Override
    public AttachmentData exportToExcel(List<SvSolicitudVisitaDetalleDTO> data) throws Exception {

        log.info("Iniciando exportación a Excel de {} registros de SvSolicitudVisitaDetalleDTO", data != null ? data.size() : 0);

        //--- Crear lista de filas para Excel
        List<List<SimpleExcelColumn>> rows = Optional.ofNullable(data).orElse(new ArrayList<>()).stream()//
                .map(this::defineColumns)//
                .collect(Collectors.toList());

        //--- Generar archivo Excel
        AttachmentData result = SimpleExcelExportUtilities.generateExcelFile(rows, "SvSolicitudVisitaDetalles");

        log.info("Archivo Excel generado exitosamente: {}", result.getName());

        return result;

    }

    /**
     * Define las columnas para un usuario específico
     */
    @Override
    public List<SimpleExcelColumn> defineColumns(SvSolicitudVisitaDetalleDTO dto) {

        return Arrays.asList(

//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(SvSolicitudVisitaDetalleColumnEnum.SOLICITUD_VISITA_DETALLE_ID) //
//                        .value(dto.getId()) //
//                        .build(),
                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvSolicitudVisitaDetalleColumnEnum.TIPO_DOCUMENTO) //
                        .value(dto.getTipoDocumento()) //
                        .build(),
                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvSolicitudVisitaDetalleColumnEnum.NUM_DOCUMENTO) //
                        .value(dto.getNumDocumento()) //
                        .build(),
                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvSolicitudVisitaDetalleColumnEnum.NOMBRE) //
                        .value(dto.getNombre()) //
                        .build(),
                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvSolicitudVisitaDetalleColumnEnum.ESTADO_SSO) //
                        .value(dto.getEstadoSSODesc()) //
                        .build(),
                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvSolicitudVisitaDetalleColumnEnum.NUM_VISITA) //
                        .value(dto.getCodVisita()) //
                        .build(),
                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvSolicitudVisitaDetalleColumnEnum.PROVEEDOR_COMENTARIO) //
                        .value(dto.getProveedorComentario()) //
                        .build(),
                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvSolicitudVisitaDetalleColumnEnum.SSO_FECHA_FIN_VIGENCIA) //
                        .value(dto.getSsoFechaFinVigencia()) //
                        .build());

//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(SvSolicitudVisitaDetalleColumnEnum.SOLICITUD_VISITA_ID) //
//                        .value(dto.getSolicitudVisitaId()) //
//                        .build(),

//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(SvSolicitudVisitaDetalleColumnEnum.TRABAJADOR_ID) //
//                        .value(dto.getTrabajadorId()) //
//                        .build(),

//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(SvSolicitudVisitaDetalleColumnEnum.IS_DELETED) //
//                        .value(dto.getIsDeleted()) //
//                        .build(),
//
//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(SvSolicitudVisitaDetalleColumnEnum.VERSION) //
//                        .value(dto.getVersion()) //
//                        .build(),
//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(SvSolicitudVisitaDetalleColumnEnum.TIPO_DOCUMENTO) //
//                        .value(dto.getTipoDocumento()) //
//                        .build());

    }
}
