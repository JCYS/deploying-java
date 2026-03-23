/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/query/imp/EntityQueryServiceImpl.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.query.imp;

import com.copeinca.apicopeincaprov.commons.utils.dto.PagingUtil;
import com.copeinca.apicopeincaprov.global.dtos.response.PagedRequest;
import com.copeinca.apicopeincaprov.global.dtos.response.PagedResult;
import com.copeinca.apicopeincaprov.modules.core.mappers.SvProveedorSedeMapper;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvProveedorSedeDTO;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.SvProveedorSedeFilter;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvProveedorSedeEntity;
import com.copeinca.apicopeincaprov.modules.core.repositories.jpa.ISvProveedorSedeRepository;
import com.copeinca.apicopeincaprov.modules.core.service.query.ISvProveedorSedeQueryService;
import com.copeinca.apicopeincaprov.modules.core.specification.SvProveedorSedeSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
* Implementación del servicio de consultas para SvProveedorSedeEntity
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class SvProveedorSedeQueryServiceImpl implements ISvProveedorSedeQueryService {

    private final ISvProveedorSedeRepository repository;
    private final SvProveedorSedeMapper mapper;

    @Override
    public PagedResult<SvProveedorSedeDTO> search(PagedRequest<SvProveedorSedeFilter> pagedRequest) {
        SvProveedorSedeFilter filter = pagedRequest.getFilter();

        Pageable pageable = PagingUtil.toPageable(pagedRequest);

        Page<SvProveedorSedeEntity> page = repository.findAll(SvProveedorSedeSpecification.byFilter(filter), pageable);

        List<SvProveedorSedeDTO> dtos = page.getContent().stream().map(mapper::entityToDto).collect(Collectors.toList());

        return PagedResult.<SvProveedorSedeDTO> builder().count((int) page.getTotalElements()).offset((int) page.getPageable().getOffset())
                .limit(page.getSize()).page(page.getNumber()).totalPages(page.getTotalPages()).hasNext(page.hasNext()).hasPrevious(page.hasPrevious())
                .result(dtos).build();

    }

    @Override
    public SvProveedorSedeDTO findById(String id) {
        SvProveedorSedeEntity entity = repository.findById(id).orElseThrow(() -> new RuntimeException("SvProveedorSedeEntity not found with id: " + id));
        return mapper.entityToDto(entity);
    }

    @Override
    public boolean existsById(String id) {
        return repository.existsById(id);
    }

    @Override
    public long countByFilter(SvProveedorSedeFilter filter) {
        return repository.count(SvProveedorSedeSpecification.byFilter(filter));
    }
}
