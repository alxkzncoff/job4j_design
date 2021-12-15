create table genres(
	id serial primary key,
	name varchar(255)
);

create table books(
	id serial primary key,
	name varchar(255),
	author varchar(255),
	genre_id int references genres(id)
);

insert into genres(name) values ('fantasy');
insert into genres(name) values ('sci-fi');
insert into genres(name) values ('romance');

insert into books(name, author, genre_id) values ('Harry Potter and Chamber of Secrets', 'J.K. Rowling', 1);
insert into books(name, author,  genre_id) values ('Dune', 'Frank Herbert', 2);
insert into books(name, author,  genre_id) values ('Lord of The Rings', 'J.R.R. Tolkien', 1);
insert into books(name, author,  genre_id) values ('Gone with the wind', 'Margaret Mitchell', 3);
insert into books(name, author,  genre_id) values ('Hyperion', 'Dan Simmons', 2);

select b.name, g.name
from books as b
join genres as g
on b.genre_id = g.id;

select b.author, g.name
from books as b
join genres as g
on b.genre_id = g.id;

select b.name as Название, b.author as Автор, g.name as Жанр
from books as b
join genres as g
on b.genre_id = g.id;