insert into car(name) values ('bmw');
insert into car(name) values ('hyundai');
insert into car(name) values ('kia');
insert into car(name) values ('renault');

insert into body(type) values ('3 doors');
insert into body(type) values ('4 doors');
insert into body(type) values ('5 doors');
insert into body(type) values ('2 doors');

insert into car_body(car_id, body_id) values (1, 2);
insert into car_body(car_id, body_id) values (1, 4);
insert into car_body(car_id, body_id) values (2, 2);
insert into car_body(car_id, body_id) values (2, 1);
insert into car_body(car_id, body_id) values (2, 4);
insert into car_body(car_id, body_id) values (3, 2);
insert into car_body(car_id, body_id) values (3, 1);
insert into car_body(car_id, body_id) values (4, 2);
insert into car_body(car_id, body_id) values (4, 4);
insert into car_body(car_id, body_id) values (4, 1);

insert into engine(name, power) values ('petrol', 150);
insert into engine(name, power) values ('petrol', 180);
insert into engine(name, power) values ('petrol', 200);
insert into engine(name, power) values ('diesel', 150);
insert into engine(name, power) values ('diesel', 180);
insert into engine(name, power) values ('diesel', 210);
insert into engine(name, power) values ('electric', 180);
insert into engine(name, power) values ('gas', 180);

insert into car_engine(car_id, engine_id) values (1, 1);
insert into car_engine(car_id, engine_id) values (1, 2);
insert into car_engine(car_id, engine_id) values (1, 3);
insert into car_engine(car_id, engine_id) values (1, 5);
insert into car_engine(car_id, engine_id) values (2, 1);
insert into car_engine(car_id, engine_id) values (2, 2);
insert into car_engine(car_id, engine_id) values (2, 4);
insert into car_engine(car_id, engine_id) values (2, 5);
insert into car_engine(car_id, engine_id) values (3, 2);
insert into car_engine(car_id, engine_id) values (3, 3);
insert into car_engine(car_id, engine_id) values (3, 4);
insert into car_engine(car_id, engine_id) values (4, 1);
insert into car_engine(car_id, engine_id) values (4, 2);
insert into car_engine(car_id, engine_id) values (4, 3);
insert into car_engine(car_id, engine_id) values (4, 6);

insert into transmission(name) values ('AT');
insert into transmission(name) values ('MT');
insert into transmission(name) values ('Robot');

insert into car_transmission(car_id, transmission_id) values (1, 1);
insert into car_transmission(car_id, transmission_id) values (1, 2);
insert into car_transmission(car_id, transmission_id) values (2, 1);
insert into car_transmission(car_id, transmission_id) values (3, 2);
insert into car_transmission(car_id, transmission_id) values (4, 1);
insert into car_transmission(car_id, transmission_id) values (4, 2);
