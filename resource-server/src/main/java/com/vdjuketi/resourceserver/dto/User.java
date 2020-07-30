package com.vdjuketi.resourceserver.dto;

import javax.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@DynamicUpdate
@Entity(name = "user")
@Table(name = "user")
public class User extends AuditModel {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String username;

  private String password;

  private String email;
}
