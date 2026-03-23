/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/crud/imp/EntityCrudServiceImpl.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.crud.imp;

import com.copeinca.apicopeincaprov.modules.core.mappers.SvSolicitudVisistaMapper;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvSolicitudVisistaDTO;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvSolicitudVisistaEntity;
import com.copeinca.apicopeincaprov.modules.core.repositories.jpa.ISvSolicitudVisistaRepository;
import com.copeinca.apicopeincaprov.modules.core.service.crud.ISvSolicitudVisistaCrudService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
* Implementación del servicio CRUD para SvSolicitudVisistaEntity
* Proporciona operaciones básicas de creación, actualización y eliminación.
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class SvSolicitudVisistaCrudServiceImpl implements ISvSolicitudVisistaCrudService {

    private final ISvSolicitudVisistaRepository repository;
    private final SvSolicitudVisistaMapper mapper;

    @Override
    public SvSolicitudVisistaEntity save(SvSolicitudVisistaDTO dto) {
        SvSolicitudVisistaEntity entity = mapper.dtoToEntity(dto);
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
    public List<SvSolicitudVisistaEntity> saveAll(List<SvSolicitudVisistaDTO> dtos) {
        List<SvSolicitudVisistaEntity> entities = dtos.stream().map(mapper::dtoToEntity).collect(Collectors.toList());
        return repository.saveAll(entities);
    }
}
