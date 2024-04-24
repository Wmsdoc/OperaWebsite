create table activity
(
    activity_id      bigint                             not null comment '活动编号'
        primary key,
    activity_name    varchar(50)                        not null comment '活动名称',
    activity_info    varchar(200)                       null comment '活动简介',
    activity_address varchar(100)                       null comment '活动地址',
    activity_num     int                                null comment '活动人数',
    start_time       datetime                           null comment '活动开始时间',
    end_time         datetime                           null comment '活动结束时间',
    is_delete        int      default 0                 null comment '是否删除',
    created_at       datetime default CURRENT_TIMESTAMP null comment '创建时间',
    updated_at       datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    created_by       bigint                             null comment '创建人',
    updated_by       bigint                             null comment '修改人'
)
    comment '活动表' engine = InnoDB;

