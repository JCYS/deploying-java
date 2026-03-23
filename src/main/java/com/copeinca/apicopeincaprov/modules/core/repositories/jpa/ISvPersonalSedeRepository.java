/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/repository/jpa/EntityRepository.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.repositories.jpa;

import com.copeinca.apicopeincaprov.modules.core.models.entities.SvPersonalSedeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
* Repository para SvPersonalSedeEntity
*/
@Repository
public interface ISvPersonalSedeRepository extends JpaRepository<SvPersonalSedeEntity, String>, JpaSpecificationExecutor<SvPersonalSedeEntity> {

    @Modifying
    @Transactional
    @Query("UPDATE SvPersonalSedeEntity o SET o.isDeleted = :isDeleted WHERE o.id = :id")
    int updateIsDeleted(@Param("isDeleted") boolean isDeleted, @Param("id") String id);

    @Query("SELECT o FROM SvPersonalSedeEntity o WHERE o.personalId = :personalId AND o.sedeId = :sedeId")
    Optional<SvPersonalSedeEntity> findByPersonalIdAndSedeId(@Param("personalId") String personalId, @Param("sedeId") String sedeId);

}
