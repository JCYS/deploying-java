/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/repository/jpa/EntityRepository.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.repositories.jpa;

import com.copeinca.apicopeincaprov.modules.core.models.entities.SvProveedorEntity;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Repository para SvProveedorEntity
 */
@Repository
public interface ISvProveedorRepository extends JpaRepository<SvProveedorEntity, String>, JpaSpecificationExecutor<SvProveedorEntity> {

    @Modifying
    @Transactional
    @Query( "UPDATE SvProveedorEntity o SET o.isDeleted = :isDeleted WHERE o.id = :id" )
    int updateIsDeleted( @Param( "isDeleted" ) boolean isDeleted, @Param( "id" ) String id );


    List<SvProveedorEntity> findAllByEmail( String sEmail );

    @Modifying
    @Transactional
    @Query( "UPDATE SvProveedorEntity o SET o.estadoSsoCode = :estadoSsoCode WHERE o.id = :id" )
    int updateEstadoSsoCode( @Param( "estadoSsoCode" ) String estadoSsoCode, @Param( "id" ) String id );

    @Modifying
    @Transactional
    @Query( "UPDATE SvProveedorEntity o SET o.ssoTieneVigencia = :ssoTieneVigencia WHERE o.id = :id" )
    int updateSsoTieneVigencia( @Param( "ssoTieneVigencia" ) boolean ssoTieneVigencia, @Param( "id" ) String id );

    @Modifying
    @Transactional
    @Query( "UPDATE SvProveedorEntity o SET o.ssoFechaInicioVigencia = :ssoFechaInicioVigencia WHERE o.id = :id" )
    int updateSsoFechaInicioVigencia( @Param( "ssoFechaInicioVigencia" ) LocalDate ssoFechaInicioVigencia, @Param( "id" ) String id );

    @Modifying
    @Transactional
    @Query( "UPDATE SvProveedorEntity o SET o.ssoFechaFinVigencia = :ssoFechaFinVigencia WHERE o.id = :id" )
    int updateSsoFechaFinVigencia( @Param( "ssoFechaFinVigencia" ) String ssoFechaFinVigencia, @Param( "id" ) String id );

    @Modifying
    @Transactional
    @Query( "UPDATE SvProveedorEntity o SET o.ssoMotivo = :ssoMotivo WHERE o.id = :id" )
    int updateSsoMotivo( @Param( "ssoMotivo" ) String ssoMotivo, @Param( "id" ) String id );

    @Modifying
    @Transactional
    @Query( "UPDATE SvProveedorEntity o SET o.ssoUsuarioEvaluador = :ssoUsuarioEvaluador WHERE o.id = :id" )
    int updateSsoUsuarioEvaluador( @Param( "ssoUsuarioEvaluador" ) String ssoUsuarioEvaluador, @Param( "id" ) String id );

    @Modifying
    @Transactional
    @Query( "UPDATE SvProveedorEntity o SET o.ssoFechaEvaluacion = :ssoFechaEvaluacion WHERE o.id = :id" )
    int updateSsoFechaEvaluacion( @Param( "ssoFechaEvaluacion" ) LocalDateTime ssoFechaEvaluacion, @Param( "id" ) String id );

    @Modifying
    @Transactional
    @Query( "UPDATE SvProveedorEntity o SET o.estadoCalidadAmbientalCode = :estadoCalidadAmbientalCode WHERE o.id = :id" )
    int updateEstadoCalidadAmbientalCode( @Param( "estadoCalidadAmbientalCode" ) String estadoCalidadAmbientalCode, @Param( "id" ) String id );

    @Modifying
    @Transactional
    @Query( "UPDATE SvProveedorEntity o SET o.caTieneVigencia = :caTieneVigencia WHERE o.id = :id" )
    int updateCaTieneVigencia( @Param( "caTieneVigencia" ) boolean caTieneVigencia, @Param( "id" ) String id );

    @Modifying
    @Transactional
    @Query( "UPDATE SvProveedorEntity o SET o.caFechaInicioVigencia = :caFechaInicioVigencia WHERE o.id = :id" )
    int updateCaFechaInicioVigencia( @Param( "caFechaInicioVigencia" ) LocalDate caFechaInicioVigencia, @Param( "id" ) String id );

    @Modifying
    @Transactional
    @Query( "UPDATE SvProveedorEntity o SET o.caFechaFinVigencia = :caFechaFinVigencia WHERE o.id = :id" )
    int updateCaFechaFinVigencia( @Param( "caFechaFinVigencia" ) LocalDate caFechaFinVigencia, @Param( "id" ) String id );

    @Modifying
    @Transactional
    @Query( "UPDATE SvProveedorEntity o SET o.caMotivo = :caMotivo WHERE o.id = :id" )
    int updateCaMotivo( @Param( "caMotivo" ) String caMotivo, @Param( "id" ) String id );

    @Modifying
    @Transactional
    @Query( "UPDATE SvProveedorEntity o SET o.caUsuarioEvaluador = :caUsuarioEvaluador WHERE o.id = :id" )
    int updateCaUsuarioEvaluador( @Param( "caUsuarioEvaluador" ) String caUsuarioEvaluador, @Param( "id" ) String id );

    @Modifying
    @Transactional
    @Query( "UPDATE SvProveedorEntity o SET o.caFechaEvaluacion = :caFechaEvaluacion WHERE o.id = :id" )
    int updateCaFechaEvaluacion( @Param( "caFechaEvaluacion" ) LocalDateTime caFechaEvaluacion, @Param( "id" ) String id );

    @Modifying
    @Transactional
    @Query( "UPDATE SvProveedorEntity o SET o.indProveedorNotificadoCreacion = :indProveedorNotificadoCreacion WHERE o.id = :id" )
    int updateIndProveedorNotificadoCreacion( @Param( "indProveedorNotificadoCreacion" ) Boolean indProveedorNotificadoCreacion, @Param( "id" ) String id );

    Boolean existsByNroDocumentoIdentidad(@Size(max = 20) String nroDocumentoIdentidad);

    List<SvProveedorEntity> findAllByNroDocumentoIdentidad(String nroDocumentoIdentidad);
}
