
/*
* Autor: Anthony Ramos
* Email: anthonyramosdev@gmail.com
* Template pack-angular:src/main/java/modules/core/specification/EntitySpecification.e.vm
*/
package com.copeinca.apicopeincaprov.modules.core.specification;

import com.copeinca.apicopeincaprov.commons.specification.AbstractSpecification;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.SvAdjuntoFilter;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvAdjuntoEntity;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvAdjuntoEntity_;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

/**
 * Specification para búsqueda dinámica de SvAdjuntoEntity.
 * Extiende de AbstractSpecification para reutilizar lógica común de filtros.
 */
public class SvAdjuntoSpecification extends AbstractSpecification {

    /**
     * Genera un {@link Specification} en base a los filtros dinámicos.
     *
     * @param filter Filtro con operaciones dinámicas
     * @return Specification para consultas JPA
     */
    public static Specification<SvAdjuntoEntity> byFilter(SvAdjuntoFilter filter) {

        return (Root<SvAdjuntoEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            //--- Filtro dinámico para id
            addPredicates(predicates, cb, root, SvAdjuntoEntity_.id, filter.getId());

            //--- Filtro dinámico para nombreArchivo
            addPredicates(predicates, cb, root, SvAdjuntoEntity_.nombreArchivo, filter.getNombreArchivo());

            //--- Filtro dinámico para idRepositorio
            addPredicates(predicates, cb, root, SvAdjuntoEntity_.idRepositorio, filter.getIdRepositorio());

            //--- Filtro dinámico para clasification
            addPredicates(predicates, cb, root, SvAdjuntoEntity_.clasification, filter.getClasification());

            //--- Filtro dinámico para fechaDocumento
            addPredicates(predicates, cb, root, SvAdjuntoEntity_.fechaDocumento, filter.getFechaDocumento());

            //--- Filtro dinámico para fechaVencimiento
            addPredicates(predicates, cb, root, SvAdjuntoEntity_.fechaVencimiento, filter.getFechaVencimiento());

            //--- Filtro dinámico para rutaRelativa
            addPredicates(predicates, cb, root, SvAdjuntoEntity_.rutaRelativa, filter.getRutaRelativa());

            //--- Filtro dinámico para ssoComentarioRevision
            addPredicates(predicates, cb, root, SvAdjuntoEntity_.ssoComentarioRevision, filter.getSsoComentarioRevision());

            //--- Filtro dinámico para caComentarioRevision
            addPredicates(predicates, cb, root, SvAdjuntoEntity_.caComentarioRevision, filter.getCaComentarioRevision());

            //--- Filtro dinámico para motivo
            addPredicates(predicates, cb, root, SvAdjuntoEntity_.motivo, filter.getMotivo());

            //--- Filtro FK por IDs: proveedorId
            if (filter.getProveedorIdIds() != null && !filter.getProveedorIdIds().isEmpty())
                predicates.add(root.get(SvAdjuntoEntity_.proveedorId).in(filter.getProveedorIdIds()));

            //--- Filtro FK por IDs: trabajadorId
            if (filter.getTrabajadorIdIds() != null && !filter.getTrabajadorIdIds().isEmpty())
                predicates.add(root.get(SvAdjuntoEntity_.trabajadorId).in(filter.getTrabajadorIdIds()));

            //--- Filtro FK por IDs: tipoDocumentoPlanillaCode
            if (filter.getTipoDocumentoPlanillaCodeIds() != null && !filter.getTipoDocumentoPlanillaCodeIds().isEmpty())
                predicates.add(root.get(SvAdjuntoEntity_.tipoDocumentoPlanillaCode).in(filter.getTipoDocumentoPlanillaCodeIds()));

            //--- Filtro FK por IDs: solicitudVisitaId
            if (filter.getSolicitudVisitaIdIds() != null && !filter.getSolicitudVisitaIdIds().isEmpty())
                predicates.add(root.get(SvAdjuntoEntity_.solicitudVisitaId).in(filter.getSolicitudVisitaIdIds()));

            //--- Filtro FK por IDs: tipoActividadAltoRiesgoCode
            if (filter.getTipoActividadAltoRiesgoCodeIds() != null && !filter.getTipoActividadAltoRiesgoCodeIds().isEmpty())
                predicates.add(root.get(SvAdjuntoEntity_.tipoActividadAltoRiesgoCode).in(filter.getTipoActividadAltoRiesgoCodeIds()));

            return combineWithAnd(predicates, cb);

        };

    }

}
