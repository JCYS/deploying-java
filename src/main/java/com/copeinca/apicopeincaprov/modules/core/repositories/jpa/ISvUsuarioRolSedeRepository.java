/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/repository/jpa/EntityRepository.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.repositories.jpa;

import com.copeinca.apicopeincaprov.modules.core.models.entities.SvUsuarioEntity;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvUsuarioRolSedeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* Repository para SvUsuarioRolSedeEntity
*/
@Repository
public interface ISvUsuarioRolSedeRepository extends JpaRepository<SvUsuarioRolSedeEntity, String>, JpaSpecificationExecutor<SvUsuarioRolSedeEntity> {

    @Modifying
    @Transactional
    @Query("UPDATE SvUsuarioRolSedeEntity o SET o.isDeleted = :isDeleted WHERE o.id = :id")
    int updateIsDeleted(@Param("isDeleted") boolean isDeleted, @Param("id") String id);


    List<SvUsuarioRolSedeEntity> findAllByUsuarioId(String id);
}
