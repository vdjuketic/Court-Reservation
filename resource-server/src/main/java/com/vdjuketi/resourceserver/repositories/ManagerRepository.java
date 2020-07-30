package com.vdjuketi.resourceserver.repositories;

import com.vdjuketi.resourceserver.dto.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Long> {}
