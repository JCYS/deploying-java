/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/query/IEntityQueryService.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.query;

import com.copeinca.apicopeincaprov.global.dtos.response.PagedRequest;
import com.copeinca.apicopeincaprov.global.dtos.response.PagedResult;
import com.copeinca.apicopeincaprov.modules.core.models.dto.ViewAdjuntoReportDTO;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.ViewAdjuntoReportFilter;

/**
* Interface de servicio para consultas de ViewAdjuntoReportView
* Responsable de todas las operaciones de búsqueda y consulta.
*/
public interface IViewAdjuntoReportQueryService {

    /**
    * Búsqueda dinámica y paginada de registros.
    */
    PagedResult<ViewAdjuntoReportDTO> search(PagedRequest<ViewAdjuntoReportFilter> pagedRequest);

    /**
    * Búsqueda por identificador único.
    */
    ViewAdjuntoReportDTO findById(String id);

    /**
    * Verificar existencia de un registro.
    */
    boolean existsById(String id);

    /**
    * Contar registros que coinciden con el filtro.
    */
    long countByFilter(ViewAdjuntoReportFilter filter);
}
