package ru.liga.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "coordinates", nullable = false)
    private String coordinates;

    @Column(name = "longitude", nullable = false)
    private BigDecimal longitude;

    @Column(name = "latitude", nullable = false)
    private BigDecimal latitude;

    private BigDecimal payment;
}
