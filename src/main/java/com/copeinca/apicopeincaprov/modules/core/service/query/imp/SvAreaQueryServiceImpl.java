/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/query/imp/EntityQueryServiceImpl.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.query.imp;

import com.copeinca.apicopeincaprov.commons.utils.dto.PagingUtil;
import com.copeinca.apicopeincaprov.global.dtos.response.PagedRequest;
import com.copeinca.apicopeincaprov.global.dtos.response.PagedResult;
import com.copeinca.apicopeincaprov.modules.core.mappers.SvAreaMapper;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvAreaDTO;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.SvAreaFilter;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvAreaEntity;
import com.copeinca.apicopeincaprov.modules.core.repositories.jpa.ISvAreaRepository;
import com.copeinca.apicopeincaprov.modules.core.service.query.ISvAreaQueryService;
import com.copeinca.apicopeincaprov.modules.core.specification.SvAreaSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
* Implementación del servicio de consultas para SvAreaEntity
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class SvAreaQueryServiceImpl implements ISvAreaQueryService {

    private final ISvAreaRepository repository;
    private final SvAreaMapper mapper;

    @Override
    public PagedResult<SvAreaDTO> search(PagedRequest<SvAreaFilter> pagedRequest) {
        SvAreaFilter filter = pagedRequest.getFilter();

        Pageable pageable = PagingUtil.toPageable(pagedRequest);

        Page<SvAreaEntity> page = repository.findAll(SvAreaSpecification.byFilter(filter), pageable);

        List<SvAreaDTO> dtos = page.getContent().stream().map(mapper::entityToDto).collect(Collectors.toList());

        return PagedResult.<SvAreaDTO> builder().count((int) page.getTotalElements()).offset((int) page.getPageable().getOffset()).limit(page.getSize())
                .page(page.getNumber()).totalPages(page.getTotalPages()).hasNext(page.hasNext()).hasPrevious(page.hasPrevious()).result(dtos).build();

    }

    @Override
    public SvAreaDTO findById(String id) {
        SvAreaEntity entity = repository.findById(id).orElseThrow(() -> new RuntimeException("SvAreaEntity not found with id: " + id));
        return mapper.entityToDto(entity);
    }

    @Override
    public boolean existsById(String id) {
        return repository.existsById(id);
    }

    @Override
    public long countByFilter(SvAreaFilter filter) {
        return repository.count(SvAreaSpecification.byFilter(filter));
    }
}
