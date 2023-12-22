package com.plant.common.model;

/**
 * Pagination params.
 *
 * @author Anatolii Hamza
 */
public class PaginationParams {

    private Integer skip = 0;
    private Integer limit = 50;

    public Integer getSkip() {
        return skip;
    }

    public void setSkip(Integer skip) {
        this.skip = skip;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
