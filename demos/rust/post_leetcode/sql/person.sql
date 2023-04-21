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