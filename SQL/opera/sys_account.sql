create table sys_account
(
    account_id       bigint                             not null
        primary key,
    account_username varchar(16)                        not null comment '戏迷登录用户名（4-16位）',
    account_password varchar(32)                        not null comment '戏迷登录密码（4-16位）
',
    account_statue   int      default 0                 not null comment '账号状态 （默认0 未禁用 1已禁用）',
    playgoer_id      bigint                             null comment '戏迷个人信息编号',
    is_delete        int      default 0                 null comment '是否删除 （默认0 未删除 1已删除）',
    created_at       datetime default CURRENT_TIMESTAMP null comment '账号创建时间',
    updated_at       datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '账号更新时间',
    created_by       bigint                             null comment '创建人',
    updated_by       bigint                             null comment '修改人'
)
    comment '戏迷账号表' engine = InnoDB;

