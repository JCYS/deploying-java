package com.copeinca.apicopeincaprov.modules.core.repositories.jpa;

import com.copeinca.apicopeincaprov.modules.core.models.entities.SvProveedorSedeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* Repository para SvProveedorSedeEntity
*/
@Repository
public interface ISvProveedorSedeRepository extends JpaRepository<SvProveedorSedeEntity, String>, JpaSpecificationExecutor<SvProveedorSedeEntity> {

    @Modifying
    @Transactional
    @Query("UPDATE SvProveedorSedeEntity o SET o.isDeleted = :isDeleted WHERE o.id = :id")
    int updateIsDeleted(@Param("isDeleted") boolean isDeleted, @Param("id") String id);

    List<SvProveedorSedeEntity> findAllByProveedorId(String proveedorId);
}
