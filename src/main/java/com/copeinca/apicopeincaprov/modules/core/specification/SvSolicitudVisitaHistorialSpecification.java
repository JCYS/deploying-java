
/*
* Autor: Anthony Ramos
* Email: anthonyramosdev@gmail.com
* Template pack-angular:src/main/java/modules/core/specification/EntitySpecification.e.vm
*/
package com.copeinca.apicopeincaprov.modules.core.specification;

import com.copeinca.apicopeincaprov.commons.specification.AbstractSpecification;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.SvSolicitudVisitaHistorialFilter;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvSolicitudVisitaHistorialEntity;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvSolicitudVisitaHistorialEntity_;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

/**
 * Specification para búsqueda dinámica de SvSolicitudVisitaHistorialEntity.
 * Extiende de AbstractSpecification para reutilizar lógica común de filtros.
 */
public class SvSolicitudVisitaHistorialSpecification extends AbstractSpecification {

    /**
     * Genera un {@link Specification} en base a los filtros dinámicos.
     *
     * @param filter Filtro con operaciones dinámicas
     * @return Specification para consultas JPA
     */
    public static Specification<SvSolicitudVisitaHistorialEntity> byFilter(SvSolicitudVisitaHistorialFilter filter) {

        return (Root<SvSolicitudVisitaHistorialEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            //--- Filtro dinámico para id
            addPredicates(predicates, cb, root, SvSolicitudVisitaHistorialEntity_.id, filter.getId());

            //--- Filtro dinámico para fechaHora
            addPredicates(predicates, cb, root, SvSolicitudVisitaHistorialEntity_.fechaHora, filter.getFechaHora());

            //--- Filtro dinámico para usuario
            addPredicates(predicates, cb, root, SvSolicitudVisitaHistorialEntity_.usuario, filter.getUsuario());

            //--- Filtro dinámico para ambito
            addPredicates(predicates, cb, root, SvSolicitudVisitaHistorialEntity_.ambito, filter.getAmbito());

            //--- Filtro dinámico para revision
            addPredicates(predicates, cb, root, SvSolicitudVisitaHistorialEntity_.revision, filter.getRevision());

            //--- Filtro dinámico para rptaByUsuario
            addPredicates(predicates, cb, root, SvSolicitudVisitaHistorialEntity_.rptaByUsuario, filter.getRptaByUsuario());

            //--- Filtro FK por IDs: solicitudVisitaId
            if (filter.getSolicitudVisitaIdIds() != null && !filter.getSolicitudVisitaIdIds().isEmpty())
                predicates.add(root.get(SvSolicitudVisitaHistorialEntity_.solicitudVisitaId).in(filter.getSolicitudVisitaIdIds()));

            //--- Filtro FK por IDs: estadoSolicitudVisitaCode
            if (filter.getEstadoSolicitudVisitaCodeIds() != null && !filter.getEstadoSolicitudVisitaCodeIds().isEmpty())
                predicates.add(root.get(SvSolicitudVisitaHistorialEntity_.estadoSolicitudVisitaCode).in(filter.getEstadoSolicitudVisitaCodeIds()));

            return combineWithAnd(predicates, cb);

        };

    }

}
