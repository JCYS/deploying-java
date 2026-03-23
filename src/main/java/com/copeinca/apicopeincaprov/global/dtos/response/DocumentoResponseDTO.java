
/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/global/dtos/response/DocumentoResponseDTO.java.p.vm
 */
package com.copeinca.apicopeincaprov.global.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DocumentoResponseDTO implements Serializable {

    private String status;
    private String code;
    private String newCode;
    private String observaciones;

    List<DocumentoResponseDetailDTO> detalles;
}