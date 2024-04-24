create table sys_role
(
    role_id     bigint      not null comment '角色编号'
        primary key,
    role_name   varchar(16) not null comment '角色名',
    role_remark varchar(16) not null comment '角色描述'
)
    comment '角色表' engine = InnoDB;

