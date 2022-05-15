package com.nuc.service.impl;

import com.nuc.entity.*;
import com.nuc.mapper.DailyMapper;
import com.nuc.service.DailyService;
import org.checkerframework.checker.units.qual.A;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * @author 福尔摩东
 * @date 2022/5/12 19:45
 * @公众号 IT云家
 * @Github https://github.com/zzdnb
 * @博客 https://blog.csdn.net/qq_43688587
 * @网站 https://blog.zzdnb.cn
 */
@Service
public class DailyServiceImpl implements DailyService {
    @Autowired
    DailyMapper dailyMapper;

    @Override
    public List<DailyTraffic> selectDailyTraffic() throws SQLException {
        return dailyMapper.selectDailyTraffic();
    }

    @Override
    public List<DailyTrafficPeak> selectDailyTrafficPeak() throws SQLException {
        return dailyMapper.selectDailyTrafficPeak();
    }

    @Override
    public List<DailySubmitNum> selectDailySubmitNum() throws SQLException {
        return dailyMapper.selectDailySubmitNum();
    }

    @Override
    public List<DailyRatio> selectDailyRatio() throws SQLException {
        return dailyMapper.selectDailyRatio();
    }

    @Override
    public List<DailyCompletion> selectDailyCompletion() throws SQLException {
        return dailyMapper.selectDaliyCompletion();
    }

    @Override
    public List<DailyCount> selectDailyCount() throws SQLException {
        return dailyMapper.selectDailyCount();
    }

    @Override
    public List<WeekCount> selectWeekCount() throws SQLException {
        return dailyMapper.selectWeekCount();
    }

    @Override
    public List<DailyMotto> selectDailyMotto() throws SQLException {
        return dailyMapper.selectDailyMotto();
    }
}
