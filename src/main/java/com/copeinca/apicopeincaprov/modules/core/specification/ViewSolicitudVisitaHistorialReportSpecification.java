
/*
* Autor: Anthony Ramos
* Email: anthonyramosdev@gmail.com
* Template pack-angular:src/main/java/modules/core/specification/EntitySpecification.e.vm
*/
package com.copeinca.apicopeincaprov.modules.core.specification;

import com.copeinca.apicopeincaprov.commons.specification.AbstractSpecification;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.ViewSolicitudVisitaHistorialReportFilter;
import com.copeinca.apicopeincaprov.modules.core.models.views.ViewSolicitudVisitaHistorialReportView;
import com.copeinca.apicopeincaprov.modules.core.models.views.ViewSolicitudVisitaHistorialReportView_;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

/**
 * Specification para búsqueda dinámica de ViewSolicitudVisitaHistorialReportView.
 * Extiende de AbstractSpecification para reutilizar lógica común de filtros.
 */
public class ViewSolicitudVisitaHistorialReportSpecification extends AbstractSpecification {

    /**
     * Genera un {@link Specification} en base a los filtros dinámicos.
     *
     * @param filter Filtro con operaciones dinámicas
     * @return Specification para consultas JPA
     */
    public static Specification<ViewSolicitudVisitaHistorialReportView> byFilter(ViewSolicitudVisitaHistorialReportFilter filter) {

        return (Root<ViewSolicitudVisitaHistorialReportView> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            //--- Filtro dinámico para id
            addPredicates(predicates, cb, root, ViewSolicitudVisitaHistorialReportView_.id, filter.getId());

            //--- Filtro dinámico para fechaHora
            addPredicates(predicates, cb, root, ViewSolicitudVisitaHistorialReportView_.fechaHora, filter.getFechaHora());

            //--- Filtro dinámico para usuario
            addPredicates(predicates, cb, root, ViewSolicitudVisitaHistorialReportView_.usuario, filter.getUsuario());

            //--- Filtro dinámico para ambito
            addPredicates(predicates, cb, root, ViewSolicitudVisitaHistorialReportView_.ambito, filter.getAmbito());

            //--- Filtro dinámico para revision
            addPredicates(predicates, cb, root, ViewSolicitudVisitaHistorialReportView_.revision, filter.getRevision());

            //--- Filtro dinámico para rptaByUsuario
            addPredicates(predicates, cb, root, ViewSolicitudVisitaHistorialReportView_.rptaByUsuario, filter.getRptaByUsuario());

            //--- Filtro dinámico para solicitudVisitaId
            addPredicates(predicates, cb, root, ViewSolicitudVisitaHistorialReportView_.solicitudVisitaId, filter.getSolicitudVisitaId());

            //--- Filtro dinámico para estadoSolicitudVisitaCode
            addPredicates(predicates, cb, root, ViewSolicitudVisitaHistorialReportView_.estadoSolicitudVisitaCode, filter.getEstadoSolicitudVisitaCode());

            //--- Filtro dinámico para solvisNroOrdenServicio
            addPredicates(predicates, cb, root, ViewSolicitudVisitaHistorialReportView_.solvisNroOrdenServicio, filter.getSolvisNroOrdenServicio());

            //--- Filtro dinámico para solvisCodigoVisita
            addPredicates(predicates, cb, root, ViewSolicitudVisitaHistorialReportView_.solvisCodigoVisita, filter.getSolvisCodigoVisita());

            //--- Filtro dinámico para esvNombre
            addPredicates(predicates, cb, root, ViewSolicitudVisitaHistorialReportView_.esvNombre, filter.getEsvNombre());

            //--- Filtro dinámico para prevEstadoSolicitudVisitaCode
            addPredicates(predicates, cb, root, ViewSolicitudVisitaHistorialReportView_.prevEstadoSolicitudVisitaCode,
                    filter.getPrevEstadoSolicitudVisitaCode());

            //--- Filtro dinámico para prevEstadoNombre
            addPredicates(predicates, cb, root, ViewSolicitudVisitaHistorialReportView_.prevEstadoNombre, filter.getPrevEstadoNombre());

            //--- Filtro dinámico para prevFechaHora
            addPredicates(predicates, cb, root, ViewSolicitudVisitaHistorialReportView_.prevFechaHora, filter.getPrevFechaHora());

            return combineWithAnd(predicates, cb);

        };

    }

}
