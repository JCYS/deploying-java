
/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/global/dtos/response/PagedResult.java.p.vm
 */
package com.copeinca.apicopeincaprov.global.dtos.response;

import lombok.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PagedResult<T> implements Serializable {

    private Integer count; // Total de elementos
    private Integer offset; // Offset actual (si usas offset)
    private Integer limit; // Tamaño de página
    private Integer page; // Página actual (opcional)
    private Integer totalPages; // Total de páginas
    private Boolean hasNext; // ¿Hay más resultados?
    private Boolean hasPrevious; // ¿Hay anteriores?

    private List<T> result; // Resultado de la página actual

    public <T> PagedResult<T> fromPage(Page<T> page) {

        return PagedResult.<T> builder().count((int) page.getTotalElements()).limit(page.getSize())
                .offset(page.getPageable().getOffset() != Pageable.unpaged().getOffset() ? (int) page.getPageable().getOffset()
                        : page.getNumber() * page.getSize())
                .page(page.getNumber()).totalPages(page.getTotalPages()).hasNext(page.hasNext()).hasPrevious(page.hasPrevious()).result(page.getContent())
                .build();

    }

    public Integer getRows() {
        return Optional.ofNullable(result).map(Collection::size).orElse(0);
    }

    public Integer getTotalPages() {
        return limit != null && limit > 0 ? (int) Math.ceil((double) count / limit) : 1;
    }

    public Integer getCurrentPage() {
        if (limit != null && limit > 0 && offset != null) {
            return (offset / limit) + 1;
        }
        return 1; // Página predeterminada si no se proporcionan offset/limit
    }

    public boolean hasNextPage() {
        return getCurrentPage() < getTotalPages();
    }

    public boolean hasPreviousPage() {
        return getCurrentPage() > 1;
    }
}
