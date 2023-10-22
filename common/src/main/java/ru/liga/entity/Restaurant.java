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
@Table(name = "restaurants")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "longitude", nullable = false)
    private BigDecimal longitude;

    @Column(name = "latitude", nullable = false)
    private BigDecimal latitude;
}
