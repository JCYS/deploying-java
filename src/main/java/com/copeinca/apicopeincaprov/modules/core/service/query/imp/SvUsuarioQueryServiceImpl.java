/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/query/imp/EntityQueryServiceImpl.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.query.imp;

import com.copeinca.apicopeincaprov.commons.utils.dto.PagingUtil;
import com.copeinca.apicopeincaprov.global.dtos.response.PagedRequest;
import com.copeinca.apicopeincaprov.global.dtos.response.PagedResult;
import com.copeinca.apicopeincaprov.modules.core.mappers.SvUsuarioMapper;
import com.copeinca.apicopeincaprov.modules.core.mappers.SvUsuarioRolSedeMapper;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvSedeDTO;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvUsuarioDTO;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvUsuarioRolSedeDTO;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.SvUsuarioFilter;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvSedeEntity;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvUsuarioEntity;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvUsuarioRolSedeEntity;
import com.copeinca.apicopeincaprov.modules.core.repositories.jpa.ISvSedeRepository;
import com.copeinca.apicopeincaprov.modules.core.repositories.jpa.ISvUsuarioRepository;
import com.copeinca.apicopeincaprov.modules.core.repositories.jpa.ISvUsuarioRolSedeRepository;
import com.copeinca.apicopeincaprov.modules.core.service.query.ISvUsuarioQueryService;
import com.copeinca.apicopeincaprov.modules.core.specification.SvUsuarioSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
* Implementación del servicio de consultas para SvUsuarioEntity
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class SvUsuarioQueryServiceImpl implements ISvUsuarioQueryService {

    private final ISvUsuarioRepository repository;
    private final SvUsuarioMapper mapper;

    private final ISvUsuarioRolSedeRepository iSvUsuarioRolSedeRepository;
    private final SvUsuarioRolSedeMapper svUsuarioRolSedeMapper;

    private final ISvSedeRepository iSvSedeRepository;

    @Override
    public PagedResult<SvUsuarioDTO> search(PagedRequest<SvUsuarioFilter> pagedRequest) {
        SvUsuarioFilter filter = pagedRequest.getFilter();

        Pageable pageable = PagingUtil.toPageable(pagedRequest);

        Page<SvUsuarioEntity> page = repository.findAll(SvUsuarioSpecification.byFilter(filter), pageable);

        List<SvUsuarioDTO> dtos = page.getContent().stream().map(mapper::entityToDto).collect(Collectors.toList());

        for (SvUsuarioDTO usuarioDTO: dtos){
            List<SvUsuarioRolSedeEntity> aList = iSvUsuarioRolSedeRepository.findAllByUsuarioId(usuarioDTO.getId());
//            List<SvUsuarioRolSedeDTO> dtoList = aList.stream()
//                    .map(svUsuarioRolSedeMapper::entityToDto)
//                    .filter(a -> Boolean.FALSE.equals(a.getIsDeleted()))
//                    .collect(Collectors.toMap(
//                            SvUsuarioRolSedeDTO::getRolCode, // clave única
//                            Function.identity(),               // valor
//                            (a1, a2) -> a1                     // si se repite, conserva el primero
//                    ))
//                    .toList();
            List<SvUsuarioRolSedeDTO> dtoList = new ArrayList<>(
                    aList.stream()
                            .map(svUsuarioRolSedeMapper::entityToDto)
                            .filter(a -> Boolean.FALSE.equals(a.getIsDeleted()))
                            .collect(Collectors.toMap(
                                    SvUsuarioRolSedeDTO::getRolCode, // clave única
                                    Function.identity(),               // valor
                                    (a1, a2) -> a1                     // si se repite, conserva el primero
                            ))
                            .values()
            );
            usuarioDTO.setListSvUsuarioRolSedeDto(dtoList);



//            ArrayList<SvSedeDTO> sedes = new ArrayList<>();
//            for(SvUsuarioRolSedeEntity item: aList.stream()
//                    .filter(a -> !a.getIsDeleted())
//                    .toList()){
//               Optional<SvSedeEntity> oSede = iSvSedeRepository.findById(item.getSedeId());
//               if(oSede.isPresent()){
//                   sedes.add(SvSedeDTO.builder()
//                                   .id(oSede.get().getId())
//                                   .name(oSede.get().getName())
//                           .build());
//                }
//
//            }
            List<SvSedeDTO> sedes = new ArrayList<>(
                    aList.stream()
                            .filter(a -> !a.getIsDeleted())
                            .map(item -> iSvSedeRepository.findById(item.getSedeId()))
                            .filter(Optional::isPresent)
                            .map(Optional::get)
                            .map(sede -> SvSedeDTO.builder()
                                    .id(sede.getId())
                                    .name(sede.getName())
                                    .build())
                            .collect(Collectors.toMap(
                                    SvSedeDTO::getName,      // clave única → DISTINCT por id
                                    Function.identity(),
                                    (s1, s2) -> s1,        // conserva el primero
                                    LinkedHashMap::new     // mantiene orden
                            ))
                            .values()
            );
            usuarioDTO.setListSedes(sedes);
        }

        return PagedResult.<SvUsuarioDTO> builder().count((int) page.getTotalElements()).offset((int) page.getPageable().getOffset()).limit(page.getSize())
                .page(page.getNumber()).totalPages(page.getTotalPages()).hasNext(page.hasNext()).hasPrevious(page.hasPrevious()).result(dtos).build();

    }

    @Override
    public SvUsuarioDTO findById(String id) {
        SvUsuarioEntity entity = repository.findById(id).orElseThrow(() -> new RuntimeException("SvUsuarioEntity not found with id: " + id));
        return mapper.entityToDto(entity);
    }

    @Override
    public boolean existsById(String id) {
        return repository.existsById(id);
    }

    @Override
    public long countByFilter(SvUsuarioFilter filter) {
        return repository.count(SvUsuarioSpecification.byFilter(filter));
    }
}
