/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/crud/IEntityCrudService.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.crud;

import com.copeinca.apicopeincaprov.modules.core.models.dto.SvRolDTO;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvRolEntity;

import java.util.List;

/**
* Interface de servicio CRUD para SvRolEntity
* Responsable de las operaciones básicas de creación, actualización y eliminación.
*/
public interface ISvRolCrudService {

    /**
    * Guarda (crea o actualiza) la entidad a partir del DTO.
    */
    SvRolEntity save(SvRolDTO dto);

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
    List<SvRolEntity> saveAll(List<SvRolDTO> dtos);
}
