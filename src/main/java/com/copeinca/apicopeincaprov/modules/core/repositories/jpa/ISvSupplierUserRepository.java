package com.copeinca.apicopeincaprov.modules.core.repositories.jpa;

import com.copeinca.apicopeincaprov.modules.core.models.entities.SvSupplierUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISvSupplierUserRepository extends JpaRepository<SvSupplierUserEntity, Integer> {
}
