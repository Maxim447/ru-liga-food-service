package ru.liga.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customerId;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurantId;

    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "courier_id")
    private Courier courierId;

    @Column(name = "timestamp", nullable = false)
    private Date timestamp;
}
