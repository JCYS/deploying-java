
/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/commons/models/FilterOperation.java.p.vm
 */
package com.copeinca.apicopeincaprov.commons.models;

import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
* Clase genérica para operaciones de filtro dinámico.
* Contiene un operador y una lista de valores tipados.
*
* @param <T> Tipo de datos para los valores del filtro
    */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class FilterOperation<T> implements Serializable {

    private OperatorEnum operator;
    private List<T> values;

}