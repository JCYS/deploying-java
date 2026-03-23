/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/query/imp/EntityQueryServiceImpl.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.query.imp;

import com.copeinca.apicopeincaprov.commons.utils.dto.PagingUtil;
import com.copeinca.apicopeincaprov.global.dtos.response.PagedRequest;
import com.copeinca.apicopeincaprov.global.dtos.response.PagedResult;
import com.copeinca.apicopeincaprov.modules.core.mappers.SvEstadoSolicitudVisitaMapper;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvEstadoSolicitudVisitaDTO;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.SvEstadoSolicitudVisitaFilter;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvEstadoSolicitudVisitaEntity;
import com.copeinca.apicopeincaprov.modules.core.repositories.jpa.ISvEstadoSolicitudVisitaRepository;
import com.copeinca.apicopeincaprov.modules.core.service.query.ISvEstadoSolicitudVisitaQueryService;
import com.copeinca.apicopeincaprov.modules.core.specification.SvEstadoSolicitudVisitaSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
* Implementación del servicio de consultas para SvEstadoSolicitudVisitaEntity
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class SvEstadoSolicitudVisitaQueryServiceImpl implements ISvEstadoSolicitudVisitaQueryService {

    private final ISvEstadoSolicitudVisitaRepository repository;
    private final SvEstadoSolicitudVisitaMapper mapper;

    @Override
    public PagedResult<SvEstadoSolicitudVisitaDTO> search(PagedRequest<SvEstadoSolicitudVisitaFilter> pagedRequest) {
        SvEstadoSolicitudVisitaFilter filter = pagedRequest.getFilter();

        Pageable pageable = PagingUtil.toPageable(pagedRequest);

        Page<SvEstadoSolicitudVisitaEntity> page = repository.findAll(SvEstadoSolicitudVisitaSpecification.byFilter(filter), pageable);

        List<SvEstadoSolicitudVisitaDTO> dtos = page.getContent().stream().map(mapper::entityToDto).collect(Collectors.toList());

        return PagedResult.<SvEstadoSolicitudVisitaDTO> builder().count((int) page.getTotalElements()).offset((int) page.getPageable().getOffset())
                .limit(page.getSize()).page(page.getNumber()).totalPages(page.getTotalPages()).hasNext(page.hasNext()).hasPrevious(page.hasPrevious())
                .result(dtos).build();

    }

    @Override
    public SvEstadoSolicitudVisitaDTO findById(String id) {
        SvEstadoSolicitudVisitaEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("SvEstadoSolicitudVisitaEntity not found with id: " + id));
        return mapper.entityToDto(entity);
    }

    @Override
    public boolean existsById(String id) {
        return repository.existsById(id);
    }

    @Override
    public long countByFilter(SvEstadoSolicitudVisitaFilter filter) {
        return repository.count(SvEstadoSolicitudVisitaSpecification.byFilter(filter));
    }
}
