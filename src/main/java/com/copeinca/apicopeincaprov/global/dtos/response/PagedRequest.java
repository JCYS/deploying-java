
/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/global/dtos/response/PagedRequest.java.p.vm
 */
package com.copeinca.apicopeincaprov.global.dtos.response;

import com.copeinca.apicopeincaprov.global.dtos.input.SortField;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PagedRequest<T> implements Serializable {

    /**
     * Número de página (empezando en 0).
     * Si se usa, ignora offset.
     */
    private Integer page;

    /**
     * Tamaño de página. Default recomendado: 20.
     */
    @Builder.Default
    private Integer size = 20;

    /**
     * Offset manual. Si se envía, ignora page/size.
     */
    private Integer offset;

    private Integer limit;

    /**
     * Texto de búsqueda general (por código, descripción, etc.).
     */
    private String searchTerm;

    /**
     * Filtro estructurado por campos del modelo.
     */
    private T filter;

    /**
     * Si el frontend pide solo contar resultados, sin paginar.
     */
    @Builder.Default
    private Boolean onlyCount = false;

    private List<SortField> sort;

    // Métodos auxiliares (opcional):
    public boolean isPagingByPage() {
        return page != null && size != null;
    }

    public boolean isPagingByOffset() {
        return offset != null && size != null;
    }

}
