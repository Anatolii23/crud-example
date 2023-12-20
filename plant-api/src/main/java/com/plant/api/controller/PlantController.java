package com.plant.api.controller;

import com.plant.api.model.plant.PlantRequest;
import com.plant.api.model.plant.PlantResponse;
import com.plant.common.model.ServiceException;
import com.plant.common.model.ServiceResponseBody;
import com.plant.core.service.PlantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

import static com.plant.api.converter.PlantConverter.toPlantBO;
import static com.plant.api.converter.PlantConverter.toPlantResponse;

/**
 * Plant Controller.
 *
 * @author Anatolii Hamza
 */
@RestController
@Validated
@RequestMapping("/plants")
public class PlantController {

    @Autowired
    private PlantService plantService;

    @Operation(summary = "${resources.plants.get.summary}",
            description = "${resources.plants.get.description}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "${resources.plants.get.response.ok}",
                    content = @Content(schema = @Schema(implementation = PlantResponse.class))),
            @ApiResponse(responseCode = "5xx", description = "${common-5xx-message}",
                    content = @Content(schema = @Schema(implementation = ServiceResponseBody.class)))
    })
    @GetMapping("/{plantId}")
    public PlantResponse get(@PathVariable UUID plantId) throws ServiceException {
        return toPlantResponse(plantService.getPlant(plantId));
    }

    @Operation(summary = "${resources.plants.create.summary}",
            description = "${resources.plants.create.description}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "${resources.plants.create.response.ok}",
                    content = @Content(schema = @Schema(implementation = PlantResponse.class))),
            @ApiResponse(responseCode = "5xx", description = "${common-5xx-message}",
                    content = @Content(schema = @Schema(implementation = ServiceResponseBody.class)))
    })
    @PostMapping
    public PlantResponse create(@RequestBody @Valid PlantRequest request) throws ServiceException {
        return toPlantResponse(plantService.createPlant(toPlantBO(request)));
    }

    @Operation(summary = "${resources.plants.update.summary}",
            description = "${resources.plants.update.description}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "${resources.plants.update.response.ok}",
                    content = @Content(schema = @Schema(implementation = PlantResponse.class))),
            @ApiResponse(responseCode = "5xx", description = "${common-5xx-message}",
                    content = @Content(schema = @Schema(implementation = ServiceResponseBody.class)))
    })
    @PutMapping("/plantId")
    public PlantResponse update(@PathVariable UUID plantId,
                                @RequestBody @Valid PlantRequest request)
            throws ServiceException {

        return toPlantResponse(plantService.updatePlant(toPlantBO(request, plantId)));
    }
}
