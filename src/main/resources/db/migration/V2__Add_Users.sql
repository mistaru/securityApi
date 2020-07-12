insert into users (id, full_name, username, email, password, activity)
        values (1024, 'Argen Kasymov', 'admin', 'em_ail@bk.ru', '123456', true);

insert into users (id, full_name, username, email, password, activity)
        values (1023, 'David Beckham', 'user', 'mistaru@bk.ru', '123456', true);

insert into user_role (user_id, roles) values (1024, 'USER'), (1024, 'ADMIN');
insert into user_role (user_id, roles) values (1023, 'USER');

