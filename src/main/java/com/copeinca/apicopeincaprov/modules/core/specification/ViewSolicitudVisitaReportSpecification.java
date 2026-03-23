
/*
* Autor: Anthony Ramos
* Email: anthonyramosdev@gmail.com
* Template pack-angular:src/main/java/modules/core/specification/EntitySpecification.e.vm
*/
package com.copeinca.apicopeincaprov.modules.core.specification;

import com.copeinca.apicopeincaprov.commons.specification.AbstractSpecification;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.ViewSolicitudVisitaReportFilter;
import com.copeinca.apicopeincaprov.modules.core.models.entities.*;
import com.copeinca.apicopeincaprov.modules.core.models.views.ViewSolicitudVisitaReportView;
import com.copeinca.apicopeincaprov.modules.core.models.views.ViewSolicitudVisitaReportView_;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

/**
 * Specification para búsqueda dinámica de ViewSolicitudVisitaReportView.
 * Extiende de AbstractSpecification para reutilizar lógica común de filtros.
 */
public class ViewSolicitudVisitaReportSpecification extends AbstractSpecification {

    /**
     * Genera un {@link Specification} en base a los filtros dinámicos.
     *
     * @param filter Filtro con operaciones dinámicas
     * @return Specification para consultas JPA
     */
    public static Specification<ViewSolicitudVisitaReportView> byFilter(ViewSolicitudVisitaReportFilter filter) {

        return (Root<ViewSolicitudVisitaReportView> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            //--- Filtro dinámico para id
            addPredicates(predicates, cb, root, ViewSolicitudVisitaReportView_.id, filter.getId());

            //--- Filtro dinámico para codigoVisita
            addPredicates(predicates, cb, root, ViewSolicitudVisitaReportView_.codigoVisita, filter.getCodigoVisita());

            //--- Filtro dinámico para codigoSistemaExterno
            addPredicates(predicates, cb, root, ViewSolicitudVisitaReportView_.codigoSistemaExterno, filter.getCodigoSistemaExterno());

            //--- Filtro dinámico para fechaInicio
            addPredicates(predicates, cb, root, ViewSolicitudVisitaReportView_.fechaInicio, filter.getFechaInicio());

            //--- Filtro dinámico para fechaFin
            addPredicates(predicates, cb, root, ViewSolicitudVisitaReportView_.fechaFin, filter.getFechaFin());

            //--- Filtro dinámico para motivoVisita
            addPredicates(predicates, cb, root, ViewSolicitudVisitaReportView_.motivoVisita, filter.getMotivoVisita());

            //--- Filtro dinámico para fechaAprobacionPersonaVisitada
            addPredicates(predicates, cb, root, ViewSolicitudVisitaReportView_.fechaAprobacionPersonaVisitada, filter.getFechaAprobacionPersonaVisitada());

            //--- Filtro dinámico para fechaObservacionAdministrador
            addPredicates(predicates, cb, root, ViewSolicitudVisitaReportView_.fechaObservacionAdministrador, filter.getFechaObservacionAdministrador());

            //--- Filtro dinámico para observaciones
            addPredicates(predicates, cb, root, ViewSolicitudVisitaReportView_.observaciones, filter.getObservaciones());

            //--- Filtro dinámico para nroOrdenServicio
            addPredicates(predicates, cb, root, ViewSolicitudVisitaReportView_.nroOrdenServicio, filter.getNroOrdenServicio());

            //--- Filtro dinámico para indActividadAltoRiesgo
            addPredicates(predicates, cb, root, ViewSolicitudVisitaReportView_.indActividadAltoRiesgo, filter.getIndActividadAltoRiesgo());

            //--- Filtro dinámico para indActividadABordo
            addPredicates(predicates, cb, root, ViewSolicitudVisitaReportView_.indActividadABordo, filter.getIndActividadABordo());

            //--- Filtro dinámico para indRequiereAndamios
            addPredicates(predicates, cb, root, ViewSolicitudVisitaReportView_.indRequiereAndamios, filter.getIndRequiereAndamios());

            //--- Filtro dinámico para indRequiereGrua
            addPredicates(predicates, cb, root, ViewSolicitudVisitaReportView_.indRequiereGrua, filter.getIndRequiereGrua());

            //--- Filtro dinámico para indRequiereEquiposMoviles
            addPredicates(predicates, cb, root, ViewSolicitudVisitaReportView_.indRequiereEquiposMoviles, filter.getIndRequiereEquiposMoviles());

            //--- Filtro dinámico para indTrabajoBuceo
            addPredicates(predicates, cb, root, ViewSolicitudVisitaReportView_.indTrabajoBuceo, filter.getIndTrabajoBuceo());

            //--- Filtro dinámico para ssoMotivo
            addPredicates(predicates, cb, root, ViewSolicitudVisitaReportView_.ssoMotivo, filter.getSsoMotivo());

            //--- Filtro dinámico para ssoDescargo
            addPredicates(predicates, cb, root, ViewSolicitudVisitaReportView_.ssoDescargo, filter.getSsoDescargo());

            //--- Filtro dinámico para caMotivo
            addPredicates(predicates, cb, root, ViewSolicitudVisitaReportView_.caMotivo, filter.getCaMotivo());

            //--- Filtro dinámico para caDescargo
            addPredicates(predicates, cb, root, ViewSolicitudVisitaReportView_.caDescargo, filter.getCaDescargo());

            //--- Filtro dinámico para estadoSsoCode
            addPredicates(predicates, cb, root, ViewSolicitudVisitaReportView_.estadoSsoCode, filter.getEstadoSsoCode());

            //--- Filtro dinámico para estadoCalidadAmbientalCode
            addPredicates(predicates, cb, root, ViewSolicitudVisitaReportView_.estadoCalidadAmbientalCode, filter.getEstadoCalidadAmbientalCode());

            //--- Filtro dinámico para estadoSolicitudVisitaCode
            addPredicates(predicates, cb, root, ViewSolicitudVisitaReportView_.estadoSolicitudVisitaCode, filter.getEstadoSolicitudVisitaCode());

            //--- Filtro dinámico para sedeId
            addPredicates(predicates, cb, root, ViewSolicitudVisitaReportView_.sedeId, filter.getSedeId());

            //--- Filtro dinámico para proveedorId
            addPredicates(predicates, cb, root, ViewSolicitudVisitaReportView_.proveedorId, filter.getProveedorId());

            //--- Filtro dinámico para personalId
            addPredicates(predicates, cb, root, ViewSolicitudVisitaReportView_.personalId, filter.getPersonalId());

            //--- Filtro dinámico para sedeNombre
            addPredicates(predicates, cb, root, ViewSolicitudVisitaReportView_.sedeNombre, filter.getSedeNombre());

            //--- Filtro dinámico para sedeAmbito
            addPredicates(predicates, cb, root, ViewSolicitudVisitaReportView_.sedeAmbito, filter.getSedeAmbito());

            //--- Filtro dinámico para personalNombreUsuario
            addPredicates(predicates, cb, root, ViewSolicitudVisitaReportView_.personalNombreUsuario, filter.getPersonalNombreUsuario());

            //--- Filtro dinámico para personalUsuario
            addPredicates(predicates, cb, root, ViewSolicitudVisitaReportView_.personalUsuario, filter.getPersonalUsuario());

            //--- Filtro dinámico para proveedorNombreFiscal
            addPredicates(predicates, cb, root, ViewSolicitudVisitaReportView_.proveedorNombreFiscal, filter.getProveedorNombreFiscal());

            //--- Filtro dinámico para proveedorNroDocumentoIdentidad
            addPredicates(predicates, cb, root, ViewSolicitudVisitaReportView_.proveedorNroDocumentoIdentidad, filter.getProveedorNroDocumentoIdentidad());

            //--- Filtro dinámico para proveedorEmail
            addPredicates(predicates, cb, root, ViewSolicitudVisitaReportView_.proveedorEmail, filter.getProveedorEmail());

            //--- Filtro dinámico para proveedorContactoPrincipal
            addPredicates(predicates, cb, root, ViewSolicitudVisitaReportView_.proveedorContactoPrincipal, filter.getProveedorContactoPrincipal());

            //--- Filtro dinámico para esvNombre
            addPredicates(predicates, cb, root, ViewSolicitudVisitaReportView_.esvNombre, filter.getEsvNombre());

            //--- Filtro dinámico para ssoEstadoNombre
            addPredicates(predicates, cb, root, ViewSolicitudVisitaReportView_.ssoEstadoNombre, filter.getSsoEstadoNombre());

            //--- Filtro dinámico para ssoEstadoDescripcion
            addPredicates(predicates, cb, root, ViewSolicitudVisitaReportView_.ssoEstadoDescripcion, filter.getSsoEstadoDescripcion());

            //--- Filtro dinámico para caEstadoNombre
            addPredicates(predicates, cb, root, ViewSolicitudVisitaReportView_.caEstadoNombre, filter.getCaEstadoNombre());

            //--- Filtro dinámico para caEstadoDescripcion
            addPredicates(predicates, cb, root, ViewSolicitudVisitaReportView_.caEstadoDescripcion, filter.getCaEstadoDescripcion());

            //--- Filtro dinámico para tarDescripciones
            addPredicates(predicates, cb, root, ViewSolicitudVisitaReportView_.tarDescripciones, filter.getTarDescripciones());

            //--- Filtro dinámico para nDias
            addPredicates(predicates, cb, root, ViewSolicitudVisitaReportView_.nDias, filter.getNDias());

            //--- Filtro dinámico para nTrabajadores
            addPredicates(predicates, cb, root, ViewSolicitudVisitaReportView_.nTrabajadores, filter.getNTrabajadores());

            //--- Filtro dinámico para areasIds
            addPredicates(predicates, cb, root, ViewSolicitudVisitaReportView_.areasIds, filter.getAreasIds());

            //--- Filtro dinámico para areasNombres
            addPredicates(predicates, cb, root, ViewSolicitudVisitaReportView_.areasNombres, filter.getAreasNombres());

            //--- Filtro dinámico para zonasIds
            addPredicates(predicates, cb, root, ViewSolicitudVisitaReportView_.zonasIds, filter.getZonasIds());

            //--- Filtro dinámico para zonasNombres
            addPredicates(predicates, cb, root, ViewSolicitudVisitaReportView_.zonasNombres, filter.getZonasNombres());


            boolean hasTipoDoc = filter.getTipoDocTrbj() != null && !filter.getTipoDocTrbj().isBlank();
            boolean hasNroDoc  = filter.getNroDocTrbj() != null && !filter.getNroDocTrbj().isBlank();
            boolean hasNombre  = filter.getNombreTrbj() != null && !filter.getNombreTrbj().isBlank();

            if (hasTipoDoc || hasNroDoc || hasNombre) {

                // EXISTS (detalle)
                Subquery<Integer> sqDetalle = query.subquery(Integer.class);
                Root<SvSolicitudVisitaDetalleEntity> det = sqDetalle.from(SvSolicitudVisitaDetalleEntity.class);
                sqDetalle.select(cb.literal(1));

                // EXISTS (trabajador)
                Subquery<Integer> sqTrab = sqDetalle.subquery(Integer.class);
                Root<SvTrabajadorEntity> tra = sqTrab.from(SvTrabajadorEntity.class);
                sqTrab.select(cb.literal(1));

                List<Predicate> pTrab = new ArrayList<>();

                // FK: trabajador.id = detalle.trabajadorId
                pTrab.add(cb.equal(
                        tra.get(SvTrabajadorEntity_.id),
                        det.get(SvSolicitudVisitaDetalleEntity_.trabajadorId)
                ));

                // ✅ NO eliminado
                pTrab.add(cb.isFalse(tra.get(SvTrabajadorEntity_.isDeleted)));

                // ✅ si quieres, solo activos (recomendado)
                // pTrab.add(cb.isTrue(tra.get(SvTrabajadorEntity_.isActive)));

                if (hasTipoDoc) {
                    pTrab.add(cb.equal(
                            tra.get(SvTrabajadorEntity_.tipoDocumentoIdentidadCode),
                            filter.getTipoDocTrbj().trim()
                    ));
                }

                if (hasNroDoc) {
                    pTrab.add(cb.equal(
                            tra.get(SvTrabajadorEntity_.nroDocumentoIdentidad),
                            filter.getNroDocTrbj().trim()
                    ));
                }

                if (hasNombre) {
                    String pattern = "%" + filter.getNombreTrbj().trim().toLowerCase() + "%";
                    pTrab.add(cb.like(cb.lower(tra.get(SvTrabajadorEntity_.nombre)), pattern));
                }

                sqTrab.where(pTrab.toArray(new Predicate[0]));

                List<Predicate> pDet = new ArrayList<>();

                // correlación con el VIEW (tu SQL ya lo muestra OK)
                pDet.add(cb.equal(
                        det.get(SvSolicitudVisitaDetalleEntity_.solicitudVisitaId),
                        root.get(ViewSolicitudVisitaReportView_.id) // o el atributo equivalente
                ));

                // ✅ detalle NO eliminado
                pDet.add(cb.isFalse(det.get(SvSolicitudVisitaDetalleEntity_.isDeleted)));
//                cb.isFalse(det.get(SvSolicitudVisitaDetalleEntity_.isDeleted))

                pDet.add(cb.exists(sqTrab));

                sqDetalle.where(pDet.toArray(new Predicate[0]));

                predicates.add(cb.exists(sqDetalle));
            }


            return combineWithAnd(predicates, cb);

        };

    }

}
