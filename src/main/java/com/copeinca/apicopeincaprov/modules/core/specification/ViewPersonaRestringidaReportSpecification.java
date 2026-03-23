
/*
* Autor: Anthony Ramos
* Email: anthonyramosdev@gmail.com
* Template pack-angular:src/main/java/modules/core/specification/EntitySpecification.e.vm
*/
package com.copeinca.apicopeincaprov.modules.core.specification;

import com.copeinca.apicopeincaprov.commons.specification.AbstractSpecification;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.ViewPersonaRestringidaReportFilter;
import com.copeinca.apicopeincaprov.modules.core.models.views.ViewPersonaRestringidaReportView;
import com.copeinca.apicopeincaprov.modules.core.models.views.ViewPersonaRestringidaReportView_;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

/**
 * Specification para búsqueda dinámica de ViewPersonaRestringidaReportView.
 * Extiende de AbstractSpecification para reutilizar lógica común de filtros.
 */
public class ViewPersonaRestringidaReportSpecification extends AbstractSpecification {

    /**
     * Genera un {@link Specification} en base a los filtros dinámicos.
     *
     * @param filter Filtro con operaciones dinámicas
     * @return Specification para consultas JPA
     */
    public static Specification<ViewPersonaRestringidaReportView> byFilter(ViewPersonaRestringidaReportFilter filter) {

        return (Root<ViewPersonaRestringidaReportView> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            //--- Filtro dinámico para id
            addPredicates(predicates, cb, root, ViewPersonaRestringidaReportView_.id, filter.getId());

            //--- Filtro dinámico para numeroDocumento
            addPredicates(predicates, cb, root, ViewPersonaRestringidaReportView_.numeroDocumento, filter.getNumeroDocumento());

            //--- Filtro dinámico para licenciaConducir
            addPredicates(predicates, cb, root, ViewPersonaRestringidaReportView_.licenciaConducir, filter.getLicenciaConducir());

            //--- Filtro dinámico para nombreCompleto
            addPredicates(predicates, cb, root, ViewPersonaRestringidaReportView_.nombreCompleto, filter.getNombreCompleto());

            //--- Filtro dinámico para usuarioReporta
            addPredicates(predicates, cb, root, ViewPersonaRestringidaReportView_.usuarioReporta, filter.getUsuarioReporta());

            //--- Filtro dinámico para restringidoEl
            addPredicates(predicates, cb, root, ViewPersonaRestringidaReportView_.restringidoEl, filter.getRestringidoEl());

            //--- Filtro dinámico para restringidoHasta
            addPredicates(predicates, cb, root, ViewPersonaRestringidaReportView_.restringidoHasta, filter.getRestringidoHasta());

            //--- Filtro dinámico para observaciones
            addPredicates(predicates, cb, root, ViewPersonaRestringidaReportView_.observaciones, filter.getObservaciones());

            //--- Filtro dinámico para tipoDocumentoIdentidadCode
            addPredicates(predicates, cb, root, ViewPersonaRestringidaReportView_.tipoDocumentoIdentidadCode, filter.getTipoDocumentoIdentidadCode());

            //--- Filtro dinámico para sedeOriginadoraId
            addPredicates(predicates, cb, root, ViewPersonaRestringidaReportView_.sedeOriginadoraId, filter.getSedeOriginadoraId());

            //--- Filtro dinámico para tdiNombre
            addPredicates(predicates, cb, root, ViewPersonaRestringidaReportView_.tdiNombre, filter.getTdiNombre());

            //--- Filtro dinámico para sedeNombre
            addPredicates(predicates, cb, root, ViewPersonaRestringidaReportView_.sedeNombre, filter.getSedeNombre());

            //--- Filtro dinámico para sedeAmbito
            addPredicates(predicates, cb, root, ViewPersonaRestringidaReportView_.sedeAmbito, filter.getSedeAmbito());

            //--- Filtro dinámico para restriccionActiva
            addPredicates(predicates, cb, root, ViewPersonaRestringidaReportView_.restriccionActiva, filter.getRestriccionActiva());

            //--- Filtro dinámico para diasRestriccion
            addPredicates(predicates, cb, root, ViewPersonaRestringidaReportView_.diasRestriccion, filter.getDiasRestriccion());

            //--- Filtro dinámico para tipoRestriccion
            addPredicates(predicates, cb, root, ViewPersonaRestringidaReportView_.tipoRestriccion, filter.getTipoRestriccion());

            return combineWithAnd(predicates, cb);

        };

    }

}
