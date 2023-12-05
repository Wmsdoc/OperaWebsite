package com.opera.activity.domain.vo;

import com.opera.activity.domain.Activity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ActivityVO extends Activity {
    //发布者id
    private Long playgoerId;
    //发布者头像
    private String playgoerAvatar;
    //发布者昵称
    private String playgoerName;
    //发布者签名
    private String playgoerInfo;
    //活动剩余名额
    private Integer activityQuota;
}
