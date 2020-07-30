package com.vdjuketi.resourceserver.dto;

import com.vdjuketi.resourceserver.dto.enums.SportTypeEnum;
import javax.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import java.util.Set;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@DynamicUpdate
@Entity(name = "facility")
@Table(name = "facility")
public class Facility extends AuditModel {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NonNull private String name;

  @NonNull
  @Column(name = "sport_type")
  @Enumerated(EnumType.STRING)
  private SportTypeEnum sportType;

  @NonNull private String address;

  @NonNull private String email;

  @NonNull private Double longitude;

  @NonNull private Double latitude;

  @NonNull
  @Column(name = "phone_number")
  private String phoneNumber;

  @NonNull
  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinColumn(name = "manager", insertable = false, updatable = false)
  private Manager manager;
}
