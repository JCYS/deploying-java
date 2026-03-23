/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/query/imp/EntityQueryServiceImpl.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.query.imp;

import com.copeinca.apicopeincaprov.commons.utils.dto.PagingUtil;
import com.copeinca.apicopeincaprov.global.dtos.response.PagedRequest;
import com.copeinca.apicopeincaprov.global.dtos.response.PagedResult;
import com.copeinca.apicopeincaprov.modules.core.mappers.ViewProveedorReportMapper;
import com.copeinca.apicopeincaprov.modules.core.models.dto.ViewProveedorReportDTO;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.ViewProveedorReportFilter;
import com.copeinca.apicopeincaprov.modules.core.models.views.ViewProveedorReportView;
import com.copeinca.apicopeincaprov.modules.core.repositories.jpa.IViewProveedorReportRepository;
import com.copeinca.apicopeincaprov.modules.core.service.query.IViewProveedorReportQueryService;
import com.copeinca.apicopeincaprov.modules.core.specification.ViewProveedorReportSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
* Implementación del servicio de consultas para ViewProveedorReportView
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class ViewProveedorReportQueryServiceImpl implements IViewProveedorReportQueryService {

    private final IViewProveedorReportRepository repository;
    private final ViewProveedorReportMapper mapper;

    @Override
    public PagedResult<ViewProveedorReportDTO> search(PagedRequest<ViewProveedorReportFilter> pagedRequest) {
        ViewProveedorReportFilter filter = pagedRequest.getFilter();

        Pageable pageable = PagingUtil.toPageable(pagedRequest);

        Page<ViewProveedorReportView> page = repository.findAll(ViewProveedorReportSpecification.byFilter(filter), pageable);

        List<ViewProveedorReportDTO> dtos = page.getContent().stream().map(mapper::entityToDto).collect(Collectors.toList());

        return PagedResult.<ViewProveedorReportDTO> builder().count((int) page.getTotalElements()).offset((int) page.getPageable().getOffset())
                .limit(page.getSize()).page(page.getNumber()).totalPages(page.getTotalPages()).hasNext(page.hasNext()).hasPrevious(page.hasPrevious())
                .result(dtos).build();

    }

    @Override
    public ViewProveedorReportDTO findById(String id) {
        ViewProveedorReportView entity = repository.findById(id).orElseThrow(() -> new RuntimeException("ViewProveedorReportView not found with id: " + id));
        return mapper.entityToDto(entity);
    }

    @Override
    public boolean existsById(String id) {
        return repository.existsById(id);
    }

    @Override
    public long countByFilter(ViewProveedorReportFilter filter) {
        return repository.count(ViewProveedorReportSpecification.byFilter(filter));
    }
}
