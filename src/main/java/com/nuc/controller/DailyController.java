package com.nuc.controller;

import com.nuc.entity.*;
import com.nuc.service.DailyService;
import org.checkerframework.checker.units.qual.A;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

/**
 * @author 福尔摩东
 * @date 2022/5/15 10:30
 * @公众号 IT云家
 * @Github https://github.com/zzdnb
 * @博客 https://blog.csdn.net/qq_43688587
 * @网站 https://blog.zzdnb.cn
 */
@RestController
public class DailyController {
    @Autowired
    DailyService dailyService;
    @GetMapping("/daily/selectDailyTraffic")
    List<DailyTraffic> selectDailyTraffic() throws SQLException{
        return dailyService.selectDailyTraffic();
    }
    @GetMapping("/daily/selectDailyTrafficPeak")
    List<DailyTrafficPeak> selectDailyTrafficPeak() throws SQLException{
        return dailyService.selectDailyTrafficPeak();
    }
    @GetMapping("/daily/selectDailySubmitNum")
    List<DailySubmitNum> selectDailySubmitNum() throws SQLException{
        return dailyService.selectDailySubmitNum();
    }
    @GetMapping("/daily/selectDailyRatio")
    List<DailyRatio> selectDailyRatio() throws SQLException{
        return dailyService.selectDailyRatio();
    }
    @GetMapping("/daily/selectDailyCompletion")
    List<DailyCompletion> selectDailyCompletion() throws SQLException{
        return dailyService.selectDailyCompletion();
    }
    @GetMapping("/daily/selectDailyCount")
    List<DailyCount> selectDailyCount() throws SQLException{
        return dailyService.selectDailyCount();
    }
    @GetMapping("/daily/selectWeekCount")
    List<WeekCount> selectWeekCount() throws SQLException{
        return dailyService.selectWeekCount();
    }
    @GetMapping("/daily/selectDailyMotto")
    List<DailyMotto> selectDailyMotto() throws SQLException{
        return dailyService.selectDailyMotto();
    }
}
