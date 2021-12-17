create table departments(
	id serial primary key,
	name varchar(255)
);

create table emploers(
	id serial primary key,
	name varchar(255),
	department_id int references departments(id)
);

create table teens(
	id serial primary key,
	name varchar(255),
	gender varchar(10)
);