create table patients(
	id serial primary key,
	first_name varchar(255),
	last_name varchar(255)
);

create table contact_info(
	id serial primary key,
	city varchar(64),
	phone varchar(64)
);

create table patients_contacts(
	id serial primary key,
	patient_id int references patient(id) unique,
	contact_id int references contact_info(id) unique
);
