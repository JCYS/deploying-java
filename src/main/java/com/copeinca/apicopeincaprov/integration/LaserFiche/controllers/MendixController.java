package com.copeinca.apicopeincaprov.integration.LaserFiche.controllers;

import com.copeinca.apicopeincaprov.integration.CloudIdentityService.service.interfaces.IASClientService;
import com.copeinca.apicopeincaprov.integration.LaserFiche.services.interfaces.ILaserFiche;
import com.copeinca.apicopeincaprov.integration.Mendix.pojos.*;
import com.copeinca.apicopeincaprov.integration.Mendix.service.interfaces.ClientService;
import com.copeinca.apicopeincaprov.modules.core.repositories.jpa.ISvProveedorRepository;
import com.copeinca.apicopeincaprov.modules.core.service.business.ISvPersonalService;
import com.copeinca.apicopeincaprov.modules.core.service.business.ISvTipoDocumentoPlanillaService;
import com.copeinca.apicopeincaprov.modules.notificacion.service.INotificationService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@Log4j2
@RestController
@RequestMapping(path = "/mendix")
public class MendixController {
    @Autowired
    public ClientService clientService;

    @Autowired
    public ILaserFiche laserFiche;

    @Autowired
    public IASClientService iasClientService;

    @Autowired
    private INotificationService iNotificationService;

    @Autowired
    private ISvProveedorRepository proveedorRepository;

    @Autowired
    private ISvPersonalService iSvPersonalService;

    @Autowired
    private ISvTipoDocumentoPlanillaService iSvTipoDocumentoPlanillaService;



//    @GetMapping(path = "/testDestination")
//    public ResponseEntity<String> readAll(@AuthenticationPrincipal Token token) {
//        try {
//            final Destination destination = DestinationAccessor.getDestination("LASERFICHE_SOAP");
//            return new ResponseEntity<String>(destination.toString(), HttpStatus.OK);
//
//            //return ResponseEntity.ok( new Gson().toJson(businessPartners));
//        } catch (final DestinationAccessException e) {
//            log.error(e.getMessage(), e);
//            return ResponseEntity.internalServerError().body("Failed to fetch destination.");
//        }
//    }
//    @GetMapping(path = "/testUser")
//    public ResponseEntity<String> readUser(@AuthenticationPrincipal Token token) {
//            log.warn("Email: " +token.getEmail());
////        log.warn("Email: " +token.get);
//            return new ResponseEntity<String>("API"+ token.getAppToken() , HttpStatus.OK);
//
//    }
//    @GetMapping(path = "/email")
//    public ResponseEntity<String> reademailuser(@AuthenticationPrincipal Token token) {
//        log.warn("Email: " +token.getEmail());
//        return new ResponseEntity<String>("API"+ token.getAppToken() , HttpStatus.OK);
//
//    }
//    @GetMapping(path = "/infoUser")
//    public ResponseEntity<String> readGroupsProperties(@AuthenticationPrincipal Jwt jwt) {
//        log.warn("Email: " +jwt.getClaims());
//        NotificacionCreacionProveedorDTO input = NotificacionCreacionProveedorDTO.builder( )
    ////                            .recipients( supplier.getEmail( ) )
//                .recipients( "123456789@yopmail.com" ) //--- TODO: Eliminar para calidad y PRD
//                .companyName( "PRUEBA PRUEBA")
//                .username( "username" )
//                .urlBtp( "https://copeinca-com.launchpad.cfapps.us10.hana.ondemand.com/site?siteId=92f3480e-7b75-4df7-9c9f-53369af34681#Shell-home" )
//                .build( );
//
//        //--- Enviamos la notificación
//        iNotificationService.sendCreacionProveedor( input );
//        return new ResponseEntity<String>("API"+ jwt.getClaims() , HttpStatus.OK);
//
//    }
//    @GetMapping("/jwtInforProv")
//    public ResponseEntity<JwtInfoDTO> readGroups(@AuthenticationPrincipal Jwt jwt) {
//
//        JwtInfoDTO dto = new JwtInfoDTO();
//
//        dto.setSub(jwt.getClaimAsString("sub"));
//        dto.setUserName(jwt.getClaimAsString("user_name"));
//        dto.setEmail(jwt.getClaimAsString("email"));
//        dto.setFamilyName(jwt.getClaimAsString("family_name"));
//
//        Map<String, Object> xsSystemAttributes = jwt.getClaim("xs.system.attributes");
//
//        if (xsSystemAttributes != null) {
//            dto.setRoleCollections(
//                    (List<String>) xsSystemAttributes.get("xs.rolecollections")
//            );
//            dto.setSamlGroups(
//                    (List<String>) xsSystemAttributes.get("xs.saml.groups")
//            );
//        }
//
//        return ResponseEntity.ok(dto);
//    }
//    @GetMapping(path = "/testUserJWT")
//    public ResponseEntity<Object> readUserJwt(@AuthenticationPrincipal Jwt jwt) {
//
//        if (jwt == null) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
//                    .body("No JWT token received");
//        }
//
//        return ResponseEntity.ok(jwt.getClaims());
//    }

//    @GetMapping(path = "/readSedes")
//    public ResponseEntity<List<SedesPojo>> readSedes() throws IOException {
//        List<SedesPojo> lista = clientService.getSedes();
//        return ResponseEntity.ok(lista);
//    }
//    @GetMapping(path = "/readAreas")
//    public ResponseEntity<List<AreasPojo>> readAreas() throws IOException {
//        List<AreasPojo> lista = clientService.getAreas();
//        return ResponseEntity.ok(lista);
//    }
//    @GetMapping(path = "/readZonas")
//    public ResponseEntity<List<ZonasPojo>> readZonas() throws IOException {
//        List<ZonasPojo> lista = clientService.getZonas();
//        return ResponseEntity.ok(lista);
//    }


//    @GetMapping(path = "/readTipoDoc")
//    public ResponseEntity<List<DocTipoPojo>> readDocTipo() throws IOException {
//        List<DocTipoPojo> lista = clientService.getDocTipo();
//        return ResponseEntity.ok(lista);
//    }
//
//    @GetMapping(path = "/readGroups", produces = "application/json")
//    public ResponseEntity<ScimListResponse<ScimUser>> readGroups() throws IOException, URISyntaxException {
//        //iasClientService.groups();
//        return ResponseEntity.ok(iasClientService.usersByGroupDisplay());
//    }

    @GetMapping(path = "/readDocPlantillaSaveAndUpdate")
    public ResponseEntity<List<DocPlantillaPojo>> readDocPlantilla() throws IOException {
        List<DocPlantillaPojo> lista = clientService.getDocPlantilla();
        iSvTipoDocumentoPlanillaService.saveAll(lista);
//        try{
//            //laserFiche.generateXml();
//            IdArchivosECMEnc ids = new IdArchivosECMEnc(
//                    List.of("C63F4-2E18D-96DBE-0467E-8A384-66C22-26000","66BDC-93C77-8426E-C53CC-385A1-A7E4A-17000","66BDC-93C77-8426E-C53CC-385A1-A7E4A-17000")
//            );
//            laserFiche.deleteFilesLaserFiche(ids);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }

        return ResponseEntity.ok(null);
    }

    @GetMapping(path = "/readPersonaVisitadaSaveAndUpdate")
    public ResponseEntity<List<PersonaVisitadaPojo>> readPersonaVisitada(@RequestParam String sede) throws IOException {
        List<PersonaVisitadaPojo> lista = clientService.getPersonaVisitadas(sede);

        iSvPersonalService.saveAll(lista);

        return ResponseEntity.ok(lista);
    }

//    @PostMapping(
//            value = "/proveedoresMockup/{id}/documentos",
//            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
//    )
//    public ResponseEntity<?> subirDocumentos(
//            @PathVariable String id,
//            @RequestPart("metadata") List<DocumentoDTO> metadata,
//            @RequestPart("filesSSO") List<MultipartFile> aFilesSSO,
//            @RequestPart("filesCalidad") List<MultipartFile> aFilesCalidad
//    ) throws Exception {
//        List<FileUploadDto> afiles = new ArrayList<>();
//        for (MultipartFile file: aFilesSSO ){
//            System.out.println("filesSSO: "+file.getOriginalFilename());
//            afiles.add(FileUploadDto.builder()
//                            .pArchivoOrigen(file)
//                    .build());
//        }
//        SubirArchivoECMSoapResponse ex = laserFiche.uploadFilesMasive(afiles);
//        System.out.println("Response controller: " + ex.getBody().getResponse().getResult().toString());
//        for (MultipartFile file: aFilesCalidad ){
//            System.out.println("filesCalidad: "+file.getOriginalFilename());
//        }
//        System.out.println(metadata.get(0).toString());
//
//        return ResponseEntity.ok().build();
//    }
}
