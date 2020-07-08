insert into users (id, full_name, username, email, password, activity)
        values (1, 'Argen Kasymov', 'admin', 'em_ail@bk.ru', '123456', true);

insert into users (id, full_name, username, email, password, activity)
        values (2, 'David Beckham', 'user', 'mistaru@bk.ru', '123456', true);

insert into user_role (user_id, roles) values (1, 'USER'), (1, 'ADMIN');
insert into user_role (user_id, roles) values (2, 'USER');

