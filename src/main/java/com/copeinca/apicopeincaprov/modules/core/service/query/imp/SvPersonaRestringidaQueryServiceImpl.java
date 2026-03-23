/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/query/imp/EntityQueryServiceImpl.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.query.imp;

import com.copeinca.apicopeincaprov.commons.utils.dto.PagingUtil;
import com.copeinca.apicopeincaprov.global.dtos.response.PagedRequest;
import com.copeinca.apicopeincaprov.global.dtos.response.PagedResult;
import com.copeinca.apicopeincaprov.modules.core.mappers.SvPersonaRestringidaMapper;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvPersonaRestringidaDTO;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.SvPersonaRestringidaFilter;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvPersonaRestringidaEntity;
import com.copeinca.apicopeincaprov.modules.core.repositories.jpa.ISvPersonaRestringidaRepository;
import com.copeinca.apicopeincaprov.modules.core.service.query.ISvPersonaRestringidaQueryService;
import com.copeinca.apicopeincaprov.modules.core.specification.SvPersonaRestringidaSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
* Implementación del servicio de consultas para SvPersonaRestringidaEntity
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class SvPersonaRestringidaQueryServiceImpl implements ISvPersonaRestringidaQueryService {

    private final ISvPersonaRestringidaRepository repository;
    private final SvPersonaRestringidaMapper mapper;

    @Override
    public PagedResult<SvPersonaRestringidaDTO> search(PagedRequest<SvPersonaRestringidaFilter> pagedRequest) {
        SvPersonaRestringidaFilter filter = pagedRequest.getFilter();

        Pageable pageable = PagingUtil.toPageable(pagedRequest);

        Page<SvPersonaRestringidaEntity> page = repository.findAll(SvPersonaRestringidaSpecification.byFilter(filter), pageable);

        List<SvPersonaRestringidaDTO> dtos = page.getContent().stream().map(mapper::entityToDto).collect(Collectors.toList());

        return PagedResult.<SvPersonaRestringidaDTO> builder().count((int) page.getTotalElements()).offset((int) page.getPageable().getOffset())
                .limit(page.getSize()).page(page.getNumber()).totalPages(page.getTotalPages()).hasNext(page.hasNext()).hasPrevious(page.hasPrevious())
                .result(dtos).build();

    }

    @Override
    public SvPersonaRestringidaDTO findById(String id) {
        SvPersonaRestringidaEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("SvPersonaRestringidaEntity not found with id: " + id));
        return mapper.entityToDto(entity);
    }

    @Override
    public boolean existsById(String id) {
        return repository.existsById(id);
    }

    @Override
    public long countByFilter(SvPersonaRestringidaFilter filter) {
        return repository.count(SvPersonaRestringidaSpecification.byFilter(filter));
    }
}
