/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/query/imp/EntityQueryServiceImpl.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.query.imp;

import com.copeinca.apicopeincaprov.commons.utils.dto.PagingUtil;
import com.copeinca.apicopeincaprov.global.dtos.response.PagedRequest;
import com.copeinca.apicopeincaprov.global.dtos.response.PagedResult;
import com.copeinca.apicopeincaprov.modules.core.mappers.ViewSolicitudVisitaReportMapper;
import com.copeinca.apicopeincaprov.modules.core.models.dto.ViewSolicitudVisitaReportDTO;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.ViewSolicitudVisitaReportFilter;
import com.copeinca.apicopeincaprov.modules.core.models.views.ViewSolicitudVisitaReportView;
import com.copeinca.apicopeincaprov.modules.core.repositories.jpa.IViewSolicitudVisitaReportRepository;
import com.copeinca.apicopeincaprov.modules.core.service.query.IViewSolicitudVisitaReportQueryService;
import com.copeinca.apicopeincaprov.modules.core.specification.ViewSolicitudVisitaReportSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
* Implementación del servicio de consultas para ViewSolicitudVisitaReportView
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class ViewSolicitudVisitaReportQueryServiceImpl implements IViewSolicitudVisitaReportQueryService {

    private final IViewSolicitudVisitaReportRepository repository;
    private final ViewSolicitudVisitaReportMapper mapper;

    @Override
    public PagedResult<ViewSolicitudVisitaReportDTO> search(PagedRequest<ViewSolicitudVisitaReportFilter> pagedRequest) {
        ViewSolicitudVisitaReportFilter filter = pagedRequest.getFilter();

        Pageable pageable = PagingUtil.toPageable(pagedRequest);

        Page<ViewSolicitudVisitaReportView> page = repository.findAll(ViewSolicitudVisitaReportSpecification.byFilter(filter), pageable);

        List<ViewSolicitudVisitaReportDTO> dtos = page.getContent().stream().map(mapper::entityToDto).collect(Collectors.toList());

        return PagedResult.<ViewSolicitudVisitaReportDTO> builder().count((int) page.getTotalElements()).offset((int) page.getPageable().getOffset())
                .limit(page.getSize()).page(page.getNumber()).totalPages(page.getTotalPages()).hasNext(page.hasNext()).hasPrevious(page.hasPrevious())
                .result(dtos).build();

    }

    @Override
    public ViewSolicitudVisitaReportDTO findById(String id) {
        ViewSolicitudVisitaReportView entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("ViewSolicitudVisitaReportView not found with id: " + id));
        return mapper.entityToDto(entity);
    }

    @Override
    public boolean existsById(String id) {
        return repository.existsById(id);
    }

    @Override
    public long countByFilter(ViewSolicitudVisitaReportFilter filter) {
        return repository.count(ViewSolicitudVisitaReportSpecification.byFilter(filter));
    }
}
