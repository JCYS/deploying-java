/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/query/imp/EntityQueryServiceImpl.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.query.imp;

import com.copeinca.apicopeincaprov.commons.utils.dto.PagingUtil;
import com.copeinca.apicopeincaprov.global.dtos.response.PagedRequest;
import com.copeinca.apicopeincaprov.global.dtos.response.PagedResult;
import com.copeinca.apicopeincaprov.modules.core.mappers.SvEstadoCalidadAmbientalMapper;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvEstadoCalidadAmbientalDTO;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.SvEstadoCalidadAmbientalFilter;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvEstadoCalidadAmbientalEntity;
import com.copeinca.apicopeincaprov.modules.core.repositories.jpa.ISvEstadoCalidadAmbientalRepository;
import com.copeinca.apicopeincaprov.modules.core.service.query.ISvEstadoCalidadAmbientalQueryService;
import com.copeinca.apicopeincaprov.modules.core.specification.SvEstadoCalidadAmbientalSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
* Implementación del servicio de consultas para SvEstadoCalidadAmbientalEntity
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class SvEstadoCalidadAmbientalQueryServiceImpl implements ISvEstadoCalidadAmbientalQueryService {

    private final ISvEstadoCalidadAmbientalRepository repository;
    private final SvEstadoCalidadAmbientalMapper mapper;

    @Override
    public PagedResult<SvEstadoCalidadAmbientalDTO> search(PagedRequest<SvEstadoCalidadAmbientalFilter> pagedRequest) {
        SvEstadoCalidadAmbientalFilter filter = pagedRequest.getFilter();

        Pageable pageable = PagingUtil.toPageable(pagedRequest);

        Page<SvEstadoCalidadAmbientalEntity> page = repository.findAll(SvEstadoCalidadAmbientalSpecification.byFilter(filter), pageable);

        List<SvEstadoCalidadAmbientalDTO> dtos = page.getContent().stream().map(mapper::entityToDto).collect(Collectors.toList());

        return PagedResult.<SvEstadoCalidadAmbientalDTO> builder().count((int) page.getTotalElements()).offset((int) page.getPageable().getOffset())
                .limit(page.getSize()).page(page.getNumber()).totalPages(page.getTotalPages()).hasNext(page.hasNext()).hasPrevious(page.hasPrevious())
                .result(dtos).build();

    }

    @Override
    public SvEstadoCalidadAmbientalDTO findById(String id) {
        SvEstadoCalidadAmbientalEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("SvEstadoCalidadAmbientalEntity not found with id: " + id));
        return mapper.entityToDto(entity);
    }

    @Override
    public boolean existsById(String id) {
        return repository.existsById(id);
    }

    @Override
    public long countByFilter(SvEstadoCalidadAmbientalFilter filter) {
        return repository.count(SvEstadoCalidadAmbientalSpecification.byFilter(filter));
    }
}
