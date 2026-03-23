/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/repository/jpa/EntityRepository.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.repositories.jpa;

import com.copeinca.apicopeincaprov.modules.core.models.entities.SvTrabajadorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
* Repository para SvTrabajadorEntity
*/
@Repository
public interface ISvTrabajadorRepository extends JpaRepository<SvTrabajadorEntity, String>, JpaSpecificationExecutor<SvTrabajadorEntity> {

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Transactional
    @Query("""
   UPDATE SvTrabajadorEntity o
       SET o.isDeleted = :isDeleted
       WHERE o.id = :id
    """)
    int updateIsDeleted(@Param("isDeleted") boolean isDeleted,
                        @Param("id") String id);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Transactional
    @Query("""
   UPDATE SvTrabajadorEntity o
       SET o.isDeleted = :isDeleted,
           o.estadoSsoCode = 'ST_SSO_0001'
       WHERE o.id = :id
    """)
    int restore(@Param("isDeleted") boolean isDeleted,
                        @Param("id") String id);


    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Transactional
    @Query("""
   UPDATE SvTrabajadorEntity o
       SET o.isActive = :isActive,
           o.estadoSsoCode = 'ST_SSO_0001'
       WHERE o.id = :id
    """)
    int isActive(@Param("isActive") boolean isDeleted,
                @Param("id") String id);

    Boolean existsByNroDocumentoIdentidadAndTipoDocumentoIdentidadCode(String nroDocumentoIdentidad, String tipoDocumentoIdentidadCode);
}
