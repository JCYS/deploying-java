/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/crud/imp/EntityCrudServiceImpl.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.crud.imp;

import com.copeinca.apicopeincaprov.integration.LaserFiche.dtos.FileUploadDto;
import com.copeinca.apicopeincaprov.integration.LaserFiche.pojos.deleteFiles.body.IdArchivosECMEnc;
import com.copeinca.apicopeincaprov.integration.LaserFiche.pojos.uploadFiles.response.SubirArchivoECMSoapResponse;
import com.copeinca.apicopeincaprov.integration.LaserFiche.services.interfaces.ILaserFiche;
import com.copeinca.apicopeincaprov.modules.core.mappers.SvAdjuntoMapper;
import com.copeinca.apicopeincaprov.modules.core.mappers.SvProveedorMapper;
import com.copeinca.apicopeincaprov.modules.core.mappers.SvProveedorSedeMapper;
import com.copeinca.apicopeincaprov.modules.core.mappers.SvTipoDocumentoPlanillaMapper;
import com.copeinca.apicopeincaprov.modules.core.models.dto.*;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvAdjuntoEntity;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvProveedorEntity;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvProveedorSedeEntity;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvTipoDocumentoPlanillaEntity;
import com.copeinca.apicopeincaprov.modules.core.repositories.jpa.*;
import com.copeinca.apicopeincaprov.modules.core.service.business.ISvAdjuntoService;
import com.copeinca.apicopeincaprov.modules.core.service.business.ISvProveedorTipoActividadAltoRiesgoService;
import com.copeinca.apicopeincaprov.modules.core.service.business.ISvTipoActividadAltoRiesgoService;
import com.copeinca.apicopeincaprov.modules.core.service.crud.ISvAdjuntoCrudService;
import com.copeinca.apicopeincaprov.modules.core.service.crud.ISvProveedorCrudService;
import com.copeinca.apicopeincaprov.modules.core.service.crud.ISvProveedorSedeCrudService;
import com.copeinca.apicopeincaprov.modules.core.service.crud.ISvProveedorTipoActividadAltoRiesgoCrudService;
import com.copeinca.apicopeincaprov.modules.core.service.query.ISvAdjuntoQueryService;
import com.copeinca.apicopeincaprov.modules.core.service.query.ISvProveedorQueryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
* Implementación del servicio CRUD para SvProveedorEntity
* Proporciona operaciones básicas de creación, actualización y eliminación.
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class SvProveedorCrudServiceImpl implements ISvProveedorCrudService {

    private final ISvProveedorRepository repository;
    private final SvProveedorMapper mapper;
    private final ISvProveedorTipoActividadAltoRiesgoCrudService iSvProveedorTipoActividadAltoRiesgoCrudService;
    private final ISvAdjuntoCrudService iSvAdjuntoCrudService;
    private final ISvAdjuntoQueryService iSvAdjuntoQueryService;
    private final ISvAdjuntoService iSvAdjuntoService;
    private final SvAdjuntoMapper adjuntoMapper;
    private final ISvProveedorQueryService queryService;
    private final ISvProveedorSedeCrudService iSvProveedorSedeCrudService;
    private final SvProveedorSedeMapper svProveedorSedeMapper;
    private final ISvAdjuntoRepository iSvAdjuntoRepository;
    private final ISvProveedorSedeRepository iSvProveedorSedeRepository;

    @Value( "${path.files}" )
    private String sPath;

    @Value( "${path.typeDoc}" )
    private String sTipo;

    private static final DateTimeFormatter DD_MM_YYYY =
            DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private static final int MAX_LEN_CADENA_MD = 10000;


//    private final ISvTipoActividadAltoRiesgoService iSvTipoActividadAltoRiesgoService;
    private final ISvProveedorTipoActividadAltoRiesgoService iSvProveedorTipoActividadAltoRiesgoService;
    private final ILaserFiche laserFiche;

    private final ISvTipoDocumentoPlanillaRepository iSvTipoDocumentoPlanillaRepository;
    private final SvTipoDocumentoPlanillaMapper svTipoDocumentoPlanillaMapper;


    @Override
    public SvProveedorEntity save(SvProveedorDTO dto) {
        SvProveedorEntity entity = mapper.dtoToEntity(dto);
        return repository.save(entity);
    }

    @Override
    public boolean softDeleteById(String id) {
        if (repository.existsById(id)) {
            repository.updateIsDeleted(true, id);
            return true;
        }
        return false;
    }

    @Override
    public boolean hardDeleteById(String id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean restoreById(String id) {
        if (repository.existsById(id)) {
            repository.updateIsDeleted(false, id);
            return true;
        }
        return false;
    }

    @Override
    public List<SvProveedorEntity> saveAll(List<SvProveedorDTO> dtos) {
        List<SvProveedorEntity> entities = dtos.stream().map(mapper::dtoToEntity).collect(Collectors.toList());
        return repository.saveAll(entities);
    }


    @Override
    public SvRegistroProveedorDto saveInfo(SvRegistroProveedorDto infoProv, List<MultipartFile> aFilesSSO, List<MultipartFile> aFilesCA, List<MultipartFile> aFilesAB, List<MultipartFile> aFilesAAR) {
        System.out.println(infoProv.toString());
        Boolean oExist = false;
        if(infoProv.getProveedorInfo().getId() == null){
            oExist = repository.existsByNroDocumentoIdentidad(infoProv.getProveedorInfo().getNroDocumentoIdentidad());
            if(oExist){
                throw new RuntimeException("Proveedor con RUC: "+infoProv.getProveedorInfo().getNroDocumentoIdentidad()+" -> ya se encuentra registrado.");
            }
        }

        //System.out.println("ADJUNTO=> "+iSvAdjuntoRepository.findById("33c9f4ee-c1d0-4d10-961e-f72989eaba10"));
        IdArchivosECMEnc deleteFiles = new IdArchivosECMEnc();

        int anio = LocalDate.now().getYear();
        ArrayList<String> ids = new ArrayList<>();
        List<SvTipoDocumentoPlanillaEntity> aListTipoPlanilla = iSvTipoDocumentoPlanillaRepository.findAll();
        //aList.stream().
        //SvAdjuntoDTO oTmp = iSvAdjuntoQueryService.findById("33c9f4ee-c1d0-4d10-961e-f72989eaba10");
        if(infoProv.getDeletesFiles() != null && infoProv.getDeletesFiles().size() > 0){
            for (SvAdjuntoDTO item :infoProv.getDeletesFiles()){
                ids.add(item.getIdRepositorio());
                log.warn(item.getId());
                SvAdjuntoDTO oAdj = iSvAdjuntoQueryService.findById(item.getId());
                oAdj.setIsDeleted(true);
                System.out.println(iSvAdjuntoCrudService.save(oAdj));
            }
            deleteFiles.setString(ids);
            try{
                laserFiche.deleteFilesLaserFiche(deleteFiles);
            } catch (Exception e) {
                log.warn(e.toString());
            }
        }
        if(infoProv.getDeleteSvTipoActividadAltoRiesgo() != null && infoProv.getDeleteSvTipoActividadAltoRiesgo().size() > 0 ){
            for(SvProveedorTipoActividadAltoRiesgoDTO item: infoProv.getDeleteSvTipoActividadAltoRiesgo()){
                item.setIsActive(false);
                item.setIsDeleted(true);
                iSvProveedorTipoActividadAltoRiesgoCrudService.save(item);
            }
        }
        if(infoProv.getProveedorInfo().getVersion() == null){
            infoProv.getProveedorInfo().setVersion((long) 1);
        }
        SvProveedorEntity result = this.save(infoProv.getProveedorInfo());
        infoProv.setProveedorInfo(mapper.entityToDto(result));
        List<SvProveedorTipoActividadAltoRiesgoDTO> aList = new ArrayList<>();


        if(infoProv.getSedes() != null && infoProv.getSedes().size() > 0){
//            for (SvProveedorSedeDTO item : infoProv.getSedes()){
//                infoProv.getSedes().set()
//            }
            List<SvProveedorSedeEntity> aListSedesProv = iSvProveedorSedeRepository.findAllByProveedorId(infoProv.getProveedorInfo().getId());
            iSvProveedorSedeRepository.saveAll(aListSedesProv.stream()
                    .map(sede -> {
                        sede.setIsActive(false); // ejemplo de propiedad a actualizar
                        sede.setIsDeleted(true);
                        // sede.setOtraPropiedad(valor);
                        return sede;
                    })
                    .collect(Collectors.toList()));
            for (int i = 0; i < infoProv.getSedes().size(); i++) {
                infoProv.getSedes().get(i).setProveedorId(infoProv.getProveedorInfo().getId());
                infoProv.getSedes().get(i).setIsActive(true);
                infoProv.getSedes().get(i).setIsDeleted(false);
                infoProv.getSedes().get(i).setId(null);

                infoProv.getSedes().set(i,svProveedorSedeMapper.entityToDto(iSvProveedorSedeCrudService.save(infoProv.getSedes().get(i))));
            }
        }


        if(result.getIndRealizaActividadAltoRiesgo() != null && infoProv.getSvTipoActividadAltoRiesgoDTOList().size() > 0){
            for(SvTipoActividadAltoRiesgoDTO item: infoProv.getSvTipoActividadAltoRiesgoDTOList()){
                aList.add(SvProveedorTipoActividadAltoRiesgoDTO.builder()
                        .proveedorId(infoProv.getProveedorInfo().getId())
                        .isActive(true)
                        .isDeleted(false)
                        .tipoActividadAltoRiesgoCode(item.getId())
                        .version(1L)
                        .build());
            }
            iSvProveedorTipoActividadAltoRiesgoCrudService.saveAll(aList);
        }


        if(infoProv.getInfoFilesSSO() != null && infoProv.getInfoFilesSSO().size() > 0 ){
            //SSO
            System.out.println("SAVING SSO");
            try{
                List<FileUploadDto> afiles = new ArrayList<>();
                for (int i = 0; i < infoProv.getInfoFilesSSO().size(); i++) {
                    SvAdjuntosIndexDto p = infoProv.getInfoFilesSSO().get(i);
                    afiles.add(FileUploadDto.builder()
                            .pArchivoOrigen(aFilesSSO.get(i))
                            .pNombresDestino(aFilesSSO.get(i).getOriginalFilename())
                            .pRutaArchivoDestino(sPath+anio+"\\")
//                            .pCadenaMD("Matricula;RTA00983")
                            .pCadenaMD(this.generateCadenaSSO(p,infoProv,aListTipoPlanilla.stream()
                                    .filter(t -> p.getInfoAdjunto().getTipoDocumentoPlanillaCode().equals(t.getId()))
                                    .findFirst().get()))
//                            .pNombreTipoDocumento(aListTipoPlanilla.stream()
//                                    .filter(t -> p.getInfoAdjunto().getTipoDocumentoPlanillaCode().equals(t.getId()))
//                                    .findFirst().get())
                            .pNombreTipoDocumento(sTipo)
                            .build());
                }
                SubirArchivoECMSoapResponse ex = laserFiche.uploadFilesMasive(afiles);
                for (int i = 0; i < ex.getBody().getResponse().getResult().getString().size(); i++) {
                    String[] Id = ex.getBody().getResponse().getResult().getString().get(i).split("#");
                    infoProv.getInfoFilesSSO().get(i).setInfoAdjunto(adjuntoMapper.entityToDto(iSvAdjuntoCrudService.save(SvAdjuntoDTO.builder()
                            .nombreArchivo(aFilesSSO.get(i).getOriginalFilename())
                            .idRepositorio(Id[1])
                            .clasification("SSO")
                            .fechaDocumento(infoProv.getInfoFilesSSO().get(i).getInfoAdjunto().getFechaDocumento())
                            .fechaVencimiento(infoProv.getInfoFilesSSO().get(i).getInfoAdjunto().getFechaVencimiento())
                            .ssoComentarioRevision(infoProv.getInfoFilesSSO().get(i).getInfoAdjunto().getSsoComentarioRevision())
                            .proveedorId(infoProv.getProveedorInfo().getId())
                            .tipoDocumentoPlanillaCode(infoProv.getInfoFilesSSO().get(i).getInfoAdjunto().getTipoDocumentoPlanillaCode())
                            .isDeleted(false)
                            .rutaRelativa(sPath+anio+"\\")
                            .motivo(infoProv.getInfoFilesSSO().get(i).getInfoAdjunto().getMotivo())
                            .version(1L)
                            .build())));
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        if(infoProv.getInfoFilesQA() != null && infoProv.getInfoFilesQA().size() > 0 ){
            System.out.println("SAVING CALIDAD");
            try{
                List<FileUploadDto> afiles = new ArrayList<>();
                for (int i = 0; i < infoProv.getInfoFilesQA().size(); i++) {
                    SvAdjuntosIndexDto p = infoProv.getInfoFilesQA().get(i);
                    afiles.add(FileUploadDto.builder()
                            .pArchivoOrigen(aFilesCA.get(i))
                            .pNombresDestino(aFilesCA.get(i).getOriginalFilename())
                            .pRutaArchivoDestino(sPath+anio+"\\")
//                            .pRutaArchivoDestino("COPEINCA\\COPETESTING\\")
//                            .pCadenaMD("Matricula;RTA00983")
                            .pCadenaMD(this.generateCadenaQA(p,infoProv,aListTipoPlanilla.stream()
                                    .filter(t -> p.getInfoAdjunto().getTipoDocumentoPlanillaCode().equals(t.getId()))
                                    .findFirst().get()))
                            .pNombreTipoDocumento(sTipo)
                            .build());
                }
                SubirArchivoECMSoapResponse ex = laserFiche.uploadFilesMasive(afiles);
                for (int i = 0; i < ex.getBody().getResponse().getResult().getString().size(); i++) {
                    String[] Id = ex.getBody().getResponse().getResult().getString().get(i).split("#");
                    infoProv.getInfoFilesQA().get(i).setInfoAdjunto(adjuntoMapper.entityToDto(iSvAdjuntoCrudService.save(SvAdjuntoDTO.builder()
                            .nombreArchivo(aFilesCA.get(i).getOriginalFilename())
                            .idRepositorio(Id[1])
                            .clasification("QA")
                            .fechaDocumento(infoProv.getInfoFilesQA().get(i).getInfoAdjunto().getFechaDocumento())
                            .fechaVencimiento(infoProv.getInfoFilesQA().get(i).getInfoAdjunto().getFechaVencimiento())
                            .ssoComentarioRevision(infoProv.getInfoFilesQA().get(i).getInfoAdjunto().getSsoComentarioRevision())
                            .proveedorId(infoProv.getProveedorInfo().getId())
                            .tipoDocumentoPlanillaCode(infoProv.getInfoFilesQA().get(i).getInfoAdjunto().getTipoDocumentoPlanillaCode())
                            .isDeleted(false)
                            .motivo(infoProv.getInfoFilesQA().get(i).getInfoAdjunto().getMotivo())
                            .rutaRelativa(sPath+anio+"\\")
                            .version(1L)
                            .build())));
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        if(infoProv.getProveedorInfo().getIndRealizaActividadABordo() && infoProv.getInfoFilesAB().size() > 0 ){
            //AB
            try{
                List<FileUploadDto> afiles = new ArrayList<>();
                for (int i = 0; i < infoProv.getInfoFilesAB().size(); i++) {
                    SvAdjuntosIndexDto p = infoProv.getInfoFilesAB().get(i);
                    afiles.add(FileUploadDto.builder()
                            .pArchivoOrigen(aFilesAB.get(i))
                            .pNombresDestino(aFilesAB.get(i).getOriginalFilename())
//                            .pRutaArchivoDestino("COPEINCA\\COPETESTING\\")
                            .pRutaArchivoDestino(sPath+anio+"\\")
//                            .pCadenaMD("Matricula;RTA00983")
                            .pCadenaMD(this.generateCadenaABordo(p,infoProv,aListTipoPlanilla.stream()
                                    .filter(t -> p.getInfoAdjunto().getTipoDocumentoPlanillaCode().equals(t.getId()))
                                    .findFirst().get()))
                            .pNombreTipoDocumento(sTipo)
                            .build());
                }
                SubirArchivoECMSoapResponse ex = laserFiche.uploadFilesMasive(afiles);
                for (int i = 0; i < ex.getBody().getResponse().getResult().getString().size(); i++) {
                    String[] Id = ex.getBody().getResponse().getResult().getString().get(i).split("#");
                    infoProv.getInfoFilesAB().get(i).setInfoAdjunto(adjuntoMapper.entityToDto(iSvAdjuntoCrudService.save(SvAdjuntoDTO.builder()
                            .nombreArchivo(aFilesAB.get(i).getOriginalFilename())
                            .idRepositorio(Id[1])
                            .clasification("AB")
                            .fechaDocumento(infoProv.getInfoFilesAB().get(i).getInfoAdjunto().getFechaDocumento())
                            .fechaVencimiento(infoProv.getInfoFilesAB().get(i).getInfoAdjunto().getFechaVencimiento())
                            .ssoComentarioRevision(infoProv.getInfoFilesAB().get(i).getInfoAdjunto().getSsoComentarioRevision())
                            .proveedorId(infoProv.getProveedorInfo().getId())
                            .tipoDocumentoPlanillaCode(infoProv.getInfoFilesAB().get(i).getInfoAdjunto().getTipoDocumentoPlanillaCode())
                            .isDeleted(false)
                            .motivo(infoProv.getInfoFilesAB().get(i).getInfoAdjunto().getMotivo())
                            .rutaRelativa(sPath+anio+"\\")
                            .version(1L)
                            .build())));
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        if(infoProv.getProveedorInfo().getIndRealizaActividadAltoRiesgo() && infoProv.getInfoFilesAAR().size() > 0 ){
            //AAR
            try{
                List<FileUploadDto> afiles = new ArrayList<>();
                for (int i = 0; i < infoProv.getInfoFilesAAR().size(); i++) {
                    SvAdjuntosIndexDto p = infoProv.getInfoFilesAAR().get(i);
                    afiles.add(FileUploadDto.builder()
                            .pArchivoOrigen(aFilesAAR.get(i))
                            .pNombresDestino(aFilesAAR.get(i).getOriginalFilename())
//                            .pRutaArchivoDestino("COPEINCA\\COPETESTING\\")
                            .pRutaArchivoDestino(sPath+anio+"\\")
//                            .pCadenaMD("Matricula;RTA00983")
                            .pCadenaMD(this.generateCadenaAAR(p,infoProv,aListTipoPlanilla.stream()
                                    .filter(t -> p.getInfoAdjunto().getTipoDocumentoPlanillaCode().equals(t.getId()))
                                    .findFirst().get()))
                            .pNombreTipoDocumento(sTipo)
                            .build());
                }
                SubirArchivoECMSoapResponse ex = laserFiche.uploadFilesMasive(afiles);
                for (int i = 0; i < ex.getBody().getResponse().getResult().getString().size(); i++) {
                    String[] Id = ex.getBody().getResponse().getResult().getString().get(i).split("#");
                    infoProv.getInfoFilesAAR().get(i).setInfoAdjunto(adjuntoMapper.entityToDto(iSvAdjuntoCrudService.save(SvAdjuntoDTO.builder()
                            .nombreArchivo(aFilesAAR.get(i).getOriginalFilename())
                            .idRepositorio(Id[1])
                            .clasification("AAR")
                            .fechaDocumento(infoProv.getInfoFilesAAR().get(i).getInfoAdjunto().getFechaDocumento())
                            .fechaVencimiento(infoProv.getInfoFilesAAR().get(i).getInfoAdjunto().getFechaVencimiento())
                            .ssoComentarioRevision(infoProv.getInfoFilesAAR().get(i).getInfoAdjunto().getSsoComentarioRevision())
                            .proveedorId(infoProv.getProveedorInfo().getId())
                            .tipoDocumentoPlanillaCode(infoProv.getInfoFilesAAR().get(i).getInfoAdjunto().getTipoDocumentoPlanillaCode())
                            .isDeleted(false)
                            .motivo(infoProv.getInfoFilesAAR().get(i).getInfoAdjunto().getMotivo())
                            .rutaRelativa(sPath+anio+"\\")
                            .version(1L)
                            .build())));
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        return infoProv;
    }
    public static String formatDdMmYyyy(LocalDate date) {
        if (date == null) return "";
        return date.format(DD_MM_YYYY);
    }
    public String generateCadenaSSO(SvAdjuntosIndexDto indexDto,SvRegistroProveedorDto infoProv,SvTipoDocumentoPlanillaEntity template){
        Map<String, Object> md = new java.util.LinkedHashMap<>(); // mantiene el orden
        md.put("RUC", infoProv.getProveedorInfo().getNroDocumentoIdentidad());
        md.put("Razon Social Proveedor", infoProv.getProveedorInfo().getNombreFiscal());
        md.put("Fecha de documento", indexDto.getInfoAdjunto().getFechaDocumento().toString());
        md.put("Fecha caduca1", indexDto.getInfoAdjunto().getFechaVencimiento() != null
                ? indexDto.getInfoAdjunto().getFechaVencimiento().toString()
                : ""
        );
        md.put("Ambito Accesos Sedes", "SSO" );
        md.put("Sede LF", "LIMA" );
        md.put("Sede Accesos Sedes", "LIMA" );
        md.put("Tipo de documento Empresa SSO", template.getDescription());
        md.put("Observaciones", indexDto.getInfoAdjunto().getMotivo());
        System.out.println(MetadataCadenaMdBuilder.buildBounded(md, MAX_LEN_CADENA_MD).length());
        return MetadataCadenaMdBuilder.buildBounded(md, MAX_LEN_CADENA_MD);
    }
    public String generateCadenaAAR(SvAdjuntosIndexDto indexDto,SvRegistroProveedorDto infoProv,SvTipoDocumentoPlanillaEntity template){
        Map<String, Object> md = new java.util.LinkedHashMap<>(); // mantiene el orden
        md.put("RUC", infoProv.getProveedorInfo().getNroDocumentoIdentidad());
        md.put("Razon Social Proveedor", infoProv.getProveedorInfo().getNombreFiscal());
        md.put("Fecha de documento", indexDto.getInfoAdjunto().getFechaDocumento().toString());
        md.put("Fecha caduca1", indexDto.getInfoAdjunto().getFechaVencimiento() != null
                ? indexDto.getInfoAdjunto().getFechaVencimiento().toString()
                : ""
        );
        md.put("Observaciones", indexDto.getInfoAdjunto().getMotivo());
        md.put("Ambito Accesos Sedes", "SSO" );
        md.put("Sede LF", "LIMA" );
        md.put("Sede Accesos Sedes", "LIMA" );
        md.put("Es Actividad de alto riesgo","SI");
        md.put("Tipo Actividad de alto riesgo",template.getDependencia());
        md.put("Sustento Actividad de alto riesgo Empresa",template.getDescription());
        System.out.println(MetadataCadenaMdBuilder.buildBounded(md, MAX_LEN_CADENA_MD).length());
        return MetadataCadenaMdBuilder.buildBounded(md, MAX_LEN_CADENA_MD);
    }
    public String generateCadenaABordo(SvAdjuntosIndexDto indexDto,SvRegistroProveedorDto infoProv,SvTipoDocumentoPlanillaEntity template){
        Map<String, Object> md = new java.util.LinkedHashMap<>(); // mantiene el orden
        md.put("RUC", infoProv.getProveedorInfo().getNroDocumentoIdentidad());
        md.put("Razon Social Proveedor", infoProv.getProveedorInfo().getNombreFiscal());
        md.put("Fecha de documento", indexDto.getInfoAdjunto().getFechaDocumento().toString());
        md.put("Fecha caduca1", indexDto.getInfoAdjunto().getFechaVencimiento() != null
                ? indexDto.getInfoAdjunto().getFechaVencimiento().toString()
                : ""
        );
        md.put("Observaciones", indexDto.getInfoAdjunto().getMotivo());
        md.put("Ambito Accesos Sedes", "SSO" );
        md.put("Sede LF", "LIMA" );
        md.put("Sede Accesos Sedes", "LIMA" );
        md.put("Es Actividad a bordo","SI");
        md.put("Sustento Actividad a bordo Empresa",template.getDescription());
        System.out.println(MetadataCadenaMdBuilder.buildBounded(md, MAX_LEN_CADENA_MD).length());
        return MetadataCadenaMdBuilder.buildBounded(md, MAX_LEN_CADENA_MD);
    }
    public String generateCadenaQA(SvAdjuntosIndexDto indexDto,SvRegistroProveedorDto infoProv,SvTipoDocumentoPlanillaEntity template){
        Map<String, Object> md = new java.util.LinkedHashMap<>(); // mantiene el orden
        md.put("RUC", infoProv.getProveedorInfo().getNroDocumentoIdentidad());
        md.put("Razon Social Proveedor", infoProv.getProveedorInfo().getNombreFiscal());
        md.put("Fecha de documento", indexDto.getInfoAdjunto().getFechaDocumento().toString());
        md.put("Fecha caduca1", indexDto.getInfoAdjunto().getFechaVencimiento() != null
                ? indexDto.getInfoAdjunto().getFechaVencimiento().toString()
                : ""
        );
        md.put("Observaciones", indexDto.getInfoAdjunto().getMotivo());
        md.put("Ambito Accesos Sedes", "Calidad Ambiental" );
        md.put("Sede LF", "LIMA" );
        md.put("Sede Accesos Sedes", "LIMA" );
//        md.put("Es Actividad a bordo","SI");
        md.put("Tipo de documento Empresa CA ",template.getDescription());
        //return truncate(MetadataCadenaMdBuilder.buildBounded(md), MAX_LEN_CADENA_MD);
        System.out.println(MetadataCadenaMdBuilder.buildBounded(md, MAX_LEN_CADENA_MD).length());
        return MetadataCadenaMdBuilder.buildBounded(md, MAX_LEN_CADENA_MD);
    }
    private static String truncate(String s, int maxLen) {
        if (s == null) return "";
        if (maxLen < 0) return "";
        return (s.length() <= maxLen) ? s : s.substring(0, maxLen);
    }
    public final class MetadataCadenaMdBuilder {

        private static final char DATA_SEP = ';';
        private static final char FIELD_SEP = '|';

        private MetadataCadenaMdBuilder() {}

        /**
         * Construye pCadenaMD como: Campo;Valor|Campo2;Valor2  (SIN '|' final)
         * - Respeta maxLen
         * - Nunca deja un segmento incompleto
         * - Si no entra completo, recorta SOLO el valor del último segmento
         */
        public static String buildBounded(Map<String, ?> md, int maxLen) {
            if (md == null || md.isEmpty() || maxLen <= 0) return "";

            StringBuilder sb = new StringBuilder(Math.min(256, maxLen));
            boolean first = true;

            for (var entry : md.entrySet()) {
                String campo = requireValidCampo(entry.getKey());
                String valor = requireValidValor(entry.getValue());

                // Segmento base (sin separador de campo al final)
                // Campo;Valor
                String segmentFull = campo + DATA_SEP + valor;

                // Si no es el primero, hay que anteponer '|'
                String prefix = first ? "" : String.valueOf(FIELD_SEP);
                int remaining = maxLen - sb.length();
                if (remaining <= 0) break;

                // Caso 1: entra completo (con su prefijo si aplica)
                if ((prefix.length() + segmentFull.length()) <= remaining) {
                    sb.append(prefix).append(segmentFull);
                    first = false;
                    continue;
                }

                // Caso 2: no entra completo -> intentamos recortar SOLO el valor
                // Necesitamos como mínimo: prefix + campo + ';'   (valor puede quedar vacío)
                int minLen = prefix.length() + campo.length() + 1; // +1 por ';'
                if (minLen > remaining) {
                    break; // no se puede agregar ni siquiera "Campo;"
                }

                int spaceForValor = remaining - minLen; // lo que queda para el valor
                String valorRecortado = (spaceForValor >= valor.length())
                        ? valor
                        : valor.substring(0, spaceForValor);

                sb.append(prefix).append(campo).append(DATA_SEP).append(valorRecortado);
                // Llegamos al límite, no seguimos agregando más campos
                break;
            }

            validateStructureNoTrailingPipe(sb.toString());
            return sb.toString();
        }

        private static String requireValidCampo(String campo) {
            if (campo == null || campo.isBlank())
                throw new IllegalArgumentException("Campo (Template) es obligatorio.");

            String c = campo.trim();
            if (c.indexOf(DATA_SEP) >= 0 || c.indexOf(FIELD_SEP) >= 0)
                throw new IllegalArgumentException("Campo inválido (contiene ';' o '|'): " + c);

            return c;
        }

        private static String requireValidValor(Object valor) {
            String v = (valor == null) ? "" : String.valueOf(valor).trim();

            // Recomendado: rechazar separadores para no romper el parseo del backend
            if (v.indexOf(DATA_SEP) >= 0 || v.indexOf(FIELD_SEP) >= 0)
                throw new IllegalArgumentException("Valor inválido (contiene ';' o '|'): " + v);

            return v;
        }

        private static void validateStructureNoTrailingPipe(String cadena) {
            if (cadena == null || cadena.isEmpty()) return;

            // NO debe terminar con '|'
            if (cadena.charAt(cadena.length() - 1) == FIELD_SEP) {
                throw new IllegalStateException("Cadena inválida: termina con '|'");
            }

            // Cada segmento debe contener ';' y tener campo no vacío
            String[] segments = cadena.split("\\|", -1);
            for (String seg : segments) {
                if (seg.isEmpty())
                    throw new IllegalStateException("Cadena inválida: segmento vacío entre '|'");
                int idx = seg.indexOf(DATA_SEP);
                if (idx <= 0)
                    throw new IllegalStateException("Cadena inválida: falta ';' en segmento: " + seg);
                // campo = seg.substring(0, idx) (no vacío), valor puede ser vacío
            }
        }
    }
    @Override
    public SvRegistroProveedorDto getInfoSupplier(String sId) {

        SvRegistroProveedorDto result = new SvRegistroProveedorDto();
        result.setProveedorInfo(queryService.findById(sId));
        List<SvAdjuntosIndexDto> aListAdjunto = iSvAdjuntoService.getAdjuntosBYProvId(sId);
        result.setInfoFilesSSO(aListAdjunto.stream()
                .filter(dto ->
                        dto.getInfoAdjunto() != null
                                && "SSO".equals(dto.getInfoAdjunto().getClasification())
                                && Boolean.FALSE.equals(dto.getInfoAdjunto().getIsDeleted())
                )
                .toList());
        result.setInfoFilesQA(aListAdjunto.stream()
                .filter(dto ->
                        dto.getInfoAdjunto() != null
                                && "QA".equals(dto.getInfoAdjunto().getClasification())
                                && Boolean.FALSE.equals(dto.getInfoAdjunto().getIsDeleted())
                )
                .toList());// Java 16+);
        result.setInfoFilesAB(aListAdjunto.stream()
                .filter(dto ->
                        dto.getInfoAdjunto() != null
                                && "AB".equals(dto.getInfoAdjunto().getClasification())
                                && Boolean.FALSE.equals(dto.getInfoAdjunto().getIsDeleted())
                )
                .toList());// Java 16+);
        result.setInfoFilesAAR(aListAdjunto.stream()
                .filter(dto ->
                        dto.getInfoAdjunto() != null
                                && "AAR".equals(dto.getInfoAdjunto().getClasification())
                                && Boolean.FALSE.equals(dto.getInfoAdjunto().getIsDeleted())
                )
                .toList());// Java 16+);
        result.setSvProveedorTipoActividadAltoRiesgoDTOList(
                iSvProveedorTipoActividadAltoRiesgoService.loadActivities(sId)
                        .stream()
                        .filter(a -> !a.getIsDeleted())
//                        .filter(a -> a.getIsActive().e)
                        .toList() // Java 16+
        );

//        List<SvUsuarioRolSedeDTO> dtoList = aList.stream()
//                .map(svUsuarioRolSedeMapper::entityToDto)
//                .filter(a -> !"controlprov_sede".equals(a.getRolCode()))
//                .filter(a -> Boolean.FALSE.equals(a.getIsDeleted()))
//                .toList();

        List<SvProveedorSedeEntity> aList = iSvProveedorSedeRepository.findAllByProveedorId(result.getProveedorInfo().getId());
        result.setSedes(aList.stream()
                .filter(a -> Boolean.FALSE.equals(a.getIsDeleted()))
                .map(svProveedorSedeMapper::entityToDto)
                .toList());
        //result.setSvProveedorTipoActividadAltoRiesgoDTOList(iSvProveedorTipoActividadAltoRiesgoService.loadActivities(sId));
        return result;
    }
}
