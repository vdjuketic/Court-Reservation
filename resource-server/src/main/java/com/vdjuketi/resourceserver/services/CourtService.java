package com.vdjuketi.resourceserver.services;

import com.vdjuketi.resourceserver.dto.Court;
import com.vdjuketi.resourceserver.exceptions.ResourceNotFoundException;
import com.vdjuketi.resourceserver.repositories.CourtRepository;
import com.vdjuketi.resourceserver.repositories.FacilityRepository;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CourtService {

  private final CourtRepository courtRepository;
  private final FacilityRepository facilityRepository;

  @GetMapping("/courts")
  public Page<Court> getCourts(Pageable pageable) {
    return courtRepository.findAll(pageable);
  }

  @PutMapping("/courts")
  public Court createCourt(@Validated @RequestBody Court court) {
    return courtRepository.save(court);
  }

  @DeleteMapping("/courts/{courtId}")
  public ResponseEntity<?> deleteCourt(@PathVariable Long courtId) {
    return courtRepository
        .findById(courtId)
        .map(
            court -> {
              courtRepository.delete(court);
              return ResponseEntity.ok().build();
            })
        .orElseThrow(() -> new ResourceNotFoundException("Court not found with id " + courtId));
  }

  @GetMapping("/courts/{courtId}")
  public Court getCourtById(@PathVariable Long courtId) {
    return courtRepository.getOne(courtId);
  }

  @PutMapping("/courts/addFacilityToCourt")
  public Integer addFacilityToCourt(@RequestBody Map<String, Long> requestJson) {
    return facilityRepository
        .findById(requestJson.get("facilityId"))
        .map(facility -> courtRepository.addFacilityToCourt(facility, requestJson.get("courtId")))
        .orElseThrow(
            () ->
                new ResourceNotFoundException(
                    "Facility with id " + requestJson.get("facilityId") + " not found"));
  }
}
