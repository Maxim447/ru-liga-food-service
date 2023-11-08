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
@Table(name = "restaurant_menu_items")
public class RestaurantMenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "restaurant_menu_items_seq")
    @SequenceGenerator(name = "restaurant_menu_items_seq", sequenceName = "restaurant_menu_items_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurantId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "image")
    private String image;

    @Column(name = "description")
    private String description;
}
