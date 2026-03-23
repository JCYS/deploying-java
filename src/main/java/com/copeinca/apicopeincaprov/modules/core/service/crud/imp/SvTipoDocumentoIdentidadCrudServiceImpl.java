/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/crud/imp/EntityCrudServiceImpl.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.crud.imp;

import com.copeinca.apicopeincaprov.modules.core.mappers.SvTipoDocumentoIdentidadMapper;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvTipoDocumentoIdentidadDTO;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvTipoDocumentoIdentidadEntity;
import com.copeinca.apicopeincaprov.modules.core.repositories.jpa.ISvTipoDocumentoIdentidadRepository;
import com.copeinca.apicopeincaprov.modules.core.service.crud.ISvTipoDocumentoIdentidadCrudService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
* Implementación del servicio CRUD para SvTipoDocumentoIdentidadEntity
* Proporciona operaciones básicas de creación, actualización y eliminación.
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class SvTipoDocumentoIdentidadCrudServiceImpl implements ISvTipoDocumentoIdentidadCrudService {

    private final ISvTipoDocumentoIdentidadRepository repository;
    private final SvTipoDocumentoIdentidadMapper mapper;

    @Override
    public SvTipoDocumentoIdentidadEntity save(SvTipoDocumentoIdentidadDTO dto) {
        SvTipoDocumentoIdentidadEntity entity = mapper.dtoToEntity(dto);
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
    public List<SvTipoDocumentoIdentidadEntity> saveAll(List<SvTipoDocumentoIdentidadDTO> dtos) {
        List<SvTipoDocumentoIdentidadEntity> entities = dtos.stream().map(mapper::dtoToEntity).collect(Collectors.toList());
        return repository.saveAll(entities);
    }
}
