create table tenant_info
(
    id            bigint auto_increment comment 'id'
        primary key,
    kp            varchar(128)            not null comment 'kp',
    tenant_id     varchar(128) default '' null comment 'tenant_id',
    tenant_name   varchar(128) default '' null comment 'tenant_name',
    tenant_desc   varchar(256)            null comment 'tenant_desc',
    create_source varchar(32)             null comment 'create_source',
    gmt_create    bigint                  not null comment '创建时间',
    gmt_modified  bigint                  not null comment '修改时间',
    constraint uk_tenant_info_kptenantid
        unique (kp, tenant_id)
)
    comment 'tenant_info' engine = InnoDB
                          collate = utf8mb3_bin;

create index idx_tenant_id
    on tenant_info (tenant_id);

