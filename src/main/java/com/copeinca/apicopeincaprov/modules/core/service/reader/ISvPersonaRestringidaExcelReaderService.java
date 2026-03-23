/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/reader/IEntityExcelReaderService.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.reader;

import com.copeinca.apicopeincaprov.modules.core.models.dto.SvPersonaRestringidaDTO;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
* Interface de servicio para lectura de archivos de SvPersonaRestringidaEntity
*/
public interface ISvPersonaRestringidaExcelReaderService {

    /**
    * Leer datos desde archivo CSV.
    */
    List<SvPersonaRestringidaDTO> readFromCsv(InputStream inputStream);

    /**
    * Leer datos desde archivo Excel.
    */
    List<SvPersonaRestringidaDTO> readFromExcel(InputStream inputStream) throws IOException;
}
