/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/repository/jpa/EntityRepository.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.repositories.jpa;

import com.copeinca.apicopeincaprov.modules.core.models.dto.SvAdjuntoDTO;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvAdjuntosIndexDto;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvAdjuntoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* Repository para SvAdjuntoEntity
*/
@Repository
public interface ISvAdjuntoRepository extends JpaRepository<SvAdjuntoEntity, String>, JpaSpecificationExecutor<SvAdjuntoEntity> {

    @Modifying
    @Transactional
    @Query("UPDATE SvAdjuntoEntity o SET o.isDeleted = :isDeleted WHERE o.id = :id")
    int updateIsDeleted(@Param("isDeleted") boolean isDeleted, @Param("id") String id);

    List<SvAdjuntoEntity> findByProveedorIdAndIsDeleted(String proveedorId, Boolean isDeleted);

    List<SvAdjuntoEntity> findByProveedorIdAndIsDeletedAndTrabajadorId(String proveedorId, Boolean isDeleted, String trabajadorId);

    List<SvAdjuntoEntity> findAllBySolicitudVisitaId(String solicitudVisitaId);


    List<SvAdjuntoEntity> findAllByTrabajadorId(String trabajadorId);

    List<SvAdjuntoEntity> findAllByTrabajadorIdAndIsDeleted(String trabajadorId, Boolean isDeleted);
}
