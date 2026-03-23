/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/query/imp/EntityQueryServiceImpl.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.query.imp;

import com.copeinca.apicopeincaprov.commons.utils.dto.PagingUtil;
import com.copeinca.apicopeincaprov.global.dtos.response.PagedRequest;
import com.copeinca.apicopeincaprov.global.dtos.response.PagedResult;
import com.copeinca.apicopeincaprov.modules.core.mappers.SvEstadoSsoMapper;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvEstadoSsoDTO;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.SvEstadoSsoFilter;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvEstadoSsoEntity;
import com.copeinca.apicopeincaprov.modules.core.repositories.jpa.ISvEstadoSsoRepository;
import com.copeinca.apicopeincaprov.modules.core.service.query.ISvEstadoSsoQueryService;
import com.copeinca.apicopeincaprov.modules.core.specification.SvEstadoSsoSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
* Implementación del servicio de consultas para SvEstadoSsoEntity
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class SvEstadoSsoQueryServiceImpl implements ISvEstadoSsoQueryService {

    private final ISvEstadoSsoRepository repository;
    private final SvEstadoSsoMapper mapper;

    @Override
    public PagedResult<SvEstadoSsoDTO> search(PagedRequest<SvEstadoSsoFilter> pagedRequest) {
        SvEstadoSsoFilter filter = pagedRequest.getFilter();

        Pageable pageable = PagingUtil.toPageable(pagedRequest);

        Page<SvEstadoSsoEntity> page = repository.findAll(SvEstadoSsoSpecification.byFilter(filter), pageable);

        List<SvEstadoSsoDTO> dtos = page.getContent().stream().map(mapper::entityToDto).collect(Collectors.toList());

        return PagedResult.<SvEstadoSsoDTO> builder().count((int) page.getTotalElements()).offset((int) page.getPageable().getOffset()).limit(page.getSize())
                .page(page.getNumber()).totalPages(page.getTotalPages()).hasNext(page.hasNext()).hasPrevious(page.hasPrevious()).result(dtos).build();

    }

    @Override
    public SvEstadoSsoDTO findById(String id) {
        SvEstadoSsoEntity entity = repository.findById(id).orElseThrow(() -> new RuntimeException("SvEstadoSsoEntity not found with id: " + id));
        return mapper.entityToDto(entity);
    }

    @Override
    public boolean existsById(String id) {
        return repository.existsById(id);
    }

    @Override
    public long countByFilter(SvEstadoSsoFilter filter) {
        return repository.count(SvEstadoSsoSpecification.byFilter(filter));
    }
}
