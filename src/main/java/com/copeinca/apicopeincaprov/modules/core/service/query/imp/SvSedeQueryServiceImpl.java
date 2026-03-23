/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/query/imp/EntityQueryServiceImpl.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.query.imp;

import com.copeinca.apicopeincaprov.commons.utils.dto.PagingUtil;
import com.copeinca.apicopeincaprov.global.dtos.response.PagedRequest;
import com.copeinca.apicopeincaprov.global.dtos.response.PagedResult;
import com.copeinca.apicopeincaprov.modules.core.mappers.SvSedeMapper;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvSedeDTO;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.SvSedeFilter;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvSedeEntity;
import com.copeinca.apicopeincaprov.modules.core.repositories.jpa.ISvSedeRepository;
import com.copeinca.apicopeincaprov.modules.core.service.query.ISvSedeQueryService;
import com.copeinca.apicopeincaprov.modules.core.specification.SvSedeSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
* Implementación del servicio de consultas para SvSedeEntity
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class SvSedeQueryServiceImpl implements ISvSedeQueryService {

    private final ISvSedeRepository repository;
    private final SvSedeMapper mapper;

    @Override
    public PagedResult<SvSedeDTO> search(PagedRequest<SvSedeFilter> pagedRequest) {
        SvSedeFilter filter = pagedRequest.getFilter();

        Pageable pageable = PagingUtil.toPageable(pagedRequest);

        Page<SvSedeEntity> page = repository.findAll(SvSedeSpecification.byFilter(filter), pageable);

        List<SvSedeDTO> dtos = page.getContent().stream().map(mapper::entityToDto).collect(Collectors.toList());

        return PagedResult.<SvSedeDTO> builder().count((int) page.getTotalElements()).offset((int) page.getPageable().getOffset()).limit(page.getSize())
                .page(page.getNumber()).totalPages(page.getTotalPages()).hasNext(page.hasNext()).hasPrevious(page.hasPrevious()).result(dtos).build();

    }

    @Override
    public SvSedeDTO findById(String id) {
        SvSedeEntity entity = repository.findById(id).orElseThrow(() -> new RuntimeException("SvSedeEntity not found with id: " + id));
        return mapper.entityToDto(entity);
    }

    @Override
    public boolean existsById(String id) {
        return repository.existsById(id);
    }

    @Override
    public long countByFilter(SvSedeFilter filter) {
        return repository.count(SvSedeSpecification.byFilter(filter));
    }
}
