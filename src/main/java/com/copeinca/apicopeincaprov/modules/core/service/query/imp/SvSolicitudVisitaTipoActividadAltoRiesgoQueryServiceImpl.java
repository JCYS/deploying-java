/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/query/imp/EntityQueryServiceImpl.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.query.imp;

import com.copeinca.apicopeincaprov.commons.utils.dto.PagingUtil;
import com.copeinca.apicopeincaprov.global.dtos.response.PagedRequest;
import com.copeinca.apicopeincaprov.global.dtos.response.PagedResult;
import com.copeinca.apicopeincaprov.modules.core.mappers.SvSolicitudVisitaTipoActividadAltoRiesgoMapper;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvSolicitudVisitaTipoActividadAltoRiesgoDTO;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.SvSolicitudVisitaTipoActividadAltoRiesgoFilter;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvSolicitudVisitaTipoActividadAltoRiesgoEntity;
import com.copeinca.apicopeincaprov.modules.core.repositories.jpa.ISvSolicitudVisitaTipoActividadAltoRiesgoRepository;
import com.copeinca.apicopeincaprov.modules.core.service.query.ISvSolicitudVisitaTipoActividadAltoRiesgoQueryService;
import com.copeinca.apicopeincaprov.modules.core.specification.SvSolicitudVisitaTipoActividadAltoRiesgoSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
* Implementación del servicio de consultas para SvSolicitudVisitaTipoActividadAltoRiesgoEntity
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class SvSolicitudVisitaTipoActividadAltoRiesgoQueryServiceImpl implements ISvSolicitudVisitaTipoActividadAltoRiesgoQueryService {

    private final ISvSolicitudVisitaTipoActividadAltoRiesgoRepository repository;
    private final SvSolicitudVisitaTipoActividadAltoRiesgoMapper mapper;

    @Override
    public PagedResult<SvSolicitudVisitaTipoActividadAltoRiesgoDTO> search(PagedRequest<SvSolicitudVisitaTipoActividadAltoRiesgoFilter> pagedRequest) {
        SvSolicitudVisitaTipoActividadAltoRiesgoFilter filter = pagedRequest.getFilter();

        Pageable pageable = PagingUtil.toPageable(pagedRequest);

        Page<SvSolicitudVisitaTipoActividadAltoRiesgoEntity> page = repository.findAll(SvSolicitudVisitaTipoActividadAltoRiesgoSpecification.byFilter(filter),
                pageable);

        List<SvSolicitudVisitaTipoActividadAltoRiesgoDTO> dtos = page.getContent().stream().map(mapper::entityToDto).collect(Collectors.toList());

        return PagedResult.<SvSolicitudVisitaTipoActividadAltoRiesgoDTO> builder().count((int) page.getTotalElements())
                .offset((int) page.getPageable().getOffset()).limit(page.getSize()).page(page.getNumber()).totalPages(page.getTotalPages())
                .hasNext(page.hasNext()).hasPrevious(page.hasPrevious()).result(dtos).build();

    }

    @Override
    public SvSolicitudVisitaTipoActividadAltoRiesgoDTO findById(String id) {
        SvSolicitudVisitaTipoActividadAltoRiesgoEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("SvSolicitudVisitaTipoActividadAltoRiesgoEntity not found with id: " + id));
        return mapper.entityToDto(entity);
    }

    @Override
    public boolean existsById(String id) {
        return repository.existsById(id);
    }

    @Override
    public long countByFilter(SvSolicitudVisitaTipoActividadAltoRiesgoFilter filter) {
        return repository.count(SvSolicitudVisitaTipoActividadAltoRiesgoSpecification.byFilter(filter));
    }
}
