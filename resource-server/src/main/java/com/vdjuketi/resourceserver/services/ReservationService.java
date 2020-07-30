package com.vdjuketi.resourceserver.services;

import com.vdjuketi.resourceserver.dto.Reservation;
import com.vdjuketi.resourceserver.exceptions.ResourceNotFoundException;
import com.vdjuketi.resourceserver.repositories.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ReservationService {

  private final ReservationRepository repository;

  @GetMapping("/reservations")
  public Page<Reservation> getReservations(Pageable pageable) {
    return repository.findAll(pageable);
  }

  @GetMapping("/reservations/{reservationId}")
  public Reservation getReservationById(@PathVariable Long reservationId) {
    return repository.getOne(reservationId);
  }

  @PutMapping("/reservations")
  public Reservation createReservation(@Validated @RequestBody Reservation reservation) {
    return repository.save(reservation);
  }

  @DeleteMapping("/reservations/{reservationId}")
  public ResponseEntity<?> deleteDevice(@PathVariable Long reservationId) {
    return repository
        .findById(reservationId)
        .map(
            device -> {
              repository.delete(device);
              return ResponseEntity.ok().build();
            })
        .orElseThrow(
            () ->
                new ResourceNotFoundException(
                    "Facility feature not found with id " + reservationId));
  }
}
