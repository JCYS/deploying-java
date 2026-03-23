/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/query/imp/EntityQueryServiceImpl.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.query.imp;

import com.copeinca.apicopeincaprov.commons.utils.dto.PagingUtil;
import com.copeinca.apicopeincaprov.global.dtos.response.PagedRequest;
import com.copeinca.apicopeincaprov.global.dtos.response.PagedResult;
import com.copeinca.apicopeincaprov.modules.core.mappers.ViewAdjuntoReportMapper;
import com.copeinca.apicopeincaprov.modules.core.models.dto.ViewAdjuntoReportDTO;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.ViewAdjuntoReportFilter;
import com.copeinca.apicopeincaprov.modules.core.models.views.ViewAdjuntoReportView;
import com.copeinca.apicopeincaprov.modules.core.repositories.jpa.IViewAdjuntoReportRepository;
import com.copeinca.apicopeincaprov.modules.core.service.query.IViewAdjuntoReportQueryService;
import com.copeinca.apicopeincaprov.modules.core.specification.ViewAdjuntoReportSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
* Implementación del servicio de consultas para ViewAdjuntoReportView
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class ViewAdjuntoReportQueryServiceImpl implements IViewAdjuntoReportQueryService {

    private final IViewAdjuntoReportRepository repository;
    private final ViewAdjuntoReportMapper mapper;

    @Override
    public PagedResult<ViewAdjuntoReportDTO> search(PagedRequest<ViewAdjuntoReportFilter> pagedRequest) {
        ViewAdjuntoReportFilter filter = pagedRequest.getFilter();

        Pageable pageable = PagingUtil.toPageable(pagedRequest);

        Page<ViewAdjuntoReportView> page = repository.findAll(ViewAdjuntoReportSpecification.byFilter(filter), pageable);

        List<ViewAdjuntoReportDTO> dtos = page.getContent().stream().map(mapper::entityToDto).collect(Collectors.toList());

        return PagedResult.<ViewAdjuntoReportDTO> builder().count((int) page.getTotalElements()).offset((int) page.getPageable().getOffset())
                .limit(page.getSize()).page(page.getNumber()).totalPages(page.getTotalPages()).hasNext(page.hasNext()).hasPrevious(page.hasPrevious())
                .result(dtos).build();

    }

    @Override
    public ViewAdjuntoReportDTO findById(String id) {
        ViewAdjuntoReportView entity = repository.findById(id).orElseThrow(() -> new RuntimeException("ViewAdjuntoReportView not found with id: " + id));
        return mapper.entityToDto(entity);
    }

    @Override
    public boolean existsById(String id) {
        return repository.existsById(id);
    }

    @Override
    public long countByFilter(ViewAdjuntoReportFilter filter) {
        return repository.count(ViewAdjuntoReportSpecification.byFilter(filter));
    }
}
