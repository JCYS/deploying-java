package com.copeinca.apicopeincaprov.modules.notificacion.models.dto;

import lombok.*;

import java.util.List;

/**
 * DTO para notificación de evaluación de proveedor
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificacionEvaluacionProveedorDTO {
    private String recipients;
    private String companyName;
    private String username;
    private String urlBtp;
    private String statusSso;
    private String reasonSso;
    private String statusEnvironmentalQuality;
    private String reasonEnvironmentalQuality;
    private List<TrabajadorEmailDTO> workers;
    private String rolUser;

    private String allowedSites;
    private String ssoDocsCount;
    private String highRisk;
    private String highRiskDocsCount;
    private String onboard;
    private String onboardDocsCount;
    private String environmentalDocsCount;
    private String has_vigencia_sso;
    private String has_vigencia_ca;
    private String date_has_vigencia_sso;
    private String date_has_vigencia_ca;
}

