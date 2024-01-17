package com.plant.data.entity;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.Serializable;

/**
 * Skip Limit Page Request.
 *
 * @author Anatolii Hamza
 */
public class SkipLimitPageRequest implements Pageable, Serializable {

    private final int limit;
    private final int skip;
    private final Sort sort;

    /**
     * Creates a new {@link SkipLimitPageRequest} with sort parameters applied.
     *
     * @param skip  zero-based skip.
     * @param limit the size of the elements to be returned.
     * @param sort  can be {@literal null}.
     */
    public SkipLimitPageRequest(int skip, int limit, Sort sort) {
        this.limit = limit;
        this.skip = skip;
        this.sort = sort;
    }

    /**
     * Creates a new {@link SkipLimitPageRequest}.
     *
     * @param skip  zero-based skip.
     * @param limit the size of the elements to be returned.
     */
    public SkipLimitPageRequest(int skip, int limit) {
        this(skip, limit, Sort.by(Sort.Direction.DESC, "createdAt"));
    }

    @Override
    public int getPageNumber() {
        return skip / limit;
    }

    @Override
    public int getPageSize() {
        return limit;
    }

    @Override
    public long getOffset() {
        return skip;
    }

    @Override
    public Sort getSort() {
        return sort;
    }

    @Override
    public Pageable next() {
        return new SkipLimitPageRequest((int) getOffset() + getPageSize(), getPageSize(), getSort());
    }

    public SkipLimitPageRequest previous() {
        return hasPrevious() ?
                new SkipLimitPageRequest((int) getOffset() - getPageSize(), getPageSize(), getSort()) :
                this;
    }

    @Override
    public Pageable previousOrFirst() {
        return hasPrevious() ? previous() : first();
    }

    @Override
    public Pageable first() {
        return new SkipLimitPageRequest(0, getPageSize(), getSort());
    }

    @Override
    public Pageable withPage(int i) {
        return null;
    }

    @Override
    public boolean hasPrevious() {
        return skip > limit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SkipLimitPageRequest that = (SkipLimitPageRequest) o;

        if (limit != that.limit) {
            return false;
        }
        if (skip != that.skip) {
            return false;
        }
        return sort != null ? sort.equals(that.sort) : that.sort == null;
    }

    @Override
    public int hashCode() {
        int result = limit;
        result = 31 * result + skip;
        result = 31 * result + (sort != null ? sort.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SkipLimitPageRequest{" +
                "limit=" + limit +
                ", skip=" + skip +
                ", sort=" + sort +
                '}';
    }
}