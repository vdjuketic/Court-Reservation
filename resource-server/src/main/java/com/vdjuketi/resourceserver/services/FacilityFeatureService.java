package com.vdjuketi.resourceserver.services;

import com.vdjuketi.resourceserver.dto.FacilityFeature;
import com.vdjuketi.resourceserver.exceptions.ResourceNotFoundException;
import com.vdjuketi.resourceserver.repositories.FacilityFeatureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class FacilityFeatureService {

  private final FacilityFeatureRepository repository;

  @GetMapping("/facilityFeatures")
  public Page<FacilityFeature> getFacilityFeatures(Pageable pageable) {
    return repository.findAll(pageable);
  }

  @GetMapping("/facilityFeatures/{facilityFeatureId}")
  public FacilityFeature getFacilityFeatureById(@PathVariable Long facilityFeatureId) {
    return repository.getOne(facilityFeatureId);
  }

  @PutMapping("/facilityFeatures")
  public FacilityFeature createFacilityFeature(
      @Validated @RequestBody FacilityFeature facilityFeature) {
    return repository.save(facilityFeature);
  }

  @DeleteMapping("/facilityFeatures/{facilityFeatureId}")
  public ResponseEntity<?> deleteDevice(@PathVariable Long facilityFeatureId) {
    return repository
        .findById(facilityFeatureId)
        .map(
            device -> {
              repository.delete(device);
              return ResponseEntity.ok().build();
            })
        .orElseThrow(
            () ->
                new ResourceNotFoundException(
                    "Facility feature not found with id " + facilityFeatureId));
  }
}
