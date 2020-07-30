package com.vdjuketi.resourceserver.dto;

import java.util.Set;
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
@Entity(name = "court_feature")
@Table(name = "court_feature")
public class CourtFeature extends AuditModel {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NonNull private String name;

  @NonNull private String type;

  @ManyToMany(mappedBy = "features")
  private Set<Court> courts;
}
