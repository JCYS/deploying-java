package com.copeinca.apicopeincaprov.modules.notificacion.models.dto;

import lombok.*;

/**
 * DTO para representar trabajadores en emails
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrabajadorEmailDTO {
    private String dni;
    private String fullName;
    private String statusSso;
    private String reasonSso;
    private String docsCount;
    private String highRisk;
    private String highRiskDocsCount;
    private String preventionist;
    private String preventionistDocsCount;
}

