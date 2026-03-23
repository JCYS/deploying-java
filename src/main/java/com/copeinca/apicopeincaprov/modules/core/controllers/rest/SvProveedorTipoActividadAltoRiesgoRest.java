
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
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvProveedorTipoActividadAltoRiesgoDTO;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.SvProveedorTipoActividadAltoRiesgoFilter;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvProveedorTipoActividadAltoRiesgoEntity;
import com.copeinca.apicopeincaprov.modules.core.service.business.ISvProveedorTipoActividadAltoRiesgoService;
import com.copeinca.apicopeincaprov.modules.core.service.crud.ISvProveedorTipoActividadAltoRiesgoCrudService;
import com.copeinca.apicopeincaprov.modules.core.service.query.ISvProveedorTipoActividadAltoRiesgoQueryService;
import com.copeinca.apicopeincaprov.modules.core.service.reader.ISvProveedorTipoActividadAltoRiesgoExcelReaderService;
import com.copeinca.apicopeincaprov.modules.core.service.writer.ISvProveedorTipoActividadAltoRiesgoWriterService;
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
* REST Controller para SvProveedorTipoActividadAltoRiesgoEntity
* Define la API base pero sin endpoints implementados
*/
@RestController
@RequiredArgsConstructor
@RequestMapping(ApiPaths.Core.SVPROVEEDORTIPOACTIVIDADALTORIESGO)
public class SvProveedorTipoActividadAltoRiesgoRest {

    private final ISvProveedorTipoActividadAltoRiesgoService service;
    private final ISvProveedorTipoActividadAltoRiesgoCrudService crudService;
    private final ISvProveedorTipoActividadAltoRiesgoQueryService queryService;
    private final ISvProveedorTipoActividadAltoRiesgoWriterService writerService;
    private final ISvProveedorTipoActividadAltoRiesgoExcelReaderService readerService;

    // Los endpoints serán implementados según las necesidades específicas del negocio
    // Esta clase sirve como base para la definición de la API REST

    @GetMapping(value = "/{id}")
    public CustomMessageView<SvProveedorTipoActividadAltoRiesgoDTO> findById(@PathVariable("id") String id) {

        SvProveedorTipoActividadAltoRiesgoDTO result = queryService.findById(id);

        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/search")
    public CustomMessageView<PagedResult<SvProveedorTipoActividadAltoRiesgoDTO>> search(
            @RequestBody PagedRequest<SvProveedorTipoActividadAltoRiesgoFilter> filter) {

        PagedResult<SvProveedorTipoActividadAltoRiesgoDTO> result = queryService.search(filter);

        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/download-excel")
    @ResponseStatus(HttpStatus.OK)
    public void downloadExcel(@RequestBody PagedRequest<SvProveedorTipoActividadAltoRiesgoFilter> filter, HttpServletResponse httpServletResponse)
            throws Exception {

        PagedResult<SvProveedorTipoActividadAltoRiesgoDTO> data = queryService.search(filter);

        AttachmentData attachmentData = writerService.exportToExcel(data.getResult());

        attachmentData.writeToResponse(httpServletResponse);

    }

    @PostMapping(value = "/read-excel", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public CustomMessageView<List<SvProveedorTipoActividadAltoRiesgoDTO>> readExcel(@RequestPart("file") MultipartFile file) throws IOException {

        List<SvProveedorTipoActividadAltoRiesgoDTO> result = readerService.readFromExcel(file.getInputStream());

        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/save")
    @ResponseStatus(HttpStatus.OK)
    public CustomMessageView<SvProveedorTipoActividadAltoRiesgoEntity> save(@RequestBody @Valid SvProveedorTipoActividadAltoRiesgoDTO dto) {

        SvProveedorTipoActividadAltoRiesgoEntity result = crudService.save(dto);

        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/save-all")
    @ResponseStatus(HttpStatus.OK)
    public CustomMessageView<List<SvProveedorTipoActividadAltoRiesgoEntity>> saveAll(@RequestBody List<SvProveedorTipoActividadAltoRiesgoDTO> list) {

        List<SvProveedorTipoActividadAltoRiesgoEntity> result = crudService.saveAll(list);

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
