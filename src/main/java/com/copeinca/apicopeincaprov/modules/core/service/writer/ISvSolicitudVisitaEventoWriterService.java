/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/writer/IEntityWriterService.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.writer;

import com.copeinca.apicopeincaprov.commons.report.excel.IDefineColumnReport;
import com.copeinca.apicopeincaprov.global.dtos.AttachmentData;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvSolicitudVisitaEventoDTO;

import java.util.List;

/**
* Interface de servicio para escritura de archivos de SvSolicitudVisitaEventoEntity
*/
public interface ISvSolicitudVisitaEventoWriterService extends IDefineColumnReport<SvSolicitudVisitaEventoDTO> {

    /**
    * Generar archivo CSV a partir de una lista de datos.
    */
    AttachmentData exportToCsv(List<SvSolicitudVisitaEventoDTO> data);

    /**
    * Generar archivo Excel a partir de una lista de datos.
    */
    AttachmentData exportToExcel(List<SvSolicitudVisitaEventoDTO> data) throws Exception;
}
