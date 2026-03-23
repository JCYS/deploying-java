
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
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvTipoDocumentoIdentidadDTO;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.SvTipoDocumentoIdentidadFilter;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvTipoDocumentoIdentidadEntity;
import com.copeinca.apicopeincaprov.modules.core.service.business.ISvTipoDocumentoIdentidadService;
import com.copeinca.apicopeincaprov.modules.core.service.crud.ISvTipoDocumentoIdentidadCrudService;
import com.copeinca.apicopeincaprov.modules.core.service.query.ISvTipoDocumentoIdentidadQueryService;
import com.copeinca.apicopeincaprov.modules.core.service.reader.ISvTipoDocumentoIdentidadExcelReaderService;
import com.copeinca.apicopeincaprov.modules.core.service.writer.ISvTipoDocumentoIdentidadWriterService;
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
* REST Controller para SvTipoDocumentoIdentidadEntity
* Define la API base pero sin endpoints implementados
*/
@RestController
@RequiredArgsConstructor
@RequestMapping(ApiPaths.Core.SVTIPODOCUMENTOIDENTIDAD)
public class SvTipoDocumentoIdentidadRest {

    private final ISvTipoDocumentoIdentidadService service;
    private final ISvTipoDocumentoIdentidadCrudService crudService;
    private final ISvTipoDocumentoIdentidadQueryService queryService;
    private final ISvTipoDocumentoIdentidadWriterService writerService;
    private final ISvTipoDocumentoIdentidadExcelReaderService readerService;

    // Los endpoints serán implementados según las necesidades específicas del negocio
    // Esta clase sirve como base para la definición de la API REST

    @GetMapping(value = "/{id}")
    public CustomMessageView<SvTipoDocumentoIdentidadDTO> findById(@PathVariable("id") String id) {

        SvTipoDocumentoIdentidadDTO result = queryService.findById(id);

        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/search")
    public CustomMessageView<PagedResult<SvTipoDocumentoIdentidadDTO>> search(@RequestBody PagedRequest<SvTipoDocumentoIdentidadFilter> filter) {

        PagedResult<SvTipoDocumentoIdentidadDTO> result = queryService.search(filter);

        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/download-excel")
    @ResponseStatus(HttpStatus.OK)
    public void downloadExcel(@RequestBody PagedRequest<SvTipoDocumentoIdentidadFilter> filter, HttpServletResponse httpServletResponse) throws Exception {

        PagedResult<SvTipoDocumentoIdentidadDTO> data = queryService.search(filter);

        AttachmentData attachmentData = writerService.exportToExcel(data.getResult());

        attachmentData.writeToResponse(httpServletResponse);

    }

    @PostMapping(value = "/read-excel", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public CustomMessageView<List<SvTipoDocumentoIdentidadDTO>> readExcel(@RequestPart("file") MultipartFile file) throws IOException {

        List<SvTipoDocumentoIdentidadDTO> result = readerService.readFromExcel(file.getInputStream());

        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/save")
    @ResponseStatus(HttpStatus.OK)
    public CustomMessageView<SvTipoDocumentoIdentidadEntity> save(@RequestBody @Valid SvTipoDocumentoIdentidadDTO dto) {

        SvTipoDocumentoIdentidadEntity result = crudService.save(dto);

        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/save-all")
    @ResponseStatus(HttpStatus.OK)
    public CustomMessageView<List<SvTipoDocumentoIdentidadEntity>> saveAll(@RequestBody List<SvTipoDocumentoIdentidadDTO> list) {

        List<SvTipoDocumentoIdentidadEntity> result = crudService.saveAll(list);

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
