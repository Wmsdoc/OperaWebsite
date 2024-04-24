create table collection_audio
(
    collection_id bigint                             not null comment '收藏音频编号'
        primary key,
    playgoer_id   bigint                             null comment '戏迷编号',
    audio_id      bigint                             null comment '戏曲音频编号',
    is_delete     int      default 0                 null comment '是否删除',
    created_at    datetime default CURRENT_TIMESTAMP null comment '创建时间',
    updated_at    datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    created_by    bigint                             null comment '创建人',
    updated_by    bigint                             null comment '修改人'
)
    comment '戏迷戏曲音频收藏关系表' engine = InnoDB;

