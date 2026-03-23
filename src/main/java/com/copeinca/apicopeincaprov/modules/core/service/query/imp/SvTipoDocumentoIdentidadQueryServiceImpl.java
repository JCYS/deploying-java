/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/query/imp/EntityQueryServiceImpl.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.query.imp;

import com.copeinca.apicopeincaprov.commons.utils.dto.PagingUtil;
import com.copeinca.apicopeincaprov.global.dtos.response.PagedRequest;
import com.copeinca.apicopeincaprov.global.dtos.response.PagedResult;
import com.copeinca.apicopeincaprov.modules.core.mappers.SvTipoDocumentoIdentidadMapper;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvTipoDocumentoIdentidadDTO;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.SvTipoDocumentoIdentidadFilter;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvTipoDocumentoIdentidadEntity;
import com.copeinca.apicopeincaprov.modules.core.repositories.jpa.ISvTipoDocumentoIdentidadRepository;
import com.copeinca.apicopeincaprov.modules.core.service.query.ISvTipoDocumentoIdentidadQueryService;
import com.copeinca.apicopeincaprov.modules.core.specification.SvTipoDocumentoIdentidadSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
* Implementación del servicio de consultas para SvTipoDocumentoIdentidadEntity
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class SvTipoDocumentoIdentidadQueryServiceImpl implements ISvTipoDocumentoIdentidadQueryService {

    private final ISvTipoDocumentoIdentidadRepository repository;
    private final SvTipoDocumentoIdentidadMapper mapper;

    @Override
    public PagedResult<SvTipoDocumentoIdentidadDTO> search(PagedRequest<SvTipoDocumentoIdentidadFilter> pagedRequest) {
        SvTipoDocumentoIdentidadFilter filter = pagedRequest.getFilter();

        Pageable pageable = PagingUtil.toPageable(pagedRequest);

        Page<SvTipoDocumentoIdentidadEntity> page = repository.findAll(SvTipoDocumentoIdentidadSpecification.byFilter(filter), pageable);

        List<SvTipoDocumentoIdentidadDTO> dtos = page.getContent().stream().map(mapper::entityToDto).collect(Collectors.toList());

        return PagedResult.<SvTipoDocumentoIdentidadDTO> builder().count((int) page.getTotalElements()).offset((int) page.getPageable().getOffset())
                .limit(page.getSize()).page(page.getNumber()).totalPages(page.getTotalPages()).hasNext(page.hasNext()).hasPrevious(page.hasPrevious())
                .result(dtos).build();

    }

    @Override
    public SvTipoDocumentoIdentidadDTO findById(String id) {
        SvTipoDocumentoIdentidadEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("SvTipoDocumentoIdentidadEntity not found with id: " + id));
        return mapper.entityToDto(entity);
    }

    @Override
    public boolean existsById(String id) {
        return repository.existsById(id);
    }

    @Override
    public long countByFilter(SvTipoDocumentoIdentidadFilter filter) {
        return repository.count(SvTipoDocumentoIdentidadSpecification.byFilter(filter));
    }
}
