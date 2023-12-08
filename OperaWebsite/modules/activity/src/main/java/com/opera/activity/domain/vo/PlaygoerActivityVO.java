package com.opera.activity.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.opera.activity.domain.PlaygoerActivity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class PlaygoerActivityVO extends PlaygoerActivity {
    /**
     * 活动名称
     */
    private String activityName;
    /**
     * 活动简介
     */
    private String activityInfo;
    /**
     * 活动开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date startTime;
    /**
     * 活动地址
     */
    private String activityAddress;
}
