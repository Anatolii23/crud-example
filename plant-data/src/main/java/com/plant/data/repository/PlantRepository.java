package com.plant.data.repository;

import com.plant.data.entity.plant.Plant;
import com.plant.data.projection.PlantIdContinentProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;
import java.util.UUID;

/**
 * Plant Repository.
 *
 * @author Anatolii Hamza
 */
public interface PlantRepository extends JpaRepository<Plant, UUID> {

    boolean existsByNameIgnoreCase(@Param("name") String name);

    @Modifying
    @Query("UPDATE Plant p SET p.removedAt = :now WHERE p.plantId = :plantId")
    void removeById(@Param("plantId") UUID plantId,
                    @Param("now") LocalDateTime now);

    @Query("""
            SELECT DISTINCT p FROM Plant p
            JOIN p.continents c
            WHERE (CAST(:search AS text) IS NULL
            OR LOWER (p.name) LIKE CONCAT('%', :search, '%')
            OR LOWER (p.species) LIKE CONCAT('%', :search, '%')
            OR LOWER (p.family) LIKE CONCAT('%', :search, '%')
            OR LOWER (p.genus) LIKE CONCAT('%', :search, '%')
            OR LOWER (c) LIKE CONCAT('%', :search, '%'))
            """)
    Page<Plant> findPlants(@Param("search") String search, Pageable preparePageable);

    @Query("SELECT p.plantId AS plantId, c AS continent FROM Plant p JOIN p.continents c WHERE p.plantId IN :plantIds")
    Collection<PlantIdContinentProjection> getAllContinentsByPlantId(@Param("plantIds") Set<UUID> plantIds);
}
