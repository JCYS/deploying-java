/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/query/imp/EntityQueryServiceImpl.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.query.imp;

import com.copeinca.apicopeincaprov.commons.utils.dto.PagingUtil;
import com.copeinca.apicopeincaprov.global.dtos.response.PagedRequest;
import com.copeinca.apicopeincaprov.global.dtos.response.PagedResult;
import com.copeinca.apicopeincaprov.modules.core.mappers.ViewPersonaRestringidaReportMapper;
import com.copeinca.apicopeincaprov.modules.core.models.dto.ViewPersonaRestringidaReportDTO;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.ViewPersonaRestringidaReportFilter;
import com.copeinca.apicopeincaprov.modules.core.models.views.ViewPersonaRestringidaReportView;
import com.copeinca.apicopeincaprov.modules.core.repositories.jpa.IViewPersonaRestringidaReportRepository;
import com.copeinca.apicopeincaprov.modules.core.service.query.IViewPersonaRestringidaReportQueryService;
import com.copeinca.apicopeincaprov.modules.core.specification.ViewPersonaRestringidaReportSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
* Implementación del servicio de consultas para ViewPersonaRestringidaReportView
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class ViewPersonaRestringidaReportQueryServiceImpl implements IViewPersonaRestringidaReportQueryService {

    private final IViewPersonaRestringidaReportRepository repository;
    private final ViewPersonaRestringidaReportMapper mapper;

    @Override
    public PagedResult<ViewPersonaRestringidaReportDTO> search(PagedRequest<ViewPersonaRestringidaReportFilter> pagedRequest) {
        ViewPersonaRestringidaReportFilter filter = pagedRequest.getFilter();

        Pageable pageable = PagingUtil.toPageable(pagedRequest);

        Page<ViewPersonaRestringidaReportView> page = repository.findAll(ViewPersonaRestringidaReportSpecification.byFilter(filter), pageable);

        List<ViewPersonaRestringidaReportDTO> dtos = page.getContent().stream().map(mapper::entityToDto).collect(Collectors.toList());

        return PagedResult.<ViewPersonaRestringidaReportDTO> builder().count((int) page.getTotalElements()).offset((int) page.getPageable().getOffset())
                .limit(page.getSize()).page(page.getNumber()).totalPages(page.getTotalPages()).hasNext(page.hasNext()).hasPrevious(page.hasPrevious())
                .result(dtos).build();

    }

    @Override
    public ViewPersonaRestringidaReportDTO findById(String id) {
        ViewPersonaRestringidaReportView entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("ViewPersonaRestringidaReportView not found with id: " + id));
        return mapper.entityToDto(entity);
    }

    @Override
    public boolean existsById(String id) {
        return repository.existsById(id);
    }

    @Override
    public long countByFilter(ViewPersonaRestringidaReportFilter filter) {
        return repository.count(ViewPersonaRestringidaReportSpecification.byFilter(filter));
    }
}
