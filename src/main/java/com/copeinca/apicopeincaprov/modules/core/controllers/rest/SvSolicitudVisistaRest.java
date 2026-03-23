
/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/controllers/rest/EntityRest.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.controllers.rest;

import com.copeinca.apicopeincaprov.commons.controllers.rest.ApiPaths;
import com.copeinca.apicopeincaprov.global.dtos.AttachmentData;
import com.copeinca.apicopeincaprov.global.dtos.response.CustomMessageView;
import com.copeinca.apicopeincaprov.global.dtos.response.PagedRequest;
import com.copeinca.apicopeincaprov.global.dtos.response.PagedResult;
import com.copeinca.apicopeincaprov.integration.Mendix.dtos.imput.ActualizarCasoRequest;
import com.copeinca.apicopeincaprov.integration.Mendix.dtos.imput.WsActualizaVisitaTerceraRequest;
import com.copeinca.apicopeincaprov.integration.Mendix.dtos.response.ResultadoWSActualiza;
import com.copeinca.apicopeincaprov.modules.core.models.dto.*;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.SvSolicitudVisistaFilter;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvSolicitudVisistaEntity;
import com.copeinca.apicopeincaprov.modules.core.service.business.ISvSolicitudVisitaService;
import com.copeinca.apicopeincaprov.modules.core.service.crud.ISvSolicitudVisistaCrudService;
import com.copeinca.apicopeincaprov.modules.core.service.query.ISvSolicitudVisistaQueryService;
import com.copeinca.apicopeincaprov.modules.core.service.reader.ISvSolicitudVisistaExcelReaderService;
import com.copeinca.apicopeincaprov.modules.core.service.writer.ISvSolicitudVisistaWriterService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
* REST Controller para SvSolicitudVisistaEntity
* Define la API base pero sin endpoints implementados
*/
@RestController
@RequiredArgsConstructor
@RequestMapping(ApiPaths.Core.SVSOLICITUDVISISTA)
public class SvSolicitudVisistaRest {

    private final ISvSolicitudVisitaService service;
    private final ISvSolicitudVisistaCrudService crudService;
    private final ISvSolicitudVisistaQueryService queryService;
    private final ISvSolicitudVisistaWriterService writerService;
    private final ISvSolicitudVisistaExcelReaderService readerService;

    // Los endpoints serán implementados según las necesidades específicas del negocio
    // Esta clase sirve como base para la definición de la API REST

    @GetMapping(value = "/{id}")
    public CustomMessageView<SvSolicitudVisistaDTO> findById(@PathVariable("id") String id) {

        SvSolicitudVisistaDTO result = queryService.findById(id);

        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/search")
    public CustomMessageView<PagedResult<SvSolicitudVisistaDTO>> search(@RequestBody PagedRequest<SvSolicitudVisistaFilter> filter) {

        PagedResult<SvSolicitudVisistaDTO> result = queryService.search(filter);

        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/download-excel")
    @ResponseStatus(HttpStatus.OK)
    public void downloadExcel(@RequestBody PagedRequest<SvSolicitudVisistaFilter> filter, HttpServletResponse httpServletResponse) throws Exception {

        PagedResult<SvSolicitudVisistaDTO> data = queryService.search(filter);

        AttachmentData attachmentData = writerService.exportToExcel(data.getResult());

        attachmentData.writeToResponse(httpServletResponse);

    }

    @PostMapping(value = "/read-excel", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public CustomMessageView<List<SvSolicitudVisistaDTO>> readExcel(@RequestPart("file") MultipartFile file) throws IOException {

        List<SvSolicitudVisistaDTO> result = readerService.readFromExcel(file.getInputStream());

        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/save")
    @ResponseStatus(HttpStatus.OK)
    public CustomMessageView<SvSolicitudVisistaEntity> save(@RequestBody @Valid SvSolicitudVisistaDTO dto) {

        SvSolicitudVisistaEntity result = crudService.save(dto);

        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/save-all")
    @ResponseStatus(HttpStatus.OK)
    public CustomMessageView<List<SvSolicitudVisistaEntity>> saveAll(@RequestBody List<SvSolicitudVisistaDTO> list) {

        List<SvSolicitudVisistaEntity> result = crudService.saveAll(list);

        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/soft-delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomMessageView<Boolean> softDelete(@PathVariable("id") String id) {

        boolean result = crudService.softDeleteById(id);

        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/restore/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomMessageView<Boolean> restore(@PathVariable("id") String id) {

        boolean result = crudService.restoreById(id);

        return CustomMessageView.success(result);

    }


    @PostMapping(
            value = "/registrovisita",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public CustomMessageView<SvProveedorCitaRegistroDTO> subirDocumentos(
            @RequestPart(value = "solicitud", required = true) SvProveedorCitaRegistroDTO solicitud,
            @RequestPart(value = "hsFilesSSO", required = false) List<MultipartFile> ahsFilesSSO,
            @RequestPart(value = "hsFilesQA", required = false) List<MultipartFile> ahsFilesQA,
            @RequestPart(value = "hsFilesAAR", required = false) List<MultipartFile> ahsFilesAAR,
            @RequestPart(value = "hsFilesAB", required = false) List<MultipartFile> ahsFilesAB,
            @RequestPart(value = "hsFilesNA", required = false) List<MultipartFile> ahsFilesNA,
            @RequestPart(value = "hsFilesNG", required = false) List<MultipartFile> ahsFilesNG,
            @RequestPart(value = "hsFilesEM", required = false) List<MultipartFile> ahsFilesEM,
            @RequestPart(value = "hsFilesTB", required = false) List<MultipartFile> ahsFilesTB
    ) throws Exception {
        return CustomMessageView.success(service.saveInfo(solicitud, ahsFilesSSO, ahsFilesQA, ahsFilesAAR, ahsFilesAB, ahsFilesNA, ahsFilesNG, ahsFilesEM, ahsFilesTB));
    }

    @GetMapping(value = "/registrovisita/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomMessageView<SvProveedorCitaRegistroDTO> getInformationVisita(@PathVariable("id") String sId) {

//        SvRegistroProveedorDto result = new SvRegistroProveedorDto();
//        result.setProveedorInfo(queryService.findById(id));
        SvProveedorCitaRegistroDTO result = service.getInfoVisita(sId);

        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/registroDetalleVisita")
    @ResponseStatus(HttpStatus.OK)
    public CustomMessageView<List<SvSolicitudVisitaDetalleDTO>> saveDetail(@RequestBody List<SvSolicitudVisitaDetalleDTO> list) {

        List<SvSolicitudVisitaDetalleDTO> result = service.saveDetalle(list);

        return CustomMessageView.success(result);

    }

    @PostMapping( value = "/actualizarEstadoVisitaReleased" )
    public CustomMessageView<?> actualizarEstadoSolicitudVisitaReleased( @RequestBody SvSolicitudVisistaDTO dto ) {
        service.wsActualizaVisitaTercera( dto );

        return CustomMessageView.success( dto );
    }

    @PostMapping( value = "/wsActualizaVisitaTercera" )
    public ResponseEntity<?> actualizarEstadoSolicitudVisita(@RequestBody WsActualizaVisitaTerceraRequest request ) {
        ResultadoWSActualiza resultado = service.wsActualizaVisitaTercera( request );
        return ResponseEntity.ok( resultado );
    }

//    @PostMapping( value = "/wsActualizaVisitaMendix" )
//    public ResponseEntity<?> actualizarEstadoMendix(@RequestBody ActualizarCasoRequest request ) {
//        ResultadoWSActualiza resultado = service.wsActualizaMendix( request, null );
//        return ResponseEntity.ok( resultado );
//    }


}
