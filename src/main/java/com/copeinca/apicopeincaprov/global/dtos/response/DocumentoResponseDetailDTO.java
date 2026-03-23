
/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/global/dtos/response/DocumentoResponseDetailDTO.java.p.vm
 */
package com.copeinca.apicopeincaprov.global.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DocumentoResponseDetailDTO implements Serializable {

    private String noDocumento;
    private String tipoLinea;
    private String codError;
    private String campo;
    private String observacion;
}