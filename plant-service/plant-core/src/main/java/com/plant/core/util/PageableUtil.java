package com.plant.core.util;

import com.plant.common.enums.Direction;
import com.plant.common.model.EntitySort;
import com.plant.common.model.PageRequestBO;
import com.plant.data.entity.SkipLimitPageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.JpaSort;

import static java.util.Objects.isNull;

/**
 * Pageable Util.
 *
 * @author Anatolii Hamza
 */
public final class PageableUtil {

    private PageableUtil() {
    }

    public static Pageable preparePageable(PageRequestBO pageRequestBO) {
        return preparePageable(
                pageRequestBO.skip(),
                pageRequestBO.limit(),
                pageRequestBO.sort().getField(),
                pageRequestBO.direction().name());
    }

    public static Pageable preparePageable(Integer skip, Integer limit, String sortingType, String sortingOrder) {
        Sort sort;
        if (isNull(sortingOrder) && "createdAt".equalsIgnoreCase(sortingType)) {
            sort = Sort.by(Sort.Direction.DESC, sortingType);
        } else if (isNull(sortingOrder)) {
            sort = Sort.by(Sort.Direction.ASC, sortingType);
        } else {
            sort = Sort.by("asc".equalsIgnoreCase(sortingOrder) ? Sort.Direction.ASC : Sort.Direction.DESC, sortingType);
        }
        return new SkipLimitPageRequest(skip, limit, sort);
    }

    public static Pageable preparePageableForProjection(PageRequestBO pageRequestBO) {
        return preparePageableForProjection(
                pageRequestBO.skip(),
                pageRequestBO.limit(),
                pageRequestBO.sort().getField(),
                pageRequestBO.direction().name());
    }

    public static Pageable preparePageableForProjection(Integer skip,
                                                        Integer limit,
                                                        String sortingType,
                                                        String sortingOrder) {

        return new SkipLimitPageRequest(skip, limit, buildSortUnsafe(sortingType, sortingOrder));
    }

    public static Sort buildSortUnsafe(EntitySort sort, Direction direction) {
        return buildSortUnsafe(sort.getField(), direction.name());
    }

    private static Sort buildSortUnsafe(String sortingType, String sortingOrder) {
        Sort sort;
        if (!isNull(sortingOrder)) {
            sort = JpaSort.unsafe(
                    "asc".equalsIgnoreCase(sortingOrder) ? Sort.Direction.ASC : Sort.Direction.DESC,
                    sortingType);
        } else if ("createdAt".equalsIgnoreCase(sortingType)) {
            sort = JpaSort.unsafe(Sort.Direction.DESC, sortingType);
        } else {
            sort = JpaSort.unsafe(Sort.Direction.ASC, sortingType);
        }
        return sort;
    }
}
