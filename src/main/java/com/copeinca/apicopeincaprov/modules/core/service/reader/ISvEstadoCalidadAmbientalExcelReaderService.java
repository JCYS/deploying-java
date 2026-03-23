/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/reader/IEntityExcelReaderService.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.reader;

import com.copeinca.apicopeincaprov.modules.core.models.dto.SvEstadoCalidadAmbientalDTO;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
* Interface de servicio para lectura de archivos de SvEstadoCalidadAmbientalEntity
*/
public interface ISvEstadoCalidadAmbientalExcelReaderService {

    /**
    * Leer datos desde archivo CSV.
    */
    List<SvEstadoCalidadAmbientalDTO> readFromCsv(InputStream inputStream);

    /**
    * Leer datos desde archivo Excel.
    */
    List<SvEstadoCalidadAmbientalDTO> readFromExcel(InputStream inputStream) throws IOException;
}
