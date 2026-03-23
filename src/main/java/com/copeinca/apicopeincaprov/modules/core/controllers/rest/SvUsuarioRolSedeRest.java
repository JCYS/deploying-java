
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
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvUsuarioRolSedeDTO;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.SvUsuarioRolSedeFilter;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvUsuarioRolSedeEntity;
import com.copeinca.apicopeincaprov.modules.core.service.business.ISvUsuarioRolSedeService;
import com.copeinca.apicopeincaprov.modules.core.service.crud.ISvUsuarioRolSedeCrudService;
import com.copeinca.apicopeincaprov.modules.core.service.query.ISvUsuarioRolSedeQueryService;
import com.copeinca.apicopeincaprov.modules.core.service.reader.ISvUsuarioRolSedeExcelReaderService;
import com.copeinca.apicopeincaprov.modules.core.service.writer.ISvUsuarioRolSedeWriterService;
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
* REST Controller para SvUsuarioRolSedeEntity
* Define la API base pero sin endpoints implementados
*/
@RestController
@RequiredArgsConstructor
@RequestMapping(ApiPaths.Core.SVUSUARIOROLSEDE)
public class SvUsuarioRolSedeRest {

    private final ISvUsuarioRolSedeService service;
    private final ISvUsuarioRolSedeCrudService crudService;
    private final ISvUsuarioRolSedeQueryService queryService;
    private final ISvUsuarioRolSedeWriterService writerService;
    private final ISvUsuarioRolSedeExcelReaderService readerService;

    // Los endpoints serán implementados según las necesidades específicas del negocio
    // Esta clase sirve como base para la definición de la API REST

    @GetMapping(value = "/{id}")
    public CustomMessageView<SvUsuarioRolSedeDTO> findById(@PathVariable("id") String id) {

        SvUsuarioRolSedeDTO result = queryService.findById(id);

        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/search")
    public CustomMessageView<PagedResult<SvUsuarioRolSedeDTO>> search(@RequestBody PagedRequest<SvUsuarioRolSedeFilter> filter) {

        PagedResult<SvUsuarioRolSedeDTO> result = queryService.search(filter);

        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/download-excel")
    @ResponseStatus(HttpStatus.OK)
    public void downloadExcel(@RequestBody PagedRequest<SvUsuarioRolSedeFilter> filter, HttpServletResponse httpServletResponse) throws Exception {

        PagedResult<SvUsuarioRolSedeDTO> data = queryService.search(filter);

        AttachmentData attachmentData = writerService.exportToExcel(data.getResult());

        attachmentData.writeToResponse(httpServletResponse);

    }

    @PostMapping(value = "/read-excel", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public CustomMessageView<List<SvUsuarioRolSedeDTO>> readExcel(@RequestPart("file") MultipartFile file) throws IOException {

        List<SvUsuarioRolSedeDTO> result = readerService.readFromExcel(file.getInputStream());

        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/save")
    @ResponseStatus(HttpStatus.OK)
    public CustomMessageView<SvUsuarioRolSedeEntity> save(@RequestBody @Valid SvUsuarioRolSedeDTO dto) {

        SvUsuarioRolSedeEntity result = crudService.save(dto);

        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/save-all")
    @ResponseStatus(HttpStatus.OK)
    public CustomMessageView<List<SvUsuarioRolSedeEntity>> saveAll(@RequestBody List<SvUsuarioRolSedeDTO> list) {

        List<SvUsuarioRolSedeEntity> result = crudService.saveAll(list);

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
