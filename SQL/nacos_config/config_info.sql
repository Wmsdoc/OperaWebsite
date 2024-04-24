create table config_info
(
    id                 bigint auto_increment comment 'id'
        primary key,
    data_id            varchar(255)                           not null comment 'data_id',
    group_id           varchar(128)                           null,
    content            longtext                               not null comment 'content',
    md5                varchar(32)                            null comment 'md5',
    gmt_create         datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    gmt_modified       datetime     default CURRENT_TIMESTAMP not null comment '修改时间',
    src_user           text                                   null comment 'source user',
    src_ip             varchar(50)                            null comment 'source ip',
    app_name           varchar(128)                           null,
    tenant_id          varchar(128) default ''                null comment '租户字段',
    c_desc             varchar(256)                           null,
    c_use              varchar(64)                            null,
    effect             varchar(64)                            null,
    type               varchar(64)                            null,
    c_schema           text                                   null,
    encrypted_data_key text                                   not null comment '秘钥',
    constraint uk_configinfo_datagrouptenant
        unique (data_id, group_id, tenant_id)
)
    comment 'config_info' engine = InnoDB
                          collate = utf8mb3_bin;

