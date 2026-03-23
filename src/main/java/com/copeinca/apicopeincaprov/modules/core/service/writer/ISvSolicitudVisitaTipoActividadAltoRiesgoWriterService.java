/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/writer/IEntityWriterService.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.writer;

import com.copeinca.apicopeincaprov.commons.report.excel.IDefineColumnReport;
import com.copeinca.apicopeincaprov.global.dtos.AttachmentData;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvSolicitudVisitaTipoActividadAltoRiesgoDTO;

import java.util.List;

/**
* Interface de servicio para escritura de archivos de SvSolicitudVisitaTipoActividadAltoRiesgoEntity
*/
public interface ISvSolicitudVisitaTipoActividadAltoRiesgoWriterService extends IDefineColumnReport<SvSolicitudVisitaTipoActividadAltoRiesgoDTO> {

    /**
    * Generar archivo CSV a partir de una lista de datos.
    */
    AttachmentData exportToCsv(List<SvSolicitudVisitaTipoActividadAltoRiesgoDTO> data);

    /**
    * Generar archivo Excel a partir de una lista de datos.
    */
    AttachmentData exportToExcel(List<SvSolicitudVisitaTipoActividadAltoRiesgoDTO> data) throws Exception;
}
