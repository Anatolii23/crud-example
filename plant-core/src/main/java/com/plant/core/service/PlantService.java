package com.plant.core.service;

import com.plant.common.model.PageContainer;
import com.plant.common.model.PageRequestBO;
import com.plant.core.exception.PlantNameExistsException;
import com.plant.core.exception.PlantNotFoundException;
import com.plant.core.model.PlantBO;
import com.plant.data.aggregator.PlantDataAggregator;
import com.plant.data.entity.plant.Plant;
import com.plant.data.repository.PlantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static com.plant.common.utils.DateTimeUtil.nowLocalDateTime;
import static com.plant.common.utils.ExceptionThrower.throwsIf;
import static com.plant.core.converter.PlantConverter.toPlant;
import static com.plant.core.converter.PlantConverter.toPlantBO;
import static com.plant.core.converter.PlantConverter.toPlantBOs;
import static com.plant.core.util.PageableUtil.preparePageable;
import static java.util.Objects.isNull;

/**
 * Plant Service.
 *
 * @author Anatolii Hamza
 */
@Service
public class PlantService {

    @Autowired
    private PlantRepository plantRepository;

    @Autowired
    private PlantDataAggregator aggregator;

    /**
     * Get plant by id.
     *
     * @param plantId {@link UUID}
     * @return {@link PlantBO}
     * @throws PlantNotFoundException when plant not found
     */
    @Transactional(readOnly = true)
    public PlantBO getPlant(UUID plantId) throws PlantNotFoundException {
        return toPlantBO(getPlantEntity(plantId));
    }

    /**
     * Create plant entity.
     *
     * @param plantBO {@link PlantBO}
     * @return {@link PlantBO}
     * @throws PlantNameExistsException when plant name already exist
     */
    @Transactional
    public PlantBO createPlant(PlantBO plantBO) throws PlantNameExistsException {
        throwsIf(isNameExists(plantBO.getName()), PlantNameExistsException::new);
        var plant = toPlant(plantBO);
        return toPlantBO(plantRepository.save(plant));
    }

    /**
     * Update plant entity.
     *
     * @param plantBO {@link PlantBO}
     * @return {@link PlantBO}
     * @throws PlantNotFoundException   when plant not found
     * @throws PlantNameExistsException when plant not found
     */
    @Transactional
    public PlantBO updatePlant(PlantBO plantBO) throws PlantNameExistsException, PlantNotFoundException {
        throwsIf(!isExists(plantBO.getPlantId()), PlantNotFoundException::new);
        throwsIf(isNameExists(plantBO.getName()), PlantNameExistsException::new);
        var plant = toPlant(plantBO);
        return toPlantBO(plantRepository.save(plant));
    }

    /**
     * Delete plant entity.
     *
     * @param plantId {@link UUID}
     * @throws PlantNotFoundException   when plant not found
     */
    @Transactional
    public void deletePlant(UUID plantId) throws PlantNotFoundException {
        throwsIf(!isExists(plantId), PlantNotFoundException::new);
        plantRepository.removeById(plantId, nowLocalDateTime());
    }

    /**
     * Get Plants by search.
     *
     * @param search        {@link String}
     * @param pageRequestBO {@link PageRequestBO}
     * @return {@link PageContainer} of {@link PlantBO} objects
     */
    @Transactional(readOnly = true)
    public PageContainer<PlantBO> getPlants(@Nullable String search, PageRequestBO pageRequestBO) {
        search = isNull(search) ? "" : search.toLowerCase();
        var plantPage = plantRepository.findPlants(search, preparePageable(pageRequestBO));
        var plants = plantPage.getContent();
        aggregator.enrich(plants, PlantDataAggregator.PlantBuilder.getInstance().withContinents());
        return new PageContainer<>(toPlantBOs(plants), plantPage.getTotalElements());
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
     * Check if it exists by name.
     *
     * @param name {@link String}
     * @return true if exist
     */
    private boolean isNameExists(String name) {
        return plantRepository.existsByNameIgnoreCase(name);
    }

    /**
     * Check if it exists by id.
     *
     * @param plantId {@link UUID}
     * @return true if exist
     */
    private boolean isExists(UUID plantId) {
        return plantRepository.existsById(plantId);
    }
}
