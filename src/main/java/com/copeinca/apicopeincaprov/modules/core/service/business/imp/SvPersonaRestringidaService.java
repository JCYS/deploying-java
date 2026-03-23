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
import com.copeinca.apicopeincaprov.modules.core.mappers.SvPersonaRestringidaMapper;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvPersonaRestringidaDTO;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvProveedorDTO;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvTipoDocumentoIdentidadDTO;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.SvPersonaRestringidaFilter;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.SvProveedorFilter;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvSedeEntity;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvTipoDocumentoIdentidadEntity;
import com.copeinca.apicopeincaprov.modules.core.repositories.jpa.ISvPersonaRestringidaRepository;
import com.copeinca.apicopeincaprov.modules.core.repositories.jpa.ISvSedeRepository;
import com.copeinca.apicopeincaprov.modules.core.repositories.jpa.ISvTipoDocumentoIdentidadRepository;
import com.copeinca.apicopeincaprov.modules.core.service.business.ISvPersonaRestringidaService;
import com.copeinca.apicopeincaprov.modules.core.service.crud.ISvPersonaRestringidaCrudService;
import com.copeinca.apicopeincaprov.modules.core.service.query.ISvPersonaRestringidaQueryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
* Implementación del service para SvPersonaRestringidaEntity
* Extiende de las implementaciones especializadas para reutilizar funcionalidad
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class SvPersonaRestringidaService implements ISvPersonaRestringidaService {

    private final ISvPersonaRestringidaRepository iSvPersonaRestringidaRepository;
    private final ISvPersonaRestringidaQueryService queryService;

    private final ISvTipoDocumentoIdentidadRepository iSvTipoDocumentoIdentidadRepository;
    private final ISvSedeRepository iSvSedeRepository;

    private final ISvPersonaRestringidaCrudService crudService;

    private final SvPersonaRestringidaMapper mapper;

    @Override
    public List<SvPersonaRestringidaDTO> isExist(List<SvPersonaRestringidaDTO> result) {
        PagedRequest<SvPersonaRestringidaFilter> filter = new PagedRequest<>();
        filter.setPage(0);
        filter.setOffset(0);
        filter.setLimit(10000);
        filter.setSize(10000);
        FilterOperation sFilter = new FilterOperation<>();
        sFilter.setOperator(OperatorEnum.IN);
        sFilter.setValues(result.stream().map(SvPersonaRestringidaDTO::getNumeroDocumento).toList());

        Map<String, SvTipoDocumentoIdentidadEntity> tdiMap =
                iSvTipoDocumentoIdentidadRepository.findAll()
                        .stream()
                        .collect(Collectors.toMap(
                                SvTipoDocumentoIdentidadEntity::getName,
                                e -> e
                        ));
        result.forEach(dto -> {
            SvTipoDocumentoIdentidadEntity tdi =
                    tdiMap.get(dto.getTipoDocumentoIdentidadCode());
            dto.setTipoDocumentoIdentidadCode(tdi.getId());
        });
        filter.setFilter(SvPersonaRestringidaFilter.builder()
                .numeroDocumento(List.of(sFilter))
                .tipoDocumentoIdentidadCodeIds(result.stream().map(SvPersonaRestringidaDTO::getTipoDocumentoIdentidadCode).toList())
                .build());

        PagedResult<SvPersonaRestringidaDTO> resultTrabajadores = queryService.search(filter);


        Map<String, SvPersonaRestringidaDTO> docsEnBD = resultTrabajadores.getResult().stream()
                .collect(Collectors.toMap(
                        SvPersonaRestringidaDTO::getNumeroDocumento,
                        dto -> dto,
                        (a, b) -> a // por si hay duplicados
                ));
        Map<String, SvSedeEntity> idSedes = iSvSedeRepository.findAll().stream()
                .collect(Collectors.toMap(
                        SvSedeEntity::getName,
                        dto -> dto,
                        (a, b) -> a // por si hay duplicados
                ));
        result.forEach(dto -> {
            String doc = dto.getNumeroDocumento();
            dto.setIsExists(docsEnBD.containsKey(doc));
            String sedeName = dto.getSedeOriginadoraId();
            dto.setSedeOriginadoraId(idSedes.get(sedeName) == null ? "" : idSedes.get(sedeName).getId());
            dto.setIsDeleted(false);
        });

        return result;
    }

    @Override
    public List<SvPersonaRestringidaDTO> addSedeID(List<SvPersonaRestringidaDTO> aNews) {
//         List<SvTipoDocumentoIdentidadEntity> aTDI = iSvTipoDocumentoIdentidadRepository.findAll();
//         List<SvSedeEntity> aSede = iSvSedeRepository.findAll();

        Map<String, SvTipoDocumentoIdentidadEntity> tdiMap =
                iSvTipoDocumentoIdentidadRepository.findAll()
                        .stream()
                        .collect(Collectors.toMap(
                                SvTipoDocumentoIdentidadEntity::getName,
                                e -> e
                        ));

        Map<String, SvSedeEntity> sedeMap =
                iSvSedeRepository.findAll()
                        .stream()
                        .collect(Collectors.toMap(
                                SvSedeEntity::getName,
                                e -> e
                        ));
        aNews.forEach(dto -> {

            // ---- Tipo Documento
            SvTipoDocumentoIdentidadEntity tdi =
                    tdiMap.get(dto.getTipoDocumentoIdentidadCode());

            if (tdi != null) {
                dto.setTipoDocumentoIdentidadCode(tdi.getId());
            }

            // ---- Sede
            SvSedeEntity sede =
                    sedeMap.get(dto.getSedeOriginadoraId());

            if (sede != null) {
                dto.setSedeOriginadoraId(sede.getId());
            }
        });
//         for
        return aNews;
    }

    @Override
    public List<SvPersonaRestringidaDTO> saveAllAndValidation(List<SvPersonaRestringidaDTO> list) {
        //        List<SvPersonaRestringidaDTO> aNews = list.stream()
//                .filter(dto -> !dto.getIsExists())
////                .peek(svPersonaRestringidaDTO -> {
////                    svPersonaRestringidaDTO.setSedeOriginadoraId("ef6f00b1-7893-45c0-bc46-46e609ae12e1");
////                })
//                .toList();
//        List<SvPersonaRestringidaDTO> aExists = list.stream()
//                .filter(SvPersonaRestringidaDTO::getIsExists)
//                .toList();
//        List<SvPersonaRestringidaDTO> aNesF = service.addSedeID(aNews);
//        List<SvPersonaRestringidaDTO> result = crudService.saveAll(aNesF).stream().map(mapper::entityToDto).peek(dto -> {
//            dto.setIsExists(true);
//        }).toList();
//        List<SvPersonaRestringidaDTO> finalList = Stream
//                .concat(result.stream(), aExists.stream())
//                .toList();
//
//        return CustomMessageView.success(finalList);

        // 1) Nuevos (no existen) vs existentes
        List<SvPersonaRestringidaDTO> aNewsRaw = list.stream()
                .filter(dto -> !Boolean.TRUE.equals(dto.getIsExists()))
                .toList();

        List<SvPersonaRestringidaDTO> aExists = list.stream()
                .filter(dto -> Boolean.TRUE.equals(dto.getIsExists()))
                .toList();

        // 2) Deduplicar aNews: dejar pasar solo 1 por clave y marcar duplicados con observacion = "registro duplicado"
        //    Clave recomendada: tipoDocumento + numeroDocumento (ajusta si quieres incluir sedeOriginadoraId u otro campo)
        Map<String, SvPersonaRestringidaDTO> firstByKey = new LinkedHashMap<>();
        List<SvPersonaRestringidaDTO> duplicadosMarcados = new ArrayList<>();

        for (SvPersonaRestringidaDTO dto : aNewsRaw) {
            String key = (dto.getTipoDocumentoIdentidadCode() == null ? "" : dto.getTipoDocumentoIdentidadCode().trim())
                    + "|" +
                    (dto.getNumeroDocumento() == null ? "" : dto.getNumeroDocumento().trim());

            SvPersonaRestringidaDTO prev = firstByKey.putIfAbsent(key, dto);

            if (prev != null) {
                dto.setObservacion("registro duplicado");
                duplicadosMarcados.add(dto);
            }
        }

        List<SvPersonaRestringidaDTO> aNewsDedup = new ArrayList<>(firstByKey.values());

        // 3) Tu lógica normal
        List<SvPersonaRestringidaDTO> aNesF = addSedeID(aNewsDedup);

        List<SvPersonaRestringidaDTO> guardados = crudService.saveAll(aNesF).stream()
                .map(mapper::entityToDto)
                .peek(dto -> dto.setIsExists(true))
                .toList();


        return Stream
                .of(guardados, aExists, duplicadosMarcados)
                .flatMap(List::stream)
                .toList();
    }
}
