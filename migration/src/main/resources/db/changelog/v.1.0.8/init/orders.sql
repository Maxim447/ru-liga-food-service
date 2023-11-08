CREATE TABLE IF NOT EXISTS Orders
(
    id            UUID                 DEFAULT gen_random_uuid(),
    customer_id   bigint      not null,
    restaurant_id bigint      not null,
    courier_id    bigint,
    status        VARCHAR(30),
    timestamp     TIMESTAMPTZ NOT NULL DEFAULT now(),
    CONSTRAINT orders_pk PRIMARY KEY (id),
    CONSTRAINT orders_customers_fk FOREIGN KEY (customer_id)
        REFERENCES Customers (id),
    CONSTRAINT orders_restaurants_fk FOREIGN KEY (restaurant_id)
        REFERENCES Restaurants (id),
    CONSTRAINT orders_couriers_fk FOREIGN KEY (courier_id)
        REFERENCES Couriers (id)
);

COMMENT ON TABLE Orders IS 'Заказы';
COMMENT ON COLUMN Orders.id IS 'Идентификатор';
COMMENT ON COLUMN Orders.customer_id IS 'Идентификатор заказчика';
COMMENT ON COLUMN Orders.restaurant_id IS 'Идентификатор ресторана';
COMMENT ON COLUMN Orders.status IS 'Статус';
COMMENT ON COLUMN Orders.courier_id IS 'Идентификатор курьера';
COMMENT ON COLUMN Orders.timestamp IS 'Время заказа';