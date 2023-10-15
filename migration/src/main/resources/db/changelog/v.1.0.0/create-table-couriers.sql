CREATE SEQUENCE IF NOT EXISTS couriers_seq;

CREATE TABLE IF NOT EXISTS couriers (
    id bigint not null default nextval('couriers_seq'),
    phone varchar (32) not null,
    status varchar (32) not null,
    coordinates varchar (128) not null,
    constraint couriers_pk primary key (id)
);

comment on table couriers is 'Курьеры';
comment on column couriers.id is 'Идентификатор курьера';
comment on column couriers.phone is 'Телефон курьера';
comment on column couriers.status is 'Статус курьера';
comment on column couriers.coordinates is 'Координаты курьера';