/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/query/imp/EntityQueryServiceImpl.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.query.imp;

import com.copeinca.apicopeincaprov.commons.utils.dto.PagingUtil;
import com.copeinca.apicopeincaprov.global.dtos.response.PagedRequest;
import com.copeinca.apicopeincaprov.global.dtos.response.PagedResult;
import com.copeinca.apicopeincaprov.modules.core.mappers.SvTrabajadorTipoActividadAltoRiesgoMapper;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvTrabajadorTipoActividadAltoRiesgoDTO;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.SvTrabajadorTipoActividadAltoRiesgoFilter;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvTrabajadorTipoActividadAltoRiesgoEntity;
import com.copeinca.apicopeincaprov.modules.core.repositories.jpa.ISvTrabajadorTipoActividadAltoRiesgoRepository;
import com.copeinca.apicopeincaprov.modules.core.service.query.ISvTrabajadorTipoActividadAltoRiesgoQueryService;
import com.copeinca.apicopeincaprov.modules.core.specification.SvTrabajadorTipoActividadAltoRiesgoSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
* Implementación del servicio de consultas para SvTrabajadorTipoActividadAltoRiesgoEntity
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class SvTrabajadorTipoActividadAltoRiesgoQueryServiceImpl implements ISvTrabajadorTipoActividadAltoRiesgoQueryService {

    private final ISvTrabajadorTipoActividadAltoRiesgoRepository repository;
    private final SvTrabajadorTipoActividadAltoRiesgoMapper mapper;

    @Override
    public PagedResult<SvTrabajadorTipoActividadAltoRiesgoDTO> search(PagedRequest<SvTrabajadorTipoActividadAltoRiesgoFilter> pagedRequest) {
        SvTrabajadorTipoActividadAltoRiesgoFilter filter = pagedRequest.getFilter();

        Pageable pageable = PagingUtil.toPageable(pagedRequest);

        Page<SvTrabajadorTipoActividadAltoRiesgoEntity> page = repository.findAll(SvTrabajadorTipoActividadAltoRiesgoSpecification.byFilter(filter), pageable);

        List<SvTrabajadorTipoActividadAltoRiesgoDTO> dtos = page.getContent().stream().map(mapper::entityToDto).collect(Collectors.toList());

        return PagedResult.<SvTrabajadorTipoActividadAltoRiesgoDTO> builder().count((int) page.getTotalElements()).offset((int) page.getPageable().getOffset())
                .limit(page.getSize()).page(page.getNumber()).totalPages(page.getTotalPages()).hasNext(page.hasNext()).hasPrevious(page.hasPrevious())
                .result(dtos).build();

    }

    @Override
    public SvTrabajadorTipoActividadAltoRiesgoDTO findById(String id) {
        SvTrabajadorTipoActividadAltoRiesgoEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("SvTrabajadorTipoActividadAltoRiesgoEntity not found with id: " + id));
        return mapper.entityToDto(entity);
    }

    @Override
    public boolean existsById(String id) {
        return repository.existsById(id);
    }

    @Override
    public long countByFilter(SvTrabajadorTipoActividadAltoRiesgoFilter filter) {
        return repository.count(SvTrabajadorTipoActividadAltoRiesgoSpecification.byFilter(filter));
    }
}
