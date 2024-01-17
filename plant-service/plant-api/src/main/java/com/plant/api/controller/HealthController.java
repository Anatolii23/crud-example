package com.plant.api.controller;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Health Controller.
 *
 * @author Anatolii Hamza
 */
@RestController
@RequestMapping("/health")
public class HealthController {

    @Hidden
    @GetMapping
    public ResponseEntity<?> health() {
        return ResponseEntity.ok().build();
    }
}