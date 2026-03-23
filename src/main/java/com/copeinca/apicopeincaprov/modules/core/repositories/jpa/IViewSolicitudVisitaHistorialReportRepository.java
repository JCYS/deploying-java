/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/repository/jpa/EntityRepository.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.repositories.jpa;

import com.copeinca.apicopeincaprov.modules.core.models.views.ViewSolicitudVisitaHistorialReportView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
* Repository para ViewSolicitudVisitaHistorialReportEntity
*/
@Repository
public interface IViewSolicitudVisitaHistorialReportRepository
        extends JpaRepository<ViewSolicitudVisitaHistorialReportView, String>, JpaSpecificationExecutor<ViewSolicitudVisitaHistorialReportView> {

}
