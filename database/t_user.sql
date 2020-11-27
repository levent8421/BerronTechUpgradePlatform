drop table if exists t_user;


create table `t_user`
(
    id          int(10)      not null auto_increment primary key comment 'ID',
    name        varchar(100) not null comment 'Username',
    login_name  varchar(100) not null comment 'User Login name',
    password    varchar(255) not null comment 'Login password',
    avatar      varchar(255) not null comment 'avatar filename',
    create_time datetime     not null comment 'Row create time',
    update_time datetime     not null comment 'Row last modify time',
    deleted     bit(1)       not null comment 'Delete flag'
) engine = 'innodb'
  charset utf8
  collate utf8_general_ci;

insert into t_user (id, name, login_name, password, avatar, create_time, update_time, deleted)
values (1, 'root', 'root', '28253a13a844383d024099ec66d0c17c,levent', 'default.png', now(), now(), false);

select u.id          as u_id,
       u.name        as u_name,
       u.login_name  as u_login_name,
       u.password    as u_password,
       u.avatar      as u_avatar,
       u.create_time as u_create_time,
       u.update_time as u_update_time,
       u.deleted     as u_deleted
from t_user as u
where u.deleted = false;