package com.copeinca.apicopeincaprov.integration.Mendix.service;

import com.copeinca.apicopeincaprov.commons.models.FilterOperation;
import com.copeinca.apicopeincaprov.commons.models.OperatorEnum;
import com.copeinca.apicopeincaprov.global.dtos.response.PagedRequest;
import com.copeinca.apicopeincaprov.global.util.DateTimeUtil;
import com.copeinca.apicopeincaprov.integration.Mendix.factory.DestinationMendixFactory;
import com.copeinca.apicopeincaprov.integration.Mendix.dtos.imput.ActualizarCasoRequest;
import com.copeinca.apicopeincaprov.integration.Mendix.dtos.imput.FiltroConsulta;
import com.copeinca.apicopeincaprov.integration.Mendix.dtos.imput.FiltroConsultaRequest;
import com.copeinca.apicopeincaprov.integration.Mendix.dtos.imput.GrabaVisitaTerceraRequestDTO;
import com.copeinca.apicopeincaprov.integration.Mendix.dtos.response.*;
import com.copeinca.apicopeincaprov.integration.Mendix.pojos.*;
import com.copeinca.apicopeincaprov.integration.Mendix.service.interfaces.ClientService;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvPersonaRestringidaDTO;
import com.copeinca.apicopeincaprov.modules.core.models.dto.ViewAdjuntoReportDTO;
import com.copeinca.apicopeincaprov.modules.core.models.dto.ViewProveedorReportDTO;
import com.copeinca.apicopeincaprov.modules.core.models.dto.ViewTrabajadorReportDTO;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.SvPersonaRestringidaFilter;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.ViewAdjuntoReportFilter;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.ViewProveedorReportFilter;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.ViewTrabajadorReportFilter;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvEstadoCalidadAmbientalEntity;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvEstadoSsoEntity;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvTipoDocumentoIdentidadEntity;
import com.copeinca.apicopeincaprov.modules.core.repositories.jpa.ISvEstadoCalidadAmbientalRepository;
import com.copeinca.apicopeincaprov.modules.core.repositories.jpa.ISvEstadoSsoRepository;
import com.copeinca.apicopeincaprov.modules.core.repositories.jpa.ISvTipoDocumentoIdentidadRepository;
import com.copeinca.apicopeincaprov.modules.core.service.business.ISvSolicitudVisitaService;
import com.copeinca.apicopeincaprov.modules.core.service.query.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sap.cloud.sdk.cloudplatform.connectivity.HttpClientAccessor;
import com.sap.cloud.sdk.cloudplatform.connectivity.HttpDestination;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//import static sun.security.krb5.internal.ktab.KeyTab.normalize;

@Service
@RequiredArgsConstructor
public class ClientImpl implements ClientService {

    private final ISvSedeQueryService iSedeQueryService;
    private final IViewAdjuntoReportQueryService iViewAdjuntoReportQueryService;
    private final ISvProveedorQueryService iProveedorQueryService;
    private final ISvTrabajadorQueryService iTrabajadorQueryService;
    private final IViewProveedorReportQueryService iViewProveedorReportQueryService;
    private final ISvPersonaRestringidaQueryService iPersonaRestringidaQueryService;
    private final IViewTrabajadorReportQueryService iViewTrabajadorReportQueryService;

    private final ISvEstadoSsoRepository iSvEstadoSsoRepository;
    private final ISvEstadoCalidadAmbientalRepository iSvEstadoCalidadAmbientalRepository;
    private final ISvTipoDocumentoIdentidadRepository isvTipoDocumentoIdentidadRepository;


    private final ISvSolicitudVisitaService iSvSolicitudVisitaService;

    private final DestinationMendixFactory destinationMendixFactory;

    @Override
    public List<EquiposPojo> getEquipos() throws IOException {
        HttpDestination destination = destinationMendixFactory.create();

        Map<String,Object> filtro = new HashMap<>();
        filtro.put("Codigo", "");

        Map<String,Object> body = new HashMap<>();
        body.put("FiltroConsulta", filtro);

        ObjectMapper mapper = new ObjectMapper();
        String jsonBody = mapper.writeValueAsString(body);

        // 3. POST REQUEST
        HttpPost post =
                new HttpPost("/odata/accesosedesconsulta/v1/wsGetEquipos");

        post.setHeader("Content-Type", "application/json");
        post.setEntity(
                new StringEntity(jsonBody, ContentType.APPLICATION_JSON)
        );

        // 4. EJECUTAR
        HttpResponse response =
                HttpClientAccessor
                        .getHttpClient(destination)
                        .execute(post);
        // 5. LEER RESPUESTA
        String jsonResponse =
                EntityUtils.toString(response.getEntity());

//        System.out.println(jsonResponse);
        ObjectMapper mappers = new ObjectMapper();

        JsonNode root = mappers.readTree(jsonResponse);
        JsonNode valueNode = root.get("value");

        List<EquiposPojo> lista =
                mappers.convertValue(
                        valueNode,
                        new TypeReference<List<EquiposPojo>>() {}
                );
        return lista;
//        return "";
    }

    @Override
    public List<SedesPojo> getSedes() throws IOException {
        HttpDestination destination = destinationMendixFactory.create();

        Map<String,Object> filtro = new HashMap<>();
        filtro.put("Ambito", "");

        Map<String,Object> body = new HashMap<>();
        body.put("FiltroConsulta", filtro);

        ObjectMapper mapper = new ObjectMapper();
        String jsonBody = mapper.writeValueAsString(body);

        // 3. POST REQUEST
        HttpPost post =
                new HttpPost("/http/gme/qas/sitioflujos/consultaSedes");

        post.setHeader("Content-Type", "application/json");
        post.setEntity(
                new StringEntity(jsonBody, ContentType.APPLICATION_JSON)
        );

        // 4. EJECUTAR
        HttpResponse response =
                HttpClientAccessor
                        .getHttpClient(destination)
                        .execute(post);

        // 5. LEER RESPUESTA
        String jsonResponse =
                EntityUtils.toString(response.getEntity());

        //System.out.println(jsonResponse);

        ObjectMapper mappers = new ObjectMapper();

        JsonNode root = mappers.readTree(jsonResponse);
        JsonNode valueNode = root.get("value");

        List<SedesPojo> lista =
                mappers.convertValue(
                        valueNode,
                        new TypeReference<List<SedesPojo>>() {}
                );
        return lista;
    }

    @Override
    public List<AreasPojo> getAreas() throws IOException {
        HttpDestination destination = destinationMendixFactory.create();

        Map<String,Object> filtro = new HashMap<>();
        filtro.put("Sede", "CHANCAY");
        filtro.put("Operativa", "");

        Map<String,Object> body = new HashMap<>();
        body.put("FiltroConsulta", filtro);

        ObjectMapper mapper = new ObjectMapper();
        String jsonBody = mapper.writeValueAsString(body);

        // 3. POST REQUEST
        HttpPost post =
                new HttpPost("/http/gme/qas/sitioflujos/consultaAreas");

        post.setHeader("Content-Type", "application/json");
        post.setEntity(
                new StringEntity(jsonBody, ContentType.APPLICATION_JSON)
        );

        // 4. EJECUTAR
        HttpResponse response =
                HttpClientAccessor
                        .getHttpClient(destination)
                        .execute(post);

        // 5. LEER RESPUESTA
        String jsonResponse =
                EntityUtils.toString(response.getEntity());

        //System.out.println(jsonResponse);

        ObjectMapper mappers = new ObjectMapper();

        JsonNode root = mappers.readTree(jsonResponse);
        JsonNode valueNode = root.get("value");

        List<AreasPojo> lista =
                mappers.convertValue(
                        valueNode,
                        new TypeReference<List<AreasPojo>>() {}
                );
        return lista;
    }

    @Override
    public List<AreasPojo> getAreasBySede(String sede) throws IOException {
        HttpDestination destination = destinationMendixFactory.create();

        Map<String,Object> filtro = new HashMap<>();
        filtro.put("Sede", sede);
        filtro.put("Operativa", "");

        Map<String,Object> body = new HashMap<>();
        body.put("FiltroConsulta", filtro);

        ObjectMapper mapper = new ObjectMapper();
        String jsonBody = mapper.writeValueAsString(body);

        // 3. POST REQUEST
        HttpPost post =
                new HttpPost("/http/gme/qas/sitioflujos/consultaAreas");

        post.setHeader("Content-Type", "application/json");
        post.setEntity(
                new StringEntity(jsonBody, ContentType.APPLICATION_JSON)
        );

        // 4. EJECUTAR
        HttpResponse response =
                HttpClientAccessor
                        .getHttpClient(destination)
                        .execute(post);

        // 5. LEER RESPUESTA
        String jsonResponse =
                EntityUtils.toString(response.getEntity());

        //System.out.println(jsonResponse);

        ObjectMapper mappers = new ObjectMapper();

        JsonNode root = mappers.readTree(jsonResponse);
        JsonNode valueNode = root.get("value");

        List<AreasPojo> lista =
                mappers.convertValue(
                        valueNode,
                        new TypeReference<List<AreasPojo>>() {}
                );
        return lista;
    }

    @Override
    public List<ZonasPojo> getZonas() throws IOException {
        HttpDestination destination = destinationMendixFactory.create();

        Map<String,Object> filtro = new HashMap<>();
        filtro.put("Sede", "CHANCAY");
        filtro.put("Operativa", "");

        Map<String,Object> body = new HashMap<>();
        body.put("FiltroConsulta", filtro);

        ObjectMapper mapper = new ObjectMapper();
        String jsonBody = mapper.writeValueAsString(body);

        // 3. POST REQUEST
        HttpPost post =
                new HttpPost("/http/gme/qas/sitioflujos/consultaZonas");

        post.setHeader("Content-Type", "application/json");
        post.setEntity(
                new StringEntity(jsonBody, ContentType.APPLICATION_JSON)
        );

        // 4. EJECUTAR
        HttpResponse response =
                HttpClientAccessor
                        .getHttpClient(destination)
                        .execute(post);

        // 5. LEER RESPUESTA
        String jsonResponse =
                EntityUtils.toString(response.getEntity());

        //System.out.println(jsonResponse);

        ObjectMapper mappers = new ObjectMapper();

        JsonNode root = mappers.readTree(jsonResponse);
        JsonNode valueNode = root.get("value");

        List<ZonasPojo> lista =
                mappers.convertValue(
                        valueNode,
                        new TypeReference<List<ZonasPojo>>() {}
                );
        return lista;
    }

    @Override
    public List<ZonasPojo> getZonasBySede(String sede) throws IOException {
        HttpDestination destination = destinationMendixFactory.create();

        Map<String,Object> filtro = new HashMap<>();
        filtro.put("Sede", sede);
        filtro.put("Operativa", "");

        Map<String,Object> body = new HashMap<>();
        body.put("FiltroConsulta", filtro);

        ObjectMapper mapper = new ObjectMapper();
        String jsonBody = mapper.writeValueAsString(body);

        // 3. POST REQUEST
        HttpPost post =
                new HttpPost("/http/gme/qas/sitioflujos/consultaZonas");

        post.setHeader("Content-Type", "application/json");
        post.setEntity(
                new StringEntity(jsonBody, ContentType.APPLICATION_JSON)
        );

        // 4. EJECUTAR
        HttpResponse response =
                HttpClientAccessor
                        .getHttpClient(destination)
                        .execute(post);

        // 5. LEER RESPUESTA
        String jsonResponse =
                EntityUtils.toString(response.getEntity());

        //System.out.println(jsonResponse);

        ObjectMapper mappers = new ObjectMapper();

        JsonNode root = mappers.readTree(jsonResponse);
        JsonNode valueNode = root.get("value");

        List<ZonasPojo> lista =
                mappers.convertValue(
                        valueNode,
                        new TypeReference<List<ZonasPojo>>() {}
                );
        return lista;
    }

    @Override
    public List<UMPojo> getUM() throws IOException {
        HttpDestination destination = destinationMendixFactory.create();

        Map<String,Object> filtro = new HashMap<>();
        filtro.put("Codigo", "");
//        filtro.put("Operativa", "");

        Map<String,Object> body = new HashMap<>();
        body.put("FiltroConsulta", filtro);

        ObjectMapper mapper = new ObjectMapper();
        String jsonBody = mapper.writeValueAsString(body);

        // 3. POST REQUEST
        HttpPost post =
                new HttpPost("/odata/accesosedesconsulta/v1/wsGetUnidades");

        post.setHeader("Content-Type", "application/json");
        post.setEntity(
                new StringEntity(jsonBody, ContentType.APPLICATION_JSON)
        );

        // 4. EJECUTAR
        HttpResponse response =
                HttpClientAccessor
                        .getHttpClient(destination)
                        .execute(post);

        // 5. LEER RESPUESTA
        String jsonResponse =
                EntityUtils.toString(response.getEntity());

//        System.out.println(jsonResponse);

        ObjectMapper mappers = new ObjectMapper();

        JsonNode root = mappers.readTree(jsonResponse);
        JsonNode valueNode = root.get("value");

        List<UMPojo> lista =
                mappers.convertValue(
                        valueNode,
                        new TypeReference<List<UMPojo>>() {}
                );
        return lista;
    }

    @Override
    public List<DocTipoPojo> getDocTipo() throws IOException {
        HttpDestination destination = destinationMendixFactory.create();

        Map<String,Object> filtro = new HashMap<>();
        filtro.put("TipoDocumento", "");
//        filtro.put("Operativa", "");

        Map<String,Object> body = new HashMap<>();
        body.put("FiltroConsulta", filtro);

        ObjectMapper mapper = new ObjectMapper();
        String jsonBody = mapper.writeValueAsString(body);

        // 3. POST REQUEST
        HttpPost post =
                new HttpPost("/odata/accesosedesconsulta/v1/wsGetTipoDocumentoIdentidad");

        post.setHeader("Content-Type", "application/json");
        post.setEntity(
                new StringEntity(jsonBody, ContentType.APPLICATION_JSON)
        );

        // 4. EJECUTAR
        HttpResponse response =
                HttpClientAccessor
                        .getHttpClient(destination)
                        .execute(post);

        // 5. LEER RESPUESTA
        String jsonResponse =
                EntityUtils.toString(response.getEntity());

//        System.out.println(jsonResponse);

        ObjectMapper mappers = new ObjectMapper();

        JsonNode root = mappers.readTree(jsonResponse);
        JsonNode valueNode = root.get("value");

        List<DocTipoPojo> lista =
                mappers.convertValue(
                        valueNode,
                        new TypeReference<List<DocTipoPojo>>() {}
                );
        return lista;
    }

    @Override
    public List<DocPlantillaPojo> getDocPlantilla() throws IOException {
        HttpDestination destination = destinationMendixFactory.create();

        Map<String,Object> filtro = new HashMap<>();
        filtro.put("Descripcion", "");
        filtro.put("Ambito", "");
        filtro.put("Influencia", "");
        filtro.put("Dependencia", "");
        filtro.put("CampoPlantillaLF", "");
        filtro.put("Obligatorio", "");
        filtro.put("Vencimiento", "");

//        filtro.put("Operativa", "");

        Map<String,Object> body = new HashMap<>();
        body.put("FiltroConsulta", filtro);

        ObjectMapper mapper = new ObjectMapper();
        String jsonBody = mapper.writeValueAsString(body);

        // 3. POST REQUEST
        HttpPost post =
                new HttpPost("/http/gme/qas/usuariosedes/consultaTipoDocumentoPlantilla");

        post.setHeader("Content-Type", "application/json");
        post.setEntity(
                new StringEntity(jsonBody, ContentType.APPLICATION_JSON)
        );

        // 4. EJECUTAR
        HttpResponse response =
                HttpClientAccessor
                        .getHttpClient(destination)
                        .execute(post);

        // 5. LEER RESPUESTA
        String jsonResponse =
                EntityUtils.toString(response.getEntity());

        System.out.println(jsonResponse);

        ObjectMapper mappers = new ObjectMapper();

        JsonNode root = mappers.readTree(jsonResponse);
        JsonNode valueNode = root.get("value");

        List<DocPlantillaPojo> lista =
                mappers.convertValue(
                        valueNode,
                        new TypeReference<List<DocPlantillaPojo>>() {}
                );
        return lista;
    }

    @Override
    public List<PersonaVisitadaPojo> getPersonaVisitadas() throws IOException {
        HttpDestination destination = destinationMendixFactory.create();

        Map<String,Object> filtro = new HashMap<>();
        filtro.put("Sede", "");
//        filtro.put("Descripcion", "");
//        filtro.put("Ambito", "");
//        filtro.put("Influencia", "");
//        filtro.put("Dependencia", "");
//        filtro.put("CampoPlantillaLF", "");
//        filtro.put("Obligatorio", "");
//        filtro.put("Vencimiento", "");

        Map<String,Object> body = new HashMap<>();
        body.put("FiltroConsulta", filtro);

        ObjectMapper mapper = new ObjectMapper();
        String jsonBody = mapper.writeValueAsString(body);

        // 3. POST REQUEST
        HttpPost post =
                new HttpPost("/odata/accesosedesconsulta/v1/wsGetVisitados");

        post.setHeader("Content-Type", "application/json");
        post.setEntity(
                new StringEntity(jsonBody, ContentType.APPLICATION_JSON)
        );

        // 4. EJECUTAR
        HttpResponse response =
                HttpClientAccessor
                        .getHttpClient(destination)
                        .execute(post);

        // 5. LEER RESPUESTA
        String jsonResponse =
                EntityUtils.toString(response.getEntity());

        System.out.println(jsonResponse);

        ObjectMapper mappers = new ObjectMapper();

        JsonNode root = mappers.readTree(jsonResponse);
        JsonNode valueNode = root.get("value");

        List<PersonaVisitadaPojo> lista =
                mappers.convertValue(
                        valueNode,
                        new TypeReference<List<PersonaVisitadaPojo>>() {}
                );
        return lista;
    }

    @Override
    public List<PersonaVisitadaPojo> getPersonaVisitadas( String sede ) throws IOException {
        HttpDestination destination = destinationMendixFactory.create();

        Map<String,Object> filtro = new HashMap<>();
        filtro.put("Sede", sede);
//        filtro.put("Descripcion", "");
//        filtro.put("Ambito", "");
//        filtro.put("Influencia", "");
//        filtro.put("Dependencia", "");
//        filtro.put("CampoPlantillaLF", "");
//        filtro.put("Obligatorio", "");
//        filtro.put("Vencimiento", "");

        Map<String,Object> body = new HashMap<>();
        body.put("FiltroConsulta", filtro);

        ObjectMapper mapper = new ObjectMapper();
        String jsonBody = mapper.writeValueAsString(body);

        // 3. POST REQUEST
        HttpPost post =
                new HttpPost("/odata/accesosedesconsulta/v1/wsGetVisitados");

        post.setHeader("Content-Type", "application/json");
        post.setEntity(
                new StringEntity(jsonBody, ContentType.APPLICATION_JSON)
        );

        // 4. EJECUTAR
        HttpResponse response =
                HttpClientAccessor
                        .getHttpClient(destination)
                        .execute(post);

        // 5. LEER RESPUESTA
        String jsonResponse =
                EntityUtils.toString(response.getEntity());

        System.out.println(jsonResponse);

        ObjectMapper mappers = new ObjectMapper();

        JsonNode root = mappers.readTree(jsonResponse);
        JsonNode valueNode = root.get("value");

        List<PersonaVisitadaPojo> lista =
                mappers.convertValue(
                        valueNode,
                        new TypeReference<List<PersonaVisitadaPojo>>() {}
                );
        return lista;
    }

    @Override
    public OutputResponse OutputResponse() throws IOException {
        Sedes sedes = Sedes.builder()
                .Sede(List.of(
                        SedeItem.builder().Sede("Bayovar").build(),
                        SedeItem.builder().Sede("Chancay").build(),
                        SedeItem.builder().Sede("Supe").build()
                ))
                .build();

        // Docs Proveedor SSO
        DocumentosProveedorSSO docsProveedorSSO = DocumentosProveedorSSO.builder()
                .docsProveedorSSO(List.of(
                        Documento.builder()
                                .ID("4324")
                                .Ambito("SSO")
                                .tipoDeDocumento("Programa de simulacros")
                                .Nombre("Procedimiento de investigacion consolidado 20251.pdf")
                                .fechaDocumento("01/09/2025")
                                .fechaVencimiento("31/12/2025")
                                .Revision("")
                                .build(),
                        Documento.builder()
                                .ID("4355")
                                .Ambito("SSO")
                                .tipoDeDocumento("Politica de SST")
                                .Nombre("Politica de SST 20251.pdf")
                                .fechaDocumento("01/09/2025")
                                .fechaVencimiento("")
                                .Revision("")
                                .build()
                ))
                .build();

        // Alto riesgo proveedor
        TipoAltoRiesgoProveedor tipoAltoRiesgoProveedor = TipoAltoRiesgoProveedor.builder()
                .AltoRiesgoProveedor(List.of(
                        ARProveedorItem.builder().ARProveedor("Trabajo en alturas").build(),
                        ARProveedorItem.builder().ARProveedor("Trabajo de buceo").build()
                ))
                .docsSSOProveedorAltoRiesgo(List.of(
                        Documento.builder()
                                .ID("3324")
                                .Ambito("SSO")
                                .tipoDeDocumento("Procedimiento AR altura")
                                .Nombre("Procedimiento altura 20251.pdf")
                                .fechaDocumento("01/09/2025")
                                .fechaVencimiento("31/12/2025")
                                .Revision("")
                                .build(),
                        Documento.builder()
                                .ID("3354")
                                .Ambito("SSO")
                                .tipoDeDocumento("Procedimiento AR buceo")
                                .Nombre("Adaptabilidad en altura 20251.pdf")
                                .fechaDocumento("01/09/2025")
                                .fechaVencimiento("31/12/2025")
                                .Revision("")
                                .build()
                ))
                .build();

        // Docs a bordo proveedor
        DocumsABordoProveedor documsABordoProveedor = DocumsABordoProveedor.builder()
                .DocsABordoProveedor(List.of(
                        Documento.builder()
                                .ID("13324")
                                .Ambito("SSO")
                                .tipoDeDocumento("Permiso de DICAPI")
                                .Nombre("Permiso de DICAPI 20251.pdf")
                                .fechaDocumento("01/09/2025")
                                .fechaVencimiento("31/12/2025")
                                .Revision("")
                                .build()
                ))
                .build();

        // Docs calidad ambiental proveedor
        DocumentosProveedorCalidadAmbiental docsCalidadAmbiental = DocumentosProveedorCalidadAmbiental.builder()
                .docsCalidadAmbientalProveedor(List.of(
                        Documento.builder()
                                .ID("23324")
                                .Ambito("Calidad Ambiental")
                                .tipoDeDocumento("Matriz IAA")
                                .Nombre("Procedimiento CA 20251.pdf")
                                .fechaDocumento("01/09/2025")
                                .fechaVencimiento("31/12/2025")
                                .Revision("")
                                .build(),
                        Documento.builder()
                                .ID("23354")
                                .Ambito("Calidad Ambiental")
                                .tipoDeDocumento("Registro de capacitacion")
                                .Nombre("Adaptabilidad CA 20251.pdf")
                                .fechaDocumento("01/09/2025")
                                .fechaVencimiento("")
                                .Revision("")
                                .build()
                ))
                .build();

        // Documentos trabajador SSO
        DocumentosTrabajadorSSO docsTrabajadorSSO = DocumentosTrabajadorSSO.builder()
                .docsTrabajadorSSO(List.of(
                        Documento.builder()
                                .ID("33324")
                                .Ambito("SSO")
                                .tipoDeDocumento("EMO")
                                .Nombre("EMO 20251.pdf")
                                .fechaDocumento("01/09/2025")
                                .fechaVencimiento("31/12/2025")
                                .Revision("")
                                .build(),
                        Documento.builder()
                                .ID("33354")
                                .Ambito("SSO")
                                .tipoDeDocumento("Induccion")
                                .Nombre("Induccion 20251.pdf")
                                .fechaDocumento("01/09/2025")
                                .fechaVencimiento("31/12/2025")
                                .Revision("")
                                .build()
                ))
                .build();

        // Alto riesgo trabajador
        TipoAltoRiesgoTrabajador tipoAltoRiesgoTrabajador = TipoAltoRiesgoTrabajador.builder()
                .AltoRiesgoTrabajador(List.of(
                        ARTrabajadorItem.builder().ARTrabajador("Trabajo en alturas").build(),
                        ARTrabajadorItem.builder().ARTrabajador("Trabajo de buceo").build()
                ))
                .docsSSOTrabajadorAltoRiesgo(List.of(
                        Documento.builder()
                                .ID("3324")
                                .Ambito("SSO")
                                .tipoDeDocumento("Registro de capacitacion trabajo en altura")
                                .Nombre("Procedimiento altura 20251.pdf")
                                .fechaDocumento("01/09/2025")
                                .fechaVencimiento("31/12/2025")
                                .Revision("")
                                .build(),
                        Documento.builder()
                                .ID("3354")
                                .Ambito("SSO")
                                .tipoDeDocumento("Registro de capacitacion trabajo de buceo")
                                .Nombre("Adaptabilidad en altura 20251.pdf")
                                .fechaDocumento("01/09/2025")
                                .fechaVencimiento("31/12/2025")
                                .Revision("")
                                .build()
                ))
                .build();

        // Docs prevencionista
        DocsPrevencionistaTbjd docsPrevencionista = DocsPrevencionistaTbjd.builder()
                .DocsPrevenTbjd(List.of(
                        Documento.builder()
                                .ID("33324")
                                .Ambito("SSO")
                                .tipoDeDocumento("Curriculum Prevencionista")
                                .Nombre("CV 20251.pdf")
                                .fechaDocumento("01/09/2025")
                                .fechaVencimiento("31/12/2025")
                                .Revision("")
                                .build()
                ))
                .build();

        // Trabajador
        Trabajador trabajador = Trabajador.builder()
                .TipoDocumento("DNI")
                .NumeroDocumento("06803919")
                .NombreTrabajador("Ollanta Humala")
                .Restringido("1")
                .EstadoSSO("Apto")
                .MotivoSSO("")
                .FlagFechaVigenciaSSO("0")
                .FechaVigenciaSSO("")
                .DocumentosTrabajadorSSO(docsTrabajadorSSO)
                .TipoAltoRiesgoTrabajador(tipoAltoRiesgoTrabajador)
                .Prevencionista("Si")
                .DocsPrevencionistaTbjd(docsPrevencionista)
                .build();

        Trabajadores trabajadores = Trabajadores.builder()
                .Trabajador(List.of(trabajador))
                .build();

        // Value item (el proveedor completo + trabajadores)
        ValueItem item = ValueItem.builder()
                .IDProveedor("3232")
                .RUC("20512868046")
                .RazonSocial("Mi empresa proveedora S.A.")
                .PersonaContacto("Juan Rojas")
                .MailContacto("juan.rojas@proveedor.com")
                .TelefonoContacto("012134000")
                .Email("miproveedor@proveedor.com.pe")
                .Sedes(sedes)
                .EstadoSSOProveedor("Apto")
                .MotivoSSOProveedor("")
                .FlagFechaVigenciaSSOProveedor("0")
                .FechaVigenciaSSOProveedor("")
                .DocumentosProveedorSSO(docsProveedorSSO)
                .ActividadAltoRiesgoProveedor("Si")
                .TipoAltoRiesgoProveedor(tipoAltoRiesgoProveedor)
                .ActividadABordoProveedor("Si")
                .DocumsABordoProveedor(documsABordoProveedor)
                .EstadoCalidadAmbientalProveedor("Apto")
                .MotivoCalidadAmbientalProveedor("")
                .FlagFechaVigenciaCalidadAmbientalProveedor("0")
                .FechaVigenciaCalidadAmbientalProveedor("")
                .DocumentosProveedorCalidadAmbiental(docsCalidadAmbiental)
                .NroTrabajadores(1)
                .Trabajadores(trabajadores)
                .build();

        return OutputResponse.builder()
                .value(List.of(item))
                .build();
    }
    private void agregarFiltro(Map<String, String> filtros, String key, String value) {
        if (value != null && !value.isBlank()) {
            filtros.put(key, value);
        }
    }
    private Map<String, String> getfiltrosValidates(FiltroConsulta filtro) {
        Map<String, String> filtros = new HashMap<>();
                agregarFiltro(filtros, "IDProveedor", filtro.getIDProveedor());
        agregarFiltro(filtros, "RUC", filtro.getRUC());
        agregarFiltro(filtros, "RazonSocial", filtro.getRazonSocial());
        agregarFiltro(filtros, "FechaCreacionInicio", filtro.getFechaCreacionInicio());
        agregarFiltro(filtros, "FechaCreacionFin", filtro.getFechaCreacionFin());
        agregarFiltro(filtros, "FechaVigenciaInicio", filtro.getFechaVigenciaInicio());
        agregarFiltro(filtros, "FechaVigenciaFin", filtro.getFechaVigenciaFin());
        agregarFiltro(filtros, "EstadoProveedorSSO", filtro.getEstadoProveedorSSO());
        agregarFiltro(filtros, "EstadoProveedorCalidadAmbiental", filtro.getEstadoProveedorCalidadAmbiental());
        agregarFiltro(filtros, "TipoDocumentoTrabajador", filtro.getTipoDocumentoTrabajador());
        agregarFiltro(filtros, "NumeroDocumentoTrabajador", filtro.getNumeroDocumentoTrabajador());
        agregarFiltro(filtros, "NombreTrabajador", filtro.getNombreTrabajador());
        agregarFiltro(filtros, "EstadoTrabajadorSSO", filtro.getEstadoTrabajadorSSO());
        return filtros;
    }
    @Override
    public OutputResponse wsRecuperaProveedoresTrabajadores( FiltroConsultaRequest request ) {
//        FiltroConsulta filtro = request.getFiltroConsulta( );
        FiltroConsulta filtro = request.getFiltroConsulta();
        List<ViewProveedorReportDTO> proveedores = new ArrayList<>( );
        DateTimeFormatter formatterFiltro = DateTimeFormatter.ofPattern("yyyy-MM-dd");


        Map<String, SvTipoDocumentoIdentidadEntity> oMapTDI = isvTipoDocumentoIdentidadRepository.findAll().stream().collect(
                Collectors.toUnmodifiableMap(
                        SvTipoDocumentoIdentidadEntity::getName,
                        Function.identity(),
                        (a, b) -> a
                )
        );

        Map<String, SvEstadoCalidadAmbientalEntity> oMapECA = iSvEstadoCalidadAmbientalRepository.findAll().stream().collect(
                Collectors.toUnmodifiableMap(
                        SvEstadoCalidadAmbientalEntity::getName,
                        Function.identity(),
                        (a, b) -> a
                )
        );

        Map<String, SvEstadoSsoEntity> oMapESSO = iSvEstadoSsoRepository.findAll().stream().collect(
                Collectors.toUnmodifiableMap(
                        SvEstadoSsoEntity::getName,
                        Function.identity(),
                        (a, b) -> a
                )
        );
        if( filtro.getTipoDocumentoTrabajador( ) != null) {
            String tipoHomo = Optional.ofNullable(request.getFiltroConsulta().getTipoDocumentoTrabajador())
//                        .map(this::normalize)
                    .map(oMapTDI::get)
                    .map(SvTipoDocumentoIdentidadEntity::getId) // el campo que necesitas
                    .orElse(null);
            if(tipoHomo == null)
                return OutputResponse.builder( )
                        .value( List.of( ) )
                        .build( );

        }
        //lookupSeacch.get()
        request.getFiltroConsulta().setTipoDocumentoTrabajador(
                Optional.ofNullable(request.getFiltroConsulta().getTipoDocumentoTrabajador())
//                        .map(this::normalize)
                        .map(oMapTDI::get)
                        .map(SvTipoDocumentoIdentidadEntity::getId) // el campo que necesitas
                        .orElse(null)
        );
        request.getFiltroConsulta().setEstadoTrabajadorSSO(
                Optional.ofNullable(request.getFiltroConsulta().getEstadoTrabajadorSSO())
//                        .map(this::normalize)
                        .map(oMapESSO::get)
                        .map(SvEstadoSsoEntity::getId) // el campo que necesitas
                        .orElse(null)
        );
        request.getFiltroConsulta().setEstadoProveedorSSO(
                Optional.ofNullable(request.getFiltroConsulta().getEstadoProveedorSSO())
//                        .map(this::normalize)
                        .map(oMapESSO::get)
                        .map(SvEstadoSsoEntity::getId) // el campo que necesitas
                        .orElse(null)
        );
        request.getFiltroConsulta().setEstadoProveedorCalidadAmbiental(
                Optional.ofNullable(request.getFiltroConsulta().getEstadoProveedorCalidadAmbiental())
//                        .map(this::normalize)
                        .map(oMapECA::get)
                        .map(SvEstadoCalidadAmbientalEntity::getId) // el campo que necesitas
                        .orElse(null)
        );

        Map<String, String> filtros = getfiltrosValidates(filtro);

//        proveedores = iViewProveedorReportQueryService.search()
        ViewProveedorReportFilter filter = new ViewProveedorReportFilter();
        ViewTrabajadorReportFilter.ViewTrabajadorReportFilterBuilder filterTrabajador = ViewTrabajadorReportFilter.builder( );

        for (Map.Entry<String, String> entry : filtros.entrySet()) {

            switch (entry.getKey()) {

                case "IDProveedor":

                    break;

                case "RUC":
                    filter.setNroDocumentoIdentidad(List.of(
                            FilterOperation.<String>builder( )
                                    .operator( OperatorEnum.EQ )
                                    .values( List.of( entry.getValue() ) )
                                    .build( )
                    ));
                    break;

                case "RazonSocial":
                    filter.setNombreFiscal(List.of(
                            FilterOperation.<String>builder( )
                                    .operator( OperatorEnum.CONTAINS )
                                    .values( List.of( entry.getValue()  ) )
                                    .build( )
                    ));
                    break;

                case "FechaCreacionInicio":

                    filter.setCreatedDateStart(LocalDate.parse(entry.getValue(), formatterFiltro));
                    break;

                case "FechaCreacionFin":
                    filter.setCreatedDateEnd(LocalDate.parse(entry.getValue(), formatterFiltro));
                    break;

                case "FechaVigenciaInicio":
                    filter.setTrabajadorSSOFechaInicioVigencia(DateTimeUtil.asLocalDate( entry.getValue(), "yyyy-MM-dd"));
                    filterTrabajador.ssoFechaInicioVigencia(List.of(
                            FilterOperation.<LocalDate>builder( )
                                    .operator( OperatorEnum.GE )
                                    .values( List.of(
                                            DateTimeUtil.asLocalDate(
                                                    entry.getValue(),
                                                    "yyyy-MM-dd"
                                            )
                                    ) )
                                    .build( )
                    ));
                    filter.setSsoFechaInicioVigencia(List.of(
                            FilterOperation.<LocalDate>builder( )
                                    .operator( OperatorEnum.GE )
                                    .values( List.of(
                                            DateTimeUtil.asLocalDate(
                                                    entry.getValue(),
                                                    "yyyy-MM-dd"
                                            )
                                    ) )
                                    .build( )
                    ));
                    filter.setCaFechaInicioVigencia(List.of(
                            FilterOperation.<LocalDate>builder( )
                                    .operator( OperatorEnum.GE )
                                    .values( List.of(
                                            DateTimeUtil.asLocalDate(
                                                    entry.getValue(),
                                                    "yyyy-MM-dd"
                                            )
                                    ) )
                                    .build( )
                    ));
                    break;

                case "FechaVigenciaFin":
                    filterTrabajador.ssoFechaFinVigencia(List.of(
                            FilterOperation.<LocalDate>builder( )
                                    .operator( OperatorEnum.GE )
                                    .values( List.of(
                                            DateTimeUtil.asLocalDate(
                                                    entry.getValue(),
                                                    "yyyy-MM-dd"
                                            )
                                    ) )
                                    .build( )
                    ));
                    filter.setTrabajadorSSOFechaFinVigencia(DateTimeUtil.asLocalDate( entry.getValue(), "yyyy-MM-dd"));
                    filter.setSsoFechaFinVigencia(
                            List.of(
                                    FilterOperation.<String>builder( )
                                            .operator( OperatorEnum.LE )
                                            .values( List.of( entry.getValue() ) )
                                            .build( )
                            )
                    );
                    filter.setCaFechaFinVigencia(List.of(
                            FilterOperation.<LocalDate>builder( )
                                    .operator( OperatorEnum.LE )
                                    .values( List.of(
                                            DateTimeUtil.asLocalDate(
                                                    entry.getValue(),
                                                    "yyyy-MM-dd"
                                            )
                                    ) )
                                    .build( )
                    ));
                    break;

                case "EstadoProveedorSSO":
                    filter.setEstadoSsoCode(List.of(
                            FilterOperation.<String>builder( )
                                    .operator( OperatorEnum.EQ )
                                    .values( List.of( entry.getValue() ) )
                                    .build( )
                    ));
                    break;

                case "EstadoProveedorCalidadAmbiental":
                    filter.setEstadoCalidadAmbientalCode(List.of(
                            FilterOperation.<String>builder( )
                                    .operator( OperatorEnum.EQ )
                                    .values( List.of( entry.getValue() ) )
                                    .build( )
                    ) );
                    break;
                case "TipoDocumentoTrabajador":
                    filter.setTrabajadorTipoDocIdentidad(List.of( entry.getValue() ));
                    filterTrabajador.tipoDocumentoIdentidadCode(List.of(
                            FilterOperation.<String>builder( )
                                    .operator( OperatorEnum.EQ )
                                    .values( List.of( entry.getValue() ) )
                                    .build( )
                    ) );
                    break;

                case "NumeroDocumentoTrabajador":
                    filter.setTrabajadorNroDocIdentidad(List.of( entry.getValue() ));
                    filterTrabajador.nroDocumentoIdentidad(List.of(
                            FilterOperation.<String>builder( )
                                    .operator( OperatorEnum.EQ )
                                    .values( List.of( entry.getValue() ) )
                                    .build( )
                    ) );
                    break;

                case "NombreTrabajador":
                    filter.setTrabajadorNombre( List.of( entry.getValue() ) );
                    filterTrabajador.nombre(List.of(
                            FilterOperation.<String>builder( )
                                    .operator( OperatorEnum.CONTAINS )
                                    .values( List.of( entry.getValue() ) )
                                    .build( )
                    ) );
                    break;

                case "EstadoTrabajadorSSO":
                    filter.setTrabajadorEstadoSSOCodeIds( List.of( entry.getValue() ) );
                    break;

            }
        }
        proveedores = iViewProveedorReportQueryService.search(PagedRequest.<ViewProveedorReportFilter>builder( )
                .filter( filter )
                .build( )).getResult();
        if( proveedores.isEmpty( ) ) {
            return OutputResponse.builder( )
                    .value( List.of( ) )
                    .build( );
        }
//        if( filtro.getRUC( ) != null ) {
//            proveedores = iViewProveedorReportQueryService.search( buildPagedRequestProveedorRUC( filtro.getRUC( ) ) ).getResult( );
//        }
//        if( filtro.getFechaCreacionInicio() != null && filtro.getFechaCreacionFin( ) != null) {
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//            if (LocalDate.parse(filtro.getFechaCreacionFin(), formatter).isBefore(LocalDate.parse(filtro.getFechaCreacionInicio(), formatter))) {
//                return OutputResponse.builder( )
//                        .value( List.of( ) )
//                        .build( );
//            }
//            proveedores = iViewProveedorReportQueryService.search( buildPagedRequestProveedorFechaCreacionInicioFin( filtro.getFechaCreacionInicio(), filtro.getFechaCreacionFin()) ).getResult( );
//        }
//        if( filtro.getFechaCreacionInicio() != null ) {
//            proveedores = iViewProveedorReportQueryService.search( buildPagedRequestProveedorFechaCreacionInicio( filtro.getFechaCreacionInicio() ) ).getResult( );
//        }
//        if( filtro.getFechaCreacionFin( ) != null ) {
//            proveedores = iViewProveedorReportQueryService.search( buildPagedRequestProveedorFechaCreacionFin( filtro.getFechaCreacionFin() ) ).getResult( );
//        }
//        if( filtro.getTipoDocumentoTrabajador( ) != null) {
//            proveedores = iViewProveedorReportQueryService.search( buildPagedRequestProveedorTrabajadorDocOnlyDocCode(
//                    filtro.getTipoDocumentoTrabajador( ), filtro.getFechaVigenciaInicio( ), filtro.getFechaVigenciaFin( )
//            ) ).getResult( );
//        }
//        if( filtro.getNumeroDocumentoTrabajador( ) != null ) {
//            proveedores = iViewProveedorReportQueryService.search( buildPagedRequestProveedorTrabajadorDocOnlyDocNum(
//                    filtro.getNumeroDocumentoTrabajador( ), filtro.getFechaVigenciaInicio( ), filtro.getFechaVigenciaFin( )
//            ) ).getResult( );
//        }
//        if( filtro.getEstadoProveedorSSO( ) != null ) {
//            proveedores = iViewProveedorReportQueryService.search( buildPagedRequestProveedorSSO(
//                    filtro.getEstadoProveedorSSO( ), filtro.getFechaVigenciaInicio( ), filtro.getFechaVigenciaFin( )
//            ) ).getResult( );
//        } else if( filtro.getEstadoProveedorCalidadAmbiental( ) != null ) {
//            proveedores = iViewProveedorReportQueryService.search( buildPagedRequestProveedorCalidadAmbiental(
//                    filtro.getEstadoProveedorCalidadAmbiental( ), filtro.getFechaVigenciaInicio( ), filtro.getFechaVigenciaFin( )
//            ) ).getResult( );
//        } else if( filtro.getEstadoTrabajadorSSO( ) != null ) {
//            proveedores = iViewProveedorReportQueryService.search( buildPagedRequestProveedorTrabajadorEstado(
//                    filtro.getEstadoTrabajadorSSO( ), filtro.getFechaVigenciaInicio( ), filtro.getFechaVigenciaFin( )
//            ) ).getResult( );
//        }
//        if( filtro.getRazonSocial( ) != null ) {
//            proveedores = iViewProveedorReportQueryService.search( buildPagedRequestProveedorNombre( filtro.getRazonSocial( ) ) ).getResult( );
//        }
//        if( filtro.getNombreTrabajador( ) != null ) {
//            proveedores = iViewProveedorReportQueryService.search( buildPagedRequestProveedorTrabajadorNombre( filtro.getNombreTrabajador( ) ) ).getResult( );
//        }


//        PagedRequest<ViewAdjuntoReportFilter> filter =
//
//        PagedRequest<ViewAdjuntoReportFilter> filter  = PagedRequest.<ViewAdjuntoReportFilter> builder()
//                .limit(10000)
//                .filter(ViewAdjuntoReportFilter.builder()
//
//                        .proveedorId( List.of( FilterOperation.<String>builder( )
//                                .operator( OperatorEnum.IN )
//                                .values(proveedores.stream().map(ViewProveedorReportDTO::getId).toList())
//                                .build( ) ) )
//                        .build())
//                .build();
       List<ViewAdjuntoReportDTO> aListAdjuntos = iViewAdjuntoReportQueryService.search(PagedRequest.<ViewAdjuntoReportFilter> builder()
               .limit(10000)
                       .filter(ViewAdjuntoReportFilter.builder()
                               .proveedorId( List.of( FilterOperation.<String>builder( )
                                       .operator( OperatorEnum.IN )
                                       .values(proveedores.stream().map(ViewProveedorReportDTO::getId).toList())
                                       .build( ) ) )
                               .build())
               .build()).getResult();
        System.out.println("Cantidad de archivos ---->"+aListAdjuntos.size());
        Map<String, Map<String, List<ViewAdjuntoReportDTO>>> adjuntosPorProveedorYClasif =
                aListAdjuntos.stream()
                        .filter(a -> a.getProveedorId() != null && a.getClasification() != null)
                        .collect(Collectors.groupingBy(
                                ViewAdjuntoReportDTO::getProveedorId,
                                Collectors.groupingBy(ViewAdjuntoReportDTO::getClasification)
                        ));
        List<ValueItem> outputValue = proveedores.stream( )
                .map( proveedor -> {
                    List<ViewTrabajadorReportDTO> trabajadores =
//                            iViewTrabajadorReportQueryService.search( buildPagedRequestTrabajador( proveedor.getId( ) ) ).getResult( );
                    iViewTrabajadorReportQueryService.search( PagedRequest.<ViewTrabajadorReportFilter>builder( )
                            .filter( filterTrabajador.build() )
                            .build( ) ).getResult( );

                    ;
                    List<Trabajador> trabajadorList = new ArrayList<>( );
                    if( !trabajadores.isEmpty( ) ) {
                        trabajadorList = trabajadores.stream( )
                                .map( t -> {
                                    Map<String, Map<String, List<ViewAdjuntoReportDTO>>> adjuntosPorTrabajadorYClasif =
                                            aListAdjuntos.stream()
                                                    .filter(a -> a.getTrabajadorId() != null && a.getClasification() != null)
                                                    .collect(Collectors.groupingBy(
                                                            ViewAdjuntoReportDTO::getTrabajadorId,
                                                            Collectors.groupingBy(ViewAdjuntoReportDTO::getClasification)
                                                    ));

                                    TipoAltoRiesgoTrabajador tipoAltoRiesgoTrabajador = TipoAltoRiesgoTrabajador.builder( )
                                            .AltoRiesgoTrabajador( Arrays.stream( t.getTarDescripciones( ) != null ? t.getTarDescripciones( ).split( "," ) : new String[0] )
                                                    .map( String::trim )
                                                    .filter( s -> !s.isEmpty( ) )
                                                    .map( s -> ARTrabajadorItem.builder( )
                                                            .ARTrabajador( s )
                                                            .build( ) )
                                                    .toList( )
                                            )
                                            .docsSSOTrabajadorAltoRiesgo( adjuntosPorTrabajadorYClasif
                                                    .getOrDefault( t.getId( ), Map.of( ) )
                                                    .getOrDefault( "Trabajador AAR", List.of( ) )
                                                    .stream( )
                                                    .map( a -> Documento.builder( )
                                                            .ID( a.getIdRepositorio( ) )              // o a.getId() según lo que necesites
//                                                            .Ambito( a.getClasification( ) )
                                                            .Ambito( "SSO" )
                                                            .tipoDeDocumento( a.getTdpDescripcion( ) )
                                                            .Nombre( a.getNombreArchivo( ) )
                                                            .fechaDocumento( a.getFechaDocumento() == null ? "": DateTimeUtil.asString( a.getFechaDocumento( ) ) )
                                                            .fechaVencimiento( a.getFechaDocumento() == null ? "": DateTimeUtil.asString( a.getFechaVencimiento( ) ) )
                                                            .Revision( a.getSsoComentarioRevision( ) == null ? "": a.getSsoComentarioRevision( ) )
                                                            .build( )
                                                    )
                                                    .toList( )
                                            )
                                            .build( );

                                    List<SvPersonaRestringidaDTO> personasRestringidasFound = iPersonaRestringidaQueryService.search( buildPagedRequestPersonaRestringida( t.getNroDocumentoIdentidad( ), t.getTipoDocumentoIdentidadCode( ) ) ).getResult( );

                                    boolean isRestringido = estaRestringido( personasRestringidasFound );

                                    boolean estadoValido = "ST_SSO_0002".equals( t.getEstadoSsoCode( ) );
                                    boolean vigenciaValida = !t.getSoTieneVigencia( )
                                            || !LocalDate.now( ).isAfter( t.getSsoFechaFinVigencia( ) );

                                    return Trabajador.builder( )
                                            .TipoDocumento( t.getTdiNombre( ) )
                                            .NumeroDocumento( t.getNroDocumentoIdentidad( ) )
                                            .NombreTrabajador( t.getNombre( ) )
                                            .Restringido( isRestringido ? "1" : "0" )
                                            .UsuarioActivo( t.getIsActive() ? "1" : "0" )
                                            .EsApto( esApto( isRestringido, estadoValido, vigenciaValida ) ? "1" : "0" )

                                            .EstadoSSO( t.getSsoEstadoNombre( ) )
                                            .MotivoSSO( t.getSsoMotivo( ) )
                                            .FlagFechaVigenciaSSO( t.getSoTieneVigencia( ) ? "1" : "0" )
                                            .FechaVigenciaSSO( t.getSoTieneVigencia( ) ? String.valueOf( t.getSsoFechaFinVigencia( ) ) : "" )

                                            .DocumentosTrabajadorSSO( DocumentosTrabajadorSSO.builder( )
                                                    .docsTrabajadorSSO( adjuntosPorTrabajadorYClasif
                                                            .getOrDefault( t.getId( ), Map.of( ) )
                                                            .getOrDefault( "Trabajador SSO", List.of( ) )
                                                            .stream( )
                                                            .map( a -> Documento.builder( )
                                                                    .ID( a.getIdRepositorio( ) )              // o a.getId() según lo que necesites
//                                                                    .Ambito( a.getClasification( ) )
                                                                    .Ambito( "SSO" )
                                                                    .tipoDeDocumento( a.getTdpDescripcion( ) )
                                                                    .Nombre( a.getNombreArchivo( ) )
                                                                    .fechaDocumento( a.getFechaDocumento() == null ? "": DateTimeUtil.asString( a.getFechaDocumento( ) ) )
                                                                    .fechaVencimiento( a.getFechaDocumento() == null ? "": DateTimeUtil.asString( a.getFechaVencimiento( ) ) )
                                                                    .Revision( a.getSsoComentarioRevision( ) == null ? "": a.getSsoComentarioRevision( ) )
                                                                    .build( )
                                                            )
                                                            .toList( )
                                                    )
                                                    .build( ) )

                                            .TipoAltoRiesgoTrabajador( tipoAltoRiesgoTrabajador )

                                            .Prevencionista( t.getIndPrevencionista( ) ? "Si" : "No" )
                                            .DocsPrevencionistaTbjd( DocsPrevencionistaTbjd.builder( )
                                                    .DocsPrevenTbjd( adjuntosPorTrabajadorYClasif
                                                            .getOrDefault( t.getId( ), Map.of( ) )
                                                            .getOrDefault( "Trabajador P", List.of( ) )
                                                            .stream( )
                                                            .map( a -> Documento.builder( )
                                                                    .ID( a.getIdRepositorio( ) )              // o a.getId() según lo que necesites
//                                                                    .Ambito( a.getClasification( ) )
                                                                    .Ambito( "SSO" )
                                                                    .tipoDeDocumento( a.getTdpDescripcion( ) )
                                                                    .Nombre( a.getNombreArchivo( ) )
                                                                    .fechaDocumento( a.getFechaDocumento() == null ? "": DateTimeUtil.asString( a.getFechaDocumento( ) ) )
                                                                    .fechaVencimiento( a.getFechaDocumento() == null ? "": DateTimeUtil.asString( a.getFechaVencimiento( ) ) )
                                                                    .Revision( a.getSsoComentarioRevision( ) == null ? "": a.getSsoComentarioRevision( ) )
                                                                    .build( )
                                                            )
                                                            .toList( )
                                                    )
                                                    .build( ) )

                                            .build( );
                                } )
                                .toList( );
                    }

                    List<SedeItem> sedeItems = new ArrayList<>( );
                    String sedes = proveedor.getSedesNombres( );

                    if( sedes != null && !sedes.trim( ).isEmpty( ) ) {
                        sedeItems = Arrays.stream( sedes.split( "," ) )
                                .map( String::trim )
                                .filter( s -> !s.isEmpty( ) )
                                .map( s -> SedeItem.builder( )
                                        .Sede( s )
                                        .build( ) )
                                .toList( );
                    }

                    TipoAltoRiesgoProveedor tipoAltoRiesgoProveedor = TipoAltoRiesgoProveedor.builder( )
                            .AltoRiesgoProveedor( Arrays.stream( proveedor.getTarDescripciones( ) != null ? proveedor.getTarDescripciones( ).split( "," ) : new String[0] )
                                    .map( String::trim )
                                    .filter( s -> !s.isEmpty( ) )
                                    .map( s -> ARProveedorItem.builder( )
                                            .ARProveedor( s )
                                            .build( ) )
                                    .toList( )
                            )
                            .docsSSOProveedorAltoRiesgo(adjuntosPorProveedorYClasif
                                    .getOrDefault(proveedor.getId(), Map.of())
                                    .getOrDefault("AAR", List.of())
                                    .stream()
                                    .map(a -> Documento.builder()
                                            .ID(a.getIdRepositorio())              // o a.getId() según lo que necesites
//                                            .Ambito(a.getClasification())
                                            .Ambito( "SSO" )
                                            .tipoDeDocumento(a.getTdpDescripcion())
                                            .Nombre(a.getNombreArchivo())
                                            .fechaDocumento( a.getFechaDocumento() == null ? "": DateTimeUtil.asString( a.getFechaDocumento( ) ) )
                                            .fechaVencimiento( a.getFechaDocumento() == null ? "": DateTimeUtil.asString( a.getFechaVencimiento( ) ) )
                                            .Revision( a.getSsoComentarioRevision( ) == null ? "": a.getSsoComentarioRevision( ) )
                                            .build())
                                    .toList())
                            .build( );

                    return ValueItem.builder( )
                            .IDProveedor( proveedor.getId( ) )
                            .RUC( proveedor.getNroDocumentoIdentidad( ) )
                            .RazonSocial( proveedor.getNombreFiscal( ) )
                            .PersonaContacto( proveedor.getContactoPrincipal( ) )
                            .MailContacto( proveedor.getEmail( ) )
                            .TelefonoContacto( proveedor.getContactoPrincipalTelefono( ) )
                            .Email( proveedor.getEmail( ) )

                            .Sedes( Sedes.builder( )
                                    .Sede( sedeItems )
                                    .build( )
                            )

                            .EstadoSSOProveedor( proveedor.getSsoEstadoNombre( ) )
                            .MotivoSSOProveedor( proveedor.getSsoMotivo( ) )
                            .FlagFechaVigenciaSSOProveedor( proveedor.getSsoTieneVigencia( ) ? "1" : "0" )
                            .FechaVigenciaSSOProveedor( proveedor.getSsoTieneVigencia( ) ? String.valueOf( proveedor.getSsoFechaInicioVigencia( ) ) : "" )

                            .DocumentosProveedorSSO(DocumentosProveedorSSO.builder()
                                    .docsProveedorSSO(adjuntosPorProveedorYClasif
                                            .getOrDefault(proveedor.getId(), Map.of())
                                            .getOrDefault("SSO", List.of())
                                            .stream()
                                            .map(a -> Documento.builder()
                                                    .ID(a.getIdRepositorio())              // o a.getId() según lo que necesites
//                                                    .Ambito(a.getClasification())
                                                    .Ambito( "SSO" )
                                                    .tipoDeDocumento(a.getTdpDescripcion())
                                                    .Nombre(a.getNombreArchivo())
                                                    .fechaDocumento( a.getFechaDocumento() == null ? "": DateTimeUtil.asString( a.getFechaDocumento( ) ) )
                                                    .fechaVencimiento( a.getFechaDocumento() == null ? "": DateTimeUtil.asString( a.getFechaVencimiento( ) ) )
                                                    .Revision( a.getSsoComentarioRevision( ) == null ? "": a.getSsoComentarioRevision( ) )
                                                    .build())
                                            .toList())
                                    .build())

                            .ActividadAltoRiesgoProveedor( proveedor.getIndRealizaActividadAltoRiesgo( ) ? "Si" : "No" )
                            .TipoAltoRiesgoProveedor( tipoAltoRiesgoProveedor )

                            .ActividadABordoProveedor( proveedor.getIndRealizaActividadABordo( ) ? "Si" : "No" )
                            .DocumsABordoProveedor(DocumsABordoProveedor.builder()
                                    .DocsABordoProveedor(adjuntosPorProveedorYClasif
                                            .getOrDefault(proveedor.getId(), Map.of())
                                            .getOrDefault("AB", List.of())
                                            .stream()
                                            .map(a -> Documento.builder()
                                                    .ID(a.getIdRepositorio())              // o a.getId() según lo que necesites
//                                                    .Ambito(a.getClasification())
                                                    .Ambito( "SSO" )
                                                    .tipoDeDocumento(a.getTdpDescripcion())
                                                    .Nombre(a.getNombreArchivo())
                                                    .fechaDocumento( a.getFechaDocumento() == null ? "": DateTimeUtil.asString( a.getFechaDocumento( ) ) )
                                                    .fechaVencimiento( a.getFechaDocumento() == null ? "": DateTimeUtil.asString( a.getFechaVencimiento( ) ) )
                                                    .Revision( a.getSsoComentarioRevision( ) == null ? "": a.getSsoComentarioRevision( ) )
                                                    .build())
                                            .toList())
                                    .build())

                            .EstadoCalidadAmbientalProveedor( proveedor.getCaEstadoNombre( ) )
                            .MotivoCalidadAmbientalProveedor( proveedor.getCaMotivo( ) )
                            .FlagFechaVigenciaCalidadAmbientalProveedor( proveedor.getCaTieneVigencia( ) ? "1" : "0" )
                            .FechaVigenciaCalidadAmbientalProveedor( proveedor.getCaTieneVigencia( ) ? String.valueOf( proveedor.getCaFechaInicioVigencia( ) ) : "" )

                            .DocumentosProveedorCalidadAmbiental( DocumentosProveedorCalidadAmbiental.builder( )
                                    .docsCalidadAmbientalProveedor( Stream.concat(
                                                    adjuntosPorProveedorYClasif
                                                            .getOrDefault( proveedor.getId( ), Map.of( ) )
                                                            .getOrDefault( "QA", List.of( ) )
                                                            .stream( ),
                                                    adjuntosPorProveedorYClasif
                                                            .getOrDefault( proveedor.getId( ), Map.of( ) )
                                                            .getOrDefault( "Calidad", List.of( ) )
                                                            .stream( )
                                            )
                                            .map( a -> Documento.builder( )
                                                    .ID( a.getIdRepositorio( ) )              // o a.getId() según lo que necesites
//                                                    .Ambito( a.getClasification( ) )
                                                    .Ambito( "Calidad Ambiental" )
                                                    .tipoDeDocumento( a.getTdpDescripcion( ) )
                                                    .Nombre( a.getNombreArchivo( ) )
                                                    .fechaDocumento( a.getFechaDocumento() == null ? "": DateTimeUtil.asString( a.getFechaDocumento( ) ) )
                                                    .fechaVencimiento( a.getFechaDocumento() == null ? "": DateTimeUtil.asString( a.getFechaVencimiento( ) ) )
                                                    .Revision( a.getCaComentarioRevision( ) == null ? "": a.getCaComentarioRevision( ) )
                                                    .build( ) )
                                            .toList( ) )
                                    .build( ) )

                            .NroTrabajadores( trabajadores.size( ) )

                            .Trabajadores( Trabajadores.builder( )
                                    .Trabajador( trabajadorList )
                                    .build( )
                            )

                            .build( );
                } )
                .toList( );

        return OutputResponse.builder( )
                .value( outputValue )
                .build( );
    }



    private PagedRequest<ViewProveedorReportFilter> buildPagedRequestProveedorFechaCreacionInicio(String fechaCreacionInicio) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        ViewProveedorReportFilter filter = ViewProveedorReportFilter.builder( )
                .createdDateStart(LocalDate.parse(fechaCreacionInicio, formatter))
                .build( );

        return PagedRequest.<ViewProveedorReportFilter>builder( )
                .filter( filter )
                .build( );
    }
    private PagedRequest<ViewProveedorReportFilter> buildPagedRequestProveedorFechaCreacionFin(String fechaCreacionFin) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        ViewProveedorReportFilter filter = ViewProveedorReportFilter.builder( )
                .createdDateStart(LocalDate.parse(fechaCreacionFin, formatter))
                .build( );

        return PagedRequest.<ViewProveedorReportFilter>builder( )
                .filter( filter )
                .build( );
    }
    private PagedRequest<ViewProveedorReportFilter> buildPagedRequestProveedorFechaCreacionInicioFin(String fechaCreacionInicio, String fechaCreacionFin) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        ViewProveedorReportFilter filter = ViewProveedorReportFilter.builder( )
                .createdDateStart(LocalDate.parse(fechaCreacionInicio, formatter))
                .createdDateEnd(LocalDate.parse(fechaCreacionFin, formatter))
                .build( );

        return PagedRequest.<ViewProveedorReportFilter>builder( )
                .filter( filter )
                .build( );
    }

    @Override
    public GrabaVisitaTerceraResponseDTO wsGrabaVisitaTercera(GrabaVisitaTerceraRequestDTO dto) throws IOException {
        HttpDestination destination = destinationMendixFactory.create();

        Map<String,Object> filtro = new HashMap<>();
        filtro.put("Codigo", "");

        Map<String,Object> body = new HashMap<>();
        body.put("FiltroConsulta", filtro);

        ObjectMapper mapper = new ObjectMapper();
          String jsonBody = mapper.writeValueAsString(dto);
          System.out.println( jsonBody );

        // 3. POST REQUEST
        HttpPost post =
                new HttpPost("/http/gme/qas/usuariosedes/grabaVisitaTercera");

        String jsonBodyTmp = "{\n" +
                "    \"VisitaTercera\": {\n" +
                "        \"RUC\": \"20601238374\",\n" +
                "        \"RazonSocial\": \"AETOS\",\n" +
                "        \"MailContacto\": \"aetos@aetosperu.com\",\n" +
                "        \"TelefonoContacto\": \"941713724\",\n" +
                "        \"Sede\": \"CHIMBOTE FLOTA\",\n" +
                "        \"FechaInicio\": \"22/12/2025\",\n" +
                "        \"HoraInicio\": \"09:00\",\n" +
                "        \"FechaFin\": \"22/12/2025\",\n" +
                "        \"HoraFin\": \"17:00\",\n" +
                "        \"DescripcionVisita\": \"Coser y reparar redes de pesca de embarcaciones a,b,c\",\n" +
                "        \"Visitado\": \"JCUZCO@COPEINCA.COM.PE\",\n" +
                "        \"AreasVisita\": [\n" +
                "            {\n" +
                "                \"Nombre\": \"FLOTA\"\n" +
                "            }\n" +
                "        ],\n" +
                "        \"ZonasVisita\": [\n" +
                "            {\n" +
                "                \"Nombre\": \"FLOTA\"\n" +
                "            }\n" +
                "        ],\n" +
                "        \"Materiales\": [\n" +
                "            {\n" +
                "                \"Material\": \"Sacos de harina\",\n" +
                "                \"Cantidad\": \"100\",\n" +
                "                \"Unidad\": \"UND\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"Material\": \"Antioxidante\",\n" +
                "                \"Cantidad\": \"50\",\n" +
                "                \"Unidad\": \"KG\"\n" +
                "            }\n" +
                "        ],\n" +
                "        \"Equipos\": [\n" +
                "            {\n" +
                "                \"CodigoTipoEquipo\": \"0001\",\n" +
                "                \"Equipo\": \"Laptop Dell\",\n" +
                "                \"Cantidad\": \"1\",\n" +
                "                \"Serie\": \"\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"CodigoTipoEquipo\": \"0001\",\n" +
                "                \"Equipo\": \"Laptop HP\",\n" +
                "                \"Cantidad\": \"1\",\n" +
                "                \"Serie\": \"\"\n" +
                "            }\n" +
                "        ],\n" +
                "        \"ObservacionesEspeciales\": \"Llevan silla de ruedas\",\n" +
                "        \"OrdenServicio\": \"51000252252\",\n" +
                "        \"DocumentosProveedorSSO\": [\n" +
                "            {\n" +
                "                \"ID\": \"B2531-31F53-2F98A-263D5-2571F-F35F6-E6000\",\n" +
                "                \"Ambito\": \"SSO\",\n" +
                "                \"TipoDocumento\": \"Matriz IPERC\",\n" +
                "                \"Nombre\": \"Matriz IPERC 20251.pdf\",\n" +
                "                \"FechaDocumento\": \"01/09/2025\",\n" +
                "                \"FechaVencimiento\": \"31/12/2025\",\n" +
                "                \"Revision\": \"\"\n" +
                "            }\n" +
                "        ],\n" +
                "        \"DocumentosProveedorCA\": [\n" +
                "            {\n" +
                "                \"ID\": \"037F4-4110E-B782B-1650A-F2494-B03C4-10000\",\n" +
                "                \"Ambito\": \"Calidad Ambiental\",\n" +
                "                \"TipoDocumento\": \"Matriz CA\",\n" +
                "                \"Nombre\": \"Matriz CA 20251.pdf\",\n" +
                "                \"FechaDocumento\": \"01/09/2025\",\n" +
                "                \"FechaVencimiento\": \"31/12/2025\",\n" +
                "                \"Revision\": \"\"\n" +
                "            }\n" +
                "        ],\n" +
                "        \"ActividadAltoRiesgoProveedor\": \"Si\",\n" +
                "        \"AltoRiesgoProveedor\": [\n" +
                "            {\n" +
                "                \"ARProveedor\": \"Trabajo en alturas\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"ARProveedor\": \"Trabajo de buceo\"\n" +
                "            }\n" +
                "        ],\n" +
                "        \"DocumentosSSOProveedorAltoRiesgo\": [\n" +
                "            {\n" +
                "                \"ID\": \"037F4-4110E-B782B-1650A-F2494-B03C4-10000\",\n" +
                "                \"Ambito\": \"SSO\",\n" +
                "                \"TipoDocumento\": \"Procedimiento AR altura\",\n" +
                "                \"Nombre\": \"Procedimiento altura 20251.pdf\",\n" +
                "                \"FechaDocumento\": \"01/09/2025\",\n" +
                "                \"FechaVencimiento\": \"31/12/2025\",\n" +
                "                \"Revision\": \"\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"ID\": \"037F4-4110E-B782B-1650A-F2494-B03C4-10000\",\n" +
                "                \"Ambito\": \"SSO\",\n" +
                "                \"TipoDocumento\": \"Procedimiento AR buceo\",\n" +
                "                \"Nombre\": \"Adaptabilidad en altura 20251.pdf\",\n" +
                "                \"FechaDocumento\": \"01/09/2025\",\n" +
                "                \"FechaVencimiento\": \"31/12/2025\",\n" +
                "                \"Revision\": \"\"\n" +
                "            }\n" +
                "        ],\n" +
                "        \"ActividadABordoProveedor\": \"Si\",\n" +
                "        \"DocumentosABordoProveedor\": [\n" +
                "            {\n" +
                "                \"ID\": \"037F4-4110E-B782B-1650A-F2494-B03C4-10000\",\n" +
                "                \"Ambito\": \"SSO\",\n" +
                "                \"TipoDocumento\": \"Permiso de DICAPI\",\n" +
                "                \"Nombre\": \"Permiso de DICAPI 20251.pdf\",\n" +
                "                \"FechaDocumento\": \"01/09/2025\",\n" +
                "                \"FechaVencimiento\": \"31/12/2025\",\n" +
                "                \"Revision\": \"\"\n" +
                "            }\n" +
                "        ],\n" +
                "        \"NecesitaAndamios\": \"Si\",\n" +
                "        \"DocumentosAndamiosProveedor\": [\n" +
                "            {\n" +
                "                \"ID\": \"037F4-4110E-B782B-1650A-F2494-B03C4-10000\",\n" +
                "                \"Ambito\": \"SSO\",\n" +
                "                \"TipoDocumento\": \"Certificado de andamios homologado\",\n" +
                "                \"Nombre\": \"Certificado de andamios homologado 20251.pdf\",\n" +
                "                \"FechaDocumento\": \"01/09/2025\",\n" +
                "                \"FechaVencimiento\": \"31/12/2025\",\n" +
                "                \"Revision\": \"\"\n" +
                "            }\n" +
                "        ],\n" +
                "        \"NecesitaGrua\": \"Si\",\n" +
                "        \"DocumentosGruaProveedor\": [\n" +
                "            {\n" +
                "                \"ID\": \"037F4-4110E-B782B-1650A-F2494-B03C4-10000\",\n" +
                "                \"Ambito\": \"SSO\",\n" +
                "                \"TipoDocumento\": \"Certificado de operatividad del vehiculo\",\n" +
                "                \"Nombre\": \"Certificado de operatividad del vehiculo 20251.pdf\",\n" +
                "                \"FechaDocumento\": \"01/09/2025\",\n" +
                "                \"FechaVencimiento\": \"31/12/2025\",\n" +
                "                \"Revision\": \"\"\n" +
                "            }\n" +
                "        ],\n" +
                "        \"NecesitaEquiposMoviles\": \"Si\",\n" +
                "        \"DocumentosEquiposMoviles\": [\n" +
                "            {\n" +
                "                \"ID\": \"037F4-4110E-B782B-1650A-F2494-B03C4-10000\",\n" +
                "                \"Ambito\": \"SSO\",\n" +
                "                \"TipoDocumento\": \"Certificado de operatividad del vehiculo\",\n" +
                "                \"Nombre\": \"Certificado de operatividad del vehiculo 20251.pdf\",\n" +
                "                \"FechaDocumento\": \"01/09/2025\",\n" +
                "                \"FechaVencimiento\": \"31/12/2025\",\n" +
                "                \"Revision\": \"\"\n" +
                "            }\n" +
                "        ],\n" +
                "        \"NecesitaTrabajoBuceo\": \"Si\",\n" +
                "        \"DocumentosTrabajoBuceo\": [\n" +
                "            {\n" +
                "                \"ID\": \"037F4-4110E-B782B-1650A-F2494-B03C4-10000\",\n" +
                "                \"Ambito\": \"SSO\",\n" +
                "                \"TipoDocumento\": \"Carne de personal acuatico\",\n" +
                "                \"Nombre\": \"Carne de personal acuatico 20251.pdf\",\n" +
                "                \"FechaDocumento\": \"01/09/2025\",\n" +
                "                \"FechaVencimiento\": \"31/12/2025\",\n" +
                "                \"Revision\": \"\"\n" +
                "            }\n" +
                "        ],\n" +
                "        \"IntegranteVisitaTercera\": [\n" +
                "            {\n" +
                "                \"TipoDocumento\": \"DNI\",\n" +
                "                \"NumeroDocumento\": \"09495877\",\n" +
                "                \"Nombres\": \"JOSE MASCARO JACOME\",\n" +
                "                \"Observaciones\": \"Persona a cargo del resto\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"TipoDocumento\": \"DNI\",\n" +
                "                \"NumeroDocumento\": \"10667378\",\n" +
                "                \"Nombres\": \"JANETT GAMBOA\",\n" +
                "                \"Observaciones\": \"Asistente\"\n" +
                "            }\n" +
                "        ]\n" +
                "    }\n" +
                "}";
        post.setHeader("Content-Type", "application/json");
        post.setEntity(
                new StringEntity(jsonBody, ContentType.APPLICATION_JSON)
        );

        HttpResponse response =
                HttpClientAccessor
                        .getHttpClient(destination)
                        .execute(post);

        // 5. LEER RESPUESTA
        String jsonResponse =
                EntityUtils.toString(response.getEntity());

        System.out.println(jsonResponse);
        GrabaVisitaTerceraResponseDTO oResponse = mapper.readValue(jsonResponse, GrabaVisitaTerceraResponseDTO.class);
        if (!"OK".equals(oResponse.getCodigo())) {
            throw new RuntimeException(oResponse.getMensaje()+" - Mendix Integration");
        }
        return oResponse;

    }

    @Override
    public ActualizarCasoODataResponse wsActualizaVisitaMendix(ActualizarCasoRequest dto) throws IOException {
        HttpDestination destination = destinationMendixFactory.create();

        Map<String,Object> filtro = new HashMap<>();
        filtro.put("Codigo", "");

        Map<String,Object> body = new HashMap<>();
        body.put("FiltroConsulta", filtro);

        ObjectMapper mapper = new ObjectMapper();
        String jsonBody = mapper.writeValueAsString(dto);
        System.out.println( jsonBody );


        // 3. POST REQUEST
        HttpPost post =
                new HttpPost("/http/gme/qas/usuariosedes/actualizaVisitaTerceraMx");
        post.setHeader("Content-Type", "application/json");
        post.setEntity(
                new StringEntity(jsonBody, ContentType.APPLICATION_JSON)
        );

        HttpResponse response =
                HttpClientAccessor
                        .getHttpClient(destination)
                        .execute(post);

        // 5. LEER RESPUESTA
        String jsonResponse =
                EntityUtils.toString(response.getEntity());

        System.out.println(jsonResponse);
        ActualizarCasoODataResponse oResponse = mapper.readValue(jsonResponse, ActualizarCasoODataResponse.class);
//        if (!"OK".equals(oResponse.getValue().get(0).getCodigo())) {
//            throw new RuntimeException(oResponse.getValue().get(0).getMensaje());
//        }
        return oResponse;
    }

    private PagedRequest<ViewProveedorReportFilter> buildPagedRequestProveedorAllFilters( FiltroConsulta filtro ) {

        ViewProveedorReportFilter.ViewProveedorReportFilterBuilder filterBuilder = ViewProveedorReportFilter.builder( );

        if( filtro.getRUC( ) != null ) {
            filterBuilder.nroDocumentoIdentidad(
                    List.of(
                            FilterOperation.<String>builder( )
                                    .operator( OperatorEnum.EQ )
                                    .values( List.of( filtro.getRUC( ) ) )
                                    .build( )
                    )
            );
        }

        return PagedRequest.<ViewProveedorReportFilter>builder( )
                .filter( filterBuilder.build( ) )
                .build( );
    }

    private PagedRequest<ViewProveedorReportFilter> buildPagedRequestProveedorRUC( String ruc ) {

        ViewProveedorReportFilter filter = ViewProveedorReportFilter.builder( )
                .nroDocumentoIdentidad(
                        List.of(
                                FilterOperation.<String>builder( )
                                        .operator( OperatorEnum.EQ )
                                        .values( List.of( ruc ) )
                                        .build( )
                        )
                )
                .build( );

        return PagedRequest.<ViewProveedorReportFilter>builder( )
                .filter( filter )
                .build( );
    }

    private PagedRequest<ViewProveedorReportFilter> buildPagedRequestProveedorTrabajadorDoc( String tipoDocumentoTrabajador, String nroDocumentoTrabajador, String fechaVigenciaInicio, String fechaVigenciaFin ) {

        ViewProveedorReportFilter filter = ViewProveedorReportFilter.builder( )
                .trabajadorNroDocIdentidad(
                        List.of( nroDocumentoTrabajador )
                )
                .trabajadorTipoDocIdentidad(
                        List.of( tipoDocumentoTrabajador )
                )
                .trabajadorSSOFechaInicioVigencia( DateTimeUtil.asLocalDate( fechaVigenciaInicio, DateTimeUtil.DEFAULT_DATE_FORMAT_PROJECT ) )
                .trabajadorSSOFechaFinVigencia( DateTimeUtil.asLocalDate( fechaVigenciaFin, DateTimeUtil.DEFAULT_DATE_FORMAT_PROJECT ) )
                .build( );

        return PagedRequest.<ViewProveedorReportFilter>builder( )
                .filter( filter )
                .build( );
    }
    private PagedRequest<ViewProveedorReportFilter> buildPagedRequestProveedorTrabajadorDocOnlyDocCode( String tipoDocumentoTrabajador, String fechaVigenciaInicio, String fechaVigenciaFin ) {

        ViewProveedorReportFilter filter = ViewProveedorReportFilter.builder( )
                .trabajadorTipoDocIdentidad(
                        List.of( tipoDocumentoTrabajador )
                )
//                .trabajadorSSOFechaInicioVigencia( DateTimeUtil.asLocalDate( fechaVigenciaInicio, DateTimeUtil.DEFAULT_DATE_FORMAT_PROJECT ) )
//                .trabajadorSSOFechaFinVigencia( DateTimeUtil.asLocalDate( fechaVigenciaFin, DateTimeUtil.DEFAULT_DATE_FORMAT_PROJECT ) )
                .build( );

        return PagedRequest.<ViewProveedorReportFilter>builder( )
                .filter( filter )
                .build( );
    }
    private PagedRequest<ViewProveedorReportFilter> buildPagedRequestProveedorTrabajadorDocOnlyDocNum(String nroDocumentoTrabajador, String fechaVigenciaInicio, String fechaVigenciaFin ) {

        ViewProveedorReportFilter filter = ViewProveedorReportFilter.builder( )
                .trabajadorNroDocIdentidad(
                        List.of( nroDocumentoTrabajador )
                )
//                .trabajadorSSOFechaInicioVigencia( DateTimeUtil.asLocalDate( fechaVigenciaInicio, DateTimeUtil.DEFAULT_DATE_FORMAT_PROJECT ) )
//                .trabajadorSSOFechaFinVigencia( DateTimeUtil.asLocalDate( fechaVigenciaFin, DateTimeUtil.DEFAULT_DATE_FORMAT_PROJECT ) )
                .build( );

        return PagedRequest.<ViewProveedorReportFilter>builder( )
                .filter( filter )
                .build( );
    }

    private PagedRequest<ViewProveedorReportFilter> buildPagedRequestProveedorNombre( String nombre ) {

        ViewProveedorReportFilter filter = ViewProveedorReportFilter.builder( )
                .nombreFiscal(
                        List.of(
                                FilterOperation.<String>builder( )
                                        .operator( OperatorEnum.CONTAINS )
                                        .values( List.of( nombre ) )
                                        .build( )
                        )
                )
                .build( );

        return PagedRequest.<ViewProveedorReportFilter>builder( )
                .filter( filter )
                .build( );
    }

    private PagedRequest<ViewProveedorReportFilter> buildPagedRequestProveedorTrabajadorNombre( String nombre ) {

        ViewProveedorReportFilter filter = ViewProveedorReportFilter.builder( )
                .trabajadorNombre( List.of( nombre ) )
                .build( );

        return PagedRequest.<ViewProveedorReportFilter>builder( )
                .filter( filter )
                .build( );
    }

    private PagedRequest<ViewProveedorReportFilter> buildPagedRequestProveedorSSO( String estadoSSO, String fechaVigenciaInicio, String fechaVigenciaFin ) {

        ViewProveedorReportFilter.ViewProveedorReportFilterBuilder filterBuilder =
                ViewProveedorReportFilter.builder( );

        if( estadoSSO != null ) {
            filterBuilder.estadoSsoCode( List.of(
                    FilterOperation.<String>builder( )
                            .operator( OperatorEnum.EQ )
                            .values( List.of( estadoSSO ) )
                            .build( )
            ) );
        }

        // Fecha vigencia inicio (>=)
        if( fechaVigenciaInicio != null ) {
            filterBuilder.ssoFechaInicioVigencia(
                    List.of(
                            FilterOperation.<LocalDate>builder( )
                                    .operator( OperatorEnum.GE )
                                    .values( List.of(
                                            DateTimeUtil.asLocalDate(
                                                    fechaVigenciaInicio,
                                                    DateTimeUtil.DEFAULT_DATE_FORMAT_PROJECT
                                            )
                                    ) )
                                    .build( )
                    )
            );
        }

        // Fecha vigencia fin (<=) → expects String
        if( fechaVigenciaFin != null ) {
            filterBuilder.ssoFechaFinVigencia(
                    List.of(
                            FilterOperation.<String>builder( )
                                    .operator( OperatorEnum.LE )
                                    .values( List.of( fechaVigenciaFin ) )
                                    .build( )
                    )
            );
        }

        return PagedRequest.<ViewProveedorReportFilter>builder( )
                .filter( filterBuilder.build( ) )
                .build( );
    }

    private PagedRequest<ViewProveedorReportFilter> buildPagedRequestProveedorCalidadAmbiental( String estadoCalidadAmbiental, String fechaVigenciaInicio, String fechaVigenciaFin ) {

        ViewProveedorReportFilter.ViewProveedorReportFilterBuilder filterBuilder =
                ViewProveedorReportFilter.builder( );

        if( estadoCalidadAmbiental != null ) {
            filterBuilder.estadoCalidadAmbientalCode( List.of(
                    FilterOperation.<String>builder( )
                            .operator( OperatorEnum.EQ )
                            .values( List.of( estadoCalidadAmbiental ) )
                            .build( )
            ) );
        }

        // Fecha vigencia inicio (>=)
        if( fechaVigenciaInicio != null ) {
            filterBuilder.caFechaInicioVigencia(
                    List.of(
                            FilterOperation.<LocalDate>builder( )
                                    .operator( OperatorEnum.GE )
                                    .values( List.of(
                                            DateTimeUtil.asLocalDate(
                                                    fechaVigenciaInicio,
                                                    DateTimeUtil.DEFAULT_DATE_FORMAT_PROJECT
                                            )
                                    ) )
                                    .build( )
                    )
            );
        }

        // Fecha vigencia fin (<=) → expects String
        if( fechaVigenciaFin != null ) {
            filterBuilder.caFechaFinVigencia(
                    List.of(
                            FilterOperation.<LocalDate>builder( )
                                    .operator( OperatorEnum.LE )
                                    .values( List.of(
                                            DateTimeUtil.asLocalDate(
                                                    fechaVigenciaFin,
                                                    DateTimeUtil.DEFAULT_DATE_FORMAT_PROJECT
                                            )
                                    ) )
                                    .build( )
                    )
            );
        }

        return PagedRequest.<ViewProveedorReportFilter>builder( )
                .filter( filterBuilder.build( ) )
                .build( );
    }

    private PagedRequest<ViewProveedorReportFilter> buildPagedRequestProveedorTrabajadorEstado( String estadoSSO, String fechaVigenciaInicio, String fechaVigenciaFin ) {

        ViewProveedorReportFilter.ViewProveedorReportFilterBuilder filterBuilder =
                ViewProveedorReportFilter.builder( );

        if( estadoSSO != null ) {
            filterBuilder.trabajadorEstadoSSOCodeIds( List.of( estadoSSO ) );
        }

        // Fecha vigencia inicio (>=)
        if( fechaVigenciaInicio != null ) {
            filterBuilder.trabajadorSSOFechaInicioVigencia( DateTimeUtil.asLocalDate( fechaVigenciaInicio, DateTimeUtil.DEFAULT_DATE_FORMAT_PROJECT ) );
        }

        // Fecha vigencia fin (<=) → expects String
        if( fechaVigenciaFin != null ) {
            filterBuilder.trabajadorSSOFechaFinVigencia( DateTimeUtil.asLocalDate( fechaVigenciaFin, DateTimeUtil.DEFAULT_DATE_FORMAT_PROJECT ) );
        }

        return PagedRequest.<ViewProveedorReportFilter>builder( )
                .filter( filterBuilder.build( ) )
                .build( );
    }

    private PagedRequest<ViewTrabajadorReportFilter> buildPagedRequestTrabajador( String proveedorId ) {
        ViewTrabajadorReportFilter filter = ViewTrabajadorReportFilter.builder( )
                .proveedorId( List.of(
                        FilterOperation.<String>builder( )
                                .operator( OperatorEnum.EQ )
                                .values( List.of( proveedorId ) )
                                .build( )
                ) )
                .build( );

        return PagedRequest.<ViewTrabajadorReportFilter>builder( )
                .filter( filter )
                .build( );
    }

    private PagedRequest<SvPersonaRestringidaFilter> buildPagedRequestPersonaRestringida( String nroDocumento, String tipoDocCode ) {
        SvPersonaRestringidaFilter filter = SvPersonaRestringidaFilter.builder( )
                .numeroDocumento( List.of(
                        FilterOperation.<String>builder( )
                                .operator( OperatorEnum.EQ )
                                .values( List.of( nroDocumento ) )
                                .build( )
                ) )
                .tipoDocumentoIdentidadCodeIds( List.of( tipoDocCode ) )
                .build( );

        return PagedRequest.<SvPersonaRestringidaFilter>builder( )
                .filter( filter )
                .build( );
    }

    private boolean estaRestringido( List<SvPersonaRestringidaDTO> restricciones ) {
        if( restricciones == null || restricciones.isEmpty( ) ) {
            return false;
        }

        LocalDate hoy = LocalDate.now( );

        return restricciones.stream( )
                .anyMatch( r ->
                        r.getRestringidoHasta( ) == null
                                || !hoy.isAfter( r.getRestringidoHasta( ).toLocalDate( ) )
                );
    }

    private boolean esApto( boolean restringido, boolean estadoValido, boolean vigenciaValida ) {
        return !restringido && estadoValido && vigenciaValida;
    }

}
