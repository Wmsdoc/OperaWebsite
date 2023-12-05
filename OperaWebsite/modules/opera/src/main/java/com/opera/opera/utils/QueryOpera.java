package com.opera.opera.utils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @description: 查询条件构造器,由于查询戏曲
 * @param <T>
 */
public class QueryOpera<T> {

    public QueryWrapper<T> structure( int typeId, int timeFlag, String filename) throws ParseException {
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        //构造查询条件
        if (filename != null) {
            queryWrapper.like("filename", filename);
        }
        if (typeId != 0) {
            queryWrapper.eq("type_id", typeId);
        }
        if (timeFlag != 0) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String stateTime = "2023-01-01";
            String endTime = "2023-01-01";
            switch (timeFlag) {
                case 1:
                    queryWrapper.ge("created_at", sdf.parse(endTime));
                    break;
                case 2:
                    endTime= "2022-01-01";
                    queryWrapper.ge("created_at", sdf.parse(endTime));
                    queryWrapper.lt("created_at", sdf.parse(stateTime));
                    break;
                case 3:
                    stateTime = "2022-01-01";
                    endTime= "2021-01-01";
                    queryWrapper.ge("created_at", sdf.parse(endTime));
                    queryWrapper.lt("created_at", sdf.parse(stateTime));
                    break;
                case 4:
                    stateTime = "2021-01-01";
                    endTime= "2020-01-01";
                    queryWrapper.ge("created_at", sdf.parse(endTime));
                    queryWrapper.lt("created_at", sdf.parse(stateTime));
                    break;
                case 5:
                    stateTime = "2020-01-01";
                    endTime= "2016-01-01";
                    queryWrapper.ge("created_at", sdf.parse(endTime));
                    queryWrapper.lt("created_at", sdf.parse(stateTime));
                    break;
                case 6:
                    endTime= "2016-01-01";
                    queryWrapper.lt("created_at", sdf.parse(endTime));
            }
        }
        //根据修改时间降序排列
        queryWrapper.orderByDesc("updated_at");
        return queryWrapper;
    }
}
