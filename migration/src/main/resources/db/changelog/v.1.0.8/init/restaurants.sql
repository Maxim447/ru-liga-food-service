CREATE SEQUENCE IF NOT EXISTS restaurants_seq;

CREATE TABLE IF NOT EXISTS Restaurants
(
    id        bigint not null default nextval('restaurants_seq'),
    name      VARCHAR(256) NOT NULL,
    address   VARCHAR(256) NOT NULL,
    status    VARCHAR(30)  NOT NULL,
    longitude NUMERIC,
    latitude  NUMERIC,
    CONSTRAINT restaurants_pk PRIMARY KEY (id)
);

COMMENT ON TABLE Restaurants IS 'Рестораны';
COMMENT ON COLUMN Restaurants.id IS 'Идентификатор';
COMMENT ON COLUMN Restaurants.name IS 'Название';
COMMENT ON COLUMN Restaurants.address IS 'Адрес';
COMMENT ON COLUMN Restaurants.status IS 'Статус';
COMMENT ON COLUMN Restaurants.longitude IS 'Долгота';
COMMENT ON COLUMN Restaurants.latitude IS 'Широта';