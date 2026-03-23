package com.copeinca.apicopeincaprov.integration.mail.smtp.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EventNotifyEnum {

    //---------------------------------------------------------
    // Notificaciones de Control de Proveedores
    CTPX_HABILITAR_PROVEEDOR(
            "CTPX_HABILITAR_PROVEEDOR",
            "CTRLPROV ${company_name}",
            "NotificacionHabilitacionProveedor.html"
    ),
    CTP2_CREACION_PROVEEDOR(
            "CTP2_CREACION_PROVEEDOR",
            "CTRLPROV ${company_name}",
            "NotificacionCreacionProveedor.html"
    ),

    CTP3_CTP4_EVALUACION_PROVEEDOR(
            "CTP3_CTP4_EVALUACION_PROVEEDOR",
            "CTRLPROV ${company_name}",
            "NotificacionEvaluacionProveedor.html"
    ),

    CTP8_MODIFICACION_DATOS(
            "CTP8_MODIFICACION_DATOS",
            "CTRLPROV ${company_name}",
            "NotificacionModificacionDatosProveedor.html"
    ),

    //---------------------------------------------------------
    // Notificaciones de Solicitud de Visitas
    SVT1_SVT4_SOLICITUD_VISITA(
            "SVT1_SVT4_SOLICITUD_VISITA",
            "SOLICVISITA PROVEEDOR ${cod_visita} - ${company_name} ",
            "NotificacionSolicitudVisita.html"
    ),

    SVT2_SVT3_APROBACION_VISITA_PROVEEDOR(
            "SVT2_SVT3_APROBACION_VISITA_PROVEEDOR",
            "SOLICVISITA PROVEEDOR ${cod_visita} - ${company_name}",
            "NotificacionAprobacionVisitaProveedor.html"
    ),

    SVT2_SVT3_APROBACION_VISITA_SSO_QA(
            "SVT2_SVT3_APROBACION_VISITA_SSO_QA",
            "SOLICVISITA PROVEEDOR ${cod_visita} - ${company_name} - ${sso_status} - ${environmental_status}",
            "NotificacionAprobacionVisitaSSOQA.html"
    ),

    SVT2_SVT3_APROBACION_VISITA_VISITADO(
            "SVT2_SVT3_APROBACION_VISITA_VISITADO",
            "SOLICVISITA PROVEEDOR ${cod_visita} ${company_name}",
            "NotificacionAprobacionVisitaVisitado.html"
    ),

    SVT8_CANCELACION_VISITA(
            "SVT8_CANCELACION_VISITA",
            "SOLICVISITA PROVEEDOR ${cod_visita} ${company_name}",
            "NotificacionCancelacionVisita.html"
    ),

    //---------------------------------------------------------
    TEST_EMAIL(
            "TEST_EMAIL",
            "Test Email",
            "EmailTemplate.html"
    )
    ;

    private final String code;
    private final String subject;
    private final String template;

}