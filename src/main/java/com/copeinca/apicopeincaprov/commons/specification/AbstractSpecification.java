
/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/commons/specification/AbstractSpecification.e.vm
 */
package com.copeinca.apicopeincaprov.commons.specification;

import com.copeinca.apicopeincaprov.commons.models.FilterOperation;
import com.copeinca.apicopeincaprov.commons.models.OperatorEnum;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.metamodel.SingularAttribute;

import java.util.List;

/**
 * Clase abstracta base para todas las Specifications.
 * Contiene la lógica común para filtros dinámicos con operadores OData.
 */
public abstract class AbstractSpecification {

    /**
    * Añade predicados basados en las operaciones de filtro.
    *
    * @param predicates Lista de predicados donde se añadirán los nuevos
    * @param cb         CriteriaBuilder para construir predicados
    * @param root       Root entity para construir paths
    * @param field      SingularAttribute del campo a filtrar
    * @param operations Lista de operaciones de filtro a procesar
    * @param <T>        Tipo de la entidad root
    * @param <D>        Tipo de datos del campo
    */

    protected static <T, D> void addPredicates(List<Predicate> predicates, CriteriaBuilder cb, Root<T> root, SingularAttribute<T, D> field,
            List<FilterOperation<D>> operations) {

        if (operations == null || operations.isEmpty())
            return;

        for (FilterOperation<D> operation : operations) {

            Predicate predicate = createPredicate(operation, root, cb, field);

            if (predicate != null)
                predicates.add(predicate);

        }

    }

    /**
    * Crea un predicado basado en la operación y el operador.
    *
    * @param operation Operación de filtro con operador y valores
    * @param root      Root entity para construir paths
    * @param cb        CriteriaBuilder para construir predicados
    * @param field     SingularAttribute del campo a filtrar
    * @param <T>       Tipo de la entidad root
    * @param <D>       Tipo de datos del campo
    * @return Predicate construido o null si no se puede crear
    */
    protected static <T, D> Predicate createPredicate(FilterOperation<D> operation, Root<T> root, CriteriaBuilder cb, SingularAttribute<T, D> field) {

        if (operation == null || operation.getOperator() == null)
            return null;

        Path<T> path = root.get(field.getName());

        List<D> values = operation.getValues();

        // Para operadores IS_NULL e IS_NOT_NULL no se necesitan valores
        if (operation.getOperator() == OperatorEnum.IS_NULL) {
            return cb.isNull(path);
        }

        if (operation.getOperator() == OperatorEnum.IS_NOT_NULL) {
            return cb.isNotNull(path);
        }

        // Para el resto de operadores se requieren valores
        if (values == null || values.isEmpty()) {
            return null;
        }

        D firstValue = values.get(0);

        switch (operation.getOperator()) {
        case EQ:
            return cb.equal(path, firstValue);

        case NE:
            return cb.notEqual(path, firstValue);

        case IN:
            return path.in(values);

        case NOT_IN:
            return cb.not(path.in(values));

        case GT:
            if (firstValue instanceof Comparable) {
                return cb.greaterThan((Path<Comparable>) path, (Comparable) firstValue);
            }
            break;

        case GE:
            if (firstValue instanceof Comparable) {
                return cb.greaterThanOrEqualTo((Path<Comparable>) path, (Comparable) firstValue);
            }
            break;

        case LT:
            if (firstValue instanceof Comparable) {
                return cb.lessThan((Path<Comparable>) path, (Comparable) firstValue);
            }
            break;

        case LE:
            if (firstValue instanceof Comparable) {
                return cb.lessThanOrEqualTo((Path<Comparable>) path, (Comparable) firstValue);
            }
            break;

        case CONTAINS:
            if (firstValue instanceof String) {
                String value = ((String) firstValue).toLowerCase();
                return cb.like(cb.lower((Path<String>) path), "%" + value + "%");
            }
            break;

        case STARTS_WITH:
            if (firstValue instanceof String) {
                String value = ((String) firstValue).toLowerCase();
                return cb.like(cb.lower((Path<String>) path), value + "%");
            }
            break;

        case ENDS_WITH:
            if (firstValue instanceof String) {
                String value = ((String) firstValue).toLowerCase();
                return cb.like(cb.lower((Path<String>) path), "%" + value);
            }
            break;

        case NOT_CONTAINS:
            if (firstValue instanceof String) {
                String value = ((String) firstValue).toLowerCase();
                return cb.not(cb.like(cb.lower((Path<String>) path), "%" + value + "%"));
            }
            break;
        }

        return null;
    }

    /**
     * Combina una lista de predicados con AND.
     *
     * @param predicates Lista de predicados a combinar
     * @param cb         CriteriaBuilder
     * @return Predicate combinado o null si la lista está vacía
     */
    protected static Predicate combineWithAnd(List<Predicate> predicates, CriteriaBuilder cb) {
        if (predicates.isEmpty()) {
            return null;
        }
        return cb.and(predicates.toArray(new Predicate[0]));
    }

    /**
     * Combina una lista de predicados con OR.
     *
     * @param predicates Lista de predicados a combinar
     * @param cb         CriteriaBuilder
     * @return Predicate combinado o null si la lista está vacía
     */
    protected static Predicate combineWithOr(List<Predicate> predicates, CriteriaBuilder cb) {
        if (predicates.isEmpty()) {
            return null;
        }
        return cb.or(predicates.toArray(new Predicate[0]));
    }
}