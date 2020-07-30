package com.vdjuketi.resourceserver.repositories;

import com.vdjuketi.resourceserver.dto.Court;
import com.vdjuketi.resourceserver.dto.Facility;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CourtRepository extends JpaRepository<Court, Long> {
  @Modifying
  @Transactional
  @Query("update court cou set cou.facility = :facility where cou.id = :courtId")
  int addFacilityToCourt(@Param("facility") Facility facility, @Param("courtId") Long courtId);
}
