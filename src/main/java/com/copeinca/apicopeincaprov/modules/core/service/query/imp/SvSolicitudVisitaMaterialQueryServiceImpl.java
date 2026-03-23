/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/query/imp/EntityQueryServiceImpl.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.query.imp;

import com.copeinca.apicopeincaprov.commons.utils.dto.PagingUtil;
import com.copeinca.apicopeincaprov.global.dtos.response.PagedRequest;
import com.copeinca.apicopeincaprov.global.dtos.response.PagedResult;
import com.copeinca.apicopeincaprov.modules.core.mappers.SvSolicitudVisitaMaterialMapper;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvSolicitudVisitaMaterialDTO;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.SvSolicitudVisitaMaterialFilter;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvSolicitudVisitaMaterialEntity;
import com.copeinca.apicopeincaprov.modules.core.repositories.jpa.ISvSolicitudVisitaMaterialRepository;
import com.copeinca.apicopeincaprov.modules.core.service.query.ISvSolicitudVisitaMaterialQueryService;
import com.copeinca.apicopeincaprov.modules.core.specification.SvSolicitudVisitaMaterialSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
* Implementación del servicio de consultas para SvSolicitudVisitaMaterialEntity
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class SvSolicitudVisitaMaterialQueryServiceImpl implements ISvSolicitudVisitaMaterialQueryService {

    private final ISvSolicitudVisitaMaterialRepository repository;
    private final SvSolicitudVisitaMaterialMapper mapper;

    @Override
    public PagedResult<SvSolicitudVisitaMaterialDTO> search(PagedRequest<SvSolicitudVisitaMaterialFilter> pagedRequest) {
        SvSolicitudVisitaMaterialFilter filter = pagedRequest.getFilter();

        Pageable pageable = PagingUtil.toPageable(pagedRequest);

        Page<SvSolicitudVisitaMaterialEntity> page = repository.findAll(SvSolicitudVisitaMaterialSpecification.byFilter(filter), pageable);

        List<SvSolicitudVisitaMaterialDTO> dtos = page.getContent().stream().map(mapper::entityToDto).collect(Collectors.toList());

        return PagedResult.<SvSolicitudVisitaMaterialDTO> builder().count((int) page.getTotalElements()).offset((int) page.getPageable().getOffset())
                .limit(page.getSize()).page(page.getNumber()).totalPages(page.getTotalPages()).hasNext(page.hasNext()).hasPrevious(page.hasPrevious())
                .result(dtos).build();

    }

    @Override
    public SvSolicitudVisitaMaterialDTO findById(String id) {
        SvSolicitudVisitaMaterialEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("SvSolicitudVisitaMaterialEntity not found with id: " + id));
        return mapper.entityToDto(entity);
    }

    @Override
    public boolean existsById(String id) {
        return repository.existsById(id);
    }

    @Override
    public long countByFilter(SvSolicitudVisitaMaterialFilter filter) {
        return repository.count(SvSolicitudVisitaMaterialSpecification.byFilter(filter));
    }
}
