
/*
* Autor: Anthony Ramos
* Email: anthonyramosdev@gmail.com
* Template pack-angular:src/main/java/modules/core/specification/EntitySpecification.e.vm
*/
package com.copeinca.apicopeincaprov.modules.core.specification;

import com.copeinca.apicopeincaprov.commons.specification.AbstractSpecification;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.SvSolicitudVisitaAreaAutorizadoraFilter;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvSolicitudVisitaAreaAutorizadoraEntity;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvSolicitudVisitaAreaAutorizadoraEntity_;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

/**
 * Specification para búsqueda dinámica de SvSolicitudVisitaAreaAutorizadoraEntity.
 * Extiende de AbstractSpecification para reutilizar lógica común de filtros.
 */
public class SvSolicitudVisitaAreaAutorizadoraSpecification extends AbstractSpecification {

    /**
     * Genera un {@link Specification} en base a los filtros dinámicos.
     *
     * @param filter Filtro con operaciones dinámicas
     * @return Specification para consultas JPA
     */
    public static Specification<SvSolicitudVisitaAreaAutorizadoraEntity> byFilter(SvSolicitudVisitaAreaAutorizadoraFilter filter) {

        return (Root<SvSolicitudVisitaAreaAutorizadoraEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            //--- Filtro dinámico para id
            addPredicates(predicates, cb, root, SvSolicitudVisitaAreaAutorizadoraEntity_.id, filter.getId());

            //--- Filtro dinámico para isActive
            addPredicates(predicates, cb, root, SvSolicitudVisitaAreaAutorizadoraEntity_.isActive, filter.getIsActive());

            //--- Filtro FK por IDs: solicitudVisitaId
            if (filter.getSolicitudVisitaIdIds() != null && !filter.getSolicitudVisitaIdIds().isEmpty())
                predicates.add(root.get(SvSolicitudVisitaAreaAutorizadoraEntity_.solicitudVisitaId).in(filter.getSolicitudVisitaIdIds()));

            //--- Filtro FK por IDs: areaAutorizadoraId
            if (filter.getAreaAutorizadoraIdIds() != null && !filter.getAreaAutorizadoraIdIds().isEmpty())
                predicates.add(root.get(SvSolicitudVisitaAreaAutorizadoraEntity_.areaAutorizadoraId).in(filter.getAreaAutorizadoraIdIds()));

            return combineWithAnd(predicates, cb);

        };

    }

}
