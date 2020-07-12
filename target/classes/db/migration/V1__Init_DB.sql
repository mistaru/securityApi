create sequence hibernate_sequence start 3 increment 1;
create table todo (
	id  bigserial not null,
	closing_date date,
	create_date date,
	description varchar(255),
	status varchar(255),
	title varchar(255),
	user_id int8,
	primary key (id));

create table user_role (
	user_id int8 not null,
	roles varchar(255));

create table users (
	id  bigserial not null,
	activity boolean,
	email varchar(255),
	full_name varchar(255) not null,
	password varchar(255) not null,
	username varchar(255) not null,
	primary key (id));

alter table if exists users
 	add constraint userunigue
 	unique (username);

alter table if exists todo
	add constraint fk_todo_user
	foreign key (user_id) references users;

alter table if exists user_role
	add constraint fk_role_user
	foreign key (user_id) references users;