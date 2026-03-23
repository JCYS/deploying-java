/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/query/imp/EntityQueryServiceImpl.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.query.imp;

import com.copeinca.apicopeincaprov.commons.utils.dto.PagingUtil;
import com.copeinca.apicopeincaprov.global.dtos.response.PagedRequest;
import com.copeinca.apicopeincaprov.global.dtos.response.PagedResult;
import com.copeinca.apicopeincaprov.modules.core.mappers.SvRolMapper;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvRolDTO;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.SvRolFilter;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvRolEntity;
import com.copeinca.apicopeincaprov.modules.core.repositories.jpa.ISvRolRepository;
import com.copeinca.apicopeincaprov.modules.core.service.query.ISvRolQueryService;
import com.copeinca.apicopeincaprov.modules.core.specification.SvRolSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
* Implementación del servicio de consultas para SvRolEntity
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class SvRolQueryServiceImpl implements ISvRolQueryService {

    private final ISvRolRepository repository;
    private final SvRolMapper mapper;

    @Override
    public PagedResult<SvRolDTO> search(PagedRequest<SvRolFilter> pagedRequest) {
        SvRolFilter filter = pagedRequest.getFilter();

        Pageable pageable = PagingUtil.toPageable(pagedRequest);

        Page<SvRolEntity> page = repository.findAll(SvRolSpecification.byFilter(filter), pageable);

        List<SvRolDTO> dtos = page.getContent().stream().map(mapper::entityToDto).collect(Collectors.toList());

        return PagedResult.<SvRolDTO> builder().count((int) page.getTotalElements()).offset((int) page.getPageable().getOffset()).limit(page.getSize())
                .page(page.getNumber()).totalPages(page.getTotalPages()).hasNext(page.hasNext()).hasPrevious(page.hasPrevious()).result(dtos).build();

    }

    @Override
    public SvRolDTO findById(String id) {
        SvRolEntity entity = repository.findById(id).orElseThrow(() -> new RuntimeException("SvRolEntity not found with id: " + id));
        return mapper.entityToDto(entity);
    }

    @Override
    public boolean existsById(String id) {
        return repository.existsById(id);
    }

    @Override
    public long countByFilter(SvRolFilter filter) {
        return repository.count(SvRolSpecification.byFilter(filter));
    }
}
