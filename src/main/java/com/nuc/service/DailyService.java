package com.nuc.service;

import com.nuc.entity.*;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * @author 福尔摩东
 * @date 2022/5/12 19:44
 * @公众号 IT云家
 * @Github https://github.com/zzdnb
 * @博客 https://blog.csdn.net/qq_43688587
 * @网站 https://blog.zzdnb.cn
 */

public interface DailyService {
    List<DailyTraffic> selectDailyTraffic() throws SQLException;
    List<DailyTrafficPeak> selectDailyTrafficPeak() throws SQLException;
    List<DailySubmitNum> selectDailySubmitNum() throws SQLException;
    List<DailyRatio> selectDailyRatio() throws SQLException;
    List<DailyCompletion> selectDailyCompletion() throws SQLException;
    List<DailyCount> selectDailyCount() throws SQLException;
    List<WeekCount> selectWeekCount() throws SQLException;
    List<DailyMotto> selectDailyMotto() throws SQLException;
}
