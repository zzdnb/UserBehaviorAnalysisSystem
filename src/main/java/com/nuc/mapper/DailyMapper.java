package com.nuc.mapper;

import com.nuc.entity.*;
import org.mapstruct.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 福尔摩东
 * @date 2022/5/12 19:47
 * @公众号 IT云家
 * @Github https://github.com/zzdnb
 * @博客 https://blog.csdn.net/qq_43688587
 * @网站 https://blog.zzdnb.cn
 */
@Repository
@CrossOrigin
public class DailyMapper {
    private static final Logger logger = LoggerFactory.getLogger(DailyMapper.class);

    @Autowired
    @Qualifier("hiveDruidDataSource")
    DataSource druidDataSource;


    public List<DailyTraffic> selectDailyTraffic() throws SQLException {
        Statement statement = druidDataSource.getConnection().createStatement();
        String sql = "select from_unixtime(unix_timestamp(ruleTime),'yyyy-MM-dd'),count(from_unixtime(unix_timestamp(ruleTime),'yyyy-MM-dd')) from daily group by ruleTime ";
        logger.info("Running: " + sql);
        ResultSet res = statement.executeQuery(sql);
        List<DailyTraffic> list = new ArrayList<>();
        int id = 1;
        while (res.next()) {
            DailyTraffic dailyTraffic = new DailyTraffic();
            dailyTraffic.setId(id++);
            dailyTraffic.setDate(res.getDate(1));
            dailyTraffic.setCount(res.getInt(2));
            list.add(dailyTraffic);
        }
        return list;
    }

    public List<DailyTrafficPeak> selectDailyTrafficPeak() throws SQLException {
        Statement statement = druidDataSource.getConnection().createStatement();
        String sql = "select hour,count from case22 where count = (select max(count) from case22)";
        logger.info("Running: " + sql);
        ResultSet res = statement.executeQuery(sql);
        List<DailyTrafficPeak> list = new ArrayList<>();
        int id = 1;
        while (res.next()) {
            DailyTrafficPeak dailyTraffic = new DailyTrafficPeak();
            dailyTraffic.setId(id++);
            dailyTraffic.setHour(res.getInt(1));
            dailyTraffic.setCount(res.getInt(2));
            list.add(dailyTraffic);
        }
        return list;
    }

    public List<DailySubmitNum> selectDailySubmitNum() throws SQLException {
        Statement statement = druidDataSource.getConnection().createStatement();
        String sql = "select hour, count(count) from case2 group by hour";
        logger.info("Running: " + sql);
        ResultSet res = statement.executeQuery(sql);
        List<DailySubmitNum> list = new ArrayList<>();
        int id = 1;
        while (res.next()) {
            DailySubmitNum dailySubmitNum = new DailySubmitNum();
            dailySubmitNum.setId(id++);
            dailySubmitNum.setHour(res.getInt(1));
            dailySubmitNum.setCount(res.getInt(2));
            list.add(dailySubmitNum);
        }
        return list;
    }



    public List<DailyRatio> selectDailyRatio() throws SQLException {
        Statement statement = druidDataSource.getConnection().createStatement();
        String sql1 = "select count(isdaily) from daily where isdaily = 0";
        String sql2 = "select count(isdaily) from daily where isdaily = 1";
        String sql3 = "select count(isdaily) from exam ";
        logger.info("Running: " + sql1 + " "+sql2+" "+sql3);
        ResultSet resultSet1 = statement.executeQuery(sql1);
        ResultSet resultSet2 = statement.executeQuery(sql2);
        ResultSet resultSet3 = statement.executeQuery(sql3);
        DailyRatio dailyRatio = new DailyRatio();
        ArrayList<DailyRatio> list = new ArrayList<>();
        dailyRatio.setId(1);
        dailyRatio.setDaily(resultSet1.getInt(1));
        dailyRatio.setWeek(resultSet2.getInt(2));
        dailyRatio.setSum(resultSet3.getInt(3));
        list.add(dailyRatio);
        return list;
    }

    public List<DailyCompletion> selectDaliyCompletion() throws SQLException {
        Statement statement = druidDataSource.getConnection().createStatement();
        String sql = "select studentno ,count(completion) as cnt from daily where completion = 100 group by studentno order by cnt desc limit 5";
        logger.info("Running: " + sql);
        ResultSet res = statement.executeQuery(sql);
        List<DailyCompletion> list = new ArrayList<>();
        int id = 1;
        while (res.next()) {
            DailyCompletion dailyCompletion = new DailyCompletion();
            dailyCompletion.setId(id++);
            dailyCompletion.setStudentNo(res.getInt(1));
            dailyCompletion.setCount(res.getInt(2));
            list.add(dailyCompletion);
        }
        return list;
    }
    public List<DailyCount> selectDailyCount() throws SQLException {
        Statement statement = druidDataSource.getConnection().createStatement();
        String sql = "select classno ,count(classno) as cnt from daily where isdaily = 0 group by classno order by cnt desc limit 5";
        logger.info("Running: " + sql);
        ResultSet res = statement.executeQuery(sql);
        List<DailyCount> list = new ArrayList<>();
        int id = 1;
        while (res.next()) {
            DailyCount dailyCount = new DailyCount();
            dailyCount.setId(id++);
            dailyCount.setClassNo(res.getInt(1));
            dailyCount.setCount(res.getInt(2));
            list.add(dailyCount);
        }
        return list;
    }
    public List<WeekCount> selectWeekCount() throws SQLException {
        Statement statement = druidDataSource.getConnection().createStatement();
        String sql = "select classno ,count(classno) as cnt from daily where isdaily = 1 group by classno order by cnt desc limit 5";
        logger.info("Running: " + sql);
        ResultSet res = statement.executeQuery(sql);
        List<WeekCount> list = new ArrayList<>();
        int id = 1;
        while (res.next()) {
            WeekCount weekCount = new WeekCount();
            weekCount.setId(id++);
            weekCount.setClassNo(res.getInt(1));
            weekCount.setCount(res.getInt(2));
            list.add(weekCount);
        }
        return list;
    }
    public List<DailyMotto> selectDailyMotto() throws SQLException {
        Statement statement = druidDataSource.getConnection().createStatement();
        String sql = "select studentno ,motto from daily where length(motto) = 5 limit 5";
        logger.info("Running: " + sql);
        ResultSet res = statement.executeQuery(sql);
        List<DailyMotto> list = new ArrayList<>();
        int id = 1;
        while (res.next()) {
            DailyMotto dailyMotto = new DailyMotto();
            dailyMotto.setId(id++);
            dailyMotto.setStudentNo(res.getInt(1));
            dailyMotto.setMotto(res.getString(2));
            list.add(dailyMotto);
        }
        return list;
    }
}
