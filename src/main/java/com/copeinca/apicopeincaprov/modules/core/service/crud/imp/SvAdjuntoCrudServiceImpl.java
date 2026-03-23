/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/crud/imp/EntityCrudServiceImpl.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.crud.imp;

import com.copeinca.apicopeincaprov.modules.core.mappers.SvAdjuntoMapper;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvAdjuntoDTO;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvAdjuntoEntity;
import com.copeinca.apicopeincaprov.modules.core.repositories.jpa.ISvAdjuntoRepository;
import com.copeinca.apicopeincaprov.modules.core.service.crud.ISvAdjuntoCrudService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
* Implementación del servicio CRUD para SvAdjuntoEntity
* Proporciona operaciones básicas de creación, actualización y eliminación.
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class SvAdjuntoCrudServiceImpl implements ISvAdjuntoCrudService {

    private final ISvAdjuntoRepository repository;
    private final SvAdjuntoMapper mapper;

    @Override
    public SvAdjuntoEntity save(SvAdjuntoDTO dto) {
        SvAdjuntoEntity entity = mapper.dtoToEntity(dto);
        return repository.save(entity);
    }

    @Override
    public boolean softDeleteById(String id) {
        if (repository.existsById(id)) {
            repository.updateIsDeleted(true, id);
            return true;
        }
        return false;
    }

    @Override
    public boolean hardDeleteById(String id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean restoreById(String id) {
        if (repository.existsById(id)) {
            repository.updateIsDeleted(false, id);
            return true;
        }
        return false;
    }

    @Override
    public List<SvAdjuntoEntity> saveAll(List<SvAdjuntoDTO> dtos) {
        List<SvAdjuntoEntity> entities = dtos.stream().map(mapper::dtoToEntity).collect(Collectors.toList());
        return repository.saveAll(entities);
    }
}
