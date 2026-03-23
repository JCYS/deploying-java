/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/query/imp/EntityQueryServiceImpl.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.query.imp;

import com.copeinca.apicopeincaprov.commons.utils.dto.PagingUtil;
import com.copeinca.apicopeincaprov.global.dtos.response.PagedRequest;
import com.copeinca.apicopeincaprov.global.dtos.response.PagedResult;
import com.copeinca.apicopeincaprov.modules.core.mappers.SvSolicitudVisitaHistorialMapper;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvSolicitudVisitaHistorialDTO;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.SvSolicitudVisitaHistorialFilter;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvSolicitudVisitaHistorialEntity;
import com.copeinca.apicopeincaprov.modules.core.repositories.jpa.ISvSolicitudVisitaHistorialRepository;
import com.copeinca.apicopeincaprov.modules.core.service.query.ISvSolicitudVisitaHistorialQueryService;
import com.copeinca.apicopeincaprov.modules.core.specification.SvSolicitudVisitaHistorialSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
* Implementación del servicio de consultas para SvSolicitudVisitaHistorialEntity
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class SvSolicitudVisitaHistorialQueryServiceImpl implements ISvSolicitudVisitaHistorialQueryService {

    private final ISvSolicitudVisitaHistorialRepository repository;
    private final SvSolicitudVisitaHistorialMapper mapper;

    @Override
    public PagedResult<SvSolicitudVisitaHistorialDTO> search(PagedRequest<SvSolicitudVisitaHistorialFilter> pagedRequest) {
        SvSolicitudVisitaHistorialFilter filter = pagedRequest.getFilter();

        Pageable pageable = PagingUtil.toPageable(pagedRequest);

        Page<SvSolicitudVisitaHistorialEntity> page = repository.findAll(SvSolicitudVisitaHistorialSpecification.byFilter(filter), pageable);

        List<SvSolicitudVisitaHistorialDTO> dtos = page.getContent().stream().map(mapper::entityToDto).collect(Collectors.toList());

        return PagedResult.<SvSolicitudVisitaHistorialDTO> builder().count((int) page.getTotalElements()).offset((int) page.getPageable().getOffset())
                .limit(page.getSize()).page(page.getNumber()).totalPages(page.getTotalPages()).hasNext(page.hasNext()).hasPrevious(page.hasPrevious())
                .result(dtos).build();

    }

    @Override
    public SvSolicitudVisitaHistorialDTO findById(String id) {
        SvSolicitudVisitaHistorialEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("SvSolicitudVisitaHistorialEntity not found with id: " + id));
        return mapper.entityToDto(entity);
    }

    @Override
    public boolean existsById(String id) {
        return repository.existsById(id);
    }

    @Override
    public long countByFilter(SvSolicitudVisitaHistorialFilter filter) {
        return repository.count(SvSolicitudVisitaHistorialSpecification.byFilter(filter));
    }
}
