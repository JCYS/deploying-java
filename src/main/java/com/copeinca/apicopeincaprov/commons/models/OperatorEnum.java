
/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/commons/models/OperatorEnum.java.p.vm
 */
package com.copeinca.apicopeincaprov.commons.models;

/**
* Enumeración de operadores para filtros dinámicos.
* Soporta operaciones de comparación, contenido de texto y verificación de nulos.
*/
public enum OperatorEnum {

    /** Igual a */
    EQ,

    /** No igual a */
    NE,

    /** Mayor que */
    GT,

    /** Mayor o igual que */
    GE,

    /** Menor que */
    LT,

    /** Menor o igual que */
    LE,

    /** Está en la lista */
    IN,

    /** No está en la lista */
    NOT_IN,

    /** Contiene el texto (solo String) */
    CONTAINS,

    /** Empieza con el texto (solo String) */
    STARTS_WITH,

    /** Termina con el texto (solo String) */
    ENDS_WITH,

    /** No contiene el texto (solo String) */
    NOT_CONTAINS,

    /** Es nulo */
    IS_NULL,

    /** No es nulo */
    IS_NOT_NULL,;

}
