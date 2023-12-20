package com.plant.core.service;

import com.plant.core.converter.PlantConverter;
import com.plant.core.exception.PlantNameExistsException;
import com.plant.core.exception.PlantNotFoundException;
import com.plant.core.model.PlantBO;
import com.plant.data.entity.plant.Plant;
import com.plant.data.repository.PlantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.plant.common.utils.ExceptionThrower.throwsIf;

/**
 * Plant Service.
 *
 * @author Anatolii Hamza
 */
@Service
public class PlantService {

    @Autowired
    private PlantRepository plantRepository;

    /**
     * Get plant by id.
     *
     * @param plantId {@link UUID}
     * @return {@link PlantBO}
     * @throws PlantNotFoundException when plant not found
     */
    public PlantBO getPlant(UUID plantId) throws PlantNotFoundException {
        return PlantConverter.toPlantBO(getPlantEntity(plantId));
    }

    /**
     * Create plant entity.
     *
     * @param plantBO {@link PlantBO}
     * @return {@link PlantBO}
     * @throws PlantNameExistsException when plant not found
     */
    public PlantBO createPlant(PlantBO plantBO) throws PlantNameExistsException {
        throwsIf(isNameExists(plantBO.getName()), PlantNameExistsException::new);
        var plant = PlantConverter.toPlant(plantBO);
        return PlantConverter.toPlantBO(plantRepository.save(plant));
    }

    /* Private methods */

    /**
     * Gets plant entity.
     *
     * @param plantId {@link UUID}
     * @return {@link Plant}
     * @throws PlantNotFoundException when plant not found
     */
    private Plant getPlantEntity(UUID plantId) throws PlantNotFoundException {
        return plantRepository.findById(plantId).orElseThrow(PlantNotFoundException::new);
    }

    /**
     * Check for exist by name.
     *
     * @param name {@link String}
     * @return true if exist
     */
    private boolean isNameExists(String name) {
        return plantRepository.existsByNameIgnoreCase(name);
    }
}
