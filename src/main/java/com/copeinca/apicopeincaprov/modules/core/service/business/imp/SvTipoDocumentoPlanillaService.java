/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/business/imp/EntityService.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.business.imp;

import com.copeinca.apicopeincaprov.integration.Mendix.pojos.DocPlantillaPojo;
import com.copeinca.apicopeincaprov.modules.core.mappers.SvTipoDocumentoPlanillaMapper;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvTipoDocumentoPlanillaEntity;
import com.copeinca.apicopeincaprov.modules.core.repositories.jpa.ISvTipoDocumentoPlanillaRepository;
import com.copeinca.apicopeincaprov.modules.core.service.business.ISvTipoDocumentoPlanillaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
* Implementación del service para SvTipoDocumentoPlanillaEntity
* Extiende de las implementaciones especializadas para reutilizar funcionalidad
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class SvTipoDocumentoPlanillaService implements ISvTipoDocumentoPlanillaService {


    private final ISvTipoDocumentoPlanillaRepository repository;
    private final SvTipoDocumentoPlanillaMapper mapper;

    @Override
    public void saveAll(List<DocPlantillaPojo> lista) {
//        for(DocPlantillaPojo item: lista){
//            System.out.println("Plantila -> "+item.toString());
////            Optional<SvTipoDocumentoPlanillaEntity> itemSearch = repository.findById(item.getCodigo());
////            if(itemSearch.isPresent()){
////                itemSearch.get().setObligatorio(true);
////                repository.save(itemSearch.get());
////            }
//
//        }
        for (DocPlantillaPojo item : lista) {
//            repository.findById(item.getCodigo())
//                    .ifPresent(entity -> {
//                        entity.setObligatorio(item.getObligatorio() == "1");
//                        entity.setVencimiento(item.getVencimiento() == "1");
//                        repository.save(entity);
//                    });
            repository.findById(item.getCodigo())
                    .ifPresentOrElse(
                            entity -> {
                                entity.setObligatorio("1".equals(item.getObligatorio()));
                                entity.setVencimiento("1".equals(item.getVencimiento()));
                                repository.save(entity);
                            },
                            () -> {
                                repository.save(SvTipoDocumentoPlanillaEntity.builder()
                                            .id(item.getCodigo())
                                            .ambito(item.getAmbito())
                                            .influencia(item.getInfluencia())
                                            .dependencia(item.getDependencia())
                                            .campoPlantillaLf(item.getCampoPlantillaLF())
                                            .obligatorio("1".equals(item.getObligatorio()))
                                            .vencimiento("1".equals(item.getVencimiento()))
                                            .isDeleted(false)
                                        .build());
                            }
                    );
        }

    }
}
