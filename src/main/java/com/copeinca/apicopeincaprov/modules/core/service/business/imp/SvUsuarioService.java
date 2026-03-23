/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/business/imp/EntityService.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.business.imp;

import com.copeinca.apicopeincaprov.modules.core.mappers.SvUsuarioMapper;
import com.copeinca.apicopeincaprov.modules.core.mappers.SvUsuarioRolSedeMapper;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvRolDTO;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvSedeDTO;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvUsuarioRolSedeDTO;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvUsuarioEntity;
import com.copeinca.apicopeincaprov.modules.core.repositories.jpa.ISvUsuarioRepository;
import com.copeinca.apicopeincaprov.modules.core.service.business.ISvUsuarioService;
import com.copeinca.apicopeincaprov.modules.core.service.crud.ISvUsuarioRolSedeCrudService;
import com.copeinca.apicopeincaprov.security.dtos.UserSedeDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
* Implementación del service para SvUsuarioEntity
* Extiende de las implementaciones especializadas para reutilizar funcionalidad
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class SvUsuarioService implements ISvUsuarioService {

    private final ISvUsuarioRepository repository;
    private final SvUsuarioMapper mapper;
    private final ISvUsuarioRolSedeCrudService iSvUsuarioRolSedeCrudService;
    private final SvUsuarioRolSedeMapper svUsuarioRolSedeMapper;

    @Override
    public UserSedeDto updateSede(UserSedeDto userInfo) throws Exception {
        List<SvUsuarioEntity> listUsuario = repository.findAllByEmail(userInfo.getEmail());
            for(SvRolDTO item: userInfo.getRoles()){
                //for(SvSedeDTO sede: userInfo.getSedes()){
                    iSvUsuarioRolSedeCrudService.save(SvUsuarioRolSedeDTO.builder()
                            .sedeId("ef6f00b1-7893-45c0-bc46-46e609ae12e1")
                            .usuarioId(userInfo.getUsuarioDTO().getId())
                            .isActive(true)
                            .isDeleted(false)
                            .rolCode(item.getId())
                            .build());
                //}
            }
            for(SvSedeDTO sede: userInfo.getSedes()){
                iSvUsuarioRolSedeCrudService.save(SvUsuarioRolSedeDTO.builder()
                        .sedeId(sede.getId())
                        .usuarioId(userInfo.getUsuarioDTO().getId())
                        .isActive(true)
                        .isDeleted(false)
                        .rolCode("controlprov_sede")
                        .build());
            }
        return userInfo;
    }
}
