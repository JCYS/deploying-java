
/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/commons/utils/dto/PagingUtil.java.p.vm
 */
package com.copeinca.apicopeincaprov.commons.utils.dto;

import com.copeinca.apicopeincaprov.global.dtos.input.SortField;
import com.copeinca.apicopeincaprov.global.dtos.response.PagedRequest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public class PagingUtil {

    public static Pageable toPageable(PagedRequest<?> pagedRequest) {

        Integer page = pagedRequest.getPage();
        Integer offset = pagedRequest.getOffset();
        Integer limit = pagedRequest.getLimit();
        List<SortField> sortFields = pagedRequest.getSort();

        // Determina el número de página
        int pageNumber = 0;
        if (page != null) {
            pageNumber = page;
        } else if (offset != null && limit != null && limit > 0) {
            pageNumber = offset / limit;
        }

        // Determina el tamaño de página
        int pageSize = (limit != null && limit > 0) ? limit : 20;

        // Construye el objeto Sort si corresponde
        Sort sort = Sort.unsorted();

        if (sortFields != null && !sortFields.isEmpty()) {

            List<Sort.Order> orders = sortFields.stream()
                    .map(s -> new Sort.Order(s.getDirection() != null ? Sort.Direction.valueOf(s.getDirection().name()) : Sort.Direction.ASC, s.getField()))
                    .toList();
            sort = Sort.by(orders);

        }

        return PageRequest.of(pageNumber, pageSize, sort);

    }

}
