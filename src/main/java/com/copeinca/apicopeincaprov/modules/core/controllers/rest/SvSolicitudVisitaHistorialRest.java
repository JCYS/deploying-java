
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
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvSolicitudVisitaHistorialDTO;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.SvSolicitudVisitaHistorialFilter;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvSolicitudVisitaHistorialEntity;
import com.copeinca.apicopeincaprov.modules.core.service.business.ISvSolicitudVisitaHistorialService;
import com.copeinca.apicopeincaprov.modules.core.service.crud.ISvSolicitudVisitaHistorialCrudService;
import com.copeinca.apicopeincaprov.modules.core.service.query.ISvSolicitudVisitaHistorialQueryService;
import com.copeinca.apicopeincaprov.modules.core.service.reader.ISvSolicitudVisitaHistorialExcelReaderService;
import com.copeinca.apicopeincaprov.modules.core.service.writer.ISvSolicitudVisitaHistorialWriterService;
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
* REST Controller para SvSolicitudVisitaHistorialEntity
* Define la API base pero sin endpoints implementados
*/
@RestController
@RequiredArgsConstructor
@RequestMapping(ApiPaths.Core.SVSOLICITUDVISITAHISTORIAL)
public class SvSolicitudVisitaHistorialRest {

    private final ISvSolicitudVisitaHistorialService service;
    private final ISvSolicitudVisitaHistorialCrudService crudService;
    private final ISvSolicitudVisitaHistorialQueryService queryService;
    private final ISvSolicitudVisitaHistorialWriterService writerService;
    private final ISvSolicitudVisitaHistorialExcelReaderService readerService;

    // Los endpoints serán implementados según las necesidades específicas del negocio
    // Esta clase sirve como base para la definición de la API REST

    @GetMapping(value = "/{id}")
    public CustomMessageView<SvSolicitudVisitaHistorialDTO> findById(@PathVariable("id") String id) {

        SvSolicitudVisitaHistorialDTO result = queryService.findById(id);

        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/search")
    public CustomMessageView<PagedResult<SvSolicitudVisitaHistorialDTO>> search(@RequestBody PagedRequest<SvSolicitudVisitaHistorialFilter> filter) {

        PagedResult<SvSolicitudVisitaHistorialDTO> result = queryService.search(filter);

        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/download-excel")
    @ResponseStatus(HttpStatus.OK)
    public void downloadExcel(@RequestBody PagedRequest<SvSolicitudVisitaHistorialFilter> filter, HttpServletResponse httpServletResponse) throws Exception {

        PagedResult<SvSolicitudVisitaHistorialDTO> data = queryService.search(filter);

        AttachmentData attachmentData = writerService.exportToExcel(data.getResult());

        attachmentData.writeToResponse(httpServletResponse);

    }

    @PostMapping(value = "/read-excel", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public CustomMessageView<List<SvSolicitudVisitaHistorialDTO>> readExcel(@RequestPart("file") MultipartFile file) throws IOException {

        List<SvSolicitudVisitaHistorialDTO> result = readerService.readFromExcel(file.getInputStream());

        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/save")
    @ResponseStatus(HttpStatus.OK)
    public CustomMessageView<SvSolicitudVisitaHistorialEntity> save(@RequestBody @Valid SvSolicitudVisitaHistorialDTO dto) {

        SvSolicitudVisitaHistorialEntity result = crudService.save(dto);

        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/save-all")
    @ResponseStatus(HttpStatus.OK)
    public CustomMessageView<List<SvSolicitudVisitaHistorialEntity>> saveAll(@RequestBody List<SvSolicitudVisitaHistorialDTO> list) {

        List<SvSolicitudVisitaHistorialEntity> result = crudService.saveAll(list);

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

}
