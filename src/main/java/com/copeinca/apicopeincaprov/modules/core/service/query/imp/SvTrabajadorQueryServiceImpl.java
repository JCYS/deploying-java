/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/query/imp/EntityQueryServiceImpl.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.query.imp;

import com.copeinca.apicopeincaprov.commons.utils.dto.PagingUtil;
import com.copeinca.apicopeincaprov.global.dtos.response.PagedRequest;
import com.copeinca.apicopeincaprov.global.dtos.response.PagedResult;
import com.copeinca.apicopeincaprov.modules.core.mappers.SvTrabajadorMapper;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvTrabajadorDTO;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.SvTrabajadorFilter;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvTrabajadorEntity;
import com.copeinca.apicopeincaprov.modules.core.repositories.jpa.ISvTrabajadorRepository;
import com.copeinca.apicopeincaprov.modules.core.service.query.ISvTrabajadorQueryService;
import com.copeinca.apicopeincaprov.modules.core.specification.SvTrabajadorSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
* Implementación del servicio de consultas para SvTrabajadorEntity
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class SvTrabajadorQueryServiceImpl implements ISvTrabajadorQueryService {

    private final ISvTrabajadorRepository repository;
    private final SvTrabajadorMapper mapper;

    @Override
    public PagedResult<SvTrabajadorDTO> search(PagedRequest<SvTrabajadorFilter> pagedRequest) {
        SvTrabajadorFilter filter = pagedRequest.getFilter();

        Pageable pageable = PagingUtil.toPageable(pagedRequest);

        Page<SvTrabajadorEntity> page = repository.findAll(SvTrabajadorSpecification.byFilter(filter), pageable);

        List<SvTrabajadorDTO> dtos = page.getContent().stream().map(mapper::entityToDto).collect(Collectors.toList());

        return PagedResult.<SvTrabajadorDTO> builder().count((int) page.getTotalElements()).offset((int) page.getPageable().getOffset()).limit(page.getSize())
                .page(page.getNumber()).totalPages(page.getTotalPages()).hasNext(page.hasNext()).hasPrevious(page.hasPrevious()).result(dtos).build();

    }

    @Override
    public SvTrabajadorDTO findById(String id) {
        SvTrabajadorEntity entity = repository.findById(id).orElseThrow(() -> new RuntimeException("SvTrabajadorEntity not found with id: " + id));
        return mapper.entityToDto(entity);
    }

    @Override
    public boolean existsById(String id) {
        return repository.existsById(id);
    }

    @Override
    public long countByFilter(SvTrabajadorFilter filter) {
        return repository.count(SvTrabajadorSpecification.byFilter(filter));
    }
}
