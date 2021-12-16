create table type(
	id serial primary key,
	name varchar(255)
);

create table product(
	id serial primary key,
	name varchar(255),
	expired_date date,
	price float,
	type_id int references type(id) 
);