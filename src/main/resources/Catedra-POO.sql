create database pooC;

use pooC;

create table user_types(
id int not null primary key,
name varchar (100) not null
);

create table users(
id int primary key not null,
name varchar (100) not null,
lastname varchar (100) not null,
email varchar (255) not null,
pass varchar (20) not null,
user_type_id int not null,
foreign key (user_type_id) references user_types(id)
);

create table complaint_types(
id int not null primary key,
name varchar (100) not null
);

create table schools(
id int not null primary key,
name varchar(100) not null
);

create table authorities(
id int not null primary key,
name varchar(100) not null
);

create table providers(
id int not null primary key,
name varchar(100) not null
);

create table calls(
id int not null primary key,
school_id int not null,
viable bool not null default 1,
complaint_id int not null,
user_id int not null,
description text not null,
call_date date not null,
foreign key (school_id) references schools(id),
foreign key (complaint_id) references complaint_types(id),
foreign key (user_id) references users(id)
);

create table authority_asigns(
id int not null primary key,
call_id int not null,
authority_id int not null,
foreign key (call_id) references calls(id),
foreign key (authority_id) references authorities(id)
);

create table provider_asigns(
id int not null primary key,
call_id int not null,
provider_id int not null,
foreign key (call_id) references calls(id),
foreign key (provider_id) references providers(id)
);





