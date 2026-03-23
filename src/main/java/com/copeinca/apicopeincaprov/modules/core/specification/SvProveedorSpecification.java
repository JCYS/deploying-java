
/*
* Autor: Anthony Ramos
* Email: anthonyramosdev@gmail.com
* Template pack-angular:src/main/java/modules/core/specification/EntitySpecification.e.vm
*/
package com.copeinca.apicopeincaprov.modules.core.specification;

import com.copeinca.apicopeincaprov.commons.specification.AbstractSpecification;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.SvProveedorFilter;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvProveedorEntity;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvProveedorEntity_;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvProveedorSedeEntity;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvTrabajadorEntity;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

/**
 * Specification para búsqueda dinámica de SvProveedorEntity.
 * Extiende de AbstractSpecification para reutilizar lógica común de filtros.
 */
public class SvProveedorSpecification extends AbstractSpecification {

    /**
     * Genera un {@link Specification} en base a los filtros dinámicos.
     *
     * @param filter Filtro con operaciones dinámicas
     * @return Specification para consultas JPA
     */
    public static Specification<SvProveedorEntity> byFilter(SvProveedorFilter filter) {

        return (Root<SvProveedorEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            //--- Filtro dinámico para id
            addPredicates(predicates, cb, root, SvProveedorEntity_.id, filter.getId());

            //--- Filtro dinámico para nroDocumentoIdentidad
            addPredicates(predicates, cb, root, SvProveedorEntity_.nroDocumentoIdentidad, filter.getNroDocumentoIdentidad());

            //--- Filtro dinámico para nombreFiscal
            addPredicates(predicates, cb, root, SvProveedorEntity_.nombreFiscal, filter.getNombreFiscal());

            //--- Filtro dinámico para email
            addPredicates(predicates, cb, root, SvProveedorEntity_.email, filter.getEmail());

            //--- Filtro dinámico para direccion
            addPredicates(predicates, cb, root, SvProveedorEntity_.direccion, filter.getDireccion());

            //--- Filtro dinámico para contactoPrincipal
            addPredicates(predicates, cb, root, SvProveedorEntity_.contactoPrincipal, filter.getContactoPrincipal());

            //--- Filtro dinámico para contactoPrincipalTelefono
            addPredicates(predicates, cb, root, SvProveedorEntity_.contactoPrincipalTelefono, filter.getContactoPrincipalTelefono());

            //--- Filtro dinámico para ssoTieneVigencia
            addPredicates(predicates, cb, root, SvProveedorEntity_.ssoTieneVigencia, filter.getSsoTieneVigencia());

            //--- Filtro dinámico para ssoFechaInicioVigencia
            addPredicates(predicates, cb, root, SvProveedorEntity_.ssoFechaInicioVigencia, filter.getSsoFechaInicioVigencia());

            //--- Filtro dinámico para ssoFechaFinVigencia
            addPredicates(predicates, cb, root, SvProveedorEntity_.ssoFechaFinVigencia, filter.getSsoFechaFinVigencia());

            //--- Filtro dinámico para ssoMotivo
            addPredicates(predicates, cb, root, SvProveedorEntity_.ssoMotivo, filter.getSsoMotivo());

            //--- Filtro dinámico para ssoUsuarioEvaluador
            addPredicates(predicates, cb, root, SvProveedorEntity_.ssoUsuarioEvaluador, filter.getSsoUsuarioEvaluador());

            //--- Filtro dinámico para ssoFechaEvaluacion
            addPredicates(predicates, cb, root, SvProveedorEntity_.ssoFechaEvaluacion, filter.getSsoFechaEvaluacion());

            //--- Filtro dinámico para indRealizaActividadAltoRiesgo
            addPredicates(predicates, cb, root, SvProveedorEntity_.indRealizaActividadAltoRiesgo, filter.getIndRealizaActividadAltoRiesgo());

            //--- Filtro dinámico para indRealizaActividadABordo
            addPredicates(predicates, cb, root, SvProveedorEntity_.indRealizaActividadABordo, filter.getIndRealizaActividadABordo());

            //--- Filtro dinámico para caTieneVigencia
            addPredicates(predicates, cb, root, SvProveedorEntity_.caTieneVigencia, filter.getCaTieneVigencia());

            //--- Filtro dinámico para caFechaInicioVigencia
            addPredicates(predicates, cb, root, SvProveedorEntity_.caFechaInicioVigencia, filter.getCaFechaInicioVigencia());

            //--- Filtro dinámico para caFechaFinVigencia
            addPredicates(predicates, cb, root, SvProveedorEntity_.caFechaFinVigencia, filter.getCaFechaFinVigencia());

            //--- Filtro dinámico para caMotivo
            addPredicates(predicates, cb, root, SvProveedorEntity_.caMotivo, filter.getCaMotivo());

            //--- Filtro dinámico para caUsuarioEvaluador
            addPredicates(predicates, cb, root, SvProveedorEntity_.caUsuarioEvaluador, filter.getCaUsuarioEvaluador());

            //--- Filtro dinámico para caFechaEvaluacion
            addPredicates(predicates, cb, root, SvProveedorEntity_.caFechaEvaluacion, filter.getCaFechaEvaluacion());

            //--- Filtro dinámico para origenRegistro
            addPredicates(predicates, cb, root, SvProveedorEntity_.origenRegistro, filter.getOrigenRegistro());

            //--- Filtro dinámico para indProveedorNotificadoCreacion
            addPredicates(predicates, cb, root, SvProveedorEntity_.indProveedorNotificadoCreacion, filter.getIndProveedorNotificadoCreacion());

            //--- Filtro dinámico para telefono
            addPredicates(predicates, cb, root, SvProveedorEntity_.telefono, filter.getTelefono());

            //--- Filtro dinámico para nacional
            addPredicates(predicates, cb, root, SvProveedorEntity_.nacional, filter.getNacional());

            //--- Filtro FK por IDs: estadoCalidadAmbientalCode
            if (filter.getEstadoCalidadAmbientalCodeIds() != null && !filter.getEstadoCalidadAmbientalCodeIds().isEmpty())
                predicates.add(root.get(SvProveedorEntity_.estadoCalidadAmbientalCode).in(filter.getEstadoCalidadAmbientalCodeIds()));

            //--- Filtro FK por IDs: estadoSsoCode
            if (filter.getEstadoSsoCodeIds() != null && !filter.getEstadoSsoCodeIds().isEmpty())
                predicates.add(root.get(SvProveedorEntity_.estadoSsoCode).in(filter.getEstadoSsoCodeIds()));

            if (filter.getSedeIds() != null && !filter.getSedeIds().isEmpty()) {
                Subquery<Long> subquery = query.subquery(Long.class);
                Root<SvProveedorSedeEntity> subRoot = subquery.from(SvProveedorSedeEntity.class);

                subquery.select(cb.literal(1L))
                        .where(cb.and(
                                cb.equal(subRoot.get("proveedorId"), root.get("id")),
                                subRoot.get("sedeId").in(filter.getSedeIds())
                        ));

                predicates.add(cb.exists(subquery));
            }

            if (filter.getTrabajadorIds() != null && !filter.getTrabajadorIds().isEmpty()) {
                Subquery<Long> subquery = query.subquery(Long.class);
                Root<SvTrabajadorEntity> subRoot = subquery.from(SvTrabajadorEntity.class);

                subquery.select(cb.literal(1L))
                        .where(cb.and(
                                cb.equal(subRoot.get("proveedorId"), root.get("id")),
                                subRoot.get("id").in(filter.getTrabajadorIds())
                        ));

                predicates.add(cb.exists(subquery));
            }

            if (filter.getTrabajadorNombre() != null && !filter.getTrabajadorNombre().isEmpty()) {
                Subquery<Long> subquery = query.subquery(Long.class);
                Root<SvTrabajadorEntity> subRoot = subquery.from(SvTrabajadorEntity.class);

                List<Predicate> likePredicates = new ArrayList<>();
                for (String nombre : filter.getTrabajadorNombre()) {
                    likePredicates.add(cb.like(cb.lower(subRoot.get("nombre")), "%" + nombre.toLowerCase() + "%"));
                }

                subquery.select(cb.literal(1L))
                        .where(cb.and(
                                cb.equal(subRoot.get("proveedorId"), root.get("id")),
                                cb.or(likePredicates.toArray(new Predicate[0]))
                        ));

                predicates.add(cb.exists(subquery));
            }

            if (filter.getTrabajadorNroDocIdentidad() != null && !filter.getTrabajadorNroDocIdentidad().isEmpty() &&
                    filter.getTrabajadorTipoDocIdentidad() != null && !filter.getTrabajadorTipoDocIdentidad().isEmpty()) {
                Subquery<Long> subquery = query.subquery(Long.class);
                Root<SvTrabajadorEntity> subRoot = subquery.from(SvTrabajadorEntity.class);

                List<Predicate> likePredicates = new ArrayList<>();
                for (String nroDoc : filter.getTrabajadorNroDocIdentidad()) {
                    likePredicates.add(cb.like(cb.lower(subRoot.get("nroDocumentoIdentidad")), nroDoc.toLowerCase() + "%"));
                }

                subquery.select(cb.literal(1L))
                        .where(cb.and(
                                cb.equal(subRoot.get("proveedorId"), root.get("id")),
                                cb.and(
                                    subRoot.get("tipoDocumentoIdentidadCode").in(filter.getTrabajadorTipoDocIdentidad()),
                                    cb.or(likePredicates.toArray(new Predicate[0]))
                                )
                        ));

                predicates.add(cb.exists(subquery));
            }

            if (filter.getTrabajadorEstadoSSOCodeIds() != null && !filter.getTrabajadorEstadoSSOCodeIds().isEmpty()) {
                Subquery<Long> subquery = query.subquery(Long.class);
                Root<SvTrabajadorEntity> subRoot = subquery.from(SvTrabajadorEntity.class);

                subquery.select(cb.literal(1L))
                        .where(cb.and(
                                cb.equal(subRoot.get("proveedorId"), root.get("id")),
                                subRoot.get("estadoSsoCode").in(filter.getTrabajadorEstadoSSOCodeIds())
                        ));

                predicates.add(cb.exists(subquery));
            }

            if (filter.getTrabajadorSSOFechaInicioVigencia() != null) {
                Subquery<Long> subquery = query.subquery(Long.class);
                Root<SvTrabajadorEntity> subRoot = subquery.from(SvTrabajadorEntity.class);

                subquery.select(cb.literal(1L))
                        .where(cb.and(
                                cb.equal(subRoot.get("proveedorId"), root.get("id")),
                                cb.greaterThanOrEqualTo(
                                        subRoot.get("ssoFechaInicioVigencia"),
                                        filter.getTrabajadorSSOFechaInicioVigencia()
                                )
                        ));

                predicates.add(cb.exists(subquery));
            }

            if (filter.getTrabajadorSSOFechaFinVigencia() != null) {
                Subquery<Long> subquery = query.subquery(Long.class);
                Root<SvTrabajadorEntity> subRoot = subquery.from(SvTrabajadorEntity.class);

                subquery.select(cb.literal(1L))
                        .where(cb.and(
                                cb.equal(subRoot.get("proveedorId"), root.get("id")),
                                cb.lessThanOrEqualTo(
                                        subRoot.get("ssoFechaFinVigencia"),
                                        filter.getTrabajadorSSOFechaFinVigencia()
                                )
                        ));

                predicates.add(cb.exists(subquery));
            }

            return combineWithAnd(predicates, cb);

        };

    }

}
