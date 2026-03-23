/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/business/imp/EntityService.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.business.imp;

import com.copeinca.apicopeincaprov.modules.core.mappers.SvAdjuntoMapper;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvAdjuntoDTO;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvAdjuntosIndexDto;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvAdjuntoEntity;
import com.copeinca.apicopeincaprov.modules.core.repositories.jpa.ISvAdjuntoRepository;
import com.copeinca.apicopeincaprov.modules.core.service.business.ISvAdjuntoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
* Implementación del service para SvAdjuntoEntity
* Extiende de las implementaciones especializadas para reutilizar funcionalidad
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class SvAdjuntoService implements ISvAdjuntoService {

    private final ISvAdjuntoRepository iSvAdjuntoRepository;
    private final SvAdjuntoMapper mapper;

    @Override
    public List<SvAdjuntosIndexDto>  getAdjuntosBYProvId(String sId) {
        List<SvAdjuntoEntity> adjuntos =
                iSvAdjuntoRepository.findByProveedorIdAndIsDeleted(sId, false);

        return IntStream.range(0, adjuntos.size())
                .mapToObj(i -> {
                    SvAdjuntosIndexDto dto = new SvAdjuntosIndexDto();
                    dto.setIndex(i);
                    dto.setInfoAdjunto(mapper.entityToDto(adjuntos.get(i)));
                    return dto;
                })
                .collect(Collectors.toList());
    }
}
