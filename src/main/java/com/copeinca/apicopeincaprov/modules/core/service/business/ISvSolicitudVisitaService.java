/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/business/IEntityService.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.business;

import com.copeinca.apicopeincaprov.integration.Mendix.dtos.imput.ActualizarCasoRequest;
import com.copeinca.apicopeincaprov.integration.Mendix.dtos.imput.GrabaVisitaTerceraRequestDTO;
import com.copeinca.apicopeincaprov.integration.Mendix.dtos.imput.WsActualizaVisitaTerceraRequest;
import com.copeinca.apicopeincaprov.integration.Mendix.dtos.response.ResultadoWSActualiza;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvProveedorCitaRegistroDTO;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvSolicitudVisistaDTO;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvSolicitudVisitaDetalleDTO;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvSolicitudVisistaEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
* Interface de servicio compuesta para SvSolicitudVisistaEntity
* Combina todas las funcionalidades especializadas: consulta, lectura, escritura y CRUD.
* Los desarrolladores pueden implementar solo las interfaces específicas que necesiten
* o usar esta interfaz completa para funcionalidad completa.
*/
public interface ISvSolicitudVisitaService {

    SvProveedorCitaRegistroDTO saveInfo(SvProveedorCitaRegistroDTO solicitud, List<MultipartFile> ahsFilesSSO, List<MultipartFile> ahsFilesQA, List<MultipartFile> ahsFilesAAR, List<MultipartFile> ahsFilesAB, List<MultipartFile> ahsFilesNA, List<MultipartFile> ahsFilesNG, List<MultipartFile> ahsFilesEM, List<MultipartFile> ahsFilesTB) throws Exception;

    SvProveedorCitaRegistroDTO getInfoVisita(String sId);

    void wsActualizaVisitaTercera( SvSolicitudVisistaDTO svSolicitudVisistaDTO );

    ResultadoWSActualiza wsActualizaVisitaTercera( WsActualizaVisitaTerceraRequest request );

    List<SvSolicitudVisitaDetalleDTO> saveDetalle( List<SvSolicitudVisitaDetalleDTO> list);

    GrabaVisitaTerceraRequestDTO getInfoVisitaDto(String sId);

    SvSolicitudVisistaEntity updateIdMendix(String sIdVisita, String sIdMendix);

    ResultadoWSActualiza wsActualizaMendix(ActualizarCasoRequest request, String visitaId);

    // Esta interfaz hereda todos los métodos de las interfaces especializadas
    // Permite flexibilidad máxima en la implementación según las necesidades del negocio

}
