
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
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvProveedorSedeDTO;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.SvProveedorSedeFilter;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvProveedorSedeEntity;
import com.copeinca.apicopeincaprov.modules.core.service.business.ISvProveedorSedeService;
import com.copeinca.apicopeincaprov.modules.core.service.crud.ISvProveedorSedeCrudService;
import com.copeinca.apicopeincaprov.modules.core.service.query.ISvProveedorSedeQueryService;
import com.copeinca.apicopeincaprov.modules.core.service.reader.ISvProveedorSedeExcelReaderService;
import com.copeinca.apicopeincaprov.modules.core.service.writer.ISvProveedorSedeWriterService;
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
* REST Controller para SvProveedorSedeEntity
* Define la API base pero sin endpoints implementados
*/
@RestController
@RequiredArgsConstructor
@RequestMapping(ApiPaths.Core.SVPROVEEDORSEDE)
public class SvProveedorSedeRest {

    private final ISvProveedorSedeService service;
    private final ISvProveedorSedeCrudService crudService;
    private final ISvProveedorSedeQueryService queryService;
    private final ISvProveedorSedeWriterService writerService;
    private final ISvProveedorSedeExcelReaderService readerService;

    // Los endpoints serán implementados según las necesidades específicas del negocio
    // Esta clase sirve como base para la definición de la API REST

    @GetMapping(value = "/{id}")
    public CustomMessageView<SvProveedorSedeDTO> findById(@PathVariable("id") String id) {

        SvProveedorSedeDTO result = queryService.findById(id);

        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/search")
    public CustomMessageView<PagedResult<SvProveedorSedeDTO>> search(@RequestBody PagedRequest<SvProveedorSedeFilter> filter) {

        PagedResult<SvProveedorSedeDTO> result = queryService.search(filter);

        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/download-excel")
    @ResponseStatus(HttpStatus.OK)
    public void downloadExcel(@RequestBody PagedRequest<SvProveedorSedeFilter> filter, HttpServletResponse httpServletResponse) throws Exception {

        PagedResult<SvProveedorSedeDTO> data = queryService.search(filter);

        AttachmentData attachmentData = writerService.exportToExcel(data.getResult());

        attachmentData.writeToResponse(httpServletResponse);

    }

    @PostMapping(value = "/read-excel", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public CustomMessageView<List<SvProveedorSedeDTO>> readExcel(@RequestPart("file") MultipartFile file) throws IOException {

        List<SvProveedorSedeDTO> result = readerService.readFromExcel(file.getInputStream());

        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/save")
    @ResponseStatus(HttpStatus.OK)
    public CustomMessageView<SvProveedorSedeEntity> save(@RequestBody @Valid SvProveedorSedeDTO dto) {

        SvProveedorSedeEntity result = crudService.save(dto);

        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/save-all")
    @ResponseStatus(HttpStatus.OK)
    public CustomMessageView<List<SvProveedorSedeEntity>> saveAll(@RequestBody List<SvProveedorSedeDTO> list) {

        List<SvProveedorSedeEntity> result = crudService.saveAll(list);

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
