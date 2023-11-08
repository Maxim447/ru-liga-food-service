CREATE SEQUENCE IF NOT EXISTS restaurant_menu_items_seq;

CREATE TABLE IF NOT EXISTS restaurant_menu_items
(
    id            BIGINT         NOT NULL DEFAULT nextval('restaurant_menu_items_seq'),
    restaurant_id BIGINT,
    name          VARCHAR(256)   NOT NULL,
    price         NUMERIC(10, 2) NOT NULL CHECK (price >= 0),
    image         VARCHAR(256),
    description   VARCHAR(1024),
    CONSTRAINT restaurant_menu_items_pk PRIMARY KEY (id),
    CONSTRAINT restaurant_menu_items_restaurants_fk FOREIGN KEY (restaurant_id)
        REFERENCES Restaurants (id)
);

COMMENT ON TABLE Restaurant_menu_items IS 'Товары в меню';
COMMENT ON COLUMN Restaurant_menu_items.id IS 'Идентификатор';
COMMENT ON COLUMN Restaurant_menu_items.restaurant_id IS 'Идентификатор ресторана';
COMMENT ON COLUMN Restaurant_menu_items.name IS 'Название';
COMMENT ON COLUMN Restaurant_menu_items.price IS 'Цена';
COMMENT ON COLUMN Restaurant_menu_items.image IS 'Изображение';
COMMENT ON COLUMN Restaurant_menu_items.description IS 'Описание';