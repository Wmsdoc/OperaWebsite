create table sys_menu_item
(
    item_id     bigint                             not null comment '权限编号'
        primary key,
    item_remark varchar(20)                        null comment '权限描述',
    item_url    varchar(30)                        null comment '权限路径',
    is_delete   int      default 0                 null comment '是否删除（默认 0 未删除 1 已删除）',
    created_at  datetime default CURRENT_TIMESTAMP null comment '创建时间',
    updated_at  datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    created_by  bigint                             null comment '创建人',
    updated_by  bigint                             null comment '修改人'
)
    comment '权限表' engine = InnoDB;

