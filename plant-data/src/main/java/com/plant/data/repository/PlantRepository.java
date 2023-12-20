package com.plant.data.repository;

import com.plant.data.entity.plant.Plant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

/**
 * Plant Repository.
 *
 * @author Anatolii Hamza
 */
public interface PlantRepository extends JpaRepository<Plant, UUID> {

    boolean existsByNameIgnoreCase(@Param("name") String name);
}
