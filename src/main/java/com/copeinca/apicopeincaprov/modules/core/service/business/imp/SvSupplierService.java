package com.copeinca.apicopeincaprov.modules.core.service.business.imp;


import com.copeinca.apicopeincaprov.modules.core.models.entities.SvSupplierUserEntity;
import com.copeinca.apicopeincaprov.modules.core.repositories.jpa.ISvSupplierUserRepository;
import com.copeinca.apicopeincaprov.modules.core.service.business.ISvSupplierUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SvSupplierService implements ISvSupplierUserService {

    private final ISvSupplierUserRepository svSupplierUserRepository;

    @Override
    public List<SvSupplierUserEntity> findAll() {
        return svSupplierUserRepository.findAll();
    }
}
