/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/crud/imp/EntityCrudServiceImpl.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.crud.imp;

import com.copeinca.apicopeincaprov.modules.core.mappers.SvRolMapper;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvRolDTO;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvRolEntity;
import com.copeinca.apicopeincaprov.modules.core.repositories.jpa.ISvRolRepository;
import com.copeinca.apicopeincaprov.modules.core.service.crud.ISvRolCrudService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
* Implementación del servicio CRUD para SvRolEntity
* Proporciona operaciones básicas de creación, actualización y eliminación.
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class SvRolCrudServiceImpl implements ISvRolCrudService {

    private final ISvRolRepository repository;
    private final SvRolMapper mapper;

    @Override
    public SvRolEntity save(SvRolDTO dto) {
        SvRolEntity entity = mapper.dtoToEntity(dto);
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
    public List<SvRolEntity> saveAll(List<SvRolDTO> dtos) {
        List<SvRolEntity> entities = dtos.stream().map(mapper::dtoToEntity).collect(Collectors.toList());
        return repository.saveAll(entities);
    }
}
