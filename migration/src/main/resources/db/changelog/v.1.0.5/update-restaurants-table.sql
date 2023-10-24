UPDATE restaurants
set status = 'OPEN'
where id % 2 = 1;

UPDATE restaurants
set status = 'CLOSED'
where id % 2 = 0;