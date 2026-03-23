
/*
* Autor: Anthony Ramos
* Email: anthonyramosdev@gmail.com
* Template pack-angular:src/main/java/modules/core/specification/EntitySpecification.e.vm
*/
package com.copeinca.apicopeincaprov.modules.core.specification;

import com.copeinca.apicopeincaprov.commons.specification.AbstractSpecification;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.SvSolicitudVisitaEquipoFilter;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvSolicitudVisitaEquipoEntity;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvSolicitudVisitaEquipoEntity_;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

/**
 * Specification para búsqueda dinámica de SvSolicitudVisitaEquipoEntity.
 * Extiende de AbstractSpecification para reutilizar lógica común de filtros.
 */
public class SvSolicitudVisitaEquipoSpecification extends AbstractSpecification {

    /**
     * Genera un {@link Specification} en base a los filtros dinámicos.
     *
     * @param filter Filtro con operaciones dinámicas
     * @return Specification para consultas JPA
     */
    public static Specification<SvSolicitudVisitaEquipoEntity> byFilter(SvSolicitudVisitaEquipoFilter filter) {

        return (Root<SvSolicitudVisitaEquipoEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            //--- Filtro dinámico para id
            addPredicates(predicates, cb, root, SvSolicitudVisitaEquipoEntity_.id, filter.getId());

            //--- Filtro dinámico para descripcion
            addPredicates(predicates, cb, root, SvSolicitudVisitaEquipoEntity_.descripcion, filter.getDescripcion());

            //--- Filtro dinámico para marca
            addPredicates(predicates, cb, root, SvSolicitudVisitaEquipoEntity_.marca, filter.getMarca());

            //--- Filtro dinámico para modelo
            addPredicates(predicates, cb, root, SvSolicitudVisitaEquipoEntity_.modelo, filter.getModelo());

            //--- Filtro dinámico para numeroSerie
            addPredicates(predicates, cb, root, SvSolicitudVisitaEquipoEntity_.numeroSerie, filter.getNumeroSerie());

            //--- Filtro dinámico para tipoEquipo
            addPredicates(predicates, cb, root, SvSolicitudVisitaEquipoEntity_.tipoEquipo, filter.getTipoEquipo());

            //--- Filtro dinámico para codigoEquipo
            addPredicates(predicates, cb, root, SvSolicitudVisitaEquipoEntity_.codigoEquipo, filter.getCodigoEquipo());

            //--- Filtro dinámico para cantidad
            addPredicates(predicates, cb, root, SvSolicitudVisitaEquipoEntity_.cantidad, filter.getCantidad());

            //--- Filtro FK por IDs: solicitudVisitaId
            if (filter.getSolicitudVisitaIdIds() != null && !filter.getSolicitudVisitaIdIds().isEmpty())
                predicates.add(root.get(SvSolicitudVisitaEquipoEntity_.solicitudVisitaId).in(filter.getSolicitudVisitaIdIds()));

            return combineWithAnd(predicates, cb);

        };

    }

}
