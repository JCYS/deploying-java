/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/query/IEntityQueryService.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.query;

import com.copeinca.apicopeincaprov.global.dtos.response.PagedRequest;
import com.copeinca.apicopeincaprov.global.dtos.response.PagedResult;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvSolicitudVisitaTipoActividadAltoRiesgoDTO;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.SvSolicitudVisitaTipoActividadAltoRiesgoFilter;

/**
* Interface de servicio para consultas de SvSolicitudVisitaTipoActividadAltoRiesgoEntity
* Responsable de todas las operaciones de búsqueda y consulta.
*/
public interface ISvSolicitudVisitaTipoActividadAltoRiesgoQueryService {

    /**
    * Búsqueda dinámica y paginada de registros.
    */
    PagedResult<SvSolicitudVisitaTipoActividadAltoRiesgoDTO> search(PagedRequest<SvSolicitudVisitaTipoActividadAltoRiesgoFilter> pagedRequest);

    /**
    * Búsqueda por identificador único.
    */
    SvSolicitudVisitaTipoActividadAltoRiesgoDTO findById(String id);

    /**
    * Verificar existencia de un registro.
    */
    boolean existsById(String id);

    /**
    * Contar registros que coinciden con el filtro.
    */
    long countByFilter(SvSolicitudVisitaTipoActividadAltoRiesgoFilter filter);
}
