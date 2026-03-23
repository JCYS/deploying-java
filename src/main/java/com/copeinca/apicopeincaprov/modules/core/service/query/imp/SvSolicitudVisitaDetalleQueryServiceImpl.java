/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/query/imp/EntityQueryServiceImpl.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.query.imp;

import com.copeinca.apicopeincaprov.commons.utils.dto.PagingUtil;
import com.copeinca.apicopeincaprov.global.dtos.response.PagedRequest;
import com.copeinca.apicopeincaprov.global.dtos.response.PagedResult;
import com.copeinca.apicopeincaprov.modules.core.mappers.SvSolicitudVisitaDetalleMapper;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvSolicitudVisitaDetalleDTO;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.SvSolicitudVisitaDetalleFilter;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvSolicitudVisitaDetalleEntity;
import com.copeinca.apicopeincaprov.modules.core.repositories.jpa.ISvSolicitudVisitaDetalleRepository;
import com.copeinca.apicopeincaprov.modules.core.service.query.ISvSolicitudVisitaDetalleQueryService;
import com.copeinca.apicopeincaprov.modules.core.specification.SvSolicitudVisitaDetalleSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
* Implementación del servicio de consultas para SvSolicitudVisitaDetalleEntity
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class SvSolicitudVisitaDetalleQueryServiceImpl implements ISvSolicitudVisitaDetalleQueryService {

    private final ISvSolicitudVisitaDetalleRepository repository;
    private final SvSolicitudVisitaDetalleMapper mapper;

    @Override
    public PagedResult<SvSolicitudVisitaDetalleDTO> search(PagedRequest<SvSolicitudVisitaDetalleFilter> pagedRequest) {
        SvSolicitudVisitaDetalleFilter filter = pagedRequest.getFilter();

        Pageable pageable = PagingUtil.toPageable(pagedRequest);

        Page<SvSolicitudVisitaDetalleEntity> page = repository.findAll(SvSolicitudVisitaDetalleSpecification.byFilter(filter), pageable);

        List<SvSolicitudVisitaDetalleDTO> dtos = page.getContent().stream().map(mapper::entityToDto).collect(Collectors.toList());

        return PagedResult.<SvSolicitudVisitaDetalleDTO> builder().count((int) page.getTotalElements()).offset((int) page.getPageable().getOffset())
                .limit(page.getSize()).page(page.getNumber()).totalPages(page.getTotalPages()).hasNext(page.hasNext()).hasPrevious(page.hasPrevious())
                .result(dtos).build();

    }

    @Override
    public SvSolicitudVisitaDetalleDTO findById(String id) {
        SvSolicitudVisitaDetalleEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("SvSolicitudVisitaDetalleEntity not found with id: " + id));
        return mapper.entityToDto(entity);
    }

    @Override
    public boolean existsById(String id) {
        return repository.existsById(id);
    }

    @Override
    public long countByFilter(SvSolicitudVisitaDetalleFilter filter) {
        return repository.count(SvSolicitudVisitaDetalleSpecification.byFilter(filter));
    }
}
