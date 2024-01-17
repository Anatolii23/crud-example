package com.plant.common.model;

import com.plant.common.enums.Direction;

/**
 * Page Request BO.
 *
 * @param sort      sort param
 * @param limit     limit param
 * @param direction direction param
 * @param skip      skip param
 * @author Anatolii Hamza
 */
public record PageRequestBO(int skip, int limit, Direction direction, EntitySort sort) {
}
