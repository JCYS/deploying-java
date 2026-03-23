
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
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvSolicitudVisitaTipoActividadAltoRiesgoDTO;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.SvSolicitudVisitaTipoActividadAltoRiesgoFilter;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvSolicitudVisitaTipoActividadAltoRiesgoEntity;
import com.copeinca.apicopeincaprov.modules.core.service.business.ISvSolicitudVisitaTipoActividadAltoRiesgoService;
import com.copeinca.apicopeincaprov.modules.core.service.crud.ISvSolicitudVisitaTipoActividadAltoRiesgoCrudService;
import com.copeinca.apicopeincaprov.modules.core.service.query.ISvSolicitudVisitaTipoActividadAltoRiesgoQueryService;
import com.copeinca.apicopeincaprov.modules.core.service.reader.ISvSolicitudVisitaTipoActividadAltoRiesgoExcelReaderService;
import com.copeinca.apicopeincaprov.modules.core.service.writer.ISvSolicitudVisitaTipoActividadAltoRiesgoWriterService;
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
* REST Controller para SvSolicitudVisitaTipoActividadAltoRiesgoEntity
* Define la API base pero sin endpoints implementados
*/
@RestController
@RequiredArgsConstructor
@RequestMapping(ApiPaths.Core.SVSOLICITUDVISITATIPOACTIVIDADALTORIESGO)
public class SvSolicitudVisitaTipoActividadAltoRiesgoRest {

    private final ISvSolicitudVisitaTipoActividadAltoRiesgoService service;
    private final ISvSolicitudVisitaTipoActividadAltoRiesgoCrudService crudService;
    private final ISvSolicitudVisitaTipoActividadAltoRiesgoQueryService queryService;
    private final ISvSolicitudVisitaTipoActividadAltoRiesgoWriterService writerService;
    private final ISvSolicitudVisitaTipoActividadAltoRiesgoExcelReaderService readerService;

    // Los endpoints serán implementados según las necesidades específicas del negocio
    // Esta clase sirve como base para la definición de la API REST

    @GetMapping(value = "/{id}")
    public CustomMessageView<SvSolicitudVisitaTipoActividadAltoRiesgoDTO> findById(@PathVariable("id") String id) {

        SvSolicitudVisitaTipoActividadAltoRiesgoDTO result = queryService.findById(id);

        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/search")
    public CustomMessageView<PagedResult<SvSolicitudVisitaTipoActividadAltoRiesgoDTO>> search(
            @RequestBody PagedRequest<SvSolicitudVisitaTipoActividadAltoRiesgoFilter> filter) {

        PagedResult<SvSolicitudVisitaTipoActividadAltoRiesgoDTO> result = queryService.search(filter);

        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/download-excel")
    @ResponseStatus(HttpStatus.OK)
    public void downloadExcel(@RequestBody PagedRequest<SvSolicitudVisitaTipoActividadAltoRiesgoFilter> filter, HttpServletResponse httpServletResponse)
            throws Exception {

        PagedResult<SvSolicitudVisitaTipoActividadAltoRiesgoDTO> data = queryService.search(filter);

        AttachmentData attachmentData = writerService.exportToExcel(data.getResult());

        attachmentData.writeToResponse(httpServletResponse);

    }

    @PostMapping(value = "/read-excel", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public CustomMessageView<List<SvSolicitudVisitaTipoActividadAltoRiesgoDTO>> readExcel(@RequestPart("file") MultipartFile file) throws IOException {

        List<SvSolicitudVisitaTipoActividadAltoRiesgoDTO> result = readerService.readFromExcel(file.getInputStream());

        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/save")
    @ResponseStatus(HttpStatus.OK)
    public CustomMessageView<SvSolicitudVisitaTipoActividadAltoRiesgoEntity> save(@RequestBody @Valid SvSolicitudVisitaTipoActividadAltoRiesgoDTO dto) {

        SvSolicitudVisitaTipoActividadAltoRiesgoEntity result = crudService.save(dto);

        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/save-all")
    @ResponseStatus(HttpStatus.OK)
    public CustomMessageView<List<SvSolicitudVisitaTipoActividadAltoRiesgoEntity>> saveAll(
            @RequestBody List<SvSolicitudVisitaTipoActividadAltoRiesgoDTO> list) {

        List<SvSolicitudVisitaTipoActividadAltoRiesgoEntity> result = crudService.saveAll(list);

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
