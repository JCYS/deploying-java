package com.copeinca.apicopeincaprov.integration.CloudIdentityService.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true) // 🔥 ignora todo lo demás
public class ScimUserDTO {

    private String userName;
}