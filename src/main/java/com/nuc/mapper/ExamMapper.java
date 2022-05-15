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
public class ExamMapper {

    private static final Logger logger = LoggerFactory.getLogger(DailyMapper.class);

    @Autowired
    @Qualifier("hiveDruidDataSource")
    DataSource druidDataSource;

    public List<ExamRatio> selectExamRatio() throws SQLException {
        Statement statement = druidDataSource.getConnection().createStatement();
        String sql1 = "select count(issubmit) from exam where issubmit = 0";
        String sql2 = "select count(issubmit) from exam where issubmit = 1";
        logger.info("Running: " + sql1 + " " + sql2 + " " );
        ResultSet resultSet1 = statement.executeQuery(sql1);
        ResultSet resultSet2 = statement.executeQuery(sql2);
        ExamRatio examRatio = new ExamRatio();
        ArrayList<ExamRatio> list = new ArrayList<>();
        examRatio.setId(1);
        examRatio.setNonExamNumber(resultSet1.getInt(1));
        examRatio.setExamNumber(resultSet2.getInt(1));
        list.add(examRatio);
        return list;
    }
    public List<ExamSubjectiveScore> selectExamSubjectiveScore() throws SQLException {
        Statement statement = druidDataSource.getConnection().createStatement();
        String sql = "select studentno from exam order by subjectivescore desc limit 5";
        logger.info("Running: " + sql);
        ResultSet res = statement.executeQuery(sql);
        List<ExamSubjectiveScore> list = new ArrayList<>();
        int id = 1;
        while (res.next()) {
            ExamSubjectiveScore examSubjectiveScore = new ExamSubjectiveScore();
            examSubjectiveScore.setId(id++);
            examSubjectiveScore.setStudentNo(res.getInt(1));
            list.add(examSubjectiveScore);
        }
        return list;
    }
    public List<ExamObjectiveScore> selectExamObjectiveScore() throws SQLException {
        Statement statement = druidDataSource.getConnection().createStatement();
        String sql = "select studentno from exam order by objectivescore desc limit 5";
        logger.info("Running: " + sql);
        ResultSet res = statement.executeQuery(sql);
        List<ExamObjectiveScore> list = new ArrayList<>();
        int id = 1;
        while (res.next()) {
            ExamObjectiveScore examObjectiveScore = new ExamObjectiveScore();
            examObjectiveScore.setId(id++);
            examObjectiveScore.setStudentNo(res.getInt(1));
            list.add(examObjectiveScore);
        }
        return list;
    }
    public List<ExamScore> selectExamScore() throws SQLException {
        Statement statement = druidDataSource.getConnection().createStatement();
        String sql = "select studentno from exam order by score desc limit 5";
        logger.info("Running: " + sql);
        ResultSet res = statement.executeQuery(sql);
        List<ExamScore> list = new ArrayList<>();
        int id = 1;
        while (res.next()) {
            ExamScore examScore = new ExamScore();
            examScore.setId(id++);
            examScore.setStudentNo(res.getInt(1));
            list.add(examScore);
        }
        return list;
    }
    //参加考试人最多的前五场考试：
    public List<ExamCount> selectExamCount() throws SQLException {
        Statement statement = druidDataSource.getConnection().createStatement();
        String sql = "select paperno ,count(paperno) as cnt from exam group by paperno order by cnt desc limit 5";
        logger.info("Running: " + sql);
        ResultSet res = statement.executeQuery(sql);
        List<ExamCount> list = new ArrayList<>();
        int id = 1;
        while (res.next()) {
            ExamCount examCount = new ExamCount();
            examCount.setId(id++);
            examCount.setPaperNo(Integer.valueOf(res.getString(1).trim()));
            examCount.setCount((res.getInt(2)));
            list.add(examCount);
        }
        return list;
    }
    //参加考试次数最多的五位：
    public List<ExamCountStudent> selectExamCountStudent() throws SQLException {
        Statement statement = druidDataSource.getConnection().createStatement();
        String sql = "select studentno ,count(studentno) as cnt from exam where issubmit != 0 group by studentno order by cnt desc limit 5";
        logger.info("Running: " + sql);
        ResultSet res = statement.executeQuery(sql);
        List<ExamCountStudent> list = new ArrayList<>();
        int id = 1;
        while (res.next()) {
            ExamCountStudent examCountStudent = new ExamCountStudent();
            examCountStudent.setId(id++);
            examCountStudent.setStudentNo((res.getInt(1)));
            examCountStudent.setCount((res.getInt(2)));
            list.add(examCountStudent);
        }
        return list;
    }
}
