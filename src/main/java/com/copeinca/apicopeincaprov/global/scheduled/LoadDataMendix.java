package com.copeinca.apicopeincaprov.global.scheduled;

import com.copeinca.apicopeincaprov.integration.Mendix.pojos.*;
import com.copeinca.apicopeincaprov.integration.Mendix.service.interfaces.ClientService;
import com.copeinca.apicopeincaprov.modules.core.models.entities.*;
import com.copeinca.apicopeincaprov.modules.core.repositories.jpa.*;
import com.copeinca.apicopeincaprov.modules.core.service.business.ISvPersonalService;
import com.copeinca.apicopeincaprov.modules.core.service.business.ISvTipoDocumentoPlanillaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class LoadDataMendix {

    @Autowired
    public ClientService clientService;

    @Autowired
    public ISvSedeRepository svSedeRepository;

    @Autowired
    public ISvAreaRepository svAreaRepository;

    @Autowired
    public ISvZonaRepository svZonaRepository;

    @Autowired
    public ISvTipoDocumentoIdentidadRepository  svTipoDocumentoIdentidadRepository;

    @Autowired
    public ISvTipoDocumentoPlanillaRepository  svTipoDocumentoPlanillaRepository;

    @Autowired
    private ISvTipoDocumentoPlanillaService iSvTipoDocumentoPlanillaService;

    @Autowired
    private ISvPersonalService iSvPersonalService;



    @Scheduled(cron = "0 0 */23 * * *")
    public void cargaSedesAreas(){
        try{
            System.out.println("Job Sedes y Areas");
            List<SedesPojo> lista = clientService.getSedes();
            for(SedesPojo item : lista){
                List<SvSedeEntity> list = svSedeRepository.findByName(item.getSede());
                if(list.isEmpty()) {
                    System.out.println("Guardando: "+item.toString());
                    SvSedeEntity oItemNew = svSedeRepository.save(SvSedeEntity.builder()
                            .name(item.getSede())
                            .isDeleted(false)
                            .ambito(item.getAmbito())
                            .build());
                    List<AreasPojo> aListA = clientService.getAreasBySede(oItemNew.getName());
                    for(AreasPojo oItemA : aListA){
                            System.out.println("Guardando Area: "+oItemA.toString());
                            svAreaRepository.save(SvAreaEntity.builder()
                                            .isDeleted(false)
                                            .ambito(oItemA.getAmbito())
                                            .operativa(oItemA.getOperativa())
                                            .name(oItemA.getArea())
                                            .sedeId(oItemNew.getId())
                                    .build());
                    }
                }else{
                    List<AreasPojo> aListA = clientService.getAreasBySede(item.getSede());
                    for(AreasPojo oItemA : aListA){
                        List<SvAreaEntity> listArea = svAreaRepository.findByNameAndSedeId(oItemA.getArea(),list.get(0).getId());
                        if(listArea.isEmpty()) {
                            System.out.println("Guardando Area: "+oItemA.toString());
                            svAreaRepository.save(SvAreaEntity.builder()
                                    .isDeleted(false)
                                    .ambito(oItemA.getAmbito())
                                    .operativa(oItemA.getOperativa())
                                    .name(oItemA.getArea())
                                    .sedeId(list.get(0).getId())
                                    .build());
                        }
                    }
                }

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Scheduled(cron = "0 0 */4 * * *")
    public void cargaZonas(){
        try{
            System.out.println("Job Zonas");
            List<SedesPojo> lista = clientService.getSedes();
            for(SedesPojo item : lista){
                List<SvSedeEntity> list = svSedeRepository.findByName(item.getSede());
                System.out.println("Sedes encontradas "+list.size());
                if(!list.isEmpty()) {
                    System.out.println("Buscando Zonas por Sede");
                    List<ZonasPojo> aZonas = clientService.getZonasBySede(list.get(0).getName());
                    for(ZonasPojo oItem : aZonas){
                        List<SvZonaEntity> aZonasEnt = svZonaRepository.findByNameAndSedeId(oItem.getZona(),list.get(0).getId());
                        if(aZonasEnt.isEmpty()) {
                            svZonaRepository.save(SvZonaEntity.builder()
                                            .name(oItem.getZona())
                                            .isDeleted(false)
                                            .operativa(oItem.getOperativa())
                                    .sedeId(list.get(0).getId())
                                    .build());
                        }
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Scheduled(cron = "0 0 */2 * * *")
    public void cargaUM(){
        try{
            System.out.println("Job UM");
            List<UMPojo> lista = clientService.getUM();
            for(UMPojo item : lista){

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Scheduled(cron = "0 0 */23 * * *")
    public void cargaDocTipo(){
        try{
            System.out.println("Job Tipo Doc");
            List<DocTipoPojo> lista = clientService.getDocTipo();
            for(DocTipoPojo item : lista){
                List<SvTipoDocumentoIdentidadEntity> listDoc = svTipoDocumentoIdentidadRepository.findByName(item.getDescripcion());
                if(listDoc.isEmpty()) {
                    svTipoDocumentoIdentidadRepository.save(SvTipoDocumentoIdentidadEntity.builder()
                                    .id(item.getTipoDocumento())
                                    .isDeleted(false)
                                    .isActive(true)
                                    .name(item.getDescripcion().toUpperCase())
                                    .expresionRegular(item.getTipoDocumento())
                            .build()
                    );
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Scheduled(cron = "0 */30 * * * *")
    public void cargaDocPlantilla(){
        try{
            System.out.println("Job Plantillas");
            List<DocPlantillaPojo> lista = clientService.getDocPlantilla();
            iSvTipoDocumentoPlanillaService.saveAll(lista);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Scheduled(cron = "0 0 */4 * * *")
    public void cargaPersonaVisitadas(){
        try{
            System.out.println("Job Personal");
            List<PersonaVisitadaPojo> lista = clientService.getPersonaVisitadas();
            iSvPersonalService.saveAll(lista);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
