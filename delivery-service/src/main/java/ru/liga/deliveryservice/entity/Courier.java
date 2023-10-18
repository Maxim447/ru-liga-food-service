package ru.liga.deliveryservice.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.liga.deliveryservice.dto.CourierStatus;

import javax.persistence.*;
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
}
