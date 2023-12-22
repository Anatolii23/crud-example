package com.plant.common.enums;

import com.plant.common.model.EntitySort;

/**
 * Plant sort enum.
 *
 * @author Anatolii Hamza
 */
public enum PlantSort implements EntitySort {

    NAME("name"),
    CREATED_AT("createdAt"),
    SPECIES("species"),
    FAMILY("family"),
    GENUS("genus");

    private final String field;

    PlantSort(String field) {
        this.field = field;
    }

    public String getField() {
        return field;
    }
}
