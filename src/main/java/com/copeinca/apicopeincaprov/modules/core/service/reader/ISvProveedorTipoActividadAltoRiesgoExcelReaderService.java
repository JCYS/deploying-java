/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/reader/IEntityExcelReaderService.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.reader;

import com.copeinca.apicopeincaprov.modules.core.models.dto.SvProveedorTipoActividadAltoRiesgoDTO;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
* Interface de servicio para lectura de archivos de SvProveedorTipoActividadAltoRiesgoEntity
*/
public interface ISvProveedorTipoActividadAltoRiesgoExcelReaderService {

    /**
    * Leer datos desde archivo CSV.
    */
    List<SvProveedorTipoActividadAltoRiesgoDTO> readFromCsv(InputStream inputStream);

    /**
    * Leer datos desde archivo Excel.
    */
    List<SvProveedorTipoActividadAltoRiesgoDTO> readFromExcel(InputStream inputStream) throws IOException;
}
