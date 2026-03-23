
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
import com.copeinca.apicopeincaprov.modules.core.models.dto.ViewPersonalReportDTO;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.ViewPersonalReportFilter;
import com.copeinca.apicopeincaprov.modules.core.service.business.IViewPersonalReportService;
import com.copeinca.apicopeincaprov.modules.core.service.query.IViewPersonalReportQueryService;
import com.copeinca.apicopeincaprov.modules.core.service.reader.IViewPersonalReportExcelReaderService;
import com.copeinca.apicopeincaprov.modules.core.service.writer.IViewPersonalReportWriterService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
* REST Controller para ViewPersonalReportView
* Define la API base pero sin endpoints implementados
*/
@RestController
@RequiredArgsConstructor
@RequestMapping(ApiPaths.Core.VIEWPERSONALREPORT)
public class ViewPersonalReportRest {

    private final IViewPersonalReportService service;
    private final IViewPersonalReportQueryService queryService;
    private final IViewPersonalReportWriterService writerService;
    private final IViewPersonalReportExcelReaderService readerService;

    // Los endpoints serán implementados según las necesidades específicas del negocio
    // Esta clase sirve como base para la definición de la API REST

    @GetMapping(value = "/{id}")
    public CustomMessageView<ViewPersonalReportDTO> findById(@PathVariable("id") String id) {

        ViewPersonalReportDTO result = queryService.findById(id);

        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/search")
    public CustomMessageView<PagedResult<ViewPersonalReportDTO>> search(@RequestBody PagedRequest<ViewPersonalReportFilter> filter) {

        PagedResult<ViewPersonalReportDTO> result = queryService.search(filter);

        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/download-excel")
    @ResponseStatus(HttpStatus.OK)
    public void downloadExcel(@RequestBody PagedRequest<ViewPersonalReportFilter> filter, HttpServletResponse httpServletResponse) throws Exception {

        PagedResult<ViewPersonalReportDTO> data = queryService.search(filter);

        AttachmentData attachmentData = writerService.exportToExcel(data.getResult());

        attachmentData.writeToResponse(httpServletResponse);

    }

    @PostMapping(value = "/read-excel", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public CustomMessageView<List<ViewPersonalReportDTO>> readExcel(@RequestPart("file") MultipartFile file) throws IOException {

        List<ViewPersonalReportDTO> result = readerService.readFromExcel(file.getInputStream());

        return CustomMessageView.success(result);

    }

}
