ALTER TABLE couriers
    DROP COLUMN payment;

ALTER TABLE restaurant_menu_items
    ALTER COLUMN price TYPE int;

ALTER TABLE order_items
    ALTER COLUMN price TYPE int;