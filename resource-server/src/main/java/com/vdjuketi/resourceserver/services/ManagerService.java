package com.vdjuketi.resourceserver.services;

import com.vdjuketi.resourceserver.repositories.ManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ManagerService {

  private final ManagerRepository repository;
}
