
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
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvEstadoSsoDTO;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.SvEstadoSsoFilter;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvEstadoSsoEntity;
import com.copeinca.apicopeincaprov.modules.core.service.business.ISvEstadoSsoService;
import com.copeinca.apicopeincaprov.modules.core.service.crud.ISvEstadoSsoCrudService;
import com.copeinca.apicopeincaprov.modules.core.service.query.ISvEstadoSsoQueryService;
import com.copeinca.apicopeincaprov.modules.core.service.reader.ISvEstadoSsoExcelReaderService;
import com.copeinca.apicopeincaprov.modules.core.service.writer.ISvEstadoSsoWriterService;
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
* REST Controller para SvEstadoSsoEntity
* Define la API base pero sin endpoints implementados
*/
@RestController
@RequiredArgsConstructor
@RequestMapping(ApiPaths.Core.SVESTADOSSO)
public class SvEstadoSsoRest {

    private final ISvEstadoSsoService service;
    private final ISvEstadoSsoCrudService crudService;
    private final ISvEstadoSsoQueryService queryService;
    private final ISvEstadoSsoWriterService writerService;
    private final ISvEstadoSsoExcelReaderService readerService;

    // Los endpoints serán implementados según las necesidades específicas del negocio
    // Esta clase sirve como base para la definición de la API REST

    @GetMapping(value = "/{id}")
    public CustomMessageView<SvEstadoSsoDTO> findById(@PathVariable("id") String id) {

        SvEstadoSsoDTO result = queryService.findById(id);

        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/search")
    public CustomMessageView<PagedResult<SvEstadoSsoDTO>> search(@RequestBody PagedRequest<SvEstadoSsoFilter> filter) {

        PagedResult<SvEstadoSsoDTO> result = queryService.search(filter);

        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/download-excel")
    @ResponseStatus(HttpStatus.OK)
    public void downloadExcel(@RequestBody PagedRequest<SvEstadoSsoFilter> filter, HttpServletResponse httpServletResponse) throws Exception {

        PagedResult<SvEstadoSsoDTO> data = queryService.search(filter);

        AttachmentData attachmentData = writerService.exportToExcel(data.getResult());

        attachmentData.writeToResponse(httpServletResponse);

    }

    @PostMapping(value = "/read-excel", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public CustomMessageView<List<SvEstadoSsoDTO>> readExcel(@RequestPart("file") MultipartFile file) throws IOException {

        List<SvEstadoSsoDTO> result = readerService.readFromExcel(file.getInputStream());

        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/save")
    @ResponseStatus(HttpStatus.OK)
    public CustomMessageView<SvEstadoSsoEntity> save(@RequestBody @Valid SvEstadoSsoDTO dto) {

        SvEstadoSsoEntity result = crudService.save(dto);

        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/save-all")
    @ResponseStatus(HttpStatus.OK)
    public CustomMessageView<List<SvEstadoSsoEntity>> saveAll(@RequestBody List<SvEstadoSsoDTO> list) {

        List<SvEstadoSsoEntity> result = crudService.saveAll(list);

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
