
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
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvUsuarioDTO;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.SvUsuarioFilter;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvUsuarioEntity;
import com.copeinca.apicopeincaprov.modules.core.service.business.ISvUsuarioService;
import com.copeinca.apicopeincaprov.modules.core.service.crud.ISvUsuarioCrudService;
import com.copeinca.apicopeincaprov.modules.core.service.query.ISvUsuarioQueryService;
import com.copeinca.apicopeincaprov.modules.core.service.reader.ISvUsuarioExcelReaderService;
import com.copeinca.apicopeincaprov.modules.core.service.writer.ISvUsuarioWriterService;
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
* REST Controller para SvUsuarioEntity
* Define la API base pero sin endpoints implementados
*/
@RestController
@RequiredArgsConstructor
@RequestMapping(ApiPaths.Core.SVUSUARIO)
public class SvUsuarioRest {

    private final ISvUsuarioService service;
    private final ISvUsuarioCrudService crudService;
    private final ISvUsuarioQueryService queryService;
    private final ISvUsuarioWriterService writerService;
    private final ISvUsuarioExcelReaderService readerService;

    // Los endpoints serán implementados según las necesidades específicas del negocio
    // Esta clase sirve como base para la definición de la API REST

    @GetMapping(value = "/{id}")
    public CustomMessageView<SvUsuarioDTO> findById(@PathVariable("id") String id) {

        SvUsuarioDTO result = queryService.findById(id);

        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/search")
    public CustomMessageView<PagedResult<SvUsuarioDTO>> search(@RequestBody PagedRequest<SvUsuarioFilter> filter) {

        PagedResult<SvUsuarioDTO> result = queryService.search(filter);

        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/download-excel")
    @ResponseStatus(HttpStatus.OK)
    public void downloadExcel(@RequestBody PagedRequest<SvUsuarioFilter> filter, HttpServletResponse httpServletResponse) throws Exception {

        PagedResult<SvUsuarioDTO> data = queryService.search(filter);

        AttachmentData attachmentData = writerService.exportToExcel(data.getResult());

        attachmentData.writeToResponse(httpServletResponse);

    }

    @PostMapping(value = "/read-excel", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public CustomMessageView<List<SvUsuarioDTO>> readExcel(@RequestPart("file") MultipartFile file) throws IOException {

        List<SvUsuarioDTO> result = readerService.readFromExcel(file.getInputStream());

        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/save")
    @ResponseStatus(HttpStatus.OK)
    public CustomMessageView<SvUsuarioEntity> save(@RequestBody @Valid SvUsuarioDTO dto) {

        SvUsuarioEntity result = crudService.save(dto);

        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/save-all")
    @ResponseStatus(HttpStatus.OK)
    public CustomMessageView<List<SvUsuarioEntity>> saveAll(@RequestBody List<SvUsuarioDTO> list) {

        List<SvUsuarioEntity> result = crudService.saveAll(list);

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
