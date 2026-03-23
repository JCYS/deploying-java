
/*
* Autor: Anthony Ramos
* Email: anthonyramosdev@gmail.com
* Template pack-angular:src/main/java/modules/core/specification/EntitySpecification.e.vm
*/
package com.copeinca.apicopeincaprov.modules.core.specification;

import com.copeinca.apicopeincaprov.commons.specification.AbstractSpecification;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.SvTipoDocumentoPlanillaFilter;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvTipoDocumentoPlanillaEntity;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvTipoDocumentoPlanillaEntity_;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

/**
 * Specification para búsqueda dinámica de SvTipoDocumentoPlanillaEntity.
 * Extiende de AbstractSpecification para reutilizar lógica común de filtros.
 */
public class SvTipoDocumentoPlanillaSpecification extends AbstractSpecification {

    /**
     * Genera un {@link Specification} en base a los filtros dinámicos.
     *
     * @param filter Filtro con operaciones dinámicas
     * @return Specification para consultas JPA
     */
    public static Specification<SvTipoDocumentoPlanillaEntity> byFilter(SvTipoDocumentoPlanillaFilter filter) {

        return (Root<SvTipoDocumentoPlanillaEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            //--- Filtro dinámico para id
            addPredicates(predicates, cb, root, SvTipoDocumentoPlanillaEntity_.id, filter.getId());

            //--- Filtro dinámico para description
            addPredicates(predicates, cb, root, SvTipoDocumentoPlanillaEntity_.description, filter.getDescription());

            //--- Filtro dinámico para ambito
            addPredicates(predicates, cb, root, SvTipoDocumentoPlanillaEntity_.ambito, filter.getAmbito());

            //--- Filtro dinámico para influencia
            addPredicates(predicates, cb, root, SvTipoDocumentoPlanillaEntity_.influencia, filter.getInfluencia());

            //--- Filtro dinámico para dependencia
            addPredicates(predicates, cb, root, SvTipoDocumentoPlanillaEntity_.dependencia, filter.getDependencia());

            //--- Filtro dinámico para campoPlantillaLf
            addPredicates(predicates, cb, root, SvTipoDocumentoPlanillaEntity_.campoPlantillaLf, filter.getCampoPlantillaLf());

            //--- Filtro dinámico para obligatorio
            addPredicates(predicates, cb, root, SvTipoDocumentoPlanillaEntity_.obligatorio, filter.getObligatorio());

            //--- Filtro dinámico para vencimiento
            addPredicates(predicates, cb, root, SvTipoDocumentoPlanillaEntity_.vencimiento, filter.getVencimiento());

            return combineWithAnd(predicates, cb);

        };

    }

}
