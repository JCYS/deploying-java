package com.copeinca.apicopeincaprov.integration.mail.smtp.models.custom;

import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmailConfig implements Serializable {


    private String name;
    private String from;
    private String description;
    private String subject;
    private String eventCode;
    private Boolean enable;

}