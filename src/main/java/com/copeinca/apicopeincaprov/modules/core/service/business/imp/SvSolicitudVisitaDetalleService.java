/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/business/imp/EntityService.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.business.imp;

import com.copeinca.apicopeincaprov.commons.models.FilterOperation;
import com.copeinca.apicopeincaprov.commons.models.OperatorEnum;
import com.copeinca.apicopeincaprov.global.dtos.response.PagedRequest;
import com.copeinca.apicopeincaprov.global.dtos.response.PagedResult;
import com.copeinca.apicopeincaprov.modules.core.mappers.SvSolicitudVisitaDetalleMapper;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvTrabajadorDTO;
import com.copeinca.apicopeincaprov.modules.core.models.dto.ViewTrabajadorReportDTO;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.SvSolicitudVisitaDetalleFilter;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.SvTrabajadorFilter;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvSolicitudVisitaDetalleEntity;
import com.copeinca.apicopeincaprov.modules.core.repositories.jpa.ISvSolicitudVisitaDetalleRepository;
import com.copeinca.apicopeincaprov.modules.core.service.business.ISvSolicitudVisitaDetalleService;
import com.copeinca.apicopeincaprov.modules.core.service.query.ISvSolicitudVisitaDetalleQueryService;
import com.copeinca.apicopeincaprov.modules.core.service.query.ISvTrabajadorQueryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
* Implementación del service para SvSolicitudVisitaDetalleEntity
* Extiende de las implementaciones especializadas para reutilizar funcionalidad
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class SvSolicitudVisitaDetalleService implements ISvSolicitudVisitaDetalleService {

    //Repositories
    private final ISvSolicitudVisitaDetalleRepository iSvSolicitudVisitaDetalleRepository;

    //Mapper
    private final SvSolicitudVisitaDetalleMapper svSolicitudVisitaDetalleMapper;

    //Services
//    private final ISvSolicitudVisitaDetalleQueryService queryService;
    private final ISvTrabajadorQueryService iSvTrabajadorQueryService;
    @Override
    public List<SvTrabajadorDTO> getDetail(String id) {
        List<SvSolicitudVisitaDetalleEntity> result =  iSvSolicitudVisitaDetalleRepository.findAllBySolicitudVisitaId(id);
        PagedRequest<SvTrabajadorFilter> filter = new PagedRequest<>();
        filter.setPage(0);
        filter.setOffset(0);
        filter.setLimit(10000);
        filter.setSize(10000);
        FilterOperation sFilter = new FilterOperation<>();
        sFilter.setOperator(OperatorEnum.IN);
        sFilter.setValues(result.size() > 0 ? result.stream().map(SvSolicitudVisitaDetalleEntity::getTrabajadorId).toList(): List.of("123456"));
        filter.setFilter(SvTrabajadorFilter.builder()
//                .nroDocumentoIdentidad(List.of(sFilter))
                        .id(List.of(sFilter))
                .build());
        Map<String, SvSolicitudVisitaDetalleEntity> detalleByTrabajadorId = result.stream()
                .filter(e -> e.getTrabajadorId() != null)
                .collect(Collectors.toMap(
                        SvSolicitudVisitaDetalleEntity::getTrabajadorId,
                        e -> e,
                        (a, b) -> a // si hay duplicado, te quedas con el primero
                ));
        PagedResult<SvTrabajadorDTO> resultTrabajadores = iSvTrabajadorQueryService.search(filter);
        resultTrabajadores.getResult().forEach(t -> {
            var detalle = detalleByTrabajadorId.get(t.getId());
            if (detalle != null) {
                t.setSolicitudVisitaDetalleObs(detalle.getProveedorComentario());
                t.setSolicitudVisitaDetalleId(detalle.getId());
                t.setSolicitudVisitaDetalleVersion(detalle.getVersion());

            }
        });

        return resultTrabajadores.getResult();
    }
}
