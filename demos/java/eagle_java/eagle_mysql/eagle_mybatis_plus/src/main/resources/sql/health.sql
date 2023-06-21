# private Integer id;
# private String name;
# private Integer age;
# private Integer status;
# private String description;
# private Boolean deleted;
create table health (
    id int primary key auto_increment,
    name varchar(200),
    age tinyint unsigned,
    status tinyint unsigned,
    description varchar(200),
    is_deleted bool # 0 for false
) engine = innodb
 default character set utf8mb4
 collate utf8mb4_general_ci;
alter table health modify is_deleted boolean default false;
select * from health;