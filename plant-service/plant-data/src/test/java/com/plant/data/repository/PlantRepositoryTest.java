package com.plant.data.repository;

import com.github.database.rider.core.api.dataset.DataSet;
import com.plant.common.enums.Continent;
import com.plant.common.model.PaginationParams;
import com.plant.data.entity.SkipLimitPageRequest;
import config.DbTestConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.UUID;

import static com.plant.common.utils.DateTimeUtil.nowLocalDateTime;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DbTestConfiguration
@DataSet(value = {"plant_dataset.yml"}, useSequenceFiltering = false, disableConstraints = true)
class PlantRepositoryTest {

    private static final UUID PLANT_ID = UUID.fromString("b8f832d5-ff30-44fa-8038-5613724bbcc1");
    private static final UUID PLANT_ID_1 = UUID.fromString("b8f832d5-ff30-44fa-8038-5613724bbcc2");
    private static final UUID BAD_UUID = UUID.fromString("4d9310f3-0952-4cf1-a03e-8c729fa42699");
    private static final String PLANT_NAME = "name";
    private static final String PLANT_NAME_1 = "name1";
    private static final String PLANT_FAMILY_1 = "family1";
    private static final String PLANT_SPECIES_1 = "species1";
    private static final String PLANT_GENUS_1 = "genus1";
    private static final String PLANT_CONTINENT = "asia";
    private static final String BAD_NAME = "bad name";
    private static final int ZERO = 0;
    private static final int ONE = 1;
    private static final int TWO = 2;
    private static final int THREE = 3;
    private static final int FIVE = 5;
    private static final int SIX = 6;
    private static final int SEVEN = 7;
    private static final PaginationParams PAGINATION_PARAMS = new PaginationParams();
    private static final SkipLimitPageRequest SKIP_LIMIT_PAGE_REQUEST = new SkipLimitPageRequest(
            PAGINATION_PARAMS.getSkip(),
            PAGINATION_PARAMS.getLimit(),
            Sort.by(Sort.Direction.valueOf("DESC"), "createdAt"));

    @Autowired
    private PlantRepository plantRepository;

    @Test
    public void testExistsByNameIgnoreCase() {
        assertTrue(plantRepository.existsByNameIgnoreCase(PLANT_NAME));
        assertFalse(plantRepository.existsByNameIgnoreCase(BAD_NAME));
    }

    @Test
    @Transactional
    public void testRemoveById() {
        assertTrue(plantRepository.existsById(PLANT_ID));
        plantRepository.removeById(PLANT_ID, nowLocalDateTime());
        assertFalse(plantRepository.existsById(PLANT_ID));
    }

    @Test
    public void testFindPlants() {
        var plants = plantRepository.findPlants(PLANT_NAME, SKIP_LIMIT_PAGE_REQUEST);
        assertNotNull(plants);
        var plantsList = plants.getContent();
        assertEquals(SEVEN, plants.getTotalElements());
        assertEquals(PLANT_ID, plantsList.get(SIX).getPlantId());
        assertEquals(PLANT_NAME, plantsList.get(SIX).getName());
        assertEquals(PLANT_ID_1, plantsList.get(FIVE).getPlantId());
        assertEquals(PLANT_NAME_1, plantsList.get(FIVE).getName());
    }

    @Test
    public void testFindPlantsSearchByName() {
        var plants = plantRepository.findPlants(PLANT_NAME_1, SKIP_LIMIT_PAGE_REQUEST);
        assertNotNull(plants);
        var plantsList = plants.getContent();
        assertEquals(ONE, plants.getTotalElements());
        assertEquals(PLANT_ID_1, plantsList.get(ZERO).getPlantId());
        assertEquals(PLANT_NAME_1, plantsList.get(ZERO).getName());
    }

    @Test
    public void testFindPlantsSearchByFamily() {
        var plants = plantRepository.findPlants(PLANT_FAMILY_1, SKIP_LIMIT_PAGE_REQUEST);
        assertNotNull(plants);
        var plantsList = plants.getContent();
        assertEquals(ONE, plants.getTotalElements());
        assertEquals(PLANT_ID_1, plantsList.get(ZERO).getPlantId());
        assertEquals(PLANT_NAME_1, plantsList.get(ZERO).getName());
        assertEquals(PLANT_FAMILY_1, plantsList.get(ZERO).getFamily());
    }

    @Test
    public void testFindPlantsSearchByGenus() {
        var plants = plantRepository.findPlants(PLANT_GENUS_1, SKIP_LIMIT_PAGE_REQUEST);
        assertNotNull(plants);
        var plantsList = plants.getContent();
        assertEquals(ONE, plants.getTotalElements());
        assertEquals(PLANT_ID_1, plantsList.get(ZERO).getPlantId());
        assertEquals(PLANT_NAME_1, plantsList.get(ZERO).getName());
        assertEquals(PLANT_GENUS_1, plantsList.get(ZERO).getGenus());
    }

    @Test
    public void testFindPlantsSearchBySpecies() {
        var plants = plantRepository.findPlants(PLANT_SPECIES_1, SKIP_LIMIT_PAGE_REQUEST);
        assertNotNull(plants);
        var plantsList = plants.getContent();
        assertEquals(ONE, plants.getTotalElements());
        assertEquals(PLANT_ID_1, plantsList.get(ZERO).getPlantId());
        assertEquals(PLANT_NAME_1, plantsList.get(ZERO).getName());
        assertEquals(PLANT_SPECIES_1, plantsList.get(ZERO).getSpecies());
    }

    @Test
    @Transactional
    public void testFindPlantsSearchByContinent() {
        var plants = plantRepository.findPlants(PLANT_CONTINENT, SKIP_LIMIT_PAGE_REQUEST);
        assertNotNull(plants);
        var plantsList = plants.getContent();
        assertEquals(THREE, plants.getTotalElements());
        assertEquals(PLANT_ID, plantsList.get(TWO).getPlantId());
        assertEquals(PLANT_NAME, plantsList.get(TWO).getName());
        assertTrue(plantsList.get(TWO).getContinents().contains(Continent.ASIA));
        assertTrue(plantsList.get(ONE).getContinents().contains(Continent.ASIA));
        assertTrue(plantsList.get(ZERO).getContinents().contains(Continent.ASIA));
    }

    @Test
    public void testGetAllContinentsByPlantId() {
        var projections = plantRepository.getAllContinentsByPlantId(Set.of(PLANT_ID, PLANT_ID_1));
        assertNotNull(projections);
        assertEquals(SIX, projections.size());
    }

    @Test
    public void testGetAllContinentsByBadId() {
        var projections = plantRepository.getAllContinentsByPlantId(Set.of(BAD_UUID));
        assertNotNull(projections);
        assertEquals(ZERO, projections.size());
    }
}