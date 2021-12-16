-- 1. Написать запрос получение всех продуктов с типом "СЫР".
select p.name as Название, p.price as Цена, 
	p.expired_date as Срок_хранения, t.name as Тип
from product as p
join type as t
on t.id = p.type_id
where t.name = 'сыр';

-- 2. Написать запрос получения всех продуктов, у кого в имени есть слово "мороженое".
select name as Название, price as Цена, 
	expired_date as Срок_хранения
from product
where name like '%мороженое%';

-- 3. Написать запрос, который выводит все продукты, срок годности которых уже истек.
select name as Название, price as Цена, 
	expired_date as Срок_хранения
from product
where expired_date < current_date;

-- 4. Написать запрос, который выводит самый дорогой продукт.
select name, price
from product
where price = (select max(price) from product);

-- 5. Написать запрос, который выводит для каждого типа 
-- количество продуктов к нему принадлежащих. В виде имя_типа, количество.
select t.name as Тип, count(p.name) as Количество
from product as p
join type as t
on t.id = p.type_id
group by Тип;

-- 6. Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО".
select p.name as Название, p.price as Цена, 
	p.expired_date as Срок_хранения, t.name as Тип
from product as p
join type as t
on t.id = p.type_id
where t.name = 'сыр' or t.name = 'молоко';

-- 7. Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук. 
-- Под количеством подразумевается количество продуктов определенного типа. 
-- Например, если есть тип "СЫР" и продукты "Сыр плавленный" и "Сыр моцарелла", 
-- которые ему принадлежат, то количество продуктов типа "СЫР" будет 2. 
select t.name as Тип, count(p.name) as Количество
from product as p
join type as t
on t.id = p.type_id
group by Тип
having count(p.name) < 10;

-- 8. Вывести все продукты и их тип.
select p.name as Название, t.name as Тип
from product as p
join type as t
on t.id = p.type_id;