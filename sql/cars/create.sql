create table car(
	id serial primary key,
	name varchar(255)
);

create table body(
	id serial primary key,
	type varchar(255)
);

create table car_body(
	id serial primary key,
	car_id int references car(id),
	body_id int references body(id)
);

create table engine(
	id serial primary key,
	name varchar(255),
	power int
);

create table car_engine(
	id serial primary key,
	car_id int references car(id),
	engine_id int references engine(id)
);

create table transmission(
	id serial primary key,
	name varchar(255)
);

create table car_transmission(
	id serial primary key,
	car_id int references car(id),
	transmission_id int references transmission(id)
);
