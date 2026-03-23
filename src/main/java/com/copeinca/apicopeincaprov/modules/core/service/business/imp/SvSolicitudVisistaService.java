/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/business/imp/EntityService.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.business.imp;

import com.copeinca.apicopeincaprov.commons.models.FilterOperation;
import com.copeinca.apicopeincaprov.commons.models.OperatorEnum;
import com.copeinca.apicopeincaprov.global.dtos.response.PagedRequest;
import com.copeinca.apicopeincaprov.global.dtos.response.PagedResult;
import com.copeinca.apicopeincaprov.global.util.DateTimeUtil;
import com.copeinca.apicopeincaprov.integration.LaserFiche.dtos.FileUploadDto;
import com.copeinca.apicopeincaprov.integration.LaserFiche.pojos.deleteFiles.body.IdArchivosECMEnc;
import com.copeinca.apicopeincaprov.integration.LaserFiche.pojos.uploadFiles.response.SubirArchivoECMSoapResponse;
import com.copeinca.apicopeincaprov.integration.LaserFiche.services.interfaces.ILaserFiche;
import com.copeinca.apicopeincaprov.integration.Mendix.dtos.imput.*;
import com.copeinca.apicopeincaprov.integration.Mendix.dtos.response.Documento;
import com.copeinca.apicopeincaprov.integration.Mendix.dtos.response.ResultadoWSActualiza;
import com.copeinca.apicopeincaprov.integration.Mendix.pojos.EquiposPojo;
import com.copeinca.apicopeincaprov.integration.Mendix.service.interfaces.ClientService;
import com.copeinca.apicopeincaprov.modules.core.mappers.*;
import com.copeinca.apicopeincaprov.modules.core.models.dto.*;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.ViewAdjuntoReportFilter;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.ViewTrabajadorReportFilter;
import com.copeinca.apicopeincaprov.modules.core.models.entities.*;
import com.copeinca.apicopeincaprov.modules.core.models.enums.EstadoCalidadAmbientalEnum;
import com.copeinca.apicopeincaprov.modules.core.models.enums.EstadoSSOEnum;
import com.copeinca.apicopeincaprov.modules.core.models.enums.EstadoSolicitudVisitaEnum;
import com.copeinca.apicopeincaprov.modules.core.repositories.jpa.*;
import com.copeinca.apicopeincaprov.modules.core.repositories.jpa.ISvSolicitudVisistaRepository;
import com.copeinca.apicopeincaprov.modules.core.repositories.jpa.ISvTipoDocumentoPlanillaRepository;
import com.copeinca.apicopeincaprov.modules.core.service.business.ISvAdjuntoService;
import com.copeinca.apicopeincaprov.modules.core.service.business.ISvSolicitudVisitaService;
import com.copeinca.apicopeincaprov.modules.core.service.crud.*;
import com.copeinca.apicopeincaprov.modules.core.service.crud.imp.SvProveedorCrudServiceImpl;
import com.copeinca.apicopeincaprov.modules.core.service.query.*;
import com.copeinca.apicopeincaprov.modules.notificacion.service.INotificationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
* Implementación del service para SvSolicitudVisistaEntity
* Extiende de las implementaciones especializadas para reutilizar funcionalidad
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class SvSolicitudVisistaService implements ISvSolicitudVisitaService {
    //Services
    private final IViewTrabajadorReportQueryService iViewTrabajadorReportQueryService;
    private final ISvAdjuntoService iSvAdjuntoService;
    private final INotificationService notificationService;
    private final ISvAdjuntoCrudService iSvAdjuntoCrudService;
    private final ISvAdjuntoQueryService iSvAdjuntoQueryService;
    private final ISvSolicitudVisistaCrudService iSvSolicitudVisistaCrudService;
    private final ISvSolicitudVisistaQueryService iSvSolicitudVisistaQueryService;
    private final ISvProveedorQueryService iSvProveedorQueryService;
    private final ISvSolicitudVisitaDetalleCrudService iSvSolicitudVisitaDetalleCrudService;
    private final IViewSolicitudVisitaReportQueryService iViewSolicitudVisitaReportQueryService;
    private final IViewAdjuntoReportQueryService iViewAdjuntoReportQueryService;
    private final ISvSolicitudVisitaZonaCrudService iSvSolicitudVisitaZonaCrudService;
    private final ISvSolicitudVisitaAreaAutorizadoraCrudService iSvSolicitudVisitaAreaAutorizadoraCrudService;
//    private final IViewTrabajadorReportQueryService iViewTrabajadorReportQueryService;

    private final ISvSolicitudVisitaEventoCrudService iSvSolicitudVisitaEventoCrudService;
    private final ISvSolicitudVisitaHistorialCrudService iSvSolicitudVisitaHistorialCrudService;
    private final ISvSolicitudVisitaMaterialCrudService iSvSolicitudVisitaMaterialCrudService;
    private final ISvSolicitudVisitaEquipoCrudService iSvSolicitudVisitaEquipoCrudService;
    private final ISvSolicitudVisitaTipoActividadAltoRiesgoCrudService iSvSolicitudVisitaTipoActividadAltoRiesgoCrudService;
//    private final ClientService clientService;
    //Mapper
    private final SvAdjuntoMapper adjuntoMapper;
    private final SvSolicitudVisistaMapper svSolicitudVisistaMapper;
    private final SvSolicitudVisitaMaterialMapper svSolicitudVisitaMaterialMapper;
    private final SvSolicitudVisitaEquipoMapper svSolicitudVisitaEquipoMapper;
    private final SvSolicitudVisitaTipoActividadAltoRiesgoMapper svSolicitudVisitaTipoActividadAltoRiesgoMapper;
//    private final SvSolicitudVisitaAreaAutorizadoraMapper svSolicitudVisitaAreaAutorizadoraMapper;
//    private final SvSolicitudVisitaZonaMapper svSolicitudVisitaZonaMapper;
    private final ILaserFiche laserFiche;

    //Repository
    private final ISvSolicitudVisistaRepository iSvSolicitudVisistaRepository;
    private final ISvTipoDocumentoPlanillaRepository iSvTipoDocumentoPlanillaRepository;
    //Mapper
    private final SvSolicitudVisitaAreaAutorizadoraMapper svSolicitudVisitaAreaAutorizadoraMapper;
    private final SvSolicitudVisitaZonaMapper svSolicitudVisitaZonaMapper;
    private final SvSolicitudVisitaDetalleMapper svSolicitudVisitaDetalleMapper;
//    private final SvSolicitudVisistaMapper svSolicitudVisistaMapper;

    //Repositories
    private final ISvSolicitudVisitaAreaAutorizadoraRepository iSvSolicitudVisitaAreaAutorizadoraRepository;
    private final ISvSolicitudVisitaMaterialRepository iSvSolicitudVisitaMaterialRepository;
    private final ISvSolicitudVisitaEquipoRepository iSvSolicitudVisitaEquipoRepository;
    private final ISvSolicitudVisitaTipoActividadAltoRiesgoRepository iSvSolicitudVisitaTipoActividadAltoRiesgoRepository;
    private final ISvSolicitudVisitaZonaRepository iSvSolicitudVisitaZonaRepository;
    private final ISvAdjuntoRepository iSvAdjuntoRepository;
    private final ISvSolicitudVisitaDetalleRepository iSvSolicitudVisitaDetalleRepository;

    private final ISvSolicitudVisitaHistorialRepository iSvSolicitudVisitaHistorialRepository;


//    private final ClientService clientService;

    @Value( "${path.files}" )
    private String sPath;

    @Value( "${path.typeDoc}" )
    private String sTipo;

    private static final DateTimeFormatter DD_MM_YYYY =
            DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private static final int MAX_LEN_CADENA_MD = 10000;

    public String generateCadenaSSO(SvAdjuntosIndexDto indexDto,SvProveedorCitaRegistroDTO infoProv,SvTipoDocumentoPlanillaEntity template){
        Map<String, Object> md = new java.util.LinkedHashMap<>(); // mantiene el orden
        md.put("RUC", infoProv.getProv().getNroDocumentoIdentidad());
        md.put("Razon Social Proveedor", infoProv.getProv().getNombreFiscal());
        md.put("Fecha de documento", indexDto.getInfoAdjunto().getFechaDocumento().toString());
//        md.put("Fecha caduca1", indexDto.getInfoAdjunto().getFechaVencimiento().toString() );
        md.put("Fecha caduca1", indexDto.getInfoAdjunto().getFechaVencimiento() != null
                ? indexDto.getInfoAdjunto().getFechaVencimiento().toString()
                : ""
        );
        md.put("Sede LF ", "LIMA" );
        md.put("Observaciones", indexDto.getInfoAdjunto().getMotivo());
        md.put("Ambito Accesos Sedes", "SSO" );
        md.put("Tipo de documento Servicio SSO", template.getCampoPlantillaLf());
        System.out.println(SvProveedorCrudServiceImpl.MetadataCadenaMdBuilder.buildBounded(md, MAX_LEN_CADENA_MD).length());
        return MetadataCadenaMdBuilder.buildBounded(md, MAX_LEN_CADENA_MD);
    }
    public String generateCadenaAAR(SvAdjuntosIndexDto indexDto,SvProveedorCitaRegistroDTO infoProv,SvTipoDocumentoPlanillaEntity template){
        Map<String, Object> md = new java.util.LinkedHashMap<>(); // mantiene el orden
        md.put("RUC", infoProv.getProv().getNroDocumentoIdentidad());
        md.put("Razon Social Proveedor", infoProv.getProv().getNombreFiscal());
        md.put("Fecha de documento", indexDto.getInfoAdjunto().getFechaDocumento().toString());
//        md.put("Fecha caduca1", indexDto.getInfoAdjunto().getFechaVencimiento().toString() );
        md.put("Fecha caduca1", indexDto.getInfoAdjunto().getFechaVencimiento() != null
                ? indexDto.getInfoAdjunto().getFechaVencimiento().toString()
                : ""
        );
        md.put("Ambito Accesos Sedes", "SSO" );
        md.put("Observaciones", indexDto.getInfoAdjunto().getMotivo());
        md.put("Sede LF ", "LIMA" );
        md.put("Es Actividad de alto riesgo","SI");
        md.put("Tipo Actividad de alto riesgo",template.getDependencia());
        md.put("Sustento Actividad de alto riesgo Servicio",template.getDescription());
        System.out.println(SvProveedorCrudServiceImpl.MetadataCadenaMdBuilder.buildBounded(md, MAX_LEN_CADENA_MD).length());
        return MetadataCadenaMdBuilder.buildBounded(md, MAX_LEN_CADENA_MD);
    }
    public String generateCadenaABordo(SvAdjuntosIndexDto indexDto,SvProveedorCitaRegistroDTO infoProv,SvTipoDocumentoPlanillaEntity template){
        Map<String, Object> md = new java.util.LinkedHashMap<>(); // mantiene el orden
        md.put("RUC", infoProv.getProv().getNroDocumentoIdentidad());
        md.put("Razon Social Proveedor", infoProv.getProv().getNombreFiscal());
        md.put("Fecha de documento", indexDto.getInfoAdjunto().getFechaDocumento().toString());
//        md.put("Fecha caduca1", indexDto.getInfoAdjunto().getFechaVencimiento().toString() );
        md.put("Fecha caduca1", indexDto.getInfoAdjunto().getFechaVencimiento() != null
                ? indexDto.getInfoAdjunto().getFechaVencimiento().toString()
                : ""
        );
        md.put("Ambito Accesos Sedes", "SSO" );
        md.put("Observaciones", indexDto.getInfoAdjunto().getMotivo());
        md.put("Sede LF ", "LIMA" );
        md.put("Es Actividad a bordo","SI");
        md.put("Sustento Actividad a bordo Tbjd",template.getDescription());
        System.out.println(SvProveedorCrudServiceImpl.MetadataCadenaMdBuilder.buildBounded(md, MAX_LEN_CADENA_MD).length());
        return MetadataCadenaMdBuilder.buildBounded(md, MAX_LEN_CADENA_MD);
    }
    public String generateCadenaQA(SvAdjuntosIndexDto indexDto,SvProveedorCitaRegistroDTO infoProv,SvTipoDocumentoPlanillaEntity template){
        Map<String, Object> md = new java.util.LinkedHashMap<>(); // mantiene el orden
        md.put("RUC", infoProv.getProv().getNroDocumentoIdentidad());
        md.put("Razon Social Proveedor", infoProv.getProv().getNombreFiscal());
        md.put("Fecha de documento", indexDto.getInfoAdjunto().getFechaDocumento().toString());
//        md.put("Fecha caduca1", indexDto.getInfoAdjunto().getFechaVencimiento().toString() );
        md.put("Fecha caduca1", indexDto.getInfoAdjunto().getFechaVencimiento() != null
                ? indexDto.getInfoAdjunto().getFechaVencimiento().toString()
                : ""
        );
        md.put("Ambito Accesos Sedes", "Calidad Ambiental" );
        md.put("Observaciones", indexDto.getInfoAdjunto().getMotivo());
        md.put("Sede LF ", "LIMA" );
//        md.put("Es Actividad a bordo","SI");
        md.put("Tipo de documento Servicio CA",template.getDescription());
        //return truncate(MetadataCadenaMdBuilder.buildBounded(md), MAX_LEN_CADENA_MD);
        System.out.println(SvProveedorCrudServiceImpl.MetadataCadenaMdBuilder.buildBounded(md, MAX_LEN_CADENA_MD).length());
        return MetadataCadenaMdBuilder.buildBounded(md, MAX_LEN_CADENA_MD);
    }
    public String generateCadenaNA(SvAdjuntosIndexDto indexDto,SvProveedorCitaRegistroDTO infoProv,SvTipoDocumentoPlanillaEntity template){
        Map<String, Object> md = new java.util.LinkedHashMap<>(); // mantiene el orden
        md.put("RUC", infoProv.getProv().getNroDocumentoIdentidad());
        md.put("Razon Social Proveedor", infoProv.getProv().getNombreFiscal());
        md.put("Fecha de documento", indexDto.getInfoAdjunto().getFechaDocumento().toString());
//        md.put("Fecha caduca1", indexDto.getInfoAdjunto().getFechaVencimiento().toString() );
        md.put("Fecha caduca1", indexDto.getInfoAdjunto().getFechaVencimiento() != null
                ? indexDto.getInfoAdjunto().getFechaVencimiento().toString()
                : ""
        );
        md.put("Ambito Accesos Sedes", "SSO" );
        md.put("Observaciones", indexDto.getInfoAdjunto().getMotivo());
        md.put("Sede LF ", "LIMA" );
        md.put("Necesita andamios","SI");
        md.put("Sustento andamios",template.getDescription());
        System.out.println(SvProveedorCrudServiceImpl.MetadataCadenaMdBuilder.buildBounded(md, MAX_LEN_CADENA_MD).length());
        return MetadataCadenaMdBuilder.buildBounded(md, MAX_LEN_CADENA_MD);
    }
    public String generateCadenaNG(SvAdjuntosIndexDto indexDto,SvProveedorCitaRegistroDTO infoProv,SvTipoDocumentoPlanillaEntity template){
        Map<String, Object> md = new java.util.LinkedHashMap<>(); // mantiene el orden
        md.put("RUC", infoProv.getProv().getNroDocumentoIdentidad());
        md.put("Razon Social Proveedor", infoProv.getProv().getNombreFiscal());
        md.put("Fecha de documento", indexDto.getInfoAdjunto().getFechaDocumento().toString());
//        md.put("Fecha caduca1", indexDto.getInfoAdjunto().getFechaVencimiento().toString() );
        md.put("Fecha caduca1", indexDto.getInfoAdjunto().getFechaVencimiento() != null
                ? indexDto.getInfoAdjunto().getFechaVencimiento().toString()
                : ""
        );
        md.put("Ambito Accesos Sedes", "SSO" );
        md.put("Observaciones", indexDto.getInfoAdjunto().getMotivo());
//        md.put("Sede LF ", "LIMA" );
        md.put("Necesita grua","SI");
        md.put("Sustento grua",template.getDescription());
        System.out.println(SvProveedorCrudServiceImpl.MetadataCadenaMdBuilder.buildBounded(md, MAX_LEN_CADENA_MD).length());
        return MetadataCadenaMdBuilder.buildBounded(md, MAX_LEN_CADENA_MD);
    }
    public String generateCadenaEM(SvAdjuntosIndexDto indexDto,SvProveedorCitaRegistroDTO infoProv,SvTipoDocumentoPlanillaEntity template){
        Map<String, Object> md = new java.util.LinkedHashMap<>(); // mantiene el orden
        md.put("RUC", infoProv.getProv().getNroDocumentoIdentidad());
        md.put("Razon Social Proveedor", infoProv.getProv().getNombreFiscal());
        md.put("Fecha de documento", indexDto.getInfoAdjunto().getFechaDocumento().toString());
//        md.put("Fecha caduca1", indexDto.getInfoAdjunto().getFechaVencimiento().toString() );
        md.put("Fecha caduca1", indexDto.getInfoAdjunto().getFechaVencimiento() != null
                ? indexDto.getInfoAdjunto().getFechaVencimiento().toString()
                : ""
        );
        md.put("Ambito Accesos Sedes", "SSO" );
        md.put("Observaciones", indexDto.getInfoAdjunto().getMotivo());
//        md.put("Sede LF ", "LIMA" );
//        md.put("Necesita equipos moviles","SI");
        md.put("Necesita equipos moviles","Si");
        md.put("Sustento equipos moviles",template.getDescription());
        System.out.println(SvProveedorCrudServiceImpl.MetadataCadenaMdBuilder.buildBounded(md, MAX_LEN_CADENA_MD).length());
        return MetadataCadenaMdBuilder.buildBounded(md, MAX_LEN_CADENA_MD);
    }
    public String generateCadenaTB(SvAdjuntosIndexDto indexDto,SvProveedorCitaRegistroDTO infoProv,SvTipoDocumentoPlanillaEntity template){
        Map<String, Object> md = new java.util.LinkedHashMap<>(); // mantiene el orden
        md.put("RUC", infoProv.getProv().getNroDocumentoIdentidad());
        md.put("Razon Social Proveedor", infoProv.getProv().getNombreFiscal());
        md.put("Fecha de documento", indexDto.getInfoAdjunto().getFechaDocumento().toString());
//        md.put("Fecha caduca1", indexDto.getInfoAdjunto().getFechaVencimiento().toString() );
        md.put("Fecha caduca1", indexDto.getInfoAdjunto().getFechaVencimiento() != null
                ? indexDto.getInfoAdjunto().getFechaVencimiento().toString()
                : ""
        );
        md.put("Ambito Accesos Sedes", "SSO" );
//        md.put("Sede LF ", "LIMA" );
        md.put("Necesita trabajo de buceo","SI");
        md.put("Observaciones", indexDto.getInfoAdjunto().getMotivo());
        md.put("Sustento trabajo buceo",template.getDescription());
        System.out.println(SvProveedorCrudServiceImpl.MetadataCadenaMdBuilder.buildBounded(md, MAX_LEN_CADENA_MD).length());
        return MetadataCadenaMdBuilder.buildBounded(md, MAX_LEN_CADENA_MD);
    }
    @Override
    @Transactional
    public SvProveedorCitaRegistroDTO saveInfo(SvProveedorCitaRegistroDTO solicitud, List<MultipartFile> ahsFilesSSO, List<MultipartFile> ahsFilesQA, List<MultipartFile> ahsFilesAAR, List<MultipartFile> ahsFilesAB, List<MultipartFile> ahsFilesNA, List<MultipartFile> ahsFilesNG, List<MultipartFile> ahsFilesEM, List<MultipartFile> ahsFilesTB) throws Exception {
        int anio = LocalDate.now().getYear();
        //solicitud.getInfoSolicitud().setNroOrdenServicio("XX");
        solicitud.getInfoSolicitud().setIsDeleted(false);
        if(solicitud.getInfoSolicitud().getId() == null){
            //solicitud.getInfoSolicitud().setEstadoSolicitudVisitaCode("ST_SV_0001");
            //String sequence = LocalDateTime.now().getYear()+""+iSvSolicitudVisistaRepository.getNextCorrelativoSimple();
//            solicitud.getInfoSolicitud().setCodigoVisita(String.format("%s - %d", "Juan", 2026));
            int correlativo = iSvSolicitudVisistaRepository.getNextCorrelativoSimple();
            solicitud.getInfoSolicitud().setCorrelativo(Long.valueOf(correlativo));
            solicitud.getInfoSolicitud().setCodigoVisita(LocalDateTime.now().getYear()+""+correlativo);
        }
        SvSolicitudVisistaEntity oSV = iSvSolicitudVisistaCrudService.save(solicitud.getInfoSolicitud());
        // --- Guardar SolicitudVisitaHistorial
        SvSolicitudVisitaHistorialEntity oSVH = iSvSolicitudVisitaHistorialCrudService.save( SvSolicitudVisitaHistorialDTO.builder( )
                .solicitudVisitaId( oSV.getId( ) )
                        .usuario(solicitud.getProv().getNombreFiscal())
                .estadoSolicitudVisitaCode( oSV.getEstadoSolicitudVisitaCode( ) )
                .fechaHora( LocalDateTime.now( ) )
                        .ambito("SSO")
                        .revision("No")
                .isDeleted(false)
                .build( ) );





        List<SvTipoDocumentoPlanillaEntity> aListTipoPlanilla = iSvTipoDocumentoPlanillaRepository.findAll();
        solicitud.setInfoSolicitud(svSolicitudVisistaMapper.entityToDto(oSV));
        int o = 0;
        if(solicitud.getListMaterials() != null && solicitud.getListMaterials().size() > 0)
            for(SvSolicitudVisitaMaterialDTO item: solicitud.getListMaterials()){
                item.setSolicitudVisitaId(solicitud.getInfoSolicitud().getId());
                if(item.getId() == null)
                    solicitud.getListMaterials().set(o,svSolicitudVisitaMaterialMapper.entityToDto(iSvSolicitudVisitaMaterialCrudService.save(item)));
                o++;
            }
        o = 0;
        if(solicitud.getDeleteListMaterials() != null && solicitud.getDeleteListMaterials().size() > 0)
            for(SvSolicitudVisitaMaterialDTO item: solicitud.getDeleteListMaterials()){
                item.setIsDeleted(true);
//                item.set(false);
                solicitud.getDeleteListMaterials().set(o,svSolicitudVisitaMaterialMapper.entityToDto(iSvSolicitudVisitaMaterialCrudService.save(item)));
                o++;
            }
        o = 0;
        if(solicitud.getListZona() != null && solicitud.getListZona().size() > 0)
            for(SvSolicitudVisitaZonaDTO item: solicitud.getListZona()){
                item.setSolicitudVisitaId(solicitud.getInfoSolicitud().getId());
                if(item.getId() == null)
                    solicitud.getListZona().set(o,svSolicitudVisitaZonaMapper.entityToDto(iSvSolicitudVisitaZonaCrudService.save(item)));
                o++;
            }
        o = 0;
        if(solicitud.getDeleteListZona() != null && solicitud.getDeleteListZona().size() > 0)
            for(SvSolicitudVisitaZonaDTO item: solicitud.getDeleteListZona()){
                item.setIsDeleted(true);
//                item.set
                solicitud.getDeleteListZona().set(o,svSolicitudVisitaZonaMapper.entityToDto(iSvSolicitudVisitaZonaCrudService.save(item)));
                o++;
            }
        o = 0;
        if(solicitud.getListAreas() != null && solicitud.getListAreas().size() > 0)
            for(SvSolicitudVisitaAreaAutorizadoraDTO item: solicitud.getListAreas()){
                item.setSolicitudVisitaId(solicitud.getInfoSolicitud().getId());
                if(item.getId() == null)
                    solicitud.getListAreas().set(o,svSolicitudVisitaAreaAutorizadoraMapper.entityToDto(iSvSolicitudVisitaAreaAutorizadoraCrudService.save(item)));
                o++;
            }
        o = 0;
        if(solicitud.getDeleteListAreas() != null && solicitud.getDeleteListAreas().size() > 0)
            for(SvSolicitudVisitaAreaAutorizadoraDTO item: solicitud.getDeleteListAreas()){
                item.setIsDeleted(true);
//                item.set
                solicitud.getDeleteListAreas().set(o,svSolicitudVisitaAreaAutorizadoraMapper.entityToDto(iSvSolicitudVisitaAreaAutorizadoraCrudService.save(item)));
                o++;
            }
        o = 0;
        if(solicitud.getListEquipos() != null && solicitud.getListEquipos().size() > 0)
            for(SvSolicitudVisitaEquipoDTO item: solicitud.getListEquipos()){
                item.setSolicitudVisitaId(solicitud.getInfoSolicitud().getId());
                if(item.getId() == null)
                    solicitud.getListEquipos().set(o,svSolicitudVisitaEquipoMapper.entityToDto(iSvSolicitudVisitaEquipoCrudService.save(item)));
                o++;
            }
        o = 0;
        if(solicitud.getDeleteListEquipos() != null && solicitud.getDeleteListEquipos().size() > 0)
            for(SvSolicitudVisitaEquipoDTO item: solicitud.getDeleteListEquipos()){
                item.setIsDeleted(true);
//                item.set
                solicitud.getDeleteListEquipos().set(o,svSolicitudVisitaEquipoMapper.entityToDto(iSvSolicitudVisitaEquipoCrudService.save(item)));
                o++;
            }
        o = 0;
        if(solicitud.getListTAAR() != null && solicitud.getListTAAR().size() > 0)
            for(SvSolicitudVisitaTipoActividadAltoRiesgoDTO item: solicitud.getListTAAR()){
                item.setSolicitudVisitaId(solicitud.getInfoSolicitud().getId());
                if(item.getId() == null)
                    solicitud.getListTAAR().set(o,svSolicitudVisitaTipoActividadAltoRiesgoMapper.entityToDto(iSvSolicitudVisitaTipoActividadAltoRiesgoCrudService.save(item)));
                o++;
            }
        o = 0;
        if(solicitud.getDeleteListTAAR() != null && solicitud.getDeleteListTAAR().size() > 0)
            for(SvSolicitudVisitaTipoActividadAltoRiesgoDTO item: solicitud.getDeleteListTAAR()){
                item.setIsDeleted(true);
                item.setIsActive(false);
                //item.setSolicitudVisitaId(solicitud.getInfoSolicitud().getId());
                solicitud.getDeleteListTAAR().set(o,svSolicitudVisitaTipoActividadAltoRiesgoMapper.entityToDto(iSvSolicitudVisitaTipoActividadAltoRiesgoCrudService.save(item)));
                o++;
            }

        IdArchivosECMEnc deleteFiles = new IdArchivosECMEnc();
        ArrayList<String> ids = new ArrayList<>();
        if(solicitud.getDeletesFiles() != null && solicitud.getDeletesFiles().size() > 0){
            for (SvAdjuntoDTO item :solicitud.getDeletesFiles()){
                ids.add(item.getIdRepositorio());
                SvAdjuntoDTO oAdj = iSvAdjuntoQueryService.findById(item.getId());
                oAdj.setIsDeleted(true);
                iSvAdjuntoCrudService.save(oAdj);
            }
            deleteFiles.setString(ids);
            try{
                laserFiche.deleteFilesLaserFiche(deleteFiles);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        //SSO
        System.out.println("SAVING SSO");
        if(solicitud.getHsFilesSSO() != null && solicitud.getHsFilesSSO().size() > 0){
            try{
                List<FileUploadDto> afiles = new ArrayList<>();
                for (int i = 0; i < solicitud.getHsFilesSSO().size(); i++) {
                    SvAdjuntosIndexDto p = solicitud.getHsFilesSSO().get(i);
                    afiles.add(FileUploadDto.builder()
                            .pArchivoOrigen(ahsFilesSSO.get(i))
                            .pNombresDestino(ahsFilesSSO.get(i).getOriginalFilename())
//                            .pRutaArchivoDestino("COPEINCA\\COPETESTING\\")
                            .pRutaArchivoDestino(sPath+anio+"\\")
                            .pCadenaMD(this.generateCadenaSSO(p,solicitud,aListTipoPlanilla.stream()
                                    .filter(t -> p.getInfoAdjunto().getTipoDocumentoPlanillaCode().equals(t.getId()))
                                    .findFirst().get()))
//                            .pNombreTipoDocumento("EXTR Certificado de Origen")
                            .pNombreTipoDocumento(sTipo)
                            .build());
                }
                SubirArchivoECMSoapResponse ex = laserFiche.uploadFilesMasive(afiles);
                for (int i = 0; i < ex.getBody().getResponse().getResult().getString().size(); i++) {
                    String[] Id = ex.getBody().getResponse().getResult().getString().get(i).split("#");
                    solicitud.getHsFilesSSO().get(i).setInfoAdjunto(adjuntoMapper.entityToDto(iSvAdjuntoCrudService.save(SvAdjuntoDTO.builder()
                            .nombreArchivo(ahsFilesSSO.get(i).getOriginalFilename())
                            .idRepositorio(Id[1])
                            .clasification("Cita SSO")
                            .fechaDocumento(solicitud.getHsFilesSSO().get(i).getInfoAdjunto().getFechaDocumento())
                            .fechaVencimiento(solicitud.getHsFilesSSO().get(i).getInfoAdjunto().getFechaVencimiento())
                            .ssoComentarioRevision(solicitud.getHsFilesSSO().get(i).getInfoAdjunto().getSsoComentarioRevision())
                            .proveedorId(solicitud.getProv().getId())
                            .solicitudVisitaId(solicitud.getInfoSolicitud().getId())
                            .tipoDocumentoPlanillaCode(solicitud.getHsFilesSSO().get(i).getInfoAdjunto().getTipoDocumentoPlanillaCode())
                            .isDeleted(false)
//                            .rutaRelativa("COPEINCA\\COPETESTING\\")
                            .rutaRelativa(sPath+anio+"\\")
                            .motivo(solicitud.getHsFilesSSO().get(i).getInfoAdjunto().getMotivo())
                            .version(1L)
                            .build())));
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }


        //QA
        System.out.println("SAVING QA");
        if(solicitud.getHsFilesQA() != null && solicitud.getHsFilesQA().size() > 0)
            try{
                List<FileUploadDto> afiles = new ArrayList<>();
                for (int i = 0; i < solicitud.getHsFilesQA().size(); i++) {
                    SvAdjuntosIndexDto p = solicitud.getHsFilesQA().get(i);
                    afiles.add(FileUploadDto.builder()
                            .pArchivoOrigen(ahsFilesQA.get(i))
                            .pNombresDestino(ahsFilesQA.get(i).getOriginalFilename())
//                            .pRutaArchivoDestino("COPEINCA\\COPETESTING\\")
                            .pRutaArchivoDestino(sPath+anio+"\\")
                            .pCadenaMD(this.generateCadenaQA(p,solicitud,aListTipoPlanilla.stream()
                                    .filter(t -> p.getInfoAdjunto().getTipoDocumentoPlanillaCode().equals(t.getId()))
                                    .findFirst().get()))
                            .pNombreTipoDocumento(sTipo)
//                            .pNombreTipoDocumento("EXTR Certificado de Origen")
                            .build());
                }
                SubirArchivoECMSoapResponse ex = laserFiche.uploadFilesMasive(afiles);
                for (int i = 0; i < ex.getBody().getResponse().getResult().getString().size(); i++) {
                    String[] Id = ex.getBody().getResponse().getResult().getString().get(i).split("#");
                    solicitud.getHsFilesQA().get(i).setInfoAdjunto(adjuntoMapper.entityToDto(iSvAdjuntoCrudService.save(SvAdjuntoDTO.builder()
                            .nombreArchivo(ahsFilesQA.get(i).getOriginalFilename())
                            .idRepositorio(Id[1])
                            .clasification("Cita QA")
                            .fechaDocumento(solicitud.getHsFilesQA().get(i).getInfoAdjunto().getFechaDocumento())
                            .fechaVencimiento(solicitud.getHsFilesQA().get(i).getInfoAdjunto().getFechaVencimiento())
                            .ssoComentarioRevision(solicitud.getHsFilesQA().get(i).getInfoAdjunto().getSsoComentarioRevision())
                            .proveedorId(solicitud.getProv().getId())
                            .tipoDocumentoPlanillaCode(solicitud.getHsFilesQA().get(i).getInfoAdjunto().getTipoDocumentoPlanillaCode())
                            .isDeleted(false)
                            .rutaRelativa(sPath+anio+"\\")
                            .motivo(solicitud.getHsFilesQA().get(i).getInfoAdjunto().getMotivo())
                            .solicitudVisitaId(solicitud.getInfoSolicitud().getId())
//                            .rutaRelativa("COPEINCA\\COPETESTING\\")
                            .version(1L)
                            .build())));
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        //AAR
        System.out.println("SAVING AAR");
        if(solicitud.getHsFilesAAR() != null && solicitud.getHsFilesAAR().size() > 0)
            try{
                List<FileUploadDto> afiles = new ArrayList<>();
                for (int i = 0; i < solicitud.getHsFilesAAR().size(); i++) {
                    SvAdjuntosIndexDto p = solicitud.getHsFilesAAR().get(i);
                    afiles.add(FileUploadDto.builder()
                            .pArchivoOrigen(ahsFilesAAR.get(i))
                            .pNombresDestino(ahsFilesAAR.get(i).getOriginalFilename())
                            .pRutaArchivoDestino(sPath+anio+"\\")
    //                        .pRutaArchivoDestino("COPEINCA\\COPETESTING\\")
                            .pCadenaMD(this.generateCadenaAAR(p,solicitud,aListTipoPlanilla.stream()
                                    .filter(t -> p.getInfoAdjunto().getTipoDocumentoPlanillaCode().equals(t.getId()))
                                    .findFirst().get()))
                            .pNombreTipoDocumento(sTipo)
    //                        .pNombreTipoDocumento("EXTR Certificado de Origen")
                            .build());
                }
                SubirArchivoECMSoapResponse ex = laserFiche.uploadFilesMasive(afiles);
                for (int i = 0; i < ex.getBody().getResponse().getResult().getString().size(); i++) {
                    String[] Id = ex.getBody().getResponse().getResult().getString().get(i).split("#");
                    solicitud.getHsFilesAAR().get(i).setInfoAdjunto(adjuntoMapper.entityToDto(iSvAdjuntoCrudService.save(SvAdjuntoDTO.builder()
                            .nombreArchivo(ahsFilesAAR.get(i).getOriginalFilename())
                            .idRepositorio(Id[1])
                            .clasification("Cita AAR")
                            .fechaDocumento(solicitud.getHsFilesAAR().get(i).getInfoAdjunto().getFechaDocumento())
                            .fechaVencimiento(solicitud.getHsFilesAAR().get(i).getInfoAdjunto().getFechaVencimiento())
                            .ssoComentarioRevision(solicitud.getHsFilesAAR().get(i).getInfoAdjunto().getSsoComentarioRevision())
                            .proveedorId(solicitud.getProv().getId())
                            .tipoDocumentoPlanillaCode(solicitud.getHsFilesAAR().get(i).getInfoAdjunto().getTipoDocumentoPlanillaCode())
                            .isDeleted(false)
                            .rutaRelativa(sPath+anio+"\\")
    //                        .rutaRelativa("COPEINCA\\COPETESTING\\")
                            .motivo(solicitud.getHsFilesAAR().get(i).getInfoAdjunto().getMotivo())
                            .solicitudVisitaId(solicitud.getInfoSolicitud().getId())
                            .version(1L)
                            .build())));
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        //AB
        System.out.println("SAVING AB");
        if(solicitud.getHsFilesAB() != null && solicitud.getHsFilesAB().size() > 0)
            try{
                List<FileUploadDto> afiles = new ArrayList<>();
                for (int i = 0; i < solicitud.getHsFilesAB().size(); i++) {
                    SvAdjuntosIndexDto p = solicitud.getHsFilesAB().get(i);
                    afiles.add(FileUploadDto.builder()
                            .pArchivoOrigen(ahsFilesAB.get(i))
                            .pNombresDestino(ahsFilesAB.get(i).getOriginalFilename())
                            .pRutaArchivoDestino(sPath+anio+"\\")
    //                        .pRutaArchivoDestino("COPEINCA\\COPETESTING\\")
                            .pCadenaMD(this.generateCadenaABordo(p,solicitud,aListTipoPlanilla.stream()
                                    .filter(t -> p.getInfoAdjunto().getTipoDocumentoPlanillaCode().equals(t.getId()))
                                    .findFirst().get()))
                            .pNombreTipoDocumento(sTipo)
    //                        .pNombreTipoDocumento("EXTR Certificado de Origen")
                            .build());
                }
                SubirArchivoECMSoapResponse ex = laserFiche.uploadFilesMasive(afiles);
                for (int i = 0; i < ex.getBody().getResponse().getResult().getString().size(); i++) {
                    String[] Id = ex.getBody().getResponse().getResult().getString().get(i).split("#");
                    solicitud.getHsFilesAB().get(i).setInfoAdjunto(adjuntoMapper.entityToDto(iSvAdjuntoCrudService.save(SvAdjuntoDTO.builder()
                            .nombreArchivo(ahsFilesAB.get(i).getOriginalFilename())
                            .idRepositorio(Id[1])
                            .clasification("Cita AB")
                            .fechaDocumento(solicitud.getHsFilesAB().get(i).getInfoAdjunto().getFechaDocumento())
                            .fechaVencimiento(solicitud.getHsFilesAB().get(i).getInfoAdjunto().getFechaVencimiento())
                            .ssoComentarioRevision(solicitud.getHsFilesAB().get(i).getInfoAdjunto().getSsoComentarioRevision())
                            .proveedorId(solicitud.getProv().getId())
                            .tipoDocumentoPlanillaCode(solicitud.getHsFilesAB().get(i).getInfoAdjunto().getTipoDocumentoPlanillaCode())
                            .isDeleted(false)
                            .rutaRelativa(sPath+anio+"\\")
                            .motivo(solicitud.getHsFilesAB().get(i).getInfoAdjunto().getMotivo())
                            .solicitudVisitaId(solicitud.getInfoSolicitud().getId())
    //                        .rutaRelativa("COPEINCA\\COPETESTING\\")
                            .version(1L)
                            .build())));
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        //NA
        System.out.println("SAVING NA");
        if(solicitud.getHsFilesNA() != null && solicitud.getHsFilesNA().size() > 0)
            try{
                List<FileUploadDto> afiles = new ArrayList<>();
                for (int i = 0; i < solicitud.getHsFilesNA().size(); i++) {
                    SvAdjuntosIndexDto p = solicitud.getHsFilesNA().get(i);
                    afiles.add(FileUploadDto.builder()
                            .pArchivoOrigen(ahsFilesNA.get(i))
                            .pNombresDestino(ahsFilesNA.get(i).getOriginalFilename())
    //                        .pRutaArchivoDestino("COPEINCA\\COPETESTING\\")
                            .pRutaArchivoDestino(sPath+anio+"\\")
                            .pCadenaMD(this.generateCadenaNA(p,solicitud,aListTipoPlanilla.stream()
                                    .filter(t -> p.getInfoAdjunto().getTipoDocumentoPlanillaCode().equals(t.getId()))
                                    .findFirst().get()))
                            .pNombreTipoDocumento(sTipo)
    //                        .pNombreTipoDocumento("EXTR Certificado de Origen")
                            .build());
                }
                SubirArchivoECMSoapResponse ex = laserFiche.uploadFilesMasive(afiles);
                for (int i = 0; i < ex.getBody().getResponse().getResult().getString().size(); i++) {
                    String[] Id = ex.getBody().getResponse().getResult().getString().get(i).split("#");
                    solicitud.getHsFilesNA().get(i).setInfoAdjunto(adjuntoMapper.entityToDto(iSvAdjuntoCrudService.save(SvAdjuntoDTO.builder()
                            .nombreArchivo(ahsFilesNA.get(i).getOriginalFilename())
                            .idRepositorio(Id[1])
                            .clasification("Cita NA")
                            .fechaDocumento(solicitud.getHsFilesNA().get(i).getInfoAdjunto().getFechaDocumento())
                            .fechaVencimiento(solicitud.getHsFilesNA().get(i).getInfoAdjunto().getFechaVencimiento())
                            .ssoComentarioRevision(solicitud.getHsFilesNA().get(i).getInfoAdjunto().getSsoComentarioRevision())
                            .proveedorId(solicitud.getProv().getId())
                            .tipoDocumentoPlanillaCode(solicitud.getHsFilesNA().get(i).getInfoAdjunto().getTipoDocumentoPlanillaCode())
                            .isDeleted(false)
                            .rutaRelativa(sPath+anio+"\\")
                            .motivo(solicitud.getHsFilesNA().get(i).getInfoAdjunto().getMotivo())
                            .solicitudVisitaId(solicitud.getInfoSolicitud().getId())
    //                        .rutaRelativa("COPEINCA\\COPETESTING\\")
                            .version(1L)
                            .build())));
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        //SSO
        System.out.println("SAVING NG");
        if(solicitud.getHsFilesNG() != null && solicitud.getHsFilesNG().size() > 0)
            try{
                List<FileUploadDto> afiles = new ArrayList<>();
                for (int i = 0; i < solicitud.getHsFilesNG().size(); i++) {
                    SvAdjuntosIndexDto p = solicitud.getHsFilesNG().get(i);
                    afiles.add(FileUploadDto.builder()
                            .pArchivoOrigen(ahsFilesNG.get(i))
                            .pNombresDestino(ahsFilesNG.get(i).getOriginalFilename())
    //                        .pRutaArchivoDestino("COPEINCA\\COPETESTING\\")
                            .pRutaArchivoDestino(sPath+anio+"\\")
                            .pCadenaMD(this.generateCadenaNG(p,solicitud,aListTipoPlanilla.stream()
                                    .filter(t -> p.getInfoAdjunto().getTipoDocumentoPlanillaCode().equals(t.getId()))
                                    .findFirst().get()))
                            .pNombreTipoDocumento(sTipo)
    //                        .pNombreTipoDocumento("EXTR Certificado de Origen")
                            .build());
                }
                SubirArchivoECMSoapResponse ex = laserFiche.uploadFilesMasive(afiles);
                for (int i = 0; i < ex.getBody().getResponse().getResult().getString().size(); i++) {
                    String[] Id = ex.getBody().getResponse().getResult().getString().get(i).split("#");
                    solicitud.getHsFilesNG().get(i).setInfoAdjunto(adjuntoMapper.entityToDto(iSvAdjuntoCrudService.save(SvAdjuntoDTO.builder()
                            .nombreArchivo(ahsFilesNG.get(i).getOriginalFilename())
                            .idRepositorio(Id[1])
                            .clasification("Cita NG")
                            .fechaDocumento(solicitud.getHsFilesNG().get(i).getInfoAdjunto().getFechaDocumento())
                            .fechaVencimiento(solicitud.getHsFilesNG().get(i).getInfoAdjunto().getFechaVencimiento())
                            .ssoComentarioRevision(solicitud.getHsFilesNG().get(i).getInfoAdjunto().getSsoComentarioRevision())
                            .proveedorId(solicitud.getProv().getId())
                            .tipoDocumentoPlanillaCode(solicitud.getHsFilesNG().get(i).getInfoAdjunto().getTipoDocumentoPlanillaCode())
                            .isDeleted(false)
                            .solicitudVisitaId(solicitud.getInfoSolicitud().getId())
                            .rutaRelativa(sPath+anio+"\\")
                            .motivo(solicitud.getHsFilesNG().get(i).getInfoAdjunto().getMotivo())
    //                        .rutaRelativa("COPEINCA\\COPETESTING\\")
                            .version(1L)
                            .build())));
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        //EM
        System.out.println("SAVING EM");
        if(solicitud.getHsFilesEM() != null && solicitud.getHsFilesEM().size() > 0)
            try{
                List<FileUploadDto> afiles = new ArrayList<>();
                for (int i = 0; i < solicitud.getHsFilesEM().size(); i++) {
                    SvAdjuntosIndexDto p = solicitud.getHsFilesEM().get(i);
                    afiles.add(FileUploadDto.builder()
                            .pArchivoOrigen(ahsFilesEM.get(i))
                            .pNombresDestino(ahsFilesEM.get(i).getOriginalFilename())
                            .pRutaArchivoDestino(sPath+anio+"\\")
    //                        .pRutaArchivoDestino("COPEINCA\\COPETESTING\\")
                            .pCadenaMD(this.generateCadenaEM(p,solicitud,aListTipoPlanilla.stream()
                                    .filter(t -> p.getInfoAdjunto().getTipoDocumentoPlanillaCode().equals(t.getId()))
                                    .findFirst().get()))
                            .pNombreTipoDocumento(sTipo)
    //                        .pNombreTipoDocumento("EXTR Certificado de Origen")
                            .build());
                }
                SubirArchivoECMSoapResponse ex = laserFiche.uploadFilesMasive(afiles);
                for (int i = 0; i < ex.getBody().getResponse().getResult().getString().size(); i++) {
                    String[] Id = ex.getBody().getResponse().getResult().getString().get(i).split("#");
                    solicitud.getHsFilesEM().get(i).setInfoAdjunto(adjuntoMapper.entityToDto(iSvAdjuntoCrudService.save(SvAdjuntoDTO.builder()
                            .nombreArchivo(ahsFilesEM.get(i).getOriginalFilename())
                            .idRepositorio(Id[1])
                            .clasification("Cita EM")
                            .fechaDocumento(solicitud.getHsFilesEM().get(i).getInfoAdjunto().getFechaDocumento())
                            .fechaVencimiento(solicitud.getHsFilesEM().get(i).getInfoAdjunto().getFechaVencimiento())
                            .ssoComentarioRevision(solicitud.getHsFilesEM().get(i).getInfoAdjunto().getSsoComentarioRevision())
                            .proveedorId(solicitud.getProv().getId())
                            .tipoDocumentoPlanillaCode(solicitud.getHsFilesEM().get(i).getInfoAdjunto().getTipoDocumentoPlanillaCode())
                            .isDeleted(false)
                            .rutaRelativa(sPath+anio+"\\")
                            .solicitudVisitaId(solicitud.getInfoSolicitud().getId())
                            .motivo(solicitud.getHsFilesEM().get(i).getInfoAdjunto().getMotivo())
    //                        .rutaRelativa("COPEINCA\\COPETESTING\\")
                            .version(1L)
                            .build())));
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        //TB
        System.out.println("SAVING TB");
        if(solicitud.getHsFilesTB() != null && solicitud.getHsFilesTB().size() > 0)
            try{
                List<FileUploadDto> afiles = new ArrayList<>();
                for (int i = 0; i < solicitud.getHsFilesTB().size(); i++) {
                    SvAdjuntosIndexDto p = solicitud.getHsFilesTB().get(i);
                    afiles.add(FileUploadDto.builder()
                            .pArchivoOrigen(ahsFilesTB.get(i))
                            .pNombresDestino(ahsFilesTB.get(i).getOriginalFilename())
                            .pRutaArchivoDestino(sPath+anio+"\\")
    //                        .pRutaArchivoDestino("COPEINCA\\COPETESTING\\")
                            .pCadenaMD(this.generateCadenaTB(p,solicitud,aListTipoPlanilla.stream()
                                    .filter(t -> p.getInfoAdjunto().getTipoDocumentoPlanillaCode().equals(t.getId()))
                                    .findFirst().get()))
                            .pNombreTipoDocumento(sTipo)
    //                        .pNombreTipoDocumento("EXTR Certificado de Origen")
                            .build());
                }
                SubirArchivoECMSoapResponse ex = laserFiche.uploadFilesMasive(afiles);
                for (int i = 0; i < ex.getBody().getResponse().getResult().getString().size(); i++) {
                    String[] Id = ex.getBody().getResponse().getResult().getString().get(i).split("#");
                    solicitud.getHsFilesTB().get(i).setInfoAdjunto(adjuntoMapper.entityToDto(iSvAdjuntoCrudService.save(SvAdjuntoDTO.builder()
                            .nombreArchivo(ahsFilesTB.get(i).getOriginalFilename())
                            .idRepositorio(Id[1])
                            .clasification("Cita TB")
                            .fechaDocumento(solicitud.getHsFilesTB().get(i).getInfoAdjunto().getFechaDocumento())
                            .fechaVencimiento(solicitud.getHsFilesTB().get(i).getInfoAdjunto().getFechaVencimiento())
                            .ssoComentarioRevision(solicitud.getHsFilesTB().get(i).getInfoAdjunto().getSsoComentarioRevision())
                            .proveedorId(solicitud.getProv().getId())
                            .tipoDocumentoPlanillaCode(solicitud.getHsFilesTB().get(i).getInfoAdjunto().getTipoDocumentoPlanillaCode())
                            .isDeleted(false)
                            .rutaRelativa(sPath+anio+"\\")
                            .solicitudVisitaId(solicitud.getInfoSolicitud().getId())
                            .motivo(solicitud.getHsFilesTB().get(i).getInfoAdjunto().getMotivo())
    //                        .rutaRelativa("COPEINCA\\COPETESTING\\")
                            .version(1L)
                            .build())));
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        //notificationService.sendSolicitudVisita( oSV.getId( ) );
        return solicitud;
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
    public SvProveedorCitaRegistroDTO getInfoVisita(String sId) {
        SvProveedorCitaRegistroDTO result = new SvProveedorCitaRegistroDTO();
        result.setInfoSolicitud(iSvSolicitudVisistaQueryService.findById(sId));
        result.setProv(iSvProveedorQueryService.findById(result.getInfoSolicitud().getProveedorId()));
//        List<SvSolicitudVisitaAreaAutorizadoraDTO> aList = ;
        List<SvAdjuntoEntity> aListAdjuntos = iSvAdjuntoRepository.findAllBySolicitudVisitaId(sId);
        result.setListAreas(iSvSolicitudVisitaAreaAutorizadoraRepository.findAllBySolicitudVisitaId(sId).stream()
                .map(svSolicitudVisitaAreaAutorizadoraMapper::entityToDto).toList());
        result.setListMaterials(iSvSolicitudVisitaMaterialRepository.findAllBySolicitudVisitaId(sId).stream()
                .map(svSolicitudVisitaMaterialMapper::entityToDto).toList());
        result.setListEquipos(iSvSolicitudVisitaEquipoRepository.findAllBySolicitudVisitaId(sId).stream()
                .map(svSolicitudVisitaEquipoMapper::entityToDto).toList());
        result.setListTAAR(iSvSolicitudVisitaTipoActividadAltoRiesgoRepository.findAllBySolicitudVisitaId(sId).stream()
                .map(svSolicitudVisitaTipoActividadAltoRiesgoMapper::entityToDto).toList());
        result.setListZona(iSvSolicitudVisitaZonaRepository.findAllBySolicitudVisitaId(sId).stream()
                .map(svSolicitudVisitaZonaMapper::entityToDto).toList());

        result.setHsFilesSSO(aListAdjuntos.stream()
                        .map(adjuntoMapper::entityToDto)
                .filter(dto ->
                        "Cita SSO".equals(dto.getClasification())
                                && Boolean.FALSE.equals(dto.getIsDeleted())
                )
                .map(dto -> SvAdjuntosIndexDto.builder()
                        .index(0)
                        .infoAdjunto(dto)
                        .build()).toList());
        result.setHsFilesQA(aListAdjuntos.stream()
                .map(adjuntoMapper::entityToDto)
                .filter(dto ->
                        "Cita QA".equals(dto.getClasification())
                                && Boolean.FALSE.equals(dto.getIsDeleted())
                )
                .map(dto -> SvAdjuntosIndexDto.builder()
                        .index(0)
                        .infoAdjunto(dto)
                        .build()).toList());
        result.setHsFilesAAR(aListAdjuntos.stream()
                .map(adjuntoMapper::entityToDto)
                .filter(dto ->
                        "Cita AAR".equals(dto.getClasification())
                                && Boolean.FALSE.equals(dto.getIsDeleted())
                )
                .map(dto -> SvAdjuntosIndexDto.builder()
                        .index(0)
                        .infoAdjunto(dto)
                        .build()).toList());

        result.setHsFilesAB(aListAdjuntos.stream()
                .map(adjuntoMapper::entityToDto)
                .filter(dto ->
                        "Cita AB".equals(dto.getClasification())
                                && Boolean.FALSE.equals(dto.getIsDeleted())
                )
                .map(dto -> SvAdjuntosIndexDto.builder()
                        .index(0)
                        .infoAdjunto(dto)
                        .build()).toList());
        result.setHsFilesNA(aListAdjuntos.stream()
                .map(adjuntoMapper::entityToDto)
                .filter(dto ->
                        "Cita NA".equals(dto.getClasification())
                                && Boolean.FALSE.equals(dto.getIsDeleted())
                )
                .map(dto -> SvAdjuntosIndexDto.builder()
                        .index(0)
                        .infoAdjunto(dto)
                        .build()).toList());
        result.setHsFilesNG(aListAdjuntos.stream()
                .map(adjuntoMapper::entityToDto)
                .filter(dto ->
                        "Cita NG".equals(dto.getClasification())
                                && Boolean.FALSE.equals(dto.getIsDeleted())
                )
                .map(dto -> SvAdjuntosIndexDto.builder()
                        .index(0)
                        .infoAdjunto(dto)
                        .build()).toList());
        result.setHsFilesEM(aListAdjuntos.stream()
                .map(adjuntoMapper::entityToDto)
                .filter(dto ->
                        "Cita EM".equals(dto.getClasification())
                                && Boolean.FALSE.equals(dto.getIsDeleted())
                )
                .map(dto -> SvAdjuntosIndexDto.builder()
                        .index(0)
                        .infoAdjunto(dto)
                        .build()).toList());
        result.setHsFilesTB(aListAdjuntos.stream()
                .map(adjuntoMapper::entityToDto)
                .filter(dto ->
                        "Cita TB".equals(dto.getClasification())
                                && Boolean.FALSE.equals(dto.getIsDeleted())
                )
                .map(dto -> SvAdjuntosIndexDto.builder()
                        .index(0)
                        .infoAdjunto(dto)
                        .build()).toList());
//        List<SvSolicitudVisitaDetalleEntity> aList = iSvSolicitudVisitaDetalleRepository.findAllBySolicitudVisitaId(sId);
//        PagedRequest<ViewTrabajadorReportFilter> filter = new PagedRequest<>();
//        filter.setPage(0);
//        filter.setOffset(0);
//        filter.setLimit(200);
//        filter.setSize(200);
//        FilterOperation sFilter = new FilterOperation<>();
//        sFilter.setOperator(OperatorEnum.IN);
//        sFilter.setValues(aList.size() > 0 ? aList.stream().map(SvSolicitudVisitaDetalleEntity::getTrabajadorId).toList(): List.of("123456"));
//        filter.setFilter(ViewTrabajadorReportFilter.builder()
//                .id(List.of(sFilter))
//                .build());
//
//        PagedResult<ViewTrabajadorReportDTO> resultTrabajadores = iViewTrabajadorReportQueryService.search(filter);
//        Map<String, SvSolicitudVisitaDetalleEntity> detalleByTrabajadorId = aList.stream()
//                .filter(e -> e.getTrabajadorId() != null)
//                .collect(Collectors.toMap(
//                        SvSolicitudVisitaDetalleEntity::getTrabajadorId,
//                        e -> e,
//                        (a, b) -> a // si hay duplicado, te quedas con el primero
//                ));
//        resultTrabajadores.getResult().forEach(t -> {
//            var detalle = detalleByTrabajadorId.get(t.getId());
//            if (detalle != null) {
//                t.setSolicitudVisitaDetalleObs(detalle.getProveedorComentario());
//                t.setSolicitudVisitaDetalleId(detalle.getId());
//                t.setSolicitudVisitaDetalleVersion(detalle.getVersion());
//
//            }
//        });
//        result.setListTrbjs(resultTrabajadores.getResult());
        return result;
    }

    /**
     WS: wsActualizaVisitaTercera
     -Actualiza estado de visita tercera en app BTP
     -Se invoca desde app Accesos Sedes
     -Opcional: en Mendix pueden adjuntar 1 o mas archivos adjuntos. Se envía detalle de IDs y texto de revision, para que en BTP se incorporen en Bitacora de documentos "Accesos Sedes".
     Esta Bitácora de documentos "Accesos Sedes" debe visualizarse en BTP, a nivel de ventana "Detalle de visita" en pestaña separada "Docs adjuntos Accesos Sedes"
     -Ambito, Revision, Respuesta del usuario => utiles cuando en Mendix Accesos Sedes el caso tuvo observacion de SSO, SP, ADM o Calidad Ambiental
     */
    @Override
    public void wsActualizaVisitaTercera( SvSolicitudVisistaDTO svSolicitudVisistaDTO ) {
        String solicitudVisitaId = svSolicitudVisistaDTO.getId( );

        try {

            EstadoSolicitudVisitaEnum estado =
                    EstadoSolicitudVisitaEnum.fromCode(
                            svSolicitudVisistaDTO.getEstadoSolicitudVisitaCode( )
                    );

            switch( estado ) {
                case EstadoSolicitudVisitaEnum.REV_RECHAZO_VIS:
                    //actualizarRechazoPersonalVisitado( solicitudVisitaId );
                    notificationService.sendAprobacionVisitaProveedor( solicitudVisitaId );
                    break;
                case EstadoSolicitudVisitaEnum.APTA_RECEPCION:
                    //actualizarAptoRecepcion( solicitudVisitaId );
                    notificationService.sendAprobacionVisitaSsoQa( solicitudVisitaId );
                    break;
                case EstadoSolicitudVisitaEnum.OBSERVADO:
                    //actualizarObservado( solicitudVisitaId );
                    break;
                case EstadoSolicitudVisitaEnum.INICIADO:
                    //actualizarIniciado( solicitudVisitaId );
                    break;
                case EstadoSolicitudVisitaEnum.FINALIZADO:
                    //actualizarFinalizado( solicitudVisitaId );
                    break;
                case EstadoSolicitudVisitaEnum.CANCELADO:
                    //actualizarCancelado( solicitudVisitaId );
                    notificationService.sendCancelacionVisita( solicitudVisitaId );
                    break;
                case EstadoSolicitudVisitaEnum.SUSPENDIDO:
                    //actualizarSuspendido( solicitudVisitaId );
                    break;
                case EstadoSolicitudVisitaEnum.SIN_REG_SALIDA:
                    //actualizarSinRegistroSalida( solicitudVisitaId );
                    break;
                case EstadoSolicitudVisitaEnum.FINALIZADO_SIN_REG_SALIDA:
                    //actualizarFinalizadoSinRegistroSalida( solicitudVisitaId );
                    break;
                case EstadoSolicitudVisitaEnum.FINALIZADO_NO_ASISTIO:
                    //actualizarFinalizadoNoAsistio( solicitudVisitaId );
                    break;
                default:
                    break;
            }
        } catch( Exception ex ) {
            log.error( ex.getMessage( ) );
        }
    }

    @Override
    public ResultadoWSActualiza wsActualizaMendix(ActualizarCasoRequest request, String visitaId) {
        SvSolicitudVisistaEntity oTmp = iSvSolicitudVisistaRepository.findById(visitaId).orElse(null);
        ActualizarCasoFiltroConsulta oCasoPersist = request.getFiltroConsulta();
        String solicitudVisitaId = oTmp.getCodigoSistemaExterno() == null ? "" : oTmp.getCodigoSistemaExterno();

        Caso caso = Caso.builder()
                .nroCaso(oTmp.getCodigoSistemaExterno() == null? "":oTmp.getCodigoSistemaExterno())
                .estadoDeCaso(oCasoPersist.getEstadoCaso())
                .ambito("")
                .revision("DEV")
                .respuestaDelUsuario(oCasoPersist.getObservacion())
                .observaciones(oCasoPersist.getObservacion())
                .fechaAccion(oCasoPersist.getFechaAccion())
                .build();
        try {
            //List<SvSolicitudVisistaEntity> oTmp =  iSvSolicitudVisistaRepository.findAllByCodigoSistemaExterno(solicitudVisitaId);
            if(oTmp == null)
                throw new Exception("Nro de Caso inexistente");

            EstadoSolicitudVisitaEnum estado =
                    EstadoSolicitudVisitaEnum.fromCode(
                            oTmp.getEstadoSolicitudVisitaCode()
                    );

//            String estado =caso.getEstadoDeCaso( );
            System.out.println(estado.getName());

            saveSolicitudVisitaEvento( caso, visitaId );
            iSvSolicitudVisitaHistorialRepository.save(SvSolicitudVisitaHistorialEntity.builder()
                    .usuario(caso.getUsuario( ) )
                    .solicitudVisitaId(solicitudVisitaId)
                    .estadoSolicitudVisitaCode(estado.getCode())
                    .fechaHora(LocalDateTime.now( ) )
                    .ambito( caso.getAmbito( ) )
                    .revision( caso.getRevision( ) )
                    .rptaByUsuario( caso.getRespuestaDelUsuario( ) )
                    .build());

            switch( estado ) {
                case EstadoSolicitudVisitaEnum.PRELIMINAR_PROV:
                    System.out.println(DateTimeUtil.asLocalDateTimev2( caso.getFechaAccion( ) ));
                    if (oTmp.getCodigoSistemaExterno().isEmpty() || oTmp.getCodigoSistemaExterno() == null)
                        actualizarCancelado( solicitudVisitaId, caso );
                    saveSolicitudVisitaEvento( caso, solicitudVisitaId );
                    notificationService.sendAprobacionVisitaProveedor( solicitudVisitaId );

                   //actualizarRechazoPersonalVisitado( solicitudVisitaId, caso );
                    //notificationService.sendAprobacionVisitaProveedor( solicitudVisitaId );
                    break;
                case EstadoSolicitudVisitaEnum.POR_HABILITAR_SRV:
                    if (oTmp.getCodigoSistemaExterno().isEmpty() || oTmp.getCodigoSistemaExterno() == null){
                        actualizarCancelado( solicitudVisitaId, caso );
                    }else{
                        //CALL SERVICE MENDIX
//                        clientService.wsActualizaVisitaMendix(request);
                        actualizarCancelado( solicitudVisitaId, caso );
                    }
                    notificationService.sendAprobacionVisitaProveedor( solicitudVisitaId );
                    break;
                case EstadoSolicitudVisitaEnum.OBSERVADO:
                    if (oTmp.getCodigoSistemaExterno().isEmpty() || oTmp.getCodigoSistemaExterno() == null){
                        actualizarCancelado( solicitudVisitaId, caso );
                    }else{
                        //CALL SERVICE MENDIX
//                        clientService.wsActualizaVisitaMendix(request);
                        actualizarCancelado( solicitudVisitaId, caso );
                    }
                    notificationService.sendAprobacionVisitaProveedor( solicitudVisitaId );
                    break;
                case EstadoSolicitudVisitaEnum.INICIADO:
                    //CAL SERVICE MENDIX
//                    clientService.wsActualizaVisitaMendix(request);
                    actualizarSuspendido( solicitudVisitaId, caso );
                    notificationService.sendAprobacionVisitaProveedor( solicitudVisitaId );
                    break;
                case EstadoSolicitudVisitaEnum.FINALIZADO:
                    throw new Exception("NO es posible cancelar.");
                case EstadoSolicitudVisitaEnum.CANCELADO:
                    throw new Exception("NO es posible cancelar.");
                case EstadoSolicitudVisitaEnum.NO_ASISTIO:
                    throw new Exception("NO es posible cancelar.");
                case EstadoSolicitudVisitaEnum.SUSPENDIDO:
                    throw new Exception("NO es posible cancelar.");
                case EstadoSolicitudVisitaEnum.FINALIZADO_SIN_REG_SALIDA:
                    throw new Exception("NO es posible cancelar.");
                case EstadoSolicitudVisitaEnum.FINALIZADO_NO_ASISTIO:
                    throw new Exception("NO es posible cancelar.");
                default:
                    break;
            }
            return ResultadoWSActualiza.builder( )
                    .codigo( "OK" )
                    .mensaje( "" )
                    .nroCaso( caso.getNroCaso( ) )
                    .build( );
        } catch( Exception ex ) {
            log.error( ex.getMessage( ) );
            return ResultadoWSActualiza.builder( )
                    .codigo( "KO" )
                    .mensaje( ex.getMessage( ) )
                    .nroCaso( caso.getNroCaso( ) )
                    .build( );
        }
    }

    private void validarEstadoMendixV2(String solicitudVisitaId, Caso caso) {
//        SvSolicitudVisistaEntity oTmp = iSvSolicitudVisistaRepository.findById(solicitudVisitaId).orElse(null);
//        if(oTmp == null)
//            throw  new RuntimeException("No existe visita con el caso: "+ caso.getNroCaso());
//
//        switch( oTmp.getEstadoSolicitudVisitaCode() ) {
//            case EstadoSolicitudVisitaEnum.REV_RECHAZO_VIS:
//                actualizarRechazoPersonalVisitado( solicitudVisitaId, caso );
//                notificationService.sendAprobacionVisitaProveedor( solicitudVisitaId );
//                break;
//            case EstadoSolicitudVisitaEnum.APTA_RECEPCION:
//                actualizarAptoRecepcion( solicitudVisitaId, caso );
//                notificationService.sendAprobacionVisitaSsoQa( solicitudVisitaId );
//                break;
//            case EstadoSolicitudVisitaEnum.OBSERVADO:
//                actualizarObservado( solicitudVisitaId, caso );
//                break;
//            case EstadoSolicitudVisitaEnum.INICIADO:
//                actualizarIniciado( solicitudVisitaId, caso );
//                break;
//            case EstadoSolicitudVisitaEnum.FINALIZADO:
//                actualizarFinalizado( solicitudVisitaId, caso );
//                break;
//            case EstadoSolicitudVisitaEnum.CANCELADO:
////                    actualizarCancelado( solicitudVisitaId, caso );
////                    notificationService.sendCancelacionVisita( solicitudVisitaId );
//                break;
//            case EstadoSolicitudVisitaEnum.SUSPENDIDO:
//                actualizarSuspendido( solicitudVisitaId, caso );
//                notificationService.sendCancelacionVisita( solicitudVisitaId );
//                break;
//            case EstadoSolicitudVisitaEnum.SIN_REG_SALIDA:
//                actualizarSinRegistroSalida( solicitudVisitaId, caso );
//                break;
//            case EstadoSolicitudVisitaEnum.FINALIZADO_SIN_REG_SALIDA:
//                actualizarFinalizadoSinRegistroSalida( solicitudVisitaId, caso );
//                break;
//            case EstadoSolicitudVisitaEnum.FINALIZADO_NO_ASISTIO:
//                actualizarFinalizadoNoAsistio( solicitudVisitaId, caso );
//                break;
//            default:
//                break;
//        }
//
//        actualizarCancelado( solicitudVisitaId, caso );
//        notificationService.sendCancelacionVisita( solicitudVisitaId );
    }



    @Override
    public ResultadoWSActualiza wsActualizaVisitaTercera( WsActualizaVisitaTerceraRequest request ) {
        Caso caso = request.getCaso( );
        String solicitudVisitaId = caso.getNroCaso( );

        try {
            List<SvSolicitudVisistaEntity> oTmp =  iSvSolicitudVisistaRepository.findAllByCodigoSistemaExterno(solicitudVisitaId);
            if(!oTmp.isEmpty()) {
                solicitudVisitaId =  oTmp.get(0).getId( );
            }else{
                throw new Exception("Nro de Caso inexistente");
            }
            EstadoSolicitudVisitaEnum estado =
                    EstadoSolicitudVisitaEnum.fromName(
                            caso.getEstadoDeCaso( )
                    );
//            String estado =caso.getEstadoDeCaso( );

            saveSolicitudVisitaEvento( caso, solicitudVisitaId );
//            iSvSolicitudVisitaHistorialRepository.save(SvSolicitudVisitaHistorialEntity.builder()
//                    .usuario(caso.getUsuario( ) )
//                    .solicitudVisitaId(solicitudVisitaId)
//                    .estadoSolicitudVisitaCode(estado.getCode())
//                    .fechaHora(LocalDateTime.now( ) )
//                    .ambito( caso.getAmbito( ) )
//                    .revision( caso.getRevision( ) )
//                    .rptaByUsuario( caso.getRespuestaDelUsuario( ) )
//                            .isDeleted(false)
//                    .build());
            List<SvAdjuntoDTO> aListMendix = new ArrayList<>();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//            LocalDate date = LocalDateTime.parse(dateStr, formatter).toLocalDate();
            for(DocumentoAdjunto oDoc: caso.getDocumentosAdjuntos().getDocsadjuntos()){
                aListMendix.add(SvAdjuntoDTO.builder()
                                .idRepositorio(oDoc.getId())
                                .clasification("MENDIX")
                                .fechaVencimiento(oDoc.getFechaVencimiento().isBlank()?null:LocalDateTime.parse(oDoc.getFechaVencimiento(), formatter).toLocalDate())
                                .fechaDocumento(oDoc.getFechaDocumento().isBlank()?null:LocalDateTime.parse(oDoc.getFechaDocumento(), formatter).toLocalDate())
                                .ssoComentarioRevision(oDoc.getRevision())
                                .nombreArchivo(oDoc.getNombre())
                                .solicitudVisitaId(solicitudVisitaId)
                                .isDeleted(false)
                        .build());
            }
            iSvAdjuntoCrudService.saveAll(aListMendix);
            switch( estado ) {
                case EstadoSolicitudVisitaEnum.REV_RECHAZO_VIS:
                    actualizarRechazoPersonalVisitado( solicitudVisitaId, caso );
                    notificationService.sendAprobacionVisitaProveedor( solicitudVisitaId );
                    break;
                case EstadoSolicitudVisitaEnum.APTA_RECEPCION:
                    actualizarAptoRecepcion( solicitudVisitaId, caso );
                    notificationService.sendAprobacionVisitaSsoQa( solicitudVisitaId );
                    break;
                case EstadoSolicitudVisitaEnum.OBSERVADO:
                    actualizarObservado( solicitudVisitaId, caso );
                    break;
                case EstadoSolicitudVisitaEnum.INICIADO:
                    actualizarIniciado( solicitudVisitaId, caso );
                    break;
                case EstadoSolicitudVisitaEnum.FINALIZADO:
                    actualizarFinalizado( solicitudVisitaId, caso );
                    break;
                case EstadoSolicitudVisitaEnum.CANCELADO:
                    actualizarCancelado( solicitudVisitaId, caso );
                    notificationService.sendCancelacionVisita( solicitudVisitaId );
                    break;
                case EstadoSolicitudVisitaEnum.SUSPENDIDO:
                    actualizarSuspendido( solicitudVisitaId, caso );
                    break;
                case EstadoSolicitudVisitaEnum.SIN_REG_SALIDA:
                    actualizarSinRegistroSalida( solicitudVisitaId, caso );
                    break;
                case EstadoSolicitudVisitaEnum.FINALIZADO_SIN_REG_SALIDA:
                    actualizarFinalizadoSinRegistroSalida( solicitudVisitaId, caso );
                    break;
                case EstadoSolicitudVisitaEnum.FINALIZADO_NO_ASISTIO:
                    actualizarFinalizadoNoAsistio( solicitudVisitaId, caso );
                    break;
                default:
                    break;
            }
            return ResultadoWSActualiza.builder( )
                    .codigo( "OK" )
                    .mensaje( "" )
                    .nroCaso( caso.getNroCaso( ) )
                    .build( );
        } catch( Exception ex ) {
            log.error( ex.getMessage( ) );
            return ResultadoWSActualiza.builder( )
                    .codigo( "KO" )
                    .mensaje( ex.getMessage( ) )
                    .nroCaso( caso.getNroCaso( ) )
                    .build( );
        }
    }

    @Override
    public List<SvSolicitudVisitaDetalleDTO> saveDetalle(List<SvSolicitudVisitaDetalleDTO> list) {
        return iSvSolicitudVisitaDetalleCrudService.saveAll(list).stream().map(svSolicitudVisitaDetalleMapper::entityToDto).toList();
    }

    private static final DateTimeFormatter FECHA =
            DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private static final DateTimeFormatter HORA =
            DateTimeFormatter.ofPattern("HH:mm");

    public static String toFechaLD(LocalDateTime dt) {
        return dt == null ? null : dt.format(FECHA);
    }

    public static String toFecha(LocalDateTime dt) {
        return dt == null ? null : dt.format(FECHA);
    }

    public static String toHora(LocalDateTime dt) {
        return dt == null ? null : dt.format(HORA);
    }

    @Override
    public GrabaVisitaTerceraRequestDTO getInfoVisitaDto(String sId) {

        ViewSolicitudVisitaReportDTO oVisita = iViewSolicitudVisitaReportQueryService.findById(sId);
        if(!oVisita.getEstadoSsoCode().equals("ST_SSO_0002")|| !oVisita.getEstadoCalidadAmbientalCode().equals("ST_CA_0002"))
            throw new RuntimeException("Visita no disponible para crear en Mendix, SSO y Calidad Ambiental deben estar Aptos.");

        List<SvSolicitudVisitaMaterialEntity> aListMaterial = iSvSolicitudVisitaMaterialRepository.findAllBySolicitudVisitaId(sId);
        List<SvSolicitudVisitaEquipoEntity> aListEquipos = iSvSolicitudVisitaEquipoRepository.findAllBySolicitudVisitaId(sId);
        List<SvSolicitudVisitaDetalleEntity> alist = iSvSolicitudVisitaDetalleRepository.findAllBySolicitudVisitaId(sId);
        //List<EquiposPojo> listId = clientService.getEquipos();
        FilterOperation<String> build = FilterOperation.<String>builder()
                .operator(OperatorEnum.IN)
                .values(alist.stream().map(SvSolicitudVisitaDetalleEntity::getTrabajadorId).toList())
                .build();



        List<ViewTrabajadorReportDTO> aListTrabajadores = iViewTrabajadorReportQueryService.search(PagedRequest.<ViewTrabajadorReportFilter> builder()
                .limit(10000)
                .filter(ViewTrabajadorReportFilter.builder()
                        .id( List.of(build) )
                        .build())
                .build()).getResult();


        System.out.println("trabajadores: -> " + aListTrabajadores.size());
        List<ViewAdjuntoReportDTO> aListAdjuntos = iViewAdjuntoReportQueryService.search(PagedRequest.<ViewAdjuntoReportFilter> builder()
                .limit(10000)
                .filter(ViewAdjuntoReportFilter.builder()
                        .proveedorId( List.of( FilterOperation.<String>builder( )
                                .operator( OperatorEnum.IN )
                                .values(List.of(oVisita.getProveedorId()))
                                .build( ) ) )
                        .solicitudVisitaId(List.of( FilterOperation.<String>builder( )
                                .operator( OperatorEnum.IN )
                                .values(List.of(oVisita.getId()))
                                .build( ) ) )
                        .build())
                .build()).getResult();
        System.out.println("Cantidad: -> " + aListAdjuntos.size());
//        Map<String, Map<String, List<ViewAdjuntoReportDTO>>> adjuntosPorProveedorYClasif =
//                aListAdjuntos.stream()
//                        .filter(a -> a.getProveedorId() != null && a.getClasification() != null)
//                        .collect(Collectors.groupingBy(
//                                ViewAdjuntoReportDTO::get,
//                                Collectors.groupingBy(ViewAdjuntoReportDTO::getClasification)
//                        ));
        Map<String, Map<String, Map<String, List<ViewAdjuntoReportDTO>>>> adjuntosPorProveedorYClasif =
                aListAdjuntos.stream()
                        .filter(a -> a.getProveedorId() != null
                                && a.getClasification() != null
                                && a.getId()!= null)
                        .collect(Collectors.groupingBy(
                                ViewAdjuntoReportDTO::getSolicitudVisitaId,
                                Collectors.groupingBy(
                                        ViewAdjuntoReportDTO::getClasification,
                                        Collectors.groupingBy(
                                                ViewAdjuntoReportDTO::getProveedorId
                                        )
                                )
                        ));
        if (oVisita.getTarDescripciones() == null)
            oVisita.setTarDescripciones("");
        return GrabaVisitaTerceraRequestDTO.builder()
                .VisitaTercera(GrabaVisitaTerceraRequestDTO.VisitaTerceraDTO.builder()
                        .ruc(oVisita.getProveedorNroDocumentoIdentidad())
                        .RazonSocial(oVisita.getProveedorNombreFiscal())
                        .MailContacto(oVisita.getProveedorEmail())
                        .TelefonoContacto(oVisita.getContactoPrincipalTelefono())
                        .Sede(oVisita.getSedeNombre())
                        .FechaInicio(this.toFecha(oVisita.getFechaInicio()))
                        .HoraInicio(this.toHora(oVisita.getFechaInicio()))
                        .FechaFin(this.toFecha(oVisita.getFechaFin()))
                        .HoraFin(this.toHora(oVisita.getFechaFin()))
                        .DescripcionVisita(oVisita.getMotivoVisita())
                        .Visitado(oVisita.getPersonalUsuario())
                        .ObservacionesEspeciales(oVisita.getObservaciones())
                        .OrdenServicio(oVisita.getNroOrdenServicio())
                        .AreasVisita(Arrays.stream(oVisita.getAreasNombres().split(","))
                                .map(String::trim)              // elimina espacios
                                .filter(s -> !s.isBlank())      // evita vacíos
                                .map(nombre ->
                                        GrabaVisitaTerceraRequestDTO.NombreItemDTO.builder()
                                                .Nombre(nombre)
                                                .build()
                                )
                                .collect(Collectors.toList()))
                        .ZonasVisita(Arrays.stream(oVisita.getZonasNombres().split(","))
                                .map(String::trim)              // elimina espacios
                                .filter(s -> !s.isBlank())      // evita vacíos
                                .map(nombre ->
                                        GrabaVisitaTerceraRequestDTO.NombreItemDTO.builder()
                                                .Nombre(nombre)
                                                .build()
                                )
                                .collect(Collectors.toList()))
                        .Materiales(aListMaterial.stream().map(svSolicitudVisitaMaterialEntity ->
                            GrabaVisitaTerceraRequestDTO.MaterialDTO.builder()
                                    .Material(svSolicitudVisitaMaterialEntity.getDescripcion())
                                    .Cantidad(svSolicitudVisitaMaterialEntity.getCantidad().intValue()+"")
                                    .Unidad(svSolicitudVisitaMaterialEntity.getUnidadMedida())
                                    .build()
                        ).toList())
                        .Equipos(aListEquipos.stream().map(svSolicitudVisitaEquipoEntity ->
                                GrabaVisitaTerceraRequestDTO.EquipoDTO.builder()
                                        .CodigoTipoEquipo(svSolicitudVisitaEquipoEntity.getCodigoEquipo())
                                        .Equipo(svSolicitudVisitaEquipoEntity.getTipoEquipo())
                                        .Cantidad(svSolicitudVisitaEquipoEntity.getCantidad().intValue()+"")
                                        .Serie(svSolicitudVisitaEquipoEntity.getNumeroSerie())
                                        .build()
                                ).toList())
                        .DocumentosProveedorSSO(adjuntosPorProveedorYClasif
                                .getOrDefault(oVisita.getId(), Map.of())
                                .getOrDefault("Cita SSO", Map.of())
                                .getOrDefault(oVisita.getProveedorId(), List.of())   // tercera llave
                                .stream()
                                .map(a -> GrabaVisitaTerceraRequestDTO.DocumentoProveedorDTO.builder()
                                        .ID(a.getIdRepositorio())
                                        .Ambito("SSO")
                                        .TipoDocumento(a.getTdpDescripcion())
                                        .Nombre(a.getNombreArchivo())
                                        .FechaDocumento(a.getFechaDocumento() == null ? "":a.getFechaDocumento().format(FECHA))
                                        .FechaVencimiento(a.getFechaVencimiento()== null? "": a.getFechaVencimiento().format(FECHA))
                                        .Revision(a.getSsoComentarioRevision() == null ? "" :a.getSsoComentarioRevision())
                                        .build())
                                .toList())
                        .DocumentosProveedorCA(adjuntosPorProveedorYClasif
                                .getOrDefault(oVisita.getId(), Map.of())
                                .getOrDefault("Cita QA", Map.of())
                                .getOrDefault(oVisita.getProveedorId(), List.of())   // tercera llave
                                .stream()
                                .map(a -> GrabaVisitaTerceraRequestDTO.DocumentoProveedorDTO.builder()
                                        .ID(a.getIdRepositorio())
                                        .Ambito("Calidad Ambiental")
                                        .TipoDocumento(a.getTdpDescripcion())
                                        .Nombre(a.getNombreArchivo())
                                        .FechaDocumento(a.getFechaDocumento() == null ? "":a.getFechaDocumento().format(FECHA))
                                        .FechaVencimiento(a.getFechaVencimiento()== null? "": a.getFechaVencimiento().format(FECHA))
                                        .Revision(a.getCaComentarioRevision() == null ? "" :a.getCaComentarioRevision())
                                        .build())
                                .toList())
                        .ActividadAltoRiesgoProveedor(oVisita.getIndActividadAltoRiesgo() ? "Si":"No")
                        .AltoRiesgoProveedor(Arrays.stream(oVisita.getTarDescripciones().split(","))
                                .map(String::trim)              // elimina espacios
                                .filter(s -> !s.isBlank())      // evita vacíos
                                .map(nombre ->
                                        GrabaVisitaTerceraRequestDTO.AltoRiesgoProveedorDTO.builder()
                                                .ARProveedor(nombre)
                                                .build()
                                )
                                .collect(Collectors.toList()))
                        .DocumentosSSOProveedorAltoRiesgo(adjuntosPorProveedorYClasif
                                .getOrDefault(oVisita.getId(), Map.of())
                                .getOrDefault("Cita AAR", Map.of())
                                .getOrDefault(oVisita.getProveedorId(), List.of())   // tercera llave
                                .stream()
                                .map(a -> GrabaVisitaTerceraRequestDTO.DocumentoProveedorDTO.builder()
                                        .ID(a.getIdRepositorio())
                                        .Ambito("SSO")
                                        .TipoDocumento(a.getTdpDescripcion())
                                        .Nombre(a.getNombreArchivo())
                                        .FechaDocumento(a.getFechaDocumento() == null ? "":a.getFechaDocumento().format(FECHA))
                                        .FechaVencimiento(a.getFechaVencimiento()== null? "": a.getFechaVencimiento().format(FECHA))
                                        .Revision(a.getSsoComentarioRevision() == null ? "" :a.getSsoComentarioRevision())
                                        .build())
                                .toList())
                        .ActividadABordoProveedor(oVisita.getIndActividadABordo() ? "Si":"No")
                        .DocumentosABordoProveedor(adjuntosPorProveedorYClasif
                                .getOrDefault(oVisita.getId(), Map.of())
                                .getOrDefault("Cita AB", Map.of())
                                .getOrDefault(oVisita.getProveedorId(), List.of())   // tercera llave
                                .stream()
                                .map(a -> GrabaVisitaTerceraRequestDTO.DocumentoProveedorDTO.builder()
                                        .ID(a.getIdRepositorio())
                                        .Ambito("SSO")
                                        .TipoDocumento(a.getTdpDescripcion())
                                        .Nombre(a.getNombreArchivo())
                                        .FechaDocumento(a.getFechaDocumento() == null ? "":a.getFechaDocumento().format(FECHA))
                                        .FechaVencimiento(a.getFechaVencimiento()== null? "": a.getFechaVencimiento().format(FECHA))
                                        .Revision(a.getSsoComentarioRevision() == null ? "" :a.getSsoComentarioRevision())
                                        .build())
                                .toList())
                        .NecesitaAndamios(oVisita.getIndRequiereAndamios() ? "Si":"No")
                        .DocumentosAndamiosProveedor(adjuntosPorProveedorYClasif
                                .getOrDefault(oVisita.getId(), Map.of())
                                .getOrDefault("Cita NA", Map.of())
                                .getOrDefault(oVisita.getProveedorId(), List.of())   // tercera llave
                                .stream()
                                .map(a -> GrabaVisitaTerceraRequestDTO.DocumentoProveedorDTO.builder()
                                        .ID(a.getIdRepositorio())
                                        .Ambito("SSO")
                                        .TipoDocumento(a.getTdpDescripcion())
                                        .Nombre(a.getNombreArchivo())
                                        .FechaDocumento(a.getFechaDocumento() == null ? "":a.getFechaDocumento().format(FECHA))
                                        .FechaVencimiento(a.getFechaVencimiento()== null? "": a.getFechaVencimiento().format(FECHA))
                                        .Revision(a.getSsoComentarioRevision() == null ? "" :a.getSsoComentarioRevision())
                                        .build())
                                .toList())
                        .NecesitaGrua(oVisita.getIndRequiereGrua() ? "Si":"No")
                        .DocumentosGruaProveedor(adjuntosPorProveedorYClasif
                                .getOrDefault(oVisita.getId(), Map.of())
                                .getOrDefault("Cita NG", Map.of())
                                .getOrDefault(oVisita.getProveedorId(), List.of())   // tercera llave
                                .stream()
                                .map(a -> GrabaVisitaTerceraRequestDTO.DocumentoProveedorDTO.builder()
                                        .ID(a.getIdRepositorio())
                                        .Ambito("SSO")
                                        .TipoDocumento(a.getTdpDescripcion())
                                        .Nombre(a.getNombreArchivo())
                                        .FechaDocumento(a.getFechaDocumento() == null ? "":a.getFechaDocumento().format(FECHA))
                                        .FechaVencimiento(a.getFechaVencimiento()== null? "": a.getFechaVencimiento().format(FECHA))
                                        .Revision(a.getSsoComentarioRevision() == null ? "" :a.getSsoComentarioRevision())
                                        .build())
                                .toList())
                        .NecesitaEquiposMoviles(oVisita.getIndRequiereEquiposMoviles() ? "Si":"No")
                        .DocumentosEquiposMoviles(adjuntosPorProveedorYClasif
                                .getOrDefault(oVisita.getId(), Map.of())
                                .getOrDefault("Cita EM", Map.of())
                                .getOrDefault(oVisita.getProveedorId(), List.of())   // tercera llave
                                .stream()
                                .map(a -> GrabaVisitaTerceraRequestDTO.DocumentoProveedorDTO.builder()
                                        .ID(a.getIdRepositorio())
                                        .Ambito("SSO")
                                        .TipoDocumento(a.getTdpDescripcion())
                                        .Nombre(a.getNombreArchivo())
                                        .FechaDocumento(a.getFechaDocumento() == null ? "":a.getFechaDocumento().format(FECHA))
                                        .FechaVencimiento(a.getFechaVencimiento()== null? "": a.getFechaVencimiento().format(FECHA))
                                        .Revision(a.getSsoComentarioRevision() == null ? "" :a.getSsoComentarioRevision())
                                        .build())
                                .toList())
                        .NecesitaTrabajoBuceo(oVisita.getIndTrabajoBuceo() ? "Si":"No")
                        .DocumentosTrabajoBuceo(adjuntosPorProveedorYClasif
                                .getOrDefault(oVisita.getId(), Map.of())
                                .getOrDefault("Cita TB", Map.of())
                                .getOrDefault(oVisita.getProveedorId(), List.of())   // tercera llave
                                .stream()
                                .map(a -> GrabaVisitaTerceraRequestDTO.DocumentoProveedorDTO.builder()
                                        .ID(a.getIdRepositorio())
                                        .Ambito("SSO")
                                        .TipoDocumento(a.getTdpDescripcion())
                                        .Nombre(a.getNombreArchivo())
                                        .FechaDocumento(a.getFechaDocumento() == null ? "":a.getFechaDocumento().format(FECHA))
                                        .FechaVencimiento(a.getFechaVencimiento()== null? "": a.getFechaVencimiento().format(FECHA))
                                        .Revision(a.getSsoComentarioRevision() == null ? "" :a.getSsoComentarioRevision())
                                        .build())
                                .toList())
//                        .IntegranteVisitaTercera(List.of(GrabaVisitaTerceraRequestDTO.IntegranteVisitaTerceraDTO.builder()
//                                        .TipoDocumento("DNI")
//                                        .NumeroDocumento("09495877")
//                                        .Nombres("JOSE MASCARO JACOME")
//                                        .Observaciones("Prueba")
//                                .build()))
                        .IntegranteVisitaTercera(aListTrabajadores.stream().map( dto ->
                                        GrabaVisitaTerceraRequestDTO.IntegranteVisitaTerceraDTO.builder()
                                                .TipoDocumento(dto.getTdiNombre())
                                                .NumeroDocumento(dto.getNroDocumentoIdentidad())
                                                .Nombres(dto.getNombre())
                                                .Observaciones("")
                                                .build()
                                ).toList())
                        .build())

                .build();
    }

    @Override
    public SvSolicitudVisistaEntity updateIdMendix(String sIdVisita, String sIdMendix) {
//        iSvSolicitudVisistaRepository.updateMendix(sIdVisita, sIdMendix);
        SvSolicitudVisistaEntity oTmp = iSvSolicitudVisistaRepository.findById(sIdVisita).get();
        oTmp.setCodigoSistemaExterno(sIdMendix);
        oTmp.setEstadoSolicitudVisitaCode("ST_SV_0003");
        iSvSolicitudVisistaRepository.save(oTmp);
        return iSvSolicitudVisistaRepository.save(oTmp);
    }



    private void actualizarRechazoPersonalVisitado( String solicitudVisitaId, Caso caso ) {
        actualizarEstadoSolicitudVisita( solicitudVisitaId, EstadoSolicitudVisitaEnum.REV_RECHAZO_VIS, caso );

        iSvSolicitudVisistaRepository.updateEstadoSSOCode( EstadoSSOEnum.PRELIMINAR.getCode( ), solicitudVisitaId );
        iSvSolicitudVisistaRepository.updateSSOMotivo( "", solicitudVisitaId );
        iSvSolicitudVisistaRepository.updateSSODescargo( "", solicitudVisitaId );

        iSvSolicitudVisistaRepository.updateEstadoCalidadAmbientalCode( EstadoCalidadAmbientalEnum.PRELIMINAR.getCode( ), solicitudVisitaId );
        iSvSolicitudVisistaRepository.updateCAMotivo( "", solicitudVisitaId );
        iSvSolicitudVisistaRepository.updateCADescargo( "", solicitudVisitaId );
    }

    private void actualizarAptoRecepcion( String solicitudVisitaId, Caso caso ) {
        actualizarEstadoSolicitudVisita( solicitudVisitaId, EstadoSolicitudVisitaEnum.APTA_RECEPCION, caso );
    }

    private void actualizarObservado( String solicitudVisitaId, Caso caso ) {
        actualizarEstadoSolicitudVisita( solicitudVisitaId, EstadoSolicitudVisitaEnum.OBSERVADO, caso );

    }

    private void actualizarIniciado( String solicitudVisitaId, Caso caso ) {
        actualizarEstadoSolicitudVisita( solicitudVisitaId, EstadoSolicitudVisitaEnum.INICIADO, caso );
    }

    private void actualizarFinalizado( String solicitudVisitaId, Caso caso ) {
        actualizarEstadoSolicitudVisita( solicitudVisitaId, EstadoSolicitudVisitaEnum.FINALIZADO, caso );
    }

    private void actualizarCancelado( String solicitudVisitaId, Caso caso ) {
        actualizarEstadoSolicitudVisita( solicitudVisitaId, EstadoSolicitudVisitaEnum.CANCELADO, caso );
    }

    private void actualizarSuspendido( String solicitudVisitaId, Caso caso ) {
        actualizarEstadoSolicitudVisita( solicitudVisitaId, EstadoSolicitudVisitaEnum.SUSPENDIDO, caso );
    }

    private void actualizarSinRegistroSalida( String solicitudVisitaId, Caso caso ) {
        actualizarEstadoSolicitudVisita( solicitudVisitaId, EstadoSolicitudVisitaEnum.SIN_REG_SALIDA, caso );
    }

    private void actualizarFinalizadoSinRegistroSalida( String solicitudVisitaId, Caso caso ) {
        actualizarEstadoSolicitudVisita( solicitudVisitaId, EstadoSolicitudVisitaEnum.FINALIZADO_SIN_REG_SALIDA, caso );
    }

    private void actualizarFinalizadoNoAsistio( String solicitudVisitaId, Caso caso ) {
        actualizarEstadoSolicitudVisita( solicitudVisitaId, EstadoSolicitudVisitaEnum.FINALIZADO_NO_ASISTIO, caso );
    }

    private void actualizarEstadoSolicitudVisita( String solicitudVisitaId, EstadoSolicitudVisitaEnum estado, Caso caso ) {


        iSvSolicitudVisistaRepository.updateEstadoSolicitudVisitaCode(
                estado.getCode( ),
                solicitudVisitaId
        );

        iSvSolicitudVisitaHistorialCrudService.save(
                SvSolicitudVisitaHistorialDTO.builder( )
                        .solicitudVisitaId( solicitudVisitaId )
                        .estadoSolicitudVisitaCode( estado.getCode( ) )
                        .fechaHora( LocalDateTime.now( ) )
                        .usuario( caso.getUsuario( ) )
                        .ambito( caso.getAmbito( ) )
                        .revision( caso.getRevision( ) )
                        .rptaByUsuario( caso.getRespuestaDelUsuario( ) )
                        .isDeleted( false )
                        .build( )
        );
//        for ()
    }

    private void saveSolicitudVisitaEvento( Caso caso, String solicitudVisitaId ) {
        System.out.println(DateTimeUtil.asLocalDateTimev2(caso.getFechaAccion()));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

//        LocalDateTime dateTime = LocalDateTime.parse(dateStr, formatter);
        SvSolicitudVisitaEventoDTO eventoDTO = SvSolicitudVisitaEventoDTO.builder( )
//                .fechaEvento( DateTimeUtil.asLocalDateTimev2( caso.getFechaAccion( ) ) )
                .fechaEvento(LocalDateTime.parse(caso.getFechaAccion( ), formatter))
                .usuario( caso.getUsuario( ) )
                .evento( caso.getNroCaso( ) )
                .ambito( caso.getAmbito( ) )
                .revision( caso.getRevision( ) )
                .respuestaUsuario( caso.getRespuestaDelUsuario( ) )
                .solicitudVisitaId( solicitudVisitaId )
                .isDeleted( false )
                .build( );

        iSvSolicitudVisitaEventoCrudService.save( eventoDTO );
    }

}
