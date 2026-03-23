
/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/global/dtos/input/SortField.java.p.vm
 */
package com.copeinca.apicopeincaprov.global.dtos.input;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SortField {

    private String field; // Campo a ordenar
    private SortDirection direction; // ASC o DESC

}