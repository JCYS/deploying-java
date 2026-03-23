
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
import com.copeinca.apicopeincaprov.modules.core.models.dto.ViewTrabajadorReportDTO;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.ViewTrabajadorReportFilter;
import com.copeinca.apicopeincaprov.modules.core.service.business.IViewTrabajadorReportService;
import com.copeinca.apicopeincaprov.modules.core.service.query.IViewTrabajadorReportQueryService;
import com.copeinca.apicopeincaprov.modules.core.service.reader.IViewTrabajadorReportExcelReaderService;
import com.copeinca.apicopeincaprov.modules.core.service.writer.IViewTrabajadorReportWriterService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
* REST Controller para ViewTrabajadorReportView
* Define la API base pero sin endpoints implementados
*/
@RestController
@RequiredArgsConstructor
@RequestMapping(ApiPaths.Core.VIEWTRABAJADORREPORT)
public class ViewTrabajadorReportRest {

    private final IViewTrabajadorReportService service;
    private final IViewTrabajadorReportQueryService queryService;
    private final IViewTrabajadorReportWriterService writerService;
    private final IViewTrabajadorReportExcelReaderService readerService;

    // Los endpoints serán implementados según las necesidades específicas del negocio
    // Esta clase sirve como base para la definición de la API REST

    @GetMapping(value = "/{id}")
    public CustomMessageView<ViewTrabajadorReportDTO> findById(@PathVariable("id") String id) {

        ViewTrabajadorReportDTO result = queryService.findById(id);

        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/search")
    public CustomMessageView<PagedResult<ViewTrabajadorReportDTO>> search(@RequestBody PagedRequest<ViewTrabajadorReportFilter> filter) {

        PagedResult<ViewTrabajadorReportDTO> result = queryService.search(filter);

        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/download-excel")
    @ResponseStatus(HttpStatus.OK)
    public void downloadExcel(@RequestBody PagedRequest<ViewTrabajadorReportFilter> filter, HttpServletResponse httpServletResponse) throws Exception {

        PagedResult<ViewTrabajadorReportDTO> data = queryService.search(filter);

        AttachmentData attachmentData = writerService.exportToExcel(data.getResult());

        attachmentData.writeToResponse(httpServletResponse);

    }

    @PostMapping(value = "/read-excel", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public CustomMessageView<List<ViewTrabajadorReportDTO>> readExcel(@RequestPart("file") MultipartFile file) throws IOException {

        List<ViewTrabajadorReportDTO> result = readerService.readFromExcel(file.getInputStream());

        return CustomMessageView.success(result);

    }

}
