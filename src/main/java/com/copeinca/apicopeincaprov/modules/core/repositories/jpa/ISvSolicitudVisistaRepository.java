/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/repository/jpa/EntityRepository.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.repositories.jpa;

import com.copeinca.apicopeincaprov.modules.core.models.entities.SvSolicitudVisistaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* Repository para SvSolicitudVisistaEntity
*/
@Repository
public interface ISvSolicitudVisistaRepository extends JpaRepository<SvSolicitudVisistaEntity, String>, JpaSpecificationExecutor<SvSolicitudVisistaEntity> {

    @Modifying
    @Transactional
    @Query("UPDATE SvSolicitudVisistaEntity o SET o.isDeleted = :isDeleted WHERE o.id = :id")
    int updateIsDeleted(@Param("isDeleted") boolean isDeleted, @Param("id") String id);

    @Modifying
    @Transactional
    @Query( "UPDATE SvSolicitudVisistaEntity o SET o.estadoSolicitudVisitaCode = :estadoSolicitudVisitaCode WHERE o.id = :id" )
    int updateEstadoSolicitudVisitaCode( @Param( "estadoSolicitudVisitaCode" ) String estadoSolicitudVisitaCode, @Param( "id" ) String id );

    @Modifying
    @Transactional
    @Query( "UPDATE SvSolicitudVisistaEntity o SET o.estadoSsoCode = :estadoSsoCode WHERE o.id = :id" )
    int updateEstadoSSOCode( @Param( "estadoSsoCode" ) String estadoSsoCode, @Param( "id" ) String id );

    @Modifying
    @Transactional
    @Query( "UPDATE SvSolicitudVisistaEntity o SET o.ssoMotivo = :ssoMotivo WHERE o.id = :id" )
    int updateSSOMotivo( @Param( "ssoMotivo" ) String ssoMotivo, @Param( "id" ) String id );

    @Modifying
    @Transactional
    @Query( "UPDATE SvSolicitudVisistaEntity o SET o.ssoDescargo = :ssoDescargo WHERE o.id = :id" )
    int updateSSODescargo( @Param( "ssoDescargo" ) String ssoDescargo, @Param( "id" ) String id );

    @Modifying
    @Transactional
    @Query( "UPDATE SvSolicitudVisistaEntity o SET o.estadoCalidadAmbientalCode = :estadoCalidadAmbientalCode WHERE o.id = :id" )
    int updateEstadoCalidadAmbientalCode( @Param( "estadoCalidadAmbientalCode" ) String estadoCalidadAmbientalCode, @Param( "id" ) String id );

    @Modifying
    @Transactional
    @Query( "UPDATE SvSolicitudVisistaEntity o SET o.caMotivo = :caMotivo WHERE o.id = :id" )
    int updateCAMotivo( @Param( "caMotivo" ) String caMotivo, @Param( "id" ) String id );

    @Modifying
    @Transactional
    @Query( "UPDATE SvSolicitudVisistaEntity o SET o.caDescargo = :caDescargo WHERE o.id = :id" )
    int updateCADescargo( @Param( "caDescargo" ) String caDescargo, @Param( "id" ) String id );

    @Modifying
    @Transactional
    @Query("UPDATE SvSolicitudVisistaEntity o SET o.codigoSistemaExterno = :mendix WHERE o.id = :id")
    int updateMendix(@Param("id") String id, @Param("mendix") String mendix);

    @Query(value = "SELECT VISITA_SEQUENCE.NEXTVAL AS new_id FROM DUMMY;", nativeQuery = true)
    Integer getNextCorrelativoSimple();

    List<SvSolicitudVisistaEntity> findAllByCodigoSistemaExterno(String solicitudVisitaId);
}
