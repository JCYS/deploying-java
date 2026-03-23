/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/writer/imp/EntityWriterServiceImpl.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.writer.imp;

import com.copeinca.apicopeincaprov.commons.report.excel.SimpleExcelColumn;
import com.copeinca.apicopeincaprov.commons.report.excel.SimpleExcelExportUtilities;
import com.copeinca.apicopeincaprov.global.dtos.AttachmentData;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvUsuarioDTO;
import com.copeinca.apicopeincaprov.modules.core.models.writer.enums.SvUsuarioColumnEnum;
import com.copeinca.apicopeincaprov.modules.core.service.writer.ISvUsuarioWriterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
* Implementación del servicio de escritura para SvUsuarioEntity
* Actualmente lanza excepciones para métodos no implementados.
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class SvUsuarioWriterServiceImpl implements ISvUsuarioWriterService {

    @Override
    public AttachmentData exportToCsv(List<SvUsuarioDTO> data) {
        log.warn("Método exportToCsv no implementado para SvUsuarioEntity");
        throw new UnsupportedOperationException("La exportación a CSV no está implementada para SvUsuarioEntity");
    }

    @Override
    public AttachmentData exportToExcel(List<SvUsuarioDTO> data) throws Exception {

        log.info("Iniciando exportación a Excel de {} registros de SvUsuarioDTO", data != null ? data.size() : 0);

        //--- Crear lista de filas para Excel
        List<List<SimpleExcelColumn>> rows = Optional.ofNullable(data).orElse(new ArrayList<>()).stream()//
                .map(this::defineColumns)//
                .collect(Collectors.toList());

        //--- Generar archivo Excel
        AttachmentData result = SimpleExcelExportUtilities.generateExcelFile(rows, "SvUsuarios");

        log.info("Archivo Excel generado exitosamente: {}", result.getName());

        return result;

    }

    /**
     * Define las columnas para un usuario específico
     */
    @Override
    public List<SimpleExcelColumn> defineColumns(SvUsuarioDTO dto) {

        return Arrays.asList(

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvUsuarioColumnEnum.USUARIO_ID) //
                        .value(dto.getId()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvUsuarioColumnEnum.NOMBRE) //
                        .value(dto.getNombre()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvUsuarioColumnEnum.APELLIDOS) //
                        .value(dto.getApellidos()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvUsuarioColumnEnum.EMAIL) //
                        .value(dto.getEmail()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvUsuarioColumnEnum.IDENTIFICADOR_EXTERNO) //
                        .value(dto.getIdentificadorExterno()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvUsuarioColumnEnum.IS_DELETED) //
                        .value(dto.getIsDeleted()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvUsuarioColumnEnum.VERSION) //
                        .value(dto.getVersion()) //
                        .build());

    }
}
