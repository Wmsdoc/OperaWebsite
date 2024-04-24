create table his_config_info
(
    id                 bigint unsigned                        not null,
    nid                bigint unsigned auto_increment
        primary key,
    data_id            varchar(255)                           not null,
    group_id           varchar(128)                           not null,
    app_name           varchar(128)                           null comment 'app_name',
    content            longtext                               not null,
    md5                varchar(32)                            null,
    gmt_create         datetime     default CURRENT_TIMESTAMP not null,
    gmt_modified       datetime     default CURRENT_TIMESTAMP not null,
    src_user           text                                   null,
    src_ip             varchar(50)                            null,
    op_type            char(10)                               null,
    tenant_id          varchar(128) default ''                null comment '租户字段',
    encrypted_data_key text                                   not null comment '秘钥'
)
    comment '多租户改造' engine = InnoDB
                         collate = utf8mb3_bin;

create index idx_did
    on his_config_info (data_id);

create index idx_gmt_create
    on his_config_info (gmt_create);

create index idx_gmt_modified
    on his_config_info (gmt_modified);

