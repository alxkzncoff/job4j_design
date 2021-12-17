-- 2. Выполнить запросы с left, rigth, full, cross соединениями
select * from emploers e left join departments d 
on e.department_id = d.id;

select * from emploers e right join departments d 
on e.department_id = d.id;

select * from emploers e full join departments d 
on e.department_id = d.id;

select * from emploers e cross join departments d;

-- 3. Используя left join найти департаменты, у которых нет работников
select d.name from departments d left join emploers e
on e.department_id = d.id
where e.name is null;

-- 4. Используя left и right join написать запросы, которые давали бы одинаковый результат.
select * from emploers e left join departments d 
on e.department_id = d.id;

select * from departments d right join emploers e 
on e.department_id = d.id;

-- 5. Создать таблицу teens с атрибутами name, gender и заполнить ее. 
-- Используя cross join составить все возможные разнополые пары.
select t1.name, t2.name from teens t1 cross join teens t2
where t1.gender = 'М' and t2.gender = 'Ж';