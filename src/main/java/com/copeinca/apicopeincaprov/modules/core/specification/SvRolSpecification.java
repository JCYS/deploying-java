
/*
* Autor: Anthony Ramos
* Email: anthonyramosdev@gmail.com
* Template pack-angular:src/main/java/modules/core/specification/EntitySpecification.e.vm
*/
package com.copeinca.apicopeincaprov.modules.core.specification;

import com.copeinca.apicopeincaprov.commons.specification.AbstractSpecification;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.SvRolFilter;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvRolEntity;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvRolEntity_;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

/**
 * Specification para búsqueda dinámica de SvRolEntity.
 * Extiende de AbstractSpecification para reutilizar lógica común de filtros.
 */
public class SvRolSpecification extends AbstractSpecification {

    /**
     * Genera un {@link Specification} en base a los filtros dinámicos.
     *
     * @param filter Filtro con operaciones dinámicas
     * @return Specification para consultas JPA
     */
    public static Specification<SvRolEntity> byFilter(SvRolFilter filter) {

        return (Root<SvRolEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            //--- Filtro dinámico para id
            addPredicates(predicates, cb, root, SvRolEntity_.id, filter.getId());

            //--- Filtro dinámico para rolName
            addPredicates(predicates, cb, root, SvRolEntity_.rolName, filter.getRolName());

            //--- Filtro dinámico para rolDescription
            addPredicates(predicates, cb, root, SvRolEntity_.rolDescription, filter.getRolDescription());

            return combineWithAnd(predicates, cb);

        };

    }

}
