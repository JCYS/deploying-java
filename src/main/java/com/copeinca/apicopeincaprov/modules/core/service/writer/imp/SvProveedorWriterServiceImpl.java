/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/writer/imp/EntityWriterServiceImpl.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.writer.imp;

import com.copeinca.apicopeincaprov.commons.report.excel.SimpleExcelColumn;
import com.copeinca.apicopeincaprov.commons.report.excel.SimpleExcelExportUtilities;
import com.copeinca.apicopeincaprov.global.dtos.AttachmentData;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvProveedorDTO;
import com.copeinca.apicopeincaprov.modules.core.models.dto.ViewProveedorReportDTO;
import com.copeinca.apicopeincaprov.modules.core.models.writer.enums.SvProveedorColumnEnum;
import com.copeinca.apicopeincaprov.modules.core.models.writer.enums.ViewProveedorReportColumnEnum;
import com.copeinca.apicopeincaprov.modules.core.service.writer.ISvProveedorWriterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
* Implementación del servicio de escritura para SvProveedorEntity
* Actualmente lanza excepciones para métodos no implementados.
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class SvProveedorWriterServiceImpl implements ISvProveedorWriterService {

    @Override
    public AttachmentData exportToCsv(List<SvProveedorDTO> data) {
        log.warn("Método exportToCsv no implementado para SvProveedorEntity");
        throw new UnsupportedOperationException("La exportación a CSV no está implementada para SvProveedorEntity");
    }

    @Override
    public AttachmentData exportToExcel(List<SvProveedorDTO> data) throws Exception {

        log.info("Iniciando exportación a Excel de {} registros de SvProveedorDTO", data != null ? data.size() : 0);

        if(data.size() == 0) {
            data =  generarTresRegistros();
        }
        //--- Crear lista de filas para Excel
        List<List<SimpleExcelColumn>> rows = Optional.ofNullable(data).orElse(new ArrayList<>()).stream()//
                .map(this::defineColumns)//
                .collect(Collectors.toList());
//        if(data.size() == 1){
//            rows.get(0).add(SimpleExcelColumn.builder() //
//                    .excelColumnProperties(SvProveedorColumnEnum.SEDE) //
//                    .value("") //
//                    .build());
//        }
        //rows.get(0).remove(0);
        //--- Generar archivo Excel
        AttachmentData result = SimpleExcelExportUtilities.generateExcelFile(rows, "SvProveedors");

        log.info("Archivo Excel generado exitosamente: {}", result.getName());

        return result;

    }

    public static List<SvProveedorDTO> generarTresRegistros() {

        LocalDate hoy = LocalDate.now();
        LocalDateTime ahora = LocalDateTime.now();

        return List.of(

                SvProveedorDTO.builder()
                        //.id(UUID.randomUUID().toString())
                        .estadoCalidadAmbientalCode("PRELIMINAR")
                        .estadoSsoCode("PRELIMINAR")
                        .nroDocumentoIdentidad("20601234567")
                        .nombreFiscal("Servicios Marítimos del Pacífico S.A.C.")
                        .email("contacto@maritimo.pe")
                        .direccion("Av. Costanera 1200 - Callao")
                        .contactoPrincipal("Carlos Mendoza")
                        .contactoPrincipalTelefono("987654321")
                        .ssoTieneVigencia(true)
                        .ssoFechaInicioVigencia(hoy.minusMonths(2))
                        .ssoFechaFinVigencia("2026-12-31")
                        .ssoMotivo("Cumple estándares SSO")
                        .ssoUsuarioEvaluador("admin.sso")
                        .ssoFechaEvaluacion(ahora.minusDays(10))
                        .indRealizaActividadAltoRiesgo(true)
                        .indRealizaActividadABordo(false)
                        .caTieneVigencia(true)
                        .caFechaInicioVigencia(hoy.minusMonths(1))
                        .caFechaFinVigencia(hoy.plusMonths(11))
                        .caMotivo("Certificación válida")
                        .caUsuarioEvaluador("admin.ca")
                        .caFechaEvaluacion(ahora.minusDays(5))
                        .origenRegistro("MANUAL")
                        .indProveedorNotificadoCreacion(true)
                        .telefono("014567890")
                        .nacional(true)
                        .isExists(false)
                        .sedesNombres("Chimbote, Callao")
                        .observacion("Proveedor habilitado para operaciones marítimas")
                        .build(),

                SvProveedorDTO.builder()
                        //.id(UUID.randomUUID().toString())
                        .estadoCalidadAmbientalCode("PRELIMINAR")
                        .estadoSsoCode("PRELIMINAR")
                        .nroDocumentoIdentidad("20567891234")
                        .nombreFiscal("Industrias Pesqueras del Norte S.A.")
                        .email("info@pesquera.pe")
                        .direccion("Mz A Lote 5 - Paita")
                        .contactoPrincipal("María López")
                        .contactoPrincipalTelefono("912345678")
                        .ssoTieneVigencia(false)
                        .ssoFechaInicioVigencia(hoy.minusMonths(6))
                        .ssoFechaFinVigencia("2024-12-31")
                        .ssoMotivo("Pendiente actualización documentación")
                        .ssoUsuarioEvaluador("evaluador1")
                        .ssoFechaEvaluacion(ahora.minusDays(30))
                        .indRealizaActividadAltoRiesgo(false)
                        .indRealizaActividadABordo(true)
                        .caTieneVigencia(false)
                        .caFechaInicioVigencia(hoy.minusMonths(8))
                        .caFechaFinVigencia(hoy.minusMonths(1))
                        .caMotivo("Certificado vencido")
                        .caUsuarioEvaluador("evaluador2")
                        .caFechaEvaluacion(ahora.minusDays(20))
                        .origenRegistro("CARGA_MASIVA")
                        .indProveedorNotificadoCreacion(false)
                        .telefono("019876543")
                        .nacional(true)
                        .isExists(false)
                        .sedesNombres("Paita")
                        .observacion("Requiere regularización de documentos")
                        .build(),

                SvProveedorDTO.builder()
                        //.id(UUID.randomUUID().toString())
                        .estadoCalidadAmbientalCode("PRELIMINAR")
                        .estadoSsoCode("PRELIMINAR")
                        .nroDocumentoIdentidad("20456789012")
                        .nombreFiscal("Servicios Industriales Andinos S.R.L.")
                        .email("contacto@andinos.pe")
                        .direccion("Av. Industrial 456 - Lima")
                        .contactoPrincipal("José Ramírez")
                        .contactoPrincipalTelefono("998877665")
                        .ssoTieneVigencia(false)
                        .ssoFechaInicioVigencia(hoy.minusYears(1))
                        .ssoFechaFinVigencia("2024-01-01")
                        .ssoMotivo("No cumple requisitos mínimos")
                        .ssoUsuarioEvaluador("admin")
                        .ssoFechaEvaluacion(ahora.minusDays(60))
                        .indRealizaActividadAltoRiesgo(true)
                        .indRealizaActividadABordo(true)
                        .caTieneVigencia(false)
                        .caFechaInicioVigencia(hoy.minusYears(1))
                        .caFechaFinVigencia(hoy.minusMonths(6))
                        .caMotivo("Falta documentación crítica")
                        .caUsuarioEvaluador("admin")
                        .caFechaEvaluacion(ahora.minusDays(45))
                        .origenRegistro("MANUAL")
                        .indProveedorNotificadoCreacion(true)
                        .telefono("015554433")
                        .nacional(false)
                        .isExists(false)
                        .sedesNombres("Callao")
                        .observacion("Proveedor bloqueado hasta nueva evaluación")
                        .build()
        );
    }
    /**
     * Define las columnas para un usuario específico
     */
    @Override
    public List<SimpleExcelColumn> defineColumns(SvProveedorDTO dto) {

        return Arrays.asList(

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvProveedorColumnEnum.PROVEEDOR_ID) //
                        .value(dto.getId()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvProveedorColumnEnum.ESTADO_CALIDAD_AMBIENTAL_CODE) //
                        .value(dto.getEstadoCalidadAmbientalCode()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvProveedorColumnEnum.NRO_DOCUMENTO_IDENTIDAD) //
                        .value(dto.getNroDocumentoIdentidad()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvProveedorColumnEnum.NOMBRE_FISCAL) //
                        .value(dto.getNombreFiscal()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvProveedorColumnEnum.EMAIL) //
                        .value(dto.getEmail()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvProveedorColumnEnum.DIRECCION) //
                        .value(dto.getDireccion()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvProveedorColumnEnum.CONTACTO_PRINCIPAL) //
                        .value(dto.getContactoPrincipal()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvProveedorColumnEnum.CONTACTO_PRINCIPAL_TELEFONO) //
                        .value(dto.getContactoPrincipalTelefono()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvProveedorColumnEnum.SSO_TIENE_VIGENCIA) //
                        .value(dto.getSsoTieneVigencia()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvProveedorColumnEnum.SSO_FECHA_INICIO_VIGENCIA) //
                        .value(dto.getSsoFechaInicioVigencia()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvProveedorColumnEnum.SSO_FECHA_FIN_VIGENCIA) //
                        .value(dto.getSsoFechaFinVigencia()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvProveedorColumnEnum.SSO_MOTIVO) //
                        .value(dto.getSsoMotivo()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvProveedorColumnEnum.SSO_USUARIO_EVALUADOR) //
                        .value(dto.getSsoUsuarioEvaluador()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvProveedorColumnEnum.SSO_FECHA_EVALUACION) //
                        .value(dto.getSsoFechaEvaluacion()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvProveedorColumnEnum.IND_REALIZA_ACTIVIDAD_ALTO_RIESGO) //
                        .value(dto.getIndRealizaActividadAltoRiesgo()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvProveedorColumnEnum.IND_REALIZA_ACTIVIDAD_A_BORDO) //
                        .value(dto.getIndRealizaActividadABordo()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvProveedorColumnEnum.CA_TIENE_VIGENCIA) //
                        .value(dto.getCaTieneVigencia()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvProveedorColumnEnum.CA_FECHA_INICIO_VIGENCIA) //
                        .value(dto.getCaFechaInicioVigencia()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvProveedorColumnEnum.CA_FECHA_FIN_VIGENCIA) //
                        .value(dto.getCaFechaFinVigencia()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvProveedorColumnEnum.CA_MOTIVO) //
                        .value(dto.getCaMotivo()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvProveedorColumnEnum.CA_USUARIO_EVALUADOR) //
                        .value(dto.getCaUsuarioEvaluador()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvProveedorColumnEnum.CA_FECHA_EVALUACION) //
                        .value(dto.getCaFechaEvaluacion()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvProveedorColumnEnum.ORIGEN_REGISTRO) //
                        .value(dto.getOrigenRegistro()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvProveedorColumnEnum.ESTADO_SSO_CODE) //
                        .value(dto.getEstadoSsoCode()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvProveedorColumnEnum.IND_PROVEEDOR_NOTIFICADO_CREACION) //
                        .value(dto.getIndProveedorNotificadoCreacion()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvProveedorColumnEnum.TELEFONO) //
                        .value(dto.getTelefono()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvProveedorColumnEnum.NACIONAL) //
                        .value(dto.getNacional()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvProveedorColumnEnum.IS_DELETED) //
                        .value(dto.getIsDeleted()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvProveedorColumnEnum.VERSION) //
                        .value(dto.getVersion()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvProveedorColumnEnum.SEDES) //
                        .value("") //
                        .build());

    }
}
