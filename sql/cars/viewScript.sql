-- Create tables
create table body(
	id serial primary key,
	type varchar(255)
);

create table engine(
	id serial primary key,
	name varchar(255),
	power int
);

create table transmission(
	id serial primary key,
	name varchar(255)
);

create table car(
	id serial primary key,
	name varchar(255),
	body_id int references body(id),
	engine_id int references engine(id),
	transmission_id int references transmission(id)
);

-- Insert data
insert into body(type) values ('3 doors');
insert into body(type) values ('4 doors');
insert into body(type) values ('5 doors');
insert into body(type) values ('2 doors');

insert into engine(name, power) values ('petrol', 150);
insert into engine(name, power) values ('petrol', 180);
insert into engine(name, power) values ('diesel', 180);
insert into engine(name, power) values ('diesel', 210);
insert into engine(name, power) values ('electric', 180);
insert into engine(name, power) values ('gas', 180);

insert into transmission(name) values ('AT');
insert into transmission(name) values ('MT');
insert into transmission(name) values ('Robot');

insert into car(name, body_id, engine_id, transmission_id) values ('bmw', 4, 2, 1);
insert into car(name, body_id, engine_id, transmission_id) values ('hyundai', 2, 3, 2);
insert into car(name, body_id, engine_id, transmission_id) values ('kia', 4, 4, 2);
insert into car(name, body_id, engine_id, transmission_id) values ('renault', 1, 3, 1);

-- Without view
select c.name as Model, b.type, e.name, e.power, t.name 
from car c
left join body b on b.id = c.body_id
left join engine e on e.id = c.engine_id
left join transmission t on t.id = c.transmission_id
where b.type = '2 doors' and e.name = 'petrol';

-- Create view
create view petrol_with_two_doors as
	select c.name as Model, 
			b.type as Body, 
			e.name as Engine, 
			e.power as Power, 
			t.name as Transmission 
		from car c
		left join body b on b.id = c.body_id
		left join engine e on e.id = c.engine_id
		left join transmission t on t.id = c.transmission_id
		where b.type = '2 doors' and e.name = 'petrol';
		
select * from petrol_with_two_doors;

-- Alter view
alter view petrol_with_two_doors rename to two_doors;

select * from two_doors;

-- Drop view
drop view two_doors;