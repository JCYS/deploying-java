
/*
* Autor: Anthony Ramos
* Email: anthonyramosdev@gmail.com
* Template pack-angular:src/main/java/modules/core/specification/EntitySpecification.e.vm
*/
package com.copeinca.apicopeincaprov.modules.core.specification;

import com.copeinca.apicopeincaprov.commons.specification.AbstractSpecification;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.ViewAdjuntoReportFilter;
import com.copeinca.apicopeincaprov.modules.core.models.views.ViewAdjuntoReportView;
import com.copeinca.apicopeincaprov.modules.core.models.views.ViewAdjuntoReportView_;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

/**
 * Specification para búsqueda dinámica de ViewAdjuntoReportView.
 * Extiende de AbstractSpecification para reutilizar lógica común de filtros.
 */
public class ViewAdjuntoReportSpecification extends AbstractSpecification {

    /**
     * Genera un {@link Specification} en base a los filtros dinámicos.
     *
     * @param filter Filtro con operaciones dinámicas
     * @return Specification para consultas JPA
     */
    public static Specification<ViewAdjuntoReportView> byFilter(ViewAdjuntoReportFilter filter) {

        return (Root<ViewAdjuntoReportView> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            //--- Filtro dinámico para id
            addPredicates(predicates, cb, root, ViewAdjuntoReportView_.id, filter.getId());

            //--- Filtro dinámico para nombreArchivo
            addPredicates(predicates, cb, root, ViewAdjuntoReportView_.nombreArchivo, filter.getNombreArchivo());

            //--- Filtro dinámico para idRepositorio
            addPredicates(predicates, cb, root, ViewAdjuntoReportView_.idRepositorio, filter.getIdRepositorio());

            //--- Filtro dinámico para clasification
            addPredicates(predicates, cb, root, ViewAdjuntoReportView_.clasification, filter.getClasification());

            //--- Filtro dinámico para fechaDocumento
            addPredicates(predicates, cb, root, ViewAdjuntoReportView_.fechaDocumento, filter.getFechaDocumento());

            //--- Filtro dinámico para fechaVencimiento
            addPredicates(predicates, cb, root, ViewAdjuntoReportView_.fechaVencimiento, filter.getFechaVencimiento());

            //--- Filtro dinámico para rutaRelativa
            addPredicates(predicates, cb, root, ViewAdjuntoReportView_.rutaRelativa, filter.getRutaRelativa());

            //--- Filtro dinámico para ssoComentarioRevision
            addPredicates(predicates, cb, root, ViewAdjuntoReportView_.ssoComentarioRevision, filter.getSsoComentarioRevision());

            //--- Filtro dinámico para caComentarioRevision
            addPredicates(predicates, cb, root, ViewAdjuntoReportView_.caComentarioRevision, filter.getCaComentarioRevision());

            //--- Filtro dinámico para proveedorId
            addPredicates(predicates, cb, root, ViewAdjuntoReportView_.proveedorId, filter.getProveedorId());

            //--- Filtro dinámico para trabajadorId
            addPredicates(predicates, cb, root, ViewAdjuntoReportView_.trabajadorId, filter.getTrabajadorId());

            //--- Filtro dinámico para tipoDocumentoPlanillaCode
            addPredicates(predicates, cb, root, ViewAdjuntoReportView_.tipoDocumentoPlanillaCode, filter.getTipoDocumentoPlanillaCode());

            //--- Filtro dinámico para solicitudVisitaId
            addPredicates(predicates, cb, root, ViewAdjuntoReportView_.solicitudVisitaId, filter.getSolicitudVisitaId());

            //--- Filtro dinámico para tipoActividadAltoRiesgoCode
            addPredicates(predicates, cb, root, ViewAdjuntoReportView_.tipoActividadAltoRiesgoCode, filter.getTipoActividadAltoRiesgoCode());

            //--- Filtro dinámico para tdpDescripcion
            addPredicates(predicates, cb, root, ViewAdjuntoReportView_.tdpDescripcion, filter.getTdpDescripcion());

            //--- Filtro dinámico para tdpAmbito
            addPredicates(predicates, cb, root, ViewAdjuntoReportView_.tdpAmbito, filter.getTdpAmbito());

            //--- Filtro dinámico para tdpInfluencia
            addPredicates(predicates, cb, root, ViewAdjuntoReportView_.tdpInfluencia, filter.getTdpInfluencia());

            //--- Filtro dinámico para tdpDependencia
            addPredicates(predicates, cb, root, ViewAdjuntoReportView_.tdpDependencia, filter.getTdpDependencia());

            //--- Filtro dinámico para tdpCampoPlantillaLf
            addPredicates(predicates, cb, root, ViewAdjuntoReportView_.tdpCampoPlantillaLf, filter.getTdpCampoPlantillaLf());

            //--- Filtro dinámico para tdpObligatorio
            addPredicates(predicates, cb, root, ViewAdjuntoReportView_.tdpObligatorio, filter.getTdpObligatorio());

            //--- Filtro dinámico para tdpVencimiento
            addPredicates(predicates, cb, root, ViewAdjuntoReportView_.tdpVencimiento, filter.getTdpVencimiento());

            //--- Filtro dinámico para tipoPropietario
            addPredicates(predicates, cb, root, ViewAdjuntoReportView_.tipoPropietario, filter.getTipoPropietario());

            //--- Filtro dinámico para documentoVencido
            addPredicates(predicates, cb, root, ViewAdjuntoReportView_.documentoVencido, filter.getDocumentoVencido());

            //--- Filtro dinámico para diasHastaVencimiento
            addPredicates(predicates, cb, root, ViewAdjuntoReportView_.diasHastaVencimiento, filter.getDiasHastaVencimiento());

            return combineWithAnd(predicates, cb);

        };

    }

}
