
/*
* Autor: Anthony Ramos
* Email: anthonyramosdev@gmail.com
* Template pack-angular:src/main/java/modules/core/specification/EntitySpecification.e.vm
*/
package com.copeinca.apicopeincaprov.modules.core.specification;

import com.copeinca.apicopeincaprov.commons.specification.AbstractSpecification;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.SvSedeFilter;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvProveedorSedeEntity;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvSedeEntity;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvSedeEntity_;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

/**
 * Specification para búsqueda dinámica de SvSedeEntity.
 * Extiende de AbstractSpecification para reutilizar lógica común de filtros.
 */
public class SvSedeSpecification extends AbstractSpecification {

    /**
     * Genera un {@link Specification} en base a los filtros dinámicos.
     *
     * @param filter Filtro con operaciones dinámicas
     * @return Specification para consultas JPA
     */
    public static Specification<SvSedeEntity> byFilter(SvSedeFilter filter) {

        return (Root<SvSedeEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            //--- Filtro dinámico para id
            addPredicates(predicates, cb, root, SvSedeEntity_.id, filter.getId());

            //--- Filtro dinámico para name
            addPredicates(predicates, cb, root, SvSedeEntity_.name, filter.getName());

            //--- Filtro dinámico para ambito
            addPredicates(predicates, cb, root, SvSedeEntity_.ambito, filter.getAmbito());

            if (filter.getProveedorIds() != null && !filter.getProveedorIds().isEmpty()) {
                Subquery<Long> subquery = query.subquery(Long.class);
                Root<SvProveedorSedeEntity> subRoot = subquery.from(SvProveedorSedeEntity.class);

                subquery.select(cb.literal(1L))
                        .where(cb.and(
                                cb.equal(subRoot.get("sedeId"), root.get("id")),
                                subRoot.get("proveedorId").in(filter.getProveedorIds())
                        ));

                predicates.add(cb.exists(subquery));
            }

            return combineWithAnd(predicates, cb);

        };

    }

}
