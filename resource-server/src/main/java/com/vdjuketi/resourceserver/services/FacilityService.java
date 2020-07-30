package com.vdjuketi.resourceserver.services;

import com.vdjuketi.resourceserver.dto.Facility;
import com.vdjuketi.resourceserver.dto.enums.SportTypeEnum;
import com.vdjuketi.resourceserver.exceptions.ResourceNotFoundException;
import com.vdjuketi.resourceserver.repositories.FacilityRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class FacilityService {

  private final FacilityRepository repository;

  @GetMapping("/facilities")
  public Page<Facility> getFacilities(Pageable pageable) {
    return repository.findAll(pageable);
  }

  @PutMapping("/facilities")
  public Facility createFacility(@Validated @RequestBody Facility facility) {
    return repository.save(facility);
  }

  @DeleteMapping("/facilities/{facilityId}")
  public ResponseEntity<?> deleteFacility(@PathVariable Long facilityId) {
    return repository
        .findById(facilityId)
        .map(
            facility -> {
              repository.delete(facility);
              return ResponseEntity.ok().build();
            })
        .orElseThrow(
            () -> new ResourceNotFoundException("Facility not found with id " + facilityId));
  }

  @GetMapping("/facilities/findBySport")
  public List<Facility> findBySport(@RequestParam SportTypeEnum sportType) {
    return repository.findBySportType(sportType);
  }

  @GetMapping("/facilities/findById")
  public Optional<Facility> findById(@RequestParam Long facilityId) {
    return repository.findById(facilityId);
  }

  @GetMapping("/facilities/nearby")
  public List<Facility> findNearby(
      @RequestParam Double longitude, @RequestParam Double latitude, @RequestParam int radius) {
    return repository.findNearby(longitude, latitude, radius);
  }
}
