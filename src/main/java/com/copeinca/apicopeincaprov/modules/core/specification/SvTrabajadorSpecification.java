
/*
* Autor: Anthony Ramos
* Email: anthonyramosdev@gmail.com
* Template pack-angular:src/main/java/modules/core/specification/EntitySpecification.e.vm
*/
package com.copeinca.apicopeincaprov.modules.core.specification;

import com.copeinca.apicopeincaprov.commons.specification.AbstractSpecification;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.SvTrabajadorFilter;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvTrabajadorEntity;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvTrabajadorEntity_;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

/**
 * Specification para búsqueda dinámica de SvTrabajadorEntity.
 * Extiende de AbstractSpecification para reutilizar lógica común de filtros.
 */
public class SvTrabajadorSpecification extends AbstractSpecification {

    /**
     * Genera un {@link Specification} en base a los filtros dinámicos.
     *
     * @param filter Filtro con operaciones dinámicas
     * @return Specification para consultas JPA
     */
    public static Specification<SvTrabajadorEntity> byFilter(SvTrabajadorFilter filter) {

        return (Root<SvTrabajadorEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            //--- Filtro dinámico para id
            addPredicates(predicates, cb, root, SvTrabajadorEntity_.id, filter.getId());

            //--- Filtro dinámico para nroDocumentoIdentidad
            addPredicates(predicates, cb, root, SvTrabajadorEntity_.nroDocumentoIdentidad, filter.getNroDocumentoIdentidad());

            //--- Filtro dinámico para nombre
            addPredicates(predicates, cb, root, SvTrabajadorEntity_.nombre, filter.getNombre());

            //--- Filtro dinámico para telefono
            addPredicates(predicates, cb, root, SvTrabajadorEntity_.telefono, filter.getTelefono());

            //--- Filtro dinámico para email
            addPredicates(predicates, cb, root, SvTrabajadorEntity_.email, filter.getEmail());

            //--- Filtro dinámico para ssoMotivo
            addPredicates(predicates, cb, root, SvTrabajadorEntity_.ssoMotivo, filter.getSsoMotivo());

            //--- Filtro dinámico para soTieneVigencia
            addPredicates(predicates, cb, root, SvTrabajadorEntity_.soTieneVigencia, filter.getSoTieneVigencia());

            //--- Filtro dinámico para ssoFechaInicioVigencia
            addPredicates(predicates, cb, root, SvTrabajadorEntity_.ssoFechaInicioVigencia, filter.getSsoFechaInicioVigencia());

            //--- Filtro dinámico para ssoFechaFinVigencia
            addPredicates(predicates, cb, root, SvTrabajadorEntity_.ssoFechaFinVigencia, filter.getSsoFechaFinVigencia());

            //--- Filtro dinámico para indTrabajoAltoRiesgo
            addPredicates(predicates, cb, root, SvTrabajadorEntity_.indTrabajoAltoRiesgo, filter.getIndTrabajoAltoRiesgo());

            //--- Filtro dinámico para indPrevencionista
            addPredicates(predicates, cb, root, SvTrabajadorEntity_.indPrevencionista, filter.getIndPrevencionista());

            //--- Filtro dinámico para isActive
            addPredicates(predicates, cb, root, SvTrabajadorEntity_.isActive, filter.getIsActive());

            //--- Filtro FK por IDs: proveedorId
            if (filter.getProveedorIdIds() != null && !filter.getProveedorIdIds().isEmpty())
                predicates.add(root.get(SvTrabajadorEntity_.proveedorId).in(filter.getProveedorIdIds()));

            //--- Filtro FK por IDs: tipoDocumentoIdentidadCode
            if (filter.getTipoDocumentoIdentidadCodeIds() != null && !filter.getTipoDocumentoIdentidadCodeIds().isEmpty())
                predicates.add(root.get(SvTrabajadorEntity_.tipoDocumentoIdentidadCode).in(filter.getTipoDocumentoIdentidadCodeIds()));

            //--- Filtro FK por IDs: estadoSsoCode
            if (filter.getEstadoSsoCodeIds() != null && !filter.getEstadoSsoCodeIds().isEmpty())
                predicates.add(root.get(SvTrabajadorEntity_.estadoSsoCode).in(filter.getEstadoSsoCodeIds()));

            return combineWithAnd(predicates, cb);

        };

    }

}
