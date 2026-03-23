/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/writer/imp/EntityWriterServiceImpl.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.writer.imp;

import com.copeinca.apicopeincaprov.commons.report.excel.SimpleExcelColumn;
import com.copeinca.apicopeincaprov.commons.report.excel.SimpleExcelExportUtilities;
import com.copeinca.apicopeincaprov.global.dtos.AttachmentData;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvPersonaRestringidaDTO;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvTrabajadorDTO;
import com.copeinca.apicopeincaprov.modules.core.models.writer.enums.SvTrabajadorColumnEnum;
import com.copeinca.apicopeincaprov.modules.core.service.writer.ISvTrabajadorWriterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
* Implementación del servicio de escritura para SvTrabajadorEntity
* Actualmente lanza excepciones para métodos no implementados.
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class SvTrabajadorWriterServiceImpl implements ISvTrabajadorWriterService {

    @Override
    public AttachmentData exportToCsv(List<SvTrabajadorDTO> data) {
        log.warn("Método exportToCsv no implementado para SvTrabajadorEntity");
        throw new UnsupportedOperationException("La exportación a CSV no está implementada para SvTrabajadorEntity");
    }
    public static List<SvTrabajadorDTO> generarTresRegistros(String proveedorId) {
        var hoy = LocalDate.now();

        // Si no te pasan proveedorId, generamos uno para que no quede null
//        String provId = (proveedorId == null || proveedorId.isBlank())
//                ? UUID.randomUUID().toString()
//                : proveedorId;

        return List.of(

                SvTrabajadorDTO.builder()
                        //.id(UUID.randomUUID().toString())
                        //.proveedorId(provId)
                        .tipoDocumentoIdentidadCode("DNI")
                        .tipoDocumentoIdentidadDescription("Documento Nacional de Identidad")
                        .nroDocumentoIdentidad("74859632")
                        .nombre("Luis Alberto Rojas")
                        .telefono("987654321")
                        .email("luis.rojas@mail.com")
                        .ssoMotivo("SSO vigente y validado")
                        .soTieneVigencia(true)
                        .ssoFechaInicioVigencia(hoy.minusMonths(3))
                        .ssoFechaFinVigencia(hoy.plusMonths(9))
                        .indTrabajoAltoRiesgo(true)
                        .indPrevencionista(false)
                        .estadoSsoCode("PRELIMINAR")
                        .estadoSsoDescription("PRELIMINAR")
                        .isActive(true)
                        .isExists(false)
                        .isDeleted(false)
                        .observacion("Apto para ingreso a sede")
//                        .solicitudVisitaDetalleObs(null)
//                        .solicitudVisitaDetalleId(null)
//                        .solicitudVisitaDetalleVersion(null)
                        .build(),

                SvTrabajadorDTO.builder()
//                        .id(UUID.randomUUID().toString())
//                        .proveedorId(provId)
                        .tipoDocumentoIdentidadCode("PASAPORTE")
                        .tipoDocumentoIdentidadDescription("Carné de Extranjería")
                        .nroDocumentoIdentidad("CE-11223344")
                        .nombre("María Fernanda Salas")
                        .telefono("912345678")
                        .email("maria.salas@mail.com")
                        .ssoMotivo("Pendiente actualización de exámenes")
                        .soTieneVigencia(false)
                        .ssoFechaInicioVigencia(hoy.minusMonths(10))
                        .ssoFechaFinVigencia(hoy.minusDays(15))
                        .indTrabajoAltoRiesgo(false)
                        .indPrevencionista(true)
                        .estadoSsoCode("PRELIMINAR")
                        .estadoSsoDescription("PRELIMINAR")
                        .isActive(true)
                        .isExists(false)
                        .isDeleted(false)
                        .observacion("Requiere regularización antes de asignar visita")
//                        .solicitudVisitaDetalleObs("SSO vencido - no habilitar")
//                        .solicitudVisitaDetalleId("SVD-" + UUID.randomUUID())
//                        .solicitudVisitaDetalleVersion(1L)
                        .build(),

                SvTrabajadorDTO.builder()
                        //.id(UUID.randomUUID().toString())
                        //.proveedorId(provId)
                        .tipoDocumentoIdentidadCode("DNI")
                        .tipoDocumentoIdentidadDescription("Documento Nacional de Identidad")
                        .nroDocumentoIdentidad("70112233")
                        .nombre("José Manuel Paredes")
                        .telefono("999888777")
                        .email("jose.paredes@mail.com")
                        .ssoMotivo("Observado por documentación incompleta")
                        .soTieneVigencia(false)
                        .ssoFechaInicioVigencia(hoy.minusMonths(1))
                        .ssoFechaFinVigencia(hoy.plusDays(10))
                        .indTrabajoAltoRiesgo(true)
                        .indPrevencionista(false)
                        .estadoSsoCode("PRELIMINAR")
                        .estadoSsoDescription("PRELIMINAR")
                        .isActive(false) // caso útil para filtros
                        .isExists(false)
                        .observacion("No activo hasta levantar observación")
                        .isDeleted(false)
//                        .solicitudVisitaDetalleObs("Falta certificado de capacitación")
//                        .solicitudVisitaDetalleId("SVD-" + UUID.randomUUID())
//                        .solicitudVisitaDetalleVersion(2L)
                        .build()
        );
    }
    @Override
    public AttachmentData exportToExcel(List<SvTrabajadorDTO> data) throws Exception {

        log.info("Iniciando exportación a Excel de {} registros de SvTrabajadorDTO", data != null ? data.size() : 0);
        if(data.size() == 0)
            data = generarTresRegistros(null);

        //--- Crear lista de filas para Excel
        List<List<SimpleExcelColumn>> rows = Optional.ofNullable(data).orElse(new ArrayList<>()).stream()//
                .map(this::defineColumns)//
                .collect(Collectors.toList());

        //--- Generar archivo Excel
        AttachmentData result = SimpleExcelExportUtilities.generateExcelFile(rows, "SvTrabajadors");

        log.info("Archivo Excel generado exitosamente: {}", result.getName());

        return result;

    }

    /**
     * Define las columnas para un usuario específico
     */
    @Override
    public List<SimpleExcelColumn> defineColumns(SvTrabajadorDTO dto) {

        return Arrays.asList(

//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(SvTrabajadorColumnEnum.TRABAJADOR_ID) //
//                        .value(dto.getId()) //
//                        .build(),
//
//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(SvTrabajadorColumnEnum.PROVEEDOR_ID) //
//                        .value(dto.getProveedorId()) //
//                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvTrabajadorColumnEnum.TIPO_DOCUMENTO_IDENTIDAD_CODE) //
                        .value(dto.getTipoDocumentoIdentidadCode()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvTrabajadorColumnEnum.NRO_DOCUMENTO_IDENTIDAD) //
                        .value(dto.getNroDocumentoIdentidad()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvTrabajadorColumnEnum.NOMBRE) //
                        .value(dto.getNombre()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvTrabajadorColumnEnum.TELEFONO) //
                        .value(dto.getTelefono()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvTrabajadorColumnEnum.EMAIL) //
                        .value(dto.getEmail()) //
                        .build(),

//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(SvTrabajadorColumnEnum.SSO_MOTIVO) //
//                        .value(dto.getSsoMotivo()) //
//                        .build(),
//
//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(SvTrabajadorColumnEnum.SO_TIENE_VIGENCIA) //
//                        .value(dto.getSoTieneVigencia()) //
//                        .build(),
//
//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(SvTrabajadorColumnEnum.SSO_FECHA_INICIO_VIGENCIA) //
//                        .value(dto.getSsoFechaInicioVigencia()) //
//                        .build(),
//
//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(SvTrabajadorColumnEnum.SSO_FECHA_FIN_VIGENCIA) //
//                        .value(dto.getSsoFechaFinVigencia()) //
//                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvTrabajadorColumnEnum.IND_TRABAJO_ALTO_RIESGO) //
                        .value(dto.getIndTrabajoAltoRiesgo()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvTrabajadorColumnEnum.IND_PREVENCIONISTA) //
                        .value(dto.getIndPrevencionista()) //
                        .build()

//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(SvTrabajadorColumnEnum.ESTADO_SSO_CODE) //
//                        .value(dto.getEstadoSsoCode()) //
//                        .build(),

//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(SvTrabajadorColumnEnum.IS_ACTIVE) //
//                        .value(dto.getIsActive()) //
//                        .build(),
//
//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(SvTrabajadorColumnEnum.IS_DELETED) //
//                        .value(dto.getIsDeleted()) //
//                        .build(),
//
//                SimpleExcelColumn.builder() //
//                        .excelColumnProperties(SvTrabajadorColumnEnum.VERSION) //
//                        .value(dto.getVersion()) //
//                        .build()
        );

    }
}
