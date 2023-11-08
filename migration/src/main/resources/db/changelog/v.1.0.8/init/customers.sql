CREATE SEQUENCE IF NOT EXISTS customer_seq;

CREATE TABLE IF NOT EXISTS customers
(
    id        bigint       not null default nextval('customer_seq'),
    phone     VARCHAR(20)  NOT NULL UNIQUE,
    email     VARCHAR(128) NOT NULL UNIQUE,
    address   VARCHAR(256) NOT NULL,
    longitude NUMERIC,
    latitude  NUMERIC,
    CONSTRAINT customers_pk PRIMARY KEY (id)
);

COMMENT ON TABLE Customers IS 'Заказчики';
COMMENT ON COLUMN Customers.id IS 'Идентификатор';
COMMENT ON COLUMN Customers.phone IS 'Телефон';
COMMENT ON COLUMN Customers.email IS 'Электронная почта';
COMMENT ON COLUMN Customers.address IS 'Адрес';
COMMENT ON COLUMN Customers.longitude IS 'Долгота';
COMMENT ON COLUMN Customers.latitude IS 'Широта';