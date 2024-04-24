create table playgoer_activity
(
    playgoer_activity_id bigint                             not null comment '戏迷活动编号'
        primary key,
    activity_id          bigint                             not null comment '活动编号',
    playgoer_id          bigint                             not null comment '戏迷编号',
    is_delete            int      default 0                 null comment '是否删除 （默认0 未删除 1 已删除）',
    created_at           datetime default CURRENT_TIMESTAMP null comment '创建时间',
    updated_at           datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    created_by           bigint                             null comment '创建人',
    updated_by           bigint                             null comment '修改人'
)
    comment '戏迷活动关系表' engine = InnoDB;

