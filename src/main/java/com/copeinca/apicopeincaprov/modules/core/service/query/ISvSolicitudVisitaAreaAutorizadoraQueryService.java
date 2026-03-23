/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/query/IEntityQueryService.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.query;

import com.copeinca.apicopeincaprov.global.dtos.response.PagedRequest;
import com.copeinca.apicopeincaprov.global.dtos.response.PagedResult;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvSolicitudVisitaAreaAutorizadoraDTO;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.SvSolicitudVisitaAreaAutorizadoraFilter;

/**
* Interface de servicio para consultas de SvSolicitudVisitaAreaAutorizadoraEntity
* Responsable de todas las operaciones de búsqueda y consulta.
*/
public interface ISvSolicitudVisitaAreaAutorizadoraQueryService {

    /**
    * Búsqueda dinámica y paginada de registros.
    */
    PagedResult<SvSolicitudVisitaAreaAutorizadoraDTO> search(PagedRequest<SvSolicitudVisitaAreaAutorizadoraFilter> pagedRequest);

    /**
    * Búsqueda por identificador único.
    */
    SvSolicitudVisitaAreaAutorizadoraDTO findById(String id);

    /**
    * Verificar existencia de un registro.
    */
    boolean existsById(String id);

    /**
    * Contar registros que coinciden con el filtro.
    */
    long countByFilter(SvSolicitudVisitaAreaAutorizadoraFilter filter);
}
