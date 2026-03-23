package com.copeinca.apicopeincaprov.integration.CloudIdentityService.service;

import com.copeinca.apicopeincaprov.integration.CloudIdentityService.config.DestinationIAS;
import com.copeinca.apicopeincaprov.integration.CloudIdentityService.pojos.ScimGroupResponse;
import com.copeinca.apicopeincaprov.integration.CloudIdentityService.pojos.ScimListResponse;
import com.copeinca.apicopeincaprov.integration.CloudIdentityService.pojos.ScimUser;
import com.copeinca.apicopeincaprov.integration.CloudIdentityService.pojos.ScimUserDTO;
import com.copeinca.apicopeincaprov.integration.CloudIdentityService.service.interfaces.IASClientService;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvRolDTO;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvSedeDTO;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvUsuarioDTO;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvUsuarioRolSedeDTO;
import com.copeinca.apicopeincaprov.modules.core.models.entities.*;
import com.copeinca.apicopeincaprov.modules.core.repositories.jpa.*;
import com.copeinca.apicopeincaprov.modules.core.service.crud.ISvUsuarioCrudService;
import com.copeinca.apicopeincaprov.modules.core.service.crud.ISvUsuarioRolSedeCrudService;
import com.copeinca.apicopeincaprov.security.dtos.UserSedeDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sap.cloud.sdk.cloudplatform.connectivity.HttpClientAccessor;
import com.sap.cloud.sdk.cloudplatform.connectivity.HttpDestination;
import lombok.extern.log4j.Log4j2;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Log4j2
@Service
public class ClientIASImpl implements IASClientService {

    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private ISvUsuarioRolSedeCrudService iSvUsuarioRolSedeCrudService;

    @Autowired
    private ISvRolRepository iSvRolRepository;

    @Autowired
    private ISvUsuarioCrudService iSvUsuarioCrudService;

    @Autowired
    private ISvUsuarioRolSedeRepository iSvUsuarioRolSedeRepository;

    @Autowired
    private  ISvUsuarioRepository iSvUsuarioRepository;

    @Autowired
    private ISvProveedorRepository iSvProveedorRepository;

    @Autowired
    private ISvProveedorSedeRepository iSvProveedorSedeRepository;

//    @Autowired
//    private ISvRolRepository iSvRolRepository;

//    private final ISvUsuarioRolSedeCrudService crudService;


    @Override
    public ScimListResponse<ScimUser> usersByGroupDisplay() throws IOException, URISyntaxException {

//        List<SvProveedorEntity> aL = iSvProveedorRepository.findAll();
//        for (SvProveedorEntity item: aL){
//            List<SvProveedorSedeEntity> ll = iSvProveedorSedeRepository.findAllByProveedorId(item.getId());
//            if(ll.size() < 1){
//                iSvProveedorSedeRepository.save(SvProveedorSedeEntity.builder()
//                        .isActive(true)
//                        .proveedorId(item.getId())
//                        .sedeId("2393c11c-9582-452d-ab99-2b242b545d83")
//                        .isDeleted(false)
//                        .build());
//                System.out.println("Creado para : "+item.getId());
//            }
//        }


        try {
            HttpDestination dest = DestinationIAS.create();
            String filter = "userType eq \"" + "employee" + "\"";
            URI uri = new URIBuilder(dest.getUri().toString() + "/scim/Users")
                    .addParameter("filter", filter)
                    .addParameter("count", "2")
                    .build();
            HttpGet get = new HttpGet(uri);
    //        get.setHeader("Accept", "application/scim+json");
            get.setHeader("Accept", "application/scim+json, application/json");

            HttpResponse resp = HttpClientAccessor.getHttpClient(dest).execute(get);

            int status = resp.getStatusLine().getStatusCode();
            String body = EntityUtils.toString(resp.getEntity());



            if (status < 200 || status >= 300) {
                throw new IOException("SCIM failed. status=" + status + " body=" + body);
            }
            List<SvRolEntity> svRolEntityList = iSvRolRepository.findAll();
            System.out.println(body);
            ScimListResponse<ScimUser> page = mapper.readValue(
                    body,
                    mapper.getTypeFactory().constructParametricType(ScimListResponse.class, ScimUser.class)
            );

            if (page.getResources() != null) {
                for (ScimUser u : page.getResources()) {

                    String userName = u.getUserName();
                    String displayName = u.getDisplayName();

                    String email = null;
                    if (u.getEmails() != null && !u.getEmails().isEmpty()) {
                        email = u.getEmails().get(0).getValue();
                    }

                    System.out.println("USER: " + userName + " | " + displayName + " | " + email);
                    //is
                    List<SvUsuarioEntity> aUserEmail = iSvUsuarioRepository.findAllByEmail(email);
                    if(aUserEmail.size() < 1){
                        System.out.println("USER NO ENCONTRADO: " + userName + " | " + displayName + " | " + email);
                        SvUsuarioEntity oUser = iSvUsuarioCrudService.save(SvUsuarioDTO.builder()
                                .email(email)
                                .nombre(userName)
                                .apellidos(displayName)
                                .isDeleted(false)
                                .identificadorExterno(u.getId())
                                .build()
                        );
                        // loop grupos (opcional)
                        if (u.getGroups() != null) {
                            for (SvRolEntity item: svRolEntityList){
                                for (var g : u.getGroups()) {
                                    if(item.getId().equals(g.getDisplay())){
                                        System.out.println("  - GROUP: " + g.getDisplay() + " (" + g.getValue() + ")");
                                        iSvUsuarioRolSedeCrudService.save(SvUsuarioRolSedeDTO.builder()
                                                .sedeId("ef6f00b1-7893-45c0-bc46-46e609ae12e1")
                                                .usuarioId(oUser.getId())
                                                .isActive(true)
                                                .isDeleted(false)
                                                .rolCode(g.getDisplay())
                                                .build());
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return page;

        } catch (IOException e) {
            throw e;
        } catch (Exception e) {
            throw new IOException("Error calling SCIM Users", e);
        }
    }

    @Override
    public ScimGroupResponse updateUserWithGroup(UserSedeDto userSede) throws IOException, URISyntaxException {
        HttpDestination dest = DestinationIAS.create();
        var client = HttpClientAccessor.getHttpClient(dest);

        List<SvUsuarioRolSedeEntity> aList = iSvUsuarioRolSedeRepository.findAllByUsuarioId(userSede.getUsuarioDTO().getId());

        if(userSede.getRoles() != null && userSede.getRoles().size() > 0){

            List<SvRolEntity> svRolEntityList = iSvRolRepository.findAll();


            for(SvUsuarioRolSedeEntity item: aList){
//                if(!item.getRolCode().equals("controlprov_sede")){
                iSvUsuarioRolSedeRepository.deleteById(item.getId());
//                    iSvUsuarioRolSedeCrudService.softDeleteById(item.getId()); //
//                }
            }


            for (SvRolEntity item: svRolEntityList){
                if(!item.getRolName().equals("SEDE")){
                    this.replaceUser(userSede,item.getRolName());//IAS
                }
            }

            for(SvRolDTO item: userSede.getRoles()){
                this.addUSertoGroup(userSede,item.getRolName()); //IAS
                for(SvSedeDTO sede: userSede.getSedes()){
                    iSvUsuarioRolSedeCrudService.save(SvUsuarioRolSedeDTO.builder()
                            .sedeId(sede.getId())
                            .usuarioId(userSede.getUsuarioDTO().getId())
                            .isActive(true)
                            .isDeleted(false)
                            .rolCode(item.getId())
                            .build());
                }

//                iSvUsuarioRolSedeCrudService.save(SvUsuarioRolSedeDTO.builder()
//                        .sedeId("ef6f00b1-7893-45c0-bc46-46e609ae12e1")
//                        .usuarioId(userSede.getUsuarioDTO().getId())
//                        .isActive(true)
//                        .isDeleted(false)
//                        .rolCode(item.getId())
//                        .build());
            }
        }

//        if(userSede.getSedes() != null && userSede.getSedes().size() > 0){
//            for(SvUsuarioRolSedeEntity item: aList){
//                if(item.getRolCode().equals("controlprov_sede")){
//                    iSvUsuarioRolSedeCrudService.softDeleteById(item.getId());
//                }
//            }
//            for(SvSedeDTO sede: userSede.getSedes()){
//                iSvUsuarioRolSedeCrudService.save(SvUsuarioRolSedeDTO.builder()
//                        .sedeId(sede.getId())
//                        .usuarioId(userSede.getUsuarioDTO().getId())
//                        .isActive(true)
//                        .isDeleted(false)
//                        .rolCode("controlprov_sede")
//                        .build());
//            }
//        }



        return null;
    }

    @Override
    public ScimUserDTO searchUSer(String userId) throws IOException, URISyntaxException {
        HttpDestination dest = DestinationIAS.create();
        var client = HttpClientAccessor.getHttpClient(dest);
        try{
            String url = "/scim/Users/"+userId; // baseURL viene del Destination

            HttpGet patch = new HttpGet(url);
//                patch.addHeader("Accept", "application/scim+json");
            patch.addHeader("Content-Type", "application/scim+json");
            patch.setHeader("Accept", "application/scim+json, application/json");

            HttpResponse resp = client.execute(patch);
            String respBody = resp.getEntity() != null ? EntityUtils.toString(resp.getEntity()) : "";
        System.out.println(respBody);
            int status = resp.getStatusLine().getStatusCode();
            if (status < 200 || status >= 300) {

                throw new RuntimeException("SCIM PATCH failed. status=" + status + " body=" + respBody);
            }
                ObjectMapper mapper = new ObjectMapper();
                ScimUserDTO group = mapper.readValue(respBody, ScimUserDTO.class);

                System.out.println(group.toString());
            return group;
        } catch (Exception e) {
            log.warn("Error calling SCIM Users", e);
            return null;
        }
    }

    public void addUSertoGroup(UserSedeDto userSede,String sGroupId){
        HttpDestination dest = DestinationIAS.create();
        var client = HttpClientAccessor.getHttpClient(dest);
        try{
            String url = "/scim/Groups/" + sGroupId; // baseURL viene del Destination

            String body = """
                    {
                      "schemas": ["urn:ietf:params:scim:api:messages:2.0:PatchOp"],
                      "Operations": [{
                        "op": "add",
                        "path": "members",
                        "value": [{"value": "%s"}]
                      }]
                    }
                    """.formatted(userSede.getUsuarioDTO().getIdentificadorExterno());
            HttpPatch patch = new HttpPatch(url);
//                patch.addHeader("Accept", "application/scim+json");
            patch.addHeader("Content-Type", "application/scim+json");
            patch.setHeader("Accept", "application/scim+json, application/json");
            patch.setEntity(new StringEntity(body, StandardCharsets.UTF_8));

            HttpResponse resp = client.execute(patch);
            String respBody = resp.getEntity() != null ? EntityUtils.toString(resp.getEntity()) : "";

            int status = resp.getStatusLine().getStatusCode();
            if (status < 200 || status >= 300) {
                throw new RuntimeException("SCIM PATCH failed. status=" + status + " body=" + respBody);
            }
//                ObjectMapper mapper = new ObjectMapper();
//                ScimGroupResponse group = mapper.readValue(respBody, ScimGroupResponse.class);
//
//                System.out.println(group.toString());
            //return null;
        } catch (Exception e) {
            log.warn("Error calling SCIM Users", e);
        }
    }
    public void replaceUser(UserSedeDto userSede,String sGroupId){
        HttpDestination dest = DestinationIAS.create();
        var client = HttpClientAccessor.getHttpClient(dest);
        try{
            String url = "/scim/Groups/" + sGroupId; // baseURL viene del Destination
            String body =
                    "{\n" +
                            "  \"schemas\": [\"urn:ietf:params:scim:api:messages:2.0:PatchOp\"],\n" +
                            "  \"Operations\": [\n" +
                            "    {\n" +
                            "      \"op\": \"remove\",\n" +
                            "      \"path\": \"members[value eq \\\"" + userSede.getUsuarioDTO().getIdentificadorExterno() + "\\\"]\"\n" +
                            "    }\n" +
                            "  ]\n" +
                            "}";
            HttpPatch patch = new HttpPatch(url);
//                patch.addHeader("Accept", "application/scim+json");
            patch.addHeader("Content-Type", "application/scim+json");
            patch.setHeader("Accept", "application/scim+json, application/json");
            patch.setEntity(new StringEntity(body, StandardCharsets.UTF_8));

            HttpResponse resp = client.execute(patch);
            String respBody = resp.getEntity() != null ? EntityUtils.toString(resp.getEntity()) : "";

            int status = resp.getStatusLine().getStatusCode();
            if (status < 200 || status >= 300) {
                throw new RuntimeException("SCIM PATCH failed. status=" + status + " body=" + respBody);
            }
//                ObjectMapper mapper = new ObjectMapper();
//                ScimGroupResponse group = mapper.readValue(respBody, ScimGroupResponse.class);
//
//                System.out.println(group.toString());
            //return null;
        } catch (Exception e) {
            log.warn("Error calling SCIM Users", e);
        }
    }

}
