
/*
* Autor: Anthony Ramos
* Email: anthonyramosdev@gmail.com
* Template pack-angular:src/main/java/modules/core/specification/EntitySpecification.e.vm
*/
package com.copeinca.apicopeincaprov.modules.core.specification;

import com.copeinca.apicopeincaprov.commons.specification.AbstractSpecification;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.ViewPersonalReportFilter;
import com.copeinca.apicopeincaprov.modules.core.models.views.ViewPersonalReportView;
import com.copeinca.apicopeincaprov.modules.core.models.views.ViewPersonalReportView_;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

/**
 * Specification para búsqueda dinámica de ViewPersonalReportView.
 * Extiende de AbstractSpecification para reutilizar lógica común de filtros.
 */
public class ViewPersonalReportSpecification extends AbstractSpecification {

    /**
     * Genera un {@link Specification} en base a los filtros dinámicos.
     *
     * @param filter Filtro con operaciones dinámicas
     * @return Specification para consultas JPA
     */
    public static Specification<ViewPersonalReportView> byFilter(ViewPersonalReportFilter filter) {

        return (Root<ViewPersonalReportView> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            //--- Filtro dinámico para id
            addPredicates(predicates, cb, root, ViewPersonalReportView_.id, filter.getId());

            //--- Filtro dinámico para isActive
            addPredicates(predicates, cb, root, ViewPersonalReportView_.isActive, filter.getIsActive());

            //--- Filtro dinámico para personalId
            addPredicates(predicates, cb, root, ViewPersonalReportView_.personalId, filter.getPersonalId());

            //--- Filtro dinámico para sedeId
            addPredicates(predicates, cb, root, ViewPersonalReportView_.sedeId, filter.getSedeId());

            //--- Filtro dinámico para personalUsuario
            addPredicates(predicates, cb, root, ViewPersonalReportView_.personalUsuario, filter.getPersonalUsuario());

            //--- Filtro dinámico para personalNombreUsuario
            addPredicates(predicates, cb, root, ViewPersonalReportView_.personalNombreUsuario, filter.getPersonalNombreUsuario());

            //--- Filtro dinámico para personalCodigo
            addPredicates(predicates, cb, root, ViewPersonalReportView_.personalCodigo, filter.getPersonalCodigo());

            //--- Filtro dinámico para personalDescripcion
            addPredicates(predicates, cb, root, ViewPersonalReportView_.personalDescripcion, filter.getPersonalDescripcion());

            //--- Filtro dinámico para sedeName
            addPredicates(predicates, cb, root, ViewPersonalReportView_.sedeName, filter.getSedeName());

            //--- Filtro dinámico para sedeAmbito
            addPredicates(predicates, cb, root, ViewPersonalReportView_.sedeAmbito, filter.getSedeAmbito());

            return combineWithAnd(predicates, cb);

        };

    }

}
