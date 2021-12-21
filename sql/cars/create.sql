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