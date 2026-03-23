
/*
* Autor: Anthony Ramos
* Email: anthonyramosdev@gmail.com
* Template pack-angular:src/main/java/modules/core/specification/EntitySpecification.e.vm
*/
package com.copeinca.apicopeincaprov.modules.core.specification;

import com.copeinca.apicopeincaprov.commons.specification.AbstractSpecification;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.SvSolicitudVisitaDetalleFilter;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvSolicitudVisitaDetalleEntity;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvSolicitudVisitaDetalleEntity_;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

/**
 * Specification para búsqueda dinámica de SvSolicitudVisitaDetalleEntity.
 * Extiende de AbstractSpecification para reutilizar lógica común de filtros.
 */
public class SvSolicitudVisitaDetalleSpecification extends AbstractSpecification {

    /**
     * Genera un {@link Specification} en base a los filtros dinámicos.
     *
     * @param filter Filtro con operaciones dinámicas
     * @return Specification para consultas JPA
     */
    public static Specification<SvSolicitudVisitaDetalleEntity> byFilter(SvSolicitudVisitaDetalleFilter filter) {

        return (Root<SvSolicitudVisitaDetalleEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            //--- Filtro dinámico para id
            addPredicates(predicates, cb, root, SvSolicitudVisitaDetalleEntity_.id, filter.getId());

            //--- Filtro dinámico para proveedorComentario
            addPredicates(predicates, cb, root, SvSolicitudVisitaDetalleEntity_.proveedorComentario, filter.getProveedorComentario());

            //--- Filtro FK por IDs: solicitudVisitaId
            if (filter.getSolicitudVisitaIdIds() != null && !filter.getSolicitudVisitaIdIds().isEmpty())
                predicates.add(root.get(SvSolicitudVisitaDetalleEntity_.solicitudVisitaId).in(filter.getSolicitudVisitaIdIds()));

            //--- Filtro FK por IDs: trabajadorId
            if (filter.getTrabajadorIdIds() != null && !filter.getTrabajadorIdIds().isEmpty())
                predicates.add(root.get(SvSolicitudVisitaDetalleEntity_.trabajadorId).in(filter.getTrabajadorIdIds()));

            return combineWithAnd(predicates, cb);

        };

    }

}
