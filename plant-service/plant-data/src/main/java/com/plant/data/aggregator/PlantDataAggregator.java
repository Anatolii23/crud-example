package com.plant.data.aggregator;

import com.plant.common.enums.EntityAggregation;
import com.plant.data.entity.plant.Plant;
import com.plant.data.projection.PlantIdContinentProjection;
import com.plant.data.repository.PlantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static com.plant.common.enums.EntityAggregation.CONTINENTS;
import static com.plant.common.enums.EntityAggregation.PLANT;
import static java.util.Collections.emptySet;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toSet;

/**
 * Plant Data Aggregator.
 *
 * @author Anatolii Hamza
 */
@Component
public class PlantDataAggregator {

    @Autowired
    private PlantRepository plantRepository;

    public void enrich(Collection<Plant> plants, PlantBuilder builder) {
        if (!plants.isEmpty()) {
            var plantIds = plants.stream().map(Plant::getPlantId).collect(toSet());
            if (builder.enums.contains(CONTINENTS)) {
                var continentMap = plantRepository.getAllContinentsByPlantId(plantIds).stream()
                        .collect(groupingBy(PlantIdContinentProjection::getPlantId,
                                mapping(PlantIdContinentProjection::getContinent, toSet())));
                plants.forEach(plant -> plant.setContinents(continentMap.getOrDefault(plant.getPlantId(), emptySet())));
            }
        }
    }

    /**
     * Plant builder.
     */
    public static final class PlantBuilder {

        private final Set<EntityAggregation> enums = new HashSet<>();

        private PlantBuilder() {
        }

        public static PlantBuilder getInstance() {
            var builder = new PlantBuilder();
            builder.enums.add(PLANT);
            return builder;
        }

        public PlantBuilder withContinents() {
            enums.add(CONTINENTS);
            return this;
        }
    }
}
