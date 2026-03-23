/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/query/imp/EntityQueryServiceImpl.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.query.imp;

import com.copeinca.apicopeincaprov.commons.utils.dto.PagingUtil;
import com.copeinca.apicopeincaprov.global.dtos.response.PagedRequest;
import com.copeinca.apicopeincaprov.global.dtos.response.PagedResult;
import com.copeinca.apicopeincaprov.modules.core.mappers.SvTipoActividadAltoRiesgoMapper;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvTipoActividadAltoRiesgoDTO;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.SvTipoActividadAltoRiesgoFilter;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvTipoActividadAltoRiesgoEntity;
import com.copeinca.apicopeincaprov.modules.core.repositories.jpa.ISvTipoActividadAltoRiesgoRepository;
import com.copeinca.apicopeincaprov.modules.core.service.query.ISvTipoActividadAltoRiesgoQueryService;
import com.copeinca.apicopeincaprov.modules.core.specification.SvTipoActividadAltoRiesgoSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
* Implementación del servicio de consultas para SvTipoActividadAltoRiesgoEntity
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class SvTipoActividadAltoRiesgoQueryServiceImpl implements ISvTipoActividadAltoRiesgoQueryService {

    private final ISvTipoActividadAltoRiesgoRepository repository;
    private final SvTipoActividadAltoRiesgoMapper mapper;

    @Override
    public PagedResult<SvTipoActividadAltoRiesgoDTO> search(PagedRequest<SvTipoActividadAltoRiesgoFilter> pagedRequest) {
        SvTipoActividadAltoRiesgoFilter filter = pagedRequest.getFilter();

        Pageable pageable = PagingUtil.toPageable(pagedRequest);

        Page<SvTipoActividadAltoRiesgoEntity> page = repository.findAll(SvTipoActividadAltoRiesgoSpecification.byFilter(filter), pageable);

        List<SvTipoActividadAltoRiesgoDTO> dtos = page.getContent().stream().map(mapper::entityToDto).collect(Collectors.toList());

        return PagedResult.<SvTipoActividadAltoRiesgoDTO> builder().count((int) page.getTotalElements()).offset((int) page.getPageable().getOffset())
                .limit(page.getSize()).page(page.getNumber()).totalPages(page.getTotalPages()).hasNext(page.hasNext()).hasPrevious(page.hasPrevious())
                .result(dtos).build();

    }

    @Override
    public SvTipoActividadAltoRiesgoDTO findById(String id) {
        SvTipoActividadAltoRiesgoEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("SvTipoActividadAltoRiesgoEntity not found with id: " + id));
        return mapper.entityToDto(entity);
    }

    @Override
    public boolean existsById(String id) {
        return repository.existsById(id);
    }

    @Override
    public long countByFilter(SvTipoActividadAltoRiesgoFilter filter) {
        return repository.count(SvTipoActividadAltoRiesgoSpecification.byFilter(filter));
    }
}
