CREATE SEQUENCE IF NOT EXISTS restaurants_seq;

CREATE TABLE IF NOT EXISTS restaurants (
    id bigint not null default nextval('restaurants_seq'),
    address varchar(256) not null,
    status varchar(32) not null,
    constraint restaurants_pk primary key (id)
);

comment on table restaurants is 'Рестораны';
comment on column restaurants.id is 'Идентификатор ресторана';
comment on column restaurants.address is 'Адрес ресторана';
comment on column restaurants.status is 'Статус ресторана';