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
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvProveedorDTO;
import com.copeinca.apicopeincaprov.modules.core.models.writer.enums.SvPersonaRestringidaColumnEnum;
import com.copeinca.apicopeincaprov.modules.core.service.writer.ISvPersonaRestringidaWriterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
* Implementación del servicio de escritura para SvPersonaRestringidaEntity
* Actualmente lanza excepciones para métodos no implementados.
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class SvPersonaRestringidaWriterServiceImpl implements ISvPersonaRestringidaWriterService {

    @Override
    public AttachmentData exportToCsv(List<SvPersonaRestringidaDTO> data) {
        log.warn("Método exportToCsv no implementado para SvPersonaRestringidaEntity");
        throw new UnsupportedOperationException("La exportación a CSV no está implementada para SvPersonaRestringidaEntity");
    }

    public static List<SvPersonaRestringidaDTO> generarTresRegistros() {

        var ahora = LocalDateTime.now();

        return List.of(

                SvPersonaRestringidaDTO.builder()
                        //.id(UUID.randomUUID().toString())
                        .tipoDocumentoIdentidadCode("DNI")
                        .numeroDocumento("74589632")
                        .licenciaConducir("B12345678")
                        .nombreCompleto("Juan Carlos Mendoza Ríos")
                        .usuarioReporta("admin.seguridad")
                        .restringidoEl(ahora.minusDays(5))
                        .restringidoHasta(ahora.plusMonths(1))
                        .observaciones("Ingreso no autorizado a zona restringida")
                        .sedeOriginadoraId("CHIMBOTA FLOTA")
                        .isExists(false)
                        .observacion("Bloqueo temporal")
                        .build(),

                SvPersonaRestringidaDTO.builder()
                        //.id(UUID.randomUUID().toString())
                        .tipoDocumentoIdentidadCode("PASAPORTE")
                        .numeroDocumento("CE-99887766")
                        .licenciaConducir(null)
                        .nombreCompleto("Pedro Antonio Salas Torres")
                        .usuarioReporta("supervisor.turno1")
                        .restringidoEl(ahora.minusMonths(2))
                        .restringidoHasta(ahora.minusDays(1)) // ya vencido
                        .observaciones("Incumplimiento de normas de seguridad")
                        .sedeOriginadoraId("CHIMBOTA FLOTA")
                        .isExists(false)
                        .observacion("Restricción vencida - pendiente revisión")
                        .build(),

                SvPersonaRestringidaDTO.builder()
                        //.id(UUID.randomUUID().toString())
                        .tipoDocumentoIdentidadCode("DNI")
                        .numeroDocumento("P-1234567")
                        .licenciaConducir("A7654321")
                        .nombreCompleto("Michael Anderson")
                        .usuarioReporta("jefe.operaciones")
                        .restringidoEl(ahora.minusDays(1))
                        .restringidoHasta(null) // restricción indefinida
                        .observaciones("Investigación en curso")
                        .sedeOriginadoraId("CHIMBOTA FLOTA")
                        .isExists(false)
                        .observacion("Restricción indefinida hasta cierre de investigación")
                        .build()
        );
    }

    @Override
    public AttachmentData exportToExcel(List<SvPersonaRestringidaDTO> data) throws Exception {

        log.info("Iniciando exportación a Excel de {} registros de SvPersonaRestringidaDTO", data != null ? data.size() : 0);
        List<List<SimpleExcelColumn>> rows = null;
        if(data.size() == 0){
            data = generarTresRegistros();
            rows = Optional.ofNullable(data).orElse(new ArrayList<>()).stream()//
                    .map(this::defineColumnsHard)//
                    .collect(Collectors.toList());
        }else {
            rows = Optional.ofNullable(data).orElse(new ArrayList<>()).stream()//
                    .map(this::defineColumns)//
                    .collect(Collectors.toList());
        }


        //--- Crear lista de filas para Excel
//        List<List<SimpleExcelColumn>> rows = Optional.ofNullable(data).orElse(new ArrayList<>()).stream()//
//                .map(this::defineColumns)//
//                .collect(Collectors.toList());

        //--- Generar archivo Excel
        AttachmentData result = SimpleExcelExportUtilities.generateExcelFile(rows, "SvPersonaRestringidas");

        log.info("Archivo Excel generado exitosamente: {}", result.getName());

        return result;

    }

    public List<SimpleExcelColumn> defineColumnsHard(SvPersonaRestringidaDTO dto) {

        return Arrays.asList(

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvPersonaRestringidaColumnEnum.PERSONA_RESTRINGIDA_ID) //
                        .value(dto.getId()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvPersonaRestringidaColumnEnum.TIPO_DOCUMENTO_IDENTIDAD_CODE) //
                        .value(dto.getTipoDocumentoIdentidadCode()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvPersonaRestringidaColumnEnum.NUMERO_DOCUMENTO) //
                        .value(dto.getNumeroDocumento()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvPersonaRestringidaColumnEnum.LICENCIA_CONDUCIR) //
                        .value(dto.getLicenciaConducir()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvPersonaRestringidaColumnEnum.NOMBRE_COMPLETO) //
                        .value(dto.getNombreCompleto()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvPersonaRestringidaColumnEnum.USUARIO_REPORTA) //
                        .value(dto.getUsuarioReporta()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvPersonaRestringidaColumnEnum.RESTRINGIDO_EL) //
                        .value("10/02/2026") //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvPersonaRestringidaColumnEnum.RESTRINGIDO_HASTA) //
                        .value("12/02/2026") //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvPersonaRestringidaColumnEnum.OBSERVACIONES) //
                        .value(dto.getObservaciones()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvPersonaRestringidaColumnEnum.SEDE_ORIGINADORA_ID) //
                        .value(dto.getSedeOriginadoraId()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvPersonaRestringidaColumnEnum.IS_DELETED) //
                        .value(dto.getIsDeleted()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvPersonaRestringidaColumnEnum.VERSION) //
                        .value(dto.getVersion()) //
                        .build());

    }

    /**
     * Define las columnas para un usuario específico
     */
    @Override
    public List<SimpleExcelColumn> defineColumns(SvPersonaRestringidaDTO dto) {

        return Arrays.asList(

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvPersonaRestringidaColumnEnum.PERSONA_RESTRINGIDA_ID) //
                        .value(dto.getId()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvPersonaRestringidaColumnEnum.TIPO_DOCUMENTO_IDENTIDAD_CODE) //
                        .value(dto.getTipoDocumentoIdentidadCode()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvPersonaRestringidaColumnEnum.NUMERO_DOCUMENTO) //
                        .value(dto.getNumeroDocumento()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvPersonaRestringidaColumnEnum.LICENCIA_CONDUCIR) //
                        .value(dto.getLicenciaConducir()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvPersonaRestringidaColumnEnum.NOMBRE_COMPLETO) //
                        .value(dto.getNombreCompleto()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvPersonaRestringidaColumnEnum.USUARIO_REPORTA) //
                        .value(dto.getUsuarioReporta()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvPersonaRestringidaColumnEnum.RESTRINGIDO_EL) //
                        .value(dto.getRestringidoEl()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvPersonaRestringidaColumnEnum.RESTRINGIDO_HASTA) //
                        .value(dto.getRestringidoHasta()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvPersonaRestringidaColumnEnum.OBSERVACIONES) //
                        .value(dto.getObservaciones()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvPersonaRestringidaColumnEnum.SEDE_ORIGINADORA_ID) //
                        .value(dto.getSedeOriginadoraId()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvPersonaRestringidaColumnEnum.IS_DELETED) //
                        .value(dto.getIsDeleted()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvPersonaRestringidaColumnEnum.VERSION) //
                        .value(dto.getVersion()) //
                        .build());

    }
}
