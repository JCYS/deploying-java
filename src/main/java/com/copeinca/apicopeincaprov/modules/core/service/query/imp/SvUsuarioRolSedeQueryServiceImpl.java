/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/query/imp/EntityQueryServiceImpl.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.query.imp;

import com.copeinca.apicopeincaprov.commons.utils.dto.PagingUtil;
import com.copeinca.apicopeincaprov.global.dtos.response.PagedRequest;
import com.copeinca.apicopeincaprov.global.dtos.response.PagedResult;
import com.copeinca.apicopeincaprov.modules.core.mappers.SvUsuarioRolSedeMapper;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvUsuarioRolSedeDTO;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.SvUsuarioRolSedeFilter;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvUsuarioRolSedeEntity;
import com.copeinca.apicopeincaprov.modules.core.repositories.jpa.ISvUsuarioRolSedeRepository;
import com.copeinca.apicopeincaprov.modules.core.service.query.ISvUsuarioRolSedeQueryService;
import com.copeinca.apicopeincaprov.modules.core.specification.SvUsuarioRolSedeSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
* Implementación del servicio de consultas para SvUsuarioRolSedeEntity
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class SvUsuarioRolSedeQueryServiceImpl implements ISvUsuarioRolSedeQueryService {

    private final ISvUsuarioRolSedeRepository repository;
    private final SvUsuarioRolSedeMapper mapper;

    @Override
    public PagedResult<SvUsuarioRolSedeDTO> search(PagedRequest<SvUsuarioRolSedeFilter> pagedRequest) {
        SvUsuarioRolSedeFilter filter = pagedRequest.getFilter();

        Pageable pageable = PagingUtil.toPageable(pagedRequest);

        Page<SvUsuarioRolSedeEntity> page = repository.findAll(SvUsuarioRolSedeSpecification.byFilter(filter), pageable);

        List<SvUsuarioRolSedeDTO> dtos = page.getContent().stream().map(mapper::entityToDto).collect(Collectors.toList());

        return PagedResult.<SvUsuarioRolSedeDTO> builder().count((int) page.getTotalElements()).offset((int) page.getPageable().getOffset())
                .limit(page.getSize()).page(page.getNumber()).totalPages(page.getTotalPages()).hasNext(page.hasNext()).hasPrevious(page.hasPrevious())
                .result(dtos).build();

    }

    @Override
    public SvUsuarioRolSedeDTO findById(String id) {
        SvUsuarioRolSedeEntity entity = repository.findById(id).orElseThrow(() -> new RuntimeException("SvUsuarioRolSedeEntity not found with id: " + id));
        return mapper.entityToDto(entity);
    }

    @Override
    public boolean existsById(String id) {
        return repository.existsById(id);
    }

    @Override
    public long countByFilter(SvUsuarioRolSedeFilter filter) {
        return repository.count(SvUsuarioRolSedeSpecification.byFilter(filter));
    }
}
