
/*
* Autor: Anthony Ramos
* Email: anthonyramosdev@gmail.com
* Template pack-angular:src/main/java/modules/core/specification/EntitySpecification.e.vm
*/
package com.copeinca.apicopeincaprov.modules.core.specification;

import com.copeinca.apicopeincaprov.commons.specification.AbstractSpecification;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.SvZonaFilter;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvZonaEntity;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvZonaEntity_;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

/**
 * Specification para búsqueda dinámica de SvZonaEntity.
 * Extiende de AbstractSpecification para reutilizar lógica común de filtros.
 */
public class SvZonaSpecification extends AbstractSpecification {

    /**
     * Genera un {@link Specification} en base a los filtros dinámicos.
     *
     * @param filter Filtro con operaciones dinámicas
     * @return Specification para consultas JPA
     */
    public static Specification<SvZonaEntity> byFilter(SvZonaFilter filter) {

        return (Root<SvZonaEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            //--- Filtro dinámico para id
            addPredicates(predicates, cb, root, SvZonaEntity_.id, filter.getId());

            //--- Filtro dinámico para name
            addPredicates(predicates, cb, root, SvZonaEntity_.name, filter.getName());

            //--- Filtro dinámico para operativa
            addPredicates(predicates, cb, root, SvZonaEntity_.operativa, filter.getOperativa());

            //--- Filtro FK por IDs: sedeId
            if (filter.getSedeIdIds() != null && !filter.getSedeIdIds().isEmpty())
                predicates.add(root.get(SvZonaEntity_.sedeId).in(filter.getSedeIdIds()));

            return combineWithAnd(predicates, cb);

        };

    }

}
