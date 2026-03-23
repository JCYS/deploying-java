
/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/controllers/rest/EntityRest.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.notificacion.controllers.rest;

import com.copeinca.apicopeincaprov.commons.controllers.rest.ApiPaths;
import com.copeinca.apicopeincaprov.global.dtos.response.CustomMessageView;
import com.copeinca.apicopeincaprov.integration.mail.smtp.models.custom.NotifyInput;
import com.copeinca.apicopeincaprov.modules.notificacion.models.dto.*;
import com.copeinca.apicopeincaprov.modules.notificacion.service.INotificationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping( ApiPaths.Notification.MAIL )
public class MailRest {

    private final INotificationService service;
    private final INotificationService iNotificationService;

    @PostMapping( value = "/send-mail" )
    public void sendMail( @RequestBody @Valid NotifyInput input ) {
        service.sendMail( input );
    }

    @PostMapping( value = "/notifica-creacion-proveedor/{proveedor_id}" )
    public ResponseEntity<CustomMessageView<?>> notificaCreacionProveedor( @PathVariable( "proveedor_id" ) String proveedorId ) {

        try {

            iNotificationService.sendCreacionProveedor( proveedorId );

            return ResponseEntity.ok( CustomMessageView.defaultInstanceSuccessful( ) );

        } catch( Exception e ) {

            return ResponseEntity.badRequest( ).body( CustomMessageView.defaultInstanceWithErros( e ) );

        }

    }

    @PostMapping( value = "/notifica-habilitar-proveedor/{proveedor_id}" )
    public ResponseEntity<CustomMessageView<?>> notificaHabilitarnProveedor( @PathVariable( "proveedor_id" ) String proveedorId ) {

        try {

//            iNotificationService.sendCreacionProveedor( proveedorId );
            iNotificationService.sendHabilitarProveedor( proveedorId );

            return ResponseEntity.ok( CustomMessageView.defaultInstanceSuccessful( ) );

        } catch( Exception e ) {

            return ResponseEntity.badRequest( ).body( CustomMessageView.defaultInstanceWithErros( e ) );

        }

    }

    @PostMapping( value = "/notifica-evaluacion-proveedor/{proveedor_id}/{rol}" )
    public ResponseEntity<CustomMessageView<?>> notificaEvaluacionProveedor( @PathVariable( "proveedor_id" ) String proveedorId, @PathVariable( "rol" ) String rol ) {

        try {

//            iNotificationService.sendEvaluacionProveedor( proveedorId );
            iNotificationService.sendEvaluacionProveedorWithRol( proveedorId, rol );

            return ResponseEntity.ok( CustomMessageView.defaultInstanceSuccessful( ) );

        } catch( Exception e ) {

            return ResponseEntity.badRequest( ).body( CustomMessageView.defaultInstanceWithErros( e ) );

        }

    }

    @PostMapping( value = "/notifica-modificacion-datos/{proveedor_id}" )
    public ResponseEntity<CustomMessageView<?>> notificaModificacionDatos( @PathVariable( "proveedor_id" ) String proveedorId ) {

        try {

            iNotificationService.sendModificacionDatos( proveedorId );
//            CustomMessageView.success(CustomMessageView.builder().build());
            return ResponseEntity.ok( CustomMessageView.defaultInstanceSuccessful( ) );

        } catch( Exception e ) {

            return ResponseEntity.badRequest( ).body( CustomMessageView.defaultInstanceWithErros( e ) );

        }

    }
    @PostMapping( value = "/notifica-modificacion-datos_release/{proveedor_id}" )
    public ResponseEntity<CustomMessageView<?>> notificaModificacionDatosTest( @PathVariable( "proveedor_id" ) String proveedorId ) {

        try {
            CustomMessageView responseNoti = iNotificationService.sendModificacionDatosWithMessage( proveedorId );
//            iNotificationService.sendModificacionDatos( proveedorId );
//            CustomMessageView.success(CustomMessageView.builder().build());
            return ResponseEntity.ok( CustomMessageView.success( responseNoti ) );

        } catch( Exception e ) {

            return ResponseEntity.badRequest( ).body( CustomMessageView.defaultInstanceWithErros( e ) );

        }

    }

    @PostMapping( value = "/notifica-solicitud-visita/{solicitud_visita_id}" )
    public ResponseEntity<CustomMessageView<?>> notificaSolicitudVisita( @PathVariable( "solicitud_visita_id" ) String solicitudVisitaId ) {

        try {

            iNotificationService.sendSolicitudVisita( solicitudVisitaId );

            return ResponseEntity.ok( CustomMessageView.defaultInstanceSuccessful( ) );

        } catch( Exception e ) {

            return ResponseEntity.badRequest( ).body( CustomMessageView.defaultInstanceWithErros( e ) );

        }

    }

    @PostMapping( value = "/notifica-aprobacion-visita-proveedor/{solicitud_visita_id}" )
    public ResponseEntity<CustomMessageView<?>> notificaAprobacionVisitaProveedor( @PathVariable( "solicitud_visita_id" ) String solicitudVisitaId ) {

        try {

            iNotificationService.sendAprobacionVisitaProveedor( solicitudVisitaId );

            return ResponseEntity.ok( CustomMessageView.defaultInstanceSuccessful( ) );

        } catch( Exception e ) {

            return ResponseEntity.badRequest( ).body( CustomMessageView.defaultInstanceWithErros( e ) );

        }

    }

    @PostMapping( value = "/notifica-aprobacion-visita-sso-qa/{solicitud_visita_id}" )
    public ResponseEntity<CustomMessageView<?>> notificaAprobacionVisitaSsoQa( @PathVariable( "solicitud_visita_id" ) String solicitudVisitaId ) {

        try {

            iNotificationService.sendAprobacionVisitaSsoQa( solicitudVisitaId );

            return ResponseEntity.ok( CustomMessageView.defaultInstanceSuccessful( ) );

        } catch( Exception e ) {

            return ResponseEntity.badRequest( ).body( CustomMessageView.defaultInstanceWithErros( e ) );

        }

    }

    @PostMapping( value = "/notifica-aprobacion-visita-visitado/{solicitud_visita_id}" )
    public ResponseEntity<CustomMessageView<?>> notificaAprobacionVisitaVisitado( @PathVariable( "solicitud_visita_id" ) String solicitudVisitaId ) {

        try {

            iNotificationService.sendAprobacionVisitaVisitado( solicitudVisitaId );

            return ResponseEntity.ok( CustomMessageView.defaultInstanceSuccessful( ) );

        } catch( Exception e ) {

            return ResponseEntity.badRequest( ).body( CustomMessageView.defaultInstanceWithErros( e ) );

        }

    }

    @PostMapping( value = "/notifica-cancelacion-visita/{solicitud_visita_id}" )
    public ResponseEntity<CustomMessageView<?>> notificaCancelacionVisita( @PathVariable( "solicitud_visita_id" ) String solicitudVisitaId ) {

        try {

            iNotificationService.sendCancelacionVisita( solicitudVisitaId );

            return ResponseEntity.ok( CustomMessageView.defaultInstanceSuccessful( ) );

        } catch( Exception e ) {

            return ResponseEntity.badRequest( ).body( CustomMessageView.defaultInstanceWithErros( e ) );

        }

    }

    @PostMapping( value = "/test-creacion-proveedor" )
    public void testCreacionProveedor( @RequestBody @Valid NotificacionCreacionProveedorDTO dto ) {
        iNotificationService.sendCreacionProveedor( dto );
    }

    @PostMapping( value = "/test-evaluacion-proveedor" )
    public void testEvaluacionProveedor( @RequestBody @Valid NotificacionEvaluacionProveedorDTO dto ) {
        iNotificationService.sendEvaluacionProveedor( dto );
    }

    @PostMapping( value = "/test-modificacion-datos" )
    public void testModificacionDatos( @RequestBody @Valid NotificacionModificacionDatosDTO dto ) {
        iNotificationService.sendModificacionDatos( dto );
    }

    @PostMapping( value = "/test-solicitud-visita" )
    public void testSolicitudVisita( @RequestBody @Valid NotificacionSolicitudVisitaDTO dto ) {
        iNotificationService.sendSolicitudVisita( dto );
    }

    @PostMapping( value = "/test-aprobacion-visita-proveedor" )
    public void testAprobacionVisitaProveedor( @RequestBody @Valid NotificacionAprobacionVisitaProveedorDTO dto ) {
        iNotificationService.sendAprobacionVisitaProveedor( dto );
    }

    @PostMapping( value = "/test-aprobacion-visita-sso-qa" )
    public void testAprobacionVisitaProveedor( @RequestBody @Valid NotificacionAprobacionVisitaSsoQaDTO dto ) {
        iNotificationService.sendAprobacionVisitaSsoQa( dto );
    }

    @PostMapping( value = "/test-aprobacion-visita-visitado" )
    public void testAprobacionVisitaVisitado( @RequestBody @Valid NotificacionAprobacionVisitaVisitadoDTO dto ) {
        iNotificationService.sendAprobacionVisitaVisitado( dto );
    }

    @PostMapping( value = "/test-cancelacion-visita" )
    public void testCancelacionVisita( @RequestBody @Valid NotificacionCancelacionVisitaDTO dto ) {
        iNotificationService.sendCancelacionVisita( dto );
    }

}
