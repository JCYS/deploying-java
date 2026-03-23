/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/query/imp/EntityQueryServiceImpl.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.query.imp;

import com.copeinca.apicopeincaprov.commons.utils.dto.PagingUtil;
import com.copeinca.apicopeincaprov.global.dtos.response.PagedRequest;
import com.copeinca.apicopeincaprov.global.dtos.response.PagedResult;
import com.copeinca.apicopeincaprov.modules.core.mappers.SvProveedorMapper;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvProveedorDTO;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.SvProveedorFilter;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvProveedorEntity;
import com.copeinca.apicopeincaprov.modules.core.repositories.jpa.ISvProveedorRepository;
import com.copeinca.apicopeincaprov.modules.core.service.query.ISvProveedorQueryService;
import com.copeinca.apicopeincaprov.modules.core.specification.SvProveedorSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
* Implementación del servicio de consultas para SvProveedorEntity
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class SvProveedorQueryServiceImpl implements ISvProveedorQueryService {

    private final ISvProveedorRepository repository;
    private final SvProveedorMapper mapper;

    @Override
    public PagedResult<SvProveedorDTO> search(PagedRequest<SvProveedorFilter> pagedRequest) {
        SvProveedorFilter filter = pagedRequest.getFilter();

        Pageable pageable = PagingUtil.toPageable(pagedRequest);

        Page<SvProveedorEntity> page = repository.findAll(SvProveedorSpecification.byFilter(filter), pageable);

        List<SvProveedorDTO> dtos = page.getContent().stream().map(mapper::entityToDto).collect(Collectors.toList());

        return PagedResult.<SvProveedorDTO> builder().count((int) page.getTotalElements()).offset((int) page.getPageable().getOffset()).limit(page.getSize())
                .page(page.getNumber()).totalPages(page.getTotalPages()).hasNext(page.hasNext()).hasPrevious(page.hasPrevious()).result(dtos).build();

    }

    @Override
    public SvProveedorDTO findById(String id) {
        SvProveedorEntity entity = repository.findById(id).orElseThrow(() -> new RuntimeException("SvProveedorEntity not found with id: " + id));
        return mapper.entityToDto(entity);
    }

    @Override
    public boolean existsById(String id) {
        return repository.existsById(id);
    }

    @Override
    public long countByFilter(SvProveedorFilter filter) {
        return repository.count(SvProveedorSpecification.byFilter(filter));
    }
}
