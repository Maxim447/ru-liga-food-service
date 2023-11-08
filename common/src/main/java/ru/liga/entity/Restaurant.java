package ru.liga.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.liga.entity.enums.RestaurantStatus;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "restaurants")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "restaurants_seq")
    @SequenceGenerator(name = "restaurants_seq", sequenceName = "restaurants_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private RestaurantStatus status;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "longitude", nullable = false)
    private BigDecimal longitude;

    @Column(name = "latitude", nullable = false)
    private BigDecimal latitude;
}
