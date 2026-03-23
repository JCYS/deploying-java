/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/query/IEntityQueryService.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.query;

import com.copeinca.apicopeincaprov.global.dtos.response.PagedRequest;
import com.copeinca.apicopeincaprov.global.dtos.response.PagedResult;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvProveedorTipoActividadAltoRiesgoDTO;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.SvProveedorTipoActividadAltoRiesgoFilter;

/**
* Interface de servicio para consultas de SvProveedorTipoActividadAltoRiesgoEntity
* Responsable de todas las operaciones de búsqueda y consulta.
*/
public interface ISvProveedorTipoActividadAltoRiesgoQueryService {

    /**
    * Búsqueda dinámica y paginada de registros.
    */
    PagedResult<SvProveedorTipoActividadAltoRiesgoDTO> search(PagedRequest<SvProveedorTipoActividadAltoRiesgoFilter> pagedRequest);

    /**
    * Búsqueda por identificador único.
    */
    SvProveedorTipoActividadAltoRiesgoDTO findById(String id);

    /**
    * Verificar existencia de un registro.
    */
    boolean existsById(String id);

    /**
    * Contar registros que coinciden con el filtro.
    */
    long countByFilter(SvProveedorTipoActividadAltoRiesgoFilter filter);
}
