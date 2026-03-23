/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/business/imp/EntityService.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.business.imp;

import com.copeinca.apicopeincaprov.modules.core.mappers.SvProveedorTipoActividadAltoRiesgoMapper;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvProveedorTipoActividadAltoRiesgoDTO;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvTipoActividadAltoRiesgoDTO;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvProveedorTipoActividadAltoRiesgoEntity;
import com.copeinca.apicopeincaprov.modules.core.repositories.jpa.ISvProveedorTipoActividadAltoRiesgoRepository;
import com.copeinca.apicopeincaprov.modules.core.service.business.ISvProveedorTipoActividadAltoRiesgoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* Implementación del service para SvProveedorTipoActividadAltoRiesgoEntity
* Extiende de las implementaciones especializadas para reutilizar funcionalidad
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class SvProveedorTipoActividadAltoRiesgoService implements ISvProveedorTipoActividadAltoRiesgoService {

    private final ISvProveedorTipoActividadAltoRiesgoRepository iSvProveedorTipoActividadAltoRiesgoRepository;
    private final SvProveedorTipoActividadAltoRiesgoMapper mapper;

    @Override
    public List<SvProveedorTipoActividadAltoRiesgoDTO> loadActivities(String sId) {
        //iSvProveedorTipoActividadAltoRiesgoRepository
        return iSvProveedorTipoActividadAltoRiesgoRepository.findByProveedorId(sId)
                .stream()
                .map(entity -> {
                    SvProveedorTipoActividadAltoRiesgoDTO dto =
                            new SvProveedorTipoActividadAltoRiesgoDTO();
                    dto.setDescription(entity.getTipoActividadAltoRiesgoCodeEntity().getDescription());
                    dto.setId(entity.getId());
                    dto.setTipoActividadAltoRiesgoCode(entity.getTipoActividadAltoRiesgoCode());
//                    dto.setProveedorId(entity.getProveedoredorId()); // ajusta si el nombre varía
                    dto.setIsActive(entity.getIsActive());
                    dto.setIsDeleted(entity.getIsDeleted());
                    dto.setVersion(entity.getVersion());
                    dto.setProveedorId(entity.getProveedorId());

                    return dto;
                })
                .toList();
    }
}
