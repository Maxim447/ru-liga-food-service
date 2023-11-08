package ru.liga.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import ru.liga.entity.enums.OrderStatus;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "orders")
public class Order {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customerId;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurantId;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @ManyToOne
    @JoinColumn(name = "courier_id")
    private Courier courierId;

    @Column(name = "timestamp", nullable = false)
    private Timestamp timestamp;

    @JsonIgnore
    @OneToMany(mappedBy = "orderId", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<OrderItem> items;
}
