create table games(
	id serial primary key,
	name varchar(255),
	platform varchar(255),
	company varchar(255)
);

insert into games(name, platform, company) values('God of War', 'Sony Playstation', 'Sony');

select * from games;

update games set name = 'Uncharterd';

select * from games;

delete from games;

select * from games;