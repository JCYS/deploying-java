/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/writer/IEntityWriterService.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.writer;

import com.copeinca.apicopeincaprov.commons.report.excel.IDefineColumnReport;
import com.copeinca.apicopeincaprov.global.dtos.AttachmentData;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvProveedorTipoActividadAltoRiesgoDTO;

import java.util.List;

/**
* Interface de servicio para escritura de archivos de SvProveedorTipoActividadAltoRiesgoEntity
*/
public interface ISvProveedorTipoActividadAltoRiesgoWriterService extends IDefineColumnReport<SvProveedorTipoActividadAltoRiesgoDTO> {

    /**
    * Generar archivo CSV a partir de una lista de datos.
    */
    AttachmentData exportToCsv(List<SvProveedorTipoActividadAltoRiesgoDTO> data);

    /**
    * Generar archivo Excel a partir de una lista de datos.
    */
    AttachmentData exportToExcel(List<SvProveedorTipoActividadAltoRiesgoDTO> data) throws Exception;
}
