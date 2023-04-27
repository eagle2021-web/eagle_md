drop database dbname;
create database dbname;
drop table student;
create table student (
    id serial primary key ,
    name varchar(100) not null ,
    gender int2
);
insert into student ( name, gender) values ('eagle', 1);
insert into student ( name, gender) values ('eagle1', 1);

CREATE TABLE posts (
   id SERIAL PRIMARY KEY,
   title VARCHAR NOT NULL,
   body TEXT NOT NULL,
   published BOOLEAN NOT NULL
);

insert into posts (title, body, published) values
    ('a', 'b', true), ('a1', 'b1', true), ('a2', 'b2', true), ('a3', 'b3', true), ('a4', 'b4', true) ;