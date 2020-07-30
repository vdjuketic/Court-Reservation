package com.vdjuketi.resourceserver.dto;

import com.vdjuketi.resourceserver.dto.enums.ReservationTypeEnum;
import java.sql.Date;
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
@Entity(name = "reservation")
@Table(name = "reservation")
public class Reservation extends AuditModel {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NonNull
  @Enumerated(EnumType.STRING)
  private ReservationTypeEnum type;

  @NonNull private Date startTime;

  @NonNull private Date endTime;

  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinColumn(name = "user", insertable = false, updatable = false)
  private User user;

  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinColumn(name = "manager", insertable = false, updatable = false)
  private Manager manager;

  @NonNull
  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinColumn(name = "court", insertable = false, updatable = false)
  private Court court;
}
