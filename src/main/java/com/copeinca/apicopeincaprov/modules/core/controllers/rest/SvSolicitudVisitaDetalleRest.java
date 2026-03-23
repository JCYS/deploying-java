
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
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvSolicitudVisitaDetalleDTO;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvTrabajadorDTO;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.SvSolicitudVisitaDetalleFilter;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvSolicitudVisitaDetalleEntity;
import com.copeinca.apicopeincaprov.modules.core.service.business.ISvSolicitudVisitaDetalleService;
import com.copeinca.apicopeincaprov.modules.core.service.crud.ISvSolicitudVisitaDetalleCrudService;
import com.copeinca.apicopeincaprov.modules.core.service.query.ISvSolicitudVisitaDetalleQueryService;
import com.copeinca.apicopeincaprov.modules.core.service.reader.ISvSolicitudVisitaDetalleExcelReaderService;
import com.copeinca.apicopeincaprov.modules.core.service.writer.ISvSolicitudVisitaDetalleWriterService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
* REST Controller para SvSolicitudVisitaDetalleEntity
* Define la API base pero sin endpoints implementados
*/
@RestController
@RequiredArgsConstructor
@RequestMapping(ApiPaths.Core.SVSOLICITUDVISITADETALLE)
public class SvSolicitudVisitaDetalleRest {

    private final ISvSolicitudVisitaDetalleService service;
    private final ISvSolicitudVisitaDetalleCrudService crudService;
    private final ISvSolicitudVisitaDetalleQueryService queryService;
    private final ISvSolicitudVisitaDetalleWriterService writerService;
    private final ISvSolicitudVisitaDetalleExcelReaderService readerService;

    // Los endpoints serán implementados según las necesidades específicas del negocio
    // Esta clase sirve como base para la definición de la API REST

    @GetMapping(value = "/{id}")
    public CustomMessageView<SvSolicitudVisitaDetalleDTO> findById(@PathVariable("id") String id) {

        SvSolicitudVisitaDetalleDTO result = queryService.findById(id);

        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/search")
    public CustomMessageView<PagedResult<SvSolicitudVisitaDetalleDTO>> search(@RequestBody PagedRequest<SvSolicitudVisitaDetalleFilter> filter) {

        PagedResult<SvSolicitudVisitaDetalleDTO> result = queryService.search(filter);

        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/download-excel")
    @ResponseStatus(HttpStatus.OK)
    public void downloadExcel(@RequestBody PagedRequest<SvSolicitudVisitaDetalleFilter> filter, HttpServletResponse httpServletResponse) throws Exception {

        PagedResult<SvSolicitudVisitaDetalleDTO> data = queryService.search(filter);

        AttachmentData attachmentData = writerService.exportToExcel(data.getResult());

        attachmentData.writeToResponse(httpServletResponse);

    }

    @PostMapping(value = "/read-excel", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public CustomMessageView<List<SvSolicitudVisitaDetalleDTO>> readExcel(@RequestPart("file") MultipartFile file) throws IOException {

        List<SvSolicitudVisitaDetalleDTO> result = readerService.readFromExcel(file.getInputStream());

        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/save")
    @ResponseStatus(HttpStatus.OK)
    public CustomMessageView<SvSolicitudVisitaDetalleEntity> save(@RequestBody @Valid SvSolicitudVisitaDetalleDTO dto) {

        SvSolicitudVisitaDetalleEntity result = crudService.save(dto);

        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/save-all")
    @ResponseStatus(HttpStatus.OK)
    public CustomMessageView<List<SvSolicitudVisitaDetalleEntity>> saveAll(@RequestBody List<SvSolicitudVisitaDetalleDTO> list) {

        List<SvSolicitudVisitaDetalleEntity> result = crudService.saveAll(list);

        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/soft-delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomMessageView<Boolean> softDelete(@PathVariable("id") String id) {

        boolean result = crudService.softDeleteById(id);

        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/detalletrabajadores/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomMessageView<?> getDetail(@PathVariable("id") String id) {

        List<SvTrabajadorDTO> result = service.getDetail(id);

        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/restore/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomMessageView<Boolean> restore(@PathVariable("id") String id) {

        boolean result = crudService.restoreById(id);

        return CustomMessageView.success(result);

    }

}
