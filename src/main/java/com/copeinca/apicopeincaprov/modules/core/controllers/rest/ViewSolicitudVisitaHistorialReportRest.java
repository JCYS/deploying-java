
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
import com.copeinca.apicopeincaprov.modules.core.models.dto.ViewSolicitudVisitaHistorialReportDTO;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.ViewSolicitudVisitaHistorialReportFilter;
import com.copeinca.apicopeincaprov.modules.core.service.business.IViewSolicitudVisitaHistorialReportService;
import com.copeinca.apicopeincaprov.modules.core.service.query.IViewSolicitudVisitaHistorialReportQueryService;
import com.copeinca.apicopeincaprov.modules.core.service.reader.IViewSolicitudVisitaHistorialReportExcelReaderService;
import com.copeinca.apicopeincaprov.modules.core.service.writer.IViewSolicitudVisitaHistorialReportWriterService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
* REST Controller para ViewSolicitudVisitaHistorialReportView
* Define la API base pero sin endpoints implementados
*/
@RestController
@RequiredArgsConstructor
@RequestMapping(ApiPaths.Core.VIEWSOLICITUDVISITAHISTORIALREPORT)
public class ViewSolicitudVisitaHistorialReportRest {

    private final IViewSolicitudVisitaHistorialReportService service;
    private final IViewSolicitudVisitaHistorialReportQueryService queryService;
    private final IViewSolicitudVisitaHistorialReportWriterService writerService;
    private final IViewSolicitudVisitaHistorialReportExcelReaderService readerService;

    // Los endpoints serán implementados según las necesidades específicas del negocio
    // Esta clase sirve como base para la definición de la API REST

    @GetMapping(value = "/{id}")
    public CustomMessageView<ViewSolicitudVisitaHistorialReportDTO> findById(@PathVariable("id") String id) {

        ViewSolicitudVisitaHistorialReportDTO result = queryService.findById(id);

        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/search")
    public CustomMessageView<PagedResult<ViewSolicitudVisitaHistorialReportDTO>> search(
            @RequestBody PagedRequest<ViewSolicitudVisitaHistorialReportFilter> filter) {

        PagedResult<ViewSolicitudVisitaHistorialReportDTO> result = queryService.search(filter);

        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/download-excel")
    @ResponseStatus(HttpStatus.OK)
    public void downloadExcel(@RequestBody PagedRequest<ViewSolicitudVisitaHistorialReportFilter> filter, HttpServletResponse httpServletResponse)
            throws Exception {

        PagedResult<ViewSolicitudVisitaHistorialReportDTO> data = queryService.search(filter);

        AttachmentData attachmentData = writerService.exportToExcel(data.getResult());

        attachmentData.writeToResponse(httpServletResponse);

    }

    @PostMapping(value = "/read-excel", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public CustomMessageView<List<ViewSolicitudVisitaHistorialReportDTO>> readExcel(@RequestPart("file") MultipartFile file) throws IOException {

        List<ViewSolicitudVisitaHistorialReportDTO> result = readerService.readFromExcel(file.getInputStream());

        return CustomMessageView.success(result);

    }

}
