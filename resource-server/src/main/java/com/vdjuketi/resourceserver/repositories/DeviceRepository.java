package com.vdjuketi.resourceserver.repositories;

import com.vdjuketi.resourceserver.dto.Device;
import com.vdjuketi.resourceserver.dto.Facility;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {
  @Modifying
  @Transactional
  @Query("update device dev set dev.facility = :facility where dev.id = :deviceId")
  int addFacilityToDevice(@Param("facility") Facility facility, @Param("deviceId") Long deviceId);
}
