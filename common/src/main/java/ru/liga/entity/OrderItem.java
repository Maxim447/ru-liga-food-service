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
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_items_seq")
    @SequenceGenerator(name = "order_items_seq", sequenceName = "order_items_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order orderId;

    @OneToOne
    @JoinColumn(name = "restaurant_menu_item_id")
    private RestaurantMenuItem restaurantMenuItem;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;
}
