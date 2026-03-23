/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/crud/imp/EntityCrudServiceImpl.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.crud.imp;

import com.copeinca.apicopeincaprov.modules.core.mappers.SvSolicitudVisitaHistorialMapper;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvSolicitudVisitaHistorialDTO;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvSolicitudVisitaHistorialEntity;
import com.copeinca.apicopeincaprov.modules.core.repositories.jpa.ISvSolicitudVisitaHistorialRepository;
import com.copeinca.apicopeincaprov.modules.core.service.crud.ISvSolicitudVisitaHistorialCrudService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
* Implementación del servicio CRUD para SvSolicitudVisitaHistorialEntity
* Proporciona operaciones básicas de creación, actualización y eliminación.
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class SvSolicitudVisitaHistorialCrudServiceImpl implements ISvSolicitudVisitaHistorialCrudService {

    private final ISvSolicitudVisitaHistorialRepository repository;
    private final SvSolicitudVisitaHistorialMapper mapper;

    @Override
    public SvSolicitudVisitaHistorialEntity save(SvSolicitudVisitaHistorialDTO dto) {
        SvSolicitudVisitaHistorialEntity entity = mapper.dtoToEntity(dto);
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
    public List<SvSolicitudVisitaHistorialEntity> saveAll(List<SvSolicitudVisitaHistorialDTO> dtos) {
        List<SvSolicitudVisitaHistorialEntity> entities = dtos.stream().map(mapper::dtoToEntity).collect(Collectors.toList());
        return repository.saveAll(entities);
    }
}
