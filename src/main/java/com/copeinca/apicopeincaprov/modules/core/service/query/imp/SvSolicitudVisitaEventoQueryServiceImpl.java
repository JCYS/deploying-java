/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/query/imp/EntityQueryServiceImpl.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.query.imp;

import com.copeinca.apicopeincaprov.commons.utils.dto.PagingUtil;
import com.copeinca.apicopeincaprov.global.dtos.response.PagedRequest;
import com.copeinca.apicopeincaprov.global.dtos.response.PagedResult;
import com.copeinca.apicopeincaprov.modules.core.mappers.SvSolicitudVisitaEventoMapper;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvSolicitudVisitaEventoDTO;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.SvSolicitudVisitaEventoFilter;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvSolicitudVisitaEventoEntity;
import com.copeinca.apicopeincaprov.modules.core.repositories.jpa.ISvSolicitudVisitaEventoRepository;
import com.copeinca.apicopeincaprov.modules.core.service.query.ISvSolicitudVisitaEventoQueryService;
import com.copeinca.apicopeincaprov.modules.core.specification.SvSolicitudVisitaEventoSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
* Implementación del servicio de consultas para SvSolicitudVisitaEventoEntity
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class SvSolicitudVisitaEventoQueryServiceImpl implements ISvSolicitudVisitaEventoQueryService {

    private final ISvSolicitudVisitaEventoRepository repository;
    private final SvSolicitudVisitaEventoMapper mapper;

    @Override
    public PagedResult<SvSolicitudVisitaEventoDTO> search(PagedRequest<SvSolicitudVisitaEventoFilter> pagedRequest) {
        SvSolicitudVisitaEventoFilter filter = pagedRequest.getFilter();

        Pageable pageable = PagingUtil.toPageable(pagedRequest);

        Page<SvSolicitudVisitaEventoEntity> page = repository.findAll(SvSolicitudVisitaEventoSpecification.byFilter(filter), pageable);

        List<SvSolicitudVisitaEventoDTO> dtos = page.getContent().stream().map(mapper::entityToDto).collect(Collectors.toList());

        return PagedResult.<SvSolicitudVisitaEventoDTO> builder().count((int) page.getTotalElements()).offset((int) page.getPageable().getOffset())
                .limit(page.getSize()).page(page.getNumber()).totalPages(page.getTotalPages()).hasNext(page.hasNext()).hasPrevious(page.hasPrevious())
                .result(dtos).build();

    }

    @Override
    public SvSolicitudVisitaEventoDTO findById(String id) {
        SvSolicitudVisitaEventoEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("SvSolicitudVisitaEventoEntity not found with id: " + id));
        return mapper.entityToDto(entity);
    }

    @Override
    public boolean existsById(String id) {
        return repository.existsById(id);
    }

    @Override
    public long countByFilter(SvSolicitudVisitaEventoFilter filter) {
        return repository.count(SvSolicitudVisitaEventoSpecification.byFilter(filter));
    }
}
