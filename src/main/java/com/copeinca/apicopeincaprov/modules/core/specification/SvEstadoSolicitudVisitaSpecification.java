
/*
* Autor: Anthony Ramos
* Email: anthonyramosdev@gmail.com
* Template pack-angular:src/main/java/modules/core/specification/EntitySpecification.e.vm
*/
package com.copeinca.apicopeincaprov.modules.core.specification;

import com.copeinca.apicopeincaprov.commons.specification.AbstractSpecification;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.SvEstadoSolicitudVisitaFilter;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvEstadoSolicitudVisitaEntity;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvEstadoSolicitudVisitaEntity_;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

/**
 * Specification para búsqueda dinámica de SvEstadoSolicitudVisitaEntity.
 * Extiende de AbstractSpecification para reutilizar lógica común de filtros.
 */
public class SvEstadoSolicitudVisitaSpecification extends AbstractSpecification {

    /**
     * Genera un {@link Specification} en base a los filtros dinámicos.
     *
     * @param filter Filtro con operaciones dinámicas
     * @return Specification para consultas JPA
     */
    public static Specification<SvEstadoSolicitudVisitaEntity> byFilter(SvEstadoSolicitudVisitaFilter filter) {

        return (Root<SvEstadoSolicitudVisitaEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            //--- Filtro dinámico para id
            addPredicates(predicates, cb, root, SvEstadoSolicitudVisitaEntity_.id, filter.getId());

            //--- Filtro dinámico para name
            addPredicates(predicates, cb, root, SvEstadoSolicitudVisitaEntity_.name, filter.getName());

            //--- Filtro dinámico para isActive
            addPredicates(predicates, cb, root, SvEstadoSolicitudVisitaEntity_.isActive, filter.getIsActive());

            return combineWithAnd(predicates, cb);

        };

    }

}
