create table devices(
	id serial primary key,
	name varchar(255),
	price float
);

create table people(
	id serial primary key,
	name varchar(255)
);

create table devices_people(
	id serial primary key,
	device_id int references devices(id),
	people_id int references people(id)
);

insert into devices(name, price) values ('iphone', 1999);
insert into devices(name, price) values ('apple watch', 1499);
insert into devices(name, price) values ('air pods', 1149);
insert into devices(name, price) values ('xiaomi', 1699);
insert into devices(name, price) values ('mi dots', 199);
insert into devices(name, price) values ('macbook', 1999);
insert into devices(name, price) values ('hp notebook', 1499);

insert into people (name) values ('Charil');
insert into people (name) values ('Trueman');
insert into people (name) values ('Frazier');
insert into people (name) values ('Latrena');
insert into people (name) values ('Pierson');

insert into devices_people(people_id, device_id) values (1, 1);
insert into devices_people(people_id, device_id) values (1, 6);
insert into devices_people(people_id, device_id) values (1, 2);
insert into devices_people(people_id, device_id) values (1, 3);
insert into devices_people(people_id, device_id) values (2, 4);
insert into devices_people(people_id, device_id) values (2, 7);
insert into devices_people(people_id, device_id) values (3, 2);
insert into devices_people(people_id, device_id) values (3, 7);
insert into devices_people(people_id, device_id) values (4, 4);
insert into devices_people(people_id, device_id) values (4, 6);
insert into devices_people(people_id, device_id) values (4, 2);
insert into devices_people(people_id, device_id) values (5, 1);
insert into devices_people(people_id, device_id) values (5, 2);
insert into devices_people(people_id, device_id) values (5, 3);
insert into devices_people(people_id, device_id) values (5, 4);
insert into devices_people(people_id, device_id) values (5, 5);
insert into devices_people(people_id, device_id) values (5, 6);
insert into devices_people(people_id, device_id) values (5, 7);

select avg(price) from devices;

select p.name, avg(d.price) from devices_people as dp
join people as p
on dp.people_id = p.id
join devices as d
on dp.device_id = d.id
group by p.name;

select p.name, avg(d.price) from devices_people as dp
join people as p
on dp.people_id = p.id
join devices as d
on dp.device_id = d.id
group by p.name
having avg(d.price) > 1500;