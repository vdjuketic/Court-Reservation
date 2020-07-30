package com.vdjuketi.resourceserver.dto;

import javax.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@DynamicUpdate
@Entity(name = "device")
@Table(name = "device")
public class Device extends AuditModel {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @NonNull private String serial;

  @NonNull private String model;

  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinColumn(name = "facility", insertable = false, updatable = false)
  private Facility facility;
}
