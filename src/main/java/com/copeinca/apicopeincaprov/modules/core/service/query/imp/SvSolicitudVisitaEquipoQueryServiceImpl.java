/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/query/imp/EntityQueryServiceImpl.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.query.imp;

import com.copeinca.apicopeincaprov.commons.utils.dto.PagingUtil;
import com.copeinca.apicopeincaprov.global.dtos.response.PagedRequest;
import com.copeinca.apicopeincaprov.global.dtos.response.PagedResult;
import com.copeinca.apicopeincaprov.modules.core.mappers.SvSolicitudVisitaEquipoMapper;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvSolicitudVisitaEquipoDTO;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.SvSolicitudVisitaEquipoFilter;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvSolicitudVisitaEquipoEntity;
import com.copeinca.apicopeincaprov.modules.core.repositories.jpa.ISvSolicitudVisitaEquipoRepository;
import com.copeinca.apicopeincaprov.modules.core.service.query.ISvSolicitudVisitaEquipoQueryService;
import com.copeinca.apicopeincaprov.modules.core.specification.SvSolicitudVisitaEquipoSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
* Implementación del servicio de consultas para SvSolicitudVisitaEquipoEntity
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class SvSolicitudVisitaEquipoQueryServiceImpl implements ISvSolicitudVisitaEquipoQueryService {

    private final ISvSolicitudVisitaEquipoRepository repository;
    private final SvSolicitudVisitaEquipoMapper mapper;

    @Override
    public PagedResult<SvSolicitudVisitaEquipoDTO> search(PagedRequest<SvSolicitudVisitaEquipoFilter> pagedRequest) {
        SvSolicitudVisitaEquipoFilter filter = pagedRequest.getFilter();

        Pageable pageable = PagingUtil.toPageable(pagedRequest);

        Page<SvSolicitudVisitaEquipoEntity> page = repository.findAll(SvSolicitudVisitaEquipoSpecification.byFilter(filter), pageable);

        List<SvSolicitudVisitaEquipoDTO> dtos = page.getContent().stream().map(mapper::entityToDto).collect(Collectors.toList());

        return PagedResult.<SvSolicitudVisitaEquipoDTO> builder().count((int) page.getTotalElements()).offset((int) page.getPageable().getOffset())
                .limit(page.getSize()).page(page.getNumber()).totalPages(page.getTotalPages()).hasNext(page.hasNext()).hasPrevious(page.hasPrevious())
                .result(dtos).build();

    }

    @Override
    public SvSolicitudVisitaEquipoDTO findById(String id) {
        SvSolicitudVisitaEquipoEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("SvSolicitudVisitaEquipoEntity not found with id: " + id));
        return mapper.entityToDto(entity);
    }

    @Override
    public boolean existsById(String id) {
        return repository.existsById(id);
    }

    @Override
    public long countByFilter(SvSolicitudVisitaEquipoFilter filter) {
        return repository.count(SvSolicitudVisitaEquipoSpecification.byFilter(filter));
    }
}
