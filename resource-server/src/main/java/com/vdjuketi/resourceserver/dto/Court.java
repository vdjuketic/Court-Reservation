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
@Entity(name = "court")
@Table(name = "court")
public class Court extends AuditModel {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NonNull
  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinColumn(name = "id", insertable = false, updatable = false)
  private Facility facility;

  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
  @JoinTable(
      name = "court_feature_inter",
      joinColumns = @JoinColumn(name = "court_id"),
      inverseJoinColumns = @JoinColumn(name = "court_feature_id"))
  private Set<CourtFeature> features;
}
