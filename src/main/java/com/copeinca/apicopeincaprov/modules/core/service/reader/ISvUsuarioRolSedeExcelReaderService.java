/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/reader/IEntityExcelReaderService.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.reader;

import com.copeinca.apicopeincaprov.modules.core.models.dto.SvUsuarioRolSedeDTO;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
* Interface de servicio para lectura de archivos de SvUsuarioRolSedeEntity
*/
public interface ISvUsuarioRolSedeExcelReaderService {

    /**
    * Leer datos desde archivo CSV.
    */
    List<SvUsuarioRolSedeDTO> readFromCsv(InputStream inputStream);

    /**
    * Leer datos desde archivo Excel.
    */
    List<SvUsuarioRolSedeDTO> readFromExcel(InputStream inputStream) throws IOException;
}
