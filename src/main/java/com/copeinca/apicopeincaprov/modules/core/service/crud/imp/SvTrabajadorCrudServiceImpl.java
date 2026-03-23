/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/crud/imp/EntityCrudServiceImpl.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.crud.imp;

import com.copeinca.apicopeincaprov.modules.core.mappers.SvTrabajadorMapper;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvTrabajadorDTO;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvTrabajadorEntity;
import com.copeinca.apicopeincaprov.modules.core.repositories.jpa.ISvTrabajadorRepository;
import com.copeinca.apicopeincaprov.modules.core.service.crud.ISvTrabajadorCrudService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
* Implementación del servicio CRUD para SvTrabajadorEntity
* Proporciona operaciones básicas de creación, actualización y eliminación.
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class SvTrabajadorCrudServiceImpl implements ISvTrabajadorCrudService {

    private final ISvTrabajadorRepository repository;
    private final SvTrabajadorMapper mapper;

    @Override
    public SvTrabajadorEntity save(SvTrabajadorDTO dto) {
        SvTrabajadorEntity entity = mapper.dtoToEntity(dto);
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
            repository.restore(false, id);
            return true;
        }
        return false;
    }

    @Override
    public List<SvTrabajadorEntity> saveAll(List<SvTrabajadorDTO> dtos) {
        List<SvTrabajadorEntity> entities = dtos.stream().map(mapper::dtoToEntity).collect(Collectors.toList());
        return repository.saveAll(entities);
    }

    @Override
    public boolean isActived(String id, Boolean active) {
        if (repository.existsById(id)) {
           repository.isActive(active, id);
            return true;
        }
        return false;
    }

}
