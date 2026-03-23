package com.copeinca.apicopeincaprov.modules.notificacion.models.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmailInputDTO {
    @NotEmpty
    private String recipients;

    private String emailSubject;

    private String emailBody;
}
