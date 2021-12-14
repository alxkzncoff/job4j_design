insert into role(name) values ('junior programmer');
insert into role(name) values ('middle programmer');
insert into role(name) values ('senior programmer');

insert into rules(name) values ('restricted');
insert into rules(name) values ('full');
insert into rules(name) values ('extended');

insert into users(name, role_id) values ('Alex', 1);
insert into users(name, role_id) values ('Mike', 2);
insert into users(name, role_id) values ('Kate', 3);

insert into role_rules(role_id, rules_id) values (1, 1);
insert into role_rules(role_id, rules_id) values (2, 2);
insert into role_rules(role_id, rules_id) values (3, 2);
insert into role_rules(role_id, rules_id) values (3, 3);

insert into categories(name) values ('urgent');
insert into categories(name) values ('common');

insert into states(state) values ('open');
insert into states(state) values ('close');
insert into states(state) values ('in progress');

insert into item(name, user_id, category_id, state_id) values ('server bug', 2, 1, 1);
insert into item(name, user_id, category_id, state_id) values ('client issue', 1, 2, 3);
insert into item(name, user_id, category_id, state_id) values ('server issue', 3, 1, 2);

insert into comments(comment, item_id) values ('Network issue', 1);
insert into comments(comment, item_id) values ('Service №3 does not work', 2);
insert into comments(comment, item_id) values ('Services comunication problem', 3);

insert into attachs(name, item_id) values ('description.txt', 2);
insert into attachs(name, item_id) values ('image.jpg', 1);
insert into attachs(name, item_id) values ('log.txt', 3);