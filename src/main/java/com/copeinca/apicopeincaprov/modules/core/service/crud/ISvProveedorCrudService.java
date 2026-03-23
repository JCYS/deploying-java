/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/crud/IEntityCrudService.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.crud;

import com.copeinca.apicopeincaprov.modules.core.models.dto.SvProveedorDTO;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvRegistroProveedorDto;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvProveedorEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
* Interface de servicio CRUD para SvProveedorEntity
* Responsable de las operaciones básicas de creación, actualización y eliminación.
*/
public interface ISvProveedorCrudService {

    /**
    * Guarda (crea o actualiza) la entidad a partir del DTO.
    */
    SvProveedorEntity save(SvProveedorDTO dto);

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
    List<SvProveedorEntity> saveAll(List<SvProveedorDTO> dtos);

    SvRegistroProveedorDto saveInfo(SvRegistroProveedorDto infoProv, List<MultipartFile> aFilesSSO, List<MultipartFile> aFilesCA, List<MultipartFile> aFilesAB,List<MultipartFile> aFilesAAR);

    SvRegistroProveedorDto getInfoSupplier(String sId);
}
