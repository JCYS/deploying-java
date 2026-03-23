package com.copeinca.apicopeincaprov.modules.notificacion.service.imp;

import com.copeinca.apicopeincaprov.commons.models.FilterOperation;
import com.copeinca.apicopeincaprov.commons.models.OperatorEnum;
import com.copeinca.apicopeincaprov.global.dtos.input.SortDirection;
import com.copeinca.apicopeincaprov.global.dtos.input.SortField;
import com.copeinca.apicopeincaprov.global.dtos.response.CustomMessageView;
import com.copeinca.apicopeincaprov.global.dtos.response.MessageViewModel;
import com.copeinca.apicopeincaprov.global.dtos.response.PagedRequest;
import com.copeinca.apicopeincaprov.global.util.DateTimeUtil;
import com.copeinca.apicopeincaprov.integration.mail.smtp.models.custom.NotifyInput;
import com.copeinca.apicopeincaprov.integration.mail.smtp.models.enums.EventNotifyEnum;
import com.copeinca.apicopeincaprov.integration.mail.smtp.services.interfaces.IEmailSenderService;
import com.copeinca.apicopeincaprov.modules.core.models.dto.*;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.SvSolicitudVisitaDetalleFilter;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.SvUsuarioRolSedeFilter;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.ViewSolicitudVisitaReportFilter;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.ViewTrabajadorReportFilter;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvProveedorEntity;
import com.copeinca.apicopeincaprov.modules.core.repositories.jpa.ISvProveedorRepository;
import com.copeinca.apicopeincaprov.modules.core.service.query.*;
import com.copeinca.apicopeincaprov.modules.notificacion.models.dto.*;
import com.copeinca.apicopeincaprov.modules.notificacion.service.INotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationService implements INotificationService {

    @Value( "${portal.url}" )
    private String portalUrl;

    @Value( "${com.innovax.isLocal}" )
    private Boolean isLocal;

    private final IEmailSenderService emailSenderService;
    private final ISvProveedorRepository  svProveedorRepository;
    private final ISvProveedorQueryService iProveedorQueryService;
    private final IViewProveedorReportQueryService iViewProveedorReportQueryService;
    private final IViewTrabajadorReportQueryService iViewTrabajadorReportQueryService;
    private final ISvSolicitudVisitaZonaQueryService iSvSolicitudVisitaZonaQueryService;
    private final ISvSolicitudVisitaDetalleQueryService iSvSolicitudVisitaDetalleQueryService;
    private final IViewSolicitudVisitaReportQueryService iViewSolicitudVisitaReportQueryService;
    private final ISvSolicitudVisitaAreaAutorizadoraQueryService iSolicitudVisitaAreaAutorizadoraQueryService;

    private final ISvUsuarioRolSedeQueryService iSvUsuarioRolSedeQueryService;

    @Override
    public void sendMail( NotifyInput input ) {
        emailSenderService.send( input );
    }

    @Override
    public void sendCreacionProveedor( String proveedorId ) {

        //--- Obtenemos información del proveedor
        SvProveedorDTO proveedor = iProveedorQueryService.findById( proveedorId );

        //--- Construimos el DTO de notificación
        NotificacionCreacionProveedorDTO dto = NotificacionCreacionProveedorDTO.builder( )
                .recipients( proveedor.getEmail( ) )
//                .recipients( "ranulfillo369@gmail.com" ) //--- TODO: Eliminar para calidad y PRD
//                .recipients( isLocal ? "jescudero@csticorp.biz": "ldiaz@copeinca.com.pe" ) //--- TODO: Eliminar para calidad y PRD
                .companyName( proveedor.getNombreFiscal( ) )
                .username( proveedor.getEmail( ) )
                .build( );

        sendCreacionProveedor( dto );

    }
    public void sendHabilitarProveedor( NotificacionCreacionProveedorDTO dto ) {
        Map<String, Object> data = new HashMap<>( );
        data.put( "company_name", dto.getCompanyName( ) );
        data.put( "username", dto.getUsername( ) );
        data.put( "url_btp", portalUrl );

        NotifyInput input = NotifyInput.builder( )
                .to( dto.getRecipients( ) )
//                .cc("ldiaz@copeinca.com.pe")
                //.cc( isLocal ? "123456789@yopmail.com": "ldiaz@copeinca.com.pe")
                .eventNotifyEnum( EventNotifyEnum.CTPX_HABILITAR_PROVEEDOR )
                .data( data )
                .build( );

        emailSenderService.send( input );
    }
    @Override
    public void sendCreacionProveedor( NotificacionCreacionProveedorDTO dto ) {
        Map<String, Object> data = new HashMap<>( );
        data.put( "company_name", dto.getCompanyName( ) );
        data.put( "username", dto.getUsername( ) );
        data.put( "url_btp", portalUrl );

        NotifyInput input = NotifyInput.builder( )
                .to( dto.getRecipients( ) )
                .cc("")
                //.cc( isLocal ? "123456789@yopmail.com": "ldiaz@copeinca.com.pe")
                .eventNotifyEnum( EventNotifyEnum.CTP2_CREACION_PROVEEDOR )
                .data( data )
                .build( );

        emailSenderService.send( input );
    }

    @Override
    public void sendEvaluacionProveedor( NotificacionEvaluacionProveedorDTO dto ) {
        Map<String, Object> data = new HashMap<>( );
        data.put( "company_name", dto.getCompanyName( ) );
        data.put( "username", dto.getUsername( ) );
        data.put( "url_btp", dto.getUrlBtp( ) );
        data.put( "status_sso", dto.getStatusSso( ) );
        data.put( "reason_sso", dto.getReasonSso( ) );
        data.put( "status_environmental_quality", dto.getStatusEnvironmentalQuality( ) );
        data.put( "reason_environmental_quality", dto.getReasonEnvironmentalQuality( ) );
        data.put( "allowed_sites", dto.getAllowedSites( ) );
        data.put( "sso_docs_count", dto.getSsoDocsCount( ) );
        data.put( "high_risk", dto.getHighRisk( ) );
        data.put( "high_risk_docs_count", dto.getHighRiskDocsCount( ) );
        data.put( "onboard", dto.getOnboard( ) );
        data.put( "onboard_docs_count", dto.getOnboardDocsCount( ) );
        data.put( "environmental_docs_count", dto.getEnvironmentalDocsCount( ) );
        data.put( "workers", dto.getWorkers( ) );
        data.put( "has_vigencia_sso", dto.getHas_vigencia_sso() );
        data.put( "date_vigencia_sso", dto.getDate_has_vigencia_sso() );
        data.put( "has_vigencia_ca", dto.getHas_vigencia_ca() );
        data.put( "date_vigencia_ca", dto.getDate_has_vigencia_ca() );

        if("SSO".equals(dto.getRolUser())){
            data.put( "rol_mensaje", "por parte de Seguridad Ocupacional");
        } else if("CA".equals(dto.getRolUser())){
            data.put( "rol_mensaje", "por parte de Calidad Ambiental");
        }else{
            data.put( "rol_mensaje", " ");
        }
        data.put( "workers", dto.getWorkers( ) );

        NotifyInput input = NotifyInput.builder( )
                .to( dto.getRecipients( ) )
                .cc("")
                .eventNotifyEnum( EventNotifyEnum.CTP3_CTP4_EVALUACION_PROVEEDOR )
                .data( data )
                .build( );

        emailSenderService.send( input );
    }

    @Override
    public void sendEvaluacionProveedor( String proveedorId ) {

        //--- Obtenemos información del proveedor
        ViewProveedorReportDTO proveedor = iViewProveedorReportQueryService.findById( proveedorId );

        //--- Obtenemos información de los workers
        List<ViewTrabajadorReportDTO> trabajadores = iViewTrabajadorReportQueryService.search( PagedRequest.<ViewTrabajadorReportFilter>builder( )
                .filter( ViewTrabajadorReportFilter.builder( )
                        .proveedorId( List.of( FilterOperation.<String>builder( )
                                .operator( OperatorEnum.EQ )
                                .values( List.of( proveedorId ) )
                                .build( ) ) )
                        .build( ) )
                .build( ) ).getResult( );

        //--- Transformamos los datos
        List<TrabajadorEmailDTO> workers = trabajadores.stream( )
                .map( t -> TrabajadorEmailDTO.builder( )
                        .fullName( t.getNombre( ) )
                        .reasonSso( t.getSsoMotivo( ) )
                        .dni( t.getNroDocumentoIdentidad( ) )
                        .statusSso( t.getSsoEstadoNombre( ) )
                        .docsCount( t.getNDocsSsoAdjuntos( ) + "" )
                        .highRisk( t.getIndTrabajoAltoRiesgo( ) ? "Sí" : "No" )
                        .highRiskDocsCount( t.getNDocsAarAdjuntos( ) + "" )
                        .preventionist( t.getIndPrevencionista( ) ? "Sí" : "No" )
                        .preventionistDocsCount( t.getNDocsPrevAdjuntos( ) + "" )
                        .build( ) )
                .toList( );

        //--- Construimos el DTO de notificación
        NotificacionEvaluacionProveedorDTO dto = NotificacionEvaluacionProveedorDTO.builder( )
                .recipients( proveedor.getEmail( ) )
//                .recipients( "ranulfillo369@gmail.com" ) //--- TODO: Eliminar para calidad y PRD
//                .recipients( isLocal ? "jescudero@csticorp.biz,123456789@yopmail.com": "ldiaz@copeinca.com.pe,jescudero@csticorp.biz" ) //--- TODO: Eliminar para calidad y PRD
                .companyName( proveedor.getNombreFiscal( ) )
                .username( proveedor.getEmail( ) )
                .statusSso( proveedor.getSsoEstadoNombre() )
                .reasonSso( proveedor.getSsoMotivo( ) )
                .statusEnvironmentalQuality( proveedor.getCaEstadoNombre() )
                .reasonEnvironmentalQuality( proveedor.getCaMotivo( ) )
                .workers( workers )
                .urlBtp( portalUrl )
                .build( );

        //--- Enviamos la notificación
        sendEvaluacionProveedor( dto );

    }

    @Override
    public void sendModificacionDatos( NotificacionModificacionDatosDTO dto ) {
        Map<String, Object> data = new HashMap<>( );
        data.put( "company_name", dto.getCompanyName( ) );
        data.put( "allowed_sites", dto.getAllowedSites( ) );
        data.put( "status_sso", dto.getStatusSso( ) );
        data.put( "sso_docs_count", dto.getSsoDocsCount( ) );
        data.put( "high_risk", dto.getHighRisk( ) );
        data.put( "high_risk_docs_count", dto.getHighRiskDocsCount( ) );
        data.put( "onboard", dto.getOnboard( ) );
        data.put( "onboard_docs_count", dto.getOnboardDocsCount( ) );
        data.put( "status_environmental_quality", dto.getStatusEnvironmentalQuality( ) );
        data.put( "environmental_docs_count", dto.getEnvironmentalDocsCount( ) );
        data.put( "workers", dto.getWorkers( ) );
        data.put( "url_btp", dto.getUrlBtp( ) );
        data.put( "has_vigencia_sso", dto.getHas_vigencia_sso() );
        data.put( "date_vigencia_sso", dto.getDate_has_vigencia_sso() );
        data.put( "has_vigencia_ca", dto.getHas_vigencia_ca() );
        data.put( "date_vigencia_ca", dto.getDate_has_vigencia_ca() );

        NotifyInput input = NotifyInput.builder( )
                .to( dto.getRecipients( ) )
                .cc("")
                .eventNotifyEnum( EventNotifyEnum.CTP8_MODIFICACION_DATOS )
                .data( data )
                .build( );

        emailSenderService.send( input );
    }

    @Override
    public void sendModificacionDatos( String proveedorId ) {

        ViewProveedorReportDTO proveedor = iViewProveedorReportQueryService.findById( proveedorId );
        SvProveedorEntity update =  svProveedorRepository.findById( proveedor.getId( ) ).get();
        //ArrayList<String> filterRoles = new ArrayList<>();
        List<String> filterRoles = new ArrayList<>();
        if(proveedor.getNotificaCa() == null && proveedor.getNotificaSso() == null)
            throw new RuntimeException("No es posible notificar, sin cambios en parametros notifica CA y SSO.");
        if(!proveedor.getNotificaCa()) {
            filterRoles.add("controlprov_amb");
            update.setNotificaCa(true);
        }
        if(!proveedor.getNotificaSso()) {
            update.setNotificaSso(true);
            filterRoles.add("controlprov_sso");
        }

        List<SvUsuarioRolSedeDTO> result = iSvUsuarioRolSedeQueryService.search(PagedRequest.<SvUsuarioRolSedeFilter>builder()
                        .filter(SvUsuarioRolSedeFilter.builder()
                                .sedeIdIds(Arrays.stream(proveedor.getSedesIds().split(",")).toList())
//                                .sedeIdIds(List.of("ef6f00b1-7893-45c0-bc46-46e609ae12e1","ef6f00b1-7893-45c0-bc46-46e609ae12e1"))
//                                .rolCodeIds(List.of("controlprov_sso","controlprov_amb"))
                                .rolCodeIds(filterRoles.stream().toList())
                                .build())
                .build()).getResult();
        if(result.isEmpty())
            throw new RuntimeException("No existe usuario SSO o Calidad en sedes: " +proveedor.getSedesNombres());
        String emailsSSO = result.stream()
                .filter(p -> "controlprov_sso".equals(p.getRolCode()))
                .distinct()
                .map(SvUsuarioRolSedeDTO::getEmailUser)
                .collect(Collectors.joining(","));
        String emailsCA = result.stream()
                .filter(p -> "controlprov_amb".equals(p.getRolCode()))
                .distinct()
                .map(SvUsuarioRolSedeDTO::getEmailUser)
                .collect(Collectors.joining(","));
        String recipientsEmail = "";
        if(!proveedor.getNotificaCa())
            recipientsEmail = recipientsEmail + emailsCA;
        if(!proveedor.getNotificaSso())
            recipientsEmail = recipientsEmail + ","+emailsSSO;



        List<ViewTrabajadorReportDTO> trabajadores = iViewTrabajadorReportQueryService.search( PagedRequest.<ViewTrabajadorReportFilter>builder( )
                .filter( ViewTrabajadorReportFilter.builder( )
                        .proveedorId( List.of( FilterOperation.<String>builder( )
                                .operator( OperatorEnum.EQ )
                                .values( List.of( proveedorId ) )
                                .build( ) ) )
                        .build( ) )
                        .sort(List.of(SortField.builder()
                                        .field("ssoEstadoNombre")
                                        .direction(SortDirection.ASC)
                                .build(),
                                SortField.builder()
                                        .field("nombre")
                                        .direction(SortDirection.ASC)
                                        .build()))
                .build( ) ).getResult( );

        List<TrabajadorEmailDTO> workers = trabajadores.stream( )
                .map( t -> TrabajadorEmailDTO.builder( )
                        .fullName( t.getNombre( ) )
                        .reasonSso( t.getSsoMotivo( ) )
                        .dni( t.getNroDocumentoIdentidad( ) )
                        .statusSso( t.getSsoEstadoNombre( ) )
                        .docsCount( t.getNDocsSsoAdjuntos( ) + "" )
                        .highRisk( t.getIndTrabajoAltoRiesgo( ) ? "Sí" : "No" )
                        .highRiskDocsCount( t.getNDocsAarAdjuntos( ) + "" )
                        .preventionist( t.getIndPrevencionista( ) ? "Sí" : "No" )
                        .preventionistDocsCount( t.getNDocsPrevAdjuntos( ) + "" )
                        .build( ) )
                .toList( );
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        //String formatted = date.format(formatter);
        NotificacionModificacionDatosDTO dto = NotificacionModificacionDatosDTO.builder( )
//                .recipients( "ranulfillo369@gmail.com" )
                .recipients(recipientsEmail )
                .companyName( proveedor.getNombreFiscal( ) )
                .allowedSites( proveedor.getSedesNombres( ) )
                .statusSso( proveedor.getSsoEstadoNombre( ) )
//                .ssoDocsCount( proveedor.getNDocsProvSso( ) + "" )
                .ssoDocsCount( proveedor.getNDocsProvSsoValid( ) + "" )
                .highRisk( proveedor.getIndRealizaActividadAltoRiesgo( ) ? "Sí" : "No" )
//                .highRiskDocsCount( proveedor.getNDocsAar() + "" )
                .highRiskDocsCount( proveedor.getNDocsAarValid() + "" )
                .onboard( proveedor.getIndRealizaActividadABordo( ) ? "Sí" : "No" )
//                .onboardDocsCount( proveedor.getNDocsAb()+ "" )
                .onboardDocsCount( proveedor.getNDocsAbValid()+ "" )
                .statusEnvironmentalQuality( proveedor.getCaEstadoNombre() )
//                .environmentalDocsCount( proveedor.getNDocsCa( ) + "" )
                .environmentalDocsCount( proveedor.getNDocsCaValid( ) + "" )
                .workers( workers )
                .urlBtp( portalUrl )
                .has_vigencia_sso(proveedor.getSsoTieneVigencia() ? "Sí" : "No")
                .has_vigencia_ca(proveedor.getCaTieneVigencia() ? "Sí" : "No")
                .date_has_vigencia_sso(proveedor.getSsoFechaFinVigencia())
                .date_has_vigencia_ca(proveedor.getCaFechaFinVigencia() == null ? "" : proveedor.getCaFechaFinVigencia().format(formatter))
                .build( );
        svProveedorRepository.save(update);
        sendModificacionDatos( dto );

    }

    @Override
    public void sendSolicitudVisita( NotificacionSolicitudVisitaDTO dto ) {
        Map<String, Object> data = new HashMap<>( );
        data.put( "company_name", dto.getCompanyName( ) );
        data.put( "site", dto.getSite( ) );
        data.put( "visited_user", dto.getVisitedUser( ) );
        data.put( "start_date_time", dto.getStartDateTime( ) );
        data.put( "end_date_time", dto.getEndDateTime( ) );
        data.put( "service_order", dto.getServiceOrder( ) );
        data.put( "visit_description", dto.getVisitDescription( ) );
        data.put( "special_observations", dto.getSpecialObservations( ) );
        data.put( "areas_to_visit", dto.getAreasToVisit( ) );
        data.put( "zones_to_visit", dto.getZonesToVisit( ) );
        data.put( "high_risk", dto.getHighRisk( ) );
        data.put( "onboard", dto.getOnboard( ) );
        data.put( "scaffolding", dto.getScaffolding( ) );
        data.put( "crane", dto.getCrane( ) );
        data.put( "mobile_equipment", dto.getMobileEquipment( ) );
        data.put( "diving_work", dto.getDivingWork( ) );
        data.put( "workers", dto.getWorkers( ) );
        data.put( "url_btp", dto.getUrlBtp( ) );
        data.put( "cod_visita", dto.getCodVisita() );

        NotifyInput input = NotifyInput.builder( )
                .to( dto.getRecipients( ) )
                .cc("")
                .eventNotifyEnum( EventNotifyEnum.SVT1_SVT4_SOLICITUD_VISITA )
                .data( data )
                .build( );

        emailSenderService.send( input );
    }

    @Override
    public void sendSolicitudVisita( String solicitudVisitaId ) {

        ViewSolicitudVisitaReportDTO solicitudVisita = iViewSolicitudVisitaReportQueryService.findById( solicitudVisitaId );

        ViewProveedorReportDTO proveedor = iViewProveedorReportQueryService.findById( solicitudVisita.getProveedorId( ) );

        List<SvSolicitudVisitaDetalleDTO> detalleList = iSvSolicitudVisitaDetalleQueryService.search( buildPagedRequestSolicitudVisitaDetalle( solicitudVisitaId ) ).getResult( );
        List<String> filterRoles = new ArrayList<>();
        filterRoles.add( "controlprov_amb" );
        filterRoles.add( "controlprov_sso" );

        List<SvUsuarioRolSedeDTO> result = iSvUsuarioRolSedeQueryService.search(PagedRequest.<SvUsuarioRolSedeFilter>builder()
                .filter(SvUsuarioRolSedeFilter.builder()
                        .sedeIdIds(List.of(solicitudVisita.getSedeId()))
//                                .sedeIdIds(List.of("ef6f00b1-7893-45c0-bc46-46e609ae12e1","ef6f00b1-7893-45c0-bc46-46e609ae12e1"))
//                                .rolCodeIds(List.of("controlprov_sso","controlprov_amb"))
                        .rolCodeIds(filterRoles.stream().toList())
                        .build())
                .build()).getResult();
//        if(result.isEmpty())
//            throw new RuntimeException("No existe usuario SSO o Calidad en sedes: " +proveedor.getSedesNombres());
        String recipientsEmail = result.stream()
                .distinct()
                .map(SvUsuarioRolSedeDTO::getEmailUser)
                .collect(Collectors.joining(","));

        List<ViewTrabajadorReportDTO> trabajadores = detalleList.stream( )
                .filter( detalle -> detalle.getTrabajadorId( ) != null )
                .map( detalleDTO -> iViewTrabajadorReportQueryService.findById( detalleDTO.getTrabajadorId( ) ) )
                .toList( );

        List<TrabajadorEmailDTO> workers = trabajadores.stream( )
                .map( t -> TrabajadorEmailDTO.builder( )
                        .fullName( t.getNombre( ) )
                        .reasonSso( t.getSsoMotivo( ) )
                        .dni( t.getNroDocumentoIdentidad( ) )
                        .statusSso( t.getSsoEstadoNombre( ) )
                        .docsCount( t.getNDocsSsoAdjuntos( ) + "" )
                        .highRisk( t.getIndTrabajoAltoRiesgo( ) ? "Sí" : "No" )
                        .highRiskDocsCount( t.getNDocsAarAdjuntos( ) + "" )
                        .preventionist( t.getIndPrevencionista( ) ? "Sí" : "No" )
                        .preventionistDocsCount( t.getNDocsPrevAdjuntos( ) + "" )
                        .build( ) )
                .toList( );

        NotificacionSolicitudVisitaDTO dto = NotificacionSolicitudVisitaDTO.builder( )
                .recipients(recipientsEmail)
                .companyName( proveedor.getNombreFiscal( ) )
                .site( "Sede Principal" )
                .visitedUser( solicitudVisita.getPersonalNombreUsuario( ) )
                .startDateTime( DateTimeUtil.asStringV2( solicitudVisita.getFechaInicio( ) ) )
                .endDateTime( DateTimeUtil.asStringV2( solicitudVisita.getFechaFin( ) ) )
                .serviceOrder( solicitudVisita.getNroOrdenServicio( ) )
                .visitDescription( solicitudVisita.getMotivoVisita( ) )
                .specialObservations( solicitudVisita.getObservaciones( ) )
                .areasToVisit( solicitudVisita.getAreasNombres( ) )
                .zonesToVisit( solicitudVisita.getZonasNombres( ) )
                .highRisk( solicitudVisita.getIndActividadAltoRiesgo( ) ? "Si" : "No" )
                .onboard( solicitudVisita.getIndActividadABordo( ) ? "Si" : "No" )
                .scaffolding( solicitudVisita.getIndRequiereAndamios( ) ? "Si" : "No" )
                .crane( solicitudVisita.getIndRequiereGrua( ) ? "Si" : "No" )
                .mobileEquipment( solicitudVisita.getIndRequiereEquiposMoviles( ) ? "Si" : "No" )
                .divingWork( solicitudVisita.getIndTrabajoBuceo( ) ? "Si" : "No" )
                .workers( workers )
                .urlBtp( portalUrl )
                .codVisita(solicitudVisita.getCodigoVisita())
                .build( );

        sendSolicitudVisita( dto );

    }

    @Override
    public void sendAprobacionVisitaProveedor( NotificacionAprobacionVisitaProveedorDTO dto ) {
        Map<String, Object> data = new HashMap<>( );
        data.put( "company_name", dto.getCompanyName( ) );
        data.put( "sso_status", dto.getSsoStatus( ) );
        data.put( "environmental_status", dto.getEnvironmentalStatus( ) );
        data.put( "site", dto.getSite( ) );
        data.put( "visited_user", dto.getVisitedUser( ) );
        data.put( "start_date_time", dto.getStartDateTime( ) );
        data.put( "end_date_time", dto.getEndDateTime( ) );
        data.put( "service_order", dto.getServiceOrder( ) );
        data.put( "visit_description", dto.getVisitDescription( ) );
        data.put( "special_observations", dto.getSpecialObservations( ) );
        data.put( "areas_to_visit", dto.getAreasToVisit( ) );
        data.put( "zones_to_visit", dto.getZonesToVisit( ) );
        data.put( "high_risk", dto.getHighRisk( ) );
        data.put( "onboard", dto.getOnboard( ) );
        data.put( "scaffolding", dto.getScaffolding( ) );
        data.put( "crane", dto.getCrane( ) );
        data.put( "mobile_equipment", dto.getMobileEquipment( ) );
        data.put( "diving_work", dto.getDivingWork( ) );
        data.put( "workers", dto.getWorkers( ) );
        data.put( "status_sso", dto.getStatusSso( ) );
        data.put( "reason_sso", dto.getReasonSso( ) );
        data.put( "status_environmental_quality", dto.getStatusEnvironmentalQuality( ) );
        data.put( "reason_environmental_quality", dto.getReasonEnvironmentalQuality( ) );
        data.put( "url_btp", dto.getUrlBtp( ) );
        data.put( "cod_visita", dto.getCodVisita() );

        NotifyInput input = NotifyInput.builder( )
                .to( dto.getRecipients( ) )
                .cc("")
                .eventNotifyEnum( EventNotifyEnum.SVT2_SVT3_APROBACION_VISITA_PROVEEDOR )
                .data( data )
                .build( );

        emailSenderService.send( input );
    }

    @Override
    public void sendAprobacionVisitaProveedor( String solicitudVisitaId ) {

        ViewSolicitudVisitaReportDTO solicitudVisita = iViewSolicitudVisitaReportQueryService.findById( solicitudVisitaId );

        ViewProveedorReportDTO proveedor = iViewProveedorReportQueryService.findById( solicitudVisita.getProveedorId( ) );

        List<SvSolicitudVisitaDetalleDTO> detalleList = iSvSolicitudVisitaDetalleQueryService.search( buildPagedRequestSolicitudVisitaDetalle( solicitudVisitaId ) ).getResult( );

        List<ViewTrabajadorReportDTO> trabajadores = detalleList.stream( )
                .filter( detalle -> detalle.getTrabajadorId( ) != null )
                .map( detalleDTO -> iViewTrabajadorReportQueryService.findById( detalleDTO.getTrabajadorId( ) ) )
                .toList( );

        List<TrabajadorEmailDTO> workers = trabajadores.stream( )
                .map( t -> TrabajadorEmailDTO.builder( )
                        .fullName( t.getNombre( ) )
                        .reasonSso( t.getSsoMotivo( ) )
                        .dni( t.getNroDocumentoIdentidad( ) )
                        .statusSso( t.getSsoEstadoNombre( ) )
                        .docsCount( t.getNDocsSsoAdjuntos( ) + "" )
                        .highRisk( t.getIndTrabajoAltoRiesgo( ) ? "Sí" : "No" )
                        .highRiskDocsCount( t.getNDocsAarAdjuntos( ) + "" )
                        .preventionist( t.getIndPrevencionista( ) ? "Sí" : "No" )
                        .preventionistDocsCount( t.getNDocsPrevAdjuntos( ) + "" )
                        .build( ) )
                .toList( );

        NotificacionAprobacionVisitaProveedorDTO dto = NotificacionAprobacionVisitaProveedorDTO.builder( )
//                .recipients( isLocal ? "jescudero@csticorp.biz": "ldiaz@copeinca.com.pe,jescudero@csticorp.biz" )
                .recipients( proveedor.getEmail())
                .companyName( proveedor.getNombreFiscal( ) )
                .ssoStatus( solicitudVisita.getSsoEstadoNombre( ) )
                .environmentalStatus( solicitudVisita.getCaEstadoNombre( ) )
                .site( solicitudVisita.getSedeNombre( ) )
                .visitedUser( solicitudVisita.getPersonalNombreUsuario( ) )
                .startDateTime( DateTimeUtil.asString( solicitudVisita.getFechaInicio( ) ) )
                .endDateTime( DateTimeUtil.asString( solicitudVisita.getFechaFin( ) ) )
                .serviceOrder( solicitudVisita.getNroOrdenServicio( ) )
                .visitDescription( solicitudVisita.getMotivoVisita( ) )
                .specialObservations( solicitudVisita.getObservaciones( ) )
                .areasToVisit( solicitudVisita.getAreasNombres( ) )
                .zonesToVisit( solicitudVisita.getZonasNombres( ) )
                .highRisk( solicitudVisita.getIndActividadAltoRiesgo( ) ? "Si" : "No" )
                .onboard( solicitudVisita.getIndActividadABordo( ) ? "Si" : "No" )
                .scaffolding( solicitudVisita.getIndRequiereAndamios( ) ? "Si" : "No" )
                .crane( solicitudVisita.getIndRequiereGrua( ) ? "Si" : "No" )
                .mobileEquipment( solicitudVisita.getIndRequiereEquiposMoviles( ) ? "Si" : "No" )
                .divingWork( solicitudVisita.getIndTrabajoBuceo( ) ? "Si" : "No" )
                .workers( workers )
                .statusSso( solicitudVisita.getSsoEstadoNombre( ) )
                .reasonSso( solicitudVisita.getSsoMotivo( ) )
                .statusEnvironmentalQuality( solicitudVisita.getCaEstadoNombre( ) )
                .reasonEnvironmentalQuality( solicitudVisita.getCaMotivo( ) )
                .urlBtp( portalUrl )
                .codVisita(solicitudVisita.getCodigoVisita())
                .build( );

        sendAprobacionVisitaProveedor( dto );

    }

    @Override
    public void sendAprobacionVisitaSsoQa( NotificacionAprobacionVisitaSsoQaDTO dto ) {
        Map<String, Object> data = new HashMap<>( );
        data.put( "company_name", dto.getCompanyName( ) );
        data.put( "sso_status", dto.getSsoStatus( ) );
        data.put( "environmental_status", dto.getEnvironmentalStatus( ) );
        data.put( "site", dto.getSite( ) );
        data.put( "visited_user", dto.getVisitedUser( ) );
        data.put( "start_date_time", dto.getStartDateTime( ) );
        data.put( "end_date_time", dto.getEndDateTime( ) );
        data.put( "service_order", dto.getServiceOrder( ) );
        data.put( "visit_description", dto.getVisitDescription( ) );
        data.put( "special_observations", dto.getSpecialObservations( ) );
        data.put( "areas_to_visit", dto.getAreasToVisit( ) );
        data.put( "zones_to_visit", dto.getZonesToVisit( ) );
        data.put( "high_risk", dto.getHighRisk( ) );
        data.put( "onboard", dto.getOnboard( ) );
        data.put( "scaffolding", dto.getScaffolding( ) );
        data.put( "crane", dto.getCrane( ) );
        data.put( "mobile_equipment", dto.getMobileEquipment( ) );
        data.put( "diving_work", dto.getDivingWork( ) );
        data.put( "workers", dto.getWorkers( ) );
        data.put( "status_sso", dto.getStatusSso( ) );
        data.put( "reason_sso", dto.getReasonSso( ) );
        data.put( "status_environmental_quality", dto.getStatusEnvironmentalQuality( ) );
        data.put( "reason_environmental_quality", dto.getReasonEnvironmentalQuality( ) );
        data.put( "url_btp", dto.getUrlBtp( ) );
        data.put( "cod_visita", dto.getCodVisita( ) );

        NotifyInput input = NotifyInput.builder( )
                .to( dto.getRecipients( ) )
                .cc("")
                .eventNotifyEnum( EventNotifyEnum.SVT2_SVT3_APROBACION_VISITA_SSO_QA )
                .data( data )
                .build( );

        emailSenderService.send( input );
    }

    @Override
    public void sendAprobacionVisitaSsoQa( String solicitudVisitaId ) {

        ViewSolicitudVisitaReportDTO solicitudVisita = iViewSolicitudVisitaReportQueryService.findById( solicitudVisitaId );

        List<String> filterRoles = new ArrayList<>();
            filterRoles.add( "controlprov_amb" );
            filterRoles.add( "controlprov_sso" );

        List<SvUsuarioRolSedeDTO> result = iSvUsuarioRolSedeQueryService.search(PagedRequest.<SvUsuarioRolSedeFilter>builder()
                .filter(SvUsuarioRolSedeFilter.builder()
                        .sedeIdIds(List.of(solicitudVisita.getSedeId()))
//                                .sedeIdIds(List.of("ef6f00b1-7893-45c0-bc46-46e609ae12e1","ef6f00b1-7893-45c0-bc46-46e609ae12e1"))
//                                .rolCodeIds(List.of("controlprov_sso","controlprov_amb"))
                        .rolCodeIds(filterRoles.stream().toList())
                        .build())
                .build()).getResult();
//        if(result.isEmpty())
//            throw new RuntimeException("No existe usuario SSO o Calidad en sedes: " +proveedor.getSedesNombres());
        String recipientsEmail = result.stream()
                .distinct()
                .map(SvUsuarioRolSedeDTO::getEmailUser)
                .collect(Collectors.joining(","));
//        String recipientsEmail = "";
//        recipientsEmail = emailsCA+","+emailsSSO;
//        recipientsEmail = recipientsEmail + ;

        ViewProveedorReportDTO proveedor = iViewProveedorReportQueryService.findById( solicitudVisita.getProveedorId( ) );

        List<SvSolicitudVisitaDetalleDTO> detalleList = iSvSolicitudVisitaDetalleQueryService.search( buildPagedRequestSolicitudVisitaDetalle( solicitudVisitaId ) ).getResult( );

        List<ViewTrabajadorReportDTO> trabajadores = detalleList.stream( )
                .filter( detalle -> detalle.getTrabajadorId( ) != null )
                .map( detalleDTO -> iViewTrabajadorReportQueryService.findById( detalleDTO.getTrabajadorId( ) ) )
                .toList( );

        List<TrabajadorEmailDTO> workers = trabajadores.stream( )
                .map( t -> TrabajadorEmailDTO.builder( )
                        .fullName( t.getNombre( ) )
                        .reasonSso( t.getSsoMotivo( ) )
                        .dni( t.getNroDocumentoIdentidad( ) )
                        .statusSso( t.getSsoEstadoNombre( ) )
                        .docsCount( t.getNDocsSsoAdjuntos( ) + "" )
                        .highRisk( t.getIndTrabajoAltoRiesgo( ) ? "Sí" : "No" )
                        .highRiskDocsCount( t.getNDocsAarAdjuntos( ) + "" )
                        .preventionist( t.getIndPrevencionista( ) ? "Sí" : "No" )
                        .preventionistDocsCount( t.getNDocsPrevAdjuntos( ) + "" )
                        .build( ) )
                .toList( );

        NotificacionAprobacionVisitaSsoQaDTO dto = NotificacionAprobacionVisitaSsoQaDTO.builder( )
//                .recipients( isLocal ? "jescudero@csticorp.biz,123456789@yopmail.com": "ldiaz@copeinca.com.pe,jescudero@csticorp.biz" )
                .recipients( recipientsEmail)
                .companyName( proveedor.getNombreFiscal( ) )
                .ssoStatus( solicitudVisita.getSsoEstadoNombre( ) )
                .environmentalStatus( solicitudVisita.getCaEstadoNombre( ) )
                .site( solicitudVisita.getSedeNombre( ) )
                .visitedUser( solicitudVisita.getPersonalNombreUsuario( ) )
                .startDateTime( DateTimeUtil.asString( solicitudVisita.getFechaInicio( ) ) )
                .endDateTime( DateTimeUtil.asString( solicitudVisita.getFechaFin( ) ) )
                .serviceOrder( solicitudVisita.getNroOrdenServicio( ) )
                .visitDescription( solicitudVisita.getMotivoVisita( ) )
                .specialObservations( solicitudVisita.getObservaciones( ) )
                .areasToVisit( solicitudVisita.getAreasNombres( ) )
                .zonesToVisit( solicitudVisita.getZonasNombres( ) )
                .highRisk( solicitudVisita.getIndActividadAltoRiesgo( ) ? "Si" : "No" )
                .onboard( solicitudVisita.getIndActividadABordo( ) ? "Si" : "No" )
                .scaffolding( solicitudVisita.getIndRequiereAndamios( ) ? "Si" : "No" )
                .crane( solicitudVisita.getIndRequiereGrua( ) ? "Si" : "No" )
                .mobileEquipment( solicitudVisita.getIndRequiereEquiposMoviles( ) ? "Si" : "No" )
                .divingWork( solicitudVisita.getIndTrabajoBuceo( ) ? "Si" : "No" )
                .workers( workers )
                .statusSso( solicitudVisita.getSsoEstadoNombre( ) )
                .reasonSso( solicitudVisita.getSsoMotivo( ) )
                .statusEnvironmentalQuality( solicitudVisita.getCaEstadoNombre( ) )
                .reasonEnvironmentalQuality( solicitudVisita.getCaMotivo( ) )
                .urlBtp( portalUrl )
                .codVisita(solicitudVisita.getCodigoVisita())
                .build( );

        sendAprobacionVisitaSsoQa( dto );

    }

    @Override
    public void sendAprobacionVisitaVisitado( NotificacionAprobacionVisitaVisitadoDTO dto ) {
        Map<String, Object> data = new HashMap<>( );
        data.put( "company_name", dto.getCompanyName( ) );
        data.put( "visited_name", dto.getVisitedName( ) );
        data.put( "site", dto.getSite( ) );
        data.put( "visited_user", dto.getVisitedUser( ) );
        data.put( "start_date_time", dto.getStartDateTime( ) );
        data.put( "end_date_time", dto.getEndDateTime( ) );
        data.put( "service_order", dto.getServiceOrder( ) );
        data.put( "visit_description", dto.getVisitDescription( ) );
        data.put( "special_observations", dto.getSpecialObservations( ) );
        data.put( "areas_to_visit", dto.getAreasToVisit( ) );
        data.put( "zones_to_visit", dto.getZonesToVisit( ) );
        data.put( "high_risk", dto.getHighRisk( ) );
        data.put( "onboard", dto.getOnboard( ) );
        data.put( "scaffolding", dto.getScaffolding( ) );
        data.put( "crane", dto.getCrane( ) );
        data.put( "mobile_equipment", dto.getMobileEquipment( ) );
        data.put( "diving_work", dto.getDivingWork( ) );
        data.put( "workers", dto.getWorkers( ) );
        data.put( "status_sso", dto.getStatusSso( ) );
        data.put( "reason_sso", dto.getReasonSso( ) );
        data.put( "status_environmental_quality", dto.getStatusEnvironmentalQuality( ) );
        data.put( "reason_environmental_quality", dto.getReasonEnvironmentalQuality( ) );
        data.put( "url_btp", dto.getUrlBtp( ) );
        data.put( "url_approve", dto.getUrlApprove( ) );
        data.put( "url_reject", dto.getUrlReject( ) );
        data.put( "cod_visita", dto.getCodVisita() );

        NotifyInput input = NotifyInput.builder( )
                .to( dto.getRecipients( ) )
                .cc("")
                .eventNotifyEnum( EventNotifyEnum.SVT2_SVT3_APROBACION_VISITA_VISITADO )
                .data( data )
                .build( );

        emailSenderService.send( input );
    }

    @Override
    public void sendAprobacionVisitaVisitado( String solicitudVisitaId ) {

        ViewSolicitudVisitaReportDTO solicitudVisita = iViewSolicitudVisitaReportQueryService.findById( solicitudVisitaId );

        ViewProveedorReportDTO proveedor = iViewProveedorReportQueryService.findById( solicitudVisita.getProveedorId( ) );

        List<SvSolicitudVisitaDetalleDTO> detalleList = iSvSolicitudVisitaDetalleQueryService.search( buildPagedRequestSolicitudVisitaDetalle( solicitudVisitaId ) ).getResult( );

        List<ViewTrabajadorReportDTO> trabajadores = detalleList.stream( )
                .filter( detalle -> detalle.getTrabajadorId( ) != null )
                .map( detalleDTO -> iViewTrabajadorReportQueryService.findById( detalleDTO.getTrabajadorId( ) ) )
                .toList( );

        List<TrabajadorEmailDTO> workers = trabajadores.stream( )
                .map( t -> TrabajadorEmailDTO.builder( )
                        .fullName( t.getNombre( ) )
                        .reasonSso( t.getSsoMotivo( ) )
                        .dni( t.getNroDocumentoIdentidad( ) )
                        .statusSso( t.getSsoEstadoNombre( ) )
                        .docsCount( t.getNDocsSsoAdjuntos( ) + "" )
                        .highRisk( t.getIndTrabajoAltoRiesgo( ) ? "Sí" : "No" )
                        .highRiskDocsCount( t.getNDocsAarAdjuntos( ) + "" )
                        .preventionist( t.getIndPrevencionista( ) ? "Sí" : "No" )
                        .preventionistDocsCount( t.getNDocsPrevAdjuntos( ) + "" )
                        .build( ) )
                .toList( );

        NotificacionAprobacionVisitaVisitadoDTO dto = NotificacionAprobacionVisitaVisitadoDTO.builder( )
//                .recipients( isLocal ? "jescudero@csticorp.biz": "ldiaz@copeinca.com.pe,jescudero@csticorp.biz" )
                .recipients( proveedor.getEmail())
                .companyName( proveedor.getNombreFiscal( ) )
                .visitedName( solicitudVisita.getPersonalNombreUsuario( ) )
                .site( solicitudVisita.getSedeNombre( ) )
                .visitedUser( solicitudVisita.getPersonalNombreUsuario( ) )
                .startDateTime( DateTimeUtil.asString( solicitudVisita.getFechaInicio( ) ) )
                .endDateTime( DateTimeUtil.asString( solicitudVisita.getFechaFin( ) ) )
                .serviceOrder( solicitudVisita.getNroOrdenServicio( ) )
                .visitDescription( solicitudVisita.getMotivoVisita( ) )
                .specialObservations( solicitudVisita.getObservaciones( ) )
                .areasToVisit( solicitudVisita.getAreasNombres( ) )
                .zonesToVisit( solicitudVisita.getZonasNombres( ) )
                .highRisk( solicitudVisita.getIndActividadAltoRiesgo( ) ? "Si" : "No" )
                .onboard( solicitudVisita.getIndActividadABordo( ) ? "Si" : "No" )
                .scaffolding( solicitudVisita.getIndRequiereAndamios( ) ? "Si" : "No" )
                .crane( solicitudVisita.getIndRequiereGrua( ) ? "Si" : "No" )
                .mobileEquipment( solicitudVisita.getIndRequiereEquiposMoviles( ) ? "Si" : "No" )
                .divingWork( solicitudVisita.getIndTrabajoBuceo( ) ? "Si" : "No" )
                .workers( workers )
                .statusSso( solicitudVisita.getSsoEstadoNombre( ) )
                .reasonSso( solicitudVisita.getSsoMotivo( ) )
                .statusEnvironmentalQuality( solicitudVisita.getCaEstadoNombre( ) )
                .reasonEnvironmentalQuality( solicitudVisita.getCaMotivo( ) )
                .urlBtp( portalUrl )
                .urlApprove( portalUrl )
                .urlReject( portalUrl )
                .codVisita(solicitudVisita.getCodigoVisita())
                .build( );

        sendAprobacionVisitaVisitado( dto );

    }

    @Override
    public void sendCancelacionVisita( NotificacionCancelacionVisitaDTO dto ) {
        Map<String, Object> data = new HashMap<>( );
        data.put( "company_name", dto.getCompanyName( ) );
        data.put( "visited_name", dto.getVisitedName( ) );
        data.put( "site", dto.getSite( ) );
        data.put( "visited_user", dto.getVisitedUser( ) );
        data.put( "start_date_time", dto.getStartDateTime( ) );
        data.put( "end_date_time", dto.getEndDateTime( ) );
        data.put( "service_order", dto.getServiceOrder( ) );
        data.put( "visit_description", dto.getVisitDescription( ) );
        data.put( "special_observations", dto.getSpecialObservations( ) );
        data.put( "areas_to_visit", dto.getAreasToVisit( ) );
        data.put( "zones_to_visit", dto.getZonesToVisit( ) );
        data.put( "high_risk", dto.getHighRisk( ) );
        data.put( "onboard", dto.getOnboard( ) );
        data.put( "scaffolding", dto.getScaffolding( ) );
        data.put( "crane", dto.getCrane( ) );
        data.put( "mobile_equipment", dto.getMobileEquipment( ) );
        data.put( "diving_work", dto.getDivingWork( ) );
        data.put( "workers", dto.getWorkers( ) );
        data.put( "status_sso", dto.getStatusSso( ) );
        data.put( "reason_sso", dto.getReasonSso( ) );
        data.put( "status_environmental_quality", dto.getStatusEnvironmentalQuality( ) );
        data.put( "reason_environmental_quality", dto.getReasonEnvironmentalQuality( ) );
        data.put( "url_btp", dto.getUrlBtp( ) );
        data.put( "cod_visita", dto.getCodVisita() );

        NotifyInput input = NotifyInput.builder( )
                .to( dto.getRecipients( ) )
                .cc("")
                .eventNotifyEnum( EventNotifyEnum.SVT8_CANCELACION_VISITA )
                .data( data )
                .build( );

        emailSenderService.send( input );
    }

    @Override
    public void sendCancelacionVisita( String solicitudVisitaId ) {

        ViewSolicitudVisitaReportDTO solicitudVisita = iViewSolicitudVisitaReportQueryService.findById( solicitudVisitaId );

        ViewProveedorReportDTO proveedor = iViewProveedorReportQueryService.findById( solicitudVisita.getProveedorId( ) );

        List<SvSolicitudVisitaDetalleDTO> detalleList = iSvSolicitudVisitaDetalleQueryService.search( buildPagedRequestSolicitudVisitaDetalle( solicitudVisitaId ) ).getResult( );

        List<ViewTrabajadorReportDTO> trabajadores = detalleList.stream( )
                .filter( detalle -> detalle.getTrabajadorId( ) != null )
                .map( detalleDTO -> iViewTrabajadorReportQueryService.findById( detalleDTO.getTrabajadorId( ) ) )
                .toList( );

        List<TrabajadorEmailDTO> workers = trabajadores.stream( )
                .map( t -> TrabajadorEmailDTO.builder( )
                        .fullName( t.getNombre( ) )
                        .reasonSso( t.getSsoMotivo( ) )
                        .dni( t.getNroDocumentoIdentidad( ) )
                        .statusSso( t.getSsoEstadoNombre( ) )
                        .docsCount( t.getNDocsSsoAdjuntos( ) + "" )
                        .highRisk( t.getIndTrabajoAltoRiesgo( ) ? "Sí" : "No" )
                        .highRiskDocsCount( t.getNDocsAarAdjuntos( ) + "" )
                        .preventionist( t.getIndPrevencionista( ) ? "Sí" : "No" )
                        .preventionistDocsCount( t.getNDocsPrevAdjuntos( ) + "" )
                        .build( ) )
                .toList( );

        NotificacionCancelacionVisitaDTO dto = NotificacionCancelacionVisitaDTO.builder( )
                .recipients( proveedor.getEmail() )
                .companyName( proveedor.getNombreFiscal( ) )
                .visitedName( solicitudVisita.getPersonalNombreUsuario( ) )
                .site( solicitudVisita.getSedeNombre( ) )
                .visitedUser( solicitudVisita.getPersonalNombreUsuario( ) )
                .startDateTime( DateTimeUtil.asString( solicitudVisita.getFechaInicio( ) ) )
                .endDateTime( DateTimeUtil.asString( solicitudVisita.getFechaFin( ) ) )
                .serviceOrder( solicitudVisita.getNroOrdenServicio( ) )
                .visitDescription( solicitudVisita.getMotivoVisita( ) )
                .specialObservations( solicitudVisita.getObservaciones( ) )
                .areasToVisit( solicitudVisita.getAreasNombres( ) )
                .zonesToVisit( solicitudVisita.getZonasNombres( ) )
                .highRisk( solicitudVisita.getIndActividadAltoRiesgo( ) ? "Si" : "No" )
                .onboard( solicitudVisita.getIndActividadABordo( ) ? "Si" : "No" )
                .scaffolding( solicitudVisita.getIndRequiereAndamios( ) ? "Si" : "No" )
                .crane( solicitudVisita.getIndRequiereGrua( ) ? "Si" : "No" )
                .mobileEquipment( solicitudVisita.getIndRequiereEquiposMoviles( ) ? "Si" : "No" )
                .divingWork( solicitudVisita.getIndTrabajoBuceo( ) ? "Si" : "No" )
                .workers( workers )
                .statusSso( solicitudVisita.getSsoEstadoNombre( ) )
                .reasonSso( solicitudVisita.getSsoMotivo( ) )
                .statusEnvironmentalQuality( solicitudVisita.getCaEstadoNombre( ) )
                .reasonEnvironmentalQuality( solicitudVisita.getCaMotivo( ) )
                .codVisita(solicitudVisita.getCodigoVisita())
                .urlBtp( portalUrl )
                .build( );

        sendCancelacionVisita( dto );

    }

    @Override
    public void sendEvaluacionProveedorWithRol(String proveedorId, String rol) {
        //--- Obtenemos información del proveedor
        ViewProveedorReportDTO proveedor = iViewProveedorReportQueryService.findById( proveedorId );

        //--- Obtenemos información de los workers
        List<ViewTrabajadorReportDTO> trabajadores = iViewTrabajadorReportQueryService.search( PagedRequest.<ViewTrabajadorReportFilter>builder( )
                .filter( ViewTrabajadorReportFilter.builder( )
                        .proveedorId( List.of( FilterOperation.<String>builder( )
                                .operator( OperatorEnum.EQ )
                                .values( List.of( proveedorId ) )
                                .build( ) ) )
                        .build( ) )
                .build( ) ).getResult( );

        //--- Transformamos los datos
        List<TrabajadorEmailDTO> workers = trabajadores.stream( )
                .map( t -> TrabajadorEmailDTO.builder( )
                        .fullName( t.getNombre( ) )
                        .reasonSso( t.getSsoMotivo( ) )
                        .dni( t.getNroDocumentoIdentidad( ) )
                        .statusSso( t.getSsoEstadoNombre( ) )
                        .docsCount( t.getNDocsSsoAdjuntos( ) + "" )
                        .highRisk( t.getIndTrabajoAltoRiesgo( ) ? "Sí" : "No" )
                        .highRiskDocsCount( t.getNDocsAarAdjuntos( ) + "" )
                        .preventionist( t.getIndPrevencionista( ) ? "Sí" : "No" )
                        .preventionistDocsCount( t.getNDocsPrevAdjuntos( ) + "" )
                        .build( ) )
                .toList( );

        //--- Construimos el DTO de notificación
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        NotificacionEvaluacionProveedorDTO dto = NotificacionEvaluacionProveedorDTO.builder( )
                .recipients( proveedor.getEmail( ) )
//                .recipients( "ranulfillo369@gmail.com" ) //--- TODO: Eliminar para calidad y PRD
//                .recipients( isLocal ? "jescudero@csticorp.biz,123456789@yopmail.com": "ldiaz@copeinca.com.pe,jescudero@csticorp.biz" ) //--- TODO: Eliminar para calidad y PRD
                .companyName( proveedor.getNombreFiscal( ) )
                .username( proveedor.getEmail( ) )
                .statusSso( proveedor.getSsoEstadoNombre() )
                .reasonSso( proveedor.getSsoMotivo( ) )
                .statusEnvironmentalQuality( proveedor.getCaEstadoNombre() )
                .reasonEnvironmentalQuality( proveedor.getCaMotivo( ) )
                .workers( workers )
                .urlBtp( portalUrl )
                .allowedSites( proveedor.getSedesNombres( ) )
//                .ssoDocsCount( proveedor.getNDocsProvSso( ) + "" )
                .ssoDocsCount( proveedor.getNDocsProvSsoValid( ) + "" )
                .highRisk( proveedor.getIndRealizaActividadAltoRiesgo( ) ? "Sí" : "No" )
//                .highRiskDocsCount( proveedor.getNDocsAar() + "" )
                .highRiskDocsCount( proveedor.getNDocsAarValid() + "" )
                .onboard( proveedor.getIndRealizaActividadABordo( ) ? "Sí" : "No" )
//                .onboardDocsCount( proveedor.getNDocsAb()+ "" )
                .onboardDocsCount( proveedor.getNDocsAbValid()+ "" )
//                .environmentalDocsCount( proveedor.getNDocsCa( ) + "" )
                .environmentalDocsCount( proveedor.getNDocsCaValid( ) + "" )
//                .urlBtp( portalUrl )
                .has_vigencia_sso(proveedor.getSsoTieneVigencia() ? "Sí" : "No")
                .has_vigencia_ca(proveedor.getCaTieneVigencia() ? "Sí" : "No")
                .date_has_vigencia_sso(proveedor.getSsoFechaFinVigencia())
                .date_has_vigencia_ca(proveedor.getCaFechaFinVigencia() == null ? "" : proveedor.getCaFechaFinVigencia().format(formatter))
                .rolUser(rol)

                .build( );

        //--- Enviamos la notificación
        sendEvaluacionProveedor( dto );
    }

    @Override
    public void sendHabilitarProveedor(String proveedorId) {
        SvProveedorDTO proveedor = iProveedorQueryService.findById( proveedorId );

        //--- Construimos el DTO de notificación
        NotificacionCreacionProveedorDTO dto = NotificacionCreacionProveedorDTO.builder( )
                .recipients( proveedor.getEmail( ) )
//                .recipients( "ranulfillo369@gmail.com" ) //--- TODO: Eliminar para calidad y PRD
//                .recipients( isLocal ? "jescudero@csticorp.biz,123456789@yopmail.com": "ldiaz@copeinca.com.pe" ) //--- TODO: Eliminar para calidad y PRD
                .companyName( proveedor.getNombreFiscal( ) )
                .username( proveedor.getEmail( ) )
                .build( );

        sendHabilitarProveedor( dto );
    }

    @Override
    public CustomMessageView sendModificacionDatosWithMessage(String proveedorId) {
        CustomMessageView.CustomMessageViewBuilder<Object> respone = CustomMessageView.builder();
        List<MessageViewModel> list = new ArrayList<>();
        MessageViewModel.MessageViewModelBuilder tmp = MessageViewModel.builder();


        ViewProveedorReportDTO proveedor = iViewProveedorReportQueryService.findById( proveedorId );
        //ArrayList<String> filterRoles = new ArrayList<>();
        List<String> filterRoles = new ArrayList<>();
        if(proveedor.getNotificaCa() == null && proveedor.getNotificaSso() == null)
            return CustomMessageView.defaultInstanceWithErros(new Exception("No es posible notificar, sin cambios en parametros notificaCA y SSO."));
//            tmp.description("No es posible notificar, sin cambios en parametros notificaCA y SSO.");
        if(proveedor.getNotificaCa())
            filterRoles.add( "controlprov_amb" );
        if(proveedor.getNotificaSso())
            filterRoles.add( "controlprov_sso" );

        List<SvUsuarioRolSedeDTO> result = iSvUsuarioRolSedeQueryService.search(PagedRequest.<SvUsuarioRolSedeFilter>builder()
                .filter(SvUsuarioRolSedeFilter.builder()
                        .sedeIdIds(Arrays.stream(proveedor.getSedesIds().split(",")).toList())
//                                .sedeIdIds(List.of("ef6f00b1-7893-45c0-bc46-46e609ae12e1","ef6f00b1-7893-45c0-bc46-46e609ae12e1"))
//                                .rolCodeIds(List.of("controlprov_sso","controlprov_amb"))
                        .rolCodeIds(filterRoles.stream().toList())
                        .build())
                .build()).getResult();
        if(result.isEmpty())
            return CustomMessageView.defaultInstanceWithErros(new Exception("No existe usuario SSO o Calidad en sedes: " +proveedor.getSedesNombres()));
        String emailsSSO = result.stream()
                .filter(p -> "controlprov_sso".equals(p.getRolCode()))
                .distinct()
                .map(SvUsuarioRolSedeDTO::getEmailUser)
                .collect(Collectors.joining(","));
        String emailsCA = result.stream()
                .filter(p -> "controlprov_amb".equals(p.getRolCode()))
                .distinct()
                .map(SvUsuarioRolSedeDTO::getEmailUser)
                .collect(Collectors.joining(","));
        String recipientsEmail = "";

        if(proveedor.getNotificaCa())
            recipientsEmail = recipientsEmail + emailsCA;
        if(proveedor.getNotificaSso())
            recipientsEmail = recipientsEmail + emailsSSO;



        List<ViewTrabajadorReportDTO> trabajadores = iViewTrabajadorReportQueryService.search( PagedRequest.<ViewTrabajadorReportFilter>builder( )
                .filter( ViewTrabajadorReportFilter.builder( )
                        .proveedorId( List.of( FilterOperation.<String>builder( )
                                .operator( OperatorEnum.EQ )
                                .values( List.of( proveedorId ) )
                                .build( ) ) )
                        .build( ) )
                .sort(List.of(SortField.builder()
                                .field("ssoEstadoNombre")
                                .direction(SortDirection.ASC)
                                .build(),
                        SortField.builder()
                                .field("nombre")
                                .direction(SortDirection.ASC)
                                .build()))
                .build( ) ).getResult( );

        List<TrabajadorEmailDTO> workers = trabajadores.stream( )
                .map( t -> TrabajadorEmailDTO.builder( )
                        .fullName( t.getNombre( ) )
                        .reasonSso( t.getSsoMotivo( ) )
                        .dni( t.getNroDocumentoIdentidad( ) )
                        .statusSso( t.getSsoEstadoNombre( ) )
                        .docsCount( t.getNDocsSsoAdjuntos( ) + "" )
                        .highRisk( t.getIndTrabajoAltoRiesgo( ) ? "Sí" : "No" )
                        .highRiskDocsCount( t.getNDocsAarAdjuntos( ) + "" )
                        .preventionist( t.getIndPrevencionista( ) ? "Sí" : "No" )
                        .preventionistDocsCount( t.getNDocsPrevAdjuntos( ) + "" )
                        .build( ) )
                .toList( );
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        //String formatted = date.format(formatter);
        NotificacionModificacionDatosDTO dto = NotificacionModificacionDatosDTO.builder( )
//                .recipients( "ranulfillo369@gmail.com" )
                .recipients( isLocal ? "jescudero@csticorp.biz,123456789@yopmail.com,julio.yupanqui.soria@gmail.com": recipientsEmail+",jescudero@csticorp.biz" )
                .companyName( proveedor.getNombreFiscal( ) )
                .allowedSites( proveedor.getSedesNombres( ) )
                .statusSso( proveedor.getSsoEstadoNombre( ) )
//                .ssoDocsCount( proveedor.getNDocsProvSso( ) + "" )
                .ssoDocsCount( proveedor.getNDocsProvSsoValid( ) + "" )
                .highRisk( proveedor.getIndRealizaActividadAltoRiesgo( ) ? "Sí" : "No" )
//                .highRiskDocsCount( proveedor.getNDocsAar() + "" )
                .highRiskDocsCount( proveedor.getNDocsAarValid() + "" )
                .onboard( proveedor.getIndRealizaActividadABordo( ) ? "Sí" : "No" )
//                .onboardDocsCount( proveedor.getNDocsAb()+ "" )
                .onboardDocsCount( proveedor.getNDocsAbValid()+ "" )
                .statusEnvironmentalQuality( proveedor.getCaEstadoNombre() )
//                .environmentalDocsCount( proveedor.getNDocsCa( ) + "" )
                .environmentalDocsCount( proveedor.getNDocsCaValid( ) + "" )
                .workers( workers )
                .urlBtp( portalUrl )
                .has_vigencia_sso(proveedor.getSsoTieneVigencia() ? "Sí" : "No")
                .has_vigencia_ca(proveedor.getCaTieneVigencia() ? "Sí" : "No")
                .date_has_vigencia_sso(proveedor.getSsoFechaFinVigencia())
                .date_has_vigencia_ca(proveedor.getCaFechaFinVigencia() == null ? "" : proveedor.getCaFechaFinVigencia().format(formatter))
                .build( );

        sendModificacionDatos( dto );

//        respone.
        respone.messages(list);
        return respone.build();
    }

    private PagedRequest<ViewSolicitudVisitaReportFilter> buildPagedRequestSolicitudVisita( String proveedorId ) {

        ViewSolicitudVisitaReportFilter filter = ViewSolicitudVisitaReportFilter.builder( )
                .proveedorId(
                        List.of(
                                FilterOperation.<String>builder( )
                                        .operator( OperatorEnum.EQ )
                                        .values( List.of( proveedorId ) )
                                        .build( )
                        )
                )
                .build( );

        return PagedRequest.<ViewSolicitudVisitaReportFilter>builder( )
                .filter( filter )
                .build( );
    }

    private PagedRequest<SvSolicitudVisitaDetalleFilter> buildPagedRequestSolicitudVisitaDetalle( String solicitudVisitaId ) {
        SvSolicitudVisitaDetalleFilter filter = SvSolicitudVisitaDetalleFilter.builder( )
                .solicitudVisitaIdIds( List.of( solicitudVisitaId ) )
                .build( );

        return PagedRequest.<SvSolicitudVisitaDetalleFilter>builder( )
                .filter( filter )
                .build( );
    }
}
