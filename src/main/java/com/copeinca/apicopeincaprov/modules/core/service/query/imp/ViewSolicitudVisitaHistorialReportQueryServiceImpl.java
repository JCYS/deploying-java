/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/query/imp/EntityQueryServiceImpl.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.query.imp;

import com.copeinca.apicopeincaprov.commons.utils.dto.PagingUtil;
import com.copeinca.apicopeincaprov.global.dtos.response.PagedRequest;
import com.copeinca.apicopeincaprov.global.dtos.response.PagedResult;
import com.copeinca.apicopeincaprov.modules.core.mappers.ViewSolicitudVisitaHistorialReportMapper;
import com.copeinca.apicopeincaprov.modules.core.models.dto.ViewSolicitudVisitaHistorialReportDTO;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.ViewSolicitudVisitaHistorialReportFilter;
import com.copeinca.apicopeincaprov.modules.core.models.views.ViewSolicitudVisitaHistorialReportView;
import com.copeinca.apicopeincaprov.modules.core.repositories.jpa.IViewSolicitudVisitaHistorialReportRepository;
import com.copeinca.apicopeincaprov.modules.core.service.query.IViewSolicitudVisitaHistorialReportQueryService;
import com.copeinca.apicopeincaprov.modules.core.specification.ViewSolicitudVisitaHistorialReportSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
* Implementación del servicio de consultas para ViewSolicitudVisitaHistorialReportView
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class ViewSolicitudVisitaHistorialReportQueryServiceImpl implements IViewSolicitudVisitaHistorialReportQueryService {

    private final IViewSolicitudVisitaHistorialReportRepository repository;
    private final ViewSolicitudVisitaHistorialReportMapper mapper;

    @Override
    public PagedResult<ViewSolicitudVisitaHistorialReportDTO> search(PagedRequest<ViewSolicitudVisitaHistorialReportFilter> pagedRequest) {
        ViewSolicitudVisitaHistorialReportFilter filter = pagedRequest.getFilter();

        Pageable pageable = PagingUtil.toPageable(pagedRequest);

        Page<ViewSolicitudVisitaHistorialReportView> page = repository.findAll(ViewSolicitudVisitaHistorialReportSpecification.byFilter(filter), pageable);

        List<ViewSolicitudVisitaHistorialReportDTO> dtos = page.getContent().stream().map(mapper::entityToDto).collect(Collectors.toList());

        return PagedResult.<ViewSolicitudVisitaHistorialReportDTO> builder().count((int) page.getTotalElements()).offset((int) page.getPageable().getOffset())
                .limit(page.getSize()).page(page.getNumber()).totalPages(page.getTotalPages()).hasNext(page.hasNext()).hasPrevious(page.hasPrevious())
                .result(dtos).build();

    }

    @Override
    public ViewSolicitudVisitaHistorialReportDTO findById(String id) {
        ViewSolicitudVisitaHistorialReportView entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("ViewSolicitudVisitaHistorialReportView not found with id: " + id));
        return mapper.entityToDto(entity);
    }

    @Override
    public boolean existsById(String id) {
        return repository.existsById(id);
    }

    @Override
    public long countByFilter(ViewSolicitudVisitaHistorialReportFilter filter) {
        return repository.count(ViewSolicitudVisitaHistorialReportSpecification.byFilter(filter));
    }
}
