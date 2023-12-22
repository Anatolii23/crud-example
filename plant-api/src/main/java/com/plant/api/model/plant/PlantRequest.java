package com.plant.api.model.plant;

import com.plant.common.enums.Continent;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

/**
 * Plant Request.
 *
 * @author Anatolii Hamza
 */
public class PlantRequest {

    @Size(min = 1, max = 100)
    @NotBlank
    private String name;

    @Size(min = 1, max = 100)
    @NotBlank
    private String species;

    @Size(min = 1, max = 2000)
    @NotBlank
    private String description;

    @Size(min = 1, max = 100)
    @NotBlank
    private String family;

    @Size(min = 1, max = 100)
    @NotBlank
    private String genus;

    @Size(min = 1, max = 7)
    private final Set<Continent> continents = new HashSet<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getGenus() {
        return genus;
    }

    public void setGenus(String genus) {
        this.genus = genus;
    }

    public Set<Continent> getContinents() {
        return continents;
    }
}
