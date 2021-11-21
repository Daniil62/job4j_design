insert into role(name) values('admin');
insert into role(name) values('user');
insert into users(name, role_id) values('Daniil', 1);
insert into users(name, role_id) values('Vasisuali', 2);
insert into rules(rule) values('write');
insert into rules(rule) values('read');
insert into role_rules(role_id, rules_id) values (1, 1);
insert into role_rules(role_id, rules_id) values (1, 2);
insert into role_rules(role_id, rules_id) values (2, 2);
insert into comments(comment) values('good job, dude.');
insert into attachs(attach) values('attach');
insert into item(name, user_id, comment_id, attach_id) values('first_item', 2, 1, 1);
insert into category(name, item_id) values('important', 1);
insert into state(name, item_id) values('accepted', 1);