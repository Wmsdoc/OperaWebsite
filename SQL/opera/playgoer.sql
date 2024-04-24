create table playgoer
(
    playgoer_id       bigint                                               not null comment '戏迷编号'
        primary key,
    playgoer_name     varchar(20)                                          null comment '戏迷用户名（4-20位）',
    playgoer_info     varchar(100) default '系统原装签名，送给每一个小可爱' null comment '戏迷个人简介',
    playgoer_sex      int          default 0                               null comment '戏迷性别 （0 保密 1 男 2 女）',
    playgoer_birthday date                                                 null comment '戏迷出生年月',
    playgoer_avatar   varchar(100)                                         null comment '戏迷头像ID',
    is_delete         int          default 0                               null comment '是否删除',
    created_at        datetime     default CURRENT_TIMESTAMP               null comment '创建时间',
    updated_at        datetime     default CURRENT_TIMESTAMP               null on update CURRENT_TIMESTAMP comment '更新时间',
    created_by        bigint                                               null comment '创建人',
    updated_by        bigint                                               null comment '修改人'
)
    comment '戏迷个人信息表' engine = InnoDB;

