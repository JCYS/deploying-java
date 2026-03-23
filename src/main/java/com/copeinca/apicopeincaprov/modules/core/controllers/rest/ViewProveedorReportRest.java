
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
import com.copeinca.apicopeincaprov.modules.core.models.dto.ViewProveedorReportDTO;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.ViewProveedorReportFilter;
import com.copeinca.apicopeincaprov.modules.core.service.business.IViewProveedorReportService;
import com.copeinca.apicopeincaprov.modules.core.service.query.IViewProveedorReportQueryService;
import com.copeinca.apicopeincaprov.modules.core.service.reader.IViewProveedorReportExcelReaderService;
import com.copeinca.apicopeincaprov.modules.core.service.writer.IViewProveedorReportWriterService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
* REST Controller para ViewProveedorReportView
* Define la API base pero sin endpoints implementados
*/
@RestController
@RequiredArgsConstructor
@RequestMapping(ApiPaths.Core.VIEWPROVEEDORREPORT)
public class ViewProveedorReportRest {

    private final IViewProveedorReportService service;
    private final IViewProveedorReportQueryService queryService;
    private final IViewProveedorReportWriterService writerService;
    private final IViewProveedorReportExcelReaderService readerService;

    // Los endpoints serán implementados según las necesidades específicas del negocio
    // Esta clase sirve como base para la definición de la API REST

    @GetMapping(value = "/{id}")
    public CustomMessageView<ViewProveedorReportDTO> findById(@PathVariable("id") String id) {

        ViewProveedorReportDTO result = queryService.findById(id);

        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/search")
    public CustomMessageView<PagedResult<ViewProveedorReportDTO>> search(@RequestBody PagedRequest<ViewProveedorReportFilter> filter) {

        PagedResult<ViewProveedorReportDTO> result = queryService.search(filter);

        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/download-excel")
    @ResponseStatus(HttpStatus.OK)
    public void downloadExcel(@RequestBody PagedRequest<ViewProveedorReportFilter> filter, HttpServletResponse httpServletResponse) throws Exception {

        PagedResult<ViewProveedorReportDTO> data = queryService.search(filter);

        AttachmentData attachmentData = writerService.exportToExcel(data.getResult());

        attachmentData.writeToResponse(httpServletResponse);

    }

    @PostMapping(value = "/read-excel", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public CustomMessageView<List<ViewProveedorReportDTO>> readExcel(@RequestPart("file") MultipartFile file) throws IOException {

        List<ViewProveedorReportDTO> result = readerService.readFromExcel(file.getInputStream());

        return CustomMessageView.success(result);

    }

}
