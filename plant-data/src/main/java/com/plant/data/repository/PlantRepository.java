package com.plant.data.repository;

import com.plant.data.entity.Plant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Plant Repository.
 *
 * @author Anatolii Hamza
 */
public interface PlantRepository extends JpaRepository<Plant, UUID> {
}
