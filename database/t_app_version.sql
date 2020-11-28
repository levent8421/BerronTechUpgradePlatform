drop table if exists t_app_version;

create table t_app_version
(
    id           int(10)      not null auto_increment primary key comment 'Row Id',
    app_id       int(10)      not null comment 'App id',
    publisher_id int(10)      not null comment 'publish user id',
    version_code int(6)       not null comment 'version code',
    version_name varchar(100) not null comment 'Version name in string',
    release_note text         null comment 'release note',
    description  text         null comment 'description text',
    filename     varchar(255) null comment 'filename',
    state        int(3)       not null comment 'state code',
    create_time  datetime     not null comment 'row create time',
    update_time  datetime     not null comment 'row last modify time',
    deleted      bit(1)       not null comment 'delete mark'
) engine = 'innodb'
  charset utf8
  collate utf8_general_ci;

select av.id           as av_id,
       av.app_id       as av_app_id,
       av.publisher_id as av_publisher_id,
       av.version_code as av_version_code,
       av.version_name as av_version_name,
       av.release_note as av_release_note,
       av.description  as av_description,
       av.filename     as av_filename,
       av.state        as av_state,
       av.create_time  as av_create_time,
       av.update_time  as av_update_time,
       av.deleted      as av_deleted
from t_app_version as av
where av.deleted = false;