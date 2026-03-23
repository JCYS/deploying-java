
/*
* Autor: Anthony Ramos
* Email: anthonyramosdev@gmail.com
* Template pack-angular:src/main/java/modules/core/specification/EntitySpecification.e.vm
*/
package com.copeinca.apicopeincaprov.modules.core.specification;

import com.copeinca.apicopeincaprov.commons.specification.AbstractSpecification;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.SvAreaFilter;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvAreaEntity;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvAreaEntity_;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

/**
 * Specification para búsqueda dinámica de SvAreaEntity.
 * Extiende de AbstractSpecification para reutilizar lógica común de filtros.
 */
public class SvAreaSpecification extends AbstractSpecification {

    /**
     * Genera un {@link Specification} en base a los filtros dinámicos.
     *
     * @param filter Filtro con operaciones dinámicas
     * @return Specification para consultas JPA
     */
    public static Specification<SvAreaEntity> byFilter(SvAreaFilter filter) {

        return (Root<SvAreaEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            //--- Filtro dinámico para id
            addPredicates(predicates, cb, root, SvAreaEntity_.id, filter.getId());

            //--- Filtro dinámico para name
            addPredicates(predicates, cb, root, SvAreaEntity_.name, filter.getName());

            //--- Filtro dinámico para ambito
            addPredicates(predicates, cb, root, SvAreaEntity_.ambito, filter.getAmbito());

            //--- Filtro dinámico para operativa
            addPredicates(predicates, cb, root, SvAreaEntity_.operativa, filter.getOperativa());

            //--- Filtro FK por IDs: sedeId
            if (filter.getSedeIdIds() != null && !filter.getSedeIdIds().isEmpty())
                predicates.add(root.get(SvAreaEntity_.sedeId).in(filter.getSedeIdIds()));

            return combineWithAnd(predicates, cb);

        };

    }

}
