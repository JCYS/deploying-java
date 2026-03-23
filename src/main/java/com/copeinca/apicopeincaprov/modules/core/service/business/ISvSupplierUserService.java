package com.copeinca.apicopeincaprov.modules.core.service.business;


import com.copeinca.apicopeincaprov.modules.core.models.entities.SvSupplierUserEntity;
import com.copeinca.apicopeincaprov.modules.core.repositories.jpa.ISvSupplierUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ISvSupplierUserService{

    List<SvSupplierUserEntity> findAll();
}
