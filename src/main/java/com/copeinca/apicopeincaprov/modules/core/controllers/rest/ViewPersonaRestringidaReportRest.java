
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
import com.copeinca.apicopeincaprov.modules.core.models.dto.ViewPersonaRestringidaReportDTO;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.ViewPersonaRestringidaReportFilter;
import com.copeinca.apicopeincaprov.modules.core.service.business.IViewPersonaRestringidaReportService;
import com.copeinca.apicopeincaprov.modules.core.service.query.IViewPersonaRestringidaReportQueryService;
import com.copeinca.apicopeincaprov.modules.core.service.reader.IViewPersonaRestringidaReportExcelReaderService;
import com.copeinca.apicopeincaprov.modules.core.service.writer.IViewPersonaRestringidaReportWriterService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
* REST Controller para ViewPersonaRestringidaReportView
* Define la API base pero sin endpoints implementados
*/
@RestController
@RequiredArgsConstructor
@RequestMapping(ApiPaths.Core.VIEWPERSONARESTRINGIDAREPORT)
public class ViewPersonaRestringidaReportRest {

    private final IViewPersonaRestringidaReportService service;
    private final IViewPersonaRestringidaReportQueryService queryService;
    private final IViewPersonaRestringidaReportWriterService writerService;
    private final IViewPersonaRestringidaReportExcelReaderService readerService;

    // Los endpoints serán implementados según las necesidades específicas del negocio
    // Esta clase sirve como base para la definición de la API REST

    @GetMapping(value = "/{id}")
    public CustomMessageView<ViewPersonaRestringidaReportDTO> findById(@PathVariable("id") String id) {

        ViewPersonaRestringidaReportDTO result = queryService.findById(id);

        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/search")
    public CustomMessageView<PagedResult<ViewPersonaRestringidaReportDTO>> search(@RequestBody PagedRequest<ViewPersonaRestringidaReportFilter> filter) {

        PagedResult<ViewPersonaRestringidaReportDTO> result = queryService.search(filter);

        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/download-excel")
    @ResponseStatus(HttpStatus.OK)
    public void downloadExcel(@RequestBody PagedRequest<ViewPersonaRestringidaReportFilter> filter, HttpServletResponse httpServletResponse) throws Exception {

        PagedResult<ViewPersonaRestringidaReportDTO> data = queryService.search(filter);

        AttachmentData attachmentData = writerService.exportToExcel(data.getResult());

        attachmentData.writeToResponse(httpServletResponse);

    }

    @PostMapping(value = "/read-excel", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public CustomMessageView<List<ViewPersonaRestringidaReportDTO>> readExcel(@RequestPart("file") MultipartFile file) throws IOException {

        List<ViewPersonaRestringidaReportDTO> result = readerService.readFromExcel(file.getInputStream());

        return CustomMessageView.success(result);

    }

}
