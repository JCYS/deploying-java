
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
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvEstadoCalidadAmbientalDTO;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.SvEstadoCalidadAmbientalFilter;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvEstadoCalidadAmbientalEntity;
import com.copeinca.apicopeincaprov.modules.core.service.business.ISvEstadoCalidadAmbientalService;
import com.copeinca.apicopeincaprov.modules.core.service.crud.ISvEstadoCalidadAmbientalCrudService;
import com.copeinca.apicopeincaprov.modules.core.service.query.ISvEstadoCalidadAmbientalQueryService;
import com.copeinca.apicopeincaprov.modules.core.service.reader.ISvEstadoCalidadAmbientalExcelReaderService;
import com.copeinca.apicopeincaprov.modules.core.service.writer.ISvEstadoCalidadAmbientalWriterService;
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
* REST Controller para SvEstadoCalidadAmbientalEntity
* Define la API base pero sin endpoints implementados
*/
@RestController
@RequiredArgsConstructor
@RequestMapping(ApiPaths.Core.SVESTADOCALIDADAMBIENTAL)
public class SvEstadoCalidadAmbientalRest {

    private final ISvEstadoCalidadAmbientalService service;
    private final ISvEstadoCalidadAmbientalCrudService crudService;
    private final ISvEstadoCalidadAmbientalQueryService queryService;
    private final ISvEstadoCalidadAmbientalWriterService writerService;
    private final ISvEstadoCalidadAmbientalExcelReaderService readerService;

    // Los endpoints serán implementados según las necesidades específicas del negocio
    // Esta clase sirve como base para la definición de la API REST

    @GetMapping(value = "/{id}")
    public CustomMessageView<SvEstadoCalidadAmbientalDTO> findById(@PathVariable("id") String id) {

        SvEstadoCalidadAmbientalDTO result = queryService.findById(id);

        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/search")
    public CustomMessageView<PagedResult<SvEstadoCalidadAmbientalDTO>> search(@RequestBody PagedRequest<SvEstadoCalidadAmbientalFilter> filter) {

        PagedResult<SvEstadoCalidadAmbientalDTO> result = queryService.search(filter);

        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/download-excel")
    @ResponseStatus(HttpStatus.OK)
    public void downloadExcel(@RequestBody PagedRequest<SvEstadoCalidadAmbientalFilter> filter, HttpServletResponse httpServletResponse) throws Exception {

        PagedResult<SvEstadoCalidadAmbientalDTO> data = queryService.search(filter);

        AttachmentData attachmentData = writerService.exportToExcel(data.getResult());

        attachmentData.writeToResponse(httpServletResponse);

    }

    @PostMapping(value = "/read-excel", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public CustomMessageView<List<SvEstadoCalidadAmbientalDTO>> readExcel(@RequestPart("file") MultipartFile file) throws IOException {

        List<SvEstadoCalidadAmbientalDTO> result = readerService.readFromExcel(file.getInputStream());

        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/save")
    @ResponseStatus(HttpStatus.OK)
    public CustomMessageView<SvEstadoCalidadAmbientalEntity> save(@RequestBody @Valid SvEstadoCalidadAmbientalDTO dto) {

        SvEstadoCalidadAmbientalEntity result = crudService.save(dto);

        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/save-all")
    @ResponseStatus(HttpStatus.OK)
    public CustomMessageView<List<SvEstadoCalidadAmbientalEntity>> saveAll(@RequestBody List<SvEstadoCalidadAmbientalDTO> list) {

        List<SvEstadoCalidadAmbientalEntity> result = crudService.saveAll(list);

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
