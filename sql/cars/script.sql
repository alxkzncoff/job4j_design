-- 1. Вывести список всех машин и все привязанные к ним детали.
select c.name, b.type, e.name, e.power, t.name  from car c
left join body b on b.id = c.body_id
left join engine e on e.id = c.engine_id
left join transmission t on t.id = c.transmission_id;


-- Вывести отдельно детали (1 деталь - 1 запрос), 
-- которые не используются НИ в одной машине, 
-- кузова, двигатели, коробки передач.
-- body
select b.type from car c
right join body b on  b.id = c.body_id
where c.name is null;

-- engine
select e.name, e.power from car c
right join engine e on  e.id = c.engine_id
where c.name is null;

-- transmission
select t.name from car c
right join transmission t on  t.id = c.transmission_id
where c.name is null;
