
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
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvSolicitudVisitaAreaAutorizadoraDTO;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.SvSolicitudVisitaAreaAutorizadoraFilter;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvSolicitudVisitaAreaAutorizadoraEntity;
import com.copeinca.apicopeincaprov.modules.core.service.business.ISvSolicitudVisitaAreaAutorizadoraService;
import com.copeinca.apicopeincaprov.modules.core.service.crud.ISvSolicitudVisitaAreaAutorizadoraCrudService;
import com.copeinca.apicopeincaprov.modules.core.service.query.ISvSolicitudVisitaAreaAutorizadoraQueryService;
import com.copeinca.apicopeincaprov.modules.core.service.reader.ISvSolicitudVisitaAreaAutorizadoraExcelReaderService;
import com.copeinca.apicopeincaprov.modules.core.service.writer.ISvSolicitudVisitaAreaAutorizadoraWriterService;
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
* REST Controller para SvSolicitudVisitaAreaAutorizadoraEntity
* Define la API base pero sin endpoints implementados
*/
@RestController
@RequiredArgsConstructor
@RequestMapping(ApiPaths.Core.SVSOLICITUDVISITAAREAAUTORIZADORA)
public class SvSolicitudVisitaAreaAutorizadoraRest {

    private final ISvSolicitudVisitaAreaAutorizadoraService service;
    private final ISvSolicitudVisitaAreaAutorizadoraCrudService crudService;
    private final ISvSolicitudVisitaAreaAutorizadoraQueryService queryService;
    private final ISvSolicitudVisitaAreaAutorizadoraWriterService writerService;
    private final ISvSolicitudVisitaAreaAutorizadoraExcelReaderService readerService;

    // Los endpoints serán implementados según las necesidades específicas del negocio
    // Esta clase sirve como base para la definición de la API REST

    @GetMapping(value = "/{id}")
    public CustomMessageView<SvSolicitudVisitaAreaAutorizadoraDTO> findById(@PathVariable("id") String id) {

        SvSolicitudVisitaAreaAutorizadoraDTO result = queryService.findById(id);

        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/search")
    public CustomMessageView<PagedResult<SvSolicitudVisitaAreaAutorizadoraDTO>> search(
            @RequestBody PagedRequest<SvSolicitudVisitaAreaAutorizadoraFilter> filter) {

        PagedResult<SvSolicitudVisitaAreaAutorizadoraDTO> result = queryService.search(filter);

        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/download-excel")
    @ResponseStatus(HttpStatus.OK)
    public void downloadExcel(@RequestBody PagedRequest<SvSolicitudVisitaAreaAutorizadoraFilter> filter, HttpServletResponse httpServletResponse)
            throws Exception {

        PagedResult<SvSolicitudVisitaAreaAutorizadoraDTO> data = queryService.search(filter);

        AttachmentData attachmentData = writerService.exportToExcel(data.getResult());

        attachmentData.writeToResponse(httpServletResponse);

    }

    @PostMapping(value = "/read-excel", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public CustomMessageView<List<SvSolicitudVisitaAreaAutorizadoraDTO>> readExcel(@RequestPart("file") MultipartFile file) throws IOException {

        List<SvSolicitudVisitaAreaAutorizadoraDTO> result = readerService.readFromExcel(file.getInputStream());

        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/save")
    @ResponseStatus(HttpStatus.OK)
    public CustomMessageView<SvSolicitudVisitaAreaAutorizadoraEntity> save(@RequestBody @Valid SvSolicitudVisitaAreaAutorizadoraDTO dto) {

        SvSolicitudVisitaAreaAutorizadoraEntity result = crudService.save(dto);

        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/save-all")
    @ResponseStatus(HttpStatus.OK)
    public CustomMessageView<List<SvSolicitudVisitaAreaAutorizadoraEntity>> saveAll(@RequestBody List<SvSolicitudVisitaAreaAutorizadoraDTO> list) {

        List<SvSolicitudVisitaAreaAutorizadoraEntity> result = crudService.saveAll(list);

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
