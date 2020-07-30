package com.vdjuketi.resourceserver.repositories;

import com.vdjuketi.resourceserver.dto.Facility;
import com.vdjuketi.resourceserver.dto.enums.SportTypeEnum;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FacilityRepository extends JpaRepository<Facility, Long> {

  List<Facility> findBySportType(SportTypeEnum sportType);

  Optional<Facility> findById(Long id);

  @Query(
      value =
          "SELECT * from facility f "
              + "WHERE (earth_box(ll_to_earth(:longitude, :latitude), :radius) @> ll_to_earth(f.longitude, f.latitude)) "
              + "              AND earth_distance(ll_to_earth(:longitude, :latitude), ll_to_earth(f.longitude, f.latitude)) <= :radius",
      nativeQuery = true)
  List<Facility> findNearby(Double longitude, Double latitude, int radius);
}
