
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
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvSedeDTO;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.SvSedeFilter;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvSedeEntity;
import com.copeinca.apicopeincaprov.modules.core.service.business.ISvSedeService;
import com.copeinca.apicopeincaprov.modules.core.service.crud.ISvSedeCrudService;
import com.copeinca.apicopeincaprov.modules.core.service.query.ISvSedeQueryService;
import com.copeinca.apicopeincaprov.modules.core.service.reader.ISvSedeExcelReaderService;
import com.copeinca.apicopeincaprov.modules.core.service.writer.ISvSedeWriterService;
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
* REST Controller para SvSedeEntity
* Define la API base pero sin endpoints implementados
*/
@RestController
@RequiredArgsConstructor
@RequestMapping(ApiPaths.Core.SVSEDE)
public class SvSedeRest {

    private final ISvSedeService service;
    private final ISvSedeCrudService crudService;
    private final ISvSedeQueryService queryService;
    private final ISvSedeWriterService writerService;
    private final ISvSedeExcelReaderService readerService;

    // Los endpoints serán implementados según las necesidades específicas del negocio
    // Esta clase sirve como base para la definición de la API REST

    @GetMapping(value = "/{id}")
    public CustomMessageView<SvSedeDTO> findById(@PathVariable("id") String id) {

        SvSedeDTO result = queryService.findById(id);

        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/search")
    public CustomMessageView<PagedResult<SvSedeDTO>> search(@RequestBody PagedRequest<SvSedeFilter> filter) {

        PagedResult<SvSedeDTO> result = queryService.search(filter);

        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/download-excel")
    @ResponseStatus(HttpStatus.OK)
    public void downloadExcel(@RequestBody PagedRequest<SvSedeFilter> filter, HttpServletResponse httpServletResponse) throws Exception {

        PagedResult<SvSedeDTO> data = queryService.search(filter);

        AttachmentData attachmentData = writerService.exportToExcel(data.getResult());

        attachmentData.writeToResponse(httpServletResponse);

    }

    @PostMapping(value = "/read-excel", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public CustomMessageView<List<SvSedeDTO>> readExcel(@RequestPart("file") MultipartFile file) throws IOException {

        List<SvSedeDTO> result = readerService.readFromExcel(file.getInputStream());

        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/save")
    @ResponseStatus(HttpStatus.OK)
    public CustomMessageView<SvSedeEntity> save(@RequestBody @Valid SvSedeDTO dto) {

        SvSedeEntity result = crudService.save(dto);

        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/save-all")
    @ResponseStatus(HttpStatus.OK)
    public CustomMessageView<List<SvSedeEntity>> saveAll(@RequestBody List<SvSedeDTO> list) {

        List<SvSedeEntity> result = crudService.saveAll(list);

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
