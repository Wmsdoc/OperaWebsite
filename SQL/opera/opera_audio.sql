create table opera_audio
(
    audio_id     bigint                             not null comment '戏曲编号'
        primary key,
    audio_info   varchar(200)                       null comment '戏曲简介',
    type_id      bigint                             null comment '类型编号',
    download_url varchar(100)                       null,
    download_num int      default 0                 null comment '戏曲音频下载量',
    filename     varchar(60)                        null comment '视频文件名称',
    is_examine   int      default 0                 not null comment '是否审核',
    is_delete    int      default 0                 null comment '是否删除',
    created_at   datetime default CURRENT_TIMESTAMP null comment '创建时间',
    updated_at   datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    created_by   bigint                             null comment '创建人',
    updated_by   bigint                             null comment '修改人'
)
    comment '戏曲音频表' engine = InnoDB;

