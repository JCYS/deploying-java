package com.copeinca.apicopeincaprov.modules.notificacion.models.dto;

import lombok.*;

import java.util.List;

/**
 * DTO para notificación de modificación de datos del proveedor
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificacionModificacionDatosDTO {
    private String recipients;
    private String companyName;
    private String allowedSites;
    private String statusSso;
    private String ssoDocsCount;
    private String highRisk;
    private String highRiskDocsCount;
    private String onboard;
    private String onboardDocsCount;
    private String statusEnvironmentalQuality;
    private String environmentalDocsCount;
    private List<TrabajadorEmailDTO> workers;
    private String urlBtp;
    private String has_vigencia_sso;
    private String has_vigencia_ca;
    private String date_has_vigencia_sso;
    private String date_has_vigencia_ca;

}

