
/*
* Autor: Anthony Ramos
* Email: anthonyramosdev@gmail.com
* Template pack-angular:src/main/java/modules/core/specification/EntitySpecification.e.vm
*/
package com.copeinca.apicopeincaprov.modules.core.specification;

import com.copeinca.apicopeincaprov.commons.specification.AbstractSpecification;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.SvPersonalFilter;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvPersonalEntity;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvPersonalEntity_;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvPersonalSedeEntity;
import com.copeinca.apicopeincaprov.modules.core.models.entities.SvSedeEntity;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

/**
 * Specification para búsqueda dinámica de SvPersonalEntity.
 * Extiende de AbstractSpecification para reutilizar lógica común de filtros.
 */
public class SvPersonalSpecification extends AbstractSpecification {

    /**
     * Genera un {@link Specification} en base a los filtros dinámicos.
     *
     * @param filter Filtro con operaciones dinámicas
     * @return Specification para consultas JPA
     */
    public static Specification<SvPersonalEntity> byFilter(SvPersonalFilter filter) {

        return (Root<SvPersonalEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            //--- Filtro dinámico para id
            addPredicates(predicates, cb, root, SvPersonalEntity_.id, filter.getId());

            //--- Filtro dinámico para usuario
            addPredicates(predicates, cb, root, SvPersonalEntity_.usuario, filter.getUsuario());

            //--- Filtro dinámico para nombreUsuario
            addPredicates(predicates, cb, root, SvPersonalEntity_.nombreUsuario, filter.getNombreUsuario());

            //--- Filtro dinámico para codigo
            addPredicates(predicates, cb, root, SvPersonalEntity_.codigo, filter.getCodigo());

            //--- Filtro dinámico para descripcion
            addPredicates(predicates, cb, root, SvPersonalEntity_.descripcion, filter.getDescripcion());

            //--- Filtro dinámico para ambito
            addPredicates(predicates, cb, root, SvPersonalEntity_.ambito, filter.getAmbito());

            //--- Filtro dinámico para influencia
            addPredicates(predicates, cb, root, SvPersonalEntity_.influencia, filter.getInfluencia());

            //--- Filtro dinámico para dependencia
            addPredicates(predicates, cb, root, SvPersonalEntity_.dependencia, filter.getDependencia());

            //--- Filtro dinámico para campoPlantillaLf
            addPredicates(predicates, cb, root, SvPersonalEntity_.campoPlantillaLf, filter.getCampoPlantillaLf());

            //--- Filtro dinámico para obligatorio
            addPredicates(predicates, cb, root, SvPersonalEntity_.obligatorio, filter.getObligatorio());

            //--- Filtro dinámico para vencimiento
            addPredicates(predicates, cb, root, SvPersonalEntity_.vencimiento, filter.getVencimiento());

            //--- Filtro dinámico para numeroCaso
            addPredicates(predicates, cb, root, SvPersonalEntity_.numeroCaso, filter.getNumeroCaso());

            //--- Filtro dinámico para mensaje
            addPredicates(predicates, cb, root, SvPersonalEntity_.mensaje, filter.getMensaje());

            //--- Filtro FK por IDs: tipoDocumentoIdentidadCode
            if (filter.getTipoDocumentoIdentidadCodeIds() != null && !filter.getTipoDocumentoIdentidadCodeIds().isEmpty())
                predicates.add(root.get(SvPersonalEntity_.tipoDocumentoIdentidadCode).in(filter.getTipoDocumentoIdentidadCodeIds()));

            if (filter.getSedeIds() != null && !filter.getSedeIds().isEmpty()) {
                Subquery<Long> subquery = query.subquery(Long.class);
                Root<SvPersonalSedeEntity> subRoot = subquery.from(SvPersonalSedeEntity.class);

                subquery.select(cb.literal(1L))
                        .where(cb.and(
                                cb.equal(subRoot.get("personalId"), root.get("id")),
                                subRoot.get("sedeId").in(filter.getSedeIds())
                        ));

                predicates.add(cb.exists(subquery));
            }

            if (filter.getSedeNames() != null && !filter.getSedeNames().isEmpty()) {
                Subquery<Long> subquery = query.subquery(Long.class);
                Root<SvPersonalSedeEntity> ps = subquery.from(SvPersonalSedeEntity.class);
                Root<SvSedeEntity> s = subquery.from(SvSedeEntity.class);

                subquery.select(cb.literal(1L))
                        .where(
                                cb.and(
                                        cb.equal(ps.get("personalId"), root.get("id")),
                                        cb.equal(ps.get("sedeId"), s.get("id")),
                                        s.get("name").in(filter.getSedeNames())
                                )
                        );

                predicates.add(cb.exists(subquery));
            }

            return combineWithAnd(predicates, cb);

        };

    }

}
