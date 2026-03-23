/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/query/imp/EntityQueryServiceImpl.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.query.imp;

import com.copeinca.apicopeincaprov.commons.utils.dto.PagingUtil;
import com.copeinca.apicopeincaprov.global.dtos.response.PagedRequest;
import com.copeinca.apicopeincaprov.global.dtos.response.PagedResult;
import com.copeinca.apicopeincaprov.modules.core.mappers.SvTipoDocumentoPlanillaMapper;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvTipoDocumentoPlanillaDTO;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.SvTipoDocumentoPlanillaFilter;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvTipoDocumentoPlanillaEntity;
import com.copeinca.apicopeincaprov.modules.core.repositories.jpa.ISvTipoDocumentoPlanillaRepository;
import com.copeinca.apicopeincaprov.modules.core.service.query.ISvTipoDocumentoPlanillaQueryService;
import com.copeinca.apicopeincaprov.modules.core.specification.SvTipoDocumentoPlanillaSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
* Implementación del servicio de consultas para SvTipoDocumentoPlanillaEntity
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class SvTipoDocumentoPlanillaQueryServiceImpl implements ISvTipoDocumentoPlanillaQueryService {

    private final ISvTipoDocumentoPlanillaRepository repository;
    private final SvTipoDocumentoPlanillaMapper mapper;

    @Override
    public PagedResult<SvTipoDocumentoPlanillaDTO> search(PagedRequest<SvTipoDocumentoPlanillaFilter> pagedRequest) {
        SvTipoDocumentoPlanillaFilter filter = pagedRequest.getFilter();

        Pageable pageable = PagingUtil.toPageable(pagedRequest);

        Page<SvTipoDocumentoPlanillaEntity> page = repository.findAll(SvTipoDocumentoPlanillaSpecification.byFilter(filter), pageable);

        List<SvTipoDocumentoPlanillaDTO> dtos = page.getContent().stream().map(mapper::entityToDto).collect(Collectors.toList());

        return PagedResult.<SvTipoDocumentoPlanillaDTO> builder().count((int) page.getTotalElements()).offset((int) page.getPageable().getOffset())
                .limit(page.getSize()).page(page.getNumber()).totalPages(page.getTotalPages()).hasNext(page.hasNext()).hasPrevious(page.hasPrevious())
                .result(dtos).build();

    }

    @Override
    public SvTipoDocumentoPlanillaDTO findById(String id) {
        SvTipoDocumentoPlanillaEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("SvTipoDocumentoPlanillaEntity not found with id: " + id));
        return mapper.entityToDto(entity);
    }

    @Override
    public boolean existsById(String id) {
        return repository.existsById(id);
    }

    @Override
    public long countByFilter(SvTipoDocumentoPlanillaFilter filter) {
        return repository.count(SvTipoDocumentoPlanillaSpecification.byFilter(filter));
    }
}
