package com.copeinca.apicopeincaprov.integration.Mendix.service.interfaces;


import com.copeinca.apicopeincaprov.integration.Mendix.dtos.imput.ActualizarCasoRequest;
import com.copeinca.apicopeincaprov.integration.Mendix.dtos.imput.FiltroConsultaRequest;
import com.copeinca.apicopeincaprov.integration.Mendix.dtos.imput.GrabaVisitaTerceraRequestDTO;
import com.copeinca.apicopeincaprov.integration.Mendix.dtos.response.*;
import com.copeinca.apicopeincaprov.integration.Mendix.pojos.*;

import java.io.IOException;
import java.util.List;


public interface ClientService {
    List<EquiposPojo> getEquipos() throws IOException;
    List<SedesPojo> getSedes() throws IOException;
    List<AreasPojo> getAreas() throws IOException;

    List<AreasPojo> getAreasBySede(String sede) throws IOException;

    List<ZonasPojo> getZonas() throws IOException;;
    List<ZonasPojo> getZonasBySede(String sede) throws IOException;;
    List<UMPojo> getUM() throws IOException;
    List<DocTipoPojo>  getDocTipo() throws IOException;
    List<DocPlantillaPojo> getDocPlantilla() throws IOException;

    List<PersonaVisitadaPojo> getPersonaVisitadas( ) throws IOException;

    List<PersonaVisitadaPojo> getPersonaVisitadas( String sede ) throws IOException;

    OutputResponse OutputResponse() throws IOException;

    OutputResponse wsRecuperaProveedoresTrabajadores( FiltroConsultaRequest request );

    GrabaVisitaTerceraResponseDTO wsGrabaVisitaTercera(GrabaVisitaTerceraRequestDTO dto) throws IOException;

    ActualizarCasoODataResponse wsActualizaVisitaMendix(ActualizarCasoRequest dto) throws IOException;
}
