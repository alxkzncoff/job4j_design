create table doctors(
	id serial primary key,
	name varchar(255)
);

create table patients(
	id serial primary key,
	name varchar(255)
);

create table patients_doctors(
	id serial primary key,
	doctor_id int references doctors(id),
	patient_id int references patients(id)
);

insert into patients(name) values ('Alex');
insert into patients(name) values ('Mike');
insert into patients(name) values ('Kate');

insert into doctors(name) values ('Therapist');
insert into doctors(name) values ('Surgeon');
insert into doctors(name) values ('Oculist');

insert into patients_doctors(patient_id, doctor_id) values (1, 1);
insert into patients_doctors(patient_id, doctor_id) values (1, 3);
insert into patients_doctors(patient_id, doctor_id) values (2, 1);
insert into patients_doctors(patient_id, doctor_id) values (3, 1);
insert into patients_doctors(patient_id, doctor_id) values (3, 2);
insert into patients_doctors(patient_id, doctor_id) values (3, 3);
