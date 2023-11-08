CREATE SEQUENCE IF NOT EXISTS couriers_seq;

CREATE TABLE IF NOT EXISTS couriers
(
    id        bigint      not null default nextval('couriers_seq'),
    phone     VARCHAR(20) NOT NULL UNIQUE,
    status    VARCHAR(30) NOT NULL,
    longitude NUMERIC,
    latitude  NUMERIC,
    CONSTRAINT couriers_pk PRIMARY KEY (id)
);

COMMENT ON TABLE Couriers IS 'Курьеры';
COMMENT ON COLUMN Couriers.id IS 'Идентификатор';
COMMENT ON COLUMN Couriers.phone IS 'Телефон';
COMMENT ON COLUMN Couriers.status IS 'Статус';
COMMENT ON COLUMN Couriers.longitude IS 'Долгота';
COMMENT ON COLUMN Couriers.latitude IS 'Широта';