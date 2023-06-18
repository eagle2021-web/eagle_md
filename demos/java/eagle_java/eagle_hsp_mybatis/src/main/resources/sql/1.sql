create database hsp_mybatis;
use hsp_mybatis;
drop table monster;
create table monster (
    id int primary key auto_increment,
    age tinyint unsigned,
    name varchar(200),
    email varchar(200),
    birthday date,
    salary double,
    gender bit
)ENGINE = InnoDB
  DEFAULT CHARACTER SET utf8mb4
    COLLATE utf8mb4_general_ci;
alter table monster modify name varchar(200) default '';
show databases ;
show tables;
select * from monster;
insert into monster (name) values('大东西');
ALTER TABLE monster CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
SHOW VARIABLES LIKE 'character_set_database';