create database hsp_mybatis;
use hsp_mybatis;
create table monster (
    id int primary key auto_increment,
    age tinyint unsigned,
    name varchar(200),
    email varchar(200),
    birthday date,
    salary double,
    gender bit
) engine=innodb
    default charset = utf8mb4
    collate utf8mb4_general_ci;
show databases ;
show tables;