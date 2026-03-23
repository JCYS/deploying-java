
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
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvEstadoSolicitudVisitaDTO;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.SvEstadoSolicitudVisitaFilter;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvEstadoSolicitudVisitaEntity;
import com.copeinca.apicopeincaprov.modules.core.service.business.ISvEstadoSolicitudVisitaService;
import com.copeinca.apicopeincaprov.modules.core.service.crud.ISvEstadoSolicitudVisitaCrudService;
import com.copeinca.apicopeincaprov.modules.core.service.query.ISvEstadoSolicitudVisitaQueryService;
import com.copeinca.apicopeincaprov.modules.core.service.reader.ISvEstadoSolicitudVisitaExcelReaderService;
import com.copeinca.apicopeincaprov.modules.core.service.writer.ISvEstadoSolicitudVisitaWriterService;
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
* REST Controller para SvEstadoSolicitudVisitaEntity
* Define la API base pero sin endpoints implementados
*/
@RestController
@RequiredArgsConstructor
@RequestMapping(ApiPaths.Core.SVESTADOSOLICITUDVISITA)
public class SvEstadoSolicitudVisitaRest {

    private final ISvEstadoSolicitudVisitaService service;
    private final ISvEstadoSolicitudVisitaCrudService crudService;
    private final ISvEstadoSolicitudVisitaQueryService queryService;
    private final ISvEstadoSolicitudVisitaWriterService writerService;
    private final ISvEstadoSolicitudVisitaExcelReaderService readerService;

    // Los endpoints serán implementados según las necesidades específicas del negocio
    // Esta clase sirve como base para la definición de la API REST

    @GetMapping(value = "/{id}")
    public CustomMessageView<SvEstadoSolicitudVisitaDTO> findById(@PathVariable("id") String id) {

        SvEstadoSolicitudVisitaDTO result = queryService.findById(id);

        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/search")
    public CustomMessageView<PagedResult<SvEstadoSolicitudVisitaDTO>> search(@RequestBody PagedRequest<SvEstadoSolicitudVisitaFilter> filter) {

        PagedResult<SvEstadoSolicitudVisitaDTO> result = queryService.search(filter);

        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/download-excel")
    @ResponseStatus(HttpStatus.OK)
    public void downloadExcel(@RequestBody PagedRequest<SvEstadoSolicitudVisitaFilter> filter, HttpServletResponse httpServletResponse) throws Exception {

        PagedResult<SvEstadoSolicitudVisitaDTO> data = queryService.search(filter);

        AttachmentData attachmentData = writerService.exportToExcel(data.getResult());

        attachmentData.writeToResponse(httpServletResponse);

    }

    @PostMapping(value = "/read-excel", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public CustomMessageView<List<SvEstadoSolicitudVisitaDTO>> readExcel(@RequestPart("file") MultipartFile file) throws IOException {

        List<SvEstadoSolicitudVisitaDTO> result = readerService.readFromExcel(file.getInputStream());

        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/save")
    @ResponseStatus(HttpStatus.OK)
    public CustomMessageView<SvEstadoSolicitudVisitaEntity> save(@RequestBody @Valid SvEstadoSolicitudVisitaDTO dto) {

        SvEstadoSolicitudVisitaEntity result = crudService.save(dto);

        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/save-all")
    @ResponseStatus(HttpStatus.OK)
    public CustomMessageView<List<SvEstadoSolicitudVisitaEntity>> saveAll(@RequestBody List<SvEstadoSolicitudVisitaDTO> list) {

        List<SvEstadoSolicitudVisitaEntity> result = crudService.saveAll(list);

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
