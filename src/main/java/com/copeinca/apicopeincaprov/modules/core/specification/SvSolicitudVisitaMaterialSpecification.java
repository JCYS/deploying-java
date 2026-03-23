
/*
* Autor: Anthony Ramos
* Email: anthonyramosdev@gmail.com
* Template pack-angular:src/main/java/modules/core/specification/EntitySpecification.e.vm
*/
package com.copeinca.apicopeincaprov.modules.core.specification;

import com.copeinca.apicopeincaprov.commons.specification.AbstractSpecification;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.SvSolicitudVisitaMaterialFilter;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvSolicitudVisitaMaterialEntity;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvSolicitudVisitaMaterialEntity_;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

/**
 * Specification para búsqueda dinámica de SvSolicitudVisitaMaterialEntity.
 * Extiende de AbstractSpecification para reutilizar lógica común de filtros.
 */
public class SvSolicitudVisitaMaterialSpecification extends AbstractSpecification {

    /**
     * Genera un {@link Specification} en base a los filtros dinámicos.
     *
     * @param filter Filtro con operaciones dinámicas
     * @return Specification para consultas JPA
     */
    public static Specification<SvSolicitudVisitaMaterialEntity> byFilter(SvSolicitudVisitaMaterialFilter filter) {

        return (Root<SvSolicitudVisitaMaterialEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            //--- Filtro dinámico para id
            addPredicates(predicates, cb, root, SvSolicitudVisitaMaterialEntity_.id, filter.getId());

            //--- Filtro dinámico para descripcion
            addPredicates(predicates, cb, root, SvSolicitudVisitaMaterialEntity_.descripcion, filter.getDescripcion());

            //--- Filtro dinámico para cantidad
            addPredicates(predicates, cb, root, SvSolicitudVisitaMaterialEntity_.cantidad, filter.getCantidad());

            //--- Filtro dinámico para unidadMedida
            addPredicates(predicates, cb, root, SvSolicitudVisitaMaterialEntity_.unidadMedida, filter.getUnidadMedida());

            //--- Filtro FK por IDs: solicitudVisitaId
            if (filter.getSolicitudVisitaIdIds() != null && !filter.getSolicitudVisitaIdIds().isEmpty())
                predicates.add(root.get(SvSolicitudVisitaMaterialEntity_.solicitudVisitaId).in(filter.getSolicitudVisitaIdIds()));

            return combineWithAnd(predicates, cb);

        };

    }

}
