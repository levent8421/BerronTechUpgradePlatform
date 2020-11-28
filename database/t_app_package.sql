drop table if exists t_app_package;

create table `t_app_package`
(
    id          int(10)      not null auto_increment primary key comment 'Row Id',
    name        varchar(255) not null comment 'App name',
    dir_name    varchar(255) not null comment 'App file storage dir name',
    description text         null comment 'Description text',
    creator_id  int(10)      not null comment 'Creator id, t_user.id',
    platform    int(3)       not null comment 'app platform',
    create_time datetime     not null comment 'Row create time',
    update_time datetime     not null comment 'Row last Update time',
    deleted     bit(1)       not null comment 'Delete mark'
) engine = 'innodb'
  charset utf8
  collate utf8_general_ci;

select ap.id          as ap_id,
       ap.name        as ap_name,
       ap.dir_name    as ap_dir_name,
       ap.description as ap_description,
       ap.creator_id  as ap_creator_id,
       ap.platform    as ap_platform,
       ap.create_time as ap_create_time,
       ap.update_time as ap_update_time,
       ap.deleted     as ap_deleted
from t_app_package as ap
where ap.deleted = false;