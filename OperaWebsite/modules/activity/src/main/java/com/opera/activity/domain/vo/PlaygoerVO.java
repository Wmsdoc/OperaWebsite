package com.opera.activity.domain.vo;

import com.opera.activity.domain.Playgoer;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PlaygoerVO extends Playgoer {

    private String accountUsername;

    private Integer accountStatue;
}
