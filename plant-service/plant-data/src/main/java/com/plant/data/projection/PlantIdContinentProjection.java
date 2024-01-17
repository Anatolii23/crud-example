package com.plant.data.projection;

import com.plant.common.enums.Continent;

import java.util.UUID;

/**
 * Plant Id Continent Projection.
 *
 * @author Anatolii Hamza
 */
public interface PlantIdContinentProjection {

    UUID getPlantId();

    Continent getContinent();
}
