create table sys_account_role
(
    account_role_id bigint not null comment '账号角色编号'
        primary key,
    account_id      bigint not null comment '账号编号',
    role_id         bigint not null comment '角色编号'
)
    comment '账号角色关系表' engine = InnoDB;

