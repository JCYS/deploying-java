
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
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvSolicitudVisitaZonaDTO;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.SvSolicitudVisitaZonaFilter;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvSolicitudVisitaZonaEntity;
import com.copeinca.apicopeincaprov.modules.core.service.business.ISvSolicitudVisitaZonaService;
import com.copeinca.apicopeincaprov.modules.core.service.crud.ISvSolicitudVisitaZonaCrudService;
import com.copeinca.apicopeincaprov.modules.core.service.query.ISvSolicitudVisitaZonaQueryService;
import com.copeinca.apicopeincaprov.modules.core.service.reader.ISvSolicitudVisitaZonaExcelReaderService;
import com.copeinca.apicopeincaprov.modules.core.service.writer.ISvSolicitudVisitaZonaWriterService;
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
* REST Controller para SvSolicitudVisitaZonaEntity
* Define la API base pero sin endpoints implementados
*/
@RestController
@RequiredArgsConstructor
@RequestMapping(ApiPaths.Core.SVSOLICITUDVISITAZONA)
public class SvSolicitudVisitaZonaRest {

    private final ISvSolicitudVisitaZonaService service;
    private final ISvSolicitudVisitaZonaCrudService crudService;
    private final ISvSolicitudVisitaZonaQueryService queryService;
    private final ISvSolicitudVisitaZonaWriterService writerService;
    private final ISvSolicitudVisitaZonaExcelReaderService readerService;

    // Los endpoints serán implementados según las necesidades específicas del negocio
    // Esta clase sirve como base para la definición de la API REST

    @GetMapping(value = "/{id}")
    public CustomMessageView<SvSolicitudVisitaZonaDTO> findById(@PathVariable("id") String id) {

        SvSolicitudVisitaZonaDTO result = queryService.findById(id);

        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/search")
    public CustomMessageView<PagedResult<SvSolicitudVisitaZonaDTO>> search(@RequestBody PagedRequest<SvSolicitudVisitaZonaFilter> filter) {

        PagedResult<SvSolicitudVisitaZonaDTO> result = queryService.search(filter);

        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/download-excel")
    @ResponseStatus(HttpStatus.OK)
    public void downloadExcel(@RequestBody PagedRequest<SvSolicitudVisitaZonaFilter> filter, HttpServletResponse httpServletResponse) throws Exception {

        PagedResult<SvSolicitudVisitaZonaDTO> data = queryService.search(filter);

        AttachmentData attachmentData = writerService.exportToExcel(data.getResult());

        attachmentData.writeToResponse(httpServletResponse);

    }

    @PostMapping(value = "/read-excel", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public CustomMessageView<List<SvSolicitudVisitaZonaDTO>> readExcel(@RequestPart("file") MultipartFile file) throws IOException {

        List<SvSolicitudVisitaZonaDTO> result = readerService.readFromExcel(file.getInputStream());

        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/save")
    @ResponseStatus(HttpStatus.OK)
    public CustomMessageView<SvSolicitudVisitaZonaEntity> save(@RequestBody @Valid SvSolicitudVisitaZonaDTO dto) {

        SvSolicitudVisitaZonaEntity result = crudService.save(dto);

        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/save-all")
    @ResponseStatus(HttpStatus.OK)
    public CustomMessageView<List<SvSolicitudVisitaZonaEntity>> saveAll(@RequestBody List<SvSolicitudVisitaZonaDTO> list) {

        List<SvSolicitudVisitaZonaEntity> result = crudService.saveAll(list);

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
