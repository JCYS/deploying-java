package com.copeinca.apicopeincaprov.global.controllers;


import com.copeinca.apicopeincaprov.integration.CloudIdentityService.pojos.ScimListResponse;
import com.copeinca.apicopeincaprov.integration.CloudIdentityService.pojos.ScimUser;
import com.copeinca.apicopeincaprov.integration.CloudIdentityService.service.interfaces.IASClientService;
import com.copeinca.apicopeincaprov.integration.LaserFiche.dtos.FileUploadDto;
import com.copeinca.apicopeincaprov.integration.LaserFiche.pojos.deleteFiles.body.IdArchivosECMEnc;
import com.copeinca.apicopeincaprov.integration.LaserFiche.pojos.uploadFiles.response.SubirArchivoECMSoapResponse;
import com.copeinca.apicopeincaprov.integration.LaserFiche.services.interfaces.ILaserFiche;
import com.copeinca.apicopeincaprov.integration.Mendix.pojos.*;
import com.copeinca.apicopeincaprov.integration.Mendix.service.interfaces.ClientService;
import com.copeinca.apicopeincaprov.modules.core.repositories.jpa.ISvProveedorRepository;
import com.copeinca.apicopeincaprov.modules.core.service.business.ISvTipoDocumentoPlanillaService;
import com.copeinca.apicopeincaprov.modules.notificacion.models.dto.NotificacionCreacionProveedorDTO;
import com.copeinca.apicopeincaprov.modules.notificacion.service.INotificationService;
import com.copeinca.apicopeincaprov.security.dtos.JwtInfoDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sap.cloud.sdk.cloudplatform.connectivity.Destination;
import com.sap.cloud.sdk.cloudplatform.connectivity.DestinationAccessor;
import com.sap.cloud.sdk.cloudplatform.connectivity.exception.DestinationAccessException;
import com.sap.cloud.security.token.Token;
import com.sap.cloud.security.token.TokenClaims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Log4j2
@RestController
@RequestMapping(path = "/testing")

public class MainController {

    @Autowired(required = false)
    private Token token;
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
@GetMapping("/jwtInforProv2")
public ResponseEntity<?> readGroupss(@AuthenticationPrincipal Jwt jwt) {

    JwtInfoDTO dto = new JwtInfoDTO();

    dto.setSub(jwt.getClaimAsString("sub"));
    dto.setUserName(jwt.getClaimAsString("user_name"));
    dto.setEmail(jwt.getClaimAsString("email"));
    dto.setFamilyName(jwt.getClaimAsString("family_name"));

    Map<String, Object> xsSystemAttributes = jwt.getClaim("xs.system.attributes");

    if (xsSystemAttributes != null) {
        dto.setRoleCollections(
                (List<String>) xsSystemAttributes.get("xs.rolecollections")
        );
        dto.setSamlGroups(
                (List<String>) xsSystemAttributes.get("xs.saml.groups")
        );
    }

    return ResponseEntity.ok(jwt);
}
    @GetMapping("/jwtInforProv")
    public ResponseEntity<JwtInfoDTO> readGroups(@AuthenticationPrincipal Jwt jwt) {

        JwtInfoDTO dto = new JwtInfoDTO();

        dto.setSub(jwt.getClaimAsString("sub"));
        dto.setUserName(jwt.getClaimAsString("user_name"));
        dto.setEmail(jwt.getClaimAsString("email"));
        dto.setFamilyName(jwt.getClaimAsString("family_name"));

        Map<String, Object> xsSystemAttributes = jwt.getClaim("xs.system.attributes");

        if (xsSystemAttributes != null) {
            dto.setRoleCollections(
                    (List<String>) xsSystemAttributes.get("xs.rolecollections")
            );
            dto.setSamlGroups(
                    (List<String>) xsSystemAttributes.get("xs.saml.groups")
            );
        }

        return ResponseEntity.ok(dto);
    }
    @GetMapping(path = "/testUserJW2T")
    public ResponseEntity<Object> readUserJwtv2(@AuthenticationPrincipal Jwt jwt) {

        if (jwt == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("No JWT token received");
        }

        String token = jwt.getTokenValue(); // 🔥 aquí tienes el token en texto

        return ResponseEntity.ok(token);
    }
    @GetMapping(path = "/testUserJWTv2")
    public ResponseEntity<Object> readUserJwt(HttpServletRequest request) {

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("No JWT token received");
        }

        String token = authHeader.substring(7); // 🔥 quitar "Bearer "

        return ResponseEntity.ok(token);
    }
    @GetMapping(path = "/testUserJWT")
    public ResponseEntity<Object> readUserJwt(@AuthenticationPrincipal Jwt jwt) {

        if (jwt == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("No JWT token received");
        }

        return ResponseEntity.ok(jwt.getClaims());
    }
    @GetMapping("/fetchToken")
    public ResponseEntity<String> returnToken(@AuthenticationPrincipal Token token) {
        //access to token claims is available via token object, e.g.
        String userName = token.getPrincipal().getName();
        String zoneId = token.getZoneId();
        List<String> scopes = token.getClaimAsStringList(TokenClaims.XSUAA.SCOPES);


        return ResponseEntity.ok(userName);
    }
    @GetMapping("/v1/scopesss")
    public String getToken() {
        if (token != null) {
            return token.getAppTid(); // o token.getTokenValue()
        }
        return "Token is null";
    }
    @GetMapping("/token")
    public String getToken(Authentication auth) {
        Jwt jwt = (Jwt) auth.getPrincipal();
        return jwt.getTokenValue();
    }

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
//    @GetMapping(path = "/readUM")
//    public ResponseEntity<List<UMPojo>> readUM() throws IOException {
//        List<UMPojo> lista = clientService.getUM();
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
//         //iasClientService.groups();
//        return ResponseEntity.ok(iasClientService.usersByGroupDisplay());
//    }
//
//    @GetMapping(path = "/readDocPlantillaSaveAndUpdate")
//    public ResponseEntity<List<DocPlantillaPojo>> readDocPlantilla() throws IOException {
//        List<DocPlantillaPojo> lista = clientService.getDocPlantilla();
//        iSvTipoDocumentoPlanillaService.saveAll(lista);
//        return ResponseEntity.ok(null);
//    }

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
