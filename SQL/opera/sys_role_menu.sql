create table sys_role_menu
(
    role_menu_id bigint not null comment '角色权限编号'
        primary key,
    role_id      bigint not null comment '角色编号',
    menu_id      bigint not null comment '权限编号'
)
    comment '角色权限关系表' engine = InnoDB;

