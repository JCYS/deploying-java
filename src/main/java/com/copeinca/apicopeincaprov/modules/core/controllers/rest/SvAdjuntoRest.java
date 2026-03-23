
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
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvAdjuntoDTO;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.SvAdjuntoFilter;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvAdjuntoEntity;
import com.copeinca.apicopeincaprov.modules.core.service.business.ISvAdjuntoService;
import com.copeinca.apicopeincaprov.modules.core.service.crud.ISvAdjuntoCrudService;
import com.copeinca.apicopeincaprov.modules.core.service.query.ISvAdjuntoQueryService;
import com.copeinca.apicopeincaprov.modules.core.service.reader.ISvAdjuntoExcelReaderService;
import com.copeinca.apicopeincaprov.modules.core.service.writer.ISvAdjuntoWriterService;
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
* REST Controller para SvAdjuntoEntity
* Define la API base pero sin endpoints implementados
*/
@RestController
@RequiredArgsConstructor
@RequestMapping(ApiPaths.Core.SVADJUNTO)
public class SvAdjuntoRest {

    private final ISvAdjuntoService service;
    private final ISvAdjuntoCrudService crudService;
    private final ISvAdjuntoQueryService queryService;
    private final ISvAdjuntoWriterService writerService;
    private final ISvAdjuntoExcelReaderService readerService;

    // Los endpoints serán implementados según las necesidades específicas del negocio
    // Esta clase sirve como base para la definición de la API REST

    @GetMapping(value = "/{id}")
    public CustomMessageView<SvAdjuntoDTO> findById(@PathVariable("id") String id) {

        SvAdjuntoDTO result = queryService.findById(id);

        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/search")
    public CustomMessageView<PagedResult<SvAdjuntoDTO>> search(@RequestBody PagedRequest<SvAdjuntoFilter> filter) {

        PagedResult<SvAdjuntoDTO> result = queryService.search(filter);

        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/download-excel")
    @ResponseStatus(HttpStatus.OK)
    public void downloadExcel(@RequestBody PagedRequest<SvAdjuntoFilter> filter, HttpServletResponse httpServletResponse) throws Exception {

        PagedResult<SvAdjuntoDTO> data = queryService.search(filter);

        AttachmentData attachmentData = writerService.exportToExcel(data.getResult());

        attachmentData.writeToResponse(httpServletResponse);

    }

    @PostMapping(value = "/read-excel", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public CustomMessageView<List<SvAdjuntoDTO>> readExcel(@RequestPart("file") MultipartFile file) throws IOException {

        List<SvAdjuntoDTO> result = readerService.readFromExcel(file.getInputStream());

        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/save")
    @ResponseStatus(HttpStatus.OK)
    public CustomMessageView<SvAdjuntoEntity> save(@RequestBody @Valid SvAdjuntoDTO dto) {

        SvAdjuntoEntity result = crudService.save(dto);

        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/save-all")
    @ResponseStatus(HttpStatus.OK)
    public CustomMessageView<List<SvAdjuntoEntity>> saveAll(@RequestBody List<SvAdjuntoDTO> list) {

        List<SvAdjuntoEntity> result = crudService.saveAll(list);

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
