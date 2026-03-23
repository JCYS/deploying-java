/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/query/imp/EntityQueryServiceImpl.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.query.imp;

import com.copeinca.apicopeincaprov.commons.utils.dto.PagingUtil;
import com.copeinca.apicopeincaprov.global.dtos.response.PagedRequest;
import com.copeinca.apicopeincaprov.global.dtos.response.PagedResult;
import com.copeinca.apicopeincaprov.modules.core.mappers.ViewTrabajadorReportMapper;
import com.copeinca.apicopeincaprov.modules.core.models.dto.ViewTrabajadorReportDTO;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.ViewTrabajadorReportFilter;
import com.copeinca.apicopeincaprov.modules.core.models.views.ViewTrabajadorReportView;
import com.copeinca.apicopeincaprov.modules.core.repositories.jpa.IViewTrabajadorReportRepository;
import com.copeinca.apicopeincaprov.modules.core.service.query.IViewTrabajadorReportQueryService;
import com.copeinca.apicopeincaprov.modules.core.specification.ViewTrabajadorReportSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
* Implementación del servicio de consultas para ViewTrabajadorReportView
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class ViewTrabajadorReportQueryServiceImpl implements IViewTrabajadorReportQueryService {

    private final IViewTrabajadorReportRepository repository;
    private final ViewTrabajadorReportMapper mapper;

    @Override
    public PagedResult<ViewTrabajadorReportDTO> search(PagedRequest<ViewTrabajadorReportFilter> pagedRequest) {
        ViewTrabajadorReportFilter filter = pagedRequest.getFilter();

        Pageable pageable = PagingUtil.toPageable(pagedRequest);

        Page<ViewTrabajadorReportView> page = repository.findAll(ViewTrabajadorReportSpecification.byFilter(filter), pageable);

        List<ViewTrabajadorReportDTO> dtos = page.getContent().stream().map(mapper::entityToDto).collect(Collectors.toList());

        return PagedResult.<ViewTrabajadorReportDTO> builder().count((int) page.getTotalElements()).offset((int) page.getPageable().getOffset())
                .limit(page.getSize()).page(page.getNumber()).totalPages(page.getTotalPages()).hasNext(page.hasNext()).hasPrevious(page.hasPrevious())
                .result(dtos).build();

    }

    @Override
    public ViewTrabajadorReportDTO findById(String id) {
        ViewTrabajadorReportView entity = repository.findById(id).orElseThrow(() -> new RuntimeException("ViewTrabajadorReportView not found with id: " + id));
        return mapper.entityToDto(entity);
    }

    @Override
    public boolean existsById(String id) {
        return repository.existsById(id);
    }

    @Override
    public long countByFilter(ViewTrabajadorReportFilter filter) {
        return repository.count(ViewTrabajadorReportSpecification.byFilter(filter));
    }
}
