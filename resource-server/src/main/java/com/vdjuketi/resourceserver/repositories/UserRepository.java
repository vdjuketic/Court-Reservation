package com.vdjuketi.resourceserver.repositories;

import com.vdjuketi.resourceserver.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {}
