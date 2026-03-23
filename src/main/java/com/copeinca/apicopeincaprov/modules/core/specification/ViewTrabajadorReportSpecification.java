
/*
* Autor: Anthony Ramos
* Email: anthonyramosdev@gmail.com
* Template pack-angular:src/main/java/modules/core/specification/EntitySpecification.e.vm
*/
package com.copeinca.apicopeincaprov.modules.core.specification;

import com.copeinca.apicopeincaprov.commons.specification.AbstractSpecification;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.ViewTrabajadorReportFilter;
import com.copeinca.apicopeincaprov.modules.core.models.views.ViewTrabajadorReportView;
import com.copeinca.apicopeincaprov.modules.core.models.views.ViewTrabajadorReportView_;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

/**
 * Specification para búsqueda dinámica de ViewTrabajadorReportView.
 * Extiende de AbstractSpecification para reutilizar lógica común de filtros.
 */
public class ViewTrabajadorReportSpecification extends AbstractSpecification {

    /**
     * Genera un {@link Specification} en base a los filtros dinámicos.
     *
     * @param filter Filtro con operaciones dinámicas
     * @return Specification para consultas JPA
     */
    public static Specification<ViewTrabajadorReportView> byFilter(ViewTrabajadorReportFilter filter) {

        return (Root<ViewTrabajadorReportView> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            //--- Filtro dinámico para id
            addPredicates(predicates, cb, root, ViewTrabajadorReportView_.id, filter.getId());

            //--- Filtro dinámico para proveedorId
            addPredicates(predicates, cb, root, ViewTrabajadorReportView_.proveedorId, filter.getProveedorId());

            //--- Filtro dinámico para provNroDocumentoIdentidad
            addPredicates(predicates, cb, root, ViewTrabajadorReportView_.provNroDocumentoIdentidad, filter.getProvNroDocumentoIdentidad());

            //--- Filtro dinámico para provNombreFiscal
            addPredicates(predicates, cb, root, ViewTrabajadorReportView_.provNombreFiscal, filter.getProvNombreFiscal());

            //--- Filtro dinámico para tipoDocumentoIdentidadCode
            addPredicates(predicates, cb, root, ViewTrabajadorReportView_.tipoDocumentoIdentidadCode, filter.getTipoDocumentoIdentidadCode());

            //--- Filtro dinámico para nroDocumentoIdentidad
            addPredicates(predicates, cb, root, ViewTrabajadorReportView_.nroDocumentoIdentidad, filter.getNroDocumentoIdentidad());

            //--- Filtro dinámico para tdiNombre
            addPredicates(predicates, cb, root, ViewTrabajadorReportView_.tdiNombre, filter.getTdiNombre());

            //--- Filtro dinámico para nombre
            addPredicates(predicates, cb, root, ViewTrabajadorReportView_.nombre, filter.getNombre());

            //--- Filtro dinámico para telefono
            addPredicates(predicates, cb, root, ViewTrabajadorReportView_.telefono, filter.getTelefono());

            //--- Filtro dinámico para email
            addPredicates(predicates, cb, root, ViewTrabajadorReportView_.email, filter.getEmail());

            //--- Filtro dinámico para estadoSsoCode
            addPredicates(predicates, cb, root, ViewTrabajadorReportView_.estadoSsoCode, filter.getEstadoSsoCode());

            //--- Filtro dinámico para ssoMotivo
            addPredicates(predicates, cb, root, ViewTrabajadorReportView_.ssoMotivo, filter.getSsoMotivo());

            //--- Filtro dinámico para soTieneVigencia
            addPredicates(predicates, cb, root, ViewTrabajadorReportView_.soTieneVigencia, filter.getSoTieneVigencia());

            //--- Filtro dinámico para ssoFechaInicioVigencia
            addPredicates(predicates, cb, root, ViewTrabajadorReportView_.ssoFechaInicioVigencia, filter.getSsoFechaInicioVigencia());

            //--- Filtro dinámico para ssoFechaFinVigencia
            addPredicates(predicates, cb, root, ViewTrabajadorReportView_.ssoFechaFinVigencia, filter.getSsoFechaFinVigencia());

            //--- Filtro dinámico para isActive
            addPredicates(predicates, cb, root, ViewTrabajadorReportView_.isActive, filter.getIsActive());

            //--- Filtro dinámico para ssoEstadoNombre
            addPredicates(predicates, cb, root, ViewTrabajadorReportView_.ssoEstadoNombre, filter.getSsoEstadoNombre());

            //--- Filtro dinámico para ssoEstadoVigente
            addPredicates(predicates, cb, root, ViewTrabajadorReportView_.ssoEstadoVigente, filter.getSsoEstadoVigente());

            //--- Filtro dinámico para indTrabajoAltoRiesgo
            addPredicates(predicates, cb, root, ViewTrabajadorReportView_.indTrabajoAltoRiesgo, filter.getIndTrabajoAltoRiesgo());

            //--- Filtro dinámico para indPrevencionista
            addPredicates(predicates, cb, root, ViewTrabajadorReportView_.indPrevencionista, filter.getIndPrevencionista());

            //--- Filtro dinámico para tarCodigos
            addPredicates(predicates, cb, root, ViewTrabajadorReportView_.tarCodigos, filter.getTarCodigos());

            //--- Filtro dinámico para tarDescripciones
            addPredicates(predicates, cb, root, ViewTrabajadorReportView_.tarDescripciones, filter.getTarDescripciones());

            //--- Filtro dinámico para nDocsSsoAdjuntos
            addPredicates(predicates, cb, root, ViewTrabajadorReportView_.nDocsSsoAdjuntos, filter.getNDocsSsoAdjuntos());

            //--- Filtro dinámico para nDocsCalidadAdjuntos
            addPredicates(predicates, cb, root, ViewTrabajadorReportView_.nDocsCalidadAdjuntos, filter.getNDocsCalidadAdjuntos());

            //--- Filtro dinámico para nDocsAarAdjuntos
            addPredicates(predicates, cb, root, ViewTrabajadorReportView_.nDocsAarAdjuntos, filter.getNDocsAarAdjuntos());

            //--- Filtro dinámico para nDocsAbAdjuntos
            addPredicates(predicates, cb, root, ViewTrabajadorReportView_.nDocsAbAdjuntos, filter.getNDocsAbAdjuntos());

            //--- Filtro dinámico para nDocsPrevAdjuntos
            addPredicates(predicates, cb, root, ViewTrabajadorReportView_.nDocsPrevAdjuntos, filter.getNDocsPrevAdjuntos());
            //--- filtro para esta restringido
            addPredicates(predicates, cb, root, ViewTrabajadorReportView_.isValid, filter.getIsValid());

            return combineWithAnd(predicates, cb);

        };

    }

}
