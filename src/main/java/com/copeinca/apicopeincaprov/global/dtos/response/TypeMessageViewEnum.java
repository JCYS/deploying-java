
/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/global/dtos/response/TypeMessageViewEnum.java.p.vm
 */
package com.copeinca.apicopeincaprov.global.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TypeMessageViewEnum {

    INFORMATION("Information"), WARNING("Warning"), SUCCESS("Success"), ERROR("Error"),;

    private final String type;

}