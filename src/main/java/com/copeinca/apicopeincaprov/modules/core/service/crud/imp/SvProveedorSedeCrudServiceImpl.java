/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/crud/imp/EntityCrudServiceImpl.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.crud.imp;

import com.copeinca.apicopeincaprov.modules.core.mappers.SvProveedorSedeMapper;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvProveedorSedeDTO;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvProveedorSedeEntity;
import com.copeinca.apicopeincaprov.modules.core.repositories.jpa.ISvProveedorSedeRepository;
import com.copeinca.apicopeincaprov.modules.core.service.crud.ISvProveedorSedeCrudService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
* Implementación del servicio CRUD para SvProveedorSedeEntity
* Proporciona operaciones básicas de creación, actualización y eliminación.
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class SvProveedorSedeCrudServiceImpl implements ISvProveedorSedeCrudService {

    private final ISvProveedorSedeRepository repository;
    private final SvProveedorSedeMapper mapper;

    @Override
    public SvProveedorSedeEntity save(SvProveedorSedeDTO dto) {
        SvProveedorSedeEntity entity = mapper.dtoToEntity(dto);
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
    public List<SvProveedorSedeEntity> saveAll(List<SvProveedorSedeDTO> dtos) {
        List<SvProveedorSedeEntity> entities = dtos.stream().map(mapper::dtoToEntity).collect(Collectors.toList());
        return repository.saveAll(entities);
    }
}
