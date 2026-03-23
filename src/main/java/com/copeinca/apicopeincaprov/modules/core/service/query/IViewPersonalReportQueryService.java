/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/query/IEntityQueryService.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.query;

import com.copeinca.apicopeincaprov.global.dtos.response.PagedRequest;
import com.copeinca.apicopeincaprov.global.dtos.response.PagedResult;
import com.copeinca.apicopeincaprov.modules.core.models.dto.ViewPersonalReportDTO;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.ViewPersonalReportFilter;

/**
* Interface de servicio para consultas de ViewPersonalReportView
* Responsable de todas las operaciones de búsqueda y consulta.
*/
public interface IViewPersonalReportQueryService {

    /**
    * Búsqueda dinámica y paginada de registros.
    */
    PagedResult<ViewPersonalReportDTO> search(PagedRequest<ViewPersonalReportFilter> pagedRequest);

    /**
    * Búsqueda por identificador único.
    */
    ViewPersonalReportDTO findById(String id);

    /**
    * Verificar existencia de un registro.
    */
    boolean existsById(String id);

    /**
    * Contar registros que coinciden con el filtro.
    */
    long countByFilter(ViewPersonalReportFilter filter);
}
