/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/query/IEntityQueryService.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.query;

import com.copeinca.apicopeincaprov.global.dtos.response.PagedRequest;
import com.copeinca.apicopeincaprov.global.dtos.response.PagedResult;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvAdjuntoDTO;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.SvAdjuntoFilter;

/**
* Interface de servicio para consultas de SvAdjuntoEntity
* Responsable de todas las operaciones de búsqueda y consulta.
*/
public interface ISvAdjuntoQueryService {

    /**
    * Búsqueda dinámica y paginada de registros.
    */
    PagedResult<SvAdjuntoDTO> search(PagedRequest<SvAdjuntoFilter> pagedRequest);

    /**
    * Búsqueda por identificador único.
    */
    SvAdjuntoDTO findById(String id);

    /**
    * Verificar existencia de un registro.
    */
    boolean existsById(String id);

    /**
    * Contar registros que coinciden con el filtro.
    */
    long countByFilter(SvAdjuntoFilter filter);
}
