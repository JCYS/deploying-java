
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
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvPersonalSedeDTO;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.SvPersonalSedeFilter;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvPersonalSedeEntity;
import com.copeinca.apicopeincaprov.modules.core.service.business.ISvPersonalSedeService;
import com.copeinca.apicopeincaprov.modules.core.service.crud.ISvPersonalSedeCrudService;
import com.copeinca.apicopeincaprov.modules.core.service.query.ISvPersonalSedeQueryService;
import com.copeinca.apicopeincaprov.modules.core.service.reader.ISvPersonalSedeExcelReaderService;
import com.copeinca.apicopeincaprov.modules.core.service.writer.ISvPersonalSedeWriterService;
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
* REST Controller para SvPersonalSedeEntity
* Define la API base pero sin endpoints implementados
*/
@RestController
@RequiredArgsConstructor
@RequestMapping(ApiPaths.Core.SVPERSONALSEDE)
public class SvPersonalSedeRest {

    private final ISvPersonalSedeService service;
    private final ISvPersonalSedeCrudService crudService;
    private final ISvPersonalSedeQueryService queryService;
    private final ISvPersonalSedeWriterService writerService;
    private final ISvPersonalSedeExcelReaderService readerService;

    // Los endpoints serán implementados según las necesidades específicas del negocio
    // Esta clase sirve como base para la definición de la API REST

    @GetMapping(value = "/{id}")
    public CustomMessageView<SvPersonalSedeDTO> findById(@PathVariable("id") String id) {

        SvPersonalSedeDTO result = queryService.findById(id);

        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/search")
    public CustomMessageView<PagedResult<SvPersonalSedeDTO>> search(@RequestBody PagedRequest<SvPersonalSedeFilter> filter) {

        PagedResult<SvPersonalSedeDTO> result = queryService.search(filter);

        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/download-excel")
    @ResponseStatus(HttpStatus.OK)
    public void downloadExcel(@RequestBody PagedRequest<SvPersonalSedeFilter> filter, HttpServletResponse httpServletResponse) throws Exception {

        PagedResult<SvPersonalSedeDTO> data = queryService.search(filter);

        AttachmentData attachmentData = writerService.exportToExcel(data.getResult());

        attachmentData.writeToResponse(httpServletResponse);

    }

    @PostMapping(value = "/read-excel", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public CustomMessageView<List<SvPersonalSedeDTO>> readExcel(@RequestPart("file") MultipartFile file) throws IOException {

        List<SvPersonalSedeDTO> result = readerService.readFromExcel(file.getInputStream());

        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/save")
    @ResponseStatus(HttpStatus.OK)
    public CustomMessageView<SvPersonalSedeEntity> save(@RequestBody @Valid SvPersonalSedeDTO dto) {

        SvPersonalSedeEntity result = crudService.save(dto);

        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/save-all")
    @ResponseStatus(HttpStatus.OK)
    public CustomMessageView<List<SvPersonalSedeEntity>> saveAll(@RequestBody List<SvPersonalSedeDTO> list) {

        List<SvPersonalSedeEntity> result = crudService.saveAll(list);

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
