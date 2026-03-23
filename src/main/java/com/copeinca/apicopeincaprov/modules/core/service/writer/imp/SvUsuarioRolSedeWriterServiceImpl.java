/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/writer/imp/EntityWriterServiceImpl.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.writer.imp;

import com.copeinca.apicopeincaprov.commons.report.excel.SimpleExcelColumn;
import com.copeinca.apicopeincaprov.commons.report.excel.SimpleExcelExportUtilities;
import com.copeinca.apicopeincaprov.global.dtos.AttachmentData;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvUsuarioRolSedeDTO;
import com.copeinca.apicopeincaprov.modules.core.models.writer.enums.SvUsuarioRolSedeColumnEnum;
import com.copeinca.apicopeincaprov.modules.core.service.writer.ISvUsuarioRolSedeWriterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
* Implementación del servicio de escritura para SvUsuarioRolSedeEntity
* Actualmente lanza excepciones para métodos no implementados.
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class SvUsuarioRolSedeWriterServiceImpl implements ISvUsuarioRolSedeWriterService {

    @Override
    public AttachmentData exportToCsv(List<SvUsuarioRolSedeDTO> data) {
        log.warn("Método exportToCsv no implementado para SvUsuarioRolSedeEntity");
        throw new UnsupportedOperationException("La exportación a CSV no está implementada para SvUsuarioRolSedeEntity");
    }

    @Override
    public AttachmentData exportToExcel(List<SvUsuarioRolSedeDTO> data) throws Exception {

        log.info("Iniciando exportación a Excel de {} registros de SvUsuarioRolSedeDTO", data != null ? data.size() : 0);

        //--- Crear lista de filas para Excel
        List<List<SimpleExcelColumn>> rows = Optional.ofNullable(data).orElse(new ArrayList<>()).stream()//
                .map(this::defineColumns)//
                .collect(Collectors.toList());

        //--- Generar archivo Excel
        AttachmentData result = SimpleExcelExportUtilities.generateExcelFile(rows, "SvUsuarioRolSedes");

        log.info("Archivo Excel generado exitosamente: {}", result.getName());

        return result;

    }

    /**
     * Define las columnas para un usuario específico
     */
    @Override
    public List<SimpleExcelColumn> defineColumns(SvUsuarioRolSedeDTO dto) {

        return Arrays.asList(

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvUsuarioRolSedeColumnEnum.USUARIO_ROL_SEDE_ID) //
                        .value(dto.getId()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvUsuarioRolSedeColumnEnum.USUARIO_ID) //
                        .value(dto.getUsuarioId()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvUsuarioRolSedeColumnEnum.ROL_CODE) //
                        .value(dto.getRolCode()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvUsuarioRolSedeColumnEnum.SEDE_ID) //
                        .value(dto.getSedeId()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvUsuarioRolSedeColumnEnum.IS_ACTIVE) //
                        .value(dto.getIsActive()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvUsuarioRolSedeColumnEnum.IS_DELETED) //
                        .value(dto.getIsDeleted()) //
                        .build(),

                SimpleExcelColumn.builder() //
                        .excelColumnProperties(SvUsuarioRolSedeColumnEnum.VERSION) //
                        .value(dto.getVersion()) //
                        .build());

    }
}
