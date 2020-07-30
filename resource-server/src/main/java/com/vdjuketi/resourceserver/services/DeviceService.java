package com.vdjuketi.resourceserver.services;

import com.vdjuketi.resourceserver.dto.Device;
import com.vdjuketi.resourceserver.exceptions.ResourceNotFoundException;
import com.vdjuketi.resourceserver.repositories.DeviceRepository;
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
public class DeviceService {

  private final DeviceRepository repository;
  private final FacilityRepository facilityRepository;

  @GetMapping("/devices")
  public Page<Device> getDevices(Pageable pageable) {
    return repository.findAll(pageable);
  }

  @GetMapping("/devices/{deviceId}")
  public Device getDeviceById(@PathVariable Long deviceId) {
    return repository.getOne(deviceId);
  }

  @PutMapping("/devices")
  public Device createDevice(@Validated @RequestBody Device device) {
    return repository.save(device);
  }

  @PutMapping("/devices/addFacilityToDevice")
  public Integer addFacilityToDevice(@RequestBody Map<String, Long> requestJson) {
    return facilityRepository
        .findById(requestJson.get("facilityId"))
        .map(facility -> repository.addFacilityToDevice(facility, requestJson.get("deviceId")))
        .orElseThrow(() -> new ResourceNotFoundException("Facility not found"));
  }

  @DeleteMapping("/devices/{deviceId}")
  public ResponseEntity<?> deleteDevice(@PathVariable Long deviceId) {
    return repository
        .findById(deviceId)
        .map(
            device -> {
              repository.delete(device);
              return ResponseEntity.ok().build();
            })
        .orElseThrow(() -> new ResourceNotFoundException("Device not found with id " + deviceId));
  }
}
