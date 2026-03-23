
/*
* Autor: Anthony Ramos
* Email: anthonyramosdev@gmail.com
* Template pack-angular:src/main/java/modules/core/specification/EntitySpecification.e.vm
*/
package com.copeinca.apicopeincaprov.modules.core.specification;

import com.copeinca.apicopeincaprov.commons.specification.AbstractSpecification;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.SvPersonaRestringidaFilter;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvPersonaRestringidaEntity;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvPersonaRestringidaEntity_;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

/**
 * Specification para búsqueda dinámica de SvPersonaRestringidaEntity.
 * Extiende de AbstractSpecification para reutilizar lógica común de filtros.
 */
public class SvPersonaRestringidaSpecification extends AbstractSpecification {

    /**
     * Genera un {@link Specification} en base a los filtros dinámicos.
     *
     * @param filter Filtro con operaciones dinámicas
     * @return Specification para consultas JPA
     */
    public static Specification<SvPersonaRestringidaEntity> byFilter(SvPersonaRestringidaFilter filter) {

        return (Root<SvPersonaRestringidaEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            //--- Filtro dinámico para id
            addPredicates(predicates, cb, root, SvPersonaRestringidaEntity_.id, filter.getId());

            //--- Filtro dinámico para numeroDocumento
            addPredicates(predicates, cb, root, SvPersonaRestringidaEntity_.numeroDocumento, filter.getNumeroDocumento());

            //--- Filtro dinámico para licenciaConducir
            addPredicates(predicates, cb, root, SvPersonaRestringidaEntity_.licenciaConducir, filter.getLicenciaConducir());

            //--- Filtro dinámico para nombreCompleto
            addPredicates(predicates, cb, root, SvPersonaRestringidaEntity_.nombreCompleto, filter.getNombreCompleto());

            //--- Filtro dinámico para usuarioReporta
            addPredicates(predicates, cb, root, SvPersonaRestringidaEntity_.usuarioReporta, filter.getUsuarioReporta());

            //--- Filtro dinámico para restringidoEl
            addPredicates(predicates, cb, root, SvPersonaRestringidaEntity_.restringidoEl, filter.getRestringidoEl());

            //--- Filtro dinámico para restringidoHasta
            addPredicates(predicates, cb, root, SvPersonaRestringidaEntity_.restringidoHasta, filter.getRestringidoHasta());

            //--- Filtro dinámico para observaciones
            addPredicates(predicates, cb, root, SvPersonaRestringidaEntity_.observaciones, filter.getObservaciones());

            //--- Filtro FK por IDs: tipoDocumentoIdentidadCode
            if (filter.getTipoDocumentoIdentidadCodeIds() != null && !filter.getTipoDocumentoIdentidadCodeIds().isEmpty())
                predicates.add(root.get(SvPersonaRestringidaEntity_.tipoDocumentoIdentidadCode).in(filter.getTipoDocumentoIdentidadCodeIds()));

            //--- Filtro FK por IDs: sedeOriginadoraId
            if (filter.getSedeOriginadoraIdIds() != null && !filter.getSedeOriginadoraIdIds().isEmpty())
                predicates.add(root.get(SvPersonaRestringidaEntity_.sedeOriginadoraId).in(filter.getSedeOriginadoraIdIds()));

            return combineWithAnd(predicates, cb);

        };

    }

}
