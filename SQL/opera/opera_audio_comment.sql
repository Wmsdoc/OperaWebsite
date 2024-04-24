create table opera_audio_comment
(
    comment_id   bigint                             not null comment '戏曲音频评论编号'
        primary key,
    audio_id     bigint                             not null comment '音频编号',
    playgoer_id  bigint                             not null comment '戏迷编号',
    comment_info varchar(200)                       not null comment '评论内容',
    is_delete    int      default 0                 null comment '是否删除',
    created_at   datetime default CURRENT_TIMESTAMP null comment '创建时间',
    updated_at   datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    created_by   bigint                             null comment '创建人',
    updated_by   bigint                             null comment '修改人'
)
    comment '戏曲音频评论表' engine = InnoDB;

