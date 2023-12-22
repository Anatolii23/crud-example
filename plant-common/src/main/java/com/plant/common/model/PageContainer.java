package com.plant.common.model;

import java.util.List;

/**
 * Page Container.
 *
 * @param <T>        generic value
 * @param content    {@link List} of content
 * @param totalCount total number of content
 * @author Anatolii Hamza
 */
public record PageContainer<T>(List<T> content, long totalCount) {

}