
/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/controllers/rest/EntityRest.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.controllers.rest;

import com.copeinca.apicopeincaprov.commons.controllers.rest.ApiPaths;
import com.copeinca.apicopeincaprov.global.dtos.AttachmentData;
import com.copeinca.apicopeincaprov.global.dtos.response.CustomMessageView;
import com.copeinca.apicopeincaprov.global.dtos.response.MessageViewModel;
import com.copeinca.apicopeincaprov.global.dtos.response.PagedRequest;
import com.copeinca.apicopeincaprov.global.dtos.response.PagedResult;
import com.copeinca.apicopeincaprov.modules.core.mappers.SvTrabajadorMapper;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvProveedorDTO;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvRegistroProveedorDto;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvTrabajadorDTO;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvTrabajadorRegistroDTO;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.SvTrabajadorFilter;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvEstadoCalidadAmbientalEntity;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvEstadoSsoEntity;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvTipoDocumentoIdentidadEntity;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvTrabajadorEntity;
import com.copeinca.apicopeincaprov.modules.core.repositories.jpa.ISvEstadoCalidadAmbientalRepository;
import com.copeinca.apicopeincaprov.modules.core.repositories.jpa.ISvEstadoSsoRepository;
import com.copeinca.apicopeincaprov.modules.core.repositories.jpa.ISvTipoDocumentoIdentidadRepository;
import com.copeinca.apicopeincaprov.modules.core.service.business.ISvTrabajadorService;
import com.copeinca.apicopeincaprov.modules.core.service.crud.ISvTrabajadorCrudService;
import com.copeinca.apicopeincaprov.modules.core.service.query.ISvTrabajadorQueryService;
import com.copeinca.apicopeincaprov.modules.core.service.reader.ISvTrabajadorExcelReaderService;
import com.copeinca.apicopeincaprov.modules.core.service.writer.ISvTrabajadorWriterService;
import io.vavr.control.Try;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
* REST Controller para SvTrabajadorEntity
* Define la API base pero sin endpoints implementados
*/
@RestController
@RequiredArgsConstructor
@RequestMapping(ApiPaths.Core.SVTRABAJADOR)
public class SvTrabajadorRest {

//    private final ClientService clientService;
    private final ISvTrabajadorService service;
    private final ISvTrabajadorCrudService crudService;
    private final ISvTrabajadorQueryService queryService;
    private final ISvTrabajadorWriterService writerService;
    private final ISvTrabajadorExcelReaderService readerService;
    private final SvTrabajadorMapper mapper;


    private final ISvEstadoSsoRepository iSvEstadoSsoRepository;
    private final ISvEstadoCalidadAmbientalRepository iSvEstadoCalidadAmbientalRepository;
    private final ISvTipoDocumentoIdentidadRepository iSvTipoDocumentoIdentidadRepository;
    // Los endpoints serán implementados según las necesidades específicas del negocio
    // Esta clase sirve como base para la definición de la API REST

    @GetMapping(value = "/{id}")
    public CustomMessageView<SvTrabajadorDTO> findById(@PathVariable("id") String id) {

        SvTrabajadorDTO result = queryService.findById(id);

        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/search")
    public CustomMessageView<PagedResult<SvTrabajadorDTO>> search(@RequestBody PagedRequest<SvTrabajadorFilter> filter) {

        PagedResult<SvTrabajadorDTO> result = queryService.search(filter);

        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/download-excel")
    @ResponseStatus(HttpStatus.OK)
    public void downloadExcel(@RequestBody PagedRequest<SvTrabajadorFilter> filter, HttpServletResponse httpServletResponse) throws Exception {

        PagedResult<SvTrabajadorDTO> data = queryService.search(filter);

        AttachmentData attachmentData = writerService.exportToExcel(data.getResult());

        attachmentData.writeToResponse(httpServletResponse);

    }

    @PostMapping(value = "/read-excel", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public CustomMessageView<List<SvTrabajadorDTO>> readExcel(@RequestPart("file") MultipartFile file) throws IOException {

        List<SvTrabajadorDTO> result = readerService.readFromExcel(file.getInputStream());
        service.isExist(result);

        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/save")
    @ResponseStatus(HttpStatus.OK)
    public CustomMessageView<SvTrabajadorEntity> save(@RequestBody @Valid SvTrabajadorDTO dto) {

        SvTrabajadorEntity result = crudService.save(dto);

        return CustomMessageView.success(result);

    }

    private String buildKey(SvTrabajadorDTO dto) {
        String tipo = dto.getTipoDocumentoIdentidadCode() == null ? "" : dto.getTipoDocumentoIdentidadCode().trim();
        String nro  = dto.getNroDocumentoIdentidad() == null ? "" : dto.getNroDocumentoIdentidad().trim();
        return tipo + "|" + nro;
    }

    @PostMapping(value = "/save-all")
    @ResponseStatus(HttpStatus.OK)
    public CustomMessageView<List<SvTrabajadorDTO>> saveAll(@RequestBody List<SvTrabajadorDTO> list) {

        // Mantiene el orden de llegada
        // 1) Deduplicar por key manteniendo orden
        Map<String, SvTrabajadorDTO> firstByKey = new LinkedHashMap<>();
        List<SvTrabajadorDTO> duplicated = new ArrayList<>();
        Map<String, SvEstadoSsoEntity> mapSetSSO = iSvEstadoSsoRepository.findAll().stream().collect(
                Collectors.toMap(
                        SvEstadoSsoEntity::getId,
                        e-> e
                )
        );
        Map<String, SvTipoDocumentoIdentidadEntity> tdiMap =
                iSvTipoDocumentoIdentidadRepository.findAll()
                        .stream()
                        .collect(Collectors.toMap(
                                SvTipoDocumentoIdentidadEntity::getId,
                                e -> e
                        ));
        for (SvTrabajadorDTO dto : list) {
            String key = buildKey(dto);

            if (!firstByKey.containsKey(key)) {
                firstByKey.put(key, dto);
            } else {
                dto.setObservacion("Duplicado por tipoDocumentoIdentidadCode + nroDocumentoIdentidad");
                duplicated.add(dto);
            }
        }

        List<SvTrabajadorDTO> uniques = new ArrayList<>(firstByKey.values());

        // 2) Separar por isExists
        List<SvTrabajadorDTO> existentes = new ArrayList<>();
        List<SvTrabajadorDTO> nuevos = new ArrayList<>();

        for (SvTrabajadorDTO dto : uniques) {
            if (Boolean.TRUE.equals(dto.getIsExists())) {
                dto.setObservacion("Trabajador existe");
                existentes.add(dto);
                dto.setTipoDocumentoIdentidadDescription(tdiMap.get(dto.getTipoDocumentoIdentidadCode()).getName());
                dto.setEstadoSsoDescription(mapSetSSO.get(dto.getEstadoSsoCode()).getName());
            } else {
                dto.setObservacion("Correcto"); // o "Nuevo" / "Registrado"
                nuevos.add(dto);
            }
        }

        // 3) Guardar SOLO los nuevos
        List<SvTrabajadorEntity> savedEntities = nuevos.isEmpty()
                ? List.of()
                : crudService.saveAll(nuevos);

        // 4) Si quieres devolver los guardados como DTOs "reales" (con ID, etc.)
        //    usa tu mapper (ajusta el nombre según tu proyecto)
        List<SvTrabajadorDTO> guardados = savedEntities.stream()
                //.peek(dto-> dto.setIs)
                .map(mapper::entityToDto) // <-- ajusta
                .toList();

        // Mantén observación en los guardados
        guardados.forEach(d -> {
            d.setObservacion("Guardado");
            d.setTipoDocumentoIdentidadDescription(tdiMap.get(d.getTipoDocumentoIdentidadCode()).getName());
            d.setEstadoSsoDescription(mapSetSSO.get(d.getEstadoSsoCode()).getName());
            d.setIsExists(true);
        });

        // 5) Respuesta final (orden sugerido)
        List<SvTrabajadorDTO> response = new ArrayList<>(existentes.size() + guardados.size() + duplicated.size());
        response.addAll(existentes);
        response.addAll(guardados);
        response.addAll(duplicated);

        return CustomMessageView.success(response);

    }


    @PostMapping(value = "/soft-delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomMessageView<?> softDelete(@PathVariable("id") String id) {

        boolean isFiles = service.findFiles(id);
        if(isFiles){
            boolean result = crudService.softDeleteById(id);
            return CustomMessageView.success(result);
        }
        CustomMessageView<Object> build = CustomMessageView.builder().successfully(false).finished(true).messages(List.of(
                MessageViewModel.builder()
                        .type("ERROR")
                        .title("Eliminar trabajador")
                        .description("Trabajador tiene documentos adjuntos.")
                        .build()
        )).response(isFiles).build();
        return build;

    }

    @PostMapping(value = "/active/{id}/{active}")
    @ResponseStatus(HttpStatus.OK)
    public CustomMessageView<Boolean> isActive(@PathVariable("id") String id,@PathVariable("active") Boolean active) {

        boolean result = crudService.isActived(id, active);

        return CustomMessageView.success(null);

    }

    @PostMapping(value = "/restore/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomMessageView<Boolean> restore(@PathVariable("id") String id) {

        boolean result = crudService.restoreById(id);

        return CustomMessageView.success(result);

    }

    @PostMapping(
            value = "/registrar",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<CustomMessageView<?>>  subirDocumentos(
            @RequestPart(value = "trabajador", required = true) SvTrabajadorRegistroDTO trabajador,
            @RequestPart(value = "filesSSO", required = false) List<MultipartFile> aFilesSSO,
            @RequestPart(value = "filesAAR", required = false) List<MultipartFile> aFilesAAR,
            @RequestPart(value = "filesP", required = false) List<MultipartFile> aFilesP
    ) throws Exception {
        try{
            return ResponseEntity.ok(CustomMessageView.success(service.registrarTrabajador(trabajador,aFilesSSO,aFilesAAR,aFilesP)));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(CustomMessageView.builder().successfully(false).finished(false).messages(List.of(
                    MessageViewModel.builder()
                            .title("Creacion trabajador")
                            .description(e.getMessage())
                            .build()
            )).response(trabajador).build());
        }
    }

    @GetMapping(value = "/trabajador/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomMessageView<SvTrabajadorRegistroDTO> getTrabajador(@PathVariable("id") String id) {
        SvTrabajadorRegistroDTO svTrabajadorRegistroDTO = service.getTrabajador(id);
        return CustomMessageView.success(svTrabajadorRegistroDTO);

    }

//    @PostMapping(value = "/getTrabajadoresAptos")
//    public CustomMessageView<OutputResponse> getTrabajadoresAptos( @RequestBody FiltroConsultaRequest request ) {
//        OutputResponse response = clientService.wsRecuperaProveedoresTrabajadores( request );
//
//        if( response != null && response.getValue( ) != null ) {
//            response.getValue( ).forEach( valueItem -> {
//                if( valueItem.getTrabajadores( ) != null &&
//                        valueItem.getTrabajadores( ).getTrabajador( ) != null ) {
//
//                    List<Trabajador> aptos = valueItem.getTrabajadores( )
//                            .getTrabajador( )
//                            .stream( )
//                            .filter( t -> !"0".equals( t.getEsApto( ) ) )
//                            .toList( );
//
//                    valueItem.getTrabajadores( ).setTrabajador( aptos );
//                }
//            } );
//        }
//
//        return CustomMessageView.success(response);
//
//    }

}
