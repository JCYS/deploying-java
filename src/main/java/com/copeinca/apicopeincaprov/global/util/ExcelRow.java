
/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/global/util/ExcelRow.java.p.vm
 */
package com.copeinca.apicopeincaprov.global.util;

import java.util.List;

/*@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes( {
        @JsonSubTypes.Type( value = RecaudacionExcelRow.class, name = "recaudacionExcelRow" ),
} )*/
@FunctionalInterface
public interface ExcelRow {

    List<String> getColumnValues();

}