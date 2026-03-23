/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/query/imp/EntityQueryServiceImpl.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.query.imp;

import com.copeinca.apicopeincaprov.commons.utils.dto.PagingUtil;
import com.copeinca.apicopeincaprov.global.dtos.response.PagedRequest;
import com.copeinca.apicopeincaprov.global.dtos.response.PagedResult;
import com.copeinca.apicopeincaprov.modules.core.mappers.SvAdjuntoMapper;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvAdjuntoDTO;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.SvAdjuntoFilter;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvAdjuntoEntity;
import com.copeinca.apicopeincaprov.modules.core.repositories.jpa.ISvAdjuntoRepository;
import com.copeinca.apicopeincaprov.modules.core.service.query.ISvAdjuntoQueryService;
import com.copeinca.apicopeincaprov.modules.core.specification.SvAdjuntoSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
* Implementación del servicio de consultas para SvAdjuntoEntity
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class SvAdjuntoQueryServiceImpl implements ISvAdjuntoQueryService {

    private final ISvAdjuntoRepository repository;
    private final SvAdjuntoMapper mapper;

    @Override
    public PagedResult<SvAdjuntoDTO> search(PagedRequest<SvAdjuntoFilter> pagedRequest) {
        SvAdjuntoFilter filter = pagedRequest.getFilter();

        Pageable pageable = PagingUtil.toPageable(pagedRequest);

        Page<SvAdjuntoEntity> page = repository.findAll(SvAdjuntoSpecification.byFilter(filter), pageable);

        List<SvAdjuntoDTO> dtos = page.getContent().stream().map(mapper::entityToDto).collect(Collectors.toList());

        return PagedResult.<SvAdjuntoDTO> builder().count((int) page.getTotalElements()).offset((int) page.getPageable().getOffset()).limit(page.getSize())
                .page(page.getNumber()).totalPages(page.getTotalPages()).hasNext(page.hasNext()).hasPrevious(page.hasPrevious()).result(dtos).build();

    }

    @Override
    public SvAdjuntoDTO findById(String id) {
        SvAdjuntoEntity entity = repository.findById(id).orElseThrow(() -> new RuntimeException("SvAdjuntoEntity not found with id: " + id));
        return mapper.entityToDto(entity);
    }

    @Override
    public boolean existsById(String id) {
        return repository.existsById(id);
    }

    @Override
    public long countByFilter(SvAdjuntoFilter filter) {
        return repository.count(SvAdjuntoSpecification.byFilter(filter));
    }
}
