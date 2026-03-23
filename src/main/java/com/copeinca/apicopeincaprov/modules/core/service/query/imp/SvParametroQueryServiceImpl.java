/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/query/imp/EntityQueryServiceImpl.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.query.imp;

import com.copeinca.apicopeincaprov.commons.utils.dto.PagingUtil;
import com.copeinca.apicopeincaprov.global.dtos.response.PagedRequest;
import com.copeinca.apicopeincaprov.global.dtos.response.PagedResult;
import com.copeinca.apicopeincaprov.modules.core.mappers.SvParametroMapper;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvParametroDTO;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.SvParametroFilter;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvParametroEntity;
import com.copeinca.apicopeincaprov.modules.core.repositories.jpa.ISvParametroRepository;
import com.copeinca.apicopeincaprov.modules.core.service.query.ISvParametroQueryService;
import com.copeinca.apicopeincaprov.modules.core.specification.SvParametroSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
* Implementación del servicio de consultas para SvParametroEntity
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class SvParametroQueryServiceImpl implements ISvParametroQueryService {

    private final ISvParametroRepository repository;
    private final SvParametroMapper mapper;

    @Override
    public PagedResult<SvParametroDTO> search(PagedRequest<SvParametroFilter> pagedRequest) {
        SvParametroFilter filter = pagedRequest.getFilter();

        Pageable pageable = PagingUtil.toPageable(pagedRequest);

        Page<SvParametroEntity> page = repository.findAll(SvParametroSpecification.byFilter(filter), pageable);

        List<SvParametroDTO> dtos = page.getContent().stream().map(mapper::entityToDto).collect(Collectors.toList());

        return PagedResult.<SvParametroDTO> builder().count((int) page.getTotalElements()).offset((int) page.getPageable().getOffset()).limit(page.getSize())
                .page(page.getNumber()).totalPages(page.getTotalPages()).hasNext(page.hasNext()).hasPrevious(page.hasPrevious()).result(dtos).build();

    }

    @Override
    public SvParametroDTO findById(String id) {
        SvParametroEntity entity = repository.findById(id).orElseThrow(() -> new RuntimeException("SvParametroEntity not found with id: " + id));
        return mapper.entityToDto(entity);
    }

    @Override
    public boolean existsById(String id) {
        return repository.existsById(id);
    }

    @Override
    public long countByFilter(SvParametroFilter filter) {
        return repository.count(SvParametroSpecification.byFilter(filter));
    }
}
