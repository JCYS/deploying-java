
/*
* Autor: Anthony Ramos
* Email: anthonyramosdev@gmail.com
* Template pack-angular:src/main/java/modules/core/specification/EntitySpecification.e.vm
*/
package com.copeinca.apicopeincaprov.modules.core.specification;

import com.copeinca.apicopeincaprov.commons.specification.AbstractSpecification;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.SvProveedorTipoActividadAltoRiesgoFilter;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvProveedorTipoActividadAltoRiesgoEntity;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvProveedorTipoActividadAltoRiesgoEntity_;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

/**
 * Specification para búsqueda dinámica de SvProveedorTipoActividadAltoRiesgoEntity.
 * Extiende de AbstractSpecification para reutilizar lógica común de filtros.
 */
public class SvProveedorTipoActividadAltoRiesgoSpecification extends AbstractSpecification {

    /**
     * Genera un {@link Specification} en base a los filtros dinámicos.
     *
     * @param filter Filtro con operaciones dinámicas
     * @return Specification para consultas JPA
     */
    public static Specification<SvProveedorTipoActividadAltoRiesgoEntity> byFilter(SvProveedorTipoActividadAltoRiesgoFilter filter) {

        return (Root<SvProveedorTipoActividadAltoRiesgoEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            //--- Filtro dinámico para id
            addPredicates(predicates, cb, root, SvProveedorTipoActividadAltoRiesgoEntity_.id, filter.getId());

            //--- Filtro dinámico para isActive
            addPredicates(predicates, cb, root, SvProveedorTipoActividadAltoRiesgoEntity_.isActive, filter.getIsActive());

            //--- Filtro FK por IDs: tipoActividadAltoRiesgoCode
            if (filter.getTipoActividadAltoRiesgoCodeIds() != null && !filter.getTipoActividadAltoRiesgoCodeIds().isEmpty())
                predicates.add(root.get(SvProveedorTipoActividadAltoRiesgoEntity_.tipoActividadAltoRiesgoCode).in(filter.getTipoActividadAltoRiesgoCodeIds()));

            //--- Filtro FK por IDs: proveedorId
            if (filter.getProveedorIdIds() != null && !filter.getProveedorIdIds().isEmpty())
                predicates.add(root.get(SvProveedorTipoActividadAltoRiesgoEntity_.proveedorId).in(filter.getProveedorIdIds()));

            return combineWithAnd(predicates, cb);

        };

    }

}
