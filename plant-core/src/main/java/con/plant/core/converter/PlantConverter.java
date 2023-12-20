package con.plant.core.converter;

import com.plant.data.entity.plant.Plant;
import con.plant.core.model.PlantBO;

/**
 * Plant Converter.
 *
 * @author Anatolii Hamza
 */
public final class PlantConverter {

    private PlantConverter() {
    }

    public static PlantBO toPlantBO(Plant plant) {
        var plantBO = new PlantBO();
        plantBO.setDescription(plant.getDescription());
        plantBO.setFamily(plant.getFamily());
        plantBO.setGenus(plant.getGenus());
        plantBO.setName(plant.getName());
        plantBO.setPlantId(plant.getPlantId());
        plantBO.setSpecies(plant.getSpecies());
        plantBO.getContinents().addAll(plant.getContinents());
        plantBO.setCreatedAt(plant.getCreatedAt());
        return plantBO;
    }

    public static Plant toPlant(PlantBO plantBO) {
        var plant = new Plant();
        plant.setDescription(plantBO.getDescription());
        plant.setFamily(plantBO.getFamily());
        plant.setGenus(plantBO.getGenus());
        plant.setName(plantBO.getName());
        plant.setPlantId(plantBO.getPlantId());
        plant.setSpecies(plantBO.getSpecies());
        plant.getContinents().addAll(plantBO.getContinents());
        plant.setCreatedAt(plantBO.getCreatedAt());
        return plant;
    }
}
