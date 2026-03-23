
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
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvZonaDTO;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.SvZonaFilter;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvZonaEntity;
import com.copeinca.apicopeincaprov.modules.core.service.business.ISvZonaService;
import com.copeinca.apicopeincaprov.modules.core.service.crud.ISvZonaCrudService;
import com.copeinca.apicopeincaprov.modules.core.service.query.ISvZonaQueryService;
import com.copeinca.apicopeincaprov.modules.core.service.reader.ISvZonaExcelReaderService;
import com.copeinca.apicopeincaprov.modules.core.service.writer.ISvZonaWriterService;
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
* REST Controller para SvZonaEntity
* Define la API base pero sin endpoints implementados
*/
@RestController
@RequiredArgsConstructor
@RequestMapping(ApiPaths.Core.SVZONA)
public class SvZonaRest {

    private final ISvZonaService service;
    private final ISvZonaCrudService crudService;
    private final ISvZonaQueryService queryService;
    private final ISvZonaWriterService writerService;
    private final ISvZonaExcelReaderService readerService;

    // Los endpoints serán implementados según las necesidades específicas del negocio
    // Esta clase sirve como base para la definición de la API REST

    @GetMapping(value = "/{id}")
    public CustomMessageView<SvZonaDTO> findById(@PathVariable("id") String id) {

        SvZonaDTO result = queryService.findById(id);

        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/search")
    public CustomMessageView<PagedResult<SvZonaDTO>> search(@RequestBody PagedRequest<SvZonaFilter> filter) {

        PagedResult<SvZonaDTO> result = queryService.search(filter);

        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/download-excel")
    @ResponseStatus(HttpStatus.OK)
    public void downloadExcel(@RequestBody PagedRequest<SvZonaFilter> filter, HttpServletResponse httpServletResponse) throws Exception {

        PagedResult<SvZonaDTO> data = queryService.search(filter);

        AttachmentData attachmentData = writerService.exportToExcel(data.getResult());

        attachmentData.writeToResponse(httpServletResponse);

    }

    @PostMapping(value = "/read-excel", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public CustomMessageView<List<SvZonaDTO>> readExcel(@RequestPart("file") MultipartFile file) throws IOException {

        List<SvZonaDTO> result = readerService.readFromExcel(file.getInputStream());

        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/save")
    @ResponseStatus(HttpStatus.OK)
    public CustomMessageView<SvZonaEntity> save(@RequestBody @Valid SvZonaDTO dto) {

        SvZonaEntity result = crudService.save(dto);

        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/save-all")
    @ResponseStatus(HttpStatus.OK)
    public CustomMessageView<List<SvZonaEntity>> saveAll(@RequestBody List<SvZonaDTO> list) {

        List<SvZonaEntity> result = crudService.saveAll(list);

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
