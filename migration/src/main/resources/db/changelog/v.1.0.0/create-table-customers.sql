CREATE SEQUENCE IF NOT EXISTS customer_seq;

CREATE TABLE IF NOT EXISTS customers(
    id bigint not null default nextval('customer_seq'),
    phone varchar(32) not null,
    email varchar(64) not null,
    address varchar(256) not null,
    constraint customers_pk primary key (id)
);

comment on table customers is 'Клиенты';
comment on column customers.id is 'Идентификатор клиента';
comment on column customers.phone is 'Телефон клиента';
comment on column customers.email is 'Почтовый ящик клиента';
comment on column customers.address is 'Адрес клиента';