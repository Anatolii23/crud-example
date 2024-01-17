package com.plant.api.util;

import com.plant.common.enums.Direction;
import com.plant.common.model.EntitySort;
import com.plant.common.model.PageRequestBO;
import com.plant.common.model.PaginationParams;
import org.springframework.http.HttpHeaders;

/**
 * Resources Util.
 *
 * @author Anatolii Hamza
 */
public final class ResourcesUtil {

    public static final String DEFAULT_SORT = "CREATED_AT";
    public static final String DEFAULT_DIRECTION = "DESC";

    private ResourcesUtil() {
    }

    public static HttpHeaders getListHeaders(long count, int skip, int limit) {
        var httpHeaders = new HttpHeaders();
        httpHeaders.add("next", ((Boolean) (count > skip + limit)).toString());
        httpHeaders.add("x-total-count", String.valueOf(count));
        return httpHeaders;
    }

    public static PageRequestBO toPageRequestBO(PaginationParams params, Direction direction, EntitySort sort) {
        return new PageRequestBO(params.getSkip(), params.getLimit(), direction, sort);
    }
}