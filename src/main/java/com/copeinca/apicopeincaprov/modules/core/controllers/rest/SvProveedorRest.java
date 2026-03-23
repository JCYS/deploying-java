
/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/controllers/rest/EntityRest.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.controllers.rest;

import com.copeinca.apicopeincaprov.commons.controllers.rest.ApiPaths;
import com.copeinca.apicopeincaprov.global.controllers.DocumentoDTO;
import com.copeinca.apicopeincaprov.global.dtos.AttachmentData;
import com.copeinca.apicopeincaprov.global.dtos.response.CustomMessageView;
import com.copeinca.apicopeincaprov.global.dtos.response.MessageViewModel;
import com.copeinca.apicopeincaprov.global.dtos.response.PagedRequest;
import com.copeinca.apicopeincaprov.global.dtos.response.PagedResult;
import com.copeinca.apicopeincaprov.integration.LaserFiche.dtos.FileUploadDto;
import com.copeinca.apicopeincaprov.integration.LaserFiche.pojos.uploadFiles.response.SubirArchivoECMSoapResponse;
import com.copeinca.apicopeincaprov.integration.LaserFiche.services.interfaces.ILaserFiche;
import com.copeinca.apicopeincaprov.integration.Mendix.service.interfaces.ClientService;
import com.copeinca.apicopeincaprov.modules.core.mappers.SvProveedorMapper;
import com.copeinca.apicopeincaprov.modules.core.models.dto.*;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.SvProveedorFilter;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvProveedorEntity;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvProveedorSedeEntity;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvSedeEntity;
import com.copeinca.apicopeincaprov.modules.core.repositories.jpa.ISvProveedorRepository;
import com.copeinca.apicopeincaprov.modules.core.repositories.jpa.ISvProveedorSedeRepository;
import com.copeinca.apicopeincaprov.modules.core.repositories.jpa.ISvSedeRepository;
import com.copeinca.apicopeincaprov.modules.core.service.business.ISvProveedorService;
import com.copeinca.apicopeincaprov.modules.core.service.crud.ISvProveedorCrudService;
import com.copeinca.apicopeincaprov.modules.core.service.query.ISvProveedorQueryService;
import com.copeinca.apicopeincaprov.modules.core.service.reader.ISvProveedorExcelReaderService;
import com.copeinca.apicopeincaprov.modules.core.service.writer.ISvProveedorWriterService;
import com.copeinca.apicopeincaprov.modules.notificacion.models.dto.NotificacionCreacionProveedorDTO;
import com.copeinca.apicopeincaprov.modules.notificacion.service.INotificationService;
import com.sap.cloud.security.xsuaa.token.Token;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
* REST Controller para SvProveedorEntity
* Define la API base pero sin endpoints implementados
*/
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(ApiPaths.Core.SVPROVEEDOR)
public class SvProveedorRest {

    private final ISvProveedorService service;
    private final ISvProveedorCrudService crudService;
    private final ISvProveedorQueryService queryService;
    private final ISvProveedorWriterService writerService;
    private final ISvProveedorExcelReaderService readerService;
    private final SvProveedorMapper mapper;



    private final INotificationService iNotificationService;


    private final ISvProveedorRepository proveedorRepository;

    private final ISvSedeRepository iSvSedeRepository;
    private final ISvProveedorSedeRepository iSvProveedorSedeRepository;

    //@Autowired
    public ClientService clientService;

    //@Autowired
    public ILaserFiche laserFiche;

    // Los endpoints serán implementados según las necesidades específicas del negocio
    // Esta clase sirve como base para la definición de la API REST

    @GetMapping(value = "/{id}")
    public CustomMessageView<SvProveedorDTO> findById(@PathVariable("id") String id) {

        SvProveedorDTO result = queryService.findById(id);

        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/search")
    public CustomMessageView<PagedResult<SvProveedorDTO>> search(@RequestBody PagedRequest<SvProveedorFilter> filter) {

        PagedResult<SvProveedorDTO> result = queryService.search(filter);

        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/download-excel")
    @ResponseStatus(HttpStatus.OK)
    public void downloadExcel(@RequestBody PagedRequest<SvProveedorFilter> filter, HttpServletResponse httpServletResponse) throws Exception {

        PagedResult<SvProveedorDTO> data = queryService.search(filter);

        AttachmentData attachmentData = writerService.exportToExcel(data.getResult());

        attachmentData.writeToResponse(httpServletResponse);

    }

    @PostMapping(value = "/read-excel", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public CustomMessageView<List<SvProveedorDTO>> readExcel(
            @RequestPart("file") MultipartFile file) throws IOException {
        List<SvProveedorDTO> result = readerService.readFromExcel(file.getInputStream());
        //List<SvProveedorDTO> isExistList = service.isExist(result);
        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/read-excel-validations", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public CustomMessageView<List<SvProveedorDTO>> readExcelValidation(
            @RequestPart("file") MultipartFile file,
            @RequestPart(value = "sedes", required = false) List<SvSedeDTO> aSedes) throws IOException {
        List<SvProveedorDTO> result = readerService.readFromExcel(file.getInputStream());
        List<SvProveedorDTO> isExistList = service.isExist(result, aSedes);
        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/save")
    @ResponseStatus(HttpStatus.OK)
    public CustomMessageView<SvProveedorEntity> save(@RequestBody @Valid SvProveedorDTO dto) {

        SvProveedorEntity result = crudService.save(dto);

        return CustomMessageView.success(result);

    }

    @PostMapping(value = "/save-all")
    @ResponseStatus(HttpStatus.OK)
    public CustomMessageView<List<SvProveedorEntity>> saveAll(@RequestBody List<SvProveedorDTO> list) {
        List<SvProveedorEntity> result = crudService.saveAll(list);
        return CustomMessageView.success(result);

    }

    private static Set<String> parseSedes(String sedesNombres) {
        if (sedesNombres == null || sedesNombres.isBlank()) return Set.of();
        return Arrays.stream(sedesNombres.split("\\|"))
                .map(String::trim)
                .filter(s -> !s.isBlank())
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    @PostMapping(value = "/save-all-masive")
    @ResponseStatus(HttpStatus.OK)
    public CustomMessageView<List<SvProveedorDTO>> saveAllMasivo(@RequestBody List<SvProveedorDTO> list) {
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

    @PostMapping(
            value = "/completarDatos",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public CustomMessageView<?> subirDocumentos(
            @RequestPart(value = "proveedor", required = true) SvRegistroProveedorDto metadata,
            @RequestPart(value = "filesSSO", required = false) List<MultipartFile> aFilesSSO,
            @RequestPart(value = "filesQA", required = false) List<MultipartFile> aFilesQA,
            @RequestPart(value = "filesAB", required = false) List<MultipartFile> aFilesAB,
            @RequestPart(value = "filesAAR", required = false) List<MultipartFile> aFilesAAR
    ) throws Exception {
        SvRegistroProveedorDto results = null;
        try{
            results = crudService.saveInfo(metadata,aFilesSSO,aFilesQA,aFilesAB,aFilesAAR);
            return CustomMessageView.success(results);
        } catch (Exception e) {
            return CustomMessageView.builder().successfully(false).finished(false).messages(List.of(
                    MessageViewModel.builder()
                            .title("Creacion proveedor")
                            .description(e.getMessage())
                            .build()
            )).response(metadata).build();
        }

    }

    @GetMapping(value = "/proveedor/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomMessageView<SvRegistroProveedorDto> getInformationSupplier(@PathVariable("id") String sId) {

//        SvRegistroProveedorDto result = new SvRegistroProveedorDto();
//        result.setProveedorInfo(queryService.findById(id));
        SvRegistroProveedorDto result = crudService.getInfoSupplier(sId);

        return CustomMessageView.success(result);

    }

//    @PostMapping(value = "/updateAndSend")
//    @ResponseStatus(HttpStatus.OK)
//    public CustomMessageView<SvProveedorEntity> updateAndSend(@RequestBody SvProveedorDTO svProveedorDTO) {
//
//
//        //TODO State and Send
//
//
//
//        return CustomMessageView.success(crudService.save(svProveedorDTO));
//    }
    @PostMapping(value = "/updateAndSendCP")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> updateAndSend(
            @RequestPart(value = "proveedor", required = true) SvRegistroProveedorDto metadata,
            @RequestPart(value = "filesSSO", required = false) List<MultipartFile> aFilesSSO,
            @RequestPart(value = "filesQA", required = false) List<MultipartFile> aFilesQA,
            @RequestPart(value = "filesAB", required = false) List<MultipartFile> aFilesAB,
            @RequestPart(value = "filesAAR", required = false) List<MultipartFile> aFilesAAR
    ) throws Exception {
//        for (MultipartFile file: aFilesSSO ){
//            System.out.println("filesSSO: "+file.getOriginalFilename());
//        }
//        for (MultipartFile file: aFilesQA ){
//            System.out.println("filesQA: "+file.getOriginalFilename());
//        }
//        for (MultipartFile file: aFilesAB ){
//            System.out.println("aFilesAB: "+file.getOriginalFilename());
//        }
//        for (MultipartFile file: aFilesAAR ){
//            System.out.println("aFilesAAR: "+file.getOriginalFilename());
//        }
//        System.out.println();
        SvRegistroProveedorDto results = crudService.saveInfo(metadata,aFilesSSO,aFilesQA,aFilesAB,aFilesAAR);
//        NotificacionCreacionProveedorDTO input = NotificacionCreacionProveedorDTO.builder( )
////                            .recipients( supplier.getEmail( ) )
//                .recipients( "123456789@yopmail.com" ) //--- TODO: Eliminar para calidad y PRD
//                .companyName( results.getProveedorInfo().getNombreFiscal())
//                .username( results.getProveedorInfo().getEmail() )
//                .urlBtp( "https://copeinca-com.launchpad.cfapps.us10.hana.ondemand.com/site?siteId=92f3480e-7b75-4df7-9c9f-53369af34681#Shell-home" )
//                .build( );
//
//        //--- Enviamos la notificación
//        iNotificationService.sendCreacionProveedor( input );
//        proveedorRepository.updateCaFechaEvaluacion( true, results.getProveedorInfo().getId( ) );
        return ResponseEntity.ok(metadata);
    }

    @PostMapping( value = "/update-sso" )
    @ResponseStatus( HttpStatus.OK )
    public CustomMessageView<?> updateSso( @RequestBody SvProveedorDTO svProveedorDTO ) {
        service.updateSsoEvaluation( svProveedorDTO );
        //TODO Send Notification

        return CustomMessageView.success( svProveedorDTO );
    }

    @PostMapping( value = "/update-ca" )
    @ResponseStatus( HttpStatus.OK )
    public CustomMessageView<?> updateCa( @RequestBody SvProveedorDTO svProveedorDTO ) {
        service.updateCaEvaluation( svProveedorDTO );
        //TODO Send Notification

        return CustomMessageView.success( svProveedorDTO );
    }

}
