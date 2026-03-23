package com.copeinca.apicopeincaprov.modules.notificacion.models.dto;

import lombok.*;

/**
 * DTO para notificación de creación de proveedor
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificacionCreacionProveedorDTO {
    private String recipients;
    private String companyName;
    private String username;
    private String urlBtp;
}

