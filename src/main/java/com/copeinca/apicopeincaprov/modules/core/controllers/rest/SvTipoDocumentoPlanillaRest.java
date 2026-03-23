
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
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvTipoDocumentoPlanillaDTO;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.SvTipoDocumentoPlanillaFilter;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvTipoDocumentoPlanillaEntity;
import com.copeinca.apicopeincaprov.modules.core.service.business.ISvTipoDocumentoPlanillaService;
import com.copeinca.apicopeincaprov.modules.core.service.crud.ISvTipoDocumentoPlanillaCrudService;
import com.copeinca.apicopeincaprov.modules.core.service.query.ISvTipoDocumentoPlanillaQueryService;
import com.copeinca.apicopeincaprov.modules.core.service.reader.ISvTipoDocumentoPlanillaExcelReaderService;
import com.copeinca.apicopeincaprov.modules.core.service.writer.ISvTipoDocumentoPlanillaWriterService;
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
* REST Controller para SvTipoDocumentoPlanillaEntity
* Define la API base pero sin endpoints implementados
*/
@RestController
@RequiredArgsConstructor
@RequestMapping(ApiPaths.Core.SVTIPODOCUMENTOPLANILLA)
public class SvTipoDocumentoPlanillaRest {

    private final ISvTipoDocumentoPlanillaService service;
    private final ISvTipoDocumentoPlanillaCrudService crudService;
    private final ISvTipoDocumentoPlanillaQueryService queryService;
    private final ISvTipoDocumentoPlanillaWriterService writerService;
    private final ISvTipoDocumentoPlanillaExcelReaderService readerService;

    // Los endpoints serán implementados según las necesidades específicas del negocio
    // Esta clase sirve como base para la definición de la API REST

    @GetMapping(value = "/{id}")
    public CustomMessageView<SvTipoDocumentoPlanillaDTO> findById(@PathVariable("id") String id) {

        SvTipoDocumentoPlanillaDTO result = queryService.findById(id);

        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/search")
    public CustomMessageView<PagedResult<SvTipoDocumentoPlanillaDTO>> search(@RequestBody PagedRequest<SvTipoDocumentoPlanillaFilter> filter) {

        PagedResult<SvTipoDocumentoPlanillaDTO> result = queryService.search(filter);

        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/download-excel")
    @ResponseStatus(HttpStatus.OK)
    public void downloadExcel(@RequestBody PagedRequest<SvTipoDocumentoPlanillaFilter> filter, HttpServletResponse httpServletResponse) throws Exception {

        PagedResult<SvTipoDocumentoPlanillaDTO> data = queryService.search(filter);

        AttachmentData attachmentData = writerService.exportToExcel(data.getResult());

        attachmentData.writeToResponse(httpServletResponse);

    }

    @PostMapping(value = "/read-excel", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public CustomMessageView<List<SvTipoDocumentoPlanillaDTO>> readExcel(@RequestPart("file") MultipartFile file) throws IOException {

        List<SvTipoDocumentoPlanillaDTO> result = readerService.readFromExcel(file.getInputStream());

        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/save")
    @ResponseStatus(HttpStatus.OK)
    public CustomMessageView<SvTipoDocumentoPlanillaEntity> save(@RequestBody @Valid SvTipoDocumentoPlanillaDTO dto) {

        SvTipoDocumentoPlanillaEntity result = crudService.save(dto);

        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/save-all")
    @ResponseStatus(HttpStatus.OK)
    public CustomMessageView<List<SvTipoDocumentoPlanillaEntity>> saveAll(@RequestBody List<SvTipoDocumentoPlanillaDTO> list) {

        List<SvTipoDocumentoPlanillaEntity> result = crudService.saveAll(list);

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
