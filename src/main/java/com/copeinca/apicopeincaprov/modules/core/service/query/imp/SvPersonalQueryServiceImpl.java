/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/query/imp/EntityQueryServiceImpl.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.query.imp;

import com.copeinca.apicopeincaprov.commons.utils.dto.PagingUtil;
import com.copeinca.apicopeincaprov.global.dtos.response.PagedRequest;
import com.copeinca.apicopeincaprov.global.dtos.response.PagedResult;
import com.copeinca.apicopeincaprov.modules.core.mappers.SvPersonalMapper;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvPersonalDTO;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.SvPersonalFilter;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvPersonalEntity;
import com.copeinca.apicopeincaprov.modules.core.repositories.jpa.ISvPersonalRepository;
import com.copeinca.apicopeincaprov.modules.core.service.query.ISvPersonalQueryService;
import com.copeinca.apicopeincaprov.modules.core.specification.SvPersonalSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
* Implementación del servicio de consultas para SvPersonalEntity
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class SvPersonalQueryServiceImpl implements ISvPersonalQueryService {

    private final ISvPersonalRepository repository;
    private final SvPersonalMapper mapper;

    @Override
    public PagedResult<SvPersonalDTO> search(PagedRequest<SvPersonalFilter> pagedRequest) {
        SvPersonalFilter filter = pagedRequest.getFilter();

        Pageable pageable = PagingUtil.toPageable(pagedRequest);

        Page<SvPersonalEntity> page = repository.findAll(SvPersonalSpecification.byFilter(filter), pageable);

        List<SvPersonalDTO> dtos = page.getContent().stream().map(mapper::entityToDto).collect(Collectors.toList());

        return PagedResult.<SvPersonalDTO> builder().count((int) page.getTotalElements()).offset((int) page.getPageable().getOffset()).limit(page.getSize())
                .page(page.getNumber()).totalPages(page.getTotalPages()).hasNext(page.hasNext()).hasPrevious(page.hasPrevious()).result(dtos).build();

    }

    @Override
    public SvPersonalDTO findById(String id) {
        SvPersonalEntity entity = repository.findById(id).orElseThrow(() -> new RuntimeException("SvPersonalEntity not found with id: " + id));
        return mapper.entityToDto(entity);
    }

    @Override
    public boolean existsById(String id) {
        return repository.existsById(id);
    }

    @Override
    public long countByFilter(SvPersonalFilter filter) {
        return repository.count(SvPersonalSpecification.byFilter(filter));
    }
}
