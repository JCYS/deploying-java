/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/business/IEntityService.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.business;

import com.copeinca.apicopeincaprov.modules.core.models.dto.SvProveedorDTO;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvSedeDTO;

import java.util.List;

/**
* Interface de servicio compuesta para SvProveedorEntity
* Combina todas las funcionalidades especializadas: consulta, lectura, escritura y CRUD.
* Los desarrolladores pueden implementar solo las interfaces específicas que necesiten
* o usar esta interfaz completa para funcionalidad completa.
*/
public interface ISvProveedorService {

    // Esta interfaz hereda todos los métodos de las interfaces especializadas
    // Permite flexibilidad máxima en la implementación según las necesidades del negocio
    public SvProveedorDTO getProv(String sEmail) throws Exception;

    void updateSsoEvaluation( SvProveedorDTO svProveedorDTO );

    void updateCaEvaluation( SvProveedorDTO svProveedorDTO );

    List<SvProveedorDTO> isExist(List<SvProveedorDTO> result, List<SvSedeDTO> aSedes);

    List<SvProveedorDTO> saveAllAndValidation(List<SvProveedorDTO> list);

    SvProveedorDTO getProvByRuc(String ruc);
}
