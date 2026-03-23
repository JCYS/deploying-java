/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/query/imp/EntityQueryServiceImpl.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.query.imp;

import com.copeinca.apicopeincaprov.commons.utils.dto.PagingUtil;
import com.copeinca.apicopeincaprov.global.dtos.response.PagedRequest;
import com.copeinca.apicopeincaprov.global.dtos.response.PagedResult;
import com.copeinca.apicopeincaprov.modules.core.mappers.SvPersonalSedeMapper;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvPersonalSedeDTO;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.SvPersonalSedeFilter;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvPersonalSedeEntity;
import com.copeinca.apicopeincaprov.modules.core.repositories.jpa.ISvPersonalSedeRepository;
import com.copeinca.apicopeincaprov.modules.core.service.query.ISvPersonalSedeQueryService;
import com.copeinca.apicopeincaprov.modules.core.specification.SvPersonalSedeSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
* Implementación del servicio de consultas para SvPersonalSedeEntity
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class SvPersonalSedeQueryServiceImpl implements ISvPersonalSedeQueryService {

    private final ISvPersonalSedeRepository repository;
    private final SvPersonalSedeMapper mapper;

    @Override
    public PagedResult<SvPersonalSedeDTO> search(PagedRequest<SvPersonalSedeFilter> pagedRequest) {
        SvPersonalSedeFilter filter = pagedRequest.getFilter();

        Pageable pageable = PagingUtil.toPageable(pagedRequest);

        Page<SvPersonalSedeEntity> page = repository.findAll(SvPersonalSedeSpecification.byFilter(filter), pageable);

        List<SvPersonalSedeDTO> dtos = page.getContent().stream().map(mapper::entityToDto).collect(Collectors.toList());

        return PagedResult.<SvPersonalSedeDTO> builder().count((int) page.getTotalElements()).offset((int) page.getPageable().getOffset()).limit(page.getSize())
                .page(page.getNumber()).totalPages(page.getTotalPages()).hasNext(page.hasNext()).hasPrevious(page.hasPrevious()).result(dtos).build();

    }

    @Override
    public SvPersonalSedeDTO findById(String id) {
        SvPersonalSedeEntity entity = repository.findById(id).orElseThrow(() -> new RuntimeException("SvPersonalSedeEntity not found with id: " + id));
        return mapper.entityToDto(entity);
    }

    @Override
    public boolean existsById(String id) {
        return repository.existsById(id);
    }

    @Override
    public long countByFilter(SvPersonalSedeFilter filter) {
        return repository.count(SvPersonalSedeSpecification.byFilter(filter));
    }
}
