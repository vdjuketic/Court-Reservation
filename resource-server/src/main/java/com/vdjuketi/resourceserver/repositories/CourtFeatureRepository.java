package com.vdjuketi.resourceserver.repositories;

import com.vdjuketi.resourceserver.dto.CourtFeature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourtFeatureRepository extends JpaRepository<CourtFeature, Long> {}
