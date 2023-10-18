CREATE SEQUENCE IF NOT EXISTS orders_seq;

CREATE TABLE IF NOT EXISTS orders(
    id bigint not null default nextval('orders_seq'),
    customer_id bigint not null,
    restaurant_id bigint not null,
    status varchar(32) not null,
    courier_id bigint,
    timestamp timestamp not null,
    constraint order_pk primary key (id),
    constraint customer_fk foreign key(customer_id) references customers(id),
    constraint restaurant_fk foreign key(restaurant_id) references restaurants(id),
    constraint courier_fk foreign key(courier_id) references couriers(id)
);


comment on table orders is 'Заказы';
comment on column orders.id is 'Идентификатор заказа';
comment on column orders.customer_id is 'Идентификатор покупателя';
comment on column orders. restaurant_id is 'Идентификатор ресторана';
comment on column orders.status is 'Статус заказа';
comment on column orders.courier_id is 'Идентификатор курьера';
comment on column orders.timestamp is 'Дата';