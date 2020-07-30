package com.vdjuketi.resourceserver.services;

import com.vdjuketi.resourceserver.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserService {

  private final UserRepository repository;
}
