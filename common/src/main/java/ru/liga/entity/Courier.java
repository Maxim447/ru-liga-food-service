package ru.liga.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.liga.entity.enums.CourierStatus;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "couriers")
public class Courier {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "couriers_seq")
    @SequenceGenerator(name = "couriers_seq", sequenceName = "couriers_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private CourierStatus status;

    @Column(name = "longitude", nullable = false)
    private BigDecimal longitude;

    @Column(name = "latitude", nullable = false)
    private BigDecimal latitude;
}
