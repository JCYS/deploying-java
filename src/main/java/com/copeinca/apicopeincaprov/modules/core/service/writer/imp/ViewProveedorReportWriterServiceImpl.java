/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/writer/imp/EntityWriterServiceImpl.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.writer.imp;

import com.copeinca.apicopeincaprov.commons.report.excel.SimpleExcelColumn;
import com.copeinca.apicopeincaprov.commons.report.excel.SimpleExcelExportUtilities;
import com.copeinca.apicopeincaprov.global.dtos.AttachmentData;
import com.copeinca.apicopeincaprov.modules.core.models.dto.ViewProveedorReportDTO;
import com.copeinca.apicopeincaprov.modules.core.models.writer.enums.ViewProveedorReportColumnEnum;
import com.copeinca.apicopeincaprov.modules.core.service.writer.IViewProveedorReportWriterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
* Implementación del servicio de escritura para ViewProveedorReportView
* Actualmente lanza excepciones para métodos no implementados.
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class ViewProveedorReportWriterServiceImpl implements IViewProveedorReportWriterService {

    @Override
    public AttachmentData exportToCsv(List<ViewProveedorReportDTO> data) {
        log.warn("Método exportToCsv no implementado para ViewProveedorReportView");
        throw new UnsupportedOperationException("La exportación a CSV no está implementada para ViewProveedorReportView");
    }

    @Override
    public AttachmentData exportToExcel(List<ViewProveedorReportDTO> data) throws Exception {

        log.info("Iniciando exportación a Excel de {} registros de ViewProveedorReportDTO", data != null ? data.size() : 0);

        //--- Crear lista de filas para Excel

        if(data.size() == 0){
            data = List.of(ViewProveedorReportDTO.builder().build());
        }
        List<List<SimpleExcelColumn>> rows = Optional.ofNullable(data).orElse(new ArrayList<>()).stream()//
                .map(this::defineColumns)//
                .collect(Collectors.toList());

        //--- Generar archivo Excel
        AttachmentData result = SimpleExcelExportUtilities.generateExcelFile(rows, "ViewProveedorReports");

        log.info("Archivo Excel generado exitosamente: {}", result.getName());

        return result;

    }

    /**
     * Define las columnas para un usuario específico
     */
    @Override
    public List<SimpleExcelColumn> defineColumns(ViewProveedorReportDTO dto) {

        return Arrays.asList(

//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewProveedorReportColumnEnum.PROVEEDOR_ID) //
//                        .value(dto.getId()) //
//                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(ViewProveedorReportColumnEnum.SEDES_NOMBRES) //
                        .value(dto.getSedesNombres()) //
                        .build(),
                SimpleExcelColumn.builder() //
                        .excelColumnProperties(ViewProveedorReportColumnEnum.NRO_DOCUMENTO_IDENTIDAD) //
                        .value(dto.getNroDocumentoIdentidad()) //
                        .build(),
                SimpleExcelColumn.builder() //
                        .excelColumnProperties(ViewProveedorReportColumnEnum.NOMBRE_FISCAL) //
                        .value(dto.getNombreFiscal()) //
                        .build(),
                SimpleExcelColumn.builder() //
                        .excelColumnProperties(ViewProveedorReportColumnEnum.EMAIL) //
                        .value(dto.getEmail()) //
                        .build(),
                SimpleExcelColumn.builder() //
                        .excelColumnProperties(ViewProveedorReportColumnEnum.TELEFONO) //
                        .value(dto.getTelefono()) //
                        .build(),
                SimpleExcelColumn.builder() //
                        .excelColumnProperties(ViewProveedorReportColumnEnum.DIRECCION) //
                        .value(dto.getDireccion()) //
                        .build(),
                SimpleExcelColumn.builder() //
                        .excelColumnProperties(ViewProveedorReportColumnEnum.CONTACTO_PRINCIPAL) //
                        .value(dto.getContactoPrincipal()) //
                        .build(),
                SimpleExcelColumn.builder() //
                        .excelColumnProperties(ViewProveedorReportColumnEnum.CONTACTO_PRINCIPAL_TELEFONO) //
                        .value(dto.getContactoPrincipalTelefono()) //
                        .build(),
                SimpleExcelColumn.builder() //
                        .excelColumnProperties(ViewProveedorReportColumnEnum.SSO_ESTADO_NOMBRE) //
                        .value(dto.getSsoEstadoNombre()) //
                        .build(),
                SimpleExcelColumn.builder() //
                        .excelColumnProperties(ViewProveedorReportColumnEnum.SSO_MOTIVO) //
                        .value(dto.getSsoMotivo()) //
                        .build(),
                SimpleExcelColumn.builder() //
                        .excelColumnProperties(ViewProveedorReportColumnEnum.SSO_FECHA_FIN_VIGENCIA) //
                        .value(dto.getSsoFechaFinVigencia()) //
                        .build(),
                SimpleExcelColumn.builder() //
                        .excelColumnProperties(ViewProveedorReportColumnEnum.CA_ESTADO_NOMBRE) //
                        .value(dto.getCaEstadoNombre()) //
                        .build(),
                SimpleExcelColumn.builder() //
                        .excelColumnProperties(ViewProveedorReportColumnEnum.CA_MOTIVO) //
                        .value(dto.getCaMotivo()) //
                        .build(),
                SimpleExcelColumn.builder() //
                        .excelColumnProperties(ViewProveedorReportColumnEnum.CA_FECHA_FIN_VIGENCIA) //
                        .value(dto.getCaFechaFinVigencia()) //
                        .build(),
                SimpleExcelColumn.builder() //
                        .excelColumnProperties(ViewProveedorReportColumnEnum.N_TRABAJADORES_APTOS) //
                        .value(dto.getNTrabajadoresAptos()) //
                        .build(),
                SimpleExcelColumn.builder() //
                        .excelColumnProperties(ViewProveedorReportColumnEnum.N_TRABAJADORES_TOTALES) //
                        .value(dto.getNTrabajadoresTotales()) //
                        .build(),
                SimpleExcelColumn.builder() //
                        .excelColumnProperties(ViewProveedorReportColumnEnum.N_DOCS_PROV_SSO) //
                        .value(dto.getNDocsProvSso()) //
                        .build(),
                SimpleExcelColumn.builder() //
                        .excelColumnProperties(ViewProveedorReportColumnEnum.N_DOCS_CA) //
                        .value(dto.getNDocsCa()) //
                        .build(),
                SimpleExcelColumn.builder() //
                        .excelColumnProperties(ViewProveedorReportColumnEnum.N_DOCS_TRABAJ_SSO) //
                        .value(dto.getNDocsTrabajSso()) //
                        .build(),
                SimpleExcelColumn.builder() //
                        .excelColumnProperties(ViewProveedorReportColumnEnum.IND_REALIZA_ACTIVIDAD_ALTO_RIESGO) //
                        .value(dto.getIndRealizaActividadAltoRiesgo()) //
                        .build(),
                SimpleExcelColumn.builder() //
                        .excelColumnProperties(ViewProveedorReportColumnEnum.TAR_DESCRIPCIONES) //
                        .value(dto.getTarDescripciones()) //
                        .build(),
                SimpleExcelColumn.builder() //
                        .excelColumnProperties(ViewProveedorReportColumnEnum.IND_REALIZA_ACTIVIDAD_A_BORDO) //
                        .value(dto.getIndRealizaActividadABordo()) //
                        .build()
//
//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewProveedorReportColumnEnum.NOMBRE_FISCAL) //
//                        .value(dto.getNombreFiscal()) //
//                        .build(),
//
//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewProveedorReportColumnEnum.EMAIL) //
//                        .value(dto.getEmail()) //
//                        .build(),
//
//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewProveedorReportColumnEnum.DIRECCION) //
//                        .value(dto.getDireccion()) //
//                        .build(),
//
//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewProveedorReportColumnEnum.TELEFONO) //
//                        .value(dto.getTelefono()) //
//                        .build(),

//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewProveedorReportColumnEnum.NACIONAL) //
//                        .value(dto.getNacional()) //
//                        .build(),

//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewProveedorReportColumnEnum.CONTACTO_PRINCIPAL) //
//                        .value(dto.getContactoPrincipal()) //
//                        .build(),
//
//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewProveedorReportColumnEnum.CONTACTO_PRINCIPAL_TELEFONO) //
//                        .value(dto.getContactoPrincipalTelefono()) //
//                        .build(),

//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewProveedorReportColumnEnum.ORIGEN_REGISTRO) //
//                        .value(dto.getOrigenRegistro()) //
//                        .build(),

//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewProveedorReportColumnEnum.SEDES_IDS) //
//                        .value(dto.getSedesIds()) //
//                        .build(),

//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewProveedorReportColumnEnum.SEDES_NOMBRES) //
//                        .value(dto.getSedesNombres()) //
//                        .build(),

//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewProveedorReportColumnEnum.ESTADO_SSO_CODE) //
//                        .value(dto.getEstadoSsoCode()) //
//                        .build(),

//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewProveedorReportColumnEnum.SSO_TIENE_VIGENCIA) //
//                        .value(dto.getSsoTieneVigencia()) //
//                        .build(),

//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewProveedorReportColumnEnum.SSO_FECHA_INICIO_VIGENCIA) //
//                        .value(dto.getSsoFechaInicioVigencia()) //
//                        .build(),

//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewProveedorReportColumnEnum.SSO_FECHA_FIN_VIGENCIA) //
//                        .value(dto.getSsoFechaFinVigencia()) //
//                        .build(),
//
//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewProveedorReportColumnEnum.SSO_MOTIVO) //
//                        .value(dto.getSsoMotivo()) //
//                        .build(),

//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewProveedorReportColumnEnum.SSO_USUARIO_EVALUADOR) //
//                        .value(dto.getSsoUsuarioEvaluador()) //
//                        .build(),
//
//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewProveedorReportColumnEnum.SSO_FECHA_EVALUACION) //
//                        .value(dto.getSsoFechaEvaluacion()) //
//                        .build(),

//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewProveedorReportColumnEnum.SSO_ESTADO_NOMBRE) //
//                        .value(dto.getSsoEstadoNombre()) //
//                        .build(),

//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewProveedorReportColumnEnum.SSO_ESTADO_DESCRIPCION) //
//                        .value(dto.getSsoEstadoDescripcion()) //
//                        .build(),

//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewProveedorReportColumnEnum.SSO_ESTADO_VIGENTE) //
//                        .value(dto.getSsoEstadoVigente()) //
//                        .build(),

//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewProveedorReportColumnEnum.ESTADO_CALIDAD_AMBIENTAL_CODE) //
//                        .value(dto.getEstadoCalidadAmbientalCode()) //
//                        .build(),

//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewProveedorReportColumnEnum.CA_TIENE_VIGENCIA) //
//                        .value(dto.getCaTieneVigencia()) //
//                        .build(),

//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewProveedorReportColumnEnum.CA_FECHA_INICIO_VIGENCIA) //
//                        .value(dto.getCaFechaInicioVigencia()) //
//                        .build(),

//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewProveedorReportColumnEnum.CA_FECHA_FIN_VIGENCIA) //
//                        .value(dto.getCaFechaFinVigencia()) //
//                        .build(),
//
//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewProveedorReportColumnEnum.CA_MOTIVO) //
//                        .value(dto.getCaMotivo()) //
//                        .build(),

//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewProveedorReportColumnEnum.CA_USUARIO_EVALUADOR) //
//                        .value(dto.getCaUsuarioEvaluador()) //
//                        .build(),
//
//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewProveedorReportColumnEnum.CA_FECHA_EVALUACION) //
//                        .value(dto.getCaFechaEvaluacion()) //
//                        .build(),

//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewProveedorReportColumnEnum.CA_ESTADO_NOMBRE) //
//                        .value(dto.getCaEstadoNombre()) //
//                        .build(),

//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewProveedorReportColumnEnum.CA_ESTADO_DESCRIPCION) //
//                        .value(dto.getCaEstadoDescripcion()) //
//                        .build(),

//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewProveedorReportColumnEnum.CA_ESTADO_VIGENTE) //
//                        .value(dto.getCaEstadoVigente()) //
//                        .build(),

//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewProveedorReportColumnEnum.N_TRABAJADORES_APTOS) //
//                        .value(dto.getNTrabajadoresAptos()) //
//                        .build(),
//
//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewProveedorReportColumnEnum.N_TRABAJADORES_TOTALES) //
//                        .value(dto.getNTrabajadoresTotales()) //
//                        .build(),
//
//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewProveedorReportColumnEnum.N_DOCS_PROV_SSO) //
//                        .value(dto.getNDocsProvSso()) //
//                        .build(),
//
//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewProveedorReportColumnEnum.N_DOCS_CA) //
//                        .value(dto.getNDocsCa()) //
//                        .build(),

//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewProveedorReportColumnEnum.N_DOCS_AAR) //
//                        .value(dto.getNDocsAar()) //
//                        .build(),

//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewProveedorReportColumnEnum.N_DOCS_AB) //
//                        .value(dto.getNDocsAb()) //
//                        .build(),

//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewProveedorReportColumnEnum.N_DOCS_TRABAJ_SSO) //
//                        .value(dto.getNDocsTrabajSso()) //
//                        .build(),
//
//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewProveedorReportColumnEnum.IND_REALIZA_ACTIVIDAD_ALTO_RIESGO) //
//                        .value(dto.getIndRealizaActividadAltoRiesgo()) //
//                        .build(),
//
//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewProveedorReportColumnEnum.IND_REALIZA_ACTIVIDAD_A_BORDO) //
//                        .value(dto.getIndRealizaActividadABordo()) //
//                        .build(),

//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewProveedorReportColumnEnum.TAR_CODIGOS) //
//                        .value(dto.getTarCodigos()) //
//                        .build(),

//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(ViewProveedorReportColumnEnum.TAR_DESCRIPCIONES) //
//                        .value(dto.getTarDescripciones()) //
//                        .build()
        );

    }
}
