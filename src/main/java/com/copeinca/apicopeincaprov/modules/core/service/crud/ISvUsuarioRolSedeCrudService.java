/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/crud/IEntityCrudService.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.crud;

import com.copeinca.apicopeincaprov.modules.core.models.dto.SvUsuarioRolSedeDTO;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvUsuarioRolSedeEntity;

import java.util.List;

/**
* Interface de servicio CRUD para SvUsuarioRolSedeEntity
* Responsable de las operaciones básicas de creación, actualización y eliminación.
*/
public interface ISvUsuarioRolSedeCrudService {

    /**
    * Guarda (crea o actualiza) la entidad a partir del DTO.
    */
    SvUsuarioRolSedeEntity save(SvUsuarioRolSedeDTO dto);

    /**
    * Elimina lógicamente una entidad por su identificador.
    */
    boolean softDeleteById(String id);

    /**
    * Elimina físicamente una entidad por su identificador.
    */
    boolean hardDeleteById(String id);

    /**
    * Restaura lógicamente una entidad por su identificador.
    */
    boolean restoreById(String id);

    /**
    * Guarda múltiples entidades en lote.
    */
    List<SvUsuarioRolSedeEntity> saveAll(List<SvUsuarioRolSedeDTO> dtos);
}
