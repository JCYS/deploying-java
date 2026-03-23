
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
import com.copeinca.apicopeincaprov.modules.core.mappers.SvPersonaRestringidaMapper;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvPersonaRestringidaDTO;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvProveedorDTO;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.SvPersonaRestringidaFilter;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvPersonaRestringidaEntity;
import com.copeinca.apicopeincaprov.modules.core.service.business.ISvPersonaRestringidaService;
import com.copeinca.apicopeincaprov.modules.core.service.crud.ISvPersonaRestringidaCrudService;
import com.copeinca.apicopeincaprov.modules.core.service.query.ISvPersonaRestringidaQueryService;
import com.copeinca.apicopeincaprov.modules.core.service.reader.ISvPersonaRestringidaExcelReaderService;
import com.copeinca.apicopeincaprov.modules.core.service.writer.ISvPersonaRestringidaWriterService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
* REST Controller para SvPersonaRestringidaEntity
* Define la API base pero sin endpoints implementados
*/
@RestController
@RequiredArgsConstructor
@RequestMapping(ApiPaths.Core.SVPERSONARESTRINGIDA)
public class SvPersonaRestringidaRest {

    private final ISvPersonaRestringidaService service;
    private final ISvPersonaRestringidaCrudService crudService;
    private final ISvPersonaRestringidaQueryService queryService;
    private final ISvPersonaRestringidaWriterService writerService;
    private final ISvPersonaRestringidaExcelReaderService readerService;
    private final SvPersonaRestringidaMapper mapper;

    // Los endpoints serán implementados según las necesidades específicas del negocio
    // Esta clase sirve como base para la definición de la API REST

    @GetMapping(value = "/{id}")
    public CustomMessageView<SvPersonaRestringidaDTO> findById(@PathVariable("id") String id) {

        SvPersonaRestringidaDTO result = queryService.findById(id);

        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/search")
    public CustomMessageView<PagedResult<SvPersonaRestringidaDTO>> search(@RequestBody PagedRequest<SvPersonaRestringidaFilter> filter) {

        PagedResult<SvPersonaRestringidaDTO> result = queryService.search(filter);

        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/download-excel")
    @ResponseStatus(HttpStatus.OK)
    public void downloadExcel(@RequestBody PagedRequest<SvPersonaRestringidaFilter> filter, HttpServletResponse httpServletResponse) throws Exception {

        PagedResult<SvPersonaRestringidaDTO> data = queryService.search(filter);

        AttachmentData attachmentData = writerService.exportToExcel(data.getResult());

        attachmentData.writeToResponse(httpServletResponse);

    }

    @PostMapping(value = "/read-excel", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public CustomMessageView<List<SvPersonaRestringidaDTO>> readExcel(@RequestPart("file") MultipartFile file) throws IOException {

        List<SvPersonaRestringidaDTO> result = readerService.readFromExcel(file.getInputStream());
        service.isExist(result);


        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/save")
    @ResponseStatus(HttpStatus.OK)
    public CustomMessageView<SvPersonaRestringidaEntity> save(@RequestBody SvPersonaRestringidaDTO dto) {

        SvPersonaRestringidaEntity result = crudService.save(dto);

        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/save-all")
    @ResponseStatus(HttpStatus.OK)
    public CustomMessageView<List<SvPersonaRestringidaEntity>> saveAll(@RequestBody List<SvPersonaRestringidaDTO> list) {

        List<SvPersonaRestringidaEntity> result = crudService.saveAll(list);

        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/save-all-masive")
    @ResponseStatus(HttpStatus.OK)
    public CustomMessageView<List<SvPersonaRestringidaDTO>> saveAllMasivo(@RequestBody List<SvPersonaRestringidaDTO> list) {

        return CustomMessageView.success(service.saveAllAndValidation(list));

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
