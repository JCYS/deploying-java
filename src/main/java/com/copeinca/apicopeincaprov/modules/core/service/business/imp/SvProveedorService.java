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
import com.copeinca.apicopeincaprov.modules.core.mappers.SvProveedorMapper;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvProveedorDTO;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvSedeDTO;
import com.copeinca.apicopeincaprov.modules.core.models.dto.ViewTrabajadorReportDTO;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.SvProveedorFilter;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.ViewTrabajadorReportFilter;
import com.copeinca.apicopeincaprov.modules.core.models.entities.*;
import com.copeinca.apicopeincaprov.modules.core.repositories.jpa.*;
import com.copeinca.apicopeincaprov.modules.core.service.business.ISvProveedorService;
import com.copeinca.apicopeincaprov.modules.core.service.crud.ISvProveedorCrudService;
import com.copeinca.apicopeincaprov.modules.core.service.query.ISvProveedorQueryService;
import com.copeinca.apicopeincaprov.modules.notificacion.service.INotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Implementación del service para SvProveedorEntity
 * Extiende de las implementaciones especializadas para reutilizar funcionalidad
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SvProveedorService implements ISvProveedorService {

    private final ISvProveedorRepository iSvProveedorRepository;
    private final INotificationService iNotificationService;
    private final SvProveedorMapper mapper;
    private final ISvProveedorQueryService queryService;
    private final ISvProveedorCrudService crudService;

    private final ISvProveedorSedeRepository iSvProveedorSedeRepository;

    private final ISvSedeRepository iSvSedeRepository;
    private final ISvEstadoCalidadAmbientalRepository iSvEstadoCalidadAmbientalRepository;
    private final ISvEstadoSsoRepository iSvEstadoSsoRepository;

    @Override
    public SvProveedorDTO getProv( String sEmail ) throws Exception {
        List<SvProveedorEntity> aListProv = iSvProveedorRepository.findAllByEmail( sEmail );
        SvProveedorDTO oProvReturn = null;
        if( aListProv.size( ) > 0 ) {
            oProvReturn = mapper.entityToDto( aListProv.get( 0 ) );
        }
        return oProvReturn;
    }

    @Override
    public void updateSsoEvaluation( SvProveedorDTO svProveedorDTO ) {
        String proveedorId = svProveedorDTO.getId( );

        iSvProveedorRepository.updateEstadoSsoCode( svProveedorDTO.getEstadoSsoCode( ), proveedorId );
        iSvProveedorRepository.updateSsoTieneVigencia( svProveedorDTO.getSsoTieneVigencia( ), proveedorId );
        iSvProveedorRepository.updateSsoFechaInicioVigencia( svProveedorDTO.getSsoFechaInicioVigencia( ), proveedorId );
        iSvProveedorRepository.updateSsoFechaFinVigencia( svProveedorDTO.getSsoFechaFinVigencia( ), proveedorId );
        iSvProveedorRepository.updateSsoMotivo( svProveedorDTO.getSsoMotivo( ), proveedorId );
        iSvProveedorRepository.updateSsoUsuarioEvaluador( svProveedorDTO.getSsoUsuarioEvaluador( ), proveedorId );
        iSvProveedorRepository.updateSsoFechaEvaluacion( svProveedorDTO.getSsoFechaEvaluacion( ), proveedorId );

        //--- Enviar notificación
        try {

            iNotificationService.sendEvaluacionProveedor( proveedorId );

        } catch( Exception e ) {

            log.error( e.getMessage( ) );

        }

    }

    @Override
    public void updateCaEvaluation( SvProveedorDTO svProveedorDTO ) {
        String proveedorId = svProveedorDTO.getId( );

        iSvProveedorRepository.updateEstadoCalidadAmbientalCode( svProveedorDTO.getEstadoCalidadAmbientalCode( ), proveedorId );
        iSvProveedorRepository.updateCaTieneVigencia( svProveedorDTO.getCaTieneVigencia( ), proveedorId );
        iSvProveedorRepository.updateCaFechaInicioVigencia( svProveedorDTO.getCaFechaInicioVigencia( ), proveedorId );
        iSvProveedorRepository.updateCaFechaFinVigencia( svProveedorDTO.getCaFechaFinVigencia( ), proveedorId );
        iSvProveedorRepository.updateCaMotivo( svProveedorDTO.getCaMotivo( ), proveedorId );
        iSvProveedorRepository.updateCaUsuarioEvaluador( svProveedorDTO.getCaUsuarioEvaluador( ), proveedorId );
        iSvProveedorRepository.updateCaFechaEvaluacion( svProveedorDTO.getCaFechaEvaluacion( ), proveedorId );

        //--- Enviar notificación
        try {

            iNotificationService.sendEvaluacionProveedor( proveedorId );

        } catch( Exception e ) {

            log.error( e.getMessage( ) );

        }

    }
    private static String norm(String s) {
        if (s == null) return null;
        String t = Normalizer.normalize(s, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "");      // quita tildes
        t = t.trim().replaceAll("\\s+", " ");  // compacta espacios
        return t.toUpperCase(Locale.ROOT);
    }

    private static Set<String> parseSedes(String sedesNombres) {
        if (sedesNombres == null || sedesNombres.isBlank()) return Collections.emptySet();

        return Arrays.stream(sedesNombres.split("\\|"))
                .map(String::trim)
                .filter(s -> !s.isBlank())
                .map(SvProveedorService::norm)  // ajusta el nombre de clase
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }
    private static boolean sedesSonPermitidas(Set<String> sedesFila, Set<String> permitidas) {
        if (sedesFila.isEmpty()) return true; // define tu regla: ¿vacío es válido?
        Set<String> diff = new HashSet<>(sedesFila);
        diff.removeAll(permitidas);
        return diff.isEmpty(); // si no queda nada, todas estaban permitidas
    }


    @Override
    public List<SvProveedorDTO> isExist(List<SvProveedorDTO> result, List<SvSedeDTO> aSedes) {
        PagedRequest<SvProveedorFilter> filter = new PagedRequest<>();
        filter.setPage(0);
        filter.setOffset(0);
        filter.setLimit(10000);
        filter.setSize(10000);
        FilterOperation sFilter = new FilterOperation<>();
        sFilter.setOperator(OperatorEnum.IN);
        sFilter.setValues(result.stream().map(SvProveedorDTO::getNroDocumentoIdentidad).toList());
        filter.setFilter(SvProveedorFilter.builder()
                        .nroDocumentoIdentidad(List.of(sFilter))
                .build());

        PagedResult<SvProveedorDTO> resultTrabajadores = queryService.search(filter);


        Map<String, SvProveedorDTO> docsEnBD = resultTrabajadores.getResult().stream()
                .collect(Collectors.toMap(
                        SvProveedorDTO::getNroDocumentoIdentidad,
                        dto -> dto,
                        (a, b) -> a // por si hay duplicados
                ));

       //List<String> sedesPermitidasList = /* iSvSedeRepository.findAllActivasNombres() */ List.of("CHANCAY");
        //List<String> sedesPermitidasList = aSedes.stream().map(SvSedeDTO::getName).toList();
        Set<String> permitidas = aSedes.stream()
                .filter(Objects::nonNull)
                .map(SvSedeDTO ->
                    SvProveedorService.norm(SvSedeDTO.getName())
                ) // ajusta
                .collect(Collectors.toSet());

        result.forEach(dto -> {
            String doc = dto.getNroDocumentoIdentidad();
            dto.setIsExists(docsEnBD.containsKey(doc));
            Set<String> sedesFila = parseSedes(dto.getSedesNombres());

            // sedes inválidas = sedesFila - sedesPermitidas
            boolean valido = sedesSonPermitidas(sedesFila, permitidas);

            // Como tu DTO no tiene campo "valido", tienes 3 opciones:
            // A) lanzar error si no es válido
            // B) marcar/guardar un campo nuevo (recomendado)
            // C) limpiar sedes (pero tú dijiste que X,Z debe ser inválido, así que no conviene “arreglarlo”)
            dto.setObservacion("Correcto");
            if (!valido) {
                //dto.setIsExists(true);
                dto.setObservacion("Este registro no cumple con la validacion de sedes asignadas al usuario.");
            }
        });

        return result;
    }

    @Override
    public List<SvProveedorDTO> saveAllAndValidation(List<SvProveedorDTO> list) {

        Map<String, SvEstadoCalidadAmbientalEntity> mapSetCalidad = iSvEstadoCalidadAmbientalRepository.findAll().stream().collect(
                Collectors.toMap(
                        SvEstadoCalidadAmbientalEntity::getName,
                        e-> e
                )
        );

        Map<String, SvEstadoSsoEntity> mapSetSSO = iSvEstadoSsoRepository.findAll().stream().collect(
                Collectors.toMap(
                        SvEstadoSsoEntity::getName,
                        e-> e
                )
        );

        // 1) Normaliza llave
        Function<SvProveedorDTO, String> keyFn =
                dto -> dto.getNroDocumentoIdentidad() == null ? "" : dto.getNroDocumentoIdentidad().trim();

// 2) Separa: primer registro por doc se queda, los demás se van a duplicados
        Set<String> seen = new HashSet<>();

        List<SvProveedorDTO> noDuplicados = new ArrayList<>();
        List<SvProveedorDTO> duplicados = new ArrayList<>();

        for (SvProveedorDTO dto : list) {
            dto.setEstadoSsoCode(mapSetSSO.get(dto.getEstadoSsoCode()).getId());
            dto.setEstadoCalidadAmbientalCode(mapSetCalidad.get(dto.getEstadoCalidadAmbientalCode()).getId());
            String key = keyFn.apply(dto);

            // si viene vacío/null, NO lo tratamos como duplicado aquí (deja que tu validación lo observe)
            if (key.isBlank()) {
                noDuplicados.add(dto);
                continue;
            }

            if (seen.add(key)) {
                // primera vez que aparece ese documento -> válido para seguir el flujo normal
                noDuplicados.add(dto);
            } else {
                // repetido -> duplicado
                dto.setObservacion("Registro duplicado");
                duplicados.add(dto);
            }
        }


        List<SvProveedorDTO> aNews = noDuplicados.stream()
                .filter(dto -> !dto.getIsExists())
                .filter(dto -> "Correcto".equals(dto.getObservacion()))
                .toList();
        List<SvProveedorDTO> aExists = noDuplicados.stream()
                .filter(dto -> !"Correcto".equals(dto.getObservacion()))
                .toList();

        List<SvProveedorEntity> result = crudService.saveAll(aNews);

        Map<String, String> docToProvId = result.stream()
                .collect(Collectors.toMap(
                        SvProveedorEntity::getNroDocumentoIdentidad,
                        SvProveedorEntity::getId,
                        (a, b) -> a
                ));
        Map<String, String> sedeNombreToId = iSvSedeRepository.findAll().stream()
                .collect(Collectors.toMap(
                        SvSedeEntity::getName,   // o getCode()
                        SvSedeEntity::getId,
                        (a, b) -> a
                ));

        List<SvProveedorSedeEntity> relaciones = aNews.stream()
                .flatMap(dto -> {
                    String provId = docToProvId.get(dto.getNroDocumentoIdentidad());
                    if (provId == null) return Stream.<SvProveedorSedeEntity>empty();

                    Set<String> sedes = parseSedes(dto.getSedesNombres());

                    return sedeNombreToId.values().stream()
                            //.map(sedeNombreToId::get)
                            //.filter(Objects::nonNull) // sedes válidas
                            .map(sedeId -> SvProveedorSedeEntity.builder()
                                    .proveedorId(provId)
                                    .sedeId(sedeId)
                                    .isDeleted(false)
                                    .isActive(true)
                                    .build());
                })
                .toList();

        iSvProveedorSedeRepository.saveAll(relaciones);

//        List<SvProveedorDTO> finalList = Stream
//                .concat(result.stream(), aExists.stream())
//                .toList();
//
//        return CustomMessageView.success(finalList);
        List<SvProveedorDTO> savedDtos = result.stream()
                .map(mapper::entityToDto)
                .map(dto -> { dto.setIsExists(true);
                    dto.setObservacion("Guardado correctamente."); ; return dto; })
                .toList();

//        List<SvProveedorDTO> finalList = Stream
//                .concat(savedDtos.stream(), aExists.stream())
//                .toList();
        return Stream
                .of(savedDtos.stream(), aExists.stream(), duplicados.stream())
                .flatMap(s -> s)
                .toList();

//        return CustomMessageView.success(finalList);
    }

    @Override
    public SvProveedorDTO getProvByRuc(String ruc) {
        List<SvProveedorEntity> aListProv = iSvProveedorRepository.findAllByNroDocumentoIdentidad( ruc );
        SvProveedorDTO oProvReturn = null;
        if( aListProv.size( ) > 0 ) {
            oProvReturn = mapper.entityToDto( aListProv.get( 0 ) );
        }
        return oProvReturn;
    }
}
