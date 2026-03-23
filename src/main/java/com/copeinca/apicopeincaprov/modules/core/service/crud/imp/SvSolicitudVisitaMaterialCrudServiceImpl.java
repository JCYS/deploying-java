/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/crud/imp/EntityCrudServiceImpl.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.crud.imp;

import com.copeinca.apicopeincaprov.modules.core.mappers.SvSolicitudVisitaMaterialMapper;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvSolicitudVisitaMaterialDTO;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvSolicitudVisitaMaterialEntity;
import com.copeinca.apicopeincaprov.modules.core.repositories.jpa.ISvSolicitudVisitaMaterialRepository;
import com.copeinca.apicopeincaprov.modules.core.service.crud.ISvSolicitudVisitaMaterialCrudService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
* Implementación del servicio CRUD para SvSolicitudVisitaMaterialEntity
* Proporciona operaciones básicas de creación, actualización y eliminación.
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class SvSolicitudVisitaMaterialCrudServiceImpl implements ISvSolicitudVisitaMaterialCrudService {

    private final ISvSolicitudVisitaMaterialRepository repository;
    private final SvSolicitudVisitaMaterialMapper mapper;

    @Override
    public SvSolicitudVisitaMaterialEntity save(SvSolicitudVisitaMaterialDTO dto) {
        SvSolicitudVisitaMaterialEntity entity = mapper.dtoToEntity(dto);
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
    public List<SvSolicitudVisitaMaterialEntity> saveAll(List<SvSolicitudVisitaMaterialDTO> dtos) {
        List<SvSolicitudVisitaMaterialEntity> entities = dtos.stream().map(mapper::dtoToEntity).collect(Collectors.toList());
        return repository.saveAll(entities);
    }
}
