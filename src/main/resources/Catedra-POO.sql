create database pooC;

use pooC;

create table user_types(
id int not null primary key auto_increment,
name varchar (100) not null
);

create table users(
id int primary key not null auto_increment,
name varchar (100) not null,
lastname varchar (100) not null,
email varchar (255) not null,
pass varchar (20) not null,
user_type_id int not null,
state bool not null default 1,
foreign key (user_type_id) references user_types(id)
);

create table complaint_types(
id int not null primary key auto_increment,
name varchar (100) not null,
taken_action enum('Remitir con autoridad competente', 'Tomar contacto con ISP y colegio') not null,
state bool not null default 1
);

create table schools(
id int not null primary key auto_increment,
name varchar(100) not null,
address varchar(255) not null,
state bool not null default 1
);

create table authorities(
id int not null primary key auto_increment,
name varchar(100) not null,
state bool not null default 1
);

create table providers(
id int not null primary key auto_increment,
name varchar(100) not null,
state bool not null default 1
);

create table calls(
id int not null primary key auto_increment,
school_id int not null,
viable bool not null default 1,
complaint_id int not null,
user_id int not null,
description text not null,
call_date date not null,
talk_given bool not null default 0,
foreign key (school_id) references schools(id),
foreign key (complaint_id) references complaint_types(id),
foreign key (user_id) references users(id)
);

create table authority_asigns(
id int not null primary key auto_increment,
call_id int not null,
authority_id int not null,
foreign key (call_id) references calls(id),
foreign key (authority_id) references authorities(id)
);

create table provider_asigns(
id int not null primary key auto_increment,
call_id int not null,
provider_id int not null,
content_removed bool not null default 0,
foreign key (call_id) references calls(id),
foreign key (provider_id) references providers(id)
);


insert into user_types values (null, "Administrador");
insert into user_types values (null, "Personal");

insert into users values (null, "Oscar", "Méndez", "admin@gmail.com", "123", 1, 1);
insert into users values (null, "Kevin", "Morán", "user@gmail.com", "123", 2, 1);





