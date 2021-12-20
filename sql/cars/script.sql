-- 1. Вывести список всех машин и все привязанные к ним детали.
select c.name, b.type, e.name, e.power, t.name from car_body cb 
left join car c on c.id = cb.car_id
left join body b on b.id = cb.body_id
left join car_engine ce on ce.car_id = cb.car_id
left join engine e on e.id = ce.engine_id
left join car_transmission ct on ct.car_id = cb.car_id
left join transmission t on t.id = ct.transmission_id;

-- Вывести отдельно детали (1 деталь - 1 запрос), 
-- которые не используются НИ в одной машине, 
-- кузова, двигатели, коробки передач.
-- body
select b.type from car_body cb 
right join car c on c.id = cb.car_id
right join body b on b.id = cb.body_id
where c.name is null;

-- engine
select e.name, e.power from car_engine ce 
right join car c on c.id = ce.car_id
right join engine e on e.id = ce.engine_id
where c.name is null;

-- transmission
select t.name from car_transmission ct 
right join car c on c.id = ct.car_id
right join transmission t on t.id = ct.transmission_id
where c.name is null;
