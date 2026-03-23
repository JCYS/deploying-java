/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/writer/imp/EntityWriterServiceImpl.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.writer.imp;

import com.copeinca.apicopeincaprov.commons.report.excel.SimpleExcelColumn;
import com.copeinca.apicopeincaprov.commons.report.excel.SimpleExcelExportUtilities;
import com.copeinca.apicopeincaprov.global.dtos.AttachmentData;
import com.copeinca.apicopeincaprov.modules.core.models.dto.ViewSolicitudVisitaReportDTO;
import com.copeinca.apicopeincaprov.modules.core.models.writer.enums.ViewSolicitudVisitaReportColumnEnum;
import com.copeinca.apicopeincaprov.modules.core.service.writer.IViewSolicitudVisitaReportWriterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
* Implementación del servicio de escritura para ViewSolicitudVisitaReportView
* Actualmente lanza excepciones para métodos no implementados.
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class ViewSolicitudVisitaReportWriterServiceImpl implements IViewSolicitudVisitaReportWriterService {

    @Override
    public AttachmentData exportToCsv(List<ViewSolicitudVisitaReportDTO> data) {
        log.warn("Método exportToCsv no implementado para ViewSolicitudVisitaReportView");
        throw new UnsupportedOperationException("La exportación a CSV no está implementada para ViewSolicitudVisitaReportView");
    }

    @Override
    public AttachmentData exportToExcel(List<ViewSolicitudVisitaReportDTO> data) throws Exception {

        log.info("Iniciando exportación a Excel de {} registros de ViewSolicitudVisitaReportDTO", data != null ? data.size() : 0);

        //--- Crear lista de filas para Excel
        List<List<SimpleExcelColumn>> rows = Optional.ofNullable(data).orElse(new ArrayList<>()).stream()//
                .map(this::defineColumns)//
                .collect(Collectors.toList());

        //--- Generar archivo Excel
        AttachmentData result = SimpleExcelExportUtilities.generateExcelFile(rows, "ViewSolicitudVisitaReports");

        log.info("Archivo Excel generado exitosamente: {}", result.getName());

        return result;

    }

    /**
     * Define las columnas para un usuario específico
     */
    @Override
    public List<SimpleExcelColumn> defineColumns(ViewSolicitudVisitaReportDTO dto) {

        return Arrays.asList(

//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewSolicitudVisitaReportColumnEnum.SOLICITUD_VISITA_ID) //
//                        .value(dto.getId()) //
//                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(ViewSolicitudVisitaReportColumnEnum.CODIGO_VISITA) //
                        .value(dto.getCodigoVisita()) //
                        .build(),
                SimpleExcelColumn.builder() //
                        .excelColumnProperties(ViewSolicitudVisitaReportColumnEnum.CODIGO_SISTEMA_EXTERNO) //
                        .value(dto.getCodigoSistemaExterno()) //
                        .build(),
                SimpleExcelColumn.builder() //
                        .excelColumnProperties(ViewSolicitudVisitaReportColumnEnum.PROVEEDOR_NRO_DOCUMENTO_IDENTIDAD) //
                        .value(dto.getProveedorNroDocumentoIdentidad()) //
                        .build(),
                SimpleExcelColumn.builder() //
                        .excelColumnProperties(ViewSolicitudVisitaReportColumnEnum.PROVEEDOR_NOMBRE_FISCAL) //
                        .value(dto.getProveedorNombreFiscal()) //
                        .build(),
                SimpleExcelColumn.builder() //
                        .excelColumnProperties(ViewSolicitudVisitaReportColumnEnum.SEDE_NOMBRE) //
                        .value(dto.getSedeNombre()) //
                        .build(),
                SimpleExcelColumn.builder() //
                        .excelColumnProperties(ViewSolicitudVisitaReportColumnEnum.FECHA_INICIO) //
                        .value(dto.getFechaInicio()) //
                        .build(),
                SimpleExcelColumn.builder() //
                        .excelColumnProperties(ViewSolicitudVisitaReportColumnEnum.FECHA_FIN) //
                        .value(dto.getFechaFin()) //
                        .build(),
                SimpleExcelColumn.builder() //
                        .excelColumnProperties(ViewSolicitudVisitaReportColumnEnum.PERSONAL_NOMBRE_USUARIO) //
                        .value(dto.getPersonalNombreUsuario()) //
                        .build(),
                SimpleExcelColumn.builder() //
                        .excelColumnProperties(ViewSolicitudVisitaReportColumnEnum.SSO_ESTADO_NOMBRE) //
                        .value(dto.getSsoEstadoNombre()) //
                        .build(),
                SimpleExcelColumn.builder() //
                        .excelColumnProperties(ViewSolicitudVisitaReportColumnEnum.CA_ESTADO_NOMBRE) //
                        .value(dto.getCaEstadoNombre()) //
                        .build(),
                SimpleExcelColumn.builder() //
                        .excelColumnProperties(ViewSolicitudVisitaReportColumnEnum.N_DIAS) //
                        .value(dto.getNDias()) //
                        .build(),
                SimpleExcelColumn.builder() //
                        .excelColumnProperties(ViewSolicitudVisitaReportColumnEnum.N_TRABAJADORES) //
                        .value(dto.getNTrabajadores()) //
                        .build(),
                SimpleExcelColumn.builder() //
                        .excelColumnProperties(ViewSolicitudVisitaReportColumnEnum.ESV_NOMBRE) //
                        .value(dto.getEsvNombre()) //
                        .build()


//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewSolicitudVisitaReportColumnEnum.CODIGO_SISTEMA_EXTERNO) //
//                        .value(dto.getCodigoSistemaExterno()) //
//                        .build(),
//
//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewSolicitudVisitaReportColumnEnum.FECHA_INICIO) //
//                        .value(dto.getFechaInicio()) //
//                        .build(),
//
//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewSolicitudVisitaReportColumnEnum.FECHA_FIN) //
//                        .value(dto.getFechaFin()) //
//                        .build(),
//
//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewSolicitudVisitaReportColumnEnum.MOTIVO_VISITA) //
//                        .value(dto.getMotivoVisita()) //
//                        .build(),
//
//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewSolicitudVisitaReportColumnEnum.FECHA_APROBACION_PERSONA_VISITADA) //
//                        .value(dto.getFechaAprobacionPersonaVisitada()) //
//                        .build(),
//
//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewSolicitudVisitaReportColumnEnum.FECHA_OBSERVACION_ADMINISTRADOR) //
//                        .value(dto.getFechaObservacionAdministrador()) //
//                        .build(),
//
//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewSolicitudVisitaReportColumnEnum.OBSERVACIONES) //
//                        .value(dto.getObservaciones()) //
//                        .build(),
//
//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewSolicitudVisitaReportColumnEnum.NRO_ORDEN_SERVICIO) //
//                        .value(dto.getNroOrdenServicio()) //
//                        .build()

//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewSolicitudVisitaReportColumnEnum.IND_ACTIVIDAD_ALTO_RIESGO) //
//                        .value(dto.getIndActividadAltoRiesgo()) //
//                        .build(),
//
//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewSolicitudVisitaReportColumnEnum.IND_ACTIVIDAD_A_BORDO) //
//                        .value(dto.getIndActividadABordo()) //
//                        .build(),
//
//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewSolicitudVisitaReportColumnEnum.IND_REQUIERE_ANDAMIOS) //
//                        .value(dto.getIndRequiereAndamios()) //
//                        .build(),
//
//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewSolicitudVisitaReportColumnEnum.IND_REQUIERE_GRUA) //
//                        .value(dto.getIndRequiereGrua()) //
//                        .build(),

//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewSolicitudVisitaReportColumnEnum.IND_REQUIERE_EQUIPOS_MOVILES) //
//                        .value(dto.getIndRequiereEquiposMoviles()) //
//                        .build(),
//
//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewSolicitudVisitaReportColumnEnum.IND_TRABAJO_BUCEO) //
//                        .value(dto.getIndTrabajoBuceo()) //
//                        .build(),

//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewSolicitudVisitaReportColumnEnum.SSO_MOTIVO) //
//                        .value(dto.getSsoMotivo()) //
//                        .build(),
//
//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewSolicitudVisitaReportColumnEnum.SSO_DESCARGO) //
//                        .value(dto.getSsoDescargo()) //
//                        .build(),
//
//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewSolicitudVisitaReportColumnEnum.CA_MOTIVO) //
//                        .value(dto.getCaMotivo()) //
//                        .build(),
//
//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewSolicitudVisitaReportColumnEnum.CA_DESCARGO) //
//                        .value(dto.getCaDescargo()) //
//                        .build(),
//
//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewSolicitudVisitaReportColumnEnum.ESTADO_SSO_CODE) //
//                        .value(dto.getEstadoSsoCode()) //
//                        .build(),
//
//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewSolicitudVisitaReportColumnEnum.ESTADO_CALIDAD_AMBIENTAL_CODE) //
//                        .value(dto.getEstadoCalidadAmbientalCode()) //
//                        .build(),
//
//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewSolicitudVisitaReportColumnEnum.ESTADO_SOLICITUD_VISITA_CODE) //
//                        .value(dto.getEstadoSolicitudVisitaCode()) //
//                        .build(),
//
//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewSolicitudVisitaReportColumnEnum.SEDE_ID) //
//                        .value(dto.getSedeId()) //
//                        .build(),
//
//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewSolicitudVisitaReportColumnEnum.PROVEEDOR_ID) //
//                        .value(dto.getProveedorId()) //
//                        .build(),
//
//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewSolicitudVisitaReportColumnEnum.PERSONAL_ID) //
//                        .value(dto.getPersonalId()) //
//                        .build(),
//
//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewSolicitudVisitaReportColumnEnum.SEDE_NOMBRE) //
//                        .value(dto.getSedeNombre()) //
//                        .build(),
//
//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewSolicitudVisitaReportColumnEnum.SEDE_AMBITO) //
//                        .value(dto.getSedeAmbito()) //
//                        .build(),
//
//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewSolicitudVisitaReportColumnEnum.PERSONAL_NOMBRE_USUARIO) //
//                        .value(dto.getPersonalNombreUsuario()) //
//                        .build(),
//
//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewSolicitudVisitaReportColumnEnum.PERSONAL_USUARIO) //
//                        .value(dto.getPersonalUsuario()) //
//                        .build(),
//
//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewSolicitudVisitaReportColumnEnum.PROVEEDOR_NOMBRE_FISCAL) //
//                        .value(dto.getProveedorNombreFiscal()) //
//                        .build(),
//
//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewSolicitudVisitaReportColumnEnum.PROVEEDOR_NRO_DOCUMENTO_IDENTIDAD) //
//                        .value(dto.getProveedorNroDocumentoIdentidad()) //
//                        .build(),
//
//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewSolicitudVisitaReportColumnEnum.PROVEEDOR_EMAIL) //
//                        .value(dto.getProveedorEmail()) //
//                        .build(),
//
//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewSolicitudVisitaReportColumnEnum.PROVEEDOR_CONTACTO_PRINCIPAL) //
//                        .value(dto.getProveedorContactoPrincipal()) //
//                        .build(),
//
//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewSolicitudVisitaReportColumnEnum.ESV_NOMBRE) //
//                        .value(dto.getEsvNombre()) //
//                        .build(),
//
//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewSolicitudVisitaReportColumnEnum.SSO_ESTADO_NOMBRE) //
//                        .value(dto.getSsoEstadoNombre()) //
//                        .build(),
//
//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewSolicitudVisitaReportColumnEnum.SSO_ESTADO_DESCRIPCION) //
//                        .value(dto.getSsoEstadoDescripcion()) //
//                        .build(),
//
//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewSolicitudVisitaReportColumnEnum.CA_ESTADO_NOMBRE) //
//                        .value(dto.getCaEstadoNombre()) //
//                        .build(),
//
//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewSolicitudVisitaReportColumnEnum.CA_ESTADO_DESCRIPCION) //
//                        .value(dto.getCaEstadoDescripcion()) //
//                        .build(),
//
//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewSolicitudVisitaReportColumnEnum.TAR_DESCRIPCIONES) //
//                        .value(dto.getTarDescripciones()) //
//                        .build(),
//
//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewSolicitudVisitaReportColumnEnum.N_DIAS) //
//                        .value(dto.getNDias()) //
//                        .build(),
//
//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewSolicitudVisitaReportColumnEnum.N_TRABAJADORES) //
//                        .value(dto.getNTrabajadores()) //
//                        .build(),
//
//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewSolicitudVisitaReportColumnEnum.AREAS_IDS) //
//                        .value(dto.getAreasIds()) //
//                        .build(),
//
//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewSolicitudVisitaReportColumnEnum.AREAS_NOMBRES) //
//                        .value(dto.getAreasNombres()) //
//                        .build(),
//
//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewSolicitudVisitaReportColumnEnum.ZONAS_IDS) //
//                        .value(dto.getZonasIds()) //
//                        .build(),
//
//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewSolicitudVisitaReportColumnEnum.ZONAS_NOMBRES) //
//                        .value(dto.getZonasNombres()) //
//                        .build()
        );

    }
}
