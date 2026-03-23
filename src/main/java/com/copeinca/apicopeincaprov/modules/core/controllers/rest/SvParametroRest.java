
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
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvParametroDTO;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.SvParametroFilter;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvParametroEntity;
import com.copeinca.apicopeincaprov.modules.core.service.business.ISvParametroService;
import com.copeinca.apicopeincaprov.modules.core.service.crud.ISvParametroCrudService;
import com.copeinca.apicopeincaprov.modules.core.service.query.ISvParametroQueryService;
import com.copeinca.apicopeincaprov.modules.core.service.reader.ISvParametroExcelReaderService;
import com.copeinca.apicopeincaprov.modules.core.service.writer.ISvParametroWriterService;
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
* REST Controller para SvParametroEntity
* Define la API base pero sin endpoints implementados
*/
@RestController
@RequiredArgsConstructor
@RequestMapping(ApiPaths.Core.SVPARAMETRO)
public class SvParametroRest {

    private final ISvParametroService service;
    private final ISvParametroCrudService crudService;
    private final ISvParametroQueryService queryService;
    private final ISvParametroWriterService writerService;
    private final ISvParametroExcelReaderService readerService;

    // Los endpoints serán implementados según las necesidades específicas del negocio
    // Esta clase sirve como base para la definición de la API REST

    @GetMapping(value = "/{id}")
    public CustomMessageView<SvParametroDTO> findById(@PathVariable("id") String id) {

        SvParametroDTO result = queryService.findById(id);

        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/search")
    public CustomMessageView<PagedResult<SvParametroDTO>> search(@RequestBody PagedRequest<SvParametroFilter> filter) {

        PagedResult<SvParametroDTO> result = queryService.search(filter);

        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/download-excel")
    @ResponseStatus(HttpStatus.OK)
    public void downloadExcel(@RequestBody PagedRequest<SvParametroFilter> filter, HttpServletResponse httpServletResponse) throws Exception {

        PagedResult<SvParametroDTO> data = queryService.search(filter);

        AttachmentData attachmentData = writerService.exportToExcel(data.getResult());

        attachmentData.writeToResponse(httpServletResponse);

    }

    @PostMapping(value = "/read-excel", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public CustomMessageView<List<SvParametroDTO>> readExcel(@RequestPart("file") MultipartFile file) throws IOException {

        List<SvParametroDTO> result = readerService.readFromExcel(file.getInputStream());

        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/save")
    @ResponseStatus(HttpStatus.OK)
    public CustomMessageView<SvParametroEntity> save(@RequestBody @Valid SvParametroDTO dto) {

        SvParametroEntity result = crudService.save(dto);

        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/save-all")
    @ResponseStatus(HttpStatus.OK)
    public CustomMessageView<List<SvParametroEntity>> saveAll(@RequestBody List<SvParametroDTO> list) {

        List<SvParametroEntity> result = crudService.saveAll(list);

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
