insert into fauna(name, avg_age, discovery_date) values ('sea fish', 9000, '1951-01-12');
insert into fauna(name, avg_age, discovery_date) values ('ocena fish', 4000, '1949-01-12');
insert into fauna(name, avg_age, discovery_date) values ('river fish', 12000, '1970-01-12');
insert into fauna(name, avg_age, discovery_date) values ('bear', null, '1931-01-12');
insert into fauna(name, avg_age, discovery_date) values ('tiger', 19000, '1901-01-12');
insert into fauna(name, avg_age, discovery_date) values ('lion', 15000, '1977-01-12');
insert into fauna(name, avg_age, discovery_date) values ('bird', 30000, null);

select * from fauna where name like '%fish%';

select * from fauna where avg_age >= 10000 and avg_age <= 21000;

select * from fauna where discovery_date is null;

select * from fauna where discovery_date < '1950-01-01';