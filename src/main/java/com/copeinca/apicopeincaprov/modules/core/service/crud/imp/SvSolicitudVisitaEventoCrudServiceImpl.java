/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/crud/imp/EntityCrudServiceImpl.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.crud.imp;

import com.copeinca.apicopeincaprov.modules.core.mappers.SvSolicitudVisitaEventoMapper;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvSolicitudVisitaEventoDTO;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvSolicitudVisitaEventoEntity;
import com.copeinca.apicopeincaprov.modules.core.repositories.jpa.ISvSolicitudVisitaEventoRepository;
import com.copeinca.apicopeincaprov.modules.core.service.crud.ISvSolicitudVisitaEventoCrudService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
* Implementación del servicio CRUD para SvSolicitudVisitaEventoEntity
* Proporciona operaciones básicas de creación, actualización y eliminación.
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class SvSolicitudVisitaEventoCrudServiceImpl implements ISvSolicitudVisitaEventoCrudService {

    private final ISvSolicitudVisitaEventoRepository repository;
    private final SvSolicitudVisitaEventoMapper mapper;

    @Override
    public SvSolicitudVisitaEventoEntity save(SvSolicitudVisitaEventoDTO dto) {
        SvSolicitudVisitaEventoEntity entity = mapper.dtoToEntity(dto);
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
    public List<SvSolicitudVisitaEventoEntity> saveAll(List<SvSolicitudVisitaEventoDTO> dtos) {
        List<SvSolicitudVisitaEventoEntity> entities = dtos.stream().map(mapper::dtoToEntity).collect(Collectors.toList());
        return repository.saveAll(entities);
    }
}
