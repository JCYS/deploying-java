package com.copeinca.apicopeincaprov.security.controllers;

import com.copeinca.apicopeincaprov.global.dtos.response.CustomMessageView;
import com.copeinca.apicopeincaprov.integration.CloudIdentityService.pojos.ScimUserDTO;
import com.copeinca.apicopeincaprov.integration.CloudIdentityService.service.interfaces.IASClientService;
import com.copeinca.apicopeincaprov.modules.core.mappers.SvUsuarioMapper;
import com.copeinca.apicopeincaprov.modules.core.mappers.SvUsuarioRolSedeMapper;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvSedeDTO;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvTipoActividadAltoRiesgoDTO;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvUsuarioRolSedeDTO;
import com.copeinca.apicopeincaprov.modules.core.models.entities.*;
import com.copeinca.apicopeincaprov.modules.core.repositories.jpa.ISvProveedorSedeRepository;
import com.copeinca.apicopeincaprov.modules.core.repositories.jpa.ISvSedeRepository;
import com.copeinca.apicopeincaprov.modules.core.repositories.jpa.ISvUsuarioRepository;
import com.copeinca.apicopeincaprov.modules.core.repositories.jpa.ISvUsuarioRolSedeRepository;
import com.copeinca.apicopeincaprov.modules.core.service.business.ISvSupplierUserService;
import com.copeinca.apicopeincaprov.modules.core.service.business.ISvUsuarioService;
import com.copeinca.apicopeincaprov.modules.core.service.business.imp.SvProveedorService;
import com.copeinca.apicopeincaprov.security.dtos.JwtInfoDTO;
import com.copeinca.apicopeincaprov.security.dtos.UserSedeDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


@Log4j2
@RestController
@RequestMapping(path = "/security")
@RequiredArgsConstructor
public class UserInfoController {

    @Value( "${com.innovax.isLocal}" )
    Boolean isLocal;

    private final SvProveedorService svProveedorService;

    private final ISvUsuarioService iSvUsuarioService;

    private final IASClientService iasClientService;

    private final ISvUsuarioRolSedeRepository iSvUsuarioRolSedeRepository;
    private final SvUsuarioRolSedeMapper svUsuarioRolSedeMapper;

    private final ISvUsuarioRepository iSvUsuarioRepository;
    private final SvUsuarioMapper svUsuarioMapper;

    private final ISvSedeRepository iSvSedeRepository;

    private final ISvProveedorSedeRepository iSvProveedorSedeRepository;

    private final ISvSupplierUserService isvSupplierUserService;

    @GetMapping("/jwtInfo")
    public ResponseEntity<JwtInfoDTO> readGroups(@AuthenticationPrincipal Jwt jwt) throws Exception{
        JwtInfoDTO dto = new JwtInfoDTO();
        List<SvSupplierUserEntity> supplierUser = isvSupplierUserService.findAll();
        if( isLocal ){

            dto.setSub("test@user@gmail.com");
            dto.setUserName("testing");
            dto.setEmail("jescuderoo@csticorp.biz");
            dto.setFamilyName("family_name");
            //dto.setProveedor(svProveedorService.getProv("info@myg-consulting.com"));
//            dto.setProveedor(svProveedorService.getProv(dto.getEmail()));
            Optional<SvSupplierUserEntity> tmp = supplierUser.stream()
                    .filter(s->dto.getEmail().equals(s.getEmail()))
                    .findFirst();

//            svProveedorService.getProvByRuc(tmp.get(0).getRuc());
//            dto.setProveedor(svProveedorService.getProv(dto.getEmail()));
            if(tmp.isPresent()){
                dto.setProveedor(svProveedorService.getProvByRuc(tmp.get().getRuc()));
            }else{
                ScimUserDTO userDto = iasClientService.searchUSer("17b56dd6-d50d-4c49-862d-f65b19dd8702");
                dto.setProveedor(svProveedorService.getProvByRuc(userDto.getUserName()));
            }
//            dto.setProveedor(svProveedorService.getProvByRuc(tmp.get().getRuc()));
            List<SvUsuarioEntity> aListUser= iSvUsuarioRepository.findAllByEmail(dto.getEmail());
            if(aListUser.size() > 0){
                List<SvUsuarioRolSedeEntity> aList = iSvUsuarioRolSedeRepository.findAllByUsuarioId(aListUser.get(0).getId());
                List<SvUsuarioRolSedeDTO> dtoList = aList.stream()
                        .map(svUsuarioRolSedeMapper::entityToDto)
                        .filter(a -> !"controlprov_sede".equals(a.getRolCode()))
                        .filter(a -> Boolean.FALSE.equals(a.getIsDeleted()))
                        .toList();
                dto.setListSvUsuarioRolSedeDto(dtoList);
                ArrayList<SvSedeDTO> sedes = new ArrayList<>();
                for(SvUsuarioRolSedeEntity item: aList.stream()
                        .filter(a-> a.getRolCode().equals("controlprov_sede"))
                        .filter(a -> !a.getIsDeleted())
                        .toList()){
                    Optional<SvSedeEntity> oSede = iSvSedeRepository.findById(item.getSedeId());
                    if(oSede.isPresent()){
                        sedes.add(SvSedeDTO.builder()
                                .id(oSede.get().getId())
                                .name(oSede.get().getName())
                                .build());
                    }

                }
                dto.setListSedes(sedes);
            }
            if(dto.getProveedor() != null){
                List<SvProveedorSedeEntity> aList = iSvProveedorSedeRepository.findAllByProveedorId(dto.getProveedor().getId());
                //List<SvUsuarioRolSedeDTO> aListRol = new ArrayList<>();
                List<SvSedeDTO> aListRol = new ArrayList<>();
                for (SvProveedorSedeEntity item: aList.stream()
                        .filter(a -> Boolean.FALSE.equals(a.getIsDeleted()))
                        .toList()){
                    aListRol.add(SvSedeDTO.builder()
                                    .id(item.getSedeId())
                            .build());
                }
                dto.setListSedes(aListRol);
            }

        }else {
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

            Optional<SvSupplierUserEntity> tmp = supplierUser.stream()
                    .filter(s->dto.getEmail().equals(s.getEmail()))
                    .findFirst();

//            svProveedorService.getProvByRuc(tmp.get(0).getRuc());
//            dto.setProveedor(svProveedorService.getProv(dto.getEmail()));
            if(tmp.isPresent()){
                dto.setProveedor(svProveedorService.getProvByRuc(tmp.get().getRuc()));
            }else{
//                ScimUserDTO UserDto = iasClientService.searchUSer("17b56dd6-d50d-4c49-862d-f65b19dd8702");
//                dto.setProveedor(svProveedorService.getProv(dto.getEmail()));
                ScimUserDTO userDto = iasClientService.searchUSer(jwt.getClaimAsString("user_uuid"));
                dto.setProveedor(svProveedorService.getProvByRuc(userDto.getUserName()));
            }
            List<SvUsuarioEntity> aListUser= iSvUsuarioRepository.findAllByEmail(dto.getEmail());
            if(aListUser.size() > 0){
                List<SvUsuarioRolSedeEntity> aList = iSvUsuarioRolSedeRepository.findAllByUsuarioId(aListUser.get(0).getId());
//                List<SvUsuarioRolSedeDTO> dtoList = aList.stream()
//                        .map(svUsuarioRolSedeMapper::entityToDto)
////                        .filter(a -> !"controlprov_sede".equals(a.getRolCode()))
//                        .filter(a -> Boolean.FALSE.equals(a.getIsDeleted()))
//                        .toList();

                List<SvUsuarioRolSedeDTO> dtoList = new ArrayList<>(
                        aList.stream()
                                .map(svUsuarioRolSedeMapper::entityToDto)
                                .filter(a -> Boolean.FALSE.equals(a.getIsDeleted()))
                                .collect(Collectors.toMap(
                                        SvUsuarioRolSedeDTO::getRolCode, // clave única
                                        Function.identity(),               // valor
                                        (a1, a2) -> a1                     // si se repite, conserva el primero
                                ))
                                .values()
                );
                dto.setListSvUsuarioRolSedeDto(dtoList);

                List<SvSedeDTO> sedes = new ArrayList<>(
                        aList.stream()
                                .filter(a -> !a.getIsDeleted())
                                .map(item -> iSvSedeRepository.findById(item.getSedeId()))
                                .filter(Optional::isPresent)
                                .map(Optional::get)
                                .map(sede -> SvSedeDTO.builder()
                                        .id(sede.getId())
                                        .name(sede.getName())
                                        .build())
                                .collect(Collectors.toMap(
                                        SvSedeDTO::getName,      // clave única → DISTINCT por id
                                        Function.identity(),
                                        (s1, s2) -> s1,        // conserva el primero
                                        LinkedHashMap::new     // mantiene orden
                                ))
                                .values()
                );
                dto.setListSedes(sedes);
            }
            if(dto.getProveedor() != null){
                List<SvProveedorSedeEntity> aList = iSvProveedorSedeRepository.findAllByProveedorId(dto.getProveedor().getId());
                //List<SvUsuarioRolSedeDTO> aListRol = new ArrayList<>();
                List<SvSedeDTO> aListRol = new ArrayList<>();
                for (SvProveedorSedeEntity item: aList.stream()
                        .filter(a -> Boolean.FALSE.equals(a.getIsDeleted()))
                        .toList()){
                    aListRol.add(SvSedeDTO.builder()
                            .id(item.getSedeId())
                            .build());
                }
                dto.setListSedes(aListRol);
            }

        }
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/userSede")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UserSedeDto> saveSedeUser(@RequestBody UserSedeDto userSedeDto) throws Exception {
//        iSvUsuarioService.updateSede(userSedeDto);
        iasClientService.updateUserWithGroup(userSedeDto);
        return ResponseEntity.ok(userSedeDto);
    }

}
