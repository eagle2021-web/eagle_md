show databases ;
create database test;
use test;
create table health
(
    id          bigint primary key,
    name        varchar(200),
    age         int,
    status      tinyint,
    description varchar(200),
    deleted     bool
) ENGINE = InnoDB
  DEFAULT CHARACTER SET utf8mb4
  COLLATE utf8mb4_general_ci;