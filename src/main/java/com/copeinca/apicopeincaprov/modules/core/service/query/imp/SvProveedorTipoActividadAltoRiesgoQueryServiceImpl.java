/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/query/imp/EntityQueryServiceImpl.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.query.imp;

import com.copeinca.apicopeincaprov.commons.utils.dto.PagingUtil;
import com.copeinca.apicopeincaprov.global.dtos.response.PagedRequest;
import com.copeinca.apicopeincaprov.global.dtos.response.PagedResult;
import com.copeinca.apicopeincaprov.modules.core.mappers.SvProveedorTipoActividadAltoRiesgoMapper;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvProveedorTipoActividadAltoRiesgoDTO;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.SvProveedorTipoActividadAltoRiesgoFilter;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvProveedorTipoActividadAltoRiesgoEntity;
import com.copeinca.apicopeincaprov.modules.core.repositories.jpa.ISvProveedorTipoActividadAltoRiesgoRepository;
import com.copeinca.apicopeincaprov.modules.core.service.query.ISvProveedorTipoActividadAltoRiesgoQueryService;
import com.copeinca.apicopeincaprov.modules.core.specification.SvProveedorTipoActividadAltoRiesgoSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
* Implementación del servicio de consultas para SvProveedorTipoActividadAltoRiesgoEntity
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class SvProveedorTipoActividadAltoRiesgoQueryServiceImpl implements ISvProveedorTipoActividadAltoRiesgoQueryService {

    private final ISvProveedorTipoActividadAltoRiesgoRepository repository;
    private final SvProveedorTipoActividadAltoRiesgoMapper mapper;

    @Override
    public PagedResult<SvProveedorTipoActividadAltoRiesgoDTO> search(PagedRequest<SvProveedorTipoActividadAltoRiesgoFilter> pagedRequest) {
        SvProveedorTipoActividadAltoRiesgoFilter filter = pagedRequest.getFilter();

        Pageable pageable = PagingUtil.toPageable(pagedRequest);

        Page<SvProveedorTipoActividadAltoRiesgoEntity> page = repository.findAll(SvProveedorTipoActividadAltoRiesgoSpecification.byFilter(filter), pageable);

        List<SvProveedorTipoActividadAltoRiesgoDTO> dtos = page.getContent().stream().map(mapper::entityToDto).collect(Collectors.toList());

        return PagedResult.<SvProveedorTipoActividadAltoRiesgoDTO> builder().count((int) page.getTotalElements()).offset((int) page.getPageable().getOffset())
                .limit(page.getSize()).page(page.getNumber()).totalPages(page.getTotalPages()).hasNext(page.hasNext()).hasPrevious(page.hasPrevious())
                .result(dtos).build();

    }

    @Override
    public SvProveedorTipoActividadAltoRiesgoDTO findById(String id) {
        SvProveedorTipoActividadAltoRiesgoEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("SvProveedorTipoActividadAltoRiesgoEntity not found with id: " + id));
        return mapper.entityToDto(entity);
    }

    @Override
    public boolean existsById(String id) {
        return repository.existsById(id);
    }

    @Override
    public long countByFilter(SvProveedorTipoActividadAltoRiesgoFilter filter) {
        return repository.count(SvProveedorTipoActividadAltoRiesgoSpecification.byFilter(filter));
    }
}
