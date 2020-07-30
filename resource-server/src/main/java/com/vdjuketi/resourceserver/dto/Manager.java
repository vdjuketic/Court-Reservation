package com.vdjuketi.resourceserver.dto;

import javax.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.DynamicUpdate;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@DynamicUpdate
@Entity(name = "manager")
@Table(name = "manager")
public class Manager extends AuditModel {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NonNull private String username;

  @NonNull private String password;

  @NonNull private String email;
}
