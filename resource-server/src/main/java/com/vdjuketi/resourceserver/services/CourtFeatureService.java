package com.vdjuketi.resourceserver.services;

import com.vdjuketi.resourceserver.dto.CourtFeature;
import com.vdjuketi.resourceserver.exceptions.ResourceNotFoundException;
import com.vdjuketi.resourceserver.repositories.CourtFeatureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CourtFeatureService {

  private final CourtFeatureRepository repository;

  @GetMapping("/courtFeatures")
  public Page<CourtFeature> getCourtFeatures(Pageable pageable) {
    return repository.findAll(pageable);
  }

  @PutMapping("/courtFeatures")
  public CourtFeature createCourtFeature(@Validated @RequestBody CourtFeature courtFeature) {
    return repository.save(courtFeature);
  }

  @DeleteMapping("/courtFeatures/{courtFeatureId}")
  public ResponseEntity<?> deleteCourt(@PathVariable Long courtFeatureId) {
    return repository
        .findById(courtFeatureId)
        .map(
            courtFeature -> {
              repository.delete(courtFeature);
              return ResponseEntity.ok().build();
            })
        .orElseThrow(
            () ->
                new ResourceNotFoundException("CourtFeature not found with id " + courtFeatureId));
  }
}
