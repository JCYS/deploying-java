/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/query/imp/EntityQueryServiceImpl.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.query.imp;

import com.copeinca.apicopeincaprov.commons.utils.dto.PagingUtil;
import com.copeinca.apicopeincaprov.global.dtos.response.PagedRequest;
import com.copeinca.apicopeincaprov.global.dtos.response.PagedResult;
import com.copeinca.apicopeincaprov.modules.core.mappers.SvZonaMapper;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvZonaDTO;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.SvZonaFilter;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvZonaEntity;
import com.copeinca.apicopeincaprov.modules.core.repositories.jpa.ISvZonaRepository;
import com.copeinca.apicopeincaprov.modules.core.service.query.ISvZonaQueryService;
import com.copeinca.apicopeincaprov.modules.core.specification.SvZonaSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
* Implementación del servicio de consultas para SvZonaEntity
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class SvZonaQueryServiceImpl implements ISvZonaQueryService {

    private final ISvZonaRepository repository;
    private final SvZonaMapper mapper;

    @Override
    public PagedResult<SvZonaDTO> search(PagedRequest<SvZonaFilter> pagedRequest) {
        SvZonaFilter filter = pagedRequest.getFilter();

        Pageable pageable = PagingUtil.toPageable(pagedRequest);

        Page<SvZonaEntity> page = repository.findAll(SvZonaSpecification.byFilter(filter), pageable);

        List<SvZonaDTO> dtos = page.getContent().stream().map(mapper::entityToDto).collect(Collectors.toList());

        return PagedResult.<SvZonaDTO> builder().count((int) page.getTotalElements()).offset((int) page.getPageable().getOffset()).limit(page.getSize())
                .page(page.getNumber()).totalPages(page.getTotalPages()).hasNext(page.hasNext()).hasPrevious(page.hasPrevious()).result(dtos).build();

    }

    @Override
    public SvZonaDTO findById(String id) {
        SvZonaEntity entity = repository.findById(id).orElseThrow(() -> new RuntimeException("SvZonaEntity not found with id: " + id));
        return mapper.entityToDto(entity);
    }

    @Override
    public boolean existsById(String id) {
        return repository.existsById(id);
    }

    @Override
    public long countByFilter(SvZonaFilter filter) {
        return repository.count(SvZonaSpecification.byFilter(filter));
    }
}
