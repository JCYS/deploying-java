
/*
* Autor: Anthony Ramos
* Email: anthonyramosdev@gmail.com
* Template pack-angular:src/main/java/modules/core/specification/EntitySpecification.e.vm
*/
package com.copeinca.apicopeincaprov.modules.core.specification;

import com.copeinca.apicopeincaprov.commons.specification.AbstractSpecification;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.SvSolicitudVisitaZonaFilter;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvSolicitudVisitaZonaEntity;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvSolicitudVisitaZonaEntity_;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

/**
 * Specification para búsqueda dinámica de SvSolicitudVisitaZonaEntity.
 * Extiende de AbstractSpecification para reutilizar lógica común de filtros.
 */
public class SvSolicitudVisitaZonaSpecification extends AbstractSpecification {

    /**
     * Genera un {@link Specification} en base a los filtros dinámicos.
     *
     * @param filter Filtro con operaciones dinámicas
     * @return Specification para consultas JPA
     */
    public static Specification<SvSolicitudVisitaZonaEntity> byFilter(SvSolicitudVisitaZonaFilter filter) {

        return (Root<SvSolicitudVisitaZonaEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            //--- Filtro dinámico para id
            addPredicates(predicates, cb, root, SvSolicitudVisitaZonaEntity_.id, filter.getId());

            //--- Filtro dinámico para isActive
            addPredicates(predicates, cb, root, SvSolicitudVisitaZonaEntity_.isActive, filter.getIsActive());

            //--- Filtro FK por IDs: zonaId
            if (filter.getZonaIdIds() != null && !filter.getZonaIdIds().isEmpty())
                predicates.add(root.get(SvSolicitudVisitaZonaEntity_.zonaId).in(filter.getZonaIdIds()));

            //--- Filtro FK por IDs: solicitudVisitaId
            if (filter.getSolicitudVisitaIdIds() != null && !filter.getSolicitudVisitaIdIds().isEmpty())
                predicates.add(root.get(SvSolicitudVisitaZonaEntity_.solicitudVisitaId).in(filter.getSolicitudVisitaIdIds()));

            return combineWithAnd(predicates, cb);

        };

    }

}
