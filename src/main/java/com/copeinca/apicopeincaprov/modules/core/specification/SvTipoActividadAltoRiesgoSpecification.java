
/*
* Autor: Anthony Ramos
* Email: anthonyramosdev@gmail.com
* Template pack-angular:src/main/java/modules/core/specification/EntitySpecification.e.vm
*/
package com.copeinca.apicopeincaprov.modules.core.specification;

import com.copeinca.apicopeincaprov.commons.specification.AbstractSpecification;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.SvTipoActividadAltoRiesgoFilter;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvTipoActividadAltoRiesgoEntity;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvTipoActividadAltoRiesgoEntity_;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

/**
 * Specification para búsqueda dinámica de SvTipoActividadAltoRiesgoEntity.
 * Extiende de AbstractSpecification para reutilizar lógica común de filtros.
 */
public class SvTipoActividadAltoRiesgoSpecification extends AbstractSpecification {

    /**
     * Genera un {@link Specification} en base a los filtros dinámicos.
     *
     * @param filter Filtro con operaciones dinámicas
     * @return Specification para consultas JPA
     */
    public static Specification<SvTipoActividadAltoRiesgoEntity> byFilter(SvTipoActividadAltoRiesgoFilter filter) {

        return (Root<SvTipoActividadAltoRiesgoEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            //--- Filtro dinámico para id
            addPredicates(predicates, cb, root, SvTipoActividadAltoRiesgoEntity_.id, filter.getId());

            //--- Filtro dinámico para description
            addPredicates(predicates, cb, root, SvTipoActividadAltoRiesgoEntity_.description, filter.getDescription());

            //--- Filtro dinámico para isActive
            addPredicates(predicates, cb, root, SvTipoActividadAltoRiesgoEntity_.isActive, filter.getIsActive());

            return combineWithAnd(predicates, cb);

        };

    }

}
