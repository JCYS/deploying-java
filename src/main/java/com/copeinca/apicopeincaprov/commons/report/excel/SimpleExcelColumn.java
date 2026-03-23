
/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/commons/report/excel/SimpleExcelColumn.java.p.vm
 */
package com.copeinca.apicopeincaprov.commons.report.excel;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SimpleExcelColumn {

    private IExcelColumnPropertiesEnum excelColumnProperties;
    private Object value;

}
