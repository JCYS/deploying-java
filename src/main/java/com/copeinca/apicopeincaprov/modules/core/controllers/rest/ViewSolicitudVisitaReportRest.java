
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
import com.copeinca.apicopeincaprov.modules.core.models.dto.ViewSolicitudVisitaReportDTO;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.ViewSolicitudVisitaReportFilter;
import com.copeinca.apicopeincaprov.modules.core.service.business.IViewSolicitudVisitaReportService;
import com.copeinca.apicopeincaprov.modules.core.service.query.IViewSolicitudVisitaReportQueryService;
import com.copeinca.apicopeincaprov.modules.core.service.reader.IViewSolicitudVisitaReportExcelReaderService;
import com.copeinca.apicopeincaprov.modules.core.service.writer.IViewSolicitudVisitaReportWriterService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
* REST Controller para ViewSolicitudVisitaReportView
* Define la API base pero sin endpoints implementados
*/
@RestController
@RequiredArgsConstructor
@RequestMapping(ApiPaths.Core.VIEWSOLICITUDVISITAREPORT)
public class ViewSolicitudVisitaReportRest {

    private final IViewSolicitudVisitaReportService service;
    private final IViewSolicitudVisitaReportQueryService queryService;
    private final IViewSolicitudVisitaReportWriterService writerService;
    private final IViewSolicitudVisitaReportExcelReaderService readerService;

    // Los endpoints serán implementados según las necesidades específicas del negocio
    // Esta clase sirve como base para la definición de la API REST

    @GetMapping(value = "/{id}")
    public CustomMessageView<ViewSolicitudVisitaReportDTO> findById(@PathVariable("id") String id) {

        ViewSolicitudVisitaReportDTO result = queryService.findById(id);

        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/search")
    public CustomMessageView<PagedResult<ViewSolicitudVisitaReportDTO>> search(@RequestBody PagedRequest<ViewSolicitudVisitaReportFilter> filter) {

        PagedResult<ViewSolicitudVisitaReportDTO> result = queryService.search(filter);

        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/download-excel")
    @ResponseStatus(HttpStatus.OK)
    public void downloadExcel(@RequestBody PagedRequest<ViewSolicitudVisitaReportFilter> filter, HttpServletResponse httpServletResponse) throws Exception {

        PagedResult<ViewSolicitudVisitaReportDTO> data = queryService.search(filter);

        AttachmentData attachmentData = writerService.exportToExcel(data.getResult());

        attachmentData.writeToResponse(httpServletResponse);

    }

    @PostMapping(value = "/read-excel", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public CustomMessageView<List<ViewSolicitudVisitaReportDTO>> readExcel(@RequestPart("file") MultipartFile file) throws IOException {

        List<ViewSolicitudVisitaReportDTO> result = readerService.readFromExcel(file.getInputStream());

        return CustomMessageView.success(result);

    }

}
