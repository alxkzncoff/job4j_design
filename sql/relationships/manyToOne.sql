create table company(
	id serial primary key,
	name varchar (255)
);

create table model(
	id serial primary key,
	name varchar (255),
	company_id int references company(id)
);

insert into company(name) values ('kia');
insert into model(name, company_id) values ('Rio', 1);

select * from model;

select * from company where id in (select id from model);