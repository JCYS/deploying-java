
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
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvTipoActividadAltoRiesgoDTO;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.SvTipoActividadAltoRiesgoFilter;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvTipoActividadAltoRiesgoEntity;
import com.copeinca.apicopeincaprov.modules.core.service.business.ISvTipoActividadAltoRiesgoService;
import com.copeinca.apicopeincaprov.modules.core.service.crud.ISvTipoActividadAltoRiesgoCrudService;
import com.copeinca.apicopeincaprov.modules.core.service.query.ISvTipoActividadAltoRiesgoQueryService;
import com.copeinca.apicopeincaprov.modules.core.service.reader.ISvTipoActividadAltoRiesgoExcelReaderService;
import com.copeinca.apicopeincaprov.modules.core.service.writer.ISvTipoActividadAltoRiesgoWriterService;
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
* REST Controller para SvTipoActividadAltoRiesgoEntity
* Define la API base pero sin endpoints implementados
*/
@RestController
@RequiredArgsConstructor
@RequestMapping(ApiPaths.Core.SVTIPOACTIVIDADALTORIESGO)
public class SvTipoActividadAltoRiesgoRest {

    private final ISvTipoActividadAltoRiesgoService service;
    private final ISvTipoActividadAltoRiesgoCrudService crudService;
    private final ISvTipoActividadAltoRiesgoQueryService queryService;
    private final ISvTipoActividadAltoRiesgoWriterService writerService;
    private final ISvTipoActividadAltoRiesgoExcelReaderService readerService;

    // Los endpoints serán implementados según las necesidades específicas del negocio
    // Esta clase sirve como base para la definición de la API REST

    @GetMapping(value = "/{id}")
    public CustomMessageView<SvTipoActividadAltoRiesgoDTO> findById(@PathVariable("id") String id) {

        SvTipoActividadAltoRiesgoDTO result = queryService.findById(id);

        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/search")
    public CustomMessageView<PagedResult<SvTipoActividadAltoRiesgoDTO>> search(@RequestBody PagedRequest<SvTipoActividadAltoRiesgoFilter> filter) {

        PagedResult<SvTipoActividadAltoRiesgoDTO> result = queryService.search(filter);

        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/download-excel")
    @ResponseStatus(HttpStatus.OK)
    public void downloadExcel(@RequestBody PagedRequest<SvTipoActividadAltoRiesgoFilter> filter, HttpServletResponse httpServletResponse) throws Exception {

        PagedResult<SvTipoActividadAltoRiesgoDTO> data = queryService.search(filter);

        AttachmentData attachmentData = writerService.exportToExcel(data.getResult());

        attachmentData.writeToResponse(httpServletResponse);

    }

    @PostMapping(value = "/read-excel", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public CustomMessageView<List<SvTipoActividadAltoRiesgoDTO>> readExcel(@RequestPart("file") MultipartFile file) throws IOException {

        List<SvTipoActividadAltoRiesgoDTO> result = readerService.readFromExcel(file.getInputStream());

        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/save")
    @ResponseStatus(HttpStatus.OK)
    public CustomMessageView<SvTipoActividadAltoRiesgoEntity> save(@RequestBody @Valid SvTipoActividadAltoRiesgoDTO dto) {

        SvTipoActividadAltoRiesgoEntity result = crudService.save(dto);

        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/save-all")
    @ResponseStatus(HttpStatus.OK)
    public CustomMessageView<List<SvTipoActividadAltoRiesgoEntity>> saveAll(@RequestBody List<SvTipoActividadAltoRiesgoDTO> list) {

        List<SvTipoActividadAltoRiesgoEntity> result = crudService.saveAll(list);

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
