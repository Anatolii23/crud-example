package com.plant.data.repository;

import com.plant.data.entity.plant.Plant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
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
}
