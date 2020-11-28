drop table if exists t_user_app;

create table t_user_app
(
    id          int(10)  not null auto_increment primary key comment 'Row id',
    user_id     int(10)  not null comment 'create User id',
    app_id      int(10)  not null comment 'Application id',
    role        int(3)   not null comment 'Role code',
    create_time datetime not null comment 'Row create time',
    update_time datetime not null comment 'Row last update time',
    deleted     bit(1)   not null comment 'Delete mark'
) engine = 'innodb'
  charset utf8
  collate utf8_general_ci;


select ua.id          as ua_id,
       ua.user_id     as ua_user_id,
       ua.app_id      as ua_app_id,
       ua.role        as ua_role,
       ua.create_time as ua_create_time,
       ua.update_time as ua_update_time,
       ua.deleted     as ua_deleted
from t_user_app as ua
where ua.deleted = false;