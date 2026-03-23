/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/business/IEntityService.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.business;

import com.copeinca.apicopeincaprov.modules.core.models.dto.SvAdjuntoDTO;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvAdjuntosIndexDto;

import java.util.List;

/**
* Interface de servicio compuesta para SvAdjuntoEntity
* Combina todas las funcionalidades especializadas: consulta, lectura, escritura y CRUD.
* Los desarrolladores pueden implementar solo las interfaces específicas que necesiten
* o usar esta interfaz completa para funcionalidad completa.
*/
public interface ISvAdjuntoService {

    // Esta interfaz hereda todos los métodos de las interfaces especializadas
    // Permite flexibilidad máxima en la implementación según las necesidades del negocio

    List<SvAdjuntosIndexDto>  getAdjuntosBYProvId(String sId);

}
