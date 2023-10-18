package ru.liga.deliveryservice.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.liga.deliveryservice.dto.RestaurantStatus;

import javax.persistence.*;
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
}
