
/*
* Autor: Anthony Ramos
* Email: anthonyramosdev@gmail.com
* Template pack-angular:src/main/java/modules/core/specification/EntitySpecification.e.vm
*/
package com.copeinca.apicopeincaprov.modules.core.specification;

import com.copeinca.apicopeincaprov.commons.specification.AbstractSpecification;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.SvUsuarioFilter;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvUsuarioEntity;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvUsuarioEntity_;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

/**
 * Specification para búsqueda dinámica de SvUsuarioEntity.
 * Extiende de AbstractSpecification para reutilizar lógica común de filtros.
 */
public class SvUsuarioSpecification extends AbstractSpecification {

    /**
     * Genera un {@link Specification} en base a los filtros dinámicos.
     *
     * @param filter Filtro con operaciones dinámicas
     * @return Specification para consultas JPA
     */
    public static Specification<SvUsuarioEntity> byFilter(SvUsuarioFilter filter) {

        return (Root<SvUsuarioEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            //--- Filtro dinámico para id
            addPredicates(predicates, cb, root, SvUsuarioEntity_.id, filter.getId());

            //--- Filtro dinámico para nombre
            addPredicates(predicates, cb, root, SvUsuarioEntity_.nombre, filter.getNombre());

            //--- Filtro dinámico para apellidos
            addPredicates(predicates, cb, root, SvUsuarioEntity_.apellidos, filter.getApellidos());

            //--- Filtro dinámico para email
            addPredicates(predicates, cb, root, SvUsuarioEntity_.email, filter.getEmail());

            //--- Filtro dinámico para identificadorExterno
            addPredicates(predicates, cb, root, SvUsuarioEntity_.identificadorExterno, filter.getIdentificadorExterno());

            return combineWithAnd(predicates, cb);

        };

    }

}
