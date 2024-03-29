package com.opera.opera.utils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.opera.common.core.util.DateUtil;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @param <T>
 * @description: 查询条件构造器, 由于查询戏曲
 */
public class QueryOpera<T> {


    public QueryWrapper<T> structure(int typeId, int timeFlag, String filename) throws ParseException {
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        //构造查询条件
        if (!ObjectUtils.isEmpty(filename)) {
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
                    endTime = "2022-01-01";
                    queryWrapper.ge("created_at", sdf.parse(endTime));
                    queryWrapper.lt("created_at", sdf.parse(stateTime));
                    break;
                case 3:
                    stateTime = "2022-01-01";
                    endTime = "2021-01-01";
                    queryWrapper.ge("created_at", sdf.parse(endTime));
                    queryWrapper.lt("created_at", sdf.parse(stateTime));
                    break;
                case 4:
                    stateTime = "2021-01-01";
                    endTime = "2020-01-01";
                    queryWrapper.ge("created_at", sdf.parse(endTime));
                    queryWrapper.lt("created_at", sdf.parse(stateTime));
                    break;
                case 5:
                    stateTime = "2020-01-01";
                    endTime = "2016-01-01";
                    queryWrapper.ge("created_at", sdf.parse(endTime));
                    queryWrapper.lt("created_at", sdf.parse(stateTime));
                    break;
                case 6:
                    endTime = "2016-01-01";
                    queryWrapper.lt("created_at", sdf.parse(endTime));
            }
        }
        //根据修改时间降序排列
        queryWrapper.orderByDesc("updated_at");
        return queryWrapper;
    }

    //构造 一周 一月 一年的 查询条件
    public QueryWrapper<T> temporalConstructs(QueryWrapper<T> queryWrapper, String time) {
        switch (time) {
            case "week":
                //过去一周
                String week = DateUtil.getWeek();
                queryWrapper.ge("created_at", week);
                break;
            case "month":
                //过去一月
                String mon = DateUtil.getMonth();
                queryWrapper.ge("created_at", mon);
                break;
            case "year":
                //过去一年
                String year = DateUtil.getYear();
                queryWrapper.ge("created_at", year);
                break;
            default:
                break;
        }
        return queryWrapper;
    }
}
