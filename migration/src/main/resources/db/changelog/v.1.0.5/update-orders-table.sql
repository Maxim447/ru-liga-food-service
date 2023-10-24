UPDATE orders
set status = 'DELIVERY_DELIVERING'
where id % 2 = 0;

UPDATE orders
set status = 'DELIVERY_COMPLETE'
where id % 2 = 1;