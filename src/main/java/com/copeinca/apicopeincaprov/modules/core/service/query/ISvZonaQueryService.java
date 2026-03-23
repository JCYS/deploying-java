/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/query/IEntityQueryService.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.query;

import com.copeinca.apicopeincaprov.global.dtos.response.PagedRequest;
import com.copeinca.apicopeincaprov.global.dtos.response.PagedResult;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvZonaDTO;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.SvZonaFilter;

/**
* Interface de servicio para consultas de SvZonaEntity
* Responsable de todas las operaciones de búsqueda y consulta.
*/
public interface ISvZonaQueryService {

    /**
    * Búsqueda dinámica y paginada de registros.
    */
    PagedResult<SvZonaDTO> search(PagedRequest<SvZonaFilter> pagedRequest);

    /**
    * Búsqueda por identificador único.
    */
    SvZonaDTO findById(String id);

    /**
    * Verificar existencia de un registro.
    */
    boolean existsById(String id);

    /**
    * Contar registros que coinciden con el filtro.
    */
    long countByFilter(SvZonaFilter filter);
}
