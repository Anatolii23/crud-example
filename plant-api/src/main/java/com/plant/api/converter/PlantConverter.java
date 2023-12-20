package com.plant.api.converter;

import com.plant.api.model.plant.PlantRequest;
import com.plant.api.model.plant.PlantResponse;
import com.plant.core.model.PlantBO;

/**
 * Plant Converter.
 *
 * @author Anatolii Hamza
 */
public final class PlantConverter {

    private PlantConverter() {
    }

    public static PlantResponse toPlantResponse(PlantBO plantBO) {
        var response = new PlantResponse();
        response.setDescription(plantBO.getDescription());
        response.setFamily(plantBO.getFamily());
        response.setGenus(plantBO.getGenus());
        response.setName(plantBO.getName());
        response.setPlantId(plantBO.getPlantId());
        response.setSpecies(plantBO.getSpecies());
        response.getContinents().addAll(plantBO.getContinents());
        response.setCreatedAt(plantBO.getCreatedAt());
        return response;
    }

    public static PlantBO toPlantBO(PlantRequest request) {
        var plantBO = new PlantBO();
        plantBO.setDescription(request.getDescription());
        plantBO.setFamily(request.getFamily());
        plantBO.setGenus(request.getGenus());
        plantBO.setName(request.getName());
        plantBO.setSpecies(request.getSpecies());
        plantBO.getContinents().addAll(request.getContinents());
        return plantBO;
    }
}
