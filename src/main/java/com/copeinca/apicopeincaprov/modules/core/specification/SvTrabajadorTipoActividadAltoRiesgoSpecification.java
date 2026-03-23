
/*
* Autor: Anthony Ramos
* Email: anthonyramosdev@gmail.com
* Template pack-angular:src/main/java/modules/core/specification/EntitySpecification.e.vm
*/
package com.copeinca.apicopeincaprov.modules.core.specification;

import com.copeinca.apicopeincaprov.commons.specification.AbstractSpecification;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.SvTrabajadorTipoActividadAltoRiesgoFilter;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvTrabajadorTipoActividadAltoRiesgoEntity;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvTrabajadorTipoActividadAltoRiesgoEntity_;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

/**
 * Specification para búsqueda dinámica de SvTrabajadorTipoActividadAltoRiesgoEntity.
 * Extiende de AbstractSpecification para reutilizar lógica común de filtros.
 */
public class SvTrabajadorTipoActividadAltoRiesgoSpecification extends AbstractSpecification {

    /**
     * Genera un {@link Specification} en base a los filtros dinámicos.
     *
     * @param filter Filtro con operaciones dinámicas
     * @return Specification para consultas JPA
     */
    public static Specification<SvTrabajadorTipoActividadAltoRiesgoEntity> byFilter(SvTrabajadorTipoActividadAltoRiesgoFilter filter) {

        return (Root<SvTrabajadorTipoActividadAltoRiesgoEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            //--- Filtro dinámico para id
            addPredicates(predicates, cb, root, SvTrabajadorTipoActividadAltoRiesgoEntity_.id, filter.getId());

            //--- Filtro dinámico para isActive
            addPredicates(predicates, cb, root, SvTrabajadorTipoActividadAltoRiesgoEntity_.isActive, filter.getIsActive());

            //--- Filtro FK por IDs: trabajadorId
            if (filter.getTrabajadorIdIds() != null && !filter.getTrabajadorIdIds().isEmpty())
                predicates.add(root.get(SvTrabajadorTipoActividadAltoRiesgoEntity_.trabajadorId).in(filter.getTrabajadorIdIds()));

            //--- Filtro FK por IDs: tipoActividadAltoRiesgoCode
            if (filter.getTipoActividadAltoRiesgoCodeIds() != null && !filter.getTipoActividadAltoRiesgoCodeIds().isEmpty())
                predicates.add(root.get(SvTrabajadorTipoActividadAltoRiesgoEntity_.tipoActividadAltoRiesgoCode).in(filter.getTipoActividadAltoRiesgoCodeIds()));

            return combineWithAnd(predicates, cb);

        };

    }

}
