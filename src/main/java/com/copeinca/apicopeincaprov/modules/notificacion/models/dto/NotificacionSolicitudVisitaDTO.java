package com.copeinca.apicopeincaprov.modules.notificacion.models.dto;

import lombok.*;

import java.util.List;

/**
 * DTO para notificación de solicitud de visita programada
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificacionSolicitudVisitaDTO {
    private String recipients;
    private String companyName;
    private String site;
    private String visitedUser;
    private String startDateTime;
    private String endDateTime;
    private String serviceOrder;
    private String visitDescription;
    private String specialObservations;
    private String areasToVisit;
    private String zonesToVisit;
    private String highRisk;
    private String onboard;
    private String scaffolding;
    private String crane;
    private String mobileEquipment;
    private String divingWork;
    private List<TrabajadorEmailDTO> workers;
    private String urlBtp;
    private String codVisita;
}

