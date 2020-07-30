package com.vdjuketi.resourceserver.repositories;

import com.vdjuketi.resourceserver.dto.FacilityFeature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacilityFeatureRepository extends JpaRepository<FacilityFeature, Long> {}
