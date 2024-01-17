package com.plant.core.service;

import com.plant.common.enums.Continent;
import com.plant.core.exception.PlantNameExistsException;
import com.plant.core.exception.PlantNotFoundException;
import com.plant.core.model.PlantBO;
import com.plant.data.entity.plant.Plant;
import com.plant.data.repository.PlantRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * {@link PlantService} Test.
 *
 * @author Anatolii Hamza
 */
@ExtendWith(MockitoExtension.class)
class PlantServiceTest {

    private static final UUID PLANT_ID = UUID.fromString("908e723c-912c-11eb-a8b3-0242ac130007");
    private static final UUID BAD_ID = UUID.fromString("908e723c-912c-11eb-a8b3-0242ac130001");
    private static final String DESCRIPTION = "description";
    private static final String NAME = "name";
    private static final String FAMILY = "family";
    private static final String UPDATED_DESCRIPTION = "updated description";
    private static final String UPDATED_NAME = "updated name";
    private static final String UPDATED_FAMILY = "updated family";
    private static final String GENUS = "genus";
    private static final String SPECIES = "species";
    private static final Set<Continent> CONTINENTS = Set.of(Continent.ASIA, Continent.EUROPE, Continent.AUSTRALIA);

    @InjectMocks
    private PlantService plantService;

    @Mock
    private PlantRepository plantRepositoryMock;

    @Test
    void getPlantTest() throws PlantNotFoundException {

        var plant = preparePlant();

        when(plantRepositoryMock.findById(PLANT_ID)).thenReturn(Optional.of(plant));

        var result = plantService.getPlant(PLANT_ID);

        assertNotNull(result);
        assertEquals(PLANT_ID, result.getPlantId());
        assertEquals(NAME, result.getName());
        assertEquals(DESCRIPTION, result.getDescription());
        assertEquals(FAMILY, result.getFamily());
        assertEquals(GENUS, result.getGenus());
        assertEquals(SPECIES, result.getSpecies());
        assertNotNull(result.getContinents());
        assertTrue(result.getContinents().contains(Continent.ASIA));
        assertTrue(result.getContinents().contains(Continent.EUROPE));
        assertTrue(result.getContinents().contains(Continent.AUSTRALIA));
        assertFalse(result.getContinents().contains(Continent.NORTH_AMERICA));
    }

    @Test
    void getPlantNotFoundTest() {
        when(plantRepositoryMock.findById(BAD_ID)).thenReturn(Optional.empty());

        assertThrows(PlantNotFoundException.class, () -> plantService.getPlant(BAD_ID));
    }

    @Test
    void createPlantTest() throws PlantNameExistsException {

        var plantBO = preparePlantBO();
        var plant = preparePlant();

        when(plantRepositoryMock.save(any(Plant.class))).thenReturn(plant);

        var result = plantService.createPlant(plantBO);

        assertNotNull(result);
        assertEquals(PLANT_ID, result.getPlantId());
        assertEquals(NAME, result.getName());
        assertEquals(DESCRIPTION, result.getDescription());
        assertEquals(FAMILY, result.getFamily());
        assertEquals(GENUS, result.getGenus());
        assertEquals(SPECIES, result.getSpecies());
        assertNotNull(result.getContinents());
        assertTrue(result.getContinents().contains(Continent.ASIA));
        assertTrue(result.getContinents().contains(Continent.EUROPE));
        assertTrue(result.getContinents().contains(Continent.AUSTRALIA));
        assertFalse(result.getContinents().contains(Continent.NORTH_AMERICA));
    }

    @Test
    void createPlantExistTest() {
        when(plantRepositoryMock.existsByNameIgnoreCase(NAME)).thenReturn(true);

        assertThrows(PlantNameExistsException.class, () -> plantService.createPlant(preparePlantBO()));
    }

    @Test
    void updatePlantTest() throws PlantNameExistsException, PlantNotFoundException {

        var plantBO = preparePlantBO();
        var plant = preparePlant();
        plant.setDescription(UPDATED_DESCRIPTION);
        plant.setName(UPDATED_NAME);
        plant.setFamily(UPDATED_FAMILY);

        when(plantRepositoryMock.existsById(PLANT_ID)).thenReturn(true);
        when(plantRepositoryMock.save(any(Plant.class))).thenReturn(plant);

        var result = plantService.updatePlant(plantBO);

        assertNotNull(result);
        assertEquals(PLANT_ID, result.getPlantId());
        assertEquals(UPDATED_NAME, result.getName());
        assertEquals(UPDATED_DESCRIPTION, result.getDescription());
        assertEquals(UPDATED_FAMILY, result.getFamily());
        assertEquals(GENUS, result.getGenus());
        assertEquals(SPECIES, result.getSpecies());
        assertNotNull(result.getContinents());
        assertTrue(result.getContinents().contains(Continent.ASIA));
        assertTrue(result.getContinents().contains(Continent.EUROPE));
        assertTrue(result.getContinents().contains(Continent.AUSTRALIA));
        assertFalse(result.getContinents().contains(Continent.NORTH_AMERICA));
    }

    @Test
    void updatePlantNotFoundTest() {
        when(plantRepositoryMock.existsById(PLANT_ID)).thenReturn(false);

        assertThrows(PlantNotFoundException.class, () -> plantService.updatePlant(preparePlantBO()));
    }

    @Test
    void updatePlantExistsTest() {
        when(plantRepositoryMock.existsById(PLANT_ID)).thenReturn(true);
        when(plantRepositoryMock.existsByNameIgnoreCase(NAME)).thenReturn(true);

        assertThrows(PlantNameExistsException.class, () -> plantService.updatePlant(preparePlantBO()));
    }

    private Plant preparePlant() {
        var plant = new Plant();
        plant.setPlantId(PLANT_ID);
        plant.setDescription(DESCRIPTION);
        plant.setName(NAME);
        plant.setFamily(FAMILY);
        plant.setGenus(GENUS);
        plant.setSpecies(SPECIES);
        plant.setContinents(CONTINENTS);
        return plant;
    }

    private PlantBO preparePlantBO() {
        var plant = new PlantBO();
        plant.setPlantId(PLANT_ID);
        plant.setDescription(DESCRIPTION);
        plant.setName(NAME);
        plant.setFamily(FAMILY);
        plant.setGenus(GENUS);
        plant.setSpecies(SPECIES);
        plant.getContinents().addAll(CONTINENTS);
        return plant;
    }
}