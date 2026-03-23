
/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/commons/report/excel/IDefineColumnReport.java.p.vm
 */
package com.copeinca.apicopeincaprov.commons.report.excel;

import java.util.List;

public interface IDefineColumnReport<T> {

    List<SimpleExcelColumn> defineColumns(T data);

}
