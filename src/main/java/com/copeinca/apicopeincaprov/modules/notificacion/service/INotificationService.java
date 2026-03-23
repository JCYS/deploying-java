package com.copeinca.apicopeincaprov.modules.notificacion.service;

import com.copeinca.apicopeincaprov.global.dtos.response.CustomMessageView;
import com.copeinca.apicopeincaprov.integration.mail.smtp.models.custom.NotifyInput;
import com.copeinca.apicopeincaprov.modules.notificacion.models.dto.*;

public interface INotificationService {

    void sendMail( NotifyInput input );

    // Cuando se crea proveedor
    void sendCreacionProveedor( String proveedorId );

    void sendCreacionProveedor( NotificacionCreacionProveedorDTO dto );

    // Cuando se evalúa proveedor
    void sendEvaluacionProveedor( NotificacionEvaluacionProveedorDTO dto );

    void sendEvaluacionProveedor( String proveedorId );

    // Cuando modifica sus datos el proveedor
    void sendModificacionDatos( NotificacionModificacionDatosDTO dto );

    void sendModificacionDatos( String proveedorId );

    // Cuando proveedor solicita visita programada o reenvía tras rechazo
    void sendSolicitudVisita( NotificacionSolicitudVisitaDTO dto );

    void sendSolicitudVisita( String solicitudVisitaId );

    // Cuando SSO o Calidad Ambiental aprueba visita (Para proveedor)
    void sendAprobacionVisitaProveedor( NotificacionAprobacionVisitaProveedorDTO dto );

    void sendAprobacionVisitaProveedor( String solicitudVisitaId );

    void sendAprobacionVisitaSsoQa( NotificacionAprobacionVisitaSsoQaDTO dto );

    void sendAprobacionVisitaSsoQa( String solicitudVisitaId );

    // Cuando pasa a POR APROBAR VISITADO (Para el visitado)
    void sendAprobacionVisitaVisitado( NotificacionAprobacionVisitaVisitadoDTO dto );

    void sendAprobacionVisitaVisitado( String solicitudVisitaId );

    // Como proveedor cancelo solicitud de visita
    void sendCancelacionVisita( NotificacionCancelacionVisitaDTO dto );

    void sendCancelacionVisita( String solicitudVisitaId );

    void sendEvaluacionProveedorWithRol(String proveedorId, String rol);

    void sendHabilitarProveedor(String proveedorId);

    CustomMessageView sendModificacionDatosWithMessage(String proveedorId);
}
