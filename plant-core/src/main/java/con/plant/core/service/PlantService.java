package con.plant.core.service;

import com.plant.data.entity.plant.Plant;
import com.plant.data.repository.PlantRepository;
import con.plant.core.exception.PlantNameExistsException;
import con.plant.core.exception.PlantNotFoundException;
import con.plant.core.model.PlantBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.plant.common.utils.ExceptionThrower.throwsIf;
import static con.plant.core.converter.PlantConverter.toPlant;
import static con.plant.core.converter.PlantConverter.toPlantBO;

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
        return toPlantBO(getPlantEntity(plantId));
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
        var plant = toPlant(plantBO);
        return toPlantBO(plantRepository.save(plant));
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
