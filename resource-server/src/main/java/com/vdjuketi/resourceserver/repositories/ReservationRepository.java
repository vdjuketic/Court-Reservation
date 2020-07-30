package com.vdjuketi.resourceserver.repositories;

import com.vdjuketi.resourceserver.dto.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {}
