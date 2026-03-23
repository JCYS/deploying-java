
/*
* Autor: Anthony Ramos
* Email: anthonyramosdev@gmail.com
* Template pack-angular:src/main/java/modules/core/specification/EntitySpecification.e.vm
*/
package com.copeinca.apicopeincaprov.modules.core.specification;

import com.copeinca.apicopeincaprov.commons.specification.AbstractSpecification;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.SvSolicitudVisitaEventoFilter;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvSolicitudVisitaEventoEntity;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvSolicitudVisitaEventoEntity_;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

/**
 * Specification para búsqueda dinámica de SvSolicitudVisitaEventoEntity.
 * Extiende de AbstractSpecification para reutilizar lógica común de filtros.
 */
public class SvSolicitudVisitaEventoSpecification extends AbstractSpecification {

    /**
     * Genera un {@link Specification} en base a los filtros dinámicos.
     *
     * @param filter Filtro con operaciones dinámicas
     * @return Specification para consultas JPA
     */
    public static Specification<SvSolicitudVisitaEventoEntity> byFilter(SvSolicitudVisitaEventoFilter filter) {

        return (Root<SvSolicitudVisitaEventoEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            //--- Filtro dinámico para id
            addPredicates(predicates, cb, root, SvSolicitudVisitaEventoEntity_.id, filter.getId());

            //--- Filtro dinámico para fechaEvento
            addPredicates(predicates, cb, root, SvSolicitudVisitaEventoEntity_.fechaEvento, filter.getFechaEvento());

            //--- Filtro dinámico para usuario
            addPredicates(predicates, cb, root, SvSolicitudVisitaEventoEntity_.usuario, filter.getUsuario());

            //--- Filtro dinámico para evento
            addPredicates(predicates, cb, root, SvSolicitudVisitaEventoEntity_.evento, filter.getEvento());

            //--- Filtro dinámico para ambito
            addPredicates(predicates, cb, root, SvSolicitudVisitaEventoEntity_.ambito, filter.getAmbito());

            //--- Filtro dinámico para revision
            addPredicates(predicates, cb, root, SvSolicitudVisitaEventoEntity_.revision, filter.getRevision());

            //--- Filtro dinámico para respuestaUsuario
            addPredicates(predicates, cb, root, SvSolicitudVisitaEventoEntity_.respuestaUsuario, filter.getRespuestaUsuario());

            //--- Filtro FK por IDs: solicitudVisitaId
            if (filter.getSolicitudVisitaIdIds() != null && !filter.getSolicitudVisitaIdIds().isEmpty())
                predicates.add(root.get(SvSolicitudVisitaEventoEntity_.solicitudVisitaId).in(filter.getSolicitudVisitaIdIds()));

            return combineWithAnd(predicates, cb);

        };

    }

}
