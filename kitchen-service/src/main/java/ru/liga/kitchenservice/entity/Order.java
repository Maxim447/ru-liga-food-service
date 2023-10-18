package ru.liga.kitchenservice.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

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
    private Timestamp timestamp;

    @OneToMany(mappedBy = "orderId")
    private List<OrderItem> items;
}
