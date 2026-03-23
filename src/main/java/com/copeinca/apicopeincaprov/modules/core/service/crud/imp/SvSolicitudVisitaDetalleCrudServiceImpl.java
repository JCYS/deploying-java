/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/crud/imp/EntityCrudServiceImpl.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.crud.imp;

import com.copeinca.apicopeincaprov.modules.core.mappers.SvSolicitudVisitaDetalleMapper;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvSolicitudVisitaDetalleDTO;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvSolicitudVisitaDetalleEntity;
import com.copeinca.apicopeincaprov.modules.core.repositories.jpa.ISvSolicitudVisitaDetalleRepository;
import com.copeinca.apicopeincaprov.modules.core.service.crud.ISvSolicitudVisitaDetalleCrudService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
* Implementación del servicio CRUD para SvSolicitudVisitaDetalleEntity
* Proporciona operaciones básicas de creación, actualización y eliminación.
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class SvSolicitudVisitaDetalleCrudServiceImpl implements ISvSolicitudVisitaDetalleCrudService {

    private final ISvSolicitudVisitaDetalleRepository repository;
    private final SvSolicitudVisitaDetalleMapper mapper;

    @Override
    public SvSolicitudVisitaDetalleEntity save(SvSolicitudVisitaDetalleDTO dto) {
        SvSolicitudVisitaDetalleEntity entity = mapper.dtoToEntity(dto);
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
    public List<SvSolicitudVisitaDetalleEntity> saveAll(List<SvSolicitudVisitaDetalleDTO> dtos) {
        List<SvSolicitudVisitaDetalleEntity> entities = dtos.stream().map(mapper::dtoToEntity).collect(Collectors.toList());
        return repository.saveAll(entities);
    }
}
