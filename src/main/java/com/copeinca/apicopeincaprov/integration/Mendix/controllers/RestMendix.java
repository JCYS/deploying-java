package com.copeinca.apicopeincaprov.integration.Mendix.controllers;

import com.copeinca.apicopeincaprov.global.dtos.response.CustomMessageView;
import com.copeinca.apicopeincaprov.global.dtos.response.MessageViewModel;
import com.copeinca.apicopeincaprov.integration.Mendix.dtos.imput.ActualizarCasoRequest;
import com.copeinca.apicopeincaprov.integration.Mendix.dtos.imput.GrabaVisitaTerceraRequestDTO;
import com.copeinca.apicopeincaprov.integration.Mendix.dtos.response.*;
import com.copeinca.apicopeincaprov.integration.Mendix.dtos.imput.FiltroConsultaRequest;
import com.copeinca.apicopeincaprov.integration.Mendix.pojos.DocPlantillaPojo;
import com.copeinca.apicopeincaprov.integration.Mendix.pojos.EquiposPojo;
import com.copeinca.apicopeincaprov.integration.Mendix.pojos.PersonaVisitadaPojo;
import com.copeinca.apicopeincaprov.integration.Mendix.pojos.UMPojo;
import com.copeinca.apicopeincaprov.integration.Mendix.service.interfaces.ClientService;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvProveedorCitaRegistroDTO;
import com.copeinca.apicopeincaprov.modules.core.service.business.ISvPersonalService;
import com.copeinca.apicopeincaprov.modules.core.service.business.ISvSolicitudVisitaService;
import com.copeinca.apicopeincaprov.modules.core.service.business.ISvTipoDocumentoPlanillaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("mendix/api")
public class RestMendix {

    @Autowired
    private  ISvSolicitudVisitaService iSvSolicitudVisitaService;

    @Autowired
    public ClientService clientService;

    @Autowired
    private ISvTipoDocumentoPlanillaService iSvTipoDocumentoPlanillaService;

    @Autowired
    private ISvPersonalService iSvPersonalService;

    @GetMapping("/getInfoProveedorReleased")
    public OutputResponse obtenerProveedores(@RequestBody FiltroConsultaRequest request) throws IOException {
        return clientService.OutputResponse();
    }

    @PostMapping("/wsRecuperaProveedoresTrabajadores")
    public OutputResponse wsObtenerProveedoresTrabajadores(@RequestBody FiltroConsultaRequest request) throws IOException {
        return clientService.wsRecuperaProveedoresTrabajadores( request );
    }

    @PostMapping(value = "/getTrabajadoresAptos")
    public OutputResponse getTrabajadoresAptos( @RequestBody FiltroConsultaRequest request ) {
        OutputResponse response = clientService.wsRecuperaProveedoresTrabajadores( request );

        if( response != null && response.getValue( ) != null ) {
            response.getValue( ).forEach( valueItem -> {
                if( valueItem.getTrabajadores( ) != null &&
                        valueItem.getTrabajadores( ).getTrabajador( ) != null ) {

                    List<Trabajador> aptos = valueItem.getTrabajadores( )
                            .getTrabajador( )
                            .stream( )
                            .filter( t -> !"0".equals( t.getEsApto( ) ) )
                            .toList( );

                    valueItem.getTrabajadores( ).setTrabajador( aptos );
                }
            } );
        }

        return response;

    }

    @GetMapping("/getRestringidos")
    public OutputResponse obtenerRestringidos(@RequestBody FiltroConsultaRequest request) throws IOException {
        return clientService.OutputResponse();
    }
    @GetMapping(path = "/readUM")
    public ResponseEntity<List<UMPojo>> readUM() throws IOException {
        List<UMPojo> lista = clientService.getUM();
        return ResponseEntity.ok(lista);
    }
    @GetMapping(path = "/readEquipos")
    public ResponseEntity<List<EquiposPojo>> readEquipos() throws IOException {
//        List<UMPojo> lista = clientService.getUM();

        return ResponseEntity.ok(clientService.getEquipos());
    }
    @GetMapping(path = "/readDocPlantillaSaveAndUpdate")
    public ResponseEntity<List<DocPlantillaPojo>> readDocPlantilla() throws IOException {
        List<DocPlantillaPojo> lista = clientService.getDocPlantilla();
        iSvTipoDocumentoPlanillaService.saveAll(lista);
        return ResponseEntity.ok(null);
    }

    @GetMapping(path = "/readPersonaVisitadaSaveAndUpdate")
    public ResponseEntity<List<PersonaVisitadaPojo>> readPersonaVisitada(@RequestParam String sede) throws IOException {
        List<PersonaVisitadaPojo> lista = clientService.getPersonaVisitadas(sede);

        iSvPersonalService.saveAll(lista);

        return ResponseEntity.ok(lista);
    }

    @PostMapping("/wsCancelarVisitaMendix/{id}")
    public ActualizarCasoODataResponse wsCancelarMendix(@PathVariable("id") String id, @RequestBody ActualizarCasoRequest request) throws IOException {
        //ResultadoWSActualiza resultado = iSvSolicitudVisitaService.wsActualizaVisitaTercera( request );
        ResultadoWSActualiza resultado = iSvSolicitudVisitaService.wsActualizaMendix( request, id );
        if("OK".equals(resultado.getCodigo()))
            return clientService.wsActualizaVisitaMendix(request);
        else
            return ActualizarCasoODataResponse.builder()
                    .value(List.of(ActualizarCasoODataItem.builder()
                                    .codigo("KO")
                                    .mensaje("No fue posible cancelar en mendix.")
                            .build()))
                    .build();
    }

    @PostMapping(value = "/grabarVisitaMendix/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CustomMessageView<?>> restore(@PathVariable("id") String id)  {


        try{
            GrabaVisitaTerceraRequestDTO result = iSvSolicitudVisitaService.getInfoVisitaDto(id);
            GrabaVisitaTerceraResponseDTO resultResponse = clientService.wsGrabaVisitaTercera(result);
            return ResponseEntity.ok(CustomMessageView.success(iSvSolicitudVisitaService.updateIdMendix(id,resultResponse.getNumeroCaso())));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(CustomMessageView.builder().successfully(false).finished(false).messages(List.of(
                    MessageViewModel.builder()
                            .type("ERROR")
                            .title("Notificacion Visita")
                            .description(e.getMessage())
                            .build()
            )).response(0).build());
            //throw new RuntimeException(e);
        }

        //iSvSolicitudVisitaService.updateIdMendix(id,resultResponse.getNumeroCaso());
//        iSvSolicitudVisitaService.getInfoVisita()









    }
}
