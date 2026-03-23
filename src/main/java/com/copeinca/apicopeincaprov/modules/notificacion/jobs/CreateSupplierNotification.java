package com.copeinca.apicopeincaprov.modules.notificacion.jobs;

import com.copeinca.apicopeincaprov.modules.core.models.entities.SvProveedorEntity;
import com.copeinca.apicopeincaprov.modules.core.repositories.jpa.ISvProveedorRepository;
import com.copeinca.apicopeincaprov.modules.notificacion.models.dto.NotificacionCreacionProveedorDTO;
import com.copeinca.apicopeincaprov.modules.notificacion.service.INotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@Component
public class CreateSupplierNotification {

    private final INotificationService iNotificationService;
    private final ISvProveedorRepository proveedorRepository;

    @Value( "${app.frontend.url:http://localhost:4200}" )
    private String urlBtp;

    //@Scheduled( cron = "0 */5 * * * *" )
    public void executeTask( ) {

        try {

            log.info( "Iniciando job de notificación de creación de proveedores" );

            //--- Obtenemos los proveedores que no hayan sido notificados (primeros 10)
            Pageable pageable = PageRequest.of( 0, 10 );

            List<SvProveedorEntity> suppliers = proveedorRepository.findAll( ( root, query, cb ) -> cb.or(
                    cb.isNull( root.get( "indProveedorNotificadoCreacion" ) ),
                    cb.equal( root.get( "indProveedorNotificadoCreacion" ), false )
            ), pageable ).getContent( );

            if( suppliers.isEmpty( ) ) {
                log.info( "No hay proveedores pendientes de notificación" );
                return;
            }

            log.info( "Se encontraron {} proveedores pendientes de notificación", suppliers.size( ) );

            //--- Por cada proveedor, generamos la notificación
            for( SvProveedorEntity supplier : suppliers ) {

                try {

                    String username = supplier.getEmail( );

                    //--- Construimos el DTO de notificación
                    NotificacionCreacionProveedorDTO input = NotificacionCreacionProveedorDTO.builder( )
                            .recipients( supplier.getEmail( ) )
//                            .recipients( "123456789@yopmail.com" ) //--- TODO: Eliminar para calidad y PRD
                            .companyName( supplier.getNombreFiscal( ) )
                            .username( username )
                            .urlBtp( urlBtp )
                            .build( );

                    //--- Enviamos la notificación
                    iNotificationService.sendCreacionProveedor( input );

                    proveedorRepository.updateIndProveedorNotificadoCreacion( true, supplier.getId( ) );

                    log.info( "Notificación enviada exitosamente al proveedor: {} - {}",
                            supplier.getNroDocumentoIdentidad( ), supplier.getNombreFiscal( ) );

                } catch( Exception e ) {

                    log.error( "Error al enviar notificación al proveedor: {} - {}",
                            supplier.getNroDocumentoIdentidad( ), supplier.getNombreFiscal( ), e );
                    //--- Continuamos con el siguiente proveedor aunque falle uno
                }

            }

            log.info( "Job de notificación de creación de proveedores finalizado" );

        } catch( Exception e ) {

            log.error( "Error general en el job de notificación de creación de proveedores", e );

        }

    }

}