/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/business/imp/EntityService.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.business.imp;

import com.copeinca.apicopeincaprov.integration.Mendix.pojos.PersonaVisitadaPojo;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvPersonalEntity;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvPersonalSedeEntity;
import com.copeinca.apicopeincaprov.modules.core.repositories.jpa.ISvPersonalRepository;
import com.copeinca.apicopeincaprov.modules.core.repositories.jpa.ISvPersonalSedeRepository;
import com.copeinca.apicopeincaprov.modules.core.repositories.jpa.ISvSedeRepository;
import com.copeinca.apicopeincaprov.modules.core.service.business.ISvPersonalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
* Implementación del service para SvPersonalEntity
* Extiende de las implementaciones especializadas para reutilizar funcionalidad
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class SvPersonalService implements ISvPersonalService {

    private final ISvSedeRepository svSedeRepository;
    private final ISvPersonalRepository svPersonalRepository;
    private final ISvPersonalSedeRepository svPersonalSedeRepository;

    @Override
    public void saveAll( List<PersonaVisitadaPojo> lista ) {
        for( PersonaVisitadaPojo pojo : lista ) {
            String sedeId = svSedeRepository
                    .findByName( pojo.getSede( ) )
                    .getFirst( )
                    .getId( );

            Optional<SvPersonalEntity> personalFound = svPersonalRepository
                    .findByUsuario( pojo.getUsuario( ) );

            String personalId = "";

            if( personalFound.isPresent( ) ) {
                SvPersonalEntity personalEntity = personalFound.get( );
                personalId = personalEntity.getId( );
                updatePersonal( personalEntity, pojo );
            } else {
                personalId = createPersonal( pojo ).getId( );
            }

            Optional<SvPersonalSedeEntity> personalSedeFound = svPersonalSedeRepository.findByPersonalIdAndSedeId( personalId, sedeId );

            if( personalSedeFound.isEmpty( ) ) {
                svPersonalSedeRepository.save( buildPersonalSede( personalId, sedeId ) );
            }

        }
    }

    private SvPersonalEntity createPersonal( PersonaVisitadaPojo pojo ) {
        return svPersonalRepository.save(
                SvPersonalEntity.builder( )
                        .usuario( pojo.getUsuario( ) )
                        .nombreUsuario( pojo.getNombreUsuario( ) )
                        .codigo( pojo.getCodigo( ) )
                        .descripcion( pojo.getDescripcion( ) )
                        .ambito( pojo.getAmbito( ) )
                        .influencia( pojo.getInfluencia( ) )
                        .dependencia( pojo.getDependencia( ) )
                        .campoPlantillaLf( pojo.getCampoPlantillaLF( ) )
                        .obligatorio( pojo.getObligatorio( ) )
                        .vencimiento( pojo.getVencimiento( ) )
                        .numeroCaso( pojo.getNumeroCaso( ) == null ? null : String.valueOf( pojo.getNumeroCaso( ) ) )
                        .mensaje( pojo.getMensaje( ) )
                        .isDeleted( false )
                        .build( )
        );
    }

    private SvPersonalEntity updatePersonal( SvPersonalEntity personalEntity, PersonaVisitadaPojo pojo ) {
        personalEntity.setNombreUsuario( pojo.getNombreUsuario( ) );
        personalEntity.setCodigo( pojo.getCodigo( ) );
        personalEntity.setDescripcion( pojo.getDescripcion( ) );
        personalEntity.setAmbito( pojo.getAmbito( ) );
        personalEntity.setInfluencia( pojo.getInfluencia( ) );
        personalEntity.setDependencia( pojo.getDependencia( ) );
        personalEntity.setCampoPlantillaLf( pojo.getCampoPlantillaLF( ) );
        personalEntity.setObligatorio( pojo.getObligatorio( ) );
        personalEntity.setVencimiento( pojo.getVencimiento( ) );
        personalEntity.setNumeroCaso( pojo.getNumeroCaso( ) == null ? null : String.valueOf( pojo.getNumeroCaso( ) ) );
        personalEntity.setMensaje( pojo.getMensaje( ) );
        return svPersonalRepository.save( personalEntity );
    }

    private SvPersonalSedeEntity buildPersonalSede( String personalId, String sedeId ) {
        return SvPersonalSedeEntity.builder( )
                .personalId( personalId )
                .sedeId( sedeId )
                .isActive( true )
                .isDeleted( false )
                .build( );
    }

}
