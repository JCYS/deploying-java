
/*
* Autor: Anthony Ramos
* Email: anthonyramosdev@gmail.com
* Template pack-angular:src/main/java/modules/core/specification/EntitySpecification.e.vm
*/
package com.copeinca.apicopeincaprov.modules.core.specification;

import com.copeinca.apicopeincaprov.commons.specification.AbstractSpecification;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.SvSolicitudVisistaFilter;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvSolicitudVisistaEntity;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvSolicitudVisistaEntity_;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

/**
 * Specification para búsqueda dinámica de SvSolicitudVisistaEntity.
 * Extiende de AbstractSpecification para reutilizar lógica común de filtros.
 */
public class SvSolicitudVisistaSpecification extends AbstractSpecification {

    /**
     * Genera un {@link Specification} en base a los filtros dinámicos.
     *
     * @param filter Filtro con operaciones dinámicas
     * @return Specification para consultas JPA
     */
    public static Specification<SvSolicitudVisistaEntity> byFilter(SvSolicitudVisistaFilter filter) {

        return (Root<SvSolicitudVisistaEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            //--- Filtro dinámico para id
            addPredicates(predicates, cb, root, SvSolicitudVisistaEntity_.id, filter.getId());

            //--- Filtro dinámico para codigoVisita
            addPredicates(predicates, cb, root, SvSolicitudVisistaEntity_.codigoVisita, filter.getCodigoVisita());

            //--- Filtro dinámico para codigoSistemaExterno
            addPredicates(predicates, cb, root, SvSolicitudVisistaEntity_.codigoSistemaExterno, filter.getCodigoSistemaExterno());

            //--- Filtro dinámico para fechaInicio
            addPredicates(predicates, cb, root, SvSolicitudVisistaEntity_.fechaInicio, filter.getFechaInicio());

            //--- Filtro dinámico para fechaFin
            addPredicates(predicates, cb, root, SvSolicitudVisistaEntity_.fechaFin, filter.getFechaFin());

            //--- Filtro dinámico para motivoVisita
            addPredicates(predicates, cb, root, SvSolicitudVisistaEntity_.motivoVisita, filter.getMotivoVisita());

            //--- Filtro dinámico para fechaAprobacionPersonaVisitada
            addPredicates(predicates, cb, root, SvSolicitudVisistaEntity_.fechaAprobacionPersonaVisitada, filter.getFechaAprobacionPersonaVisitada());

            //--- Filtro dinámico para fechaObservacionAdministrador
            addPredicates(predicates, cb, root, SvSolicitudVisistaEntity_.fechaObservacionAdministrador, filter.getFechaObservacionAdministrador());

            //--- Filtro dinámico para observaciones
            addPredicates(predicates, cb, root, SvSolicitudVisistaEntity_.observaciones, filter.getObservaciones());

            //--- Filtro dinámico para nroOrdenServicio
            addPredicates(predicates, cb, root, SvSolicitudVisistaEntity_.nroOrdenServicio, filter.getNroOrdenServicio());

            //--- Filtro dinámico para indActividadAltoRiesgo
            addPredicates(predicates, cb, root, SvSolicitudVisistaEntity_.indActividadAltoRiesgo, filter.getIndActividadAltoRiesgo());

            //--- Filtro dinámico para indActividadABordo
            addPredicates(predicates, cb, root, SvSolicitudVisistaEntity_.indActividadABordo, filter.getIndActividadABordo());

            //--- Filtro dinámico para indRequiereAndamios
            addPredicates(predicates, cb, root, SvSolicitudVisistaEntity_.indRequiereAndamios, filter.getIndRequiereAndamios());

            //--- Filtro dinámico para indRequiereGrua
            addPredicates(predicates, cb, root, SvSolicitudVisistaEntity_.indRequiereGrua, filter.getIndRequiereGrua());

            //--- Filtro dinámico para indRequiereEquiposMoviles
            addPredicates(predicates, cb, root, SvSolicitudVisistaEntity_.indRequiereEquiposMoviles, filter.getIndRequiereEquiposMoviles());

            //--- Filtro dinámico para indTrabajoBuceo
            addPredicates(predicates, cb, root, SvSolicitudVisistaEntity_.indTrabajoBuceo, filter.getIndTrabajoBuceo());

            //--- Filtro dinámico para ssoMotivo
            addPredicates(predicates, cb, root, SvSolicitudVisistaEntity_.ssoMotivo, filter.getSsoMotivo());

            //--- Filtro dinámico para ssoDescargo
            addPredicates(predicates, cb, root, SvSolicitudVisistaEntity_.ssoDescargo, filter.getSsoDescargo());

            //--- Filtro dinámico para caMotivo
            addPredicates(predicates, cb, root, SvSolicitudVisistaEntity_.caMotivo, filter.getCaMotivo());

            //--- Filtro dinámico para caDescargo
            addPredicates(predicates, cb, root, SvSolicitudVisistaEntity_.caDescargo, filter.getCaDescargo());

            //--- Filtro FK por IDs: estadoSsoCode
            if (filter.getEstadoSsoCodeIds() != null && !filter.getEstadoSsoCodeIds().isEmpty())
                predicates.add(root.get(SvSolicitudVisistaEntity_.estadoSsoCode).in(filter.getEstadoSsoCodeIds()));

            //--- Filtro FK por IDs: estadoCalidadAmbientalCode
            if (filter.getEstadoCalidadAmbientalCodeIds() != null && !filter.getEstadoCalidadAmbientalCodeIds().isEmpty())
                predicates.add(root.get(SvSolicitudVisistaEntity_.estadoCalidadAmbientalCode).in(filter.getEstadoCalidadAmbientalCodeIds()));

            //--- Filtro FK por IDs: estadoSolicitudVisitaCode
            if (filter.getEstadoSolicitudVisitaCodeIds() != null && !filter.getEstadoSolicitudVisitaCodeIds().isEmpty())
                predicates.add(root.get(SvSolicitudVisistaEntity_.estadoSolicitudVisitaCode).in(filter.getEstadoSolicitudVisitaCodeIds()));

            //--- Filtro FK por IDs: sedeId
            if (filter.getSedeIdIds() != null && !filter.getSedeIdIds().isEmpty())
                predicates.add(root.get(SvSolicitudVisistaEntity_.sedeId).in(filter.getSedeIdIds()));

            //--- Filtro FK por IDs: proveedorId
            if (filter.getProveedorIdIds() != null && !filter.getProveedorIdIds().isEmpty())
                predicates.add(root.get(SvSolicitudVisistaEntity_.proveedorId).in(filter.getProveedorIdIds()));

            //--- Filtro FK por IDs: personalId
            if (filter.getPersonalIdIds() != null && !filter.getPersonalIdIds().isEmpty())
                predicates.add(root.get(SvSolicitudVisistaEntity_.personalId).in(filter.getPersonalIdIds()));

            return combineWithAnd(predicates, cb);

        };

    }

}
